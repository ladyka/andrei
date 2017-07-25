var app = {};

app = angular.module('MINIAdminApp');

app.config(['$routeProvider', function($rP) {
  $rP
    .when('/security/users/add', {
      templateUrl: '_tmpl/security/users/add.html'
    })
    .when('/security/users/:user_id', {
      templateUrl: '_tmpl/security/users/edit.html',
      controller: 'UserEditController'
    })
}]);