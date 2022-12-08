const app = require('express')();
const io = require('socket.io')(app.listen(3000));

app.get('/', (req, res) => {
    res.sendFile(__dirname+'/index.html')
});

io.sockets.on('connection', socket => {
    console.log('connected');
    socket.emit('message', 'Guest')
    socket.on('message', data => console.log(data));

    // io.sockets.emit('for-all', 'Hello everybody');
    socket.broadcast.emit('hello', 'someone has joined')
    socket.on('disconnect', ()=>{
        io.sockets.emit('close', 'someone quit')
    })
})
