const EventEmitter = require('events'). EventEmitter;
const test = new EventEmitter;
test.on('kiss', param => {
    console.log(param);
});

test.emit('kiss', 'girl');
test.emit('kiss', 'another girl');