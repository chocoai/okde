`import Ember from 'ember'`

LoginController = Ember.Controller.extend
	actions: 
		login: ->
			loginObject = @getProperties 'username', 'password' 
			self = @
			@get('session')
				.open 'basic', loginObject
				.then (authorization) ->
					if authorization.currentUser
						self.transitionToRoute 'index'
		

`export default LoginController`