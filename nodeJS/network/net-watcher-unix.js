const net = require('net');
const fs = require('fs');
const filename = process.argv[2];

if (!filename) throw Error('Enter the file name!!');

net.createServer(cnn => {

    console.log('someone has joined');
    cnn.write(`You are watching changes in ${filename}\n`);

    fs.watchFile(filename, () => {
        cnn.write(`file has changed ${new Date()}\n`)
    });

    cnn.on('close', () => {
        console.log('the client closed server');
        fs.unwatchFile(filename);
    });

}).listen('/tmp/watcher.sock', () => console.log('Server hosted'));