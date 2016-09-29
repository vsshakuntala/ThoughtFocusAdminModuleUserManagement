/**
 * 
 */

var dashboard = angular.module('tfadmin.usermanagementmodule.dashboard', []);

dashboard.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
	$stateProvider.state('usermgmt.dashboard', {
		url: '/dashboard',
		templateUrl: 'app/partial/usermanagement/dashboard/dashboard.html',
		controller: 'DashboardController'
	});
}]);

dashboard.controller('DashboardController', ['$scope', '$state', function ($scope, $state) {

	$scope.address = function () {

		$state.go('usermgmt.addressassignment');
	}

	$scope.customer = function () {

		$state.go('usermgmt.customerassignment');
	}

	$scope.group = function () {

		$state.go('usermgmt.groupassignment');
	}

	$scope.role = function () {

		$state.go('usermgmt.rolesassignment');
	}

	$scope.user = function () {

		$state.go('usermgmt.user.viewuser');
	}

	$scope.division = function () {

		$state.go('usermgmt.divisionassignment');
	}
	
	$scope.machine = function () {

		$state.go('usermgmt.machineassignment');
	}

}]);

/**
 * 
 */