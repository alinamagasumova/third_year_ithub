module.exports = (app, db) => {
    const Course = require('../schema').Course;
    app.get('/courses', (req, res) => {
        Course.find()
            .then(result => res.status(200).send(result))
            .catch(err => res.status(404).send('Not found'))
    })
    app.get('/course/:id', (req, res) => {
        Course.findById(req.params.id)
            .then(result => res.status(200).send(result))
            .catch(err => res.status(404).send('Not found'))
    })
    app.get('/course/title/:title', (req, res) => {
        Course.findOne({'title': req.params.title})
            .then(result => {
                if(!result) throw Error()
                res.status(200).send(result)})
            .catch(err => res.status(404).send('Not found'))
    })
}