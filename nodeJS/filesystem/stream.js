#!/usr/local/bin/node
require('fs')
    .createReadStream(process.argv[2])
    .pipe(process.stdout)

// chmod +x stream.js