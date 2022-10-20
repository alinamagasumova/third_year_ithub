const fs = require('fs');
const filename = process.argv[2];
console.log(process.argv)
if(!filename){
    throwError('Укажите имя файла!');
}
fs.watch(filename, () => console.log(`Файл ${filename} изменён!`));
console.log(`Следим за изменениями в target.txt...`);