import Ember from 'ember';

export default Ember.Object.extend({
	open: function() {
		return new Ember.RSVP.Promise(function(resolve, reject) {
			Ember.$.ajax({
		        url: 'session',
		        dataType: 'json',
		        success: Ember.run.bind(null, resolve),
		        error: Ember.run.bind(null, reject)
		    });
		});
	}
});