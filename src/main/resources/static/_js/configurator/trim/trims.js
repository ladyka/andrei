var app = {};

app = angular.module('MINIAdminApp');

app.config(['$routeProvider', function($rP) {
  $rP
    .when('/configurator/trims/add', {
      templateUrl: '_tmpl/configurator/trims/add.html'
    })
//    .when('/security/users/:user_id', {
//      templateUrl: '_tmpl/security/users/edit.html',
//      controller: 'UserEditController'
//    })
}]);

app.controller('TrimsListController', function($scope, TrimFactory) {

    $scope.headers = [{
          name: 'Price',
          field: 'price'
        },{
          name: 'Model Name',
          field: 'modelname'
        },{
          name: 'Year',
          field: 'year'
        },{
          name: 'Last Modified',
          field: 'last_modified'
        },{
          name: 'Created',
          field: 'modelname'
        }];

    // grab all the users at page load
    UsersFactory.all()
      .success(function(data) {

      // bind the users that come back to vm.users
      $scope.users = data;
    });

    $scope.editUser = function(user) {
        console.log('editing user', user);
    };

    $scope.deleteUser = function(user) {
        console.log('removing user', user);
    };

    $scope.addUser = function() {
      console.log('add user - should probably go to the add user form route');
    };
});

app.controller('UserEditController', function($scope, $routeParams, UsersFactory, GroupsFactory) {

  UsersFactory.get($routeParams.user_id)
    .success(function(data) {
      $scope.userData = data;
    });

  GroupsFactory.all()
    .success(function(data) {
      $scope.userGroups = data
    })

  $scope.saveUser = function() {
      UsersFactory.update($routeParams.user_id, $scope.userData)
        .success(function(data) {
          console.log('Success');
        });
  };

  $scope.saveUserGroups = function() {

  };

});