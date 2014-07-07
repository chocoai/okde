eval("//# sourceURL=assets/ember-cli/loader.js");

;eval("define(\"okde-client/app\", \n  [\"ember\",\"ember/resolver\",\"ember/load-initializers\",\"exports\"],\n  function(__dependency1__, __dependency2__, __dependency3__, __exports__) {\n    \"use strict\";\n    var Ember = __dependency1__[\"default\"];\n    var Resolver = __dependency2__[\"default\"];\n    var loadInitializers = __dependency3__[\"default\"];\n    var App;\n\n    Ember.MODEL_FACTORY_INJECTIONS = true;\n\n    App = Ember.Application.extend({\n      modulePrefix: \'okde-client\',\n      Resolver: Resolver\n    });\n\n    loadInitializers(App, \'okde-client\');\n\n    __exports__[\"default\"] = App;\n  });//# sourceURL=okde-client/app.js");

;eval("define(\"okde-client/controllers/login\", \n  [\"ember\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    var Ember = __dependency1__[\"default\"];\n    var LoginController;\n\n    LoginController = Ember.Controller.extend({\n      actions: {\n        login: function() {\n          var loginObject, self;\n          loginObject = this.getProperties(\'username\', \'password\');\n          self = this;\n          return this.get(\'session\').open(\'basic\', loginObject).then(function(authorization) {\n            if (authorization.currentUser) {\n              return self.transitionToRoute(\'index\');\n            }\n          });\n        }\n      }\n    });\n\n    __exports__[\"default\"] = LoginController;\n  });//# sourceURL=okde-client/controllers/login.js");

;eval("define(\"okde-client/initializers/initialize-torii-callback\", \n  [\"torii/redirect-handler\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    var RedirectHandler = __dependency1__[\"default\"];\n\n    __exports__[\"default\"] = {\n      name: \'torii-callback\',\n      before: \'torii\',\n      initialize: function(container, app){\n        app.deferReadiness();\n        RedirectHandler.handle(window.location[\"toString\"]())[\"catch\"](function(){\n          app.advanceReadiness();\n        });\n      }\n    };\n  });//# sourceURL=okde-client/initializers/initialize-torii-callback.js");

;eval("define(\"okde-client/initializers/initialize-torii-session\", \n  [\"torii/configuration\",\"torii/session\",\"exports\"],\n  function(__dependency1__, __dependency2__, __exports__) {\n    \"use strict\";\n    var configuration = __dependency1__[\"default\"];\n    var Session = __dependency2__[\"default\"];\n\n    __exports__[\"default\"] = {\n      name: \'torii-session\',\n      after: \'torii\',\n\n      initialize: function(container, app){\n\n        if (configuration.sessionServiceName) {\n          var sessionName = configuration.sessionServiceName;\n          app.register(\'torii:session\', Session);\n          app.inject(\'torii:session\', \'torii\', \'torii:main\');\n          app.inject(\'route\',      sessionName, \'torii:session\');\n          app.inject(\'controller\', sessionName, \'torii:session\');\n        }\n      }\n    };\n  });//# sourceURL=okde-client/initializers/initialize-torii-session.js");

;eval("define(\"okde-client/initializers/initialize-torii\", \n  [\"torii/bootstrap\",\"torii/configuration\",\"exports\"],\n  function(__dependency1__, __dependency2__, __exports__) {\n    \"use strict\";\n    var bootstrapTorii = __dependency1__[\"default\"];\n    var configuration = __dependency2__[\"default\"];\n\n    __exports__[\"default\"] = {\n      name: \'torii\',\n      after: \'torii-callback\',\n      initialize: function(container, app){\n        bootstrapTorii(container);\n\n        // Walk all configured providers and eagerly instantiate\n        // them. This gives providers with initialization side effects\n        // like facebook-connect a chance to load up assets.\n        for (var key in  configuration.providers) {\n          if (configuration.providers[\"hasOwnProperty\"](key)) {\n            container.lookup(\'torii-provider:\'+key);\n          }\n        }\n\n        app.inject(\'route\', \'torii\', \'torii:main\');\n      }\n    };\n  });//# sourceURL=okde-client/initializers/initialize-torii.js");

;eval("define(\"okde-client/initializers/torii-store\", \n  [\"exports\"],\n  function(__exports__) {\n    \"use strict\";\n    var torii_store;\n\n    torii_store = {\n      name: \'torii-store\',\n      initialize: function(container, application) {\n        return application.inject(\'torii-adapter\', \'store\', \'store:main\');\n      }\n    };\n\n    __exports__[\"default\"] = torii_store;\n  });//# sourceURL=okde-client/initializers/torii-store.js");

;eval("define(\"okde-client/router\", \n  [\"ember\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    var Ember = __dependency1__[\"default\"];\n    var Router;\n\n    Router = Ember.Router.extend({\n      location: OkdeClientENV.locationType\n    });\n\n    Router.map(function() {\n      return this.route(\'login\');\n    });\n\n    __exports__[\"default\"] = Router;\n  });//# sourceURL=okde-client/router.js");

;eval("define(\"okde-client/routes/index\", \n  [\"ember\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    var Ember = __dependency1__[\"default\"];\n    var IndexRoute;\n\n    IndexRoute = Ember.Route.extend({\n      activate: function() {\n        return console.log(this.get(\'session.currentUser\'));\n      }\n    });\n\n    __exports__[\"default\"] = IndexRoute;\n  });//# sourceURL=okde-client/routes/index.js");

;eval("define(\"okde-client/routes/login\", \n  [\"ember\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    var Ember = __dependency1__[\"default\"];\n    var ApplicationRoute;\n\n    ApplicationRoute = Ember.Route.extend({\n      setupController: function(controller) {\n        return controller.set(\'torii\', this.get(\'torii\'));\n      }\n    });\n\n    __exports__[\"default\"] = ApplicationRoute;\n  });//# sourceURL=okde-client/routes/login.js");

;eval("define(\"okde-client/templates/application\", \n  [\"ember\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    var Ember = __dependency1__[\"default\"];\n    __exports__[\"default\"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {\n    this.compilerInfo = [4,\'>= 1.0.0\'];\n    helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};\n      var buffer = \'\', stack1;\n\n\n      stack1 = helpers._triageMustache.call(depth0, \"outlet\", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:[\"ID\"],data:data});\n      if(stack1 || stack1 === 0) { data.buffer.push(stack1); }\n      data.buffer.push(\"\\n\");\n      return buffer;\n      \n    });\n  });//# sourceURL=okde-client/templates/application.js");

;eval("define(\"okde-client/templates/index\", \n  [\"ember\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    var Ember = __dependency1__[\"default\"];\n    __exports__[\"default\"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {\n    this.compilerInfo = [4,\'>= 1.0.0\'];\n    helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};\n      var stack1, self=this, helperMissing=helpers.helperMissing;\n\n    function program1(depth0,data) {\n      \n      var buffer = \'\', stack1, helper, options;\n      data.buffer.push(\"\\r\\n	\");\n      stack1 = (helper = helpers[\'link-to\'] || (depth0 && depth0[\'link-to\']),options={hash:{},hashTypes:{},hashContexts:{},inverse:self.noop,fn:self.program(2, program2, data),contexts:[depth0],types:[\"STRING\"],data:data},helper ? helper.call(depth0, \"login\", options) : helperMissing.call(depth0, \"link-to\", \"login\", options));\n      if(stack1 || stack1 === 0) { data.buffer.push(stack1); }\n      data.buffer.push(\"\\r\\n\");\n      return buffer;\n      }\n    function program2(depth0,data) {\n      \n      \n      data.buffer.push(\"登录\");\n      }\n\n    function program4(depth0,data) {\n      \n      var buffer = \'\', stack1;\n      data.buffer.push(\"\\r\\n	欢迎\");\n      stack1 = helpers._triageMustache.call(depth0, \"session.currentUser\", {hash:{},hashTypes:{},hashContexts:{},contexts:[depth0],types:[\"ID\"],data:data});\n      if(stack1 || stack1 === 0) { data.buffer.push(stack1); }\n      data.buffer.push(\"	\\r\\n\");\n      return buffer;\n      }\n\n      stack1 = helpers.unless.call(depth0, \"session.currentUser\", {hash:{},hashTypes:{},hashContexts:{},inverse:self.program(4, program4, data),fn:self.program(1, program1, data),contexts:[depth0],types:[\"ID\"],data:data});\n      if(stack1 || stack1 === 0) { data.buffer.push(stack1); }\n      else { data.buffer.push(\'\'); }\n      \n    });\n  });//# sourceURL=okde-client/templates/index.js");

