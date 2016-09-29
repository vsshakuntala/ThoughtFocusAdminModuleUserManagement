var userassignmentmodule = angular.module('tf.homemodule.userassignmentmodule', [
    'tf.homemodule.userassignmentmodule.addressmodule',
    'tf.homemodule.userassignmentmodule.customermodule',
    'tf.homemodule.userassignmentmodule.divisionmodule',
    'tf.homemodule.userassignmentmodule.groupmodule',
    'tf.homemodule.userassignmentmodule.machinemodule',
    'tf.homemodule.userassignmentmodule.rolemodule',
]);

userassignmentmodule.constant('USERASSIGNMENTCONSTANTS', {
    CONFIG: {
        STATE: 'home.userassignment',
        URL: '/userassignment',
        CONTROLLER: 'UserAssignmentController',
        TEMPLATEURL: 'app/partials/home/userassignment/userassignment.html',
    },
    CONTROLLER: {}
});

userassignmentmodule.config(
    ['$stateProvider',
        'USERASSIGNMENTCONSTANTS',
        function ($stateProvider, USERASSIGNMENTCONSTANTS) {
            $stateProvider.state(USERASSIGNMENTCONSTANTS.CONFIG.STATE, {
                url: USERASSIGNMENTCONSTANTS.CONFIG.URL,
                templateUrl: USERASSIGNMENTCONSTANTS.CONFIG.TEMPLATEURL,
                controller: USERASSIGNMENTCONSTANTS.CONFIG.CONTROLLER,
                data: {
                    requireLogin: false
                }
            });
        }
    ]);

userassignmentmodule.controller('UserAssignmentController',
    ['$scope',
    '$log',
        'userAssignmentService',
        'USERASSIGNMENTCONSTANTS',
        function ($scope, $log, userAssignmentService, USERASSIGNMENTCONSTANTS) {

        }
    ]);

userassignmentmodule.factory('userAssignmentService', ['USERASSIGNMENTCONSTANTS',
    function (USERASSIGNMENTCONSTANTS) {
        var factory = {};
        return factory;
    }
]);
