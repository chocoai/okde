import Ember from 'ember';

var Router = Ember.Router.extend({
  location: GloitENV.locationType
});

Router.map(function() {
  this.route('login');
  this.route('application');
});

export default Router;
