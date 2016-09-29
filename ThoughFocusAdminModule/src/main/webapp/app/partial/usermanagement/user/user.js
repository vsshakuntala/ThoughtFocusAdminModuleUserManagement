/**
 * 
 */
var user = angular.module('tfadmin.usermanagementmodule.user',['tfadmin.usermanagementmodule.user.createuser',
                                                               'tfadmin.usermanagementmodule.user.updateuser',
                                                               'tfadmin.usermanagementmodule.user.viewuser']);
user.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider){
	$stateProvider.state('usermgmt.user',{
		url:'/user',
		templateUrl : 'app/partial/usermanagement/user/user.html',
		controller : 'UserController'
	});
}]);
user.controller('UserController',['$scope','$state',function($scope, $state){
	$scope.create = function(){		
				 $state.go('usermgmt.user.createuser');
		}
	$scope.view = function(){		
		 $state.go('usermgmt.user.viewuser');
}
}]);