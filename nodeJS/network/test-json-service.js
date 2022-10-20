const server = require('net').createServer(cnn => {
    console.log('someone has joined');
    const msgFirst = '{"type": "changed", "timesta';
    const msgSecond = 'mp": 1234567890}\n';
    cnn.write(msgFirst);
    const timer = setTimeout(()=>{
        cnn.write(msgSecond);
        cnn.end
    }, 100);
    cnn.on('end', () => {
        clearTimeout(timer);
        console.log('client has gone away');
    })
});
server.listen(3870, () => console.log('test servise hosted'))