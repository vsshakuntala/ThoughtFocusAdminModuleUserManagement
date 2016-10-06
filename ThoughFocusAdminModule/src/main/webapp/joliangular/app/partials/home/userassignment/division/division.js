var divisionmodule = angular.module('tf.homemodule.userassignmentmodule.divisionmodule', []);

divisionmodule.constant('DIVISIONCONSTANTS', {
    CONFIG: {
        STATE: 'home.userassignment.division',
        URL: '/division',
        CONTROLLER: 'DivisionController',
        TEMPLATEURL: 'app/partials/home/userassignment/division/division.html',
    },
    CONTROLLER: {}
});

divisionmodule.config(
    ['$stateProvider',
        'DIVISIONCONSTANTS',
        function ($stateProvider, DIVISIONCONSTANTS) {
            $stateProvider.state(DIVISIONCONSTANTS.CONFIG.STATE, {
                url: DIVISIONCONSTANTS.CONFIG.URL,
                templateUrl: DIVISIONCONSTANTS.CONFIG.TEMPLATEURL,
                controller: DIVISIONCONSTANTS.CONFIG.CONTROLLER,
                data: {
                    requireLogin: false
                },
                params: {
                    userDetails: null
                }
            });
        }
    ]);

divisionmodule.controller('DivisionController',
    ['$scope',
        '$rootScope',
        '$log',
        '$compile',
        '$state',
        '$stateParams',
        'DTOptionsBuilder',
        'DTColumnBuilder',
        'StorageService',
        'genericService',
        'applicationUtilityService',
        'divisionService',
        'DIVISIONCONSTANTS',
        function ($scope, $rootScope, $log, $compile, $state, $stateParams, DTOptionsBuilder,
            DTColumnBuilder, StorageService, genericService, applicationUtilityService,
            divisionService, DIVISIONCONSTANTS) {

            $scope.unAssignedDivision = [];
            $scope.assignedDivision = [];
            $scope.assignedSelectedDivision = [];
            $scope.user = {};
            $scope.users = [];
            $scope.orgobj = {};
            $scope.neddToUpdateStatus = false;

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

            var prepareApiObject = function (pageNumber, count, queryString) {
                var requestForScroll = {};
                requestForScroll.pageNumber = angular.copy(pageNumber);
                requestForScroll.count = angular.copy(count);
                requestForScroll.queryString = angular.copy(queryString);
                return requestForScroll;
            }

            $scope.goToRole = function () {
                // if ($scope.adminAccess) {
                //     $state.go('home.userassignment.role', { userDetails: $scope.userDetails, organization: $scope.orgobj }, { reolad: true });
                // } else {
                //     $.toaster({ priority: 'danger', message: 'you dont have access' });
                // }
                $state.go('home.userassignment.role', { userDetails: $scope.userDetails, organization: $scope.orgobj }, { reolad: true });
            }

            $scope.goToAddress = function () {
                url = $rootScope.baseUrl + 'addassgn/allcustomerlist/' + $scope.userDetails.userId + '/' + $scope.orgobj.organizationId;
                var obj = prepareApiObject(1, 10, null);
                genericService.addObject(url, obj).then(function (data) {
                    if (data.data.length) {
                        // if ($scope.adminAccess) {
                        //     $state.go('home.userassignment.address', { userDetails: $scope.userDetails, organization: $scope.orgobj }, { reolad: true });
                        // } else {
                        //     $.toaster({ priority: 'danger', message: 'you dont have access' });
                        // }
                        $state.go('home.userassignment.address', { userDetails: $scope.userDetails, organization: $scope.orgobj }, { reolad: true });
                    } else {
                        $.toaster({ priority: 'danger', message: 'This user dont have assigned customer' });
                    }
                }, function () {
                    return null;
                });
            }

            $scope.goToCustomer = function () {
                // if ($scope.adminAccess) {
                //     $state.go('home.userassignment.customer', { userDetails: $scope.userDetails, organization: $scope.orgobj }, { reolad: true });
                // } else {
                //     $.toaster({ priority: 'danger', message: 'you dont have access' });
                // }
            	
            	if($scope.selectedObj.roleCount>0){
                $state.go('home.userassignment.customer', { userDetails: $scope.userDetails, organization: $scope.orgobj }, { reolad: true });
            	}else{
            		 $.toaster({ priority: 'danger', message: 'Please assign role before assigning customer' });
            	}
            }

            $scope.goToMachine = function () {
                // if ($scope.adminAccess) {
                //     $state.go('home.userassignment.machine', { userDetails: $scope.userDetails, organization: $scope.orgobj }, { reolad: true });
                // } else {
                //     $.toaster({ priority: 'danger', message: 'you dont have access' });
                // }
                $state.go('home.userassignment.machine', { userDetails: $scope.userDetails, organization: $scope.orgobj }, { reolad: true });
            }

            $scope.goToGroups = function () {
                // if ($scope.adminAccess) {
                //     $state.go('home.userassignment.group', { userDetails: $scope.userDetails, organization: $scope.orgobj }, { reolad: true });
                // } else {
                //     $.toaster({ priority: 'danger', message: 'you dont have access' });
                // }
                $log.debug('going group :' + angular.toJson($scope.orgobj));
                $state.go('home.userassignment.group', { userDetails: $scope.userDetails, organization: $scope.orgobj }, { reolad: true });
            }

            var goToAssignment = function (goToAssignment) {
                switch (goToAssignment) {
                    case 2:
                        $scope.goToRole();
                        break;
                    case 3:
                        $scope.goToAddress();
                        break;
                    case 4:
                        $scope.goToCustomer();
                        break;
                    case 5:
                        $scope.goToMachine();
                        break;
                    case 6:
                        $scope.goToGroups();
                        break;
                }
            }

            /**
             * API FOR RESET PASSWORD 
             */
            $scope.resetPassword = function () {
                $rootScope.startSpin();
                genericService.getObjects($rootScope.baseUrl + 'usermgmtrest/resetpassword?userEmail=' + $scope.userDetails.userEmail).then(function (response) {
                    $.toaster({ priority: 'success', message: resetPasswordSuccess });
                    $rootScope.stopSpin();
                }, function (response) {
                    $.toaster({ priority: 'danger', message: resetPasswordfailed });
                    $rootScope.stopSpin();
                });
            }

            $scope.updateStatus = function (toDo) {
                $scope.neddToUpdateStatus = true;
                console.log('to do :' + toDo);
                console.log('checking :' + $scope.orgName);
                $scope.isDelete = toDo;
                if (toDo) {
                    $('#deletenote').modal('show');
                    $(document).on('hidden.bs.modal', '#deletenote', function () {
                        $state.reload();
                    });
                } else {
                    $('#assignnote').modal('show');
                }
            }

            $scope.drawDataTable = function () {
                $scope.dtOptions = DTOptionsBuilder.fromSource('../divisionAssignment/getDivisionAssignments/' + $scope.userDetails.userId + '/' + $rootScope.adminId)
                    // .withOption('processing', true)
                    .withOption('bDestory', true)
                    .withOption('searching', false)
                    .withOption('info', false)
                    .withOption('paging', false)
                    .withPaginationType('full_numbers')
                    .withOption('oLanguage', {sEmptyTable: "No matching results found for this search criteria" })
                    .withOption('bFilter', true)
                    .withOption('rowCallback', rowCallback)
                    .withOption('createdRow', function (row, data, dataIndex) {
                        $compile(angular.element(row).contents());
                    });

                function rowCallback(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                    $scope.totalrows = this.fnSettings().fnRecordsTotal();
                    $(nRow).mouseover(function (e) {
                        $(nRow).addClass('highlight');
                    })
                    $(nRow).mouseout(function (e) {
                        $(nRow).removeClass('highlight');
                    })
                    $('td', nRow).unbind('click');
                    $('td', nRow).bind('click', function () {
                        var cell = $(this).closest('td');
                        var cellIndex = cell[0].cellIndex;
                        $scope.$apply(function () {
                            $scope.orgobj = {
                                organizationId: aData.organizationId,
                                organizationName: aData.organizationName,
      
                            }
                            $scope.selectedObj=aData;
                            $scope.adminAccess = aData.adminAccess;
                            goToAssignment(cellIndex);
                        });
                    });
                    return nRow;
                }

                $scope.dtColumns = [
                    DTColumnBuilder.newColumn('organizationName').withTitle('Division').withOption('sortable', false).withOption('name', 'organizationName').withOption('width', '204px').renderWith(function (data, type, full) {
                        return '' + full.organizationName;
                    }),
                    DTColumnBuilder.newColumn('createdDate').withTitle('Registered Date').withOption('sortable', false).withOption('name', 'createdDate').withOption('width', '204px').renderWith(function (data, type, full) {
                        var date = new Date(full.createdDate);
                        var month = date.getMonth() + 1;
                        var monthNames = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                            'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
                        ];
                        return date.getDate() + ' ' + monthNames[date.getMonth()] + ' ' + date.getFullYear();
                    }),
                    DTColumnBuilder.newColumn('roleCount').withTitle('Role <em class="error">*</em>').withOption('sortable', false).withOption('name', 'roleCount').withOption('width', '204px').renderWith(function (data, type, full) {
                        $log.debug('org name :' + full.organizationName);
                        if (full.roleCount) {
                            return '<div class="cursor-point division-td" title="Click to assign role"><i class="custom custom-customer_role_access tf-icon tf-icon-3x icon-color"></i><br>' + full.roleCount + '</div>';
                        } else {
                            return '<div class="cursor-point division-td" title="Click to assign role"><i class="custom custom-iconscustomer_role_access_add tf-icon tf-icon-3x "></i></div>';
                        }
                    }),
                    DTColumnBuilder.newColumn('defaultAddressCount').withTitle('Default Address <em class="error">*</em>').withOption('sortable', false).withOption('name', 'defaultAddressCount').withOption('width', '204px').renderWith(function (data, type, full) {
                        if (full.defaultAddressCount) {
                            return '<div class="cursor-point division-td" title="Click to assign default address"><i class="custom custom-map-pin_outline tf-icon tf-icon-3x icon-color"></i><br>' + full.defaultAddressCount + '</div>';
                        } else {
                            return '<div class="cursor-point division-td" title="Click to assign default address"><i class="custom custom-iconsmap-pin_outline_add tf-icon tf-icon-3x"></i></div>';
                        }
                    }),
                    DTColumnBuilder.newColumn('totalCustomerCount').withTitle('Customer <em class="error">*</em>').withOption('sortable', false).withOption('name', 'totalCustomerCount').withOption('width', '204px').renderWith(function (data, type, full) {
                        if (full.totalCustomerCount) {
                            return '<div class="cursor-point division-td" title="Click to assign customer"><i class="custom custom-customer_tick_role tf-icon tf-icon-3x icon-color"></i><br>' + (full.totalCustomerCount) + '</div>';
                        } else {
                            return '<div class="cursor-point division-td" title="Click to assign customer"><i class="custom custom-iconscustomer_tick_role_add tf-icon tf-icon-3x"></i></div>';
                        }
                    }),
                    DTColumnBuilder.newColumn('totalCatalogCount').withTitle('Machine').withOption('sortable', false).withOption('name', 'totalCatalogCount').withOption('width', '204px').renderWith(function (data, type, full) {
                        if (full.totalCatalogCount) {
                            return '<div class="cursor-point division-td" title="Click to assign machines"><i class="custom custom-machines tf-icon tf-icon-3x icon-color"></i><br>' + (full.totalCatalogCount) + '</div>';
                        } else {
                            return '<div class="cursor-point division-td" title="Click to assign machines"><i class="custom custom-iconsadd_machines tf-icon tf-icon-3x"></i></div>';
                        }
                    }),
                    DTColumnBuilder.newColumn('groupsCount').withTitle('Groups').withOption('sortable', false).withOption('name', 'groupsCount').withOption('width', '204px').renderWith(function (data, type, full) {
                        if (full.groupsCount) {
                            return '<div class="cursor-point division-td" title="Click to assign groups" ><i class="custom custom-group tf-icon tf-icon-3x icon-color"></i><br>' + full.groupsCount + '</div>';
                        } else {
                            return '<div class="cursor-point division-td" title="Click to assign groups"><i class="custom custom-iconsgroup_add tf-icon tf-icon-3x"></i></div>';
                        }
                    }),
                    DTColumnBuilder.newColumn('null').withTitle('Action').withOption('sortable', false).withOption('width', '204px').renderWith(function (data, type, full) {
                        $scope.users[full.customerCount] = full;
                        $scope.orgName = full.organizationName
                        console.log('In action: ' + full.organizationName);
                        if (full.roleCount && full.defaultAddressCount && full.totalCustomerCount && angular.equals(full.status, 'Pending')) {
                            //True for unassign and false for assign
                            return '<button class="btn assign-class" ng-click="updateStatus(false)" title="Click to assign organization">' +
                                '   <i class="fa fa-check tf-icon-2x assign-user-icon"></i>' +
                                '</button>&nbsp;' +
                                '<button class="btn assign-class" ng-click="updateStatus(true)" title="Click to unassign organization">' +
                                '   <i class="fa fa-trash-o tf-icon-2x delete-user-icon"></i>' +
                                '</button>';
                        } else {
                            return '<button class="btn assign-class button-visibility">' +
                                '   <i class="fa fa-check tf-icon-2x assign-user-icon"></i>' +
                                '</button>&nbsp;' +
                                '<button class="btn assign-class" ng-click="updateStatus(true)" title="Click to unassign organization">' +
                                '   <i class="fa fa-trash-o tf-icon-2x delete-user-icon "></i>' +
                                '</button>';
                        }
                    })
                ];

                $scope.dtInstanceCallback = function (dtInstance) {
                    $scope.dtInstance = dtInstance;
                    dtInstance.DataTable.on('draw.dt', function () {
                        let elements = angular.element("#" + dtInstance.id + " .ng-scope");
                        angular.forEach(elements, function (element) {
                            $compile(element)($scope);
                        });
                        onresize();
                    });
                }
            }
            $scope.drawDataTable();

            var getNotesOfUser = function () {
                genericService.getObjects($rootScope.baseUrl + 'divisionAssignment/getNotesOfUser/' + $scope.userDetails.userId).then(function (data) {
                    $log.debug('notes : ' + angular.toJson(data));
                    $scope.notes = data;
                }, function (data) {
                    $log.error('notes failed : ' + angular.toJson(data));
                });
            }

            $scope.submitNoteOfUser = function () {
                if ($scope.neddToUpdateStatus && !$scope.isDelete) {
                    $scope.user.userId = $scope.userDetails.userId;
                    $scope.user.organizationIds = [];
                    var obj = {};
                    obj.organizationId = $scope.orgobj.organizationId;
                    $scope.user.organizationIds.push(angular.copy(obj));
                    obj = null;
                    $scope.user.modifiedById = $rootScope.adminId;
                    $scope.user.note = angular.copy($scope.user.notes);
                    delete $scope.user['notes'];
                    $log.debug('before addOrAssignUserToOrg');
                    $rootScope.startSpin();
                    genericService.addObject($rootScope.baseUrl + 'divisionAssignment/addOrAssignUserToOrg', $scope.user).then(function (data) {
                        $.toaster({ priority: 'success', message: ApproveUserStatusSuccess });
                        $scope.user = {};
                        $log.debug('after addOrAssignUserToOrg');
                        $rootScope.stopSpin();
                        $state.reload();
                        $('#close-note-modal').trigger('click');
                    }, function (data) {
                        $log.debug('failed deAssignAllUserAllocations');
                        $.toaster({ priority: 'danger', message: ApproveUserStatusFailed });
                        $scope.user = {};
                        $rootScope.stopSpin();
                        $state.reload();
                        $('#close-note-modal').trigger('click');
                    });
                } else if ($scope.neddToUpdateStatus && $scope.isDelete) {
                    $scope.user.userId = $scope.userDetails.userId;
                    $scope.user.modifiedById = $rootScope.adminId;
                    $scope.user.organizationId = $scope.orgobj.organizationId;
                    $log.debug('before deAssignAllUserAllocations');
                    $rootScope.startSpin();
                    genericService.addObject($rootScope.baseUrl + 'divisionAssignment/deAssignAllUserAllocations', $scope.user).then(function (data) {
                        $.toaster({ priority: 'success', message: DeleteUserStatusSuccess });
                        $log.debug('after deAssignAllUserAllocations');
                        $scope.user = {};
                        $rootScope.stopSpin();
                        $state.reload();
                        $('#close-note-modal').trigger('click');
                    }, function (data) {
                        $log.debug('failed deAssignAllUserAllocations');
                        $.toaster({ priority: 'danger', message: DeleteUserStatusFailed });
                        $scope.user = {};
                        $rootScope.stopSpin();
                        $state.reload();
                        $('#close-note-modal').trigger('click');
                    });
                } else {
                    $scope.user.createdBy = $rootScope.adminId;
                    $scope.user.userId = $scope.userDetails.userId;
                    $rootScope.startSpin();
                    genericService.addObject($rootScope.baseUrl + 'divisionAssignment/addNotesForUser', $scope.user).then(function (data) {
                        $.toaster({ priority: 'success', message: notesAddedSuccessfully });
                        $scope.user = {};
                        $rootScope.stopSpin();
                        $state.reload();
                        $('#close-note-modal').trigger('click');
                    }, function (data) {
                        $log.debug('failed addNotesForUser');
                        $.toaster({ priority: 'danger', message: notesNotAdded });
                        $scope.user = {};
                        $rootScope.stopSpin();
                        $state.reload();
                        $('#close-note-modal').trigger('click');
                    });
                }

            }

            $(document).on('hidden.bs.modal', '#modal_large', function () {
                $state.reload();
            });

            $(document).on('hidden.bs.modal', '#adddivision', function () {
                $state.reload();
            });

            $(document).on('hidden.bs.modal', '#note', function () {
                $state.reload();
            });

            $(document).on('hidden.bs.modal', '#deletenote', function () {
                $state.reload();
            });

            $(document).on('hidden.bs.modal', '#assignnote', function () {
                $log.info('assignnote hidden ');
                $state.reload();
            });


            var getUnAssignedDivisionsForUser = function () {
                $log.debug('calling getUnAssignedDivisionsForUser');
                genericService.getObjects($rootScope.baseUrl + 'divisionAssignment/getUnAssignedDivisionsForUser/' + $scope.userDetails.userId + '/' + $rootScope.adminId).then(function (data) {
                    $log.debug('UnAssignedDivisionsForUser : ' + angular.toJson(data));
                    $scope.unAssignedDivision = data;
                }, function (data) {
                    $log.error('UnAssignedDivisionsForUser failed : ' + angular.toJson(data));
                });
            }

            var getDivisionAssignments = function () {
                genericService.getObjects($rootScope.baseUrl + 'divisionAssignment/getDivisionAssignments/' + $scope.userDetails.userId + '/' + $rootScope.adminId).then(function (data) {
                    $log.debug('getDivisionAssignments : ' + angular.toJson(data));
                    $scope.assignedDivision = data;
                    getUnAssignedDivisionsForUser();
                }, function (data) {
                    $log.error('getDivisionAssignments failed : ' + angular.toJson(data));
                });
            }

            /**
             * Tasks to be done on load of controller
             */
            var onLoadTasks = function () {
                console.log('on load');
                getUnAssignedDivisionsForUser();
                getNotesOfUser();
                onresize();
            }
            onLoadTasks();

            $scope.assignedSelectedDivision = [];
            $scope.assignedCheck = 'uncheck';
            $scope.checkToggleAssigned = function (assignedCheck) {
                $scope.assignedSelectedDivision = [];
                if (assignedCheck.toString() === 'check') {
                    for (var i = 0; i < $scope.assignedDivision.length; i++) {
                        $scope.assignedSelectedDivision.push($scope.assignedDivision[i]);
                    }
                }
            }
            $scope.unassignedSelectedDivision = [];
            $scope.unAssignedCheck = 'uncheck';
            $scope.checkToggleUnAssigned = function (unAssignedCheck) {
                $scope.unassignedSelectedDivision = [];
                if (unAssignedCheck.toString() === 'check') {
                    for (var i = 0; i < $scope.unAssignedDivision.length; i++) {
                        $scope.unassignedSelectedDivision.push($scope.unAssignedDivision[i]);
                    }
                }
            }

            var findIndexOf = function (myArray, obj) {
                for (var i = 0; i < myArray.length; i++) {
                    if (angular.equals(myArray[i], obj)) {
                        return i;
                    }
                }
                return -1;
            }

            $scope.moveToAssigned = function (unassignedSelectedDivision) {
                if (angular.isUndefined(unassignedSelectedDivision) || !unassignedSelectedDivision.length) {
                    $.toaster({ priority: 'warning', message: atleatOneDivisionAssign });
                    return;
                }
                angular.forEach(unassignedSelectedDivision, function (value, key) {
                    $scope.assignedDivision.push(angular.copy(value));
                });
                for (var i = 0; i < unassignedSelectedDivision.length; i++) {
                    var index = findIndexOf($scope.unAssignedDivision, unassignedSelectedDivision[i]);
                    if (index !== -1) {
                        $scope.unAssignedDivision.splice(index, 1);
                    }
                }
                if (!$scope.unAssignedDivision.length) {
                    $scope.unAssignedCheck = '';
                }
                newInput = [];
                unassignedSelectedDivision = [];
            }
            $scope.moveToUnAssigned = function (assignedSelectedDivision) {
                if (angular.isUndefined(assignedSelectedDivision) || !assignedSelectedDivision.length) {
                    $.toaster({ priority: 'warning', message: atleatOneDivisionUnAssign });
                    return;
                }
                angular.forEach(assignedSelectedDivision, function (value, key) {
                    $scope.unAssignedDivision.push(angular.copy(value));
                });
                for (var i = 0; i < assignedSelectedDivision.length; i++) {
                    var index = findIndexOf($scope.assignedDivision, assignedSelectedDivision[i]);
                    if (index !== -1) {
                        $scope.assignedDivision.splice(index, 1);
                    }
                }
                if (!$scope.assignedDivision.length) {
                    $scope.assignedCheck = '';
                }
                newInput = [];
                assignedSelectedDivision = [];
            }

            $scope.updateDivisionAssignment = function () {
                BootstrapDialog.confirm({
                    title: 'Confirm',
                    message: 'Are you sure you want to Save?', callback: function (result) {
                        if (result) {
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
                                    $state.reload();
                                    $('#adddivision').trigger('click');
                                }, function (data) {
                                });
                            } else {
                                $.toaster({ priority: 'warning', message: atleatOneDivisionAssign });
                            }
                        }
                        else {
                            $rootScope.stopSpin();

                        }
                    }
                });
            }
        }
    ]);

divisionmodule.factory('divisionService', ['DIVISIONCONSTANTS',
    function (DIVISIONCONSTANTS) {
        var factory = {};
        return factory;
    }
]);