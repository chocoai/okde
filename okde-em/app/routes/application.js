import Ember from 'ember';

export default Ember.Route.extend({
	actions: {
		login: function() {
			this.get('session').open('basic')
				.then(function(authorization) {
					console.log(authorization);
				});
		}
	}
});
