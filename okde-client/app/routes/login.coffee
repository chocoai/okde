`import Ember from 'ember'`

ApplicationRoute = Ember.Route.extend
	setupController: (controller)->
		controller.set 'torii', @get('torii')


`export default ApplicationRoute`