module.exports = (app, db) => {
    const Course = require('../schema').Course;
    app.put('/course/:id', (req, res) => {
        Course.findById(req.params.id)
            .then(result => {
                result.title = req.body.title ? req.body.title : result.title
                result.length = req.body.len ? req.body.len : result.length
                result.save()
                    .then(res.status(204).send(result))
                    .catch(err => res.status(409).send('Update error'))
            })
            .catch(err => res.status(404).send('Not found'))
    })
}