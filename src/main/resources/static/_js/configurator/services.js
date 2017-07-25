var app = {};

app = angular.module('MINIAdminApp');

app.factory('TrimFactory', function($http) {
    // create a new object
    var trimFactory = {};

    // get a single trim
    trimFactory.get = function(id) {
      return $http.get('/rest/configurator/trim/' + id);
    };

    // get all trims
    trimFactory.all = function() {
      return $http.get('/rest/configurator/trim/');
    };

    // update a user
//    trimFactory.add = function(id, userData) {
//      return $http.post('/rest/security/groups/' + id, userData);
//    };

    // delete a user
//    trimFactory.update = function(id) {
//      return $http.put('/rest/security/groups/' + id);
//    };

    // return our entire groupsFactory object
    return trimFactory;
});

