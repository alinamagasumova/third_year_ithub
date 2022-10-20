const zmq = require('zeromq');
const fs = require('fs');
const responder = zmq.socket('rep');

responder.bind('tcp://127.0.0.1:60123', err => {
    if(err) throw err;
    console.log('waiting for requests');
});

responder.on('message', data => {
    const request = JSON.parse(data);
    console.log('got a request on: ' + request.path);
    fs.readFile(request.path, (err, content)=>{
        console.log('sending a content');
        const msg = JSON.stringify({
            content: content.toString(),
            timestamp: Date.now()
        })
        responder.send(msg);
    })
})
