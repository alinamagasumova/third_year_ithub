module.exports = (app, db) => {
    app.post('/new', (req, res) => {
        const Course = require('../schema').Course
        const course = new Course({
            title: req.body.title,
            length: req.body.len
        })
        course.save()
            .then(result => res.status(201).send(result._id))
            .catch(err => res.status(409).send('Create error'))
    })
}