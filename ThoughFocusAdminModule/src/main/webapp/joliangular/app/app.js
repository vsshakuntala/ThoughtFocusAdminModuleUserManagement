/**
 * This is main applicatio  module which is root module.
 *
 * @author Preetham G R
 *
 * @CopyRights Reserved with Spaneos Software Solutions Pvt. Ltd.
 *
 * All of the code within the Spaneos Software Solutions is developed and copyrighted
 * by Spaneos Software Solutions Pvt. Ltd, and may not be copied,replicated, or used in any
 * other software or application without prior permission from Spaneos Software Solutions Pvt. Ltd
 * All usage must coincide with the Spaneos Software Solutions pvt ltd End User License Agreement.
 */

var tf = angular.module('tf',
    ['ui.router',
        'GenericServiceModule',
        'applicationutility',
        'ngSanitize',
        'ui.select',
        'checklist-model',
        'mystorage',
        'angularSpinner',
        'datatables',
        'ui.bootstrap',
        'tf.loginmodule',
        'tf.homemodule'
    ]);
/**
 * Application level Constants
 */
tf.constant('APPLICATIONCONSTANTS', {
    RUN: {},
    CONFIG: {
        HOME_PAGE: '/login'
    }
});
/**
 * used to run some custom function when some event is triggered
 */
tf.run(
    ['$rootScope',
        '$state',
        'StorageService',
        '$log',
        'usSpinnerService',
        'genericService',
        'APPLICATIONCONSTANTS',
        function ($rootScope, $state, StorageService, $log, usSpinnerService, genericService, APPLICATIONCONSTANTS) {
            /**
             * Base url for API call should be configured here.
             */
            StorageService.set('baseurl', '../');

            $rootScope.baseUrl = '../';

            $rootScope.$on('$stateChangeStart', function (e, toState, toParams, fromState, fromParams) {
                if (toState.name === 'home.user.viewusers') {
                    StorageService.remove('userDetails');
                }
            });
            
            genericService.getObjects('../' + 'usermgmtrest/getuseremailbyid/'+0).then(function (data) {
               $log.debug('getEmailbyId: ' + angular.toJson(data));
               $rootScope.adminEmail = data['emailId'];
           }, function () {
               $log.debug('getEmailbyId failed: ' + angular.toJson(data));
           });

            $rootScope.startSpin = function () {
                if (!$rootScope.spinnerActive) {
                    $rootScope.spinnerActive = true;
                    usSpinnerService.spin('spinner-1');
                }
            }
            $rootScope.stopSpin = function () {
                if ($rootScope.spinnerActive) {
                    $rootScope.spinnerActive = false;
                    usSpinnerService.stop('spinner-1');
                }
            }

            onresize();

        }]);

tf.directive('convertToNumber', function () {
    return {
        require: 'ngModel',
        link: function (scope, element, attrs, ngModel) {
            ngModel.$parsers.push(function (val) {
                return parseInt(val, 10);
            });
            ngModel.$formatters.push(function (val) {
                return '' + val;
            });
        }
    };
});

tf.config(
    ['$stateProvider',
        '$urlRouterProvider',
        '$provide',
        'APPLICATIONCONSTANTS',
        function ($stateProvider, $urlRouterProvider, $provide, APPLICATIONCONSTANTS) {

            var IN_DEVELOPMENT = true;

            $provide.decorator('$log', ['$delegate', function ($delegate) {
                // Keep track of the original debug method, we'll need it later.
                var originals = {};
                var methods = ['info', 'debug', 'warn', 'error'];

                angular.forEach(methods, function (method) {
                    originals[method] = $delegate[method];
                    /*
                     * Intercept the call to $log.debug() so we can add on 
                     * our enhancement. We're going to add on a date and 
                     * time stamp to the message that will be logged.
                     */
                    $delegate[method] = function () {
                        if (IN_DEVELOPMENT) {
                            var args = [].slice.call(arguments);
                            var timestamp = new Date().toString();
                            args[0] = [timestamp.substring(4, 24), ': ', args[0]].join('');
                            // Send on our enhanced message to the original debug method.
                            originals[method].apply(null, args);
                        }
                    };
                });
                return $delegate;
            }]);
            //forward to default login page
            $urlRouterProvider.otherwise(APPLICATIONCONSTANTS.CONFIG.HOME_PAGE);
        }]);
