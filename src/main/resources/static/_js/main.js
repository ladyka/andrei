(function (angular) {

    'use strict';

    /**
    * Angular App Configuration
    * @param $stateProvider
    * @param $urlRouterProvider
    * @param $mdThemingProvider
    */
    var appConfig = function($stateProvider, $urlRouterProvider, $mdThemingProvider) {

      $urlRouterProvider.otherwise("/");

      $stateProvider
          .state('dashboard', {
            url: "/",
            templateUrl: "_tmpl/welcome.html"
          })
          .state('security', {
            url: "/security",
              templateUrl: "_tmpl/security/base.html"
          });


      // Angular Material Theme
      $mdThemingProvider.theme('default').primaryPalette('blue-grey', {
          default: '900',
          'hue-1': '100',
          'hue-2': '300',
          'hue-3': '500'
      }).accentPalette('red', {
          default: '900',
          'hue-1': '100',
          'hue-2': '300',
          'hue-3': '500'
      });

    };


    /**
    * Admin Tool Wrapper Controller
    * @param $scope
    * @param $mdSidenav
    * @param $mdUtil
    * @constructor
     */
    var MainController = function($scope, $mdSidenav, $mdUtil) {

    $scope.currentTool = {};

    $scope.toggleMainMenu = $mdUtil.debounce(function() {
      $mdSidenav('mainNav').toggle();
    }, 300);

    };



    var NavController = function($scope, ApiService) {

    ApiService.getTools().then(function(res){
      $scope.groups = res.data;

      // angular.forEach($scope.groups, function(group) {
      //   angular.forEach(group.tools, function(tool) {
      //
      //     // Look for the extra files to download for this tool
      //     var tmpl = '';
      //     var extraFiles = [];
      //
      //     angular.forEach(tool.files, function(file) {
      //       if (file.type.code == 'template') {
      //         tmpl = file.resourcePath;
      //       } else {
      //         extraFiles.push(file.resourcePath);
      //       }
      //     });
      //
      //     // Have to conditionally build this because bad things
      //     // happen when you pass empty arrays to the lazy loader
      //     if (tmpl) {
      //       var content = {
      //         templateUrl : tmpl
      //       };
      //
      //       if (tool.angularController) {
      //         content.controller = tool.angularController;
      //       }
      //
      //       if (extraFiles.length > 0) {
      //         content.resolve = {
      //           lazy : [ '$ocLazyLoad', function($ocLazyLoad) {
      //             return $ocLazyLoad.load([ {
      //               name : 'MINIAdminApp',
      //               files : extraFiles
      //             } ]);
      //           } ]
      //         };
      //       }
      //
      //       postConfigRouteProvider.when('/' + group.code + '/' + tool.code, content);
      //     }
      //
      //   });
      // });

      //$route.reload();

    }, function(res){
      $log.error("Can't load tools", res.data, res.status);
      $location.path('/logout');
      return true;
    });

    // $scope.setCurrentTool = function(group, tool) {
    //   $scope.$parent.currentGroup = group;
    //   $scope.$parent.currentTool = tool;
    //   $scope.$parent.toggleMainMenu();
    //
    //   $location.path("/" + group.code + "/" + tool.code);
    //   return true;
    // };


    $scope.logout = function() {
      window.location="/logout";
      return true;
    };


    $scope.isOpen = function(group) {
      return $scope.openedGroup === group;
    };


    $scope.toggleOpen = function(group) {
      $scope.openedGroup = ($scope.openedGroup === group ? null : group);
    };

    };


    var BaseToolController = function($scope) { };


    var tableHeadersDirective = function () {
      return {
        restrict: 'A',
        scope: {
          headers: '=',
          sortable: '='
        },
        link: function ($scope,$filter,$window) {
        },
        template: '<tr class="item-table-headers-row">' +
        '<th ng-repeat="h in headers" class="item-table-header">' +
        '<span>{{h.name}}</span></th>' +
        '<th class="item-table-header"></th></tr>'
      }
    };




    angular.module('MINIAdminApp', ['ngMaterial', 'ui.router', 'MINIAdminApp.Users'])
        .config(['$stateProvider', '$urlRouterProvider', '$mdThemingProvider', appConfig])
        .controller('MainController', ['$scope', '$mdSidenav', '$mdUtil', MainController ])
        .controller('NavController', ['$scope', 'ApiService', NavController ])
        .controller('BaseToolController', ['$scope', BaseToolController])
        .directive('tableHeaders', tableHeadersDirective);


})(window.angular);