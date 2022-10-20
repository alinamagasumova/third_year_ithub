const fs = require('fs');

fs.writeFile('target-2.txt', 'Hello, Alina!', (err) => {
    if (err) throw err;
});

console.log('Пишем в файл');