`import Ember from 'ember'`

Router = Ember.Router.extend
	location: OkdeClientENV.locationType


Router.map ->
	@route 'login'

`export default Router`
