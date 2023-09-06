package main

import (
	"bytes"
	"crypto/md5"
	"database/sql"
	"encoding/hex"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"strconv"
	"strings"
	"time"

	"github.com/gorilla/mux"
	_ "github.com/lib/pq"
)

const (
	port     = 5432
	user     = "postgres"
	password = "123"
	dbname   = "postgres"
	apiUrl   = "https://api.telegram.org/" + "bot5593551307:AAH4knPtYPOsgu9SkvEXmJ5C4UoeifqY6Io"
)

var idcache map[int]ChatInfo = make(map[int]ChatInfo)

func main() {
	initCache()
	go UpdateLoop()
	go initiateNats()
	// go cronProccess()
	router := mux.NewRouter()
	router.HandleFunc("/api", IndexHandler)
	router.HandleFunc("/botName", NameHandler)
	router.HandleFunc("/lastId", LastIdHandler)
	router.HandleFunc("/login", Login)
	router.HandleFunc("/register", Register)
	router.HandleFunc("/postAd", PostAdHandler)
	router.PathPrefix("/").Handler(http.FileServer(http.Dir("./static/")))
	http.ListenAndServe("localhost:8080", router)
}

func IndexHandler(w http.ResponseWriter, r *http.Request) {
	var M MainStruct

	resp, err := http.Get(apiUrl + "/getMe")
	if err != nil {
		fmt.Println(err)
	}
	respBody, _ := io.ReadAll(resp.Body)

	err = json.Unmarshal(respBody, &M) // заполнили перемнную m
	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
	}
	M.Result.Abilities = append(M.Result.Abilities, "Replying to messages", "test")

	respReady, err := json.Marshal(M.Result)
	if err != nil {
		panic(err)
	}

	w.Write([]byte(respReady))
}

func NameHandler(w http.ResponseWriter, _ *http.Request) {
	db := connectDb()
	defer db.Close()
	var gotname string
	var resp sql.NullString // для результата
	err := db.QueryRow("SELECT name FROM bot_status").Scan(&resp)
	if err != nil {
		fmt.Println(err)
	}
	if resp.Valid { // если результат валид
		gotname = resp.String // берём оттуда обычный string
	}
	w.Write([]byte(gotname))
}

func Login(w http.ResponseWriter, r *http.Request) {
	reqBody, err := io.ReadAll(r.Body)
	if err != nil {
		fmt.Println(err)
		w.WriteHeader(http.StatusBadRequest)
		w.Write([]byte("BAD REQUEST"))
	}

	var data UserLogin
	err = json.Unmarshal(reqBody, &data)
	if err != nil {
		fmt.Println(err)
		w.WriteHeader(http.StatusBadRequest)
		w.Write([]byte("BAD REQUEST"))
	}

	db := connectDb()
	defer db.Close()
	var password string
	err = db.QueryRow("select password from admins where username = ?", data.Username).Scan(&password)
	if err != nil {
		if err != sql.ErrNoRows {
			fmt.Println(err)
			w.WriteHeader(http.StatusInternalServerError)
			w.Write([]byte("INTERNAL DATABASE ERROR"))
		} else {
			fmt.Println(err)
			w.WriteHeader(http.StatusConflict)
			w.Write([]byte("NO SUCH USER"))
		}
	}
	hash := md5.Sum([]byte(data.Password))
	hashPass := hex.EncodeToString(hash[:])
	if hashPass == password {
		fmt.Println("user is ok")
	} else {
		fmt.Println("password is not correct")
	}
}

