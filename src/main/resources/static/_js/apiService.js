angular.module('MINIAdminApp')
    .factory('ApiService', ['$http', function ($http) {

        var urlBase = '/rest';
        var apiFactory = {};


        apiFactory.getTools = function() {
            return $http.get(urlBase + '/auth/tools');
        };


        return apiFactory;
    }]);