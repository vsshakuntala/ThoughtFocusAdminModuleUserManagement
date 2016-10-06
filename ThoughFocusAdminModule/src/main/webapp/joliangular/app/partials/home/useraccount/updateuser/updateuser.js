var usermodule = angular.module('tf.homemodule.usermodule.updateuser', ['ngIntlTelInput', 'angularjs-dropdown-multiselect']);
usermodule.constant('UPDATEUSERCONSTANTS', {
    CONFIG: {
        STATE: 'home.user.updateuser',
        URL: '/updateuser',
        CONTROLLER: 'UpdateUserController',
        TEMPLATEURL: 'app/partials/home/useraccount/updateuser/updateuser.html',
    },
    CONTROLLER: {}
});
usermodule.config(
    ['$stateProvider',
        'UPDATEUSERCONSTANTS', '$urlRouterProvider', 'ngIntlTelInputProvider',
        function ($stateProvider, UPDATEUSERCONSTANTS, $urlRouterProvider, ngIntlTelInputProvider) {
            ngIntlTelInputProvider.set({ defaultCountry: 'us' });
            $stateProvider.state(UPDATEUSERCONSTANTS.CONFIG.STATE, {
                url: UPDATEUSERCONSTANTS.CONFIG.URL,
                templateUrl: UPDATEUSERCONSTANTS.CONFIG.TEMPLATEURL,
                controller: UPDATEUSERCONSTANTS.CONFIG.CONTROLLER,
                data: {
                    requireLogin: false
                }
            });
        }
    ]);
usermodule.controller('UpdateUserController',
    ['$scope',
        '$log',
        '$filter',
        '$rootScope',
        'updateUserService',
        'UPDATEUSERCONSTANTS',
        'genericService',
        '$state',
        function ($scope, $log, $filter, $rootScope, userService, UPDATEUSERCONSTANTS, genericService, $state) {
            // $scope.userId = $scope.userDetails.userId;
            // angular.element('#mydiv').hide() 
            $scope.loading = true;

            $scope.lang = "";
            $scope.user = {};
            //getAllLanguages will fetch the division name and division Id
            $scope.getAllLanguages = function () {
                genericService.getObjects($rootScope.baseUrl + 'usermgmtrest/languages').then(function (response) {
                    $scope.languageList = response;
                })
            };
            $scope.getAllLanguages();
            /**
            * Getting User to be updated
            */
            $scope.getUser = function () {
                genericService.getObjectById($rootScope.baseUrl + 'usermgmtrest/getuser/' + $scope.userDetails.userId + '/' + $rootScope.adminId).then(function (response) {
                    $log.debug('getUser :' + angular.toJson(response));
                    $scope.divisionList = response.userDivision;
                    //$scope.user.phoneNumber = $scope.user.phoneNumber.substr($scope.user.countryFlag.length, $scope.user.phoneNumber.length);
                    $scope.user.phone = response.phoneNumber;
                    $scope.user = response;
                    var obj = $filter('filter')($scope.languageList, { languageId: $scope.user.userLanguage }, true);
                    if (obj.length) {
                        $scope.userLanguage = obj[0];
                    }
                    // $log.info('number : ' + $("#phone").intlTelInput("setNumber", $scope.user.phoneNumber));
                    $scope.user.phoneNumber = $scope.user.phoneNumber;
                    genericService.getObjectById($rootScope.baseUrl + 'usermgmtrest/getactiveuser/' + $scope.userDetails.userId).then(function (response) {
                        $scope.userActiveOrg = response;
                        for (var i = 0; i < $scope.user.userDivision.length; i++) {
                            for (var j = 0; j < $scope.userActiveOrg.length; j++) {
                                if ($scope.user.userDivision[i].organizationId == $scope.userActiveOrg[j].organizationId) {
                                    $scope.user.userDivision[i].active = angular.copy($scope.userActiveOrg[j].active);
                                }
                            }
                        }
                        $scope.user.commonUserDivisions = [];
                        for (var i = 0; i < $scope.user.userDivision.length; i++) {
                            if ($scope.user.userDivision[i].active) {
                                $scope.user.commonUserDivisions.push($scope.user.userDivision[i]);
                            }
                        }
                        for (var i = 0; i < $scope.languageList.length; i++) {
                            if ($scope.languageList[i].languageId == $scope.user.userLanguage) {
                                $scope.lang = $scope.languageList[i].languageDescription;
                            }
                        }
                    });
                });
            }
            $scope.getUser();

            $scope.languageSelected = function (selected) {
                $scope.user.userLanguage = selected.languageId;
                delete $scope.updateUserForm.userLanguage.$error.required;
            }

            /**
             * UPDATING CODE GOES HERE -> API CALL
             */
            $scope.submitUpdateForm = function (user) {
                if ($scope.user.userLanguage) {
                    $log.debug("Update successfuly");
                    var countryData = $("#mobile").intlTelInput("getSelectedCountryData");
                    $scope.user.countryFlag = countryData.iso2;
                    //$scope.user.phoneNumber = $scope.user.phoneNumber.substr($scope.user.countryFlag.length, $scope.user.phoneNumber.length);
                    $log.info('user phoneNumber: ' + angular.toJson($scope.user.phoneNumber));
                    var aliasName = $scope.user.firstName + " " + $scope.user.lastName;
                    $scope.user.alias = aliasName;
                    user.currentLoggedUserId = $rootScope.adminId;
                    user.userDivision = user.commonUserDivisions.slice(0);
                    $rootScope.startSpin();
                    genericService.putObject($rootScope.baseUrl + 'usermgmtrest/updateuser', user).then(function (response) {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'success', message: response.message });
                        $('#close-modal').trigger('click');
                        $rootScope.stopSpin();
                    }, function (response) {
                        $rootScope.stopSpin();
                        $.toaster({ priority: 'danger', message: response.message });
                    });
                } else {
                    $scope.updateUserForm.userLanguage.$error.required = true;
                }
            }
            //function to get notes of the user
            $scope.getNotesList = function () {
                $scope.notes = [];
                genericService.getObjects($rootScope.baseUrl + 'divisionAssignment/getNotesOfUser/' + $scope.userDetails.userId).then(function (data) {
                    $scope.notes = data;
                    $log.debug("Getting notes:" + angular.toJson(data));
                }, function (data) {
                    // $.toaster({ priority: 'danger', message: data.message });
                });
            }
            $scope.getNotesList();
        }
    ]);
usermodule.factory('updateUserService', ['UPDATEUSERCONSTANTS',
    function (UPDATEUSERCONSTANTS) {
        var factory = {};
        return factory;
    }
]);
usermodule.directive('allowPattern', [allowPatternDirective]);
function allowPatternDirective() {
    return {
        restrict: "A",
        compile: function (tElement, tAttrs) {
            return function (scope, element, attrs) {
                element.bind("keypress", function (event) {
                    var keyCode = event.which || event.keyCode;
                    var keyCodeChar = String.fromCharCode(keyCode);
                    if (!keyCodeChar.match(new RegExp(attrs.allowPattern, "i"))) {
                        event.preventDefault();
                        return false;
                    }
                });
            };
        }
    };
}