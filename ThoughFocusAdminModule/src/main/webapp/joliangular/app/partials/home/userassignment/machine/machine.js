var machinemodule = angular.module('tf.homemodule.userassignmentmodule.machinemodule', []);

machinemodule.constant('MACHINECONSTANTS', {
    CONFIG: {
        STATE: 'home.userassignment.machine',
        URL: '/machine',
        CONTROLLER: 'MachineController',
        TEMPLATEURL: 'app/partials/home/userassignment/machine/machine.html',
    },
    CONTROLLER: {}
});

machinemodule.config(
    ['$stateProvider',
        'MACHINECONSTANTS',
        function ($stateProvider, MACHINECONSTANTS) {
            $stateProvider.state(MACHINECONSTANTS.CONFIG.STATE, {
                url: MACHINECONSTANTS.CONFIG.URL,
                templateUrl: MACHINECONSTANTS.CONFIG.TEMPLATEURL,
                controller: MACHINECONSTANTS.CONFIG.CONTROLLER,
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

machinemodule.controller('MachineController',
    ['$scope',
        '$rootScope',
        '$log',
        '$compile',
        '$stateParams',
        'StorageService',
        'DTOptionsBuilder',
        'DTColumnBuilder',
        'genericService',
        'applicationUtilityService',
        'machineService',
        'MACHINECONSTANTS', '$state',
        function ($scope, $rootScope, $log, $compile, $stateParams, StorageService, DTOptionsBuilder,
            DTColumnBuilder, genericService, applicationUtilityService,
            machineService, MACHINECONSTANTS, $state) {
            $('#myModal').appendTo("body").modal('show');
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
                $stateParams.oraganization = null;
            } else {
                var temp = StorageService.get('organization');
                console.log(angular.toJson(temp));
                if (temp !== null) {
                    $scope.organization = angular.copy(temp);
                    temp = null;
                } else {
                    //TODO: INVALID SESSION
                }
            }
            $scope.angular = angular;
            $scope.orgName = $scope.organization.organizationName;
            $scope.statuses = [{ status: 'assigned', value: 'Assigned' }, { status: 'notassigned', value: 'Not Assigned' }];
            $scope.logedIn_user_id = $rootScope.adminId;
            $scope.filteredObjects = {
                user_id: $scope.userDetails.userId,
                organization_id: $scope.organization.organizationId,
                catalog_id: '',
                model: '',
                catalog_reference: '',
                customer_name: '',
                status: $scope.statuses[0].status,
                group_name: ''
            };
            $scope.totalrows = 0;
            $scope.selectedObjects = [];
            $scope.searchValue = '';
            /**
             * 
             * Assign function
             */
            $scope.assign = function (type) {

                var assignUtil = function (url, jsonData) {
                    genericService.addObject(url, jsonData).then(function (data) {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'success', message: assignFromUnAssignedListSuccess });
                        $scope.reloadDataTable();
                    }, function () {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'failed', message: assignFromUnAssignedListFailed });
                    });
                }

                if (angular.equals($scope.filteredObjects.status, 'notassigned') && !angular.equals(type, 'all') && !angular.equals($scope.totalrows, 0)) {
                    BootstrapDialog.confirm({
                        title: 'Assign',
                        message: 'Are you sure you want to assign selected machine?', callback: function (result) {
                            if (result) {
                                if ($scope.selectedObjects.length) {
                                    var url = $rootScope.baseUrl + 'assignmachine/' + $scope.filteredObjects.user_id + '/' + $scope.filteredObjects.organization_id + '/' + $scope.logedIn_user_id;
                                    var data = {};
                                    data.params = $scope.selectedObjects;
                                    assignUtil(url, data);
                                }
                                else {
                                    $rootScope.stopSpin();
                                    $.toaster({ priority: 'danger', message: selectatleastoneToAssign });
                                }
                            } else {
                                $rootScope.stopSpin();

                            }
                        }
                    });
                } else if (angular.equals($scope.totalrows, 0) && angular.equals($scope.filteredObjects.status, 'notassigned')) {
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotAssignFromEmptyList });
                } else if (angular.equals($scope.filteredObjects.status, 'assigned')) {
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotAssignFromAssignedList });
                }

            }
            /**
             * 
             *  Assignall function
             */
            $scope.assignall = function (type) {

                var assignUtil = function (url, jsonData) {
                    genericService.addObject(url, jsonData).then(function (data) {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'success', message: assignFromUnAssignedListSuccess });
                        $scope.reloadDataTable();
                    }, function () {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'failed', message: assignFromUnAssignedListFailed });
                    });
                }

                if (angular.equals($scope.filteredObjects.status, 'notassigned') && angular.equals(type, 'all') && !angular.equals($scope.totalrows, 0)) {
                    BootstrapDialog.confirm({
                        title: 'Assign All',
                        message: 'Are you sure you want to assign all machine ?', callback: function (result) {
                            if (result) {
                                $rootScope.startSpin();
                                var url = $rootScope.baseUrl + 'assignallmachine/' + $scope.filteredObjects.user_id + '/' + $scope.filteredObjects.organization_id + '/' + $scope.logedIn_user_id;
                                var data = JSON.stringify($scope.filteredObjects);
                                assignUtil(url, data);
                            } else {
                                $rootScope.stopSpin();

                            }
                        }
                    });
                } else if (angular.equals($scope.totalrows, 0) && angular.equals($scope.filteredObjects.status, 'notassigned')) {
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotAssignFromEmptyList });
                } else if (angular.equals($scope.filteredObjects.status, 'assigned')) {
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotAssignFromAssignedList });
                }

            }
            /**
            * 
            * remove function
            */
            $scope.remove = function (type) {

                var removeUtil = function (url, jsonData) {
                    genericService.addObject(url, jsonData).then(function (data) {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'success', message: removeFromAssignedListSuccess });
                        $scope.reloadDataTable();
                    }, function () {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'failed', message: removeFromAssignedListFailed });
                    });
                }

                if (angular.equals($scope.filteredObjects.status, 'assigned') && !angular.equals(type, 'all') && !angular.equals($scope.totalrows, 0)) {
                    BootstrapDialog.confirm({
                        title: 'Remove',
                        message: 'Are you sure you want to remove machine?', callback: function (result) {
                            if (result) {
                                if ($scope.selectedObjects.length) {
                                    $rootScope.startSpin();
                                    var url = $rootScope.baseUrl + 'removemachine/' + $scope.filteredObjects.user_id + '/' + $scope.filteredObjects.organization_id + '/' + $scope.logedIn_user_id;
                                    var data = {};
                                    data.params = $scope.selectedObjects;
                                    $('#remove-close').trigger('click');
                                    removeUtil(url, data);
                                } else {
                                    $('#remove-close').trigger('click');
                                    $rootScope.stopSpin();
                                    $.toaster({ priority: 'danger', message: selectatleastoneToRemove });

                                }
                            } else {
                                $rootScope.stopSpin();

                            }
                        }
                    });
                } else if (angular.equals($scope.totalrows, 0) && angular.equals($scope.filteredObjects.status, 'assigned')) {
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotRemoveFromEmptyList });
                } else if (angular.equals($scope.filteredObjects.status, 'notassigned')) {
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotRemoveFromUnAssignedList });
                }

            }
            /**
             * 
             *  removeall function
             */
            $scope.removeall = function (type) {

                var removeUtil = function (url, jsonData) {
                    genericService.addObject(url, jsonData).then(function (data) {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'success', message: removeFromAssignedListSuccess });
                        $scope.reloadDataTable();
                    }, function () {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'failed', message: removeFromAssignedListFailed });
                    });
                }

                if (angular.equals($scope.filteredObjects.status, 'assigned') && angular.equals(type, 'all') && !angular.equals($scope.totalrows, 0)) {
                    BootstrapDialog.confirm({
                        title: 'Remove All',
                        message: 'Are you sure you want to remove all machine ?', callback: function (result) {
                            if (result) {
                                $rootScope.startSpin();
                                var url = $rootScope.baseUrl + 'removeallmachine/' + $scope.filteredObjects.user_id + '/' + $scope.filteredObjects.organization_id + '/' + $scope.logedIn_user_id;
                                var data = JSON.stringify($scope.filteredObjects);
                                removeUtil(url, data);
                            } else {
                                $rootScope.stopSpin();

                            }
                        }
                    });
                } else if (angular.equals($scope.totalrows, 0) && angular.equals($scope.filteredObjects.status, 'assigned')) {
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotRemoveFromEmptyList });
                } else if (angular.equals($scope.filteredObjects.status, 'notassigned')) {
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotRemoveFromUnAssignedList });
                }

            }


            $scope.downloadresult = function () {
                if (!angular.isUndefined($scope.filteredObjects) && !angular.isUndefined($scope.filteredObjects.status) && !angular.equals($scope.totalrows, 0) && !angular.isUndefined($scope.totalrows)) {
                    $rootScope.startSpin();
                    var donwloadUrl = $rootScope.baseUrl + 'downloadmachinedocument/' + $scope.filteredObjects.user_id + '/' + $scope.filteredObjects.organization_id + '/' + $scope.logedIn_user_id;
                    var data = applicationUtilityService.buildDataObjectForDataTable($scope.filteredObjects);
                    genericService.downloadFile(donwloadUrl, data).then(function () {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'success', message: downloadSuccesfull });
                    }, function () {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'danger', message: downloadFailed });
                    });
                } else {
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: noDataToDownload });
                }

            }

            var getUrl = function () {
                if (angular.equals($scope.filteredObjects.status, 'assigned')) {
                    return $rootScope.baseUrl + 'machineassignmentreport';
                } else {
                    return $rootScope.baseUrl + 'machineunassignmentreport';
                }
            }
            getAvailableCatalogCount();
            getAssignedCount();
            function getAvailableCatalogCount() {
                genericService.getObjects('../getCatalogCount/' + $scope.filteredObjects.user_id + '/' + $scope.filteredObjects.organization_id).then(function (data) {
                    console.log('In count api :' + angular.toJson(data));
                    if (data != null) {
                        $scope.count = data;
                    }
                }, function (data) {
                    console.log('In count api fail:' + data);
                });
            }

            function getAssignedCount() {
                genericService.getObjects('../catalogmgmt/catalogAssignedCount/' + $scope.filteredObjects.organization_id + '/' + $scope.filteredObjects.user_id).then(function (data) {
                    console.log('In assigned count api :' + angular.toJson(data));
                    if (data != null) {
                        $scope.assignedcount = data;
                    }
                }, function (data) {
                    console.log('In count api fail:' + data);
                });
            }

            $scope.dtInstance = {};
            $scope.reloadDataTable = function () {
                $scope.dtInstance.rerender();
                $scope.selectedObjects = [];
                getAvailableCatalogCount();
                getAssignedCount();
                $scope.totalrows = 0;
            }

            $scope.drawCustomeFilteredDataTable = function () {
                var obj = angular.copy($scope.filteredObjects);
                obj.search = {};
                obj.search.value = $scope.searchValue;
                $scope.filteredObjects = angular.copy(obj);
                console.log(angular.toJson($scope.filteredObjects));
                $scope.drawDataTable();
                $('#globsearch').val('');
                $scope.filteredObjects.search='';
                $scope.searchValue='';
            }

            $(function() {
                $("form input").keypress(function (e) {
                    if ((e.which && e.which == 13) || (e.keyCode && e.keyCode == 13)) {
                    	$scope.drawCustomeFilteredDataTable();
                    }
                });
            });
            
            $scope.drawDataTable = function () {
                var index = -1;
                //$scope.totalrows = 0;
                $scope.dtOptions = DTOptionsBuilder.newOptions()
                    .withOption('ajax', {
                        url: getUrl(),
                        type: 'GET',
                        data: applicationUtilityService.buildDataObjectForDataTable($scope.filteredObjects)
                    })
                    .withDataProp('data')
                    .withOption('bProcessing', true)
                    .withOption('serverSide', true)
                    .withOption('oLanguage', { sProcessing: '<div class="loadposition"><img src="app-content/img/blueimp/loading.gif" alt="nothing"/></div>',sEmptyTable: "No matching results found for this search criteria" })
                    .withOption('searching', false)
                    .withOption('bDestory', true)
                    .withPaginationType('full_numbers')
                    .withOption('bFilter', true)
                    .withOption('order', [5, 'asc'])
                    .withOption('rowCallback', rowCallback)
                    .withOption('createdRow', function (row, data, dataIndex) {
                        index++;
                        // Recompiling so we can bind Angular directive to the DT
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
                    $scope.$apply();
                    $('td:first-child>input', nRow).unbind('click');
                    $('td:first-child>input', nRow).bind('click', function () {
                        $scope.$apply(function () {
                            $scope.selectedObjects = applicationUtilityService.addUniqueObjectToList($scope.selectedObjects, aData.catalog_id);
                        });
                    });
                    if (iDisplayIndex === index) {
                        onresize();
                        index = -1;
                    }
                    return nRow;
                }
                $scope.dtColumns = [
                    DTColumnBuilder.newColumn(null).withTitle('Select').withOption('name', 'group_name').withOption('sortable', false).renderWith(
                        function (data, type, full) {
                            if (full.group_name != null && !angular.equals(full.group_name, ''))
                                return '<input type="checkbox" class="checkbox" name=' + data.catalog_id + ' disabled>';
                            else {
                                return '<input type="checkbox" class="checkbox" ng-model="selectedObjects" name=' + data.catalog_id + ' ng-value=' + data.catalog_id + '>';
                            }
                        }),
                    DTColumnBuilder.newColumn('catalog_id').withTitle('Serial #').withOption('name', 'catalog_id'),
                    DTColumnBuilder.newColumn('model').withTitle('Model').withOption('name', 'model'),
                    DTColumnBuilder.newColumn('catalog_reference').withTitle('Machine Reference').withOption('name', 'catalog_reference'),
                    DTColumnBuilder.newColumn('customer_name').withTitle('Customer Name').withOption('name', 'customer_name'),
                    // DTColumnBuilder.newColumn('status').withTitle('Status').withOption('name', 'status'),
                    DTColumnBuilder.newColumn('group_name').withTitle('Group Name').withOption('name', 'group_name')
                ];
                $scope.dtInstance = {};
                $scope.dtInstanceCallback = function (dtInstance) {
                    $scope.dtInstance = dtInstance;
                    dtInstance.DataTable.on('draw.dt', function () {
                        let elements = angular.element("#" + dtInstance.id + " .ng-scope");
                        angular.forEach(elements, function (element) {
                            $compile(element)($scope);
                        });
                        onresize();
                    });
                };
            }

            $(document).on('click', '.paginate_button', function () {
                $scope.selectedObjects = [];
            });

            /**
             * Tasks to be done on load of controller
             */
            var onLoadTasks = function () {
                $scope.drawDataTable();
                onresize();
            }
            onLoadTasks();
        }
    ]);

machinemodule.factory('machineService', ['MACHINECONSTANTS',
    function (MACHINECONSTANTS) {
        var factory = {};
        return factory;
    }
]);

$(document).ready(function () {
    $("#search-panel").hide();
    $(document).off("click", "#open-filter").on("click", "#open-filter",
        function () {
            $("#search-panel").animate({
                height: 'toggle'
            });
        });
});