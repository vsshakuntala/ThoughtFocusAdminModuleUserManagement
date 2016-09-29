/**
 * 
 */

var addressassignment = angular.module(
    'tfadmin.usermanagementmodule.addressassignment', ['ngMaterial',
        'ngMessages', 'ngAnimate', 'ngSanitize']);

addressassignment
    .config([
        '$stateProvider',
        '$urlRouterProvider',
        function ($stateProvider, $urlRouterProvider) {
            $stateProvider
                .state(
                'usermgmt.addressassignment',
                {
                    url: '/addressassignment',
                    templateUrl: 'app/partial/usermanagement/addressassignment/addressassignment.html',
                    controller: 'AddressAssignmentController'
                });
        }]);

addressassignment
    .controller('AddressAssignmentController',['$scope','localStorageService','$state','$http','$filter','SharedService','genericService','$templateCache','$loading',
        function ($scope,localStorageService, $state, $http, $filter, SharedService,genericService, $templateCache, $loading) {

            $scope.userOrgDetails = localStorageService.get('userOrgDetails');
            $scope.userAndAdminDetails = localStorageService.get('userAndAdminDetails');
           
            
            $scope.userNameSetLocal = $scope.userAndAdminDetails.userName;
            $scope.orgName=$scope.userOrgDetails.orgName;
            $scope.selectedSalesAreaId = 0;
            $scope.selectedCustomerId = 0;
            $scope.selectedBillToAddressId = 0;
            $scope.selectedShipToId = 0;
            $scope.selectedUserOrgId = 0;
            $scope.selectedUserId = 0;
            $scope.selectedOrganizationId = 0;
            $scope.ships = [];
            $scope.shipList = [];
            $scope.ids = [];
            $scope.selectedArea = 0;
            $scope.selectedChannel='';
            $scope.area={
            		salesAreaId :0,
            		salesOrgName :'',
            		distributionChannelName:''
            };
            
            $scope.selectedUserId = $scope.userOrgDetails.userId;
            console.log('user org details :'+$scope.selectedUserId);

            $scope.isItEquals = function(o1, o2){
                if(angular.equals(o1,o2)){
                    return true;
                } else{
                    return false;
                }
            }

            $scope.getsalesAreaId = function () { 
            	console.log('222222 :'+$scope.area);
            	console.log('#### '+$scope.area.salesOrgName);
            	$scope.selectedChannel=$scope.area.distributionChannelName;
                $scope.selectedCustomerId = 0;
                $scope.custList=[];
                if ($scope.area.salesAreaId !== 0) {
                	$scope.selectedSalesAreaId=$scope.area.salesAreaId;
                    
                    console.log('userorgid while getting customer',$scope.selectedUserId);
                    var url = 'addassgn/getcatlist/' + $scope.selectedSalesAreaId + '/' + $scope.selectedUserId;
                    genericService.getObjects(url).then(function (data) {
                        if (data.length) {
                            $scope.custList = data;
                            var obj = $filter('filter')($scope.custList, { customerId: $scope.ids.customerId }, true);
                            if (obj.length) {
                                $scope.selectedCustomerId = obj[0].customerId;
                                $scope.getCustId($scope.selectedCustomerId);
                            }
                        }else {
                            $scope.selectedBillToAddressId=0;
                            $scope.selectedShipToId=0;
                            BootstrapDialog.alert('No Customer for the selected Sales');
                        }
                    }, function (data) {
                        $scope.selectedBillToAddressId=0;
                        $scope.selectedShipToId=0;
                        $scope.billList=[];
                        $scope.shipList=[];
                    	$loading.finish('users');
                		if(data.statusCode==204){
                			BootstrapDialog.alert({
                     			 title: 'Message',
                     			    message: 'There are no customers for the Sales', 
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
                		}else {
                            $.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
                                from: 'top',
                                align: 'right'
                            }});
                		}
                    });
                } else {
                    $scope.selectedUserId = $scope.userOrgDetails.userId;
                    var url1 = 'addassgn/allcustomerlist/' + $scope.userOrgDetails.userId;
                    genericService.getObjects(url1).then(function (data) {
                        if (data.length) {
                            $scope.custList = data;
                            var obj = $filter('filter')($scope.custList, { customerId: $scope.ids.customerId }, true);
                            if (obj.length) {
                                $scope.selectedCustomerId = obj[0].customerId;
                                $scope.getCustId($scope.selectedCustomerId);
                            }
                        } else {
                            $scope.selectedBillToAddressId=0;
                            $scope.selectedShipToId=0;
                            $scope.billList=[];
                            BootstrapDialog.alert('No Customer for the selected User');
                        }
                    },function (data) {
                        $scope.selectedBillToAddressId=0;
                        $scope.selectedShipToId=0;
                        $scope.billList=[];
                        $scope.shipList=[];
                		$loading.finish('users');
                		if(data.statusCode==204){
                			BootstrapDialog.alert({
                     			 title: 'Message',
                     			    message: 'There are no customers for the user', 
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
                                from: 'top',
                                align: 'right'
                            }});
                		}//end of else
                	});
                }
            }

            $scope.getAlluser = function () {
                var objinput = {};
                objinput.userId = $scope.userOrgDetails.userId;
                objinput.organizationId = $scope.userOrgDetails.orgId;
                var url1 = 'addassgn/getuserorgid/';
                genericService.addObject(url1, objinput).then(function (data) {
                    console.log(angular.toJson(data));
                    for (var i = 0; i < data.length; i++) {
                        var userOrgId = data[i].userOrgMapId;
                        objinput.userOrgId = userOrgId;
                    }
                    var url2 = 'divisionAssignment/getDefaultAddressForUserOrg/' + objinput.userOrgId;
                    genericService.getObjects(url2).then(function (data) {
                        $scope.ids = data;
                        console.log($scope.ids)
                        $scope.selectedOrganizationId = $scope.userOrgDetails.orgId;
                        var req = {
                            method: 'GET',
                            url: 'addassgn/getsalesarealist/' + $scope.selectedOrganizationId
                        }
                        $http(req).success(function (data) {
                            $scope.areaList = data;
                            console.log('api call :'+angular.toJson(data));
                            if ($scope.areaList.length !== 0) {
                                var obj = $filter('filter')($scope.areaList, { salesAreaId: $scope.ids.salesAreaId }, true);
                                if (obj.length) {
                                    $scope.selectedSalesAreaId = obj[0].salesAreaId;
                                    $scope.getsalesAreaId($scope.selectedSalesAreaId);
                                }
                            } else {
                                BootstrapDialog.alert('No Sales for the selected User and Organization. Want to continue!!');
                                $scope.getsalesAreaId(0);
                            }

                        });
                    },function () {
                            console.log('call failed sales area');
                        });
                },function (data) {
                	$loading.finish('users');
            		if(data.statusCode==204){
            			BootstrapDialog.alert({
                 			 title: 'Message',
                 			    message: 'There are no user org for the user and organiztion', 
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
            		}else{
                        $.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
                            from: 'top',
                            align: 'right'
                        }});
            		}//end of else
                });

            }
            $scope.getAlluser();

            $scope.getCustId = function (custId) {
              $scope.selectedBillToAddressId=0;
              $scope.billList=[];
                $scope.selectedCustomerId = custId;
                var url = 'addassgn/getbilladd/' + custId + '/'+ 1;
                $loading.start('users');
                genericService.getObjects(url).then(function (data) {
                        if (data.length) {
                            $loading.finish('users');
                            $scope.billList = data;
                            var obj = $filter('filter')($scope.billList, { addressId: $scope.ids.billToAddressId }, true);
                                if (obj.length) {
                                    console.log();
                                    $scope.selectedBillToAddressId = obj[0].addressId;
                                    $scope.getBill($scope.selectedBillToAddressId);
                                }
                        } else {
                            $loading.finish('users');
                            BootstrapDialog.alert('No Billing Address for the selected Customer');
                        }
                    }, function (data) {
                    	$loading.finish('users');
                		if(data.statusCode==204){
                			BootstrapDialog.alert({
                     			 title: 'Message',
                     			    message: 'There are no Billing Address for the selected Customer', 
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
                		}else{
                            $.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
                                from: 'top',
                                align: 'right'
                            }});
                		}//end of else
                    });
            }

            $scope.getBill = function (bilToId) {
                $scope.selectedShipToId=0;
                $scope.shipList=[];
                $scope.selectedBillToAddressId = bilToId;
                var saddressTypeId = 2
                var url = 'addassgn/getshipadd/'+ $scope.selectedCustomerId + '/'+ saddressTypeId;
                $loading.start('users');
                genericService.getObjects(url).then(function (data) {
                        if (data.length) {
                            $loading.finish('users');

                            $scope.shipList = data;
                            var obj = $filter('filter')($scope.shipList, { addressId: $scope.ids.shipToAddressId }, true);
                                if (obj.length) {
                                    
                                    $scope.selectedShipToId = obj[0].addressId;
                                    $scope.getShip($scope.selectedShipToId);
                                }
                        } else {
                            $loading.finish('users');
                            BootstrapDialog.alert('No Shipping Address for the selected Customer');
                        }
                    }, function (data) {
                    	$loading.finish('users');
                		if(data.statusCode==204){
                			BootstrapDialog.alert({
                     			 title: 'Message',
                     			    message: 'There are no Shipping Address for the selected Customer', 
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
                		}else{
                            $.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
                                from: 'top',
                                align: 'right'
                            }});
                		}//end of else
                    });
            }

            $scope.getShip = function (shipToId) {
                $scope.selectedShipToId = shipToId;
            }
            
            $scope.getAllId = function () {
                console.log('userorgid while getting save',$scope.selectedUserId);
                var input = {};
                input.salesAreaId = $scope.selectedSalesAreaId;
                input.customerId = $scope.selectedCustomerId;
                input.billToAddressId = $scope.selectedBillToAddressId;
                input.shipToAddressId = $scope.selectedShipToId;
                input.userId = $scope.selectedUserId;
                input.organizationId = $scope.selectedOrganizationId;
                input.adminId = $scope.userOrgDetails.adminId;
                var SalesAreaId = input.selectedSalesAreaId;
                var CustId = input.selectedCustomerId;
                var BillToId = input.selectedBillToAddressId;
                var ShipToId = input.selectedShipToId;
                var UserId = input.selectedUserId;
                var OrganiztionId = input.selectedOrganizationId;
                console.log('userorgid while getting input object',input.userId);
                console.log(input);
                if(($scope.selectedSalesAreaId || $scope.selectedCustomerId) && $scope.selectedBillToAddressId && $scope.selectedShipToId){
                	var url1 = 'addassgn/getuserorgid/';
                    genericService.addObject(url1, input).then(function () {
                            if ((input.salesAreaId != 0
                                && input.customerId != 0
                                && input.billToAddressId != 0 && input.shipToAddressId != 0)
                                || (input.customerId != 0
                                    && input.billToAddressId != 0 && input.shipToAddressId != 0)) {

                                var url2 = 'addassgn/getbillshipmap/';
                                genericService.addObject(url2,input).then(function (data) {
                                        console.log(data);
                                        $scope.mapList = data;
                                        var url3 = 'addassgn/getsalesareamap/';
                                        genericService.addObject(url3,input).then(function (data) {
                                        	$.notify({icon: 'glyphicon glyphicon-ok',message: 'Adding Default Address to division is successfully' },{ type: 'success', placement: {
                     							from: 'top',
                     							align: 'right'
                     						}});
                                                $scope.salesmapList = data;
                                            },
                                            function (data) {
                     							$.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
                     								from: 'top',
                     								align: 'right'
                     							}});
                                            });
                                    },
                                    function (data) {
             							$.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
             								from: 'top',
             								align: 'right'
             							}});
                                    });
                            }
                        },
                        function (data) {
                        	if(data.statusCode==204){
                    			BootstrapDialog.alert({
                         			 title: 'Message',
                         			    message: 'There are no user org for the user and organiztion', 
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
                    		}else{
                                $.notify({icon: 'glyphicon glyphicon-warning-sign',message: data.message },{ type: 'success', placement: {
                                    from: 'top',
                                    align: 'right'
                                }});
                    		}//end of else
                        });
                }else{
                	BootstrapDialog.alert({
            			 title: 'Message',
            			    message: 'Please select valid data', 
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
          
            }
            
            
            $scope.cancel = function () {
            	console.log('cancel method....');
                if (confirm('Are you sure!! you want to cancel?')) {

                    $state.go('usermgmt.divisionassignment');
                }
            }
            $scope.backToUserList = function () {
                $state.go('usermgmt.user.viewuser');
            }
            $scope.backToDivision = function () {
                $state.go('usermgmt.divisionassignment');
            }
        }]);
addressassignment.directive('convertToNumber', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            ngModel.$parsers.push(function (val) {
                return parseInt(val, 10);
            });
            ngModel.$formatters.push(function (val) {
                return '' + val;
            });
        }
    };
});