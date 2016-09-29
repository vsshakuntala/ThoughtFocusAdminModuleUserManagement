/**
 *@author	Narasingha  
 */
var createuser = angular.module('tfadmin.usermanagementmodule.user.createuser',[ 'ngIntlTelInput', 'angularjs-dropdown-multiselect']);

createuser.config(['$stateProvider','$urlRouterProvider','ngIntlTelInputProvider',
                   function($stateProvider, $urlRouterProvider, ngIntlTelInputProvider){
					
		ngIntlTelInputProvider.set({
			preferredCountries : [ "in", "us" ],
			autoHideDialCode: true,
			nationalMode: false,
			utilsScript : "app-content/js/utils.js"
			
		});
		
		$stateProvider.state('usermgmt.user.createuser',{
		url:'/createuser',
		templateUrl : 'app/partial/usermanagement/user/createuser/createuser.html',
		controller: 'CreateUserController'
	});
}]);

createuser.controller('CreateUserController',['$scope','$state','$http', 'genericService', 'localStorageService', 'SharedService', function($scope, $state, $http, genericService, localStorageService, SharedService){
	
	$scope.adminId = localStorageService.get('AdminId');
	console.log("ADMIN DETAILS: ",$scope.adminId);
	
	$scope.message="";
	$scope.user = {};
	$scope.user.userDivision = [];
	
	
	/*EXTRA-SETTINGS*/
	$scope.userDivision = {
			smartButtonMaxItems: 3,
			smartButtonTextConverter: function(itemText, originalItem) {
				return itemText;
			},
			enableSearch : true,
			scrollable : true,
			idProp : 'organizationId',			
			displayProp : 'organizationName',
			externalIdProp : 'organizationId',
			showCheckAll : false,
			showUncheckAll : false,
		};
	
	/*TRANSLATION-TEXT*/
	$scope.customTexts={
			buttonDefaultText: '-- Select Division(s) --',
			searchPlaceholder: 'Search for Organization...'	
	}
	
	/*getAllDivisions will fetch the division name and division Id*/
	$scope.getAllDivisions = function () {
		genericService.getObjects('usermgmtrest/organizations').then(function(response){			
			console.log(angular.toJson(response));
			$scope.divisionList = response;			
		})
	};
	
	/*getAllLanguages will fetch the division name and division Id*/
	$scope.getAllLanguages = function () {
		genericService.getObjects('usermgmtrest/languages').then(function(response){
			console.log(angular.toJson(response));
			$scope.languageList = response;
		})
	};
	
	$scope.getAllDivisions();
	$scope.getAllLanguages();
	
	/**
	 * Auto generation of password
	 */
	$scope.generatePassword = function(){
		var chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		var password_length = 8;
		var randomstring = '';
		for(var i = 0; i < password_length; i++){
			var rnum = Math.floor(Math.random()*chars.length);
			randomstring += chars.substring(rnum, rnum+1); 
		}
		return randomstring;
	}
	
	$scope.isMatching = function(){
		console.log(angular.equals($scope.user.userName, $scope.user.email));
		return angular.equals($scope.user.userName, $scope.user.email);
	}
	/*CREATING NEW USER*/
	$scope.submitRegdForm = function(userData){

		//Getting phone number with country code appended from UI
		var mobile = userData.phone;
		console.log(mobile);
		
		
		var countryData = $("#mobile").intlTelInput("getSelectedCountryData");
		var Flag = countryData.dialCode;
		for(var i = 1; i<= Flag.length; i++){
			var mobileNumber = userData.phone.slice(i);
		}
		
		//phoneNumber with out countryFlag
		userData.phoneNumber = mobileNumber;
		
		//only countryFlag
		userData.countryFlag = Flag;
		
		//alias 
		var aliasName = userData.firstName+" "+userData.lastName;
		userData.alias = aliasName;
		
		//password
		var pwd = $scope.generatePassword();
		userData.password = pwd;

		//createdBy or modifiedBy
		userData.currentLoggedUserId = $scope.adminId;
		
		console.log("the added user is: "+angular.toJson(userData));
		$scope.userData='';
		genericService.getObjects('usermgmtrest/checkemailexistance?email='+userData.email).then(function(response){
			//email is unique so we are adding user
			
			genericService.addObject('usermgmtrest/createuser',userData).then(function (data) {
				alert("User is added successfuly");
				$.notify({icon: 'glyphicon glyphicon-ok',message:data.message },{ type: 'success', placement: {
					from: "top",
					align: "right"
				}});
				$state.go('usermgmt.user.viewuser');
		     }, function (data) {
					$.notify({icon: 'glyphicon glyphicon-warning-sign',message:data.message },{ type: 'danger', placement: {
						from: "top",
						align: "right"
					}});
					
				});
			
		},function(response){
			alert(response.message);
			$.notify({icon: 'glyphicon glyphicon-warning-sign',message:response.message },{ type: 'danger', placement: {
				from: "top",
				align: "right"
			}});
		});
		
		$scope.createUserForm.$setPristine();
		$scope.createUserForm.$setUntouched();
	};
	
	$scope.goToUserList = function() {
		$state.go('usermgmt.user.viewuser');
	}
}]);

createuser.directive('allowPattern', [allowPatternDirective]);
function allowPatternDirective() {
	return {
		restrict: "A",
		compile: function(tElement, tAttrs) {
			return function(scope, element, attrs) {
				element.bind("keypress", function(event) {
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
