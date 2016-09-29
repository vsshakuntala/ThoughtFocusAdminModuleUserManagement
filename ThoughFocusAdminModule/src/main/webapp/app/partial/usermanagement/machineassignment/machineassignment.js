var machineassignment = angular.module(
	'tfadmin.usermanagementmodule.machineassignment', []);

machineassignment
	.config([
		'$stateProvider',
		'$urlRouterProvider',
		function ($stateProvider, $urlRouterProvider) {
			$stateProvider
				.state(
				'usermgmt.machineassignment',
				{
					url: '/machineassignment',
					templateUrl: 'app/partial/usermanagement/machineassignment/machineassignment.html',
					controller: 'MachineAssignmentController'
				});
		}]);

machineassignment
	.controller(
				'MachineAssignmentController',
				[
		'$scope', '$rootScope',
		'$state',
		'DTOptionsBuilder', 'localStorageService', 'DTColumnBuilder', 'machineAssignmentService', '$compile', 'genericService', '$http',
		function ($scope, $rootScope, $state, DTOptionsBuilder, localStorageService, DTColumnBuilder, machineAssignmentService, $compile, genericService, $http) {
			//Getting hardcoded userId and OrgId from divisionassignment.js	

			$scope.CatalogAvailableCount = 0;
			$rootScope.totalrows = 0;
			$rootScope.assignedMachineCount = 0;
			$scope.assignedDivisionsCount = 0;

			var userOrgDetails = localStorageService.get('userOrgDetails');
			$scope.userName = userOrgDetails['userName'];
			$scope.user_id = userOrgDetails['userId'];
			$scope.org_id = userOrgDetails['orgId'];
			$scope.orgName = userOrgDetails['orgName'];
			$scope.logedIn_user_id = userOrgDetails['adminId'];
			$scope.assignedDivisionsCount = userOrgDetails['assignedDivisionsCount'];;
			console.log('userOrgDetails in machine :' + angular.toJson(userOrgDetails));
			$scope.machine = {

			};

			console.log('Status value: ' + localStorageService.get('status'));
			if (localStorageService.get('status') == null) {
				console.log('Inside local check');
				$scope.machine.status = 'assigned';
				$scope.status = 'assigned';
				localStorageService.set('status', 'assigned');
			};


			$scope.rowCallback = function (nRow, aData, iDisplayIndex, iDisplayIndexFull) {

				$rootScope.totalrows = this.fnSettings().fnRecordsTotal();
				if ($scope.status === 'assigned') {
					$rootScope.assignedMachineCount = this.fnSettings().fnRecordsTotal();
				}

				console.log('Total rows of table :' + $rootScope.totalrows);
				console.log('Total assigned of table :' + $rootScope.assignedMachineCount);
			}

			//$scope.status = $scope.machine.status;

			//var  user_id =106; //userOrgDetails['userId'];
			//var org_id=1;//userOrgDetails['orgId'];
			//var login_id=2;//userOrgDetails['adminId'];
			$scope.FilterData = {
				selecteduser: "",
				selectedorganization: "",
				selectedserial: "",
				selectedmodel: "",
				selectedcatalog_reference: "",
				selectedcustomer_name: "",
				selectedgroup: "",
				/*selectedstatus : ""*/
			};
			$scope.alldata = {
				alldivision: ""
			}
			$scope.data = {
				selecteddivisions: ""
			};

			$scope.selecteddivision = [];

			getAvailableCatalogCount();

			function getAvailableCatalogCount() {
				genericService.getObjects('getCatalogCount/' + $scope.org_id).then(function (data) {
					console.log('In count api :' + angular.toJson(data));
					if (data != null) {
						console.log('CatalogAvailableCount :' + data['catalogCount']);
						$scope.CatalogAvailableCount = data['catalogCount'];
					}
				}, function (data) {
					console.log('In count api fail:' + data);
				});
			}

			$scope.toggle = function () {
				$scope.isVisible = !$scope.isVisible;
			};
			// Default the blocks to be visible.
			$scope.isVisible = true;


			vm = this;
			vm.selected = {};
			$rootScope.selList = [];

			showmachine();


			// filter work
			$scope.doFilter = function (machine) {
				console.log("before machine :" + angular.toJson(machine));
				console.log("machine.status :" + machine.status);
				resetListAfterapiCall();
				$scope.status = machine.status;
				// $rootScope.selList=[];
				console.log("Filtering :" + $rootScope.selList);
				showmachine(machine.catalog_id, machine.model, machine.catalog_reference, machine.customer_name, machine.group, machine.status);
				$scope.searhfilter = angular.copy(machine);
				//alert(angular.toJson($scope.searhfilter));
			};

			$scope.checkStatusAssign = function () {
				console.log('checkStatusAssign $scope.status :' + $scope.status);//+" "+table.data().count());
				if ($scope.status === "assigned") { // your question said "more than one element"
					return true;
				}
				else {
					return false;
				}
			};

			$scope.checkStatusRemove = function () {
				if ($scope.status === "notassigned") { // your question said "more than one element"
					return true;
				}
				else {
					return false;
				}
			};

			$scope.downloadresult = function () {

				BootstrapDialog.confirm({
					title: 'Download',
								            message: 'Are you sure you want to download?', callback: function (result) {
						if (result) {
							if (!angular.isUndefined($scope.searhfilter) && !angular.isUndefined($scope.searhfilter.status)) {

								var donwloadUrl = 'downloadmachinedocument/' + $scope.user_id + '/' + org_id + '/' + login_id;
								$http({
									method: 'POST',
									url: donwloadUrl,
									data: $scope.searhfilter,
									headers: {
										'Content-Type': 'application/json',
										'accept': 'application/vnd.ms-excel'
									},
									responseType: 'arraybuffer',
									cache: false,
								}).success(function (data, status, headers, config) {
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
								});
							} else {
								alert("Please select the Assigned type status and filter the result");
							}


								                } else {
								                    alert('Fail');
								                }
								            }
				});
			};

			function showmachine(catalog_id, model, catalog_reference, customer_name, group, status) {
				//$scope.userOrgDetails = localStorageService.get('userOrgDetails');
				console.log("function show machine")
				if (angular.isUndefined(status)) {
					$scope.url = 'machineassignmentreport';
				} else if (!angular.isUndefined(status) && angular.isUndefined(status) && angular.equals(status, 'assigned')) {
					$scope.url = 'machineassignmentreport';
				} else if (!angular.isUndefined(status) && !angular.isUndefined(status) && angular.equals(status, 'notassigned')) {
					$scope.url = 'machineunassignmentreport';
				} else {
					$scope.url = 'machineassignmentreport';
				}
				resetListAfterapiCall();
				console.log('Formed url in showtable:' + $scope.url + ' Userid :' + $scope.user_id + ' OrgId :' + $scope.org_id);

				console.log('Formed url in showtable:' + $scope.url + ' Userid :' + $scope.user_id + ' OrgId :' + $scope.org_id);
				//alert(user_id+" "+org_id);
				/*var  user_id = 1854;
				var org_id=4;*/

				/*alert(user_id+" "+org_id);*/
				//$scope.FilterData.selecteduser = user_id;
				//	$scope.FilterData.selectedorganization = organization_id;
				$scope.FilterData.selectedserial = catalog_id;
				$scope.FilterData.selectedmodel = model;
				$scope.FilterData.selectedcatalog_reference = catalog_reference;
				$scope.FilterData.selectedcustomer_name = customer_name;
				$scope.FilterData.selectedgroup = group;
				$scope.FilterData.selectedstatus = status;
				//download
				//								document.getElementById('idShowFilter').innerHTML="<b>SERIAL ID:<b>"+serial+"<br/><b>MODEL:<b>"


				vm.dtOptions = DTOptionsBuilder.newOptions()
					.withOption('createdRow', function (row, data, dataIndex) {
						console.log(angular.toJson(row) + " " + angular.toJson(data) + " " + angular.toJson(dataIndex))
						$compile(angular.element(row).contents())($scope);
						// Recompiling so we can bind Angular directive to the DT
						//  $compile(angular.element(row).contents())($scope);
					})
					.withOption('ajax', {
						url: $scope.url,
						type: 'GET',
						data: {
							'user_id': $scope.user_id,
							'organization_id': $scope.org_id,
							'catalog_id': catalog_id,
							'model': model,
							'catalog_reference': catalog_reference,
							'customer_name': customer_name,
							'status': status,
							'group_name': group
						}
					})
					.withDataProp('data')
					.withOption('processing', true)
					.withOption('serverSide', true)
					.withOption("bDestory", true)
					.withPaginationType('full_numbers')
					.withOption('bFilter', true)
					.withOption('rowCallback', $scope.rowCallback)

				// .withOption('createdRow', createdRow)

				/* function createdRow(row, data, dataIndex) {
					 // Recompiling so we can bind Angular directive to the DT
					 $compile(angular.element(row).contents())($scope);
				 }*/

				vm.dtColumns = [
						            	 DTColumnBuilder.newColumn(null).withTitle('Select').withOption('name', 'group_name').withClass('idSelect').renderWith(

						function (data, type, full) {
							// console.log('Full value: '+showCase.selected[full.catalog_id]);
							// console.log('Data value: ' +angular.toJson(data)+ 'Type value: '+angular.toJson(type));
							if (full.group_name != null)

								return '<input type="checkbox" disabled>';
							else {

								return '<input type="checkbox"  ng-model="showCase.selected[' + full.catalog_id + ']" ng-click="clikedsel(' + full.catalog_id + ')" >';
							}
							//return '<input type="checkbox" id="check" ng-model="HasPassport">';

						}),

					DTColumnBuilder.newColumn('catalog_id').withTitle('Serial #').withOption('name', 'catalog_id'),
					DTColumnBuilder.newColumn('model').withTitle('Model').withOption('name', 'model'),
					DTColumnBuilder.newColumn('catalog_reference').withTitle('Machine Reference').withOption('name', 'catalog_reference'),
					DTColumnBuilder.newColumn('customer_name').withTitle('Customer Name').withOption('name', 'customer_name'),
					DTColumnBuilder.newColumn('status').withTitle('Status').withOption('name', 'status'),
					DTColumnBuilder.newColumn('group_name').withTitle('Group Name').withOption('name', 'group_name')
				];

			};

			$scope.clikedsel = function (catalog_id) {
				//console.log("get in machine"+angular.toJson(address));

				console.log('clicked selection :' + angular.toJson(vm.selected));
				var flag = 0;
				for (var int = 0; int < $rootScope.selList.length; int++) {
					if ($rootScope.selList[int] == catalog_id) {
						flag++;
					}
				}
				if (flag == 0)
					$rootScope.selList.push(catalog_id);
				else {
					var index = $rootScope.selList.indexOf(catalog_id);
					$rootScope.selList.splice(index, 1);
				}
				console.log(angular.toJson($rootScope.selList));


			}

			//all assign work
			$scope.assignall = function () {
				//alert("inside assign33");
				//alert(angular.toJson($scope.searhfilter));
				if ($scope.searhfilter) {
					var jsonData = JSON.stringify({
						params: $scope.searhfilter

					});
					console.log(jsonData);
					//alert("inside assign33");
					BootstrapDialog.confirm({
						title: 'Assign All',
						message: 'Are you sure you want to assign all machine from user?', callback: function (result) {
							if (result) {
								//alert("inside assign");
								genericService.addObject('assignallmachine/' + $scope.user_id + '/' + $scope.org_id + '/' + $scope.logedIn_user_id, jsonData).then(function (data) {
									//alert("inside assign11");
									console.log('In assign generic service :' + angular.toJson(data));
									BootstrapDialog.alert({
										title: 'Message',
										message: 'All Machines are Assigned Succesfully',
										buttons: [{
											id: 'btn-ok',
											icon: 'glyphicon glyphicon-check',
											label: 'Close',
											cssClass: 'btn-primary',
											autospin: false,
											action: function (dialogRef) {
												dialogRef.close();
												resetListAfterapiCall();
											}
										}]
									});

									reloadData();
								}, function (data) {
									//alert("inside assign22");
									if (data.statusCode == 400) {
										BootstrapDialog.alert({
											title: 'Message',
											message: 'Assigning Machines Failed due to some error',
											buttons: [{
												id: 'btn-ok',
												icon: 'glyphicon glyphicon-check',
												label: 'Close',
												cssClass: 'btn-primary',
												autospin: false,
												action: function (dialogRef) {
													dialogRef.close();
												}
											}]
										});
									}

									else {
										$.notify({ icon: 'glyphicon glyphicon-warning-sign', message: data.message }, {
											type: 'success', placement: {
												from: 'top',
												align: 'right'
											}
										});
									}//end of else
								});
							} else {
								BootstrapDialog.alert({
									title: 'Message',
									message: 'Assigning Machines Failed',
									buttons: [{
										id: 'btn-ok',
										icon: 'glyphicon glyphicon-check',
										label: 'Close',
										cssClass: 'btn-primary',
										autospin: false,
										action: function (dialogRef) {
											dialogRef.close();
											resetListAfterapiCall();
										}
									}]
								});
							}
						}
					});
				} else {
					var jsonData = JSON.stringify({
						params: null

					});
					BootstrapDialog.confirm({
						title: 'Assign All',
						message: 'Are you sure you want to assign all machine from user?', callback: function (result) {
							if (result) {

								genericService.addObject('assignallmachine/' + $scope.user_id + '/' + $scope.org_id + '/' + $scope.logedIn_user_id, jsonData).then(function (response) {
									console.log('In assign generic service :' + angular.toJson(response));
									BootstrapDialog.alert({
										title: 'Message',
										message: 'All Machines are Assigned Succesfully',
										buttons: [{
											id: 'btn-ok',
											icon: 'glyphicon glyphicon-check',
											label: 'Close',
											cssClass: 'btn-primary',
											autospin: false,
											action: function (dialogRef) {
												dialogRef.close();
												resetListAfterapiCall();
											}
										}]
									});

									reloadData();
								}, function (response) {
									//alert(angular.toJson(response));
									BootstrapDialog.alert({
										title: 'Message',
										message: 'Assigning all Machines Failed due to internal error',
										buttons: [{
											id: 'btn-ok',
											icon: 'glyphicon glyphicon-check',
											label: 'Close',
											cssClass: 'btn-primary',
											autospin: false,
											action: function (dialogRef) {
												dialogRef.close();
											}
										}]
									});
								});
							} else {
								BootstrapDialog.alert({
									title: 'Message',
									message: 'Assigning Machines Failed',
									buttons: [{
										id: 'btn-ok',
										icon: 'glyphicon glyphicon-check',
										label: 'Close',
										cssClass: 'btn-primary',
										autospin: false,
										action: function (dialogRef) {
											dialogRef.close();
											resetListAfterapiCall();
										}
									}]
								});
							}
						}
					});
				}





			};
			$scope.backToDivision = function () {
				$state.go('usermgmt.divisionassignment');
			}
			//all remove work

			$scope.removeall = function () {
				//alert(angular.toJson($scope.searhfilter));
				if ($scope.searhfilter) {
					var jsonData = JSON.stringify({
						params: $scope.searhfilter

					});
					console.log(jsonData);
					BootstrapDialog.confirm({
						title: 'Remove All',
						message: 'Are you sure you want to remove all machine from user?', callback: function (result) {
							if (result) {

								genericService.addObject('removeallmachine/' + $scope.user_id + '/' + $scope.org_id + '/' + $scope.logedIn_user_id, jsonData).then(function (response) {
									console.log('In remove generic service :' + angular.toJson(response));
									BootstrapDialog.alert({
										title: 'Message',
										message: 'All Machines are Removed Succesfully',
										buttons: [{
											id: 'btn-ok',
											icon: 'glyphicon glyphicon-check',
											label: 'Close',
											cssClass: 'btn-primary',
											autospin: false,
											action: function (dialogRef) {
												dialogRef.close();
												resetListAfterapiCall();
											}
										}]
									});

									reloadData();
								}, function (response) {
									//alert(angular.toJson(response));
									BootstrapDialog.alert({
										title: 'Message',
										message: 'Removing all Machines Failed due to internal error',
										buttons: [{
											id: 'btn-ok',
											icon: 'glyphicon glyphicon-check',
											label: 'Close',
											cssClass: 'btn-primary',
											autospin: false,
											action: function (dialogRef) {
												dialogRef.close();
											}
										}]
									});
								});
							} else {
								BootstrapDialog.alert({
									title: 'Message',
									message: 'Removing Machines Failed',
									buttons: [{
										id: 'btn-ok',
										icon: 'glyphicon glyphicon-check',
										label: 'Close',
										cssClass: 'btn-primary',
										autospin: false,
										action: function (dialogRef) {
											dialogRef.close();
											resetListAfterapiCall();
										}
									}]
								});
							}
						}
					});
				} else {
					var jsonData = JSON.stringify({
						params: null

					});
					BootstrapDialog.confirm({
						title: 'Remove All',
						message: 'Are you sure you want to remove all machine from user?', callback: function (result) {
							if (result) {

								genericService.addObject('removeallmachine/' + $scope.user_id + '/' + $scope.org_id + '/' + $scope.logedIn_user_id, jsonData).then(function (response) {
									console.log('In remove generic service :' + angular.toJson(response));
									BootstrapDialog.alert({
										title: 'Message',
										message: 'All Machines are Removed Succesfully',
										buttons: [{
											id: 'btn-ok',
											icon: 'glyphicon glyphicon-check',
											label: 'Close',
											cssClass: 'btn-primary',
											autospin: false,
											action: function (dialogRef) {
												dialogRef.close();
												resetListAfterapiCall();
											}
										}]
									});

									reloadData();
								}, function (response) {
									//alert(angular.toJson(response));
									BootstrapDialog.alert({
										title: 'Message',
										message: 'Removing all Machines Failed due to internal error',
										buttons: [{
											id: 'btn-ok',
											icon: 'glyphicon glyphicon-check',
											label: 'Close',
											cssClass: 'btn-primary',
											autospin: false,
											action: function (dialogRef) {
												dialogRef.close();
											}
										}]
									});
								});
							} else {
								BootstrapDialog.alert({
									title: 'Message',
									message: 'Removing Machines Failed',
									buttons: [{
										id: 'btn-ok',
										icon: 'glyphicon glyphicon-check',
										label: 'Close',
										cssClass: 'btn-primary',
										autospin: false,
										action: function (dialogRef) {
											dialogRef.close();
											resetListAfterapiCall();
										}
									}]
								});
							}
						}
					});
				}




			};
			$scope.backToUserList = function () {
				$state.go('usermgmt.user.viewuser');

			}

			$scope.navigateToEditUser = function () {
				// To add to local storage  {"userId":110,"adminId":"2","userName":"dan_lillback@milacron.com"}
				$scope.userDetailsObj = {
					"userId": $scope.userOrgDetails.userId,
					"adminId": $scope.userOrgDetails.adminId,
					"userName": $scope.userOrgDetails.userName
				};
				localStorageService.set('userOrgDetails', $scope.userDetailsObj);
				$state.go('usermgmt.user.updateuser');
			};
			// assign work
			/*	$scope.assign = function() {
					
					BootstrapDialog.confirm({ title: 'Assign',
						message: 'Are you sure you want to assign selected machine to user?', callback: function(result){
							if(result) {
								alert('Assigned');
							}else {
								alert('Fail');
							}
						}});	

					
				};*/

			function resetListAfterapiCall() {
				console.log('making selected false :' + angular.toJson(vm.selected));
				//							for(var selectdObj in vm.selected){
				//								console.log('Obj is :'+vm.selected[selectdObj]);
				//								vm.selected[selectdObj]=false;
				//								
				//							}
				//							console.log('making selected false after:'+angular.toJson(vm.selected));
				$rootScope.selList = [];
				//vm.selected={};
			}

			$scope.assign = function () {


				if (!angular.isUndefined($rootScope.selList) && $rootScope.selList.length > 0) {
					BootstrapDialog.confirm({
						title: 'Assign',
						message: 'Are you sure you want to assign selected machine to user?', callback: function (result) {
							if (result) {
								var jsonData = JSON.stringify({
									params: $rootScope.selList
								});
								console.log("In assign :" + jsonData);
								genericService.addObject('assignmachine/' + $scope.user_id + '/' + $scope.logedIn_user_id, jsonData).then(function (response) {
									console.log('In assign generic service :' + angular.toJson(response));
									//reloadData();
									BootstrapDialog.alert({
										title: 'Message',
										message: 'Selected Machines are Assigned Succesfully',
										buttons: [{
											id: 'btn-ok',
											icon: 'glyphicon glyphicon-check',
											label: 'Close',
											cssClass: 'btn-primary',
											autospin: false,
											action: function (dialogRef) {
												dialogRef.close();

												//  resetListAfterapiCall();

											}
										}]
									});

									reloadData();
									resetListAfterapiCall();
								}, function (response) {
									//alert(angular.toJson(response));
									BootstrapDialog.alert({
										title: 'Message',
										message: 'Assigning Machines Failed due to internal error',
										buttons: [{
											id: 'btn-ok',
											icon: 'glyphicon glyphicon-check',
											label: 'Close',
											cssClass: 'btn-primary',
											autospin: false,
											action: function (dialogRef) {
												dialogRef.close();
											}
										}]
									});
								});

							} else {
								BootstrapDialog.alert({
									title: 'Message',
									message: 'Assigning Machines Failed',
									buttons: [{
										id: 'btn-ok',
										icon: 'glyphicon glyphicon-check',
										label: 'Close',
										cssClass: 'btn-primary',
										autospin: false,
										action: function (dialogRef) {
											dialogRef.close();
											resetListAfterapiCall();
										}
									}]
								});
							}
						}
					});
				} else {
					BootstrapDialog.alert({
						title: 'Message',
						message: 'Please select atleast one row',
						buttons: [{
							id: 'btn-ok',
							icon: 'glyphicon glyphicon-check',
							label: 'Close',
							cssClass: 'btn-primary',
							autospin: false,
							action: function (dialogRef) {
								dialogRef.close();
								resetListAfterapiCall();
							}
						}]
					});
				}

			}

			window.onbeforeunload = function () {
				return "Data will be lost if you leave the page, are you sure?";
			};
			//remove work
			/*$scope.remove = function() {
				
				BootstrapDialog.confirm({ title: 'Remove',
					message: 'Are you sure you want to remove selected machine from user?', callback: function(result){
						if(result) {
							alert('Removed');
						}else {
							alert('Fail');
						}
					}});		
			};*/

			vm.reloadData = reloadData;
			vm.dtInstance = {};
			$scope.remove = function () {
				if (!angular.isUndefined($rootScope.selList) && $rootScope.selList.length > 0) {
					BootstrapDialog.confirm({
						title: 'Remove',
						message: 'Are you sure you want to remove selected machine from user?', callback: function (result) {
							if (result) {
								//alert("inside")
								var jsonData = JSON.stringify({
									params: $rootScope.selList

								});
								console.log(jsonData);
								genericService.addObject('removemachine/' + $scope.user_id + '/' + $scope.logedIn_user_id, jsonData).then(function (response) {
									console.log('In remove generic service :' + angular.toJson(response));
									//reloadData();
									BootstrapDialog.alert({
										title: 'Message',
										message: 'Selected Machines are Removed Succesfully',
										buttons: [{
											id: 'btn-ok',
											icon: 'glyphicon glyphicon-check',
											label: 'Close',
											cssClass: 'btn-primary',
											autospin: false,
											action: function (dialogRef) {
												dialogRef.close();

											}
										}]
									});
									reloadData();
									resetListAfterapiCall();
								}, function (response) {
									//alert(angular.toJson(response));
									BootstrapDialog.alert({
										title: 'Message',
										message: 'Removing Machines Failed Failed due to internal error',
										buttons: [{
											id: 'btn-ok',
											icon: 'glyphicon glyphicon-check',
											label: 'Close',
											cssClass: 'btn-primary',
											autospin: false,
											action: function (dialogRef) {
												dialogRef.close();
											}
										}]
									});
								});

							} else {
								BootstrapDialog.alert({
									title: 'Message',
									message: 'Removing Machines Failed',
									buttons: [{
										id: 'btn-ok',
										icon: 'glyphicon glyphicon-check',
										label: 'Close',
										cssClass: 'btn-primary',
										autospin: false,
										action: function (dialogRef) {
											dialogRef.close();
											resetListAfterapiCall();
										}
									}]
								});
							}
						}
					});
				} else {
					BootstrapDialog.alert({
						title: 'Message',
						message: 'Please select atleast one row',
						buttons: [{
							id: 'btn-ok',
							icon: 'glyphicon glyphicon-check',
							label: 'Close',
							cssClass: 'btn-primary',
							autospin: false,
							action: function (dialogRef) {
								dialogRef.close();
								resetListAfterapiCall();
							}
						}]
					});
				}
			}



			function reloadData() {
				console.log('reloadData....');
				//$rootScope.selList = [];
				//vm.selected=false;
				resetListAfterapiCall();
				var resetPaging = false;
				vm.dtInstance.reloadData(callback, resetPaging);
			}

			function callback(json) {
				console.log(json);
			}
			/* start of configuring dataTable */
			$scope.dtOptions = DTOptionsBuilder
				.newOptions()
				.withPaginationType('full_numbers')
				.withOption(
				'initComplete',
				function () {
					$(
						"#dataTable_length > label > select")
						.css('width', '100px');
				});
			/* end of configuring dataTable */

		}]);
machineassignment.factory('machineAssignmentService', [
	'$q',
	'$http',
	'$state',
	function ($q, $http, $state) {

		var factory = {};
		factory.assignedallmachine = function (machine) {
			var deferred = $q.defer();
			$http({
				method: 'POST',
				url: 'assignallmachine',
				data: machine,
				headers: {
					'Content-Type': 'application/json'
				}
			}).success(function (data) {
				console.log("Response" + JSON.stringify(data));
				deferred.resolve(data);
			}).error(function (data) {
				console.log("Not a valid machine " + data);
				deferred.reject(data);
			});
			return deferred.promise;
		};
		factory.removeallmachine = function (machine) {
			var deferred = $q.defer();
			$http({
				method: 'POST',
				url: 'removeallmachine',
				data: machine,
				headers: {
					'Content-Type': 'application/json'
				}
			}).success(function (data) {
				console.log("Response" + JSON.stringify(data));
				deferred.resolve(data);
			}).error(function (data) {
				console.log("Not a valid machine " + data);
				deferred.reject(data);
			});
			return deferred.promise;
		};
		factory.removemachine = function (machine) {
			var deferred = $q.defer();
			$http({
				method: 'POST',
				url: 'removemachine',
				data: machine,
				headers: {
					'Content-Type': 'application/json'
				}
			}).success(function (data) {
				console.log("Response" + JSON.stringify(data));
				deferred.resolve(data);
			}).error(function (data) {
				console.log("Not a valid machine " + data);
				deferred.reject(data);
			});
			return deferred.promise;
		};
		factory.assignmachine = function (machine) {
			var deferred = $q.defer();
			$http({
				method: 'POST',
				url: 'assignmachine',
				data: machine,
				headers: {
					'Content-Type': 'application/json'
				}
			}).success(function (data) {
				console.log("Response" + JSON.stringify(data));
				deferred.resolve(data);
			}).error(function (data) {
				console.log("Not a valid machine " + data);
				deferred.reject(data);
			});
			return deferred.promise;
		};
		return factory;
	}]); 
