const app = require("express")();
const mongoose = require('mongoose')
mongoose.connect('mongodb://localhost:27017/url',
    { useNewUrlParser: true, useUnifiedTopology: true });

const adress = mongoose.model('Adress', { full: String, short: String });
const kitty = new Cat({ full: String, short: String });
kitty.save().then(() => console.log('url added'));