package main

import (
	"fmt"

	"github.com/nats-io/nats.go"
)

func main() {
	// Connect to a server
	nc, _ := nats.Connect("nats://95.165.107.100:4222")
	defer nc.Drain()
	// Simple Publisher
	// nc.Publish("cat-a-boo", []byte("Hello World"))

	// Simple Async Subscriber
	// nc.Subscribe("foo", func(m *nats.Msg) {
	// 	fmt.Printf("Received a message: %s\n", string(m.Data))
	// })

	// Responding to a request message
	nc.Subscribe("ith.*.students", func(m *nats.Msg) {
		m.Respond([]byte("Magasumova accepted"))
		fmt.Printf("Received a message: %s\n", string(m.Data))
	})
	for {

	}

	// Close connection
	// nc.Close()
}
