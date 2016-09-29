var usermodule = angular.module('tf.homemodule.usermodule.createuser', [
    'ngIntlTelInput',
    'angularjs-dropdown-multiselect']);
usermodule.constant('CREATEUSERCONSTANTS', {
    CONFIG: {
        STATE: 'home.user.createuser',
        URL: '/createuser',
        CONTROLLER: 'CreateUserController',
        TEMPLATEURL: 'app/partials/home/useraccount/createuser/createuser.html',
    },
    CONTROLLER: {
    }
});
usermodule.config(
    ['$stateProvider',
        'CREATEUSERCONSTANTS', 'ngIntlTelInputProvider',
        function ($stateProvider, CREATEUSERCONSTANTS, ngIntlTelInputProvider) {
            ngIntlTelInputProvider.set({ defaultCountry: 'us', separateDialCode: true, preferredCountries: ["us", "gb"] });
            $stateProvider.state(CREATEUSERCONSTANTS.CONFIG.STATE, {
                url: CREATEUSERCONSTANTS.CONFIG.URL,
                templateUrl: CREATEUSERCONSTANTS.CONFIG.TEMPLATEURL,
                controller: CREATEUSERCONSTANTS.CONFIG.CONTROLLER,
                data: {
                    requireLogin: false
                }
            });
        }
    ]);
usermodule.controller('CreateUserController',
    ['$scope',
        '$rootScope', '$state',
        '$log',
        'createUserService',
        'genericService',
        'CREATEUSERCONSTANTS',
        function ($scope, $rootScope, $state, $log, createUserService, genericService, CREATEUSERCONSTANTS) {
            $log.debug('In create controller');
            $scope.loading = true;
            $scope.user = {};
            $scope.createUserForm = {};
            $scope.user.userDivision = [];
            $scope.userLanguage = {};
            $scope.user.userLanguage = 0;

            $(document).on('hidden.bs.modal', '#modal_large', function () {
                $state.reload();
            });
            /*EXTRA-SETTINGS*/
            $scope.userDivision = {
                smartButtonMaxItems: 2,
                smartButtonTextConverter: function (itemText, originalItem) {
                    return itemText;
                },
                enableSearch: true,
                scrollable: true,
                idProp: 'organizationId',
                displayProp: 'organizationName',
                externalIdProp: 'organizationId',
                showCheckAll: false,
                showUncheckAll: false,
            };

            /*TRANSLATION-TEXT*/
            $scope.customTexts = {
                buttonDefaultText: '-- Select Division(s) --',
                searchPlaceholder: 'Search for Organization...'
            }
            /*getAllDivisions will fetch the division name and division Id*/
            $scope.getAllDivisions = function () {
                genericService.getObjects($rootScope.baseUrl + 'usermgmtrest/getAssignedOrganization' + '/' + $rootScope.adminId).then(function (response) {
                    $log.debug(angular.toJson(response));
                    $scope.divisionList = response;
                }, function () {
                })
            };
            /*getAllLanguages will fetch the division name and division Id*/
            $scope.getAllLanguages = function () {
                genericService.getObjects($rootScope.baseUrl + '/usermgmtrest/languages').then(function (response) {
                    $log.debug(angular.toJson(response));
                    $scope.languageList = response;
                }, function () {

                });
            };
            $scope.getAllDivisions();
            $scope.getAllLanguages();
            $scope.isMatching = function () {
                $log.debug(angular.equals($scope.user.userName, $scope.user.email));
                return angular.equals($scope.user.userName, $scope.user.email);
            }
            /**
            * Auto generation of password
            */
            $scope.generatePassword = function () {
                var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
                var password_length = 8;
                var randomstring = '';
                for (var i = 0; i < password_length; i++) {
                    var rnum = Math.floor(Math.random() * chars.length);
                    randomstring += chars.substring(rnum, rnum + 1);
                }
                return randomstring;
            }
            var closeForm = function () {
                $scope.user = {};
                $scope.user.userDivision = [];
                $scope.createUserForm.$setPristine();
                $scope.createUserForm.$setUntouched();
            }
            $scope.languageSelected = function (selected) {
                $scope.user.userLanguage = selected.languageId;
                delete $scope.createUserForm.userLanguage.$error.required;
            }

            /*CREATING NEW USER*/
            $scope.submitRegdForm = function () {
                if ($scope.user.userLanguage) {
                    var countryData = $("#mobile").intlTelInput("getSelectedCountryData");
                    $scope.user.countryFlag = countryData.iso2;
                    //$scope.user.phoneNumber = $scope.user.phoneNumber.substr($scope.user.countryFlag.length, $scope.user.phoneNumber.length);
                    $log.info('user phoneNumber: ' + angular.toJson($scope.user));
                    var aliasName = $scope.user.firstName + " " + $scope.user.lastName;
                    $scope.user.alias = aliasName;
                    var pwd = $scope.generatePassword();
                    $scope.user.password = pwd;
                    //createdBy or modifiedBy
                    $scope.user.currentLoggedUserId = $rootScope.adminId;
                    $log.debug("Created User: " + angular.toJson($scope.user));
                    $rootScope.startSpin();
                    genericService.getObjects($rootScope.baseUrl + 'usermgmtrest/checkemailexistance?email=' + $scope.user.email).then(function (response) {
                        genericService.addObject($rootScope.baseUrl + 'usermgmtrest/createuser', $scope.user).then(function (data) {
                            $log.debug("" + $scope.user);
                            $.toaster({ priority: 'success', message: registrationSuccessful });
                            closeForm();
                            $('#close-modal').trigger('click');
                            $rootScope.stopSpin();
                        }, function (data) {
                            $rootScope.stopSpin();
                            $.toaster({ priority: 'danger', message: registrationFailed });
                        });
                    }, function (response) {
                        $rootScope.stopSpin();
                        $log.debug('Message' + response.message);
                        $.toaster({ priority: 'danger', message: emailAlreadyExisting });
                    });
                } else {
                    console.log('$scope.user.userLanguage : '+$scope.user.userLanguage);
                    $scope.createUserForm.userLanguage.$error.required = true;
                }
            };
            $scope.goToUserList = function () {
                $state.go('usermgmt.user.viewuser');
            }
        }
    ]);
usermodule.factory('createUserService', ['CREATEUSERCONSTANTS',
    function (CREATEUSERCONSTANTS) {
        var factory = {};
        return factory;
    }
]);



usermodule.filter('propsFilter', function () {
    return function (items, props) {
        var out = [];

        if (angular.isArray(items)) {
            var keys = Object.keys(props);

            items.forEach(function (item) {
                var itemMatches = false;

                for (var i = 0; i < keys.length; i++) {
                    var prop = keys[i];
                    var text = props[prop].toLowerCase();
                    if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                        itemMatches = true;
                        break;
                    }
                }

                if (itemMatches) {
                    out.push(item);
                }
            });
        } else {
            // Let the output be the input untouched
            out = items;
        }

        return out;
    };
});
