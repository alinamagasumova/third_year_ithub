const fs = require('fs');
const spawn= require('child_process').spawn;
const filename = process.argv[2];
if(!filename){
    throwError('Укажите имя файла!');
}
fs.watch(filename, () => {
    const ls = spawn('ls', ['-l', '-h', filename]);
    ls.stdout.pipe(process.stdout);
});
console.log(`Следим за изменениями в ${filename}...`);