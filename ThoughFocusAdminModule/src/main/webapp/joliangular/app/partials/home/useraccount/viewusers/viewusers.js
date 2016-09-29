var usermodule = angular.module('tf.homemodule.usermodule.viewusers', ['datatables']);

usermodule.constant('VIEWUSERCONSTANTS', {
    CONFIG: {
        STATE: 'home.user.viewusers',
        URL: '/viewusers',
        CONTROLLER: 'ViewUsersController',
        TEMPLATEURL: 'app/partials/home/useraccount/viewusers/viewusers.html',
    },
    CONTROLLER: {}
});

usermodule.config(
    ['$stateProvider',
        'VIEWUSERCONSTANTS',
        function ($stateProvider, VIEWUSERCONSTANTS) {
            $stateProvider.state(VIEWUSERCONSTANTS.CONFIG.STATE, {
                url: VIEWUSERCONSTANTS.CONFIG.URL,
                templateUrl: VIEWUSERCONSTANTS.CONFIG.TEMPLATEURL,
                controller: VIEWUSERCONSTANTS.CONFIG.CONTROLLER,
                data: {
                    requireLogin: false
                },
                resolve: {
                    divisionList: function ($rootScope, genericService) {
                        return genericService.getObjects($rootScope.baseUrl + 'userlists/getalldivisions');
                    }
                }
            });
        }
    ]);

