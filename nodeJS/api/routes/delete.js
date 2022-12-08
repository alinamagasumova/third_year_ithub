module.exports = (app, db) => {
    app.delete('/course/:id', (req, res) => {
        const Course = require('../schema').Course
        Course.findByIdAndRemove(req.params.id)
            .then(result => res.status(202).send(result))
            .catch(err => res.status(404).send('Not found'))
    })
}