var customermodule = angular.module('tf.homemodule.userassignmentmodule.customermodule', []);

customermodule.constant('CUSTOMERCONSTANTS', {
    CONFIG: {
        STATE: 'home.userassignment.customer',
        URL: '/customer',
        CONTROLLER: 'CustomerController',
        TEMPLATEURL: 'app/partials/home/userassignment/customer/customer.html',
    },
    CONTROLLER: {}
});

customermodule.config(
    ['$stateProvider',
        'CUSTOMERCONSTANTS',
        function ($stateProvider, CUSTOMERCONSTANTS) {
            $stateProvider.state(CUSTOMERCONSTANTS.CONFIG.STATE, {
                url: CUSTOMERCONSTANTS.CONFIG.URL,
                templateUrl: CUSTOMERCONSTANTS.CONFIG.TEMPLATEURL,
                controller: CUSTOMERCONSTANTS.CONFIG.CONTROLLER,
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

customermodule.controller('CustomerController',
    ['$scope',
        '$log',
        '$stateParams',
        'StorageService',
        'customerService',
        'CUSTOMERCONSTANTS', 'DTOptionsBuilder', 'DTColumnBuilder', '$rootScope', 'applicationUtilityService', '$compile', 'genericService',
        function ($scope, $log, $stateParams, StorageService, customerService, CUSTOMERCONSTANTS, DTOptionsBuilder, DTColumnBuilder, $rootScope, applicationUtilityService, $compile, genericService) {
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
                var temp = StorageService.get('oraganization');
                if (temp !== null) {
                    $scope.organization = angular.copy(temp);
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
            $scope.filteredObjectsCustomer = {
                userId: $scope.userDetails.userId,
                orgId: $scope.organization.organizationId,
                customerName: '',
                customerNumber: '',
                cBill: '',
                addressOne: '',
                cCity: '',
                cState: '',
                cPostal: '',
                status: $scope.statuses[0].status,
            };
            $scope.searchValue = '';
            
            
            $scope.totalrows = 0;
            $scope.selectedObjects = [];
            $scope.download = function () {
                $rootScope.startSpin();
                if (!angular.isUndefined($scope.filteredObjectsCustomer) && !angular.isUndefined($scope.filteredObjectsCustomer.status) && !angular.equals($scope.totalrows, 0) && !angular.isUndefined($scope.totalrows)) {
                    var url = $rootScope.baseUrl + 'downloaddocument/' + $scope.filteredObjectsCustomer.userId + '/' + $scope.filteredObjectsCustomer.orgId + '/' + $scope.logedIn_user_id;
                    var data = applicationUtilityService.buildDataObjectForDataTable($scope.downloadfilteredCustomer);
                    genericService.downloadFile(url, data).then(function () {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'success', message: downloadSuccesfull });
                    }, function () {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'danger', message: downloadFailed });
                    });
                }
                else {
                    $rootScope.stopSpin();
                   $.toaster({ priority: 'info', message: noDataToDownload });
                }
            };
            /**
            * 
            * Assign and Assignall function
            */
            $scope.assign = function (type) {
                $log.debug("selected obj :"+$scope.selectedObjects)
                var assignUtil = function (url, jsonData) {
                    $rootScope.startSpin();
                    genericService.addObject(url, jsonData).then(function (data) {
                        $('#assign-all-close').trigger('click');
                        $('#assign-close').trigger('click');
                        $.toaster({ priority: 'success', message: assignCustomerFromUnAssignedListSuccess });
                        $rootScope.stopSpin();
                        $scope.reloadDataTable();
                    }, function () {
                        $('#assign-all-close').trigger('click');
                        $('#assign-close').trigger('click');
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'danger', message: assignCustomerFromUnAssignedListFailed });
                    });
                }
                if (angular.equals($scope.filteredObjectsCustomer.status, 'notassigned') && angular.equals(type, 'all') && !angular.equals($scope.totalrows, 0)) {
                    var url = $rootScope.baseUrl + 'assignallcustomer/' + $scope.filteredObjectsCustomer.userId + '/' + $scope.filteredObjectsCustomer.orgId + '/' + $scope.logedIn_user_id;
                    var data = JSON.stringify($scope.filteredObjectsCustomer);
                    $('#assign-all-close').trigger('click');
                    assignUtil(url, data);

                } else if (angular.equals($scope.filteredObjectsCustomer.status, 'notassigned') && !angular.equals(type, 'all')) {
                    if ($scope.selectedObjects.length) {
                        var url = $rootScope.baseUrl + 'assigncustomer/' + $scope.filteredObjectsCustomer.userId + '/' + $scope.filteredObjectsCustomer.orgId + '/' + $scope.logedIn_user_id;
                        var data = {};
                        data.custList = $scope.selectedObjects;
                        $('#assign-close').trigger('click');
                        assignUtil(url, data);
                    } else {
                        $('#assign-close').trigger('click');
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'danger', message: selectatleastoneCustomerToAssign });
                    }
                } else if (angular.equals($scope.totalrows, 0) && angular.equals($scope.filteredObjectsCustomer.status, 'notassigned')) {
                    $('#assign-all-close').trigger('click');
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotAssigncustomerFromEmptyList });
                } else if (angular.equals($scope.filteredObjectsCustomer.status, 'assigned')) {
                    $('#assign-all-close').trigger('click');
                    $('#assign-close').trigger('click');
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotAssignCustomerFromAssignedList });
                }
            }/**
             * 
             * Remove and Removeall function
             */
            $scope.remove = function (type) {
                $log.debug("selected obj :"+$scope.selectedObjects)
                $rootScope.startSpin();
                var removeUtil = function (url, jsonData) {
                    genericService.addObject(url, jsonData).then(function (data) {
                        $('#remove-close').trigger('click');
                        $('#remove-all-close').trigger('click');
                        $.toaster({ priority: 'success', message: removeCustomerFromAssignedListSuccess });
                         $rootScope.stopSpin();
                        $scope.reloadDataTable();
                    }, function () {
                        $('#remove-close').trigger('click');
                        $('#remove-all-close').trigger('click');
                         $rootScope.stopSpin();
                        $.toaster({ priority: 'danger', message: removeCustomerFromAssignedListFailed });
                    });
                }
                if (angular.equals($scope.filteredObjectsCustomer.status, 'assigned') && angular.equals(type, 'all') && !angular.equals($scope.totalrows, 0)) {
                    var url = $rootScope.baseUrl + 'removeallcustomer/' + $scope.filteredObjectsCustomer.userId + '/' + $scope.filteredObjectsCustomer.orgId + '/' + $scope.logedIn_user_id;
                    var data = JSON.stringify($scope.filteredObjectsCustomer);
                    $('#remove-all-close').trigger('click');
                    removeUtil(url, data);
                } else if (angular.equals($scope.filteredObjectsCustomer.status, 'assigned') && !angular.equals(type, 'all')) {
                    if ($scope.selectedObjects.length) {
                        var url = $rootScope.baseUrl + 'removecustomer/' + $scope.filteredObjectsCustomer.userId + '/' + $scope.filteredObjectsCustomer.orgId + '/' + $scope.logedIn_user_id;
                        var data = {};
                        data.custList = $scope.selectedObjects;
                        $('#remove-close').trigger('click');
                        removeUtil(url, data);
                    } else {
                        $('#remove-close').trigger('click');
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'danger', message: selectatleastoneCustomerToRemove });

                    }
                } else if (angular.equals($scope.totalrows, 0) && angular.equals($scope.filteredObjectsCustomer.status, 'assigned')) {
                    $('#remove-all-close').trigger('click');
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotRemovecustomerFromEmptyList });
                } else if (angular.equals($scope.filteredObjectsCustomer.status, 'notassigned')) {
                    $('#remove-close').trigger('click');
                    $('#remove-all-close').trigger('click');
                    $rootScope.stopSpin();
                    $.toaster({ priority: 'info', message: cannotRemoveCustomerFromUnAssignedList });
                }
            }

            allCustomerCount();
            function allCustomerCount() {
                genericService.getObjects('../getCustomerCount/' + $scope.filteredObjectsCustomer.userId + '/' + $scope.filteredObjectsCustomer.orgId).then(function (data) {
                    console.log('In count api :' + angular.toJson(data));
                    if (data != null) {
                        $scope.count = data;
                    }
                }, function (data) {
                    console.log('In count api fail:' + data);
                });
            }
            var getUrl = function () {
                if (angular.equals($scope.filteredObjectsCustomer.status, 'assigned')) {
                    return $rootScope.baseUrl + 'customerAssignmentReport';
                } else {
                    return $rootScope.baseUrl + 'customerUnAssignmentReport';
                }
            }
            
            $scope.drawCustomeFilteredDataTable = function(){
                var obj = angular.copy($scope.filteredObjectsCustomer);
                obj.search = {};
                obj.search.value = $scope.searchValue;
                $scope.filteredObjectsCustomer = angular.copy(obj);
                console.log(angular.toJson($scope.filteredObjectsCustomer));
                $scope.drawDataTable();
            }

            $scope.dtInstance = {};
            $scope.reloadDataTable = function () {
                $scope.dtInstance.rerender();
                allCustomerCount();
                 $scope.selectedObjects = [];
                $scope.totalrows = 0;
            }

            function objectForDownload(){
                $scope.downloadfilteredCustomer = {
                userId: $scope.filteredObjectsCustomer.userId,
                orgId: $scope.filteredObjectsCustomer.orgId,
                customername: $scope.filteredObjectsCustomer.customerName,
                customernum: $scope.filteredObjectsCustomer.customerNumber,
                cBilbilltonuml: $scope.filteredObjectsCustomer.cBill,
                addressone:$scope.filteredObjectsCustomer.addressOne,
                city: $scope.filteredObjectsCustomer.cCity,
                state: $scope.filteredObjectsCustomer.cState,
                postal: $scope.filteredObjectsCustomer.cPostal,
                status: $scope.filteredObjectsCustomer.status,

            };
            }
            $scope.drawDataTable = function () {
                objectForDownload();
                var index = -1;
                $scope.totalrows = 0;
                $scope.dtOptions = DTOptionsBuilder.newOptions()
                    .withOption('ajax', {
                        url: getUrl(),
                        type: 'GET',
                        data: applicationUtilityService.buildDataObjectForDataTable($scope.filteredObjectsCustomer)
                    })
                    .withDataProp('data')
                    .withOption('processing', true)
                    .withOption('serverSide', true)
                    .withOption('oLanguage',{sProcessing:'<img src="app-content/img/blueimp/loading.gif" alt="nothing"/>'})
                    .withOption('searching', false)
                    .withOption('bDestory', true)
                    .withPaginationType('full_numbers')
                    .withOption('bFilter', true)
                    .withOption('order', [8, 'asc'])
                    .withOption('rowCallback', rowCallback)
                    .withOption('createdRow', function (row, data, dataIndex) {
                        index++;
                        // Recompiling so we can bind Angular directive to the DT
                        $compile(angular.element(row).contents());
                    });
                function rowCallback(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                    $scope.totalrows = this.fnSettings().fnRecordsTotal();
                    $scope.$apply();
                    $('td:first-child>input', nRow).unbind('click');
                    $('td:first-child>input', nRow).bind('click', function () {
                        $scope.$apply(function () {
                            $scope.selectedObjects = applicationUtilityService.addUniqueObjectToList($scope.selectedObjects, aData.customerNumber);
                        });
                    });
                    if (iDisplayIndex === index) {
                        onresize();
                        index = -1;
                    }
                    return nRow;
                }
                $scope.dtColumns = [
                    DTColumnBuilder.newColumn(null).withTitle('Select').withOption().notSortable()
                        .renderWith(function (data, type, full, meta) {
                            if (!angular.isUndefined(data.Type) && data.Type != '')

                                return '<input type="checkbox" class="checkbox" disabled>';
                            else
                                return '<input type="checkbox" class="checkbox" ng-model="selectedObjects" ng-value=' + data.customerNumber + '>';
                        }),
                    DTColumnBuilder.newColumn('customerNumber').withTitle('Customer #').withOption('name', 'customerNumber'),
                    DTColumnBuilder.newColumn('customerName').withTitle('Customer Name').withOption('name', 'customerName'),
                    DTColumnBuilder.newColumn('addressOne').withTitle('Address1').withOption('name', 'addressOne'),
                    DTColumnBuilder.newColumn('city').withTitle('City').withOption('name', 'cCity'),
                    DTColumnBuilder.newColumn('state').withTitle('State').withOption('name', 'cState'),
                    DTColumnBuilder.newColumn('postal').withTitle('Postal code').withOption('name', 'cPostal'),
                    DTColumnBuilder.newColumn('country').withTitle('Country').withOption('name', 'country'),
                    //DTColumnBuilder.newColumn('Status').withTitle('Status').withOption('name', 'Status').notSortable(),
                    DTColumnBuilder.newColumn('Type').withTitle('Group Name').withOption('name', 'Type')
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

customermodule.factory('customerService', ['CUSTOMERCONSTANTS',
    function (CUSTOMERCONSTANTS) {
        var factory = {};
        return factory;
    }
]);
/*
$(document).ready(function () {
    $("#search-panel").hide();
    $(document).off("click", "#open-filter").on("click", "#open-filter",
        function () {
            $("#search-panel").animate({
                height: 'toggle'
            });
        });
});*/