;eval("define(\"okde-client/templates/login\", \n  [\"ember\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    var Ember = __dependency1__[\"default\"];\n    __exports__[\"default\"] = Ember.Handlebars.template(function anonymous(Handlebars,depth0,helpers,partials,data) {\n    this.compilerInfo = [4,\'>= 1.0.0\'];\n    helpers = this.merge(helpers, Ember.Handlebars.helpers); data = data || {};\n      var buffer = \'\', helper, options, escapeExpression=this.escapeExpression, helperMissing=helpers.helperMissing;\n\n\n      data.buffer.push(\"<form \");\n      data.buffer.push(escapeExpression(helpers.action.call(depth0, \"login\", {hash:{\n        \'on\': (\"submit\")\n      },hashTypes:{\'on\': \"STRING\"},hashContexts:{\'on\': depth0},contexts:[depth0],types:[\"STRING\"],data:data})));\n      data.buffer.push(\">\\r\\n\\r\\n\\r\\n	\\r\\n	<div class=\\\"row\\\">\\r\\n		<div class=\\\"small-12\\\">\\r\\n			<div class=\\\"small-3 column\\\">\\r\\n				<label for=\\\"username\\\" class=\\\"right inline\\\">账户</label>\\r\\n			</div>\\r\\n			<div class=\\\"small-9 column\\\">\\r\\n				\");\n      data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{\n        \'type\': (\"text\"),\n        \'id\': (\"username\"),\n        \'value\': (\"username\")\n      },hashTypes:{\'type\': \"STRING\",\'id\': \"STRING\",\'value\': \"ID\"},hashContexts:{\'type\': depth0,\'id\': depth0,\'value\': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, \"input\", options))));\n      data.buffer.push(\"				\\r\\n			</div>\\r\\n		</div>\\r\\n	</div>\\r\\n\\r\\n\\r\\n	\\r\\n	<div class=\\\"row\\\">\\r\\n		<div class=\\\"small-12\\\">\\r\\n			<div class=\\\"small-3 column\\\">\\r\\n				<label for=\\\"password\\\" class=\\\"right inline\\\">密码</label>\\r\\n			</div>\\r\\n			<div class=\\\"small-9 column\\\">\\r\\n				\");\n      data.buffer.push(escapeExpression((helper = helpers.input || (depth0 && depth0.input),options={hash:{\n        \'type\': (\"text\"),\n        \'id\': (\"password\"),\n        \'value\': (\"password\")\n      },hashTypes:{\'type\': \"STRING\",\'id\': \"STRING\",\'value\': \"ID\"},hashContexts:{\'type\': depth0,\'id\': depth0,\'value\': depth0},contexts:[],types:[],data:data},helper ? helper.call(depth0, options) : helperMissing.call(depth0, \"input\", options))));\n      data.buffer.push(\"				\\r\\n			</div>\\r\\n		</div>\\r\\n	</div>\\r\\n\\r\\n\\r\\n	\\r\\n	<div class=\\\"row\\\">\\r\\n		<div class=\\\"small column\\\">\\r\\n			<button type=\\\"submit\\\" class=\\\"button expand radius\\\">登录</button>\\r\\n		</div>\\r\\n	</div>\\r\\n\\r\\n\\r\\n</form>\");\n      return buffer;\n      \n    });\n  });//# sourceURL=okde-client/templates/login.js");

;eval("define(\"okde-client/tests/helpers/resolver\", \n  [\"ember/resolver\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    var Resolver = __dependency1__[\"default\"];\n\n    var resolver = Resolver.create();\n\n    resolver.namespace = {\n      modulePrefix: \'okde-client\'\n    };\n\n    __exports__[\"default\"] = resolver;\n  });//# sourceURL=okde-client/tests/helpers/resolver.js");

;eval("define(\"okde-client/tests/helpers/start-app\", \n  [\"ember\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    /* global require */\n\n    var Application = require(\'okde-client/app\')[\'default\'];\n    var Router = require(\'okde-client/router\')[\'default\'];\n    var Ember = __dependency1__[\"default\"];\n\n    __exports__[\"default\"] = function startApp(attrs) {\n      var App;\n\n      var attributes = Ember.merge({\n        // useful Test defaults\n        rootElement: \'#ember-testing\',\n        LOG_ACTIVE_GENERATION:false,\n        LOG_VIEW_LOOKUPS: false\n      }, attrs); // but you can override;\n\n      Router.reopen({\n        location: \'none\'\n      });\n\n      Ember.run(function(){\n        App = Application.create(attributes);\n        App.setupForTesting();\n        App.injectTestHelpers();\n      });\n\n      App.reset(); // this shouldn\'t be needed, i want to be able to \"start an app at a specific URL\"\n\n      return App;\n    }\n  });//# sourceURL=okde-client/tests/helpers/start-app.js");

;eval("define(\"okde-client/tests/okde-client/app.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client\');\n    test(\'okde-client/app.js should pass jshint\', function() { \n      ok(true, \'okde-client/app.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/app.jshint.js");

;eval("define(\"okde-client/tests/okde-client/controllers/login.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/controllers\');\n    test(\'okde-client/controllers/login.js should pass jshint\', function() { \n      ok(true, \'okde-client/controllers/login.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/controllers/login.jshint.js");

;eval("define(\"okde-client/tests/okde-client/initializers/initialize-torii-callback.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/initializers\');\n    test(\'okde-client/initializers/initialize-torii-callback.js should pass jshint\', function() { \n      ok(true, \'okde-client/initializers/initialize-torii-callback.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/initializers/initialize-torii-callback.jshint.js");

;eval("define(\"okde-client/tests/okde-client/initializers/initialize-torii-session.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/initializers\');\n    test(\'okde-client/initializers/initialize-torii-session.js should pass jshint\', function() { \n      ok(true, \'okde-client/initializers/initialize-torii-session.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/initializers/initialize-torii-session.jshint.js");

;eval("define(\"okde-client/tests/okde-client/initializers/initialize-torii.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/initializers\');\n    test(\'okde-client/initializers/initialize-torii.js should pass jshint\', function() { \n      ok(true, \'okde-client/initializers/initialize-torii.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/initializers/initialize-torii.jshint.js");

;eval("define(\"okde-client/tests/okde-client/initializers/torii-store.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/initializers\');\n    test(\'okde-client/initializers/torii-store.js should pass jshint\', function() { \n      ok(true, \'okde-client/initializers/torii-store.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/initializers/torii-store.jshint.js");

;eval("define(\"okde-client/tests/okde-client/router.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client\');\n    test(\'okde-client/router.js should pass jshint\', function() { \n      ok(true, \'okde-client/router.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/router.jshint.js");

;eval("define(\"okde-client/tests/okde-client/routes/index.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/routes\');\n    test(\'okde-client/routes/index.js should pass jshint\', function() { \n      ok(true, \'okde-client/routes/index.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/routes/index.jshint.js");

;eval("define(\"okde-client/tests/okde-client/routes/login.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/routes\');\n    test(\'okde-client/routes/login.js should pass jshint\', function() { \n      ok(true, \'okde-client/routes/login.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/routes/login.jshint.js");

;eval("define(\"okde-client/tests/okde-client/tests/helpers/resolver.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/tests/helpers\');\n    test(\'okde-client/tests/helpers/resolver.js should pass jshint\', function() { \n      ok(true, \'okde-client/tests/helpers/resolver.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/tests/helpers/resolver.jshint.js");

;eval("define(\"okde-client/tests/okde-client/tests/helpers/start-app.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/tests/helpers\');\n    test(\'okde-client/tests/helpers/start-app.js should pass jshint\', function() { \n      ok(true, \'okde-client/tests/helpers/start-app.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/tests/helpers/start-app.jshint.js");

;eval("define(\"okde-client/tests/okde-client/tests/test-helper.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/tests\');\n    test(\'okde-client/tests/test-helper.js should pass jshint\', function() { \n      ok(true, \'okde-client/tests/test-helper.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/tests/test-helper.jshint.js");

;eval("define(\"okde-client/tests/okde-client/tests/unit/routes/index-test.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/tests/unit/routes\');\n    test(\'okde-client/tests/unit/routes/index-test.js should pass jshint\', function() { \n      ok(true, \'okde-client/tests/unit/routes/index-test.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/tests/unit/routes/index-test.jshint.js");

;eval("define(\"okde-client/tests/okde-client/torii-adapters/application.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/torii-adapters\');\n    test(\'okde-client/torii-adapters/application.js should pass jshint\', function() { \n      ok(true, \'okde-client/torii-adapters/application.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/torii-adapters/application.jshint.js");

;eval("define(\"okde-client/tests/okde-client/torii-providers/basic.jshint\", \n  [],\n  function() {\n    \"use strict\";\n    module(\'JSHint - okde-client/torii-providers\');\n    test(\'okde-client/torii-providers/basic.js should pass jshint\', function() { \n      ok(true, \'okde-client/torii-providers/basic.js should pass jshint.\'); \n    });\n  });//# sourceURL=okde-client/tests/okde-client/torii-providers/basic.jshint.js");

;eval("define(\"okde-client/tests/test-helper\", \n  [\"okde-client/tests/helpers/resolver\",\"ember-qunit\"],\n  function(__dependency1__, __dependency2__) {\n    \"use strict\";\n    var resolver = __dependency1__[\"default\"];\n    var setResolver = __dependency2__.setResolver;\n\n    setResolver(resolver);\n\n    document.write(\'<div id=\"ember-testing-container\"><div id=\"ember-testing\"></div></div>\');\n  });//# sourceURL=okde-client/tests/test-helper.js");

;eval("define(\"okde-client/tests/unit/routes/index-test\", \n  [\"ember-qunit\"],\n  function(__dependency1__) {\n    \"use strict\";\n    var test = __dependency1__.test;\n    var moduleFor = __dependency1__.moduleFor;\n\n    moduleFor(\'route:index\', \'IndexRoute\', {\n      // Specify the other units that are required for this test.\n      // needs: [\'controller:foo\']\n    });\n\n    test(\'it exists\', function() {\n      var route = this.subject();\n      ok(route);\n    });\n  });//# sourceURL=okde-client/tests/unit/routes/index-test.js");

;eval("define(\"okde-client/torii-adapters/application\", \n  [\"ember\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    var Ember = __dependency1__[\"default\"];\n    var ApplicationAdapters;\n\n    ApplicationAdapters = Ember.Object.extend({\n      open: function(authorization) {\n        return new Ember.RSVP.Promise(function(resolve, reject) {\n          return Ember.$.ajax({\n            url: \'api/session\',\n            data: {\n              token: authorization.token\n            },\n            dataType: \'json\',\n            success: Ember.run.bind(null, resolve),\n            error: Ember.run.bind(null, reject)\n          }).then(function(user) {\n            return {\n              currentUser: user\n            };\n          });\n        });\n      }\n    });\n\n    __exports__[\"default\"] = ApplicationAdapters;\n  });//# sourceURL=okde-client/torii-adapters/application.js");

;eval("define(\"okde-client/torii-providers/basic\", \n  [\"ember\",\"exports\"],\n  function(__dependency1__, __exports__) {\n    \"use strict\";\n    var Ember = __dependency1__[\"default\"];\n    var BasicProviders;\n\n    BasicProviders = Ember.Object.extend({\n      open: function(credentials) {\n        return new Ember.RSVP.Promise(function(resolve, reject) {\n          return Ember.$.ajax({\n            url: \'api/sign-in\',\n            data: {\n              username: credentials.username,\n              password: credentials.password\n            },\n            dataType: \'json\',\n            success: Ember.run.bind(null, resolve),\n            error: Ember.run.bind(null, reject)\n          });\n        });\n      }\n    });\n\n    __exports__[\"default\"] = BasicProviders;\n  });//# sourceURL=okde-client/torii-providers/basic.js");