usermodule.controller('ViewUsersController',
    ['$scope',
        '$state',
        '$rootScope',
        '$log',
        '$compile',
        '$filter',
        'divisionList',
        'DTOptionsBuilder',
        'DTColumnBuilder',
        'genericService',
        'applicationUtilityService',
        'viewUsersService',
        'VIEWUSERCONSTANTS',
        function ($scope, $state, $rootScope, $log, $compile, $filter, divisionList, DTOptionsBuilder,
            DTColumnBuilder, genericService, applicationUtilityService,
            viewUsersService, VIEWUSERCONSTANTS) {
            $scope.totalrows = 0;
            $scope.angular = angular;
            $scope.dataInTable = [];
            $scope.statuses = [{ status: 'Pending' }, { status: 'Approved' }, { status: 'Deleted' }];
            $scope.filteredObjects = {
                divisions: [],
                roles: [],
                status: ['Pending'],
                companyName: '',
                name: '',
                from_date: '',
                to_date: ''
            };
            $scope.searchValue = '';

            /**
             * Start of datepicker related configuration and settings
             */
            $scope.format = 'dd-MMM-yyyy';
            $scope.dateOptions = {
                fromDate: {
                    appendToBody: true,
                    startingDay: 1,
                    showWeeks: false
                },
                toDate: {
                    appendToBody: true,
                    startingDay: 1,
                    showWeeks: false
                }
            };
            $scope.fromDateopened = false;
            $scope.toDateopened = false;
            $scope.open = function (type) {
                if (angular.equals(type, 'fromDate')) {
                    $scope.fromDateopened = true;
                } else {
                    $scope.toDateopened = true;
                }
            };

            /**
             * To get flags based on contact number.
             */
            $scope.getflagByCountryCode = function (userId) {
                if (userId !== null) {
                    var obj = $filter('filter')($scope.dataInTable, { userId: userId }, true);
                    if (obj.length) {
                        return obj[0].countryFlag;
                    }
                    return;
                }
            }
            
            $scope.drawCustomeFilteredDataTable = function(){
                var obj = angular.copy($scope.filteredObjects);
                obj.search = {};
                obj.search.value = $scope.searchValue;
                $scope.filteredObjects = angular.copy(obj);
                console.log(angular.toJson($scope.filteredObjects));
                $scope.drawDataTable();
            }

            /**
            * End of datepicker related configuration and settings
            */
            $scope.drawDataTable = function () {
                $scope.totalrows = 0;
                $scope.dtOptions = DTOptionsBuilder.newOptions()
                    .withOption('ajax', {
                        url: $rootScope.baseUrl + 'userlists/getfiltereduserlist',
                        type: 'GET',
                        data: applicationUtilityService.buildDataObjectForDataTable($scope.filteredObjects)
                    })
                    .withDataProp('data')
                    .withOption('processing', true)
                    .withOption('oLanguage',{sProcessing:'<img src="app-content/img/blueimp/loading.gif" alt="nothing"/>'})
                    .withOption('serverSide', true)
                    .withOption('searching', false)
                    .withOption('bDestory', true)
                    .withPaginationType('full_numbers')
                    .withOption('bFilter', true)
                    .withOption('order', [3, 'desc'])
                    .withOption('rowCallback', rowCallback)
                    .withOption('createdRow', function (row, data, dataIndex) {
                        // Recompiling so we can bind Angular directive to the DT
                        $compile(angular.element(row).contents());
                    });

                function rowCallback(nRow, aData, iDisplayIndex, iDisplayIndexFull) {
                    $scope.totalrows = this.fnSettings().fnRecordsTotal();
                    $scope.$apply();
                    $('td', nRow).unbind('click');
                    $('td', nRow).bind('click', function () {
                        $scope.$apply(function () {
                            var obj = {
                                userId: aData.userId,
                                userName: aData.userName,
                                userLastActiveDate: aData.last_date,
                                userEmail: aData.email
                            }
                            $log.debug('division : ' + angular.toJson(obj));
                            $state.go('home.userassignment.division', { userDetails: obj }, { reload: true });
                        });
                    });
                    return nRow;
                }
                $scope.dtColumns = [
                    DTColumnBuilder.newColumn('userName').withTitle('User Name').withOption('name', 'userName'),
                    DTColumnBuilder.newColumn('firstName').withTitle('First Name').withOption('name', 'firstName').renderWith(function (data, type, full) {
                        if (!full.firstName)
                            full.firstName = '--';
                        return full.firstName;
                    }),
                    DTColumnBuilder.newColumn('lastName').withTitle('Last Name').withOption('name', 'lastName').renderWith(function (data, type, full) {
                        if (!full.lastName)
                            full.lastName = '--';
                        return full.lastName;
                    }),
                    DTColumnBuilder.newColumn('createdDate').withTitle('Registered Date').withOption('name', 'createdDate').renderWith(function (data, type, full) {
                        if(data===null){
                        return null;
                        }
                        var date = new Date(data);
                        var month = date.getMonth() + 1;
                        var monthNames = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                            'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'
                        ];
                        return date.getDate() + ' ' + monthNames[date.getMonth()] + ' ' + date.getFullYear();
                    }),
                    DTColumnBuilder.newColumn('phoneNumber').withTitle('Phone').withOption('name', 'phoneNumber').renderWith(function (data, type, full) {
                        //return full.phoneNumber;
                        if (!full.phoneNumber)
                            full.phoneNumber = '--';
                        var flag = '' + full.countryFlag;
                        return '<td><span class="iti-flag-custom" ng-class="getflagByCountryCode(' + full.userId + ')"></span>' + full.phoneNumber + '</td>';
                    }),
                    DTColumnBuilder.newColumn('companyName').withTitle('Company Name').withOption('name', 'companyName'),
                    DTColumnBuilder.newColumn('null').withTitle('Status').withOption('name', 'null').withOption('sortable', false).renderWith(function (data, type, full) {
                        if (full.Approved == 0 && full.Pending == 0) {
                            return 'DELETED';
                        }
                        if (full.active === false) {
                            return 'DELETED';
                        }
                        else {
                            if (full.Pending == 0)
                                return 'APPROVED';
                            else
                                return 'PENDING';
                        }
                    }),
                    DTColumnBuilder.newColumn('Approved').withTitle('Approved').withOption('name', 'Approved').renderWith(function (data, type, full) {
                        if (full.Approved === 0 && full.Pending === 0) {
                            return '<td><span class="label label-danger custom-style">' + full.Approved + '</span></td>';

                        }
                        else if (full.Approved === 0 && full.Pending > 0) {
                            return '<td><span class="label custom-style" style="color:black">' + full.Approved + '</span></td>';
                        }
                        else if (full.Approved > 0) {
                            return '<td><span class="label label-success custom-style">' + full.Approved + '</span></td>';
                        }

                    }),

                    DTColumnBuilder.newColumn('Pending').withTitle('Pending').withOption('name', 'Pending').renderWith(function (data, type, full) {
                        if (full.Pending === 0 && full.Approved === 0) {
                            return '<td><span class="label label-danger custom-style">' + full.Pending + '</span></td>';

                        }
                        else if (full.Pending > 0) {
                            return '<td><span class="label pendingSpan custom-style">' + full.Pending + '</span></td>';
                        }
                        else if (full.Approved > 0 && full.Pending === 0) {
                            return '<td><span class="label custom-style" style="color:black">' + full.Pending + '</span></td>';

                        }
                    })
                ];

                $scope.dtInstanceCallback = function (dtInstance) {
                    $scope.dtInstance = dtInstance;
                    dtInstance.DataTable.on('draw.dt', function () {
                        $scope.dataInTable = [];
                        for (var i = 0; i < dtInstance.DataTable.context[0].aoData.length; i++) {
                            $scope.dataInTable.push(dtInstance.DataTable.context[0].aoData[i]._aData);
                        }
                        let elements = angular.element("#" + dtInstance.id + " .ng-scope");
                        angular.forEach(elements, function (element) {
                            $compile(element)($scope);
                        });
                        onresize();
                    });
                }
            }

            /**
             * function to get all divisions which was loaded before controller loaded
             * SEE CONFIG SECTION THIS MODULE
             */
            var getDivisions = function () {
                $scope.divisions = divisionList;
                $log.debug('division list: ' + angular.toJson($scope.divisions))
                $scope.filteredObjects.divisions = applicationUtilityService.checkAllObjectsInList($scope.divisions, 'organizationId');
                $scope.drawDataTable();
            };

            /**
             * function to get all roles
             */
            var getRoles = function () {
                genericService.getObjects($rootScope.baseUrl + 'userlists/getallroles').then(function (data) {
                    $log.debug('roles list: ' + angular.toJson(data));
                    $scope.roles = data;
                }, function () {
                    $log.debug('getallroles failed: ' + angular.toJson(data));
                });
            };


            $scope.download = function () {
                var url = $rootScope.baseUrl + 'userlists/downloaddocument/';
                var data = applicationUtilityService.buildDataObjectForDataTable($scope.filteredObjects);
                genericService.downloadFile(url, data).then(function () {
                    $log.debug('file download ');
                }, function () {
                    $log.debug('file download failed');
                });
            };

            /**
             * Tasks to be done on load of controller
             */
            var onLoadTasks = function () {
                getDivisions();
                getRoles();
                onresize();
            }
            onLoadTasks();
        }
    ]);

usermodule.factory('viewUsersService', ['VIEWUSERCONSTANTS',
    function (VIEWUSERCONSTANTS) {
        var factory = {};
        return factory;
    }
]);
