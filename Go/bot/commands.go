package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"net/http"
	"strings"
)

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

func ChangeName(lastId int, ev UpdateStruct, txt string, nickname *string) int {
	new := strings.Split(txt, "измени обращение на: ")
	*nickname = new[1]

	db := connectDb()
	defer db.Close()
	result, err := db.Exec(`update bot_status set name = $1`, *nickname)
	if err != nil {
		panic(err)
	}
	fmt.Println(result.RowsAffected())

	txtmsg := SendMessage{
		ChId:        ev.Message.Chat.Id,
		Text:        "Теперь я " + *nickname,
		Reply_to_id: ev.Message.Id,
	}

	bytemsg, _ := json.Marshal(txtmsg)
	_, err = http.Post(apiUrl+"/sendMessage", "application/json", bytes.NewReader(bytemsg))
	if err != nil {
		fmt.Println(err)
		return lastId
	} else {
		return ev.Id + 1
	}
}

func WAY(lastId int, ev UpdateStruct, nickname *string) int {
	txtmsg := SendMessage{
		ChId:        ev.Message.Chat.Id,
		Text:        "Я " + *nickname,
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
