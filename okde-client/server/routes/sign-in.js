var express = require('express');


var authorization = function(query) {
	if(query.username === 'admin' && query.password === '123') {
		// okde::*yufi* -> to md5
		return {
			token: '43C4AC1280A483A620053CAF57EDDB79'
		};
  	} else {
		return {
			error: '你的账户或密码错误'
		};
  	}
}


module.exports = function(app) {
	var signInRouter = express.Router();
	signInRouter.get('/', function(req, res) {

		var query = req.query;
		res.send(authorization(query));
    

	});
	app.use('/api/sign-in', signInRouter);
};


