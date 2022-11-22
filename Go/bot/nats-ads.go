package main

import (
	"encoding/json"
	"fmt"

	"github.com/nats-io/nats.go"
)

type NatsAd struct {
	Msg string `json: "msg"`
	Time int `json: "time"`
}

func initiateNats() {
	nc, _ := nats.Connect("nats://95.165.107.100:4222")
	nc.Subscribe("ith.bot.ads", func(msg *nats.Msg) {
	// decode msg
	// send msg to all chats in cache
	// reply to nats msg with result
	msgbytes := msg.Data
	var receivedMessage NatsAd
	err := json.Unmarshal(msgbytes, &receivedMessage)
	if err != nil {
		fmt.Println(err)
	}
	})
	// drop to gb
}