const db = require('../db');

const courseSchema = new db.Schema({
    title: { type: String, required: true, unique: true },
    length: { type: Number },
    created: { type: Number, default: Date.now }
})

exports.Course = db.model('Course', courseSchema)