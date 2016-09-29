/**
 * 
 */

var loginapp = angular.module('tfadmin.loginmodule', []);

loginapp.config(['$stateProvider', function ($stateProvider, $urlRouterProvider) {
	$stateProvider.state('login', {
		url: '/login',
		templateUrl: 'app/partial/login/login.html',
		controller: 'LoginController'
	});
}]);

loginapp.controller('LoginController', ['$scope', '$state', function ($scope, $state) {


	console.log("entering...");

	$scope.user = {};

	$scope.showPassword = false;

	console.log("message for password");

	$scope.toggleShowPassword = function () {
		console.log("toggleeeeeee.....");
		$scope.showPassword = !$scope.showPassword;
	}


	$scope.onLogin = function (user) {
		console.log(angular.toJson(user));
		/*alert("working");*/
		if (user.email === 'admin@gmail.com' && user.password === 'admin') {
			console.log("Successful login... ");
			$state.go('usermgmt.user.viewuser');
		} else {
			/*alert("fail");*/
			/* $state.go('usermgmt.user.viewuser');*/
		}
	}

}]);

