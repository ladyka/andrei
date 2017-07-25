(function (angular) {

    'use strict';

    var GroupsFactory = function($http) {
        // create a new object
        var groupsFactory = {};

        // get a single user
        groupsFactory.get = function(id) {
            return $http.get('/rest/security/groups/' + id);
        };

        // get all groups
        groupsFactory.all = function() {
            return $http.get('/rest/security/groups/');
        };

        // update a user
        groupsFactory.update = function(id, userData) {
            return $http.put('/rest/security/groups/' + id, userData);
        };

        // delete a user
        groupsFactory.delete = function(id) {
            return $http.delete('/rest/security/groups/' + id);
        };

        // return our entire groupsFactory object
        return groupsFactory;
    };


    var UsersFactory = function($http) {
        // create a new object
        var userFactory = {};

        // get a single user
        userFactory.get = function(id) {
            return $http.get('/rest/security/users/' + id);
        };

        // get all users
        userFactory.all = function() {
            return $http.get('/rest/security/users/');
        };

        // update a user
        userFactory.update = function(id, userData) {
            return $http.put('/rest/security/users/' + id, userData);
        };

        // delete a user
        userFactory.delete = function(id) {
            return $http.delete('/rest/security/users/' + id);
        };

        // return our entire userFactory object
        return userFactory;
    };


    angular.module('MINIAdminApp.Security', [])
        .factory('GroupsFactory', ['$http', GroupsFactory])
        .factory('UsersFactory', ['$http', UsersFactory]);

})(window.angular);
