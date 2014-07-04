module.exports = function(app) {
	app.get('/login', function(req, res) {
		res.send({ sessionToken: 123456789 });
	});
};
