/**
 * 
 */

var groupassignment = angular.module('tfadmin.usermanagementmodule.groupassignment',[]);

groupassignment.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider){
	$stateProvider.state('usermgmt.groupassignment',{
		url:'/groupassignment',
		templateUrl : 'app/partial/usermanagement/groupassignment/groupassignment.html',
		controller: 'GroupAssignmentController'
	});
}]);

groupassignment.controller('GroupAssignmentController',['$scope','localStorageService','$state','$http','groupAssignmentService','SharedService',function($scope,localStorageService, $state,$http,groupAssignmentService,SharedService){
	
	$scope.unassignedgroup = [];
	$scope.selectedgroup = [];
	$scope.getAllGroup = function () {
		$http.get('groupassignment/getgroup/'+user_id+'/'+organization_id+'/'+login_user_id).then(function(response){
			console.log(response.data);
			$scope.groupList = response.data;
			$scope.oldassignedgrouplist= angular.copy(response.data.assigned);
			$scope.assignedgrplist= angular.copy(response.data.assigned);
			$scope.unassignedgrplist=response.data.unAssigned;
			console.log($scope.groupList);
			console.log("Assigned "+angular.toJson($scope.assignedgrplist));
			console.log("un assigned "+angular.toJson($scope.unassignedgrplist));
		})
	};
	
	//$scope.userOrgDetails = SharedService.get('userOrgDetails');
	$scope.userOrgDetails = localStorageService.get('userOrgDetails');
	var user_id = $scope.userOrgDetails.userId;
	var organization_id=$scope.userOrgDetails.orgId;
	var login_user_id =$scope.userOrgDetails.adminId;
	$scope.orgName = $scope.userOrgDetails.orgName;
	$scope.userNameSetLocal=$scope.userOrgDetails.userName;
	 console.log("user_id "+user_id+"organization_id"+organization_id+"login_user_id"+login_user_id);
	$scope.getAllGroup();
	
	
	
	$scope.toggleSelection = function () {
		if (angular.equals($scope.toggleSeleted, 'check') && angular.isDefined($scope.unassignedgrplist)) {
			$scope.unassignedgroup = [];
			for (var i = 0; i < $scope.unassignedgrplist.length; i++) {
				$scope.unassignedgroup.push($scope.unassignedgrplist[i]);
			}
		} else {
			$scope.unassignedgroup = [];
		}
	}

	$scope.toggleSelectedSelection = function () {
		if (angular.equals($scope.toggleSeletedSelection, 'check') && angular.isDefined($scope.assignedgrplist)) {
			$scope.selectedgroup = [];
			for (var i = 0; i < $scope.assignedgrplist.length; i++) {
				$scope.selectedgroup.push($scope.assignedgrplist[i]);
			}
		} else {
			$scope.selectedgroup = [];
		}
	}
	
	//alert("user id "+user_id+" org id "+organization_id+"login id"+login_user_id);
	
	$scope.savegroup = function(){
		 
		 console.log("new final list "+angular.toJson($scope.assignedgrplist));
		 console.log("old list "+angular.toJson($scope.oldassignedgrouplist));
		 
		 groupAssignmentService.updategroup($scope.oldassignedgrouplist,$scope.assignedgrplist,user_id,organization_id,login_user_id).then(function(data) {
			  console.log(angular.toJson(data));
			 // alert('successfully updated!');
			  $.notify({icon: 'glyphicon glyphicon-ok',message: 'Group is successfully updated' },{ type: 'success', placement: {
					from: "top",
					align: "right"
				}});
			  $scope.getAllGroup();
		   }, function(data) {
			   console.log("fail "+angular.toJson(data));
			  // alert('fail');
			   $.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
					from: "top",
					align: "right"
				}});
		  });
	};
	
	$scope.cancel = function(){
		
		if(confirm("Are you sure you want to cancel?")){
			
			$state.go('usermgmt.divisionassignment');
		}
	};
	
	 //use to assign group from selected unassign group
	$scope.assignitem = function(unassignedgroup) {

        console.log("unassignedgroup "+unassignedgroup);     
        
        var i;
        if(unassignedgroup.length != 0){      	
        
        for (i = 0; i < unassignedgroup.length; i++) {
        	 console.log('before assigned: '+$scope.assignedgrplist);
        	  var index = $scope.assignedgrplist.indexOf(unassignedgroup[i])
        	  var index1 = $scope.unassignedgrplist.indexOf(unassignedgroup[i])
        	  console.log('Unassigned: '+index);
            if (index == -1) {
            	$scope.assignedgrplist.push(unassignedgroup[i]);            	
            }
            
            if(index1 != -1){
            	console.log('Index: '+index);
            	$scope.unassignedgrplist.splice(index1, 1); 
            	
            }
            
            console.log('assigned: '+$scope.assignedgrplist);
        } 
	  }
    };
    //use to assign all group from unassign group
    $scope.assignall = function() {
    	
    	angular.forEach($scope.unassignedgrplist, function(item) {
    		$scope.assignedgrplist.push(item);
    		           });
    	$scope.unassignedgrplist = [];

    };                

    //use to unassign group from selected assign group
    $scope.unassignitem = function(selectedgroup){
    console.log("selectedgroup "+selectedgroup);     
        
        var i;
        if(selectedgroup.length != 0){      	
        
        for (i = 0; i < selectedgroup.length; i++) {
        	
        	  var index = $scope.unassignedgrplist.indexOf(selectedgroup[i])
        	  var index1 = $scope.assignedgrplist.indexOf(selectedgroup[i])
        	  console.log('Unassigned: '+index);
            if (index == -1) {
            	$scope.unassignedgrplist.push(selectedgroup[i]);
            	
            }
            
            if(index1 != -1){
            	console.log('Index: '+index);
            	$scope.assignedgrplist.splice(index1, 1);          	
            	console.log('Lenght aft remove: '+$scope.oldassignedgrouplist.length);
            	
            }
        } 
	}
    };
    
    //use to unassign all group from assign group
    $scope.unassignall = function() {

    	angular.forEach($scope.assignedgrplist, function(item) {
    		$scope.unassignedgrplist.push(item);
    		           });
    	$scope.assignedgrplist = [];
    	console.log("$scope.assignedgrplist "+angular.toJson($scope.assignedgrplist));
    }; 
    
    $scope.backToDivision = function() {
		$state.go('usermgmt.divisionassignment');
	}
    
    $scope.backToUserList=function(){
    	$state.go('usermgmt.user.viewuser');
    }	
	
}]);

groupassignment.factory('groupAssignmentService', [
                            		'$q',
                            		'$http',
                            		'$state',
                            		function($q, $http, $state) {

                            			var factory = {};
                            			factory.updategroup = function(oldGroupList,newGroupList,user_id,organization_id,login_user_id) {
                            				var deferred = $q.defer();
                            				var jsonData = JSON.stringify({
                            				    params: {
                            				    	assigned: oldGroupList,
                                					unAssigned: newGroupList  
                            				    }
                            				});
                            				$http({
                            					method : 'POST',
                            					url : 'groupassignment/updategroup/'+user_id+'/'+organization_id+'/'+login_user_id,
                            					data: jsonData,
                            					headers : {
                            						'Content-Type' : 'application/json'
                            					}
                            				}).success(function(data) {
                            							console.log("Response" + JSON.stringify(data));
                            							deferred.resolve(data);
                            						}).error(function(data) {
                            					console.log("Not a valid group " + data);
                            					deferred.reject(data);
                            				});
                            				return deferred.promise;
                            			};
                            			return factory;
                            		} ]); 

