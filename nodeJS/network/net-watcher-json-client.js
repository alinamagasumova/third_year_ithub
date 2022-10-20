const net = require('net');
const client = net.connect({port: 3870});

client.on('data', data => {
    const msg = JSON.parse(data)
    switch(msg.type.toLowerCase()){
        case 'watching':
            console.log('Watching file: ' + msg.file); break;
        case 'changed':
            console.log('File has changed: ' + new Date(msg.timestamp)); break;
        default:
            console.log('Unknown operation ' + msg.type);
    }
})