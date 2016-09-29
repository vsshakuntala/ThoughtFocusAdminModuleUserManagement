var rolemodule = angular.module('tf.homemodule.userassignmentmodule.rolemodule', []);

rolemodule.constant('ROLECONSTANTS', {
    CONFIG: {
        STATE: 'home.userassignment.role',
        URL: '/role',
        CONTROLLER: 'RoleController',
        TEMPLATEURL: 'app/partials/home/userassignment/role/role.html',
    },
    CONTROLLER: {}
});

rolemodule.config(
    ['$stateProvider',
        'ROLECONSTANTS',
        function ($stateProvider, ROLECONSTANTS) {
            $stateProvider.state(ROLECONSTANTS.CONFIG.STATE, {
                url: ROLECONSTANTS.CONFIG.URL,
                templateUrl: ROLECONSTANTS.CONFIG.TEMPLATEURL,
                controller: ROLECONSTANTS.CONFIG.CONTROLLER,
                data: {
                    requireLogin: false
                },
                params: {
                    userDetails: null,
                    organization: null
                }
            });
        }
    ]);

rolemodule.controller('RoleController',
    ['$scope',
        '$log',
        '$filter',
        '$rootScope',
        'genericService',
        '$stateParams',
        'StorageService',
        'roleService',
        'ROLECONSTANTS',
        function ($scope, $log, $filter, $rootScope, genericService, $stateParams, StorageService, roleService, ROLECONSTANTS) {

            if (angular.isDefined($stateParams.userDetails) && $stateParams.userDetails !== null) {
                $scope.userDetails = angular.copy($stateParams.userDetails);
                StorageService.set('userDetails', $scope.userDetails);
                $stateParams.userDetails = null;
            } else {
                var temp = StorageService.get('userDetails');
                if (temp !== null) {
                    $scope.userDetails = angular.copy(temp);
                    temp = null;
                } else {
                    //TODO: INVALID SESSION
                }
            }

            if (angular.isDefined($stateParams.organization) && $stateParams.organization !== null) {
                $scope.organization = angular.copy($stateParams.organization);
                StorageService.set('organization', $scope.organization);
                $stateParams.organization = null;
            } else {
                var temp = StorageService.get('organization');
                if (temp !== null) {
                    $scope.organization = angular.copy(temp);
                    temp = null;
                } else {
                    //TODO: INVALID SESSION
                }
            }

            $scope.assignedSelectedRole = [];
            $scope.assignedCheck = 'uncheck';
            $scope.checkToggleAssigned = function (assignedCheck) {
                $scope.assignedSelectedRole = [];
                if (assignedCheck.toString() === 'check') {
                    for (var i = 0; i < $scope.assignedRole.length; i++) {
                        $scope.assignedSelectedRole.push($scope.assignedRole[i]);
                    }
                }
            }
            $scope.unassignedSelectedRole = [];
            $scope.unAssignedCheck = 'uncheck';
            $scope.checkToggleUnAssigned = function (unAssignedCheck) {
                $scope.unassignedSelectedRole = [];
                if (unAssignedCheck.toString() === 'check') {
                    for (var i = 0; i < $scope.unAssignedRole.length; i++) {
                        $scope.unassignedSelectedRole.push($scope.unAssignedRole[i]);
                    }
                }
            }
            $scope.permissions = [];
            $scope.getPermissions = function (role) {
                if (role.length === 1) {
                    var url = $rootScope.baseUrl + 'rolemgnt/rolePermissions/' + role[0].roleId;
                    genericService.getObjects(url).then(function (data) {
                        $log.debug("In rolePermissions api :" + angular.toJson(data));
                        $scope.permissions = angular.copy(data);
                    }, function (data) {
                        $log.debug("in failure");
                    });
                } else {
                    $scope.permissions = [];
                }
            }

            $scope.getUnassignedRoles = function () {
                var url = $rootScope.baseUrl + 'rolemgnt/roleUnAssignedlist/' + $scope.userDetails.userId + '/' + $scope.organization.organizationId;
                genericService.getObjects(url).then(function (data) {
                    $log.debug("In getAllUnassignedRoles api :" + angular.toJson(data));
                    $scope.unAssignedRole = angular.copy(data);
                }, function (data) {
                    $log.debug("in failure");
                });
            }
            $scope.getUnassignedRoles();


            $scope.getAssignedRoles = function () {
                var url = $rootScope.baseUrl + 'rolemgnt/roleAssignedlist/' + $scope.userDetails.userId + '/' + $scope.organization.organizationId;
                genericService.getObjects(url).then(function (data) {
                    $log.debug("In getAllassignedRoles api :" + angular.toJson(data));
                    $scope.assignedRole = angular.copy(data);
                }, function (data) {
                    $log.debug("in failure");
                });
            }
            $scope.getAssignedRoles();

            var findIndexOf = function (myArray, obj) {
                for (var i = 0; i < myArray.length; i++) {
                    if (angular.equals(myArray[i], obj)) {
                        return i;
                    }
                }
                return -1;
            }

            $scope.saverole = function () {
                var url = $rootScope.baseUrl + 'rolemgnt/updaterole/' + $scope.userDetails.userId + '/' + $scope.organization.organizationId;
                var obj = JSON.stringify({
                    params: {
                        assigned: $scope.assignedRole,
                        unAssigned: $scope.unAssignedRole
                    }
                });
                $rootScope.startSpin();
                genericService.addObject(url, obj).then(function (data) {
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'success', message: "role updated sucessfully" });
                    $log.debug("In updaterole api :" + angular.toJson(data));
                }, function (data) {
                    $log.debug("in failure");
                    $rootScope.stopSpin();
                });
            }

            $scope.moveToAssigned = function (unassignedSelectedRole) {
                if (angular.isUndefined(unassignedSelectedRole) || !unassignedSelectedRole.length) {
                    $.toaster({ priority: 'warning', message: atleatOneDivisionAssign });
                    return;
                }
                angular.forEach(unassignedSelectedRole, function (value, key) {
                    $scope.assignedRole.push(angular.copy(value));
                });
                for (var i = 0; i < unassignedSelectedRole.length; i++) {
                    var index = findIndexOf($scope.unAssignedRole, unassignedSelectedRole[i]);
                    if (index !== -1) {
                        $scope.unAssignedRole.splice(index, 1);
                    }
                }
                newInput = [];
                unassignedSelectedRole = [];
            }
            $scope.moveToUnAssigned = function (assignedSelectedRole) {
                if (angular.isUndefined(assignedSelectedRole) || !assignedSelectedRole.length) {
                    $.toaster({ priority: 'warning', message: atleatOneDivisionUnAssign });
                    return;
                }
                angular.forEach(assignedSelectedRole, function (value, key) {
                    $log.info('came');
                    $scope.unAssignedRole.push(angular.copy(value));
                });
                for (var i = 0; i < assignedSelectedRole.length; i++) {
                    var index = findIndexOf($scope.assignedRole, assignedSelectedRole[i]);
                    if (index !== -1) {
                        $scope.assignedRole.splice(index, 1);
                    }
                }
                newInput = [];
                assignedSelectedRole = [];
            }

            $scope.updateDivisionAssignment = function () {
                var obj = {};
                obj.userId = $scope.userDetails.userId;
                obj.organizationIds = $scope.assignedDivision;
                obj.modifiedById = $rootScope.adminId;
                obj.addUserToOrg = true;
                if (!angular.isUndefined($scope.notes)) {
                    obj.note = $scope.user.notes;
                } else {
                    obj.note = '';
                }
                if (obj.organizationIds.length) {
                    genericService.addObject($rootScope.baseUrl + 'divisionAssignment/addOrAssignUserToOrg/', obj).then(function (data) {
                        $.toaster({ priority: 'success', message: divisionAddedSuccessfully });
                        $('#adddivision').trigger('click');
                    }, function (data) {
                    });
                } else {
                    $.toaster({ priority: 'warning', message: atleatOneDivisionAssign });
                }
            }
            onresize();
        }
    ]);

rolemodule.factory('roleService', ['ROLECONSTANTS',
    function (ROLECONSTANTS) {
        var factory = {};
        return factory;
    }
]);