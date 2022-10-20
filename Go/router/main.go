package main

import (
	"encoding/json"
	_ "fmt"
	"io"
	"net/http"
	"strconv"
	"github.com/gorilla/mux"
)

type People struct {
	People []Person `json:"people"`
}

type Person struct {
	Name    string `json:"name"`
	Age     int    `json:"age"`
	Comment string `json:"comment"`
}

func main() {
	router := mux.NewRouter()
	router.HandleFunc("/", Handler)
	router.HandleFunc("/summary", SummaryHandler)
	http.ListenAndServe("localhost:8080", router)
}

func Handler(writer http.ResponseWriter, req *http.Request) {
	var 地图 map[string]int = make(map[string]int)
	地图["Паша"] = 2 // "Паша: 2"
	地图["Алина"] = 10
	地图["Ваня"] = 723832

	var resp People
	for i, n := range 地图 {
		// resp += i + ": " + strconv.Itoa(n) + "\n"
		pers := Person{
			Name:    i,
			Age:     n,
			Comment: "",
		}

		resp.People = append(resp.People, pers)
	}
	respReady, err := json.Marshal(resp)
	if err != nil {
		panic(err)
	}

	// writer.WriteHeader(http.StatusOK)
	writer.Header().Set("Content-Type", "application/json; charset=UTF-8")
	writer.Header().Set("Content-Disposition", "inline")
	writer.Write(respReady)
}

func SummaryHandler(w http.ResponseWriter, r *http.Request) { //количество человек, имена
	var P People

	resp, err := http.Get("http://localhost:8080/")
	if err != nil {
		panic(err)
	}

	body, _ := io.ReadAll(resp.Body)

	err = json.Unmarshal(body, &P) // заполнили перемнную п
	if err != nil {
		w.WriteHeader(http.StatusBadRequest)
	}

	// var listofnames string
	// for _, name := range p.People{
	//     listofnames += name.Name + ", "
	// }

	newResp := "Количество человек: " + strconv.Itoa(len(P.People)) + "\n" + "Имена: "

	for _, n := range P.People {
		newResp += n.Name + "; "
	}

	w.Header().Set("Content-Type", "text/plain; charset=UTF-8")
	w.Header().Set("Content-Disposition", "inline")
	w.Write([]byte(newResp))
}
