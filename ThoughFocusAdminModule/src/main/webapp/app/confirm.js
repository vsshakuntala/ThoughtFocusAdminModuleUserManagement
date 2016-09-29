var tfdialogue = angular.module('tfdialogue', ['ngDialog']);

tfdialogue.controller('ConfirmController',['$scope',
       'headerMessage', 'userMessage',
       function($scope ,headerMessage, userMessage) {
		$scope.headerMessage = headerMessage;
		$scope.userMessage = userMessage;
}]);

tfdialogue.factory('ConfirmService', ['ngDialog', '$q', function (ngDialog, $q) {
        var factory = {};
        factory.openDialog = function (headerMessage, userMessage) {
        	var deferred = $q.defer();
        	ngDialog.openConfirm({
    	      template: 'templateId',
    	      closeByDocument: false,
    	      closeByEscape: false,
    	      showClose: false,
    	      closeByNavigation: true,
    	      controller: 'ConfirmController',
    	      resolve: {
    	    	  headerMessage: function(){
    	    		  return headerMessage;
    	    	  },
    	          userMessage: function(){
    	    		  return userMessage;
    	    	  }
    	      }
    	    }).then(function(value) {
    	    	deferred.resolve();
    	    }, function(reason) {
    	    	deferred.reject();
    	    });
        	return deferred.promise;
        }
        return factory;
    }
]);