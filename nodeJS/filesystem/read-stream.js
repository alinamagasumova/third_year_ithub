require('fs')
    .createReadStream(process.argv[2])
    .on('data', chunk => process.stdout.write(`MSG: ${chunk}\n`))
    .on('error', err => process.stderr.write(`ERR: ${err.message}\n`));