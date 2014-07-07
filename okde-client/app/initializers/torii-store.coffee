torii_store = 
	name: 'torii-store'
	initialize: (container, application) ->
		application.inject 'torii-adapter', 'store', 'store:main'



`export default torii_store`
