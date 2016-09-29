var homemodule = angular.module('tf.homemodule', ['tf.homemodule.dashboardmodule',
    'tf.homemodule.userassignmentmodule',
    'tf.homemodule.usermodule']);

homemodule.constant('HOMECONSTANTS', {
    CONFIG: {
        STATE: 'home',
        URL: '/home/{adminId:int}',
        CONTROLLER: 'HomeController',
        TEMPLATEURL: 'app/partials/home/home.html',
    },
    CONTROLLER: {}
});

homemodule.config(
    ['$stateProvider',
        'HOMECONSTANTS',
        function ($stateProvider, HOMECONSTANTS) {
            $stateProvider.state(HOMECONSTANTS.CONFIG.STATE, {
                url: HOMECONSTANTS.CONFIG.URL,
                templateUrl: HOMECONSTANTS.CONFIG.TEMPLATEURL,
                controller: HOMECONSTANTS.CONFIG.CONTROLLER,
                data: {
                    requireLogin: false
                }
            });
        }
    ]);

homemodule.controller('HomeController',
    ['$rootScope',
        '$scope',
        '$state',
        '$log',
        '$stateParams',
        'StorageService',
        'homeService',
        'HOMECONSTANTS',
        function ($rootScope, $scope, $state, $log, $stateParams, StorageService, homeService, HOMECONSTANTS) {
            // $stateParams.adminId = 2;
            if (angular.isDefined($stateParams.adminId) && $stateParams.adminId !== null) {
                var temp = StorageService.get('adminId');
                if (temp !== null) {
                    $rootScope.adminId = angular.copy(temp);
                    temp = null;
                    $log.info('current state is :' + $state.current.name);
                } else {
                    $rootScope.adminId = angular.copy($stateParams.adminId);
                    StorageService.set('adminId', $scope.adminId);
                    $stateParams.adminId = null;
                    $state.go('home.user.viewusers', null, { reolad: true });
                }
            } else {
                var temp = StorageService.get('adminId');
                if (temp !== null) {
                    $rootScope.adminId = angular.copy(temp);
                    temp = null;
                } else {
                    //TODO: INVALID SESSION
                }
            }

            /**
             * Logout function
             */
            $rootScope.logout = function () {
                $log.debug('logout function');
                StorageService.reset();
                $('.mb-control-close').parents(".message-box").removeClass("open");
            }
        }
    ]);

homemodule.factory('homeService', ['HOMECONSTANTS',
    function (HOMECONSTANTS) {
        var factory = {};
        return factory;
    }
]);
