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
	lastid := 0
	nickname := "коть"
	for {
		lastid = Update(lastid, &nickname)
		time.Sleep(5 * time.Second)
	}
}

func Update(lastid int, nickname *string) int {
	raw, err := http.Get(apiUrl + "/getUpdates?offset=" + strconv.Itoa(lastid))
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
		txtmsg := SendMessage{
			ChId:        ev.Message.Chat.Id,
			Text:        "Обратись ко мне корректно! Меня зовут "+*nickname,
			Reply_to_id: ev.Message.Id,
		}

		if strings.Contains(strings.ToLower(txt), *nickname) {
			if strings.Contains(strings.ToLower(txt), "дай гороскоп на деву") {
				txtmsg = SendMessage{
					ChId:        ev.Message.Chat.Id,
					Text:        "Сегодня чудный день для исполнения своих мечт",
					Reply_to_id: ev.Message.Id,
				}
			} else if strings.Contains(strings.ToLower(txt), "расскажи анекдот") {
				txtmsg = SendMessage{
					ChId:        ev.Message.Chat.Id,
					Text:        "Колобок повесился",
					Reply_to_id: ev.Message.Id,
				}
			} else {
				txtmsg = SendMessage{
					ChId:        ev.Message.Chat.Id,
					Text:        "Что нужно сделать?",
					Reply_to_id: ev.Message.Id,
				}
			}		
		}


		if strings.Contains(strings.ToLower(txt), "я хочу поменять обращение на") {
			if len(strings.SplitAfter(txt, "я хочу поменять обращение на ")) > 1 {
				*nickname = strings.SplitAfter(txt, "я хочу поменять обращение на ")[1]
				txtmsg = SendMessage{
					ChId:        ev.Message.Chat.Id,
					Text:        "Теперь я "+*nickname,
					Reply_to_id: ev.Message.Id,
				}
			} else {
				txtmsg = SendMessage{
					ChId:        ev.Message.Chat.Id,
					Text:        "Нормально назови",
					Reply_to_id: ev.Message.Id,
				}
			}

		}


		bytemsg, _ := json.Marshal(txtmsg)
				_, err := http.Post(apiUrl+"/sendMessage", "application/json", bytes.NewReader(bytemsg))
				if err != nil {
					fmt.Println(err)
					return lastid
				} else {
					return ev.Id + 1
				}
		
	}

	return lastid
}
