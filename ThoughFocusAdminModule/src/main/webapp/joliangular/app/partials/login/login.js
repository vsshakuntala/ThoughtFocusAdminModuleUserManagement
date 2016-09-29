var loginmodule = angular.module('tf.loginmodule', []);

loginmodule.constant('LOGINCONSTANTS', {
    CONFIG: {
        STATE: 'login',
        URL: '/login',
        CONTROLLER: 'LoginController',
        TEMPLATEURL: 'app/partials/login/login.html',
    },
    CONTROLLER: {}
});

loginmodule.config(
    ['$stateProvider',
        'LOGINCONSTANTS',
        function ($stateProvider, LOGINCONSTANTS) {
            $stateProvider.state(LOGINCONSTANTS.CONFIG.STATE, {
                url: LOGINCONSTANTS.CONFIG.URL,
                templateUrl: LOGINCONSTANTS.CONFIG.TEMPLATEURL,
                controller: LOGINCONSTANTS.CONFIG.CONTROLLER,
                data: {
                    requireLogin: false
                }
            });
        }
    ]);

loginmodule.controller('LoginController',
    ['$scope',
        '$state',
        '$log',
        'loginService',
        'LOGINCONSTANTS',
        function ($scope, $state, $log, loginService, LOGINCONSTANTS) {

            $scope.login = function () {
                $state.go('home.dashboard', {}, { reload: true });
            }
        }
    ]);

loginmodule.factory('loginService', ['LOGINCONSTANTS',
    function (LOGINCONSTANTS) {
        var factory = {};
        return factory;
    }
]);
