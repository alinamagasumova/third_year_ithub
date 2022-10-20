const fs = require('fs');
data = fs.readFileSync('target.txt');
console.log(data.toString())