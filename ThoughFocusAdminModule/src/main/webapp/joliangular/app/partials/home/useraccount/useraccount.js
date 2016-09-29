var usermodule = angular.module('tf.homemodule.usermodule', [
    'tf.homemodule.usermodule.viewusers',
    'tf.homemodule.usermodule.createuser',
    'tf.homemodule.usermodule.updateuser'
]);

usermodule.constant('USERCONSTANTS', {
    CONFIG: {
        STATE: 'home.user',
        URL: '/user',
        CONTROLLER: 'UserAccountController',
        TEMPLATEURL: 'app/partials/home/useraccount/useraccount.html',
    },
    CONTROLLER: {}
});

usermodule.config(
    ['$stateProvider',
        'USERCONSTANTS',
        function ($stateProvider, USERCONSTANTS) {
            $stateProvider.state(USERCONSTANTS.CONFIG.STATE, {
                url: USERCONSTANTS.CONFIG.URL,
                templateUrl: USERCONSTANTS.CONFIG.TEMPLATEURL,
                controller: USERCONSTANTS.CONFIG.CONTROLLER,
                data: {
                    requireLogin: false
                }
            });
        }
    ]);

usermodule.controller('UserAccountController',
    ['$scope',
        '$log',
        'userAccountService',
        'USERCONSTANTS',
        function ($scope, $log, userAccountService, USERCONSTANTS) {
        }
    ]);

usermodule.factory('userAccountService', ['USERCONSTANTS',
    function (USERCONSTANTS) {
        var factory = {};
        return factory;
    }
]);
