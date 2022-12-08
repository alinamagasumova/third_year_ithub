const db = require('mongoose');
db.connect('mongodb://localhost:27017/api',
{ useNewUrlParser: true, useUnifiedTopology: true });
// db.set('useCreateIndex', true)
module.exports = db;