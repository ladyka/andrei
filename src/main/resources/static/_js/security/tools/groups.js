var app = {};

app = angular.module('MINIAdminApp');

app.controller('ToolsGroupsController', function($scope, $http, $mdDialog) {
  $http.get('/rest/security/tools/group').success(function(data, status, headers, config) {
    $scope.groups = data;
  }).error(function(data, status, headers, config) {
    $log.error("Can't load groups", data, status)
  });

  $scope.add = function() {
		var alert = $mdDialog.alert({
			title: 'Add...',
			content: 'Adding new Tool Group',
			ok: 'Close'
		});
		$mdDialog
			.show( alert )
			.finally(function() {
				alert = undefined;
			});
  };

});

app.controller('ToolsGroupController', function($scope, $http, $mdDialog) {
	$scope.formatDate = function(dateString) {
		var instant = moment(dateString);
		return instant.format("MM/DD/YYYY h:mm:ss a");
	};
  $scope.edit = function() {
		var alert = $mdDialog.alert({
			title: 'Edit...',
			content: 'Doing edit on ' + $scope.group.id,
			ok: 'Close'
		});
		$mdDialog
			.show( alert )
			.finally(function() {
				alert = undefined;
			});
  };
  $scope.delete = function() {
		var alert = $mdDialog.alert({
			title: 'Delete...',
			content: 'Doing delete on ' + $scope.group.id,
			ok: 'Close'
		});
		$mdDialog
			.show( alert )
			.finally(function() {
				alert = undefined;
			});
  };
});