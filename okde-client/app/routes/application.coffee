`import Ember from 'ember'`

ApplicationRoute = Ember.Route.extend
	
	active: 
		login: ->
			@get('torii').open('basic')


`export default ApplicationRoute`