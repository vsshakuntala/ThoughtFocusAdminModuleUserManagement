/**
 * This is applicationutility module which can be reused, this mainly contains
 * factory methods for most common operation used for application specific operation
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
var storagemmodule = angular.module('applicationutility', []);

storagemmodule.controller('ApplicationUtilityController', [function () {
}]);

storagemmodule.factory('applicationUtilityService', [function () {

    var factory = {};

    factory.checkAllObjectsInList = function (list, param) {
        return angular.copy(list.map(function (item) {
            return item[param];
        }));
    };

    factory.addUniqueObjectToList = function (list, object) {
        var index = list.indexOf(object);
        if (index !== -1) {
            list.splice(index,1);
        } else {
            list.push(object);
        }
        return list;
    };

    factory.buildDataObjectForDataTable = function (object) {
        var dtObject = {};
        for (var key in object) {
            if (angular.isArray(object[key])) {
                dtObject[key] = object[key].toString();
            } else if (object[key] !== null && !angular.equals(object[key], '')) {
                console.log(object[key]);
                dtObject[key] = object[key];
            }
        }
        return dtObject;
    };

    return factory;
}
]);