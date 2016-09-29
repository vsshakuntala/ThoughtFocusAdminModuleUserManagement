
/**
 *
 */

var divisionassignment = angular.module('tfadmin.usermanagementmodule.divisionassignment', ['datatables']);

divisionassignment.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $stateProvider.state('usermgmt.divisionassignment', {
        url: '/divisionassignment',
        templateUrl: 'app/partial/usermanagement/divisionassignment/divisionassignment.html',
        controller: 'DivisionAssignmentController'
    });
}]);

divisionassignment.controller('DivisionAssignmentController', ['$scope','$rootScope','usSpinnerService', 'localStorageService','$state', '$http','DTOptionsBuilder', 'SharedService','genericService','$loading','$uibModal','$log','$rootScope',
                                                               function ($scope,$rootScope,usSpinnerService,localStorageService, $state, $http,DTOptionsBuilder, SharedService,genericService, $loading,$uibModal, $log,$rootScope) {
	
	//data that we are getting from list of users(adimId:'2',userId:aData.userId,userName:aData.userName).
	//$scope.userAndAdminDetails = SharedService.get('userAndAdminDetails');
	$scope.userAndAdminDetails = localStorageService.get('userAndAdminDetails');
	$rootScope.heading='';
	$rootScope.title='';
	console.log(angular.toJson($scope.userAndAdminDetails));
	
	$scope.dtOptions = DTOptionsBuilder.newOptions()
	.withPaginationType('full_numbers')
	.withOption('drawCallback', function () {
		$(window).trigger('resize');
	});
	
//Start of Show the process dialog
	
	$scope.spinneractive = false;

    $rootScope.$on('us-spinner:spin', function(event, key) {
      $scope.spinneractive = true;
    });

    $rootScope.$on('us-spinner:stop', function(event, key) {
      $scope.spinneractive = false;
    });
  //End of Show the process dialog

    $scope.userNote = {};

    $scope.submitNoteOfUser = function (userNote) {
        $scope.userNotes.description = $scope.userNote;
        $scope.notes.push($scope.userNotes);
        console.log($scope.notes);

    }

    $scope.divisionList = [];
    
    $scope.userNameSetLocal=$scope.userAndAdminDetails.userName;
    //Setting userOrgDetails in SharedService
    $scope.userDetailsObj = {
    		"userId":$scope.userAndAdminDetails.userId,
    		"adminId":$scope.userAndAdminDetails.adimId,
    		"userName":$scope.userAndAdminDetails.userName
    };
    
    SharedService.set('userOrgDetails',$scope.userDetailsObj);
    
    
    $scope.userDetails={};
    
    if (!$scope.spinneractive) {
            				usSpinnerService.spin('spinner-1');
               		      }
    genericService.getObjects('divisionAssignment/getDivisionAssignments/'+$scope.userAndAdminDetails.userId+'/'+$scope.userAndAdminDetails.adimId).then(function (data) {
    	console.log(data);
    	console.log("Data :"+angular.toJson(data));
    	$scope.divisionList=data;
        
        var assignedCount=0;
        for (var i=0; i < $scope.divisionList.length; i++) {
            if($scope.divisionList[i].status=='Approved'){
            	assignedCount++;
            }
        }
        console.log($scope.divisionList);
        if($scope.divisionList.length > 0){
        $scope.userDetails.count=assignedCount;
        
        $scope.userDetails.createdDate=$scope.userAndAdminDetails.createdDate;
        $scope.userDetails.lastLoginDate=$scope.userAndAdminDetails.Last_modified_date;
        
    	if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
        }else{
        	if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
        	BootstrapDialog.alert({
      			 title: 'Message',
      			    message: 'There are no active divisions for the user, please add divisions from Add Division', 
      			    buttons: [{
      			        id: 'btn-ok',   
      			        icon: 'glyphicon glyphicon-check',       
      			        label: 'Close',
      			        cssClass: 'btn-primary', 
      			        autospin: false,
      			        action: function(dialogRef){    
      			            dialogRef.close();
      			        }
      			    }]});
        	
        }
        console.log("details :"+angular.toJson($scope.userDetails));
        //$scope.getNotesList();
        
	}, function (data) {
		if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
		if(data.statusCode==204){
			BootstrapDialog.alert({
     			 title: 'Message',
     			    message: 'There are no active divisions for the user, please add divisions from Add Division', 
     			    buttons: [{
     			        id: 'btn-ok',   
     			        icon: 'glyphicon glyphicon-check',       
     			        label: 'Close',
     			        cssClass: 'btn-primary', 
     			        autospin: false,
     			        action: function(dialogRef){    
     			            dialogRef.close();
     			        }
     			    }]});
		}
		
		else{
		$.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
			from: "top",
			align: "right"
		}});
		}//end of else
		$scope.getNotesList();
	});
    
  
    $scope.checkRoleResults = function (division) {
        //role
        if (division.roleCount > 0) {
            return true;
        } else {
            return false;
        }
    };

    $scope.checkMachinesResults = function (division) {
        //machines
        if ((division.catalogCount + division.catalogGroupCount) > 0) {
            return true;
        } else {
            return false;
        }
    };

    $scope.checkCustomerResults = function (division) {
        //customers
        if ((division.customerCount + division.customerGroupCount) > 0) {
            return true;
        } else {
            return false;
        }
    };

    $scope.checkDefaultAddressResults = function (division) {
        //address
        if (division.defaultAddressCount > 0) {
            return true;
        } else {
            return false;
        }
    };

    $scope.checkGroupResults = function (division) {
        //groups
        if (division.groupsCount > 0) {
            return true;
        } else {
            return false;
        }
    };

    $scope.checkApproveDivisionUI = function (division) {
        
        if ((division.customerCount + division.customerGroupCount) > 0 &&
            division.defaultAddressCount > 0  &&  division.roleCount>0) {
            return true;
        } else {
            return false;
        }
    };

    

    $scope.checkRemoveDivisionUI = function (division) {

       /* if ((division.customerCount + division.customerGroupCount) > 0
            && division.defaultAddressCount > 0 && (division.catalogCount + division.catalogGroupCount) > 0 && division.status=='Approved') {

            return false;
        } else {
            return true;
        }*/
    	
    	return true;

    };
    
    $scope.checkAddDivisionUI = function (division) {
        
        if (division.roleCount < 1 && (division.customerCount + division.customerGroupCount) < 1 && division.defaultAddressCount < 1 && (division.catalogCount + division.catalogGroupCount) < 1) {

            return true;
        } else {
            return false;
        }

    };
    
   
    
  /**$scope.deAssignDivisionToUser = function(division) {
     if (division.adminAccess) {
			BootstrapDialog.confirm('Are you sure you want to De-assign?',function(result) {
			if (result) {	
					var userInput = {};
					userInput.userId = $scope.userAndAdminDetails.userId;
					userInput.organizationId = division.organizationId;
					userInput.modifiedById = $scope.userAndAdminDetails.adimId;
					if (!$scope.spinneractive) {
            				usSpinnerService.spin('spinner-1');
               		      }
					genericService.addObject('divisionAssignment/deAssignAllUserAllocations',userInput).then(function (data) {
						$('#unAssignedOrgModel').modal('hide');
						
						$.notify({icon: 'glyphicon glyphicon-ok',message:'De-asssignment of division to user is successfull' },{ type: 'success', placement: {
							from: "top",
							align: "right"
						}});
						
						$state.go('usermgmt.divisionassignment',null,{reload : true});
						if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
						}, function (data) {
							if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
							$.notify({icon: 'glyphicon glyphicon-warning-sign',message:'De-asssignment of division to user is un-successfull' },{ type: 'success', placement: {
								from: "top",
								align: "right"
							}});
							
						});
				}//end of if for confirmation dialog

			});//end of Bootstrap dialog
	} else {
		BootstrapDialog.alert({title : 'Message',message : 'You dont have admin access for this Division',
		buttons : [ {
				id : 'btn-ok',
				icon : 'glyphicon glyphicon-check',
				label : 'Close',
				cssClass : 'btn-primary',
				autospin : false,
				action : function(dialogRef) {
					dialogRef.close();
				}
				}]
				});
		}//end of else
	}//end of function of deAssignDivisionToUser()
    
  	//function to add assign/approve user to organization
    $scope.assignDivisionToUser=function(division){
    	if(division.adminAccess){
   		 BootstrapDialog.confirm('Are you sure you want to Assign?', function(result){
   			 if(result) {
   				 	if (!$scope.spinneractive) {
            				usSpinnerService.spin('spinner-1');
               		      }
					var userInput = {};
					userInput.userId=$scope.userAndAdminDetails.userId;
               		userInput.organizationId=division.organizationId;
               		userInput.modifiedById=$scope.userAndAdminDetails.adimId;
					
					genericService.addObject('divisionAssignment/addOrAssignUserToOrg',userInput).then(function (data) {
						
						$('#unAssignedOrgModel').modal('hide');
						
						$.notify({icon: 'glyphicon glyphicon-ok',message:'Asssignment of division to user is successfull' },{ type: 'success', placement: {
							from: "top",
							align: "right"
						}});
						
						$state.go('usermgmt.divisionassignment',null,{reload : true});
						if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
						}, function (data) {
							if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
							$.notify({icon: 'glyphicon glyphicon-warning-sign',message:'Asssignment of division to user is un-successfull' },{ type: 'success', placement: {
								from: "top",
								align: "right"
							}});
						});
				
   			 }//end of If for confirm dialog
       
           });//end of Bootstrap dialog
   	}else{
   		BootstrapDialog.alert({title: 'Message',message: 'You dont have admin access for this Division',
  			    buttons: [{
  			        id: 'btn-ok',   
  			        icon: 'glyphicon glyphicon-check',       
  			        label: 'Close',
  			        cssClass: 'btn-primary', 
  			        autospin: false,
  			        action: function(dialogRef){    
  			            dialogRef.close();
  			        }
  			    }]});
   		}//end of else
    }//end of method assignDivisionToUser()*/
    
    //use to assign division from selected unassign division
	$scope.assigndivision = function(unassigneddivision) {

        console.log("unassigned division "+angular.toJson(unassigneddivision));     
        
        var i;
        if(unassigneddivision.length != 0){      	
        
        for (i = 0; i < unassigneddivision.length; i++) {
        	 console.log('before assigned: '+$scope.assignedorglist);
        	  var index = $scope.assignedorglist.indexOf(unassigneddivision[i])
        	  var index1 = $scope.unassgList.indexOf(unassigneddivision[i])
        	  console.log('Unassigned: '+index);
            if (index == -1) {
            	$scope.assignedorglist.push(unassigneddivision[i]);            	
            }
            
            if(index1 != -1){
            	console.log('Index: '+index);
            	$scope.unassgList.splice(index1, 1); 
            	
            }
            
            console.log('assigned: '+$scope.assignedorglist);
        } 
	  }
    };
    
  //use to unassign division from selected assign division
    $scope.unassigndivision = function(selecteddevision){
    console.log("selecteddivision "+angular.toJson(selecteddevision));     
        
        var i;
        if(selecteddevision.length != 0){      	
        
        for (i = 0; i < selecteddevision.length; i++) {
        	
        	  var index = $scope.unassgList.indexOf(selecteddevision[i])
        	  var index1 = $scope.assignedorglist.indexOf(selecteddevision[i])
        	  console.log('Unassigned: '+index);
            if (index == -1) {
            	$scope.unassgList.push(selecteddevision[i]);
            	
            }
            
            if(index1 != -1){
            	console.log('Index: '+index);
            	$scope.assignedorglist.splice(index1, 1);          	
            	console.log('Lenght aft remove: '+$scope.unassgList.length);
            	
            }
        } 
	}
    };
   
    //function to add user to organization
    $scope.addOrganization = function(){
    	/*if(division.adminAccess){*/
    	if(!angular.isUndefined($scope.assignedorglist) && $scope.assignedorglist.length >0){
    		
    	
      		 BootstrapDialog.confirm('Are you sure you want to Assign?', function(result){
      			 if(result) {
      				 if (!$scope.spinneractive) {
            				usSpinnerService.spin('spinner-1');
               		      }
      				
      				 var userInput = {};
      				 userInput.userId=$scope.userAndAdminDetails.userId;
      				 userInput.organizationIds=angular.copy($scope.assignedorglist);
      				 userInput.modifiedById=$scope.userAndAdminDetails.adimId;
      				 userInput.addUserToOrg=true;
      				 if(!angular.isUndefined($scope.notemsg)){
      					 userInput.note=$scope.notemsg;
      					 
      				 }else{
      					userInput.note='';
      				 }
      				 
 					console.log("userInput "+angular.toJson(userInput));
 					genericService.addObject('divisionAssignment/addOrAssignUserToOrg',userInput).then(function (data) {
 						
 						$('#unAssignedOrgModel').modal('hide');
 						
 						$.notify({icon: 'glyphicon glyphicon-ok',message: 'Adding Division to user is successfully' },{ type: 'success', placement: {
 							from: "top",
 							align: "right"
 						}});
 						
 						$state.go('usermgmt.divisionassignment',null,{reload : true});
 						
 						if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
 						}, function (data) {
 							if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
 							userInput.note='';
 							$.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
 								from: "top",
 								align: "right"
 							}});
 						});
 				}//end of if for confirmation dialog
          
              });//end of Bootstrap dialog
      	/*}else{
      		BootstrapDialog.alert({
     			 title: 'Message',message: 'You dont have admin access for this Division',
     			    buttons: [{
     			        id: 'btn-ok',   
     			        icon: 'glyphicon glyphicon-check',       
     			        label: 'Close',
     			        cssClass: 'btn-primary', 
     			        autospin: false,
     			        action: function(dialogRef){    
     			            dialogRef.close();
     			        }
     			    }]});
      	}*///end of else
    	}else {
    		alert("Select at least one division from left side if available!")
    	}
       }//end of function for addOrganization()
    
    $scope.cancel = function(){
    	$('#unAssignedOrgModel').modal('hide');
    }
    
    
    $scope.checkDivisionStatus = function (division) {
        if (division.status === 'Approved') {

            return true;
        } else {
            return false;
        }
    };
    
    
    //function to get Un-assigned organizations for user
    $scope.unassignedOrganization = function(){
    	var userId=$scope.userAndAdminDetails.userId;
    	var adminId=$scope.userAndAdminDetails.adimId; 
    	if (!$scope.spinneractive) {
            				usSpinnerService.spin('spinner-1');
               		      }
    	genericService.getObjects('divisionAssignment/getUnAssignedDivisionsForUser/'+userId+'/'+adminId+'/').then(function (data) {
    		$scope.unassgList=data;
    		$scope.assignedorglist=[];
    		$scope.getNotesList();
    		if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }    
    		}, function (data) {
    			$scope.getNotesList();
    			if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
    			$.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
    				from: "top",
    				align: "right"
    			}});
    		});	  
    }
    
    
    //function to get notes of the user
    $scope.getNotesList = function(){
    	$scope.notes = [];
    	var userId=$scope.userAndAdminDetails.userId;	
    	if (!$scope.spinneractive) {
            				usSpinnerService.spin('spinner-1');
               		      }
    	genericService.getObjects('divisionAssignment/getNotesOfUser/'+userId).then(function (data) {
    		if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
    		$scope.notes=data;
    		}, function (data) {
    			if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
    			$.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
    				from: "top",
    				align: "right"
    			}});
    		});	  	
    }
    
    //function to add notes of the user
    
    $scope.submitNotes = function(userNotesInput){
    	
    	
    	var userId=$scope.userAndAdminDetails.userId;
    	var createdById=$scope.userAndAdminDetails.adimId;
    	userNotesInput.userId=userId;
    	userNotesInput.createdBy=createdById;
    	
    	
    	if (!$scope.spinneractive) {
            				usSpinnerService.spin('spinner-1');
               		      }
    	genericService.addObject('divisionAssignment/addNotesForUser',userNotesInput).then(function (data) {
    		if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
    		userNotesInput.notes='';
    		$.notify({icon: 'glyphicon glyphicon-ok',message: data.message },{ type: 'success', placement: {
				from: "top",
				align: "right"
			}});
			}, function (data) {
				if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
				$.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
					from: "top",
					align: "right"
				}});
			});
    	
    		  	
    }
    

    /*
     * navigation to different modules based on user
     * selection
     */

    $scope.navigateToRoles = function (division) {
    	
    	if (division.adminAccess) {
    		$scope.userDetailsObj.orgId=division.organizationId;
        	$scope.userDetailsObj.orgName=division.organizationName;
        	$scope.userDetailsObj.assignedDivisionsCount=$scope.userDetails.count;
        	localStorageService.set('userOrgDetails',$scope.userDetailsObj);
        	$state.go('usermgmt.rolesassignment');
    	}else{
    		BootstrapDialog.alert({title : 'Message',message : 'You dont have admin access for this Division',
    			buttons : [ {
    					id : 'btn-ok',
    					icon : 'glyphicon glyphicon-check',
    					label : 'Close',
    					cssClass : 'btn-primary',
    					autospin : false,
    					action : function(dialogRef) {
    						dialogRef.close();
    					}
    					}]
    					});
    	}
    	
    	
    };

    $scope.navigateToMachines = function (division) {
    	
    	if(division.adminAccess){
    		$scope.userDetailsObj.orgId=division.organizationId;
        	$scope.userDetailsObj.orgName=division.organizationName;
        	$scope.userDetailsObj.assignedDivisionsCount=$scope.userDetails.count;
        	 // To add to local storage     	
        	localStorageService.set('userOrgDetails',$scope.userDetailsObj);
            $state.go('usermgmt.machineassignment');
    	}else{
    		BootstrapDialog.alert({title : 'Message',message : 'You dont have admin access for this Division',
    			buttons : [ {
    					id : 'btn-ok',
    					icon : 'glyphicon glyphicon-check',
    					label : 'Close',
    					cssClass : 'btn-primary',
    					autospin : false,
    					action : function(dialogRef) {
    						dialogRef.close();
    					}
    					}]
    					});
    	}
    	
    };

    $scope.navigateToCustomers = function (division) {
    	
    	if(division.adminAccess){
    		$scope.userDetailsObj.orgId=division.organizationId;
        	$scope.userDetailsObj.orgName=division.organizationName;
        	$scope.userDetailsObj.assignedDivisionsCount=$scope.userDetails.count;
        	 // To add to local storage     	
        	localStorageService.set('userOrgDetails',$scope.userDetailsObj);
            $state.go('usermgmt.customerassignment');
    	}else{
    		BootstrapDialog.alert({title : 'Message',message : 'You dont have admin access for this Division',
    			buttons : [ {
    					id : 'btn-ok',
    					icon : 'glyphicon glyphicon-check',
    					label : 'Close',
    					cssClass : 'btn-primary',
    					autospin : false,
    					action : function(dialogRef) {
    						dialogRef.close();
    					}
    					}]
    					});
    	}
    	
    };

    $scope.navigateToDefaultAddress = function (division) {
    	if(division.adminAccess){
    		$scope.userDetailsObj.orgId=division.organizationId;
        	$scope.userDetailsObj.orgName=division.organizationName;
        	$scope.userDetailsObj.assignedDivisionsCount=$scope.userDetails.count;
        	 // To add to local storage     	
        	localStorageService.set('userOrgDetails',$scope.userDetailsObj);
            $state.go('usermgmt.addressassignment');
    	}else{
    		BootstrapDialog.alert({title : 'Message',message : 'You dont have admin access for this Division',
    			buttons : [ {
    					id : 'btn-ok',
    					icon : 'glyphicon glyphicon-check',
    					label : 'Close',
    					cssClass : 'btn-primary',
    					autospin : false,
    					action : function(dialogRef) {
    						dialogRef.close();
    					}
    					}]
    					});
    	}
    	
    };

    $scope.navigateToGroups = function (division) {
    	if(division.adminAccess){
    		$scope.userDetailsObj.orgId=division.organizationId;
        	$scope.userDetailsObj.orgName=division.organizationName;
        	$scope.userDetailsObj.assignedDivisionsCount=$scope.userDetails.count;
        	 // To add to local storage     	
        	localStorageService.set('userOrgDetails',$scope.userDetailsObj);
            $state.go('usermgmt.groupassignment');
    	}else{
    		BootstrapDialog.alert({title : 'Message',message : 'You dont have admin access for this Division',
    			buttons : [ {
    					id : 'btn-ok',
    					icon : 'glyphicon glyphicon-check',
    					label : 'Close',
    					cssClass : 'btn-primary',
    					autospin : false,
    					action : function(dialogRef) {
    						dialogRef.close();
    					}
    					}]
    					});
    	}
    	
    };
    
    $scope.navigateToEditUser = function () {
    	 // To add to local storage     	
    	localStorageService.set('userOrgDetails',$scope.userDetailsObj);
        $state.go('usermgmt.user.updateuser');
    };
    
    $scope.backToUserList=function(){
    	//back to user list
    	$state.go('usermgmt.user.viewuser');
    }
    
//    Code for custom popup
    var $ctrl = this;
    $ctrl.items = ['item1', 'item2', 'item3'];

    $ctrl.animationsEnabled = true;

    $scope.open = function (division,isAssignflag) {
    	
    	console.log('in open method :'+angular.toJson(division)+' '+isAssignflag);
    	if(isAssignflag){
    		$rootScope.heading='Assign User';
    		$rootScope.title='Are you sure you want to assign?'
    	}else{
    		$rootScope.heading='De-assign User';
    		$rootScope.title='Are you sure you want to de-Assign?'
    	}
    	$ctrl.division=division;
    	 if (division.adminAccess) {
    		 $scope.getNotesList();
    	      var modalInstance = $uibModal.open({
    	        animation: $ctrl.animationsEnabled,
    	        ariaLabelledBy: 'modal-title',
    	        ariaDescribedBy: 'modal-body',
    	        templateUrl: 'app/partial/usermanagement/divisionassignment/customPopup.html',
    	        controller: 'ModalInstanceCtrl',
    	        controllerAs: '$ctrl',
    	        size: 0,
    	        resolve: {
    	          items: function () {
    	            return $ctrl.items;
    	          },
    	      division: function () {
  	            return $ctrl.division;
  	          },
  	          isAssignflagClicked :function () {
  	            return isAssignflag;
	          }
    	        }
    	      });

    	      modalInstance.result.then(function (selectedItem) {
    	        $ctrl.selected = selectedItem;
    	      }, function () {
    	        $log.info('Modal dismissed at: ' + new Date());
    	      });
      }else{
    	  BootstrapDialog.alert({title : 'Message',message : 'You dont have admin access for this Division',
				buttons : [ {
						id : 'btn-ok',
						icon : 'glyphicon glyphicon-check',
						label : 'Close',
						cssClass : 'btn-primary',
						autospin : false,
						action : function(dialogRef) {
							dialogRef.close();
						}
						}]
						});
      }
    	
    };

    $ctrl.openComponentModal = function () {
      var modalInstance = $uibModal.open({
        animation: $ctrl.animationsEnabled,
        component: 'modalComponent',
        resolve: {
          items: function () {
            return $ctrl.items;
          }
        }
      });

      modalInstance.result.then(function (selectedItem) {
        $ctrl.selected = selectedItem;
      }, function () {
        $log.info('modal-component dismissed at: ' + new Date());
      });
    };

    $ctrl.toggleAnimation = function () {
      $ctrl.animationsEnabled = !$ctrl.animationsEnabled;
    };
    
    
}]);

//Please note that $uibModalInstance represents a modal window (instance) dependency.
// It is not the same as the $uibModal service used above.

divisionassignment.controller('ModalInstanceCtrl',function ($uibModalInstance, items,division,isAssignflagClicked,$scope,localStorageService,$loading,genericService,$state,$rootScope) {
$scope.userAndAdminDetails = localStorageService.get('userAndAdminDetails');
$scope.userNotesInput = {
		userId : 0,
		createdBy:0,
		notes : ''
}
//$rootScope.heading='';
  var $ctrl = this;
  $ctrl.items = items;
  $ctrl.selected = {
    item: $ctrl.items[0]
  };
  
//function to add assign/approve user to organization
    $scope.assignDivisionToUser=function(division,notes){
    	
   				 	$loading.start('users');
					var userInput = {};
					userInput.userId=$scope.userAndAdminDetails.userId;
               		userInput.organizationId=division.organizationId;
               		userInput.modifiedById=$scope.userAndAdminDetails.adimId;
               		userInput.notes =notes;
               		console.log('Json generated assignDivisionToUser:'+angular.toJson(userInput));
					genericService.addObject('divisionAssignment/addOrAssignUserToOrg',userInput).then(function (data) {
						
						$('#unAssignedOrgModel').modal('hide');
						
						$.notify({icon: 'glyphicon glyphicon-ok',message:'Asssignment of division to user is successfull' },{ type: 'success', placement: {
							from: "top",
							align: "right"
						}});
						
						$state.go('usermgmt.divisionassignment',null,{reload : true});
						$loading.finish('users');
						}, function (data) {
							$loading.finish('users');
							$.notify({icon: 'glyphicon glyphicon-warning-sign',message:'Asssignment of division to user is un-successfull' },{ type: 'success', placement: {
								from: "top",
								align: "right"
							}});
						});
				
   			
    }//end of method assignDivisionToUser()
  
  $scope.deAssignDivisionToUser = function(division,notes) {
	  console.log('deAssignDivisionToUser :'+division.adminAccess+'  '+isAssignflagClicked);
	 	
						var userInput = {};
						userInput.userId = $scope.userAndAdminDetails.userId;
						userInput.organizationId = division.organizationId;
						userInput.modifiedById = $scope.userAndAdminDetails.adimId;
						userInput.notes =notes;
						console.log('Json generated :'+angular.toJson(userInput));
						$loading.start('users');
						genericService.addObject('divisionAssignment/deAssignAllUserAllocations',userInput).then(function (data) {
							$('#unAssignedOrgModel').modal('hide');
							
							$.notify({icon: 'glyphicon glyphicon-ok',message:'De-asssignment of division to user is successfull' },{ type: 'success', placement: {
								from: "top",
								align: "right"
							}});
							
							$state.go('usermgmt.divisionassignment',null,{reload : true});
							$loading.finish('users');
							}, function (data) {
								$loading.finish('users');
								$.notify({icon: 'glyphicon glyphicon-warning-sign',message:'De-asssignment of division to user is un-successfull' },{ type: 'success', placement: {
									from: "top",
									align: "right"
								}});
								
							});
		}//end of function of deAssignDivisionToUser()
  
 

  $ctrl.ok = function () {
    $uibModalInstance.close($ctrl.selected.item);
    console.log('In ok button division:'+angular.toJson(division)+'  '+$scope.userNotesInput.notes);
    if(isAssignflagClicked){
    	$scope.assignDivisionToUser(division,$scope.userNotesInput.notes);
    }else{
    	$scope.deAssignDivisionToUser(division,$scope.userNotesInput.notes);
    }
  };
  

  $ctrl.cancel = function () {
    $uibModalInstance.dismiss('cancel');
    console.log('In cancel button');
  };
});

// Please note that the close and dismiss bindings are from $uibModalInstance.

divisionassignment.component('modalComponent', {
  templateUrl: 'myModalContent.html',
  bindings: {
    resolve: '<',
    close: '&',
    dismiss: '&'
  },
  controller: function () {
    var $ctrl = this;

    $ctrl.$onInit = function () {
      $ctrl.items = $ctrl.resolve.items;
      $ctrl.selected = {
        item: $ctrl.items[0]
      };
    };

    $ctrl.ok = function () {
      $ctrl.close({$value: $ctrl.selected.item});
    };

    $ctrl.cancel = function () {
      $ctrl.dismiss({$value: 'cancel'});
    };
  }
});