func Register(w http.ResponseWriter, r *http.Request) {
	reqBody, err := io.ReadAll(r.Body)
	if err != nil {
		fmt.Println(err)
		w.WriteHeader(http.StatusBadRequest)
		w.Write([]byte("BAD REQUEST"))
	}

	var data UserLogin
	err = json.Unmarshal(reqBody, &data)
	if err != nil {
		fmt.Println(err)
		w.WriteHeader(http.StatusBadRequest)
		w.Write([]byte("BAD REQUEST"))
	}

	db := connectDb()
	defer db.Close()

	rows, err := db.Query("select id from admins where username = ?", data.Username)
	if err != nil {
		fmt.Println(err)
		w.WriteHeader(http.StatusInternalServerError)
		w.Write([]byte("INTERNAL DATABASE ERROR"))
	}

	if rows.Next() {
		fmt.Println("User already exists")
		w.WriteHeader(http.StatusConflict)
		w.Write([]byte("USER EXISTS"))
	} else {
		hash := md5.Sum([]byte(data.Password))
		hashString := hex.EncodeToString(hash[:])
		_, err = db.Exec("INSERT INTO admins (username, password) VALUES (?, ?)", data.Username, hashString)
		if err != nil {
			fmt.Println(err)
			w.WriteHeader(http.StatusInternalServerError)
			w.Write([]byte("INTERNAL DATABASE ERROR"))
		}
	}
}

func LastIdHandler(w http.ResponseWriter, _ *http.Request) {
	db := connectDb()
	defer db.Close()
	var gotlastid string
	var resp sql.NullString // для результата
	err := db.QueryRow("SELECT lastid FROM bot_status").Scan(&resp)
	if err != nil {
		fmt.Println(err)
	}
	if resp.Valid { // если результат валид
		gotlastid = resp.String // берём оттуда обычный string
	}
	w.Write([]byte(gotlastid))
}

func UpdateLoop() {
	db := connectDb()
	defer db.Close()
	lastId := 0
	var nickname1 string
	err := db.QueryRow(`select name from bot_status`).Scan(&nickname1)
	if err != nil {
		fmt.Println(err)
	}
	for {
		newId := Update(lastId, &nickname1)
		if lastId != newId {
			lastId = newId
			db.Exec(`update bot_status set lastid = $1`, lastId)
		}
		time.Sleep(500 * time.Millisecond)
	}
}

func Update(lastId int, nickname *string) int {
	raw, err := http.Get(apiUrl + "/getUpdates?offset=" + strconv.Itoa(lastId))
	if err != nil {
		panic(err)
	}
	body, _ := io.ReadAll(raw.Body)

	var v UpdateResponse
	err = json.Unmarshal(body, &v)
	if err != nil {
		panic(err)
	}

	if len(v.Result) > 0 {
		ev := v.Result[len(v.Result)-1]
		txt := ev.Message.Text

		if !checkCache(ev.Message.Chat.Id) {
			addToCache(ev.Message.Chat.Id)
		}

		if strings.ToLower(txt) == "как тебя зовут?" {
			return WAY(lastId, ev, nickname)
		}

		if strings.Split(txt, ": ")[0] == "отзыв" {
			feedBack := strings.Split(txt, ": ")[1]
			return FeedBack(lastId, ev, feedBack)
		}
		if strings.Split(txt, ", ")[0] == *nickname {
			switch strings.Split(strings.Split(txt, ", ")[1], ": ")[0] {
			case "расскажи анекдот":
				{
					return Haha(lastId, ev)
				}
			case "дай предсказание на день":
				{
					return Predict(lastId, ev)
				}
			case "пока":
				{
					return Bye(lastId, ev)
				}
			case "измени обращение на":
				{
					if strings.Contains(txt, ": ") {
						return ChangeName(lastId, ev, txt, nickname)
					} else {
						fmt.Println(err)
					}
				}
			default:
				{
					txtmsg := SendMessage{
						ChId:        ev.Message.Chat.Id,
						Text:        "Что нужно сделать?",
						Reply_to_id: ev.Message.Id,
					}
					bytemsg, _ := json.Marshal(txtmsg)
					_, err := http.Post(apiUrl+"/sendMessage", "application/json", bytes.NewReader(bytemsg))
					if err != nil {
						fmt.Println(err)
						return lastId
					} else {
						return ev.Id + 1
					}
				}
			}
		}
	}
	return lastId
}

func connectDb() *sql.DB {
	connstr := fmt.Sprintf("user=%s port=%d password=%s dbname=%s sslmode=disable", user, port, password, dbname)
	conn, err := sql.Open("postgres", connstr)
	if err != nil {
		panic(err)
	}
	if conn == nil {
		panic("db nil")
	}
	return conn
}
