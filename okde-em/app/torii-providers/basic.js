import Ember from 'ember';

export default Ember.Object.extend({
	open: function(credentials) {
		return new Ember.RSVP.Promise(function(resolve, reject) {
			Ember.$.ajax({
		        url: 'login',
		        dataType: 'json',
		        success: Ember.run.bind(null, resolve),
		        error: Ember.run.bind(null, reject)
		    });
		});
	}
});