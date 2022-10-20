const fs = require('fs');
const rs = fs.createReadStream(process.argv[2]);
const ws = fs.createWriteStream('output.txt');
rs.on('data', chunk => {
    ws.write(`MSG: ${chunk}\n`)
});
rs.on('error', err => process.stderr.write(`ERR: ${err.message}\n`));