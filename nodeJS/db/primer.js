const mongoose = require('mongoose')
mongoose.connect('mongodb://localhost:27017/test',
    { useNewUrlParser: true, useUnifiedTopology: true });

const Cat = mongoose.model('Cat', { name: String });
const kitty = new Cat({ name: 'Мурзик' });
kitty.save().then(() => console.log('Мяу!'));