var homemodule = angular.module('tf.homemodule', ['tf.homemodule.dashboardmodule',
    'tf.homemodule.userassignmentmodule',
    'tf.homemodule.usermodule']);

homemodule.constant('HOMECONSTANTS', {
    CONFIG: {
        STATE: 'home',
        URL: '/home',
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
        'HOMECONSTANTS','genericService',
        function ($rootScope, $scope, $state, $log, $stateParams, StorageService, homeService, HOMECONSTANTS,genericService) {
            // $stateParams.adminId = 2;

	    	$rootScope.adminId = 0;
	        //StorageService.set('adminId', $scope.adminId);	
            /*if (angular.isDefined($stateParams.adminId) && $stateParams.adminId !== null) {
                var temp = StorageService.get('adminId');
                if (temp !== null) {
                    $log.info('current state is :' + $state.current.name);
                    if (angular.equals($state.current.name, 'home')) {
                        $rootScope.adminId = angular.copy($stateParams.adminId);
                        StorageService.set('adminId', $scope.adminId);
                        $state.go('home.user.viewusers', null, { reolad: true });
                    }
                    $rootScope.adminId = angular.copy(temp);
                    getEmailbyId();
                    temp = null;
                } else {
                    
                    $stateParams.adminId = null;
                    getEmailbyId();
                    $state.go('home.user.viewusers', null, { reolad: true });
                }
            } else {
                var temp = StorageService.get('adminId');
                if (temp !== null) {
                    $rootScope.adminId = angular.copy(temp);
                    temp = null;
                    getEmailbyId();
                } else {
                    //TODO: INVALID SESSION
                }
            }*/

            /*function getEmailbyId(){
                 genericService.getObjects('../' + 'usermgmtrest/getuseremailbyid/'+$rootScope.adminId).then(function (data) {
                    $log.debug('getEmailbyId: ' + angular.toJson(data));
                    $rootScope.adminEmail = data['emailId'];
                }, function () {
                    $log.debug('getEmailbyId failed: ' + angular.toJson(data));
                });
            }*/
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
