const zmq = require('zeromq');
const fs = require('fs');
const filename = process.argv[2];
const publisher = zmq.socket('pub');

publisher.bind('tcp://127.0.0.1:60123', err => {
    if(err) throw err;
    console.log('waiting for subscribers');
});

fs.watchFile(filename, () => {
    const message_1 = JSON.stringify({
        type: 'changed',
        file: filename,
    });
    const message_2 = JSON.stringify({
        type: 'changed',
        timestamp: Date.now()
    })
    console.log('>', message_1, message_2)
    publisher.send(['ByFileName', message_1]);
    publisher.send(['ByDate', message_2]);
});

