const netClient = require('net').connect({port: 3870});
const jmpClient = require('./lib/ajmp-client').connect(netClient);

jmpClient.on('message', msg => {
    switch(msg.type.toLowerCase()){
        case 'watching':
            console.log('Watching file: ' + msg.file); break;
        case 'changed':
            console.log('File has changed: ' + new Date(msg.timestamp)); break;
        default:
            console.log('Unknown operation ' + msg.type);
    }
})