/**
 * 
 */

var customerassignment = angular.module('tfadmin.usermanagementmodule.customerassignment',['datatables']);

customerassignment.config(['$stateProvider','$urlRouterProvider',function($stateProvider,$urlRouterProvider){
	$stateProvider.state('usermgmt.customerassignment',{
		url:'/customerassignment',
		templateUrl : 'app/partial/usermanagement/customerassignment/customerassignment.html',
		controller: 'CustomerAssignmentController'
	});
}]);

customerassignment.directive('allowPattern', [ allowPatternDirective ]);

function allowPatternDirective() {
        return {
                restrict : "A",
                compile : function(tElement, tAttrs) {
                        return function(scope, element, attrs) {
                                element.bind("keypress", function(event) {
                                        var keyCode = event.which || event.keyCode;
                                        var keyCodeChar = String.fromCharCode(keyCode);
                                        if (!(keyCode === 8)) {
                                                if (!keyCodeChar.match(new RegExp(attrs.allowPattern,
                                                                "i"))) {
                                                        event.preventDefault();
                                                       return false;
                                                }
                                        }
                                });
                        };
                }
        };
}


customerassignment.controller('CustomerAssignmentController',['$scope','localStorageService','usSpinnerService','$rootScope','$http','$state','DTOptionsBuilder','DTColumnBuilder',
                                                              'customerAssignmentService','$compile','SharedService',
                                                              function($scope,localStorageService,usSpinnerService,$rootScope,$http, $state,DTOptionsBuilder, DTColumnBuilder,customerAssignmentService,$compile,SharedService){
	
	$rootScope.selList=[];
  
    
	$scope.toggle = function() {
        $scope.isVisible = ! $scope.isVisible;
    };
    // Default the blocks to be visible.
    
    $scope.isVisible = true; 
    
    $scope.defaultFilterData = {    		
    		
			customername:'',
			customernum:'',
			billtonum : '',
			addressone : '',
			city : '',
			state : '',
			postal : '',
			status : 'assigned'
	};
	$scope.searhfilter=angular.copy($scope.defaultFilterData)
    
 // Read that value back
    //var value = localStorageService.get('userOrgDetails');
    // To remove a local storage
   // localStorageService.remove('localStorageKey');
    // Removes all local storage
    //localStorageService.clearAll();
    
   // $scope.userOrgDetails = SharedService.get('userOrgDetails');
    $scope.userOrgDetails = localStorageService.get('userOrgDetails');
	var user_id = $scope.userOrgDetails.userId;
	var organization_id=$scope.userOrgDetails.orgId;
	var login_user_id =$scope.userOrgDetails.adminId;
	$scope.selectedUser= $scope.userOrgDetails.userName;
	$scope.orgName = $scope.userOrgDetails.orgName;
	
	console.log("user_id "+user_id+" organization_id "+organization_id+" login_user_id "+login_user_id);
	
	if(angular.isUndefined(user_id)){
		user_id=0;
		console.log("user_id "+user_id);
	}
	if(angular.isUndefined(organization_id)){
		organization_id=0;
		console.log(" organization_id "+organization_id);
	}
	if(angular.isUndefined(login_user_id)){
		login_user_id=0;
		console.log(" login_user_id "+login_user_id);
	}
	
	$scope.allCustomerCount = function(){
		var getCustomerUrl='getCustomerCount/'+user_id+'/'+organization_id;
		$http({
				method : 'GET',
				url : getCustomerUrl			
				
			}).success(function(data) {
						console.log("Response for customer count" + angular.toJson(data));
						
						$scope.availableCustomerCount=angular.copy(data.availableCustomerCount);
						$scope.assignedCustomerCount=angular.copy(data.assignedCustomerCount);
						
					}).error(function(data) {
				console.log("Not a valid customer " + data);
			});
	};
	
	$scope.allCustomerCount();
	
    
    $scope.assignall = function(){
    	
    	BootstrapDialog.confirm({ title: 'Assign All',
            message: 'Are you sure you want to assign all customer to user?', callback: function(result){
            	if(result) {
            		console.log($scope.searhfilter);
            		if(!angular.isUndefined($scope.searhfilter) && !angular.isUndefined($scope.searhfilter.status) && 
            				angular.equals($scope.searhfilter.status,'notassigned')){
            			if (!$scope.spinneractive) {
            				usSpinnerService.spin('spinner-1');
               		      }
            			customerAssignmentService.assignedallcustomer($scope.searhfilter,user_id,organization_id,login_user_id).then(function(data) {
            				console.log(angular.toJson(data));
            				alert('successfully assigned!');
            				if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
            				$state.reload();
            			}, function(data) {
            				if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
            				console.log("fail "+angular.toJson(data));
            				alert('fail');
            			});	
            		}else{
            			alert("Please select the Not Assigned type status and filter the result");
            		}
    	  	
            	}
            }});
    };    

    $scope.backToDivision = function() {
		$state.go('usermgmt.divisionassignment');
	}
    
	$scope.removeall = function() {
		
		BootstrapDialog.confirm({ title: 'Remove All',
            message: 'Are you sure you want to remove all customer from user?', callback: function(result){
            	if(result) {
            		
            		if(!angular.isUndefined($scope.searhfilter) && !angular.isUndefined($scope.searhfilter.status) && 
            				angular.equals($scope.searhfilter.status,'assigned')){
            			if (!$scope.spinneractive) {
            				usSpinnerService.spin('spinner-1');
               		      }
            			var removeCustomerUrl='removeallcustomer/'+user_id+'/'+organization_id+'/'+login_user_id;
                		$http({
           					method : 'POST',
           					url : removeCustomerUrl,
           					data: $scope.searhfilter,
           					headers : {
           						'Content-Type' : 'application/json'
           					}
           				}).success(function(data) {
           							console.log("Response" + JSON.stringify(data));
           							
           							if ($scope.spinneractive) {
                           		        usSpinnerService.stop('spinner-1');
                           		      }
           							$state.reload();
           						}).error(function(data) {
           							if ($scope.spinneractive) {
                           		        usSpinnerService.stop('spinner-1');
                           		      }
           					console.log("Not a valid customer " + data);
           				});
            		}else{
            			alert("Please select the Assigned type status and filter the result");
            		}           		
            	
                }
            }});	

		
	};
	
	$scope.checkStatusAssign = function() {
		 if ($scope.customer.status==="assigned") { // your question said "more than one element"
			   return true;
			  }
			  else {
			   return false;
			  }
			};
			$scope.checkStatusRemove = function() {
				 if ($scope.customer.status==="notassigned") { // your question said "more than one element"
				   return true;
				  }
				  else {
				   return false;
				  }
				};

	$scope.backToUserList=function(){
    	$state.go('usermgmt.user.viewuser');
    }

	$scope.assign = function() {
		console.log("selected list"+angular.toJson($rootScope.selList));
		
		BootstrapDialog.confirm({ title: 'Assign',
            message: 'Are you sure you want to assign selected customer to user?', callback: function(result){
            	if(result) {
            		if(!angular.isUndefined($scope.searhfilter) && !angular.isUndefined($scope.searhfilter.status) && 
            				angular.equals($scope.searhfilter.status,'notassigned')){
            		
            			$scope.unAssignedCustomerList=angular.copy($rootScope.selList);
            	        vm.selected= false;
            	        $rootScope.selList=[];
            		if(!angular.isUndefined($scope.unAssignedCustomerList) && $scope.unAssignedCustomerList.length > 0){
            			if (!$scope.spinneractive) {
            		        usSpinnerService.spin('spinner-1');
            		      }
            			var assignCustomerUrl='assigncustomer/'+user_id+'/'+organization_id+'/'+login_user_id;
            			var jsonData = JSON.stringify({custList:$scope.unAssignedCustomerList});
                		$http({
           					method : 'POST',
           					url : assignCustomerUrl,
           					data: jsonData,
           					headers : {
           						'Content-Type' : 'application/json'
           					}
           				}).success(function(data) {
           					if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
           							console.log("Response" + JSON.stringify(data));
           							$state.reload();
           						}).error(function(data) {
           							if ($scope.spinneractive) {
           		           		        usSpinnerService.stop('spinner-1');
           		           		      }
           					console.log("Not a valid customer " + data);
           				});
            		}else{
            			alert("Please select the at least one customer")
            		} }
        			else{
            			alert("Please select the Not Assigned type status and filter the result");
            		}          		
            		
                }else {
                    alert('Fail');
                }
            }});	

		
	};

	$scope.remove = function() {
		console.log("selected list"+angular.toJson($rootScope.selList));
		
		BootstrapDialog.confirm({ title: 'Remove',
            message: 'Are you sure you want to remove selected customer from user?', callback: function(result){
            	if(result) {
            		
            		if(!angular.isUndefined($scope.searhfilter) && !angular.isUndefined($scope.searhfilter.status) && 
            				angular.equals($scope.searhfilter.status,'assigned')){
            			$scope.assignedCustomerList=angular.copy($rootScope.selList);
            			 vm.selected= false;
            		        $rootScope.selList=[];
            			
            		if(!angular.isUndefined($scope.assignedCustomerList) && $scope.assignedCustomerList.length > 0){
            			if (!$scope.spinneractive) {
            		        usSpinnerService.spin('spinner-1');
            		      }
            		var removeCustomerUrl='removecustomer/'+user_id+'/'+organization_id+'/'+login_user_id;
            		
            		var jsonData = JSON.stringify({custList:$scope.assignedCustomerList});
            		
            		 console.log("custList "+jsonData);
                    
            		$http({
       					method : 'POST',
       					url : removeCustomerUrl,
       					data: jsonData,
       					headers : {
       						'Content-Type' : 'application/json'
       					}
       				}).success(function(data) {
       					if ($scope.spinneractive) {
               		        usSpinnerService.stop('spinner-1');
               		      }
       							console.log("Response" + JSON.stringify(data));
       							$state.reload();
       						}).error(function(data) {
       							if ($scope.spinneractive) {
       		           		        usSpinnerService.stop('spinner-1');
       		           		      }
       					console.log("Not a valid customer " + data);
       				});
            	}else{
        			alert("Please select the at least one customer")
        		} }else{
					alert("Please select the Assigned type status and filter the result");
				}
                }else {
                    alert('Fail');
                }
            }});		
	};
	
	$scope.downloadresult = function() {

		BootstrapDialog.confirm({ title: 'Download',
            message: 'Are you sure you want to download?', callback: function(result){
            	if(result) {            		
            		if(!angular.isUndefined($scope.searhfilter) && !angular.isUndefined($scope.searhfilter.status)){
            			if (!$scope.spinneractive) {
            		        usSpinnerService.spin('spinner-1');
            		      }
            			var donwloadUrl='downloaddocument/'+user_id+'/'+organization_id+'/'+login_user_id;
                		$http({
           					method : 'POST',
           					url : donwloadUrl,
           					data: $scope.searhfilter,
           					headers : {
           						'Content-Type' : 'application/json',
           						'accept' : 'application/vnd.ms-excel'
           					},
                		responseType: 'arraybuffer',
                        cache: false,
           				}).success(function(data, status, headers, config) {
           					headers = headers();
           			 
           		        var filename = headers['x-filename'];
           		        var contentType = headers['content-type'];
           		 
           		        var linkElement = document.createElement('a');
           		        try {
           		            var blob = new Blob([data], { type: contentType });
           		            var url = window.URL.createObjectURL(blob);
           		 
           		            linkElement.setAttribute('href', url);
           		            linkElement.setAttribute("download", filename);
           		 
           		            var clickEvent = new MouseEvent("click", {
           		                "view": window,
           		                "bubbles": true,
           		                "cancelable": false
           		            });
           		            linkElement.dispatchEvent(clickEvent);
           		        } catch (ex) {
           		            console.log(ex);
           		        }
           		     if ($scope.spinneractive) {
           		        usSpinnerService.stop('spinner-1');
           		      }
           				}).error(function(data) {
           					if ($scope.spinneractive) {
                   		        usSpinnerService.stop('spinner-1');
                   		      }
           					console.log("Not a valid customer " + data);
           				});
            		}else{
            			alert("Please select the Assigned type status and filter the result");
            		}           		
            	
                
                }
            }});
	};	
	
	//Start of Show the process dialog
	
	$scope.spinneractive = false;

    $rootScope.$on('us-spinner:spin', function(event, key) {
      $scope.spinneractive = true;
    });

    $rootScope.$on('us-spinner:stop', function(event, key) {
      $scope.spinneractive = false;
    });
  //End of Show the process dialog
		
    	vm = this;
    	 vm.selected = {};
    	 vm.dtInstance = {};
    	 $scope.url = 'customerAssignmentReport';
    	 $rootScope.obj=[];
    	
  
    	   // vm.toggleOne = toggleOne;
       
        showtable();
        
        $scope.searchByParams = function (customer) {
        	console.log("before customer"+angular.toJson(customer));
        	console.log("customer.status "+customer.status);
        	
        	
        	if(angular.isUndefined(customer)){
        		console.log("inside 1 ");
        		$scope.url = 'customerAssignmentReport';
        	}else if(!angular.isUndefined(customer) && angular.isUndefined(customer.status)){
        		console.log("inside 2 ");
        		$scope.url = 'customerAssignmentReport';
        	}else if(!angular.isUndefined(customer) && !angular.isUndefined(customer.status) && angular.equals(customer.status, 'notassigned')){
        		console.log("inside 3 ");
        		$scope.url = 'customerUnAssignmentReport';
        	}else{
        		console.log("inside 4 ");
        		$scope.url = 'customerAssignmentReport';
        	}
            showtable(customer.customername,customer.customernum, customer.billtonum, customer.addressone, customer.city, customer.state,
            		customer.postal, customer.status);
            
            if(!angular.isUndefined(customer)){
            	
            	$scope.searhfilter=angular.copy(customer)
            }
            console.log("customer"+angular.toJson(customer));
        };
        $rootScope.result=0;        
        
        function showtable(custName,custNumber,billToNumber,addressOne,city,state,postal,status) {
    
        	
            vm.dtOptions = DTOptionsBuilder.newOptions()
                    .withOption('ajax', {
                        url: $scope.url,
                        type: 'GET',                         
                       
                        data: {
                        	 'customerName': custName,
                        	 'customerNumber': custNumber,
                             'cCity':  city,
                             'addressOne': addressOne,
                             'cState': state,
                             'cPostal': postal,
                             'cBill': billToNumber,
                             'userId':user_id,
                             'orgId': organization_id,
                             'cStatus': status,
                                                
                        }
                    })
                    .withDataProp('data')
                    .withOption('processing', true)
                    .withOption('serverSide', true)
                    .withOption("bDestory", true)
                    .withPaginationType('full_numbers')
                    .withOption('bFilter', false)
                    .withOption('order', [8, 'asc'])
                    .withOption('rowCallback',rowCallback)
                    .withOption('createdRow', createdRow);
         
            
          
          //  var _toggle = true;
            
            function createdRow(row, data, dataIndex) {
  
            $rootScope.obj=vm.dtInstance.DataTable.page.info();
   
            	if(dataIndex==0)
            	select($rootScope.obj);

//s++;
            	//console.log(vm.dtInstance.DataTable.page.info()); 
            // Recompiling so we can bind Angular directive to the DT
            $compile(angular.element(row).contents())($scope);
        }
            function rowCallback(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
		    	console.log("TOTAL record "+this.fnSettings().fnRecordsTotal());
            	$rootScope.result=angular.copy(this.fnSettings().fnRecordsTotal()); 
            }
           
            $scope.sel= function(address){
          	  // console.log("get"+angular.toJson(address));
          	   var flag=0;
          	   for (var int = 0; int < $rootScope.selList.length; int++) {
          		   if ($rootScope.selList[int] == address) {
  						flag++;
  					}
          	   }
          	   if(flag==0)
          	   $rootScope.selList.push(address);
          	   else{
          		   var index=$rootScope.selList.indexOf(address);
          		   $rootScope.selList.splice(index, 1);
          	   }
          	  // console.log(angular.toJson($rootScope.selList));
          	   
              
              }
            
           // var flag1=1;
            //var flag=$rootScope.obj.length;
           // var flag2=$rootScope.obj.page;
           function select(data){
           
            	vm.selected= false;
            	 $rootScope.selList=[];
            	console.log("Current Page Information"+angular.toJson(data));
            	console.log("Total records Information"+data.recordsTotal);            	
            	console.log("Result "+$rootScope);
           
           }
            
            /*function rowCallback(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                // Unbind first in order to avoid any duplicate handler (see https://github.com/l-lin/angular-datatables/issues/87)
                $('td', nRow).unbind('click');
                $('td', nRow).bind('click', function() {
                    $scope.$apply(function() {
                        //vm.someClickHandler(aData);
                    	console.log("selected row "+angular.toJson(aData));
                    	
                    });
                });
                return nRow;
            }*/
           $scope.toggleAll= function(){
               console.log("selected list"+angular.toJson($rootScope.selList));
               vm.selected= false;
               $rootScope.selList=[];
             //  console.log("hhh"+angular.toJson($rootScope.obj));    
              // console.log(vm.dtInstance.DataTable.page.info());               
           }
        
   
           
           
            
     
           
                                vm.dtColumns = [
                                        DTColumnBuilder.newColumn(null).withTitle('Select').withOption().notSortable()
                                            .renderWith(function(data, type, full, meta) {
                                            	 if(!angular.isUndefined(data.Type) && data.Type!='')
 						                			
 			                						return '<input type="checkbox" disabled>';
 			                						else
 			                					                                            	
                                                return '<input type="checkbox" ng-model="showCase.selected[' + data.customerNumber + ']" ng-click="sel(' + data.customerNumber + ')"/>';
                                            }),
    DTColumnBuilder.newColumn('customerNumber').withTitle('Customer #').withOption('name', 'customerNumber'),
    DTColumnBuilder.newColumn('customerName').withTitle('Customer Name').withOption('name', 'customerName'),
    DTColumnBuilder.newColumn('addressOne').withTitle('Addres1').withOption('name', 'addressOne'),
    DTColumnBuilder.newColumn('city').withTitle('City').withOption('name', 'city'),
    DTColumnBuilder.newColumn('state').withTitle('State').withOption('name', 'state'),
    DTColumnBuilder.newColumn('postal').withTitle('Postal code').withOption('name', 'postal'),
    DTColumnBuilder.newColumn('country').withTitle('Country').withOption('name', 'country'),
   // DTColumnBuilder.newColumn('Status').withTitle('Status').withOption('name', 'Status').notSortable(),
    DTColumnBuilder.newColumn('Type').withTitle('Group Name').withOption('name','Type')
];

        }	
}]);

customerassignment.factory('customerAssignmentService', [
                                           		'$q',
                                           		'$http',
                                           		'$state',
                                           		function($q, $http, $state) {

                                           			var factory = {};
                                           			factory.assignedallcustomer = function(customer,user_id,organization_id,login_user_id) {
                                           				var deferred = $q.defer();   
                                           				$http({
                                           					method : 'POST',
                                           					url : 'assignallcustomer/'+user_id+'/'+organization_id+'/'+login_user_id,
                                           					data: customer,
                                           					headers : {
                                           						'Content-Type' : 'application/json'
                                           					}
                                           				}).success(function(data) {
                                           							console.log("Response" + JSON.stringify(data));
                                           							deferred.resolve(data);
                                           						}).error(function(data) {
                                           					console.log("Not a valid customer " + data);
                                           					deferred.reject(data);
                                           				});
                                           				return deferred.promise;
                                           			};
                                           			return factory;
                                           		} ]); 
