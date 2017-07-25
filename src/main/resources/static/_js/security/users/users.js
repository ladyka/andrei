(function (angular) {


    var userModuleConfig = function ($stateProvider) {

        $stateProvider
            .state('security.users', {
                url: "/users",
                controller: 'UsersCtrl',
                templateUrl: '_tmpl/security/users/list.html'
            })
            .state('security.users.edit', {
                url: "/:user_id",
                templateUrl: '_tmpl/security/users/edit.html'
            })
            .state('security.users.add', {
                url: "/new",
                templateUrl: '_tmpl/security/users/edit.html'
            });
    };


    var UsersCtrl = function ($scope, UsersFactory) {

        $scope.users = [];

        debugger;

        UsersFactory.all()
            .success(function (data) {
                $scope.users = data;
            });

        $scope.editUser = function (user) {
            console.log('editing user', user);
        };

        $scope.deleteUser = function (user) {
            console.log('removing user', user);
        };

        $scope.addUser = function () {
            console.log('add user - should probably go to the add user form route');
        };
    };


    var UserEditCtrl = function ($scope, UsersFactory, GroupsFactory) {

        UsersFactory.get($routeParams.user_id)
            .success(function (data) {
                $scope.userData = data;
            });

        GroupsFactory.all()
            .success(function (data) {
                $scope.userGroups = data
            });

        $scope.saveUser = function () {
            UsersFactory.update($routeParams.user_id, $scope.userData)
                .success(function (data) {
                    console.log('Success');
                });
        };

        $scope.saveUserGroups = function () {

        };

    };


    angular.module('MINIAdminApp.Users', ['MINIAdminApp.Security'])
        .config(['$stateProvider', userModuleConfig])
        .controller('UsersCtrl', ['$scope', 'UsersFactory', UsersCtrl])
        .controller('UserEditCtrl', ['$scope', 'UsersFactory', 'GroupsFactory', UserEditCtrl]);


})(window.angular);
