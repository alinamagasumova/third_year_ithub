const create = require('./create');
const read = require('./read');
const del = require('./delete');
const update = require('./update')

module.exports = (app, db) => {
  create(app,db)
  read(app, db)
  del(app, db)
  update(app, db)
};
