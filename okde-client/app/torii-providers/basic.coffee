`import Ember from 'ember'`

BasicProviders = Ember.Object.extend
	open: (credentials) ->
		new Ember.RSVP.Promise (resolve, reject) ->
    		Ember.$.ajax
    			url: 'api/sign-in'
    			data: { username: credentials.username, password: credentials.password }
    			dataType: 'json'
    			success: Ember.run.bind null, resolve
    			error: Ember.run.bind null, reject



`export default BasicProviders`