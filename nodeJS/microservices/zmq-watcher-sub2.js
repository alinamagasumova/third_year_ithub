const zmq = require('zeromq');
const subscriber = zmq.socket('sub');
subscriber.connect('tcp://localhost:60123');

subscriber.subscribe('ByDate');

subscriber.on('message', (topic, data) => {
    const message = JSON.parse(data);
    const date = new Date(message.timestamp);
    console.log('file has changed ' + date)
})