/**
 * 
 */

var usermgmtmodule = angular.module('tfadmin.usermanagementmodule', ['tfadmin.usermanagementmodule.addressassignment'
	, 'tfadmin.usermanagementmodule.customerassignment', 'tfadmin.usermanagementmodule.divisionassignment',
	'tfadmin.usermanagementmodule.groupassignment', 'tfadmin.usermanagementmodule.machineassignment',
	'tfadmin.usermanagementmodule.rolesassignment', 'tfadmin.usermanagementmodule.user', 'tfadmin.usermanagementmodule.dashboard']);
usermgmtmodule.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
	$stateProvider.state('usermgmt', {
		url: '/usermgmt',
		templateUrl: 'app/partial/usermanagement/usermanagement.html'
	});
}]);
