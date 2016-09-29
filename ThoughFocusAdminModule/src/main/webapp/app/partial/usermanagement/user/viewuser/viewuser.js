/**
 * 
 */
var viewuser = angular.module('tfadmin.usermanagementmodule.user.viewuser',
		[ 'datatables','ngAnimate', 'ngSanitize', 'ui.bootstrap' ]);
viewuser
		.config([
				'$stateProvider',
				'$urlRouterProvider',
				function($stateProvider, $urlRouterProvider) {
					$stateProvider
							.state(
									'usermgmt.user.viewuser',
									{
										url : '/viewuser',
										templateUrl : 'app/partial/usermanagement/user/viewuser/viewuser.html',
										controller : 'ViewUserController'
									});
				} ]);
viewuser
		.controller(
				'ViewUserController',
				[
						'$scope',
						'$state',
						'$http',
						'$timeout',
						'DTOptionsBuilder','DTColumnBuilder',
						'SharedService',
						'$compile',
						'$filter','localStorageService',
						function($scope, $state, $http, $timeout, DTOptionsBuilder, DTColumnBuilder,SharedService,$compile,$filter,localStorageService) {
							
							
							 $scope.today = function() {
								    $scope.dt = new Date();
								  };
								  $scope.today();
								  $scope.format = 'dd-MMM-yyyy';
								  $scope.clear = function() {
								    $scope.dt = null;
								  };

								  $scope.inlineOptions = {
								    customClass: getDayClass,
								    minDate: new Date(),
								    showWeeks: true
								  };

								  $scope.dateOptions = {
								    dateDisabled: disabled,
								    formatYear: 'yy',
								    maxDate: new Date(2020, 5, 22),
								    minDate: new Date(),
								    startingDay: 1
								  };

								  // Disable weekend selection
								  function disabled(data) {
								    var date = data.date,
								      mode = data.mode;
								    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
								  }

								  $scope.toggleMin = function() {
								    $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
								    $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
								  };

								  $scope.toggleMin();

								  $scope.open1 = function() {
								    $scope.popup1.opened = true;
								  };

								  $scope.open2 = function() {
								    $scope.popup2.opened = true;
								  };

								  $scope.setDate = function(year, month, day) {
								    $scope.dt = new Date(year, month, day);
								  };
								  $scope.popup1 = {
								    opened: false
								  };

								  $scope.popup2 = {
								    opened: false
								  };

								 
								  function getDayClass(data) {
								    var date = data.date,
								      mode = data.mode;
								    if (mode === 'day') {
								      var dayToCheck = new Date(date).setHours(0,0,0,0);

								      for (var i = 0; i < $scope.events.length; i++) {
								        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

								        if (dayToCheck === currentDay) {
								          return $scope.events[i].status;
								        }
								      }
								    }

								    return '';
								  }
							
							$scope.selectedDivisions = [];
							$scope.selectedRole = [];
							$scope.selectstatus = [];
							$scope.searhfilter = {divisions:'',
									roles:'',
									status:'',
									from_date:'',
									company:'',
									to_date:'',
									name:''};
							
							
							/**
							 * setting the status options for filter
							 */
							$scope.status = [ {
								id : 'Pending',
								name : 'Pending',
								status : true
							}, {
								id : 'Approved',
								name : 'Approved'
							}, {
								id : 'Deleted',
								name : 'Deleted'
							} ];
							if ($scope.status[0].status == true) {
								$scope.selectstatus.push($scope.status[0].id);
							}
							/**
							 * to get all divisions names from db
							 */
							$scope.getAllDivisions = function() {
								$http
										.get('userlists/getalldivisions')
										.success(
												function(data) {
													$scope.divisions = data;
													console
															.log('Divisions: '
																	+ angular
																			.toJson($scope.divisions));
													for (var int = 0; int < data.length; int++) {
														$scope.selectedDivisions
																.push(data[int].organizationId);
													}

												});
							}

							/**
							 * to get all roles names from db
							 */
							$scope.getRoles = function() {
								$http.get('userlists/getallroles')
								.success(
										function(data) {
											console.log(data)
											$scope.roleList = data;
										});
							}
							
							
							$scope.getAllDivisions();
							$scope.getRoles();
							
							/**
							 * to get selected divisions on click
							 */
							$scope.getdiv = function(division) {
								var index = $scope.selectedDivisions
										.indexOf(division.organizationId);
								if (index === -1) {
									$scope.selectedDivisions
											.push(division.organizationId);
								} else
									$scope.selectedDivisions.splice(index, 1);
							};
							
							
							/**
							 * to get selected roles on click
							 */
							$scope.getroles = function(roles) {
								var roleid = roles.roleId;
								var flag = 0;
								for (var int = 0; int < $scope.selectedRole.length; int++) {
									if ($scope.selectedRole[int]===roleid) {
										flag++;
									}
								}
								if (flag === 0){
									$scope.selectedRole.push(roles.roleId);
								}
								else {
									var index = $scope.selectedRole
											.indexOf(roles.roleId);
									$scope.selectedRole.splice(index, 1);
								}

							};
							
							/**
							 * to get selected status on click
							 */
							$scope.getstatus = function(status) {
								if (status.status == true) {
									$scope.selectstatus.push(status.id);
									
								} else {
									var index = $scope.selectstatus
											.indexOf(status.id);
									$scope.selectstatus.splice(index, 1);
								}
							};
							
							
							/**
							 * to download the report on click of download icon
							 */
							$scope.reportdata = function(from_date,
									company, to_date, name) {
								BootstrapDialog.confirm({ title: 'Download',
						            message: 'Are you sure you want to download?', callback: function(result){
						            	if(result) {
						            		$scope.searhfilter.divisions=$scope.selectedDivisions.toString();
						            		$scope.searhfilter.roles=$scope.selectedRole.toString();
						            		$scope.searhfilter.status=$scope.selectstatus.toString();
						            		$scope.searhfilter.from_date=from_date;
						            		$scope.searhfilter.company=company;
						            		$scope.searhfilter.to_date=to_date;
						            		$scope.searhfilter.name=name;
						            		
						            		var donwloadUrl='userlists/downloaddocument/';
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
					           		        }});	
						            		
						                }
						            }});
							};
							
							
							
							$scope.toggle = function() {
								console.log('in toggle view user');
								$scope.isVisible = !$scope.isVisible;
								console.log('isVisible :' + $scope.isVisible);
							};
					
							$scope.isVisible = true;
							
							/**
							 * to check the user is deleted or not 
							 */
							$scope.checkDeletedStatus = function (user) {
								if (user.status === 'Deleted') {
									return true;
								} else {
									return false;
								}
							};
					/**
					 * To get flags based on contact number.
					 */
					$scope.getflagByCountryCode = function(code){
						if(code !== null){
							//console.log(code);
							code = code + '';
							var res = code.substring(0, 2);
							var obj = $filter('filter')(allCountries,{dialCode:res},true);
							return obj[0].iso2;
						}
					}

							$scope.create = function() {
								localStorageService.set('AdminId','2');
								$state.go('usermgmt.user.createuser');
							};

							$scope.checkIsDeleted = function(user) {
								if (user.isDeleted === true) {
									return true;
								} else {
									return false;
								}
							};

							$scope.checkApprovedStatus = function(user) {
								if (user.status === 'Approved') {
									return true;
								} else {
									return false;
								}
							};

							$scope.checkPendingStatus = function(user) {
								if (user.status === 'Pending') {
									return true;
								} else {
									return false;
								}
							};

							$scope.checkDeletedStatus = function(user) {
								if (user.status === 'Deleted') {
									return true;
								} else {
									return false;
								}
							};

							$scope.getdate = function(){
								($("#from").val() == "" ) && ($("#to").val() == "")  && ($("#name").val() == "") &&  ($("#company").val() == "") && 
								  ($("#role").val() == null) && ($("#status").val() == null)
								if(($("#div").val() == "")){
									alert("No Filters are selected.");
								}
								else if(($("#from").val() == "" ) &&($("#to").val() != "")) {
								  alert("From date is empty");
								}
								else if(($("#to").val() == "") && ($("#from").val() != "" )) {
								  alert("To date is empty");
								}
							};
							
							
							vm = this;
							showtable();
							$scope.doFilter = function(from_date,
									company, to_date, name) {
								var Divisions = $scope.selectedDivisions;
								var roles = $scope.selectedRole;
								var status = $scope.selectstatus;
								//alert(angular.toJson($scope.selectstatus));
								showtable(Divisions, roles, status, from_date,
										company, to_date, name);
							};
														$scope.totalrows=0;
							function showtable(Divisions, roles, status,
									from_date, company, to_date, name) {
							
								vm.dtOptions = DTOptionsBuilder
										.newOptions()
										.withOption(
												'ajax',
												{
													url : 'userlists/getfiltereduserlist',
													type : 'GET',

													data : {
														'divisions' : ""+$scope.selectedDivisions.toString()+"",
														'status' : ""+$scope.selectstatus.toString()+"",
														'roles' : ""+$scope.selectedRole.toString()+"",
														'from_date' : from_date,
														'companyName' : company,
														'to_date' : to_date,
														'name' : name

													}
												}).withDataProp('data')
										.withOption('processing', true)
										.withOption('serverSide', true)
										.withOption('bDestory', true)
										.withPaginationType('full_numbers')
										.withOption('bFilter', true)
										.withOption('order', [7, 'asc'])
								 		.withOption('rowCallback', $scope.rowCallback)
								 		.withOption('createdRow', function (row, data, dataIndex) {
								 	        // Recompiling so we can bind Angular directive to the DT
								 	        $compile(angular.element(row).contents());
								 	    });

						
								$scope.rowCallback = function(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
							    	
							    	$scope.totalrows=this.fnSettings().fnRecordsTotal(); 
							    	
							        // Unbind first in order to avoid any duplicate handler
							        $('td', nRow).unbind('click');
							        $('td', nRow).bind('click', function() {
							            $scope.$apply(function() {
							                //vm.someClickHandler(aData);
							            	var passingObject={
							            			adimId:'2',
							            			userId:aData.userId,
							            			createdDate:aData.createdDate,
							            			Last_modified_date:aData.last_date,
							            			userName:aData.userName
							            	}
							            	if(aData.Approved===0 || aData.Pending===0||aData.active==0){

							            		console.log('successfull delete click '+angular.toJson(aData));
								            	
								            	//SharedService.set('userAndAdminDetails',passingObject);
							            		localStorageService.set('userAndAdminDetails',passingObject);
								            	$state.go('usermgmt.user.updateuser');
							            	}
							            	else{
								            	console.log('successfull approved click '+angular.toJson(aData));
								            	
								            	//SharedService.set('userAndAdminDetails',passingObject);
								            	localStorageService.set('userAndAdminDetails',passingObject);
								            	$state.go('usermgmt.divisionassignment');
							            	}
							            });
							        });
							        return nRow;
							    }
							    $scope.deleteUser = function(user){
							    	console.log(angular.toJson(user));
							    }
								
								vm.dtColumns = [ 
								{
									'mData' : 'userId',
									'sTitle' : 'userId',
									'name' : 'userId',
									'visible':false
								},
								{
									'mData' : 'last_date',
									'sTitle' : 'last_date',
									'name' : 'last_date',
									'visible':false
								},
								   {
									'mData' : 'userName',
									'sTitle' : 'User Name',
									'name' : 'userName'
								}, {
									'mData' : 'firstName',
									'sTitle' : 'First Name',
									'name' : 'firstName',
									
								},
								{
									'mData' : 'lastName',
									'sTitle' : 'Last Name',
									'name' : 'lastName',
								},
								{
									'mData' : 'email',
									'sTitle' : 'Email',
									'name' : 'email',
								},{
									'mData' : 'active',
									'sTitle' : 'Active',
									'name' : 'active',
									'visible':false
								},
								{
									'mData' : 'createdDate',
									'sTitle' : 'Registered Date ',
									'name' : 'createdDate',
									'render': function (mData) {
								        var date = new Date(mData);
								        var month = date.getMonth() + 1;
								        var monthNames = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
								                          'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
								                        ];
								        return date.getDate()+' '+monthNames[date.getMonth()]+' '+ date.getFullYear();
								    }
								},
								{
									'mData' : 'phoneNumber',
									'sTitle' : 'Phone',
									'name' : 'phoneNumber',
									'render':function(data, type, full) {
										if(!full.phoneNumber)
											full.phoneNumber='--';
										return '<td><span class="iti-flag-custom" ng-class="getflagByCountryCode('+data+')"></span>'+full.phoneNumber+'</td>';
									}
								},
								{
									'mData' : 'companyName',
									'sTitle' : 'Company Name',
									'name' : 'companyName',
									'render':function(data, type, full) {
										if(!full.companyName)
											full.companyName='--';
											return full.companyName;
									}
								},
								{
									'mData' : null,
									'sTitle' : 'Status',
									'name' : 'STATUS',
									'sortable':false,
									'render':function(data, type, full){
										if(full.Approved==0 && full.Pending==0){
											//full.STATUS='DELETED'
												return '<td><span class="label label-danger">DELETED</span></td>';
										}
										if(full.active === false){
											//full.STATUS='DELETED'
											return '<td><span class="label label-danger">DELETED</span></td>';
										}
										else{
											if(full.Pending==0)
												return '<td><span class="label label-success">APPROVED</span></td>';
											else
													return '<td><span class="label label-warning">PENDING</span></td>';
											
										}
										
									}
								
								}
								,
								{
									'mData' : 'Approved',
									'sTitle' : 'Approved/Pending',
									'name' : 'Approved',
									'sortable':false,
									'render':function(data, type, full) {
										if(!full.Approved)
											full.Approved='0';
										if(!full.Pending)
											full.Pending='0';
							            return full.Approved + '/' + full.Pending;
									}
								}
								];
								
								vm.dtInstanceCallback = function(dtInstance) {
								    vm.dtInstance = dtInstance;
								    dtInstance.DataTable.on('draw.dt', function() {
								        let elements = angular.element("#" + dtInstance.id + " .ng-scope");
								        angular.forEach(elements, function(element) {
								            $compile(element)($scope);
								        })
								    });
								}
							}

						} ]);
