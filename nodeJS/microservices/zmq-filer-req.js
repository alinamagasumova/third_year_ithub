const zmq = require('zeromq');
const filename = process.argv[2];
const requester = zmq.socket('req');

requester.connect('tcp://localhost:60123');

requester.on('message', data => {
    const response = JSON.parse(data);
    response.timestamp = new Date(response.timestamp)
    console.log('>', response);
});

console.log('sending request on ' + filename);
requester.send(JSON.stringify({path: filename}))