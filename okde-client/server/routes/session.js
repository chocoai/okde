var express = require('express');


module.exports = function(app) {
  var sessionRouter = express.Router();
  sessionRouter.get('/', function(req, res) {
    res.send({currentUser:'yufi'});
  });
  app.use('/api/session', sessionRouter);
};
