const express = require('express');
const app = express();
const io = require('socket.io')(app.listen(3000));
app.use(express.static(__dirname));
app.get('/', (req, res) => {
    res.sendFile(__dirname+'/index.html')
});

let users = {};

io.sockets.on('connection', socket => {
    console.log('connected');

    socket.emit('hello', users)

    socket.on('user', userInfo => {
        users[socket.id] = [userInfo.name, userInfo.color];
        console.log('added')
        socket.broadcast.emit('join', users)
    });

    socket.on('msg', msg => {
        let message = [users[socket.id][0], users[socket.id][1], msg]
        io.sockets.emit('message', message)
    })

    socket.on('giveInfo', ()=>socket.emit('getInfo', users))

    socket.on('private', (msg)=>{
        io.to(msg.recId).emit('privateMsg', {adName: users[socket.id][0], text: msg.text});
    })

    socket.on('disconnect', ()=>{
        io.sockets.emit('close', {len: Object.keys(users).length, man: users[socket.id]})
        delete users[socket.id];
        console.log('deleted')
    })
})