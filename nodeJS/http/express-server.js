// const express = require('express');
// const app = express()
const app = require('express')();
app.listen(80, console.log("Запустился"));

// __dirname - текущая папка
app.set("views", __dirname)
// при ejs писать необязательно, так как он умолчательный
app.set("view engine", "ejs")

app.get("/", (req, res)=>{
    res.send("<h1>Hello from express</h1>")
});
app.get("/user", (req, res)=>{
    res.send("<h1>Hello, User</h1>")
});
app.get("/user/:name", (req, res)=>{
    // let userName = req.params.name
    // res.send("<h1>Hello, " + userName + "</h1>")
    // когда есть view engine, здесь можно не писать расширение
    res.render("user", {_name: req.params.name})
});

app.get("/sum/:n1/:n2", (req, res)=>{
    let n1 = +req.params.n1
    let n2 = +req.params.n2
    res.send(`<h1>${n1} + ${n2} = ${n1 + n2}</h1>`)
});