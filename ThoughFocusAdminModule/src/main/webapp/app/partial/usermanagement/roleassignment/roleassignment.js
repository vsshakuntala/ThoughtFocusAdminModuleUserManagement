/**
 * 
 */

var roleassignment = angular.module(
	'tfadmin.usermanagementmodule.rolesassignment', []);

roleassignment
	.config([
		'$stateProvider',
		'$urlRouterProvider',
		function ($stateProvider, $urlRouterProvider) {
			$stateProvider
				.state(
				'usermgmt.rolesassignment',
				{
					url: '/rolesassignment',
					templateUrl: 'app/partial/usermanagement/roleassignment/roleassignment.html',
					controller: 'RolesAssignmentController'
				});
		}]);

roleassignment
	.controller(
				'RolesAssignmentController',
				[
		'$scope',
		'$state',
		'$filter',
		'$http', 'roleAssignmentService', 'SharedService', 'genericService',
		function ($scope, $state, $filter, $http, roleAssignmentService, SharedService, genericService) {


			//$scope.selectedOrg = $state.params.orgObj;
			$scope.unassignedrole = [];
			$scope.selectedrole = [];
			var userOrgDetails = SharedService.get('userOrgDetails');
			console.log('userOrgDetails in role :' + angular.toJson(userOrgDetails));
			$scope.userIdSelected = userOrgDetails['userId'];
			$scope.selectedOrg = userOrgDetails['orgId'];
			$scope.selectedUser = userOrgDetails['userName'];
			$scope.orgName = userOrgDetails['orgName'];
			$scope.changeinleft = function (unassignedrole) {
				console.log(angular.toJson(unassignedrole));
				if (unassignedrole.length == 1) {
					$scope.getPermissions(unassignedrole[0].roleId);
				} else {
					$scope.permissionslist = [];
				}
			}

			$scope.toggleSelection = function () {
				if (angular.equals($scope.toggleSeleted, 'check') && angular.isDefined($scope.unassignedrolelist)) {
					$scope.unassignedrole = [];
					for (var i = 0; i < $scope.unassignedrolelist.length; i++) {
						$scope.unassignedrole.push($scope.unassignedrolelist[i]);
					}
				} else {
					$scope.unassignedrole = [];
				}
			}

			$scope.toggleSelectedSelection = function () {
				if (angular.equals($scope.toggleSeletedSelection, 'check') && angular.isDefined($scope.assignedrolelist)) {
					$scope.selectedrole = [];
					for (var i = 0; i < $scope.assignedrolelist.length; i++) {
						$scope.selectedrole.push($scope.assignedrolelist[i]);
					}
				} else {
					$scope.selectedrole = [];
				}
			}

			$scope.isRoleSelected = function (role) {
				var found = $filter('filter')($scope.unassignedrolelist, { roleName: role }, true);
				if (angular.isDefined(found) && found.length) {
					return true;
				} else {
					return false;
				}
			}

			$scope.isRoleSelectedSelection = function (role) {
				var found = $filter('filter')($scope.selectedrole, { roleName: role }, true);
				if (angular.isDefined(found) && found.length) {
					return true;
				} else {
					return false;
				}
			}


			$scope.getAllUnassignedRoles = function () {
				var url = 'rolemgnt/roleUnAssignedlist/' + $scope.userIdSelected + '/' + $scope.selectedOrg;
				genericService.getObjects(url).then(function (data) {
					console.log("In getAllUnassignedRoles api :" + angular.toJson(data));
					$scope.oldunassignedrolelist = angular.copy(data);
					$scope.unassignedrolelist = angular.copy(data);
					console
						.log($scope.unassignedrolelist);
					console
						.log("un assigned "
						+ angular
							.toJson($scope.unassignedrolelist));
				}, function (data) {
					console.log("in failure");
				});
			};

			$scope.getAllassignedRoles = function () {

				var url = 'rolemgnt/roleAssignedlist/' + $scope.userIdSelected + '/' + $scope.selectedOrg;
				genericService.getObjects(url).then(function (data) {
					console.log("In getAllassignedRoles api :" + angular.toJson(data));

					$scope.oldassignedrolelist = angular.copy(data);
					$scope.assignedrolelist = angular.copy(data);
					console
						.log($scope.assignedrolelist);
					console
						.log("Assigned "
						+ angular
							.toJson($scope.assignedrolelist));

				}, function (data) {
					console.log("in failure");
				});

			};

			$scope.getPermissions = function (roleId) {
				console.log('In getPermissions:' + roleId);
				var url = 'rolemgnt/rolePermissions/' + roleId;
				genericService.getObjects(url).then(function (data) {
					console.log("In getPermissions api :" + angular.toJson(data));
					console.log(data);
					$scope.permissionslist = data;
					console.log($scope.permissionslist);
					console.log("Assigned " + angular.toJson($scope.permissionslist));
				}, function (data) {
					$loading.finish('users');
					if (data.statusCode == 204) {
						BootstrapDialog.alert({
			                     			 title: 'Message',
							message: 'There are no permissions for this user',
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
								from: "top",
								align: "right"
							}
						});
					}
				});
				/*console.log('In getPermissions:'+roleId);
				$http.get('rolemgnt/rolePermissions/'+roleId).then(function(response){
				  console.log(response.data);
				  $scope.permissionslist=response.data;
				  console.log($scope.permissionslist);
				  console.log("Assigned "+angular.toJson($scope.permissionslist)); 
				})*/
			}

			$scope.getAllUnassignedRoles();
			$scope.getAllassignedRoles();
			$scope.getPermissions();

			$scope.saverole = function () {

				console
					.log("new final list "
					+ angular
						.toJson($scope.assignedrolelist));
				console
					.log("old list "
					+ angular
						.toJson($scope.oldassignedrolelist));
				roleAssignmentService.updategroup(
					$scope.unassignedrolelist,
					$scope.assignedrolelist,$scope.userIdSelected,$scope.selectedOrg).then(
					function (data) {
						console.log(angular.toJson(data));
						//alert('successfully updated!');
						$.notify({ icon: 'glyphicon glyphicon-ok', message: 'Role is successfully updated' }, {
							type: 'success', placement: {
								from: "top",
								align: "right"
							}
						});


					}, function (data) {
						//alert('Some error in updating');
						$.notify({ icon: 'glyphicon glyphicon-warning-sign', message: data.message }, {
							type: 'success', placement: {
								from: "top",
								align: "right"
							}
						});
					});
			};

			$scope.cancel = function () {
				if (confirm("Are you sure you want to cancel?")) {
					$state.go('usermgmt.divisionassignment');
				}
				ConfirmService.openDialog('Confirm!', 'Are you sure you want to cancel?').then(function () {
					$state.go('usermgmt.divisionassignment');
				}, function () {
					//TODO:cancel logic goes here
				});
			};

			// use to assign group from selected unassign group
			$scope.assignitem = function (unassignedrole) {
				//$scope.permissionsRequired =false;
				console.log("unassignedrole : "
					+ unassignedrole);

				var i;
				if (unassignedrole.length != 0) {

					for (i = 0; i < unassignedrole.length; i++) {
						console.log('before assigned: '
							+ $scope.assignedrolelist);
						var index = $scope.assignedrolelist.indexOf(unassignedrole[i])
						var index1 = $scope.unassignedrolelist.indexOf(unassignedrole[i])
						console.log('Unassigned: ' + index);
						if (index == -1) {
							$scope.assignedrolelist.push(unassignedrole[i]);
						}

						if (index1 != -1) {
							console.log('Index: ' + index);
							$scope.unassignedrolelist.splice(index1, 1);
						}

						console.log('assigned: ' + $scope.assignedrolelist);
					}
				}
			};
			// use to assign all group from unassign group
			$scope.assignall = function () {
				//$scope.permissionsRequired =false;
				angular.forEach($scope.unassignedrolelist,
					function (item) {
						$scope.assignedrolelist.push(item);
					});
				$scope.unassignedrolelist = [];
			};

			// use to unassign group from selected assign group
			$scope.unassignitem = function (selectedrole) {
				//$scope.permissionsRequired =false;
				console.log("selectedgroup " + selectedrole);
				var i;
				if (selectedrole.length != 0) {
					for (i = 0; i < selectedrole.length; i++) {
						var index = $scope.unassignedrolelist
							.indexOf(selectedrole[i])
						var index1 = $scope.assignedrolelist
							.indexOf(selectedrole[i])
						console.log('Unassigned: ' + index);
						if (index == -1) {
							$scope.unassignedrolelist
								.push(selectedrole[i]);

						}

						if (index1 != -1) {
							console.log('Index: ' + index);
							$scope.assignedrolelist.splice(
								index1, 1);

						}
					}
				}
			};

			// use to unassign all group from assign group
			$scope.unassignall = function () {
				//$scope.permissionsRequired =false;

				angular
					.forEach($scope.assignedrolelist,
					function (item) {
						$scope.unassignedrolelist
							.push(item);
					});
				$scope.assignedrolelist = [];
				console
					.log("$scope.assignedgrplist "
					+ angular
						.toJson($scope.assignedrolelist));
			};

			$scope.backToDivision = function () {
				$state.go('usermgmt.divisionassignment');
			}

			$scope.backToUserList = function () {
				$state.go('usermgmt.user.viewuser');
			}

		}]);

roleassignment
	.factory(
				'roleAssignmentService',
				[
		'$q',
		'$http',
		'$state', 'SharedService',
		function ($q, $http, $state, SharedService) {
			
			var factory = {};
			factory.updategroup = function (oldRoleList,
				newRoleList,userID,orgId) {
				var deferred = $q.defer();
				var jsonData = JSON.stringify({
					params: {
						assigned: newRoleList,
						unAssigned: oldRoleList
					}
				});
				var tempurl = 'rolemgnt/updaterole/' + userID + '/' + orgId;
				console.log('While roleAssignmentService :' + userID + ' ' + orgId);
				$http(
					{
						method: 'POST',
						url: tempurl,
						data: jsonData,
						headers: {
							'Content-Type': 'application/json'
						}
					})
					.success(
					function (data, status, headers,
						config) {
						console
							.log("Response"
							+ JSON
								.stringify(data)
							+ " Status :"
							+ status);
						deferred.resolve(data);
					})
					.error(
					function (data) {
						console
							.log("Not a valid user "
							+ data);
						deferred.reject(data);
					});
				return deferred.promise;
			};
			return factory;

		}]);
