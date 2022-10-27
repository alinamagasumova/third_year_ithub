package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
	"strconv"
	"strings"
	"time"

	"github.com/gorilla/mux"
)

const apiUrl = "https://api.telegram.org/" + "bot5593551307:AAH4knPtYPOsgu9SkvEXmJ5C4UoeifqY6Io"

func main() {
	go UpdateLoop()
	router := mux.NewRouter()
	router.HandleFunc("/", IndexHandler)
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
	M.Result.Abilities = append(M.Result.Abilities, "Replying to message /privet", "test")

	respReady, err := json.Marshal(M.Result)
	if err != nil {
		panic(err)
	}

	w.Write([]byte(respReady))
}

func UpdateLoop() {
	lastId := 0
	nickname := "коть"
	for {
		lastId = Update(lastId, &nickname)
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
	fmt.Println(v)
	
	if len(v.Result) > 0 {
		ev := v.Result[len(v.Result)-1]
		txt := ev.Message.Text
		txtmsg := SendMessage{
			ChId:        ev.Message.Chat.Id,
			Text:        "Привет, я коть!",
			Reply_to_id: ev.Message.Id,
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
			case "измени обращение на":
				{
					if strings.Contains(txt, ": ") {
						return ChangeName(lastId, ev, txt)
					} else {
						fmt.Println(err)
					}
				}
			default:
				{
					txtmsg.Text = "Что нужно сделать?"
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

func Predict(lastId int, ev UpdateStruct) int {
	txtmsg := SendMessage{
		ChId:        ev.Message.Chat.Id,
		Text:        "Сегодня чудный день для исполнения своих мечт",
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

func ChangeName(lastId int, ev UpdateStruct, txt string) int {
	new := strings.Split(txt, "измени обращение на ")
	nickname := new[1]
	fmt.Println(nickname)
	txtmsg := SendMessage{
		ChId:        ev.Message.Chat.Id,
		Text:        "Теперь я " + nickname,
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

func Haha(lastId int, ev UpdateStruct) int {
	txtmsg := SendMessage{
		ChId:        ev.Message.Chat.Id,
		Text:        "Колобок повесился",
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

// func Ping() {
// 	txtmsg := SendMessage{
// 		ChId: 911850117,
// 		Text:    "Страницу посетили.",
// 	}

// 	bytemsg, _ := json.Marshal(txtmsg)
// 	_, err := http.Post(tgApiUrl+"/sendMessage", "application/json", bytes.NewReader(bytemsg))
// 	if err != nil {
// 		fmt.Println(err)
// 	}