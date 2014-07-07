`import Ember from 'ember'`

ApplicationAdapters = Ember.Object.extend
	open: (authorization) ->
		new Ember.RSVP.Promise (resolve, reject) ->
            Ember.$.ajax(
                url: 'api/session'
                data: { token: authorization.token }
                dataType: 'json'
                success: Ember.run.bind null, resolve
                error: Ember.run.bind null, reject)
            .then (user) ->
                currentUser: user


`export default ApplicationAdapters`