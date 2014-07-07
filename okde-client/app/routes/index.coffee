`import Ember from 'ember'`

IndexRoute = Ember.Route.extend
	activate: ->
		console.log @get('session.currentUser')
  # deactivate: ->
  # setupController: (controller, model)->
  # renderTemplate: ->
  # beforeModel: ->
  # afterModel: ->



`export default IndexRoute`