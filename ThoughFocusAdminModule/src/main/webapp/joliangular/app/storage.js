/**
 * This is Storage module which can be reused, this mainly contains
 * factory methods for most common operation like set, get, remove
 * reset, extract.
 * If localstorage is supported then localstorage is used else cookies
 * will be used.
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
/*
 * Note: This module is still under development for support of cookie storage,
 * Development completed for local storage.
 */
var storagemmodule = angular.module('mystorage', []);

storagemmodule.controller('StorageController',
    [function () {

    }
    ]);

storagemmodule.factory('StorageService', ['$window',
    function ($window) {

        var factory = {};
        var serializer = angular.toJson;
        var deserializer = angular.fromJson;
        var copy = angular.copy;
        var storageKeyPrefix = 'myStorage-';
        var prefixLength = storageKeyPrefix.length;

        function isSupported() {
            var supported;
            try {
                supported = $window[storageType];
            }
            catch (err) {
                supported = false;
            }
            return supported;
        }

        function isSupportedInIncognito() {
            if (supported) {
                var key = '__' + Math.round(Math.random() * 1e7);

                try {
                    localStorage.setItem(key, key);
                    localStorage.removeItem(key);
                }
                catch (err) {
                    supported = false;
                    alert('Please do not use in incognito mode');
                }
            }
            return supported;
        }

        factory.set = function (key, item, expires) {
            var expiryDate = null;
            if (angular.isNumber(expires)) {
                var time = new Date();
                time.setMinutes(time.getMinutes() + expires);
                var encrypted = Aes.Ctr.encrypt(time, 'spaneos$123', 256);
                angular.merge(item, {expire: encrypted});
            }
            if (isSupported && isSupportedInIncognito) {
                $window.localStorage.setItem(storageKeyPrefix + key, serializer(copy(item)));
            } else {

            }
        }

        factory.get = function (key) {
            var obj = null;
            if (isSupported && isSupportedInIncognito) {
                obj = copy(deserializer($window.localStorage.getItem(storageKeyPrefix + key)));
            } else {

            }
            if (obj !== null && angular.isDefined(obj.expire)) {
                var time = Date.parse(new Date());
                var decrypted = Date.parse(Aes.Ctr.decrypt(obj.expire, 'spaneos$123', 256));
                // delete the key if it timed out
                if (decrypted < time) {
                    factory.remove(key);
                    return null;
                }
            }
            return obj;
        }

        factory.reset = function () {
            if (isSupported && isSupportedInIncognito) {
                for (var i = 0; i < $window.localStorage.length; i++) {
                    var key = $window.localStorage.key(i);
                    if (angular.equals(key.slice(0, prefixLength), storageKeyPrefix)) {
                        $window.localStorage.removeItem(key);
                    }
                }
            } else {

            }
        }

        factory.remove = function (key) {
            if (isSupported && isSupportedInIncognito) {
                $window.localStorage.removeItem(storageKeyPrefix + key);
            } else {

            }
        }

        factory.extract = function (key) {
            var obj = null;
            if (isSupported && isSupportedInIncognito) {
                obj = copy(deserializer($window.localStorage.getItem(storageKeyPrefix + key)));
                $window.localStorage.removeItem(storageKeyPrefix + key);
            } else {

            }
            if (obj !== null && angular.isDefined(obj.expire)) {
            	var time = Date.parse(new Date());
                var decrypted = Date.parse(Aes.Ctr.decrypt(obj.expire, 'spaneos$123', 256));
                // delete the key if it timed out
                if (decrypted < time) {
                    factory.remove(key);
                    return null;
                }
            }
            return obj;
        }

        return factory;
    }
]);