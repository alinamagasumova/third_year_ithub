const zmq = require('zeromq');
const pusher = zmq.socket('push');

let counter = 0;
setInterval(()=>{
    const message = 'Hello: ' + ++counter;
    console.log(`Sent "${message}"`);
    pusher.send(message);
}, 2000);

pusher.bind('tcp://127.0.0.1:60123', err => { 
    if(err) throw err
    console.log('ok')
});