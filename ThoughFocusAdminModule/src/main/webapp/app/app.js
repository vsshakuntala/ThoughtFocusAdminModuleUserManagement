/**
 * 
 */
var app = angular.module('tfadmin',['ui.router','LocalStorageModule','angularSpinner','darthwade.loading','tfdialogue','tfadmin.usermanagementmodule','tfadmin.loginmodule','tfadmin.appconstantsmodule', 'GenericServiceModule','mgcrea.ngStrap','ngAnimate', 'ngSanitize', 'ui.bootstrap']);
app.run(['$rootScope','$state','APPCONSTANTS',function($rootScope,$state, APPCONSTANTS){
	$rootScope.APPCONSTANTS  = APPCONSTANTS;
	$rootScope.myclass='hold-transition skin-blue sidebar-mini sidebar-collapse fixed';
}]);
app.config(['$urlRouterProvider',function($urlRouterProvider){
	$urlRouterProvider.otherwise('/login');
}]);

app.run(['$rootScope', function($rootScope){
	
	$rootScope.isRunning = function() {
    	if($rootScope.spinnerstarted === true){
    		return true;
    	} else{
    		return false;
    	}
    };
	
	$rootScope.$on('us-spinner:spin', function(event, key) {
		$rootScope.spinnerstarted = true;
    });
    $rootScope.$on('us-spinner:stop', function(event, key) {
		$rootScope.spinnerstarted = false;
		var myEl = angular.element( document.querySelector( '#spin1' ) );
		myEl.removeClass('first-spin');
		var myEl = angular.element( document.querySelector( 'body' ) );
		myEl.removeClass('newclass');
    });
	
}]);

app.factory('SharedService', function() {
	  console.log("In SharedService")
	 var __variables = {};

	  return {
	   get: function(varname) {
	    return (typeof __variables[varname] !== 'undefined') ? __variables[varname] : false;
	   },
	   set: function(varname, value) {
	    __variables[varname] = value;
	   }
	  };

	})