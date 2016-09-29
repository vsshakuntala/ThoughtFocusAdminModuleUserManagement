/**
 * This is generic module which can be reused, this mainly contains
 * factory methods for most commonly used http methods like get,
 * post, put and delete.
 * Note: All the function here follows the REST standard.
 * 
 * @author Preetham G R
 *
 * @CopyRights Reserved with Spaneos Software Solutions Pvt. Ltd.
 * 
 * All of the code within the Spaneos Software Solutions is developed and copyrighted 
 * by Spaneos Software Solutions Pvt. Ltd., and may not be copied,replicated, or used in any 
 * other software or application without prior permission from Spaneos Software Solutions Pvt. Ltd. 
 * All usage must coincide with the Spaneos Software Solutions pvt ltd End User License Agreement.
 */
genericservice = angular.module('GenericServiceModule', []);
/**
 * All module level constants goes here 
 */
genericservice.constant('GENERICSERVICECONSTANTS', {
    SERVICE: {
        GET_METHOD: 'GET',
        POST_METHOD: 'POST',
        PUT_METHOD: 'PUT',
        DELETE_METHOD: 'DELETE'
    }
});
/**
 * This is do nothing configuration for this module,
 * if customization is required then can be configured accordingly.
 * Ex: Adding interceptors 
 */
genericservice.config([function () {

}]);

/**
 * This is factory for this module
 */
genericservice.factory('genericService',
    ['$q',
        '$http',
        'GENERICSERVICECONSTANTS',
        function ($q, $http, GENERICSERVICECONSTANTS) {

            var factory = {};

            /**
             * This will allow to get all objects by calling backend API,
             * using http GET method,
             * @param url of api to be invoked
             */
            factory.getObjects = function (url) {
                var deferred = $q.defer();
                $http({
                    method: GENERICSERVICECONSTANTS.SERVICE.GET_METHOD,
                    url: url
                }).success(function (data, status, headers, config) {
                    if (angular.isDefined(data.statusCode)) {
                        if (angular.equals(data.statusCode, "200")) {
                            deferred.resolve(data);
                        } else {
                            deferred.reject(data);
                        }
                    } else {
                        deferred.resolve(data);
                    }
                }).error(function (data) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };
            /**
             * This will allow to get object by id, id of the object to be
             * fetched should be appended to the url.
             * @param url of the api with appended id. 
             */
            factory.getObjectById = function (url) {
                var deferred = $q.defer();
                $http({
                    method: GENERICSERVICECONSTANTS.SERVICE.GET_METHOD,
                    url: url
                }).success(function (data, status, headers, config) {
                    if (angular.isDefined(data.statusCode)) {
                        if (angular.equals(data.statusCode, "200")) {
                            deferred.resolve(data);
                        } else {
                            deferred.reject(data);
                        }
                    } else {
                        deferred.resolve(data);
                    }
                }).error(function (data) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };
            /**
             * This will allow to add object to DB by calling the api.
             * @param url at which api is mapped
             * @param object to be added
             */
            factory.addObject = function (url, object) {
                var deferred = $q.defer();
                $http({
                    method: GENERICSERVICECONSTANTS.SERVICE.POST_METHOD,
                    url: url,
                    data: object
                }).success(function (data, status, headers, config) {
                    if (angular.isDefined(data.statusCode)) {
                        if (angular.equals(data.statusCode, "200")) {
                            deferred.resolve(data);
                        } else {
                            deferred.reject(data);
                        }
                    } else {
                        deferred.resolve(data);
                    }
                }).error(function (data, status, headers, config) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };
            /**
             * This will allow to upload file by calling the api.
             * @param url at which api is mapped
             * @param object file object to be uploaded
             */
            factory.uploadFile = function (url, object) {
                var deferred = $q.defer();
                $http({
                    method: GENERICSERVICECONSTANTS.SERVICE.POST_METHOD,
                    url: url,
                    data: object,
                    headers: { 'Content-Type': undefined },
                    transformRequest: angular.identity
                }).success(function (data, status, headers, config) {
                    if (angular.isDefined(data.statusCode)) {
                        if (angular.equals(data.statusCode, "200")) {
                            deferred.resolve(data);
                        } else {
                            deferred.reject(data);
                        }
                    } else {
                        deferred.resolve(data);
                    }
                }).error(function (data) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };
            /**
             * This will allow to update the object by calling api
             */
            factory.putObject = function (url, object) {
                var deferred = $q.defer();
                $http({
                    method: GENERICSERVICECONSTANTS.SERVICE.PUT_METHOD,
                    url: url,
                    data: object
                }).success(function (data, status, headers, config) {
                    if (angular.isDefined(data.statusCode)) {
                        if (angular.equals(data.statusCode, "200")) {
                            deferred.resolve(data);
                        } else {
                            deferred.reject(data);
                        }
                    } else {
                        deferred.resolve(data);
                    }
                }).error(function (data) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };

            factory.deleteObject = function (url, object) {
                var deferred = $q.defer();
                $http({
                    method: GENERICSERVICECONSTANTS.SERVICE.DELETE_METHOD,
                    url: url,
                    data: object,
                    headers: { 'Content-Type': 'application/json' }
                }).success(function (data, status, headers, config) {
                    if (angular.isDefined(data.statusCode)) {
                        if (angular.equals(data.statusCode, "200")) {
                            deferred.resolve(data);
                        } else {
                            deferred.reject(data);
                        }
                    } else {
                        deferred.resolve(data);
                    }
                }).error(function (data) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };

            factory.tokenObject = function (url, object, headersConfig) {
                var deferred = $q.defer();
                $http({
                    method: GENERICSERVICECONSTANTS.SERVICE.POST_METHOD,
                    url: url,
                    data: object,
                    headers: {
                        Authorization: headersConfig,
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                }).success(function (data, status, headers, config) {
                    if (angular.isDefined(data.statusCode)) {
                        if (angular.equals(data.statusCode, "200")) {
                            deferred.resolve(data);
                        } else {
                            deferred.reject(data);
                        }
                    } else {
                        deferred.resolve(data);
                    }
                }).error(function (data, status, headers, config) {
                    deferred.reject(data);
                });
                return deferred.promise;
            };


            factory.downloadFile = function (url, object) {
                var deferred = $q.defer();
                $http({
                    method: 'POST',
                    url: url,
                    data: object,
                    headers: {
                        'Content-Type': 'application/json',
                        'accept': 'application/vnd.ms-excel'
                    },
                    responseType: 'arraybuffer',
                    cache: false,
                }).success(function (data, status, headers, config) {
                    headers = headers();
                    var filename = headers['x-filename'];
                    var contentType = headers['content-type'];
                    var linkElement = document.createElement('a');
                    try {
                        var blob = new Blob([data], { type: contentType });
                        var url = window.URL.createObjectURL(blob);
                        linkElement.setAttribute('href', url);
                        linkElement.setAttribute("download", filename);
                        var clickEvent = new MouseEvent("click", {
                            "view": window,
                            "bubbles": true,
                            "cancelable": false
                        });
                        linkElement.dispatchEvent(clickEvent);
                        deferred.resolve();
                    } catch (ex) {
                       deferred.reject();
                    }
                });
                return deferred.promise;
            };
            return factory;
        }
    ]);