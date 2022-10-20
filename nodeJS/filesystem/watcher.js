const fs = require('fs');
fs.watch('target.txt', () => console.log('Файл изменён!'));
console.log('Следим за изменениями в target.txt...');