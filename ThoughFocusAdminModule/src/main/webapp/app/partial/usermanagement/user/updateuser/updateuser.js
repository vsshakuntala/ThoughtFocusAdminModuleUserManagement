/**
 * 
 */
var updateuser = angular.module('tfadmin.usermanagementmodule.user.updateuser', ['ngIntlTelInput', 'angularjs-dropdown-multiselect']);

updateuser.config(['$stateProvider', '$urlRouterProvider','ngIntlTelInputProvider', 
                   function ($stateProvider, $urlRouterProvider, ngIntlTelInputProvider) {
	ngIntlTelInputProvider.set({
		preferredCountries : [ "in", "us" ],
		separateDialCode : true,
		nationalMode: true,
		autoFormat : true,
		numberType : "MOBILE",
		utilsScript : "app-content/js/utils.js"
	});
	
	$stateProvider.state('usermgmt.user.updateuser', {
		url: '/updateuser',
		templateUrl: 'app/partial/usermanagement/user/updateuser/updateuser.html',
		controller: 'UpdateUserController'
	});
}]);

updateuser.controller('UpdateUserController', ['$scope', '$state','$http', 'SharedService', 'localStorageService', 'genericService', function ($scope, $state, $http, SharedService, localStorageService, genericService) {
	
	$scope.updateUserObject = localStorageService.get('userOrgDetails');
	console.log("ADMIN DETAILS: ",$scope.updateUserObject);
	$scope.userId = $scope.updateUserObject['userId'];
	$scope.adminId = $scope.updateUserObject['adminId'];
	console.log("ADMIN ID: ",$scope.adminId);
	
	$scope.lang="";
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
			groupByTextProvider : function(groupValue){
				if(groupValue === true){
					
					return 'Assigned organizations';
				}else
					return 'Unassigned organizations'
			}
			
		};
	
	/*TRANSLATION-TEXT*/
	$scope.customTexts={
			buttonDefaultText: '-- Select Division(s) --',
			searchPlaceholder: 'Search for Organization...'	
	}
	
	//getAllDivisions will fetch the division name and division Id	
	/*$scope.getAllDivisions = function () {
		genericService.getObjects('usermgmtrest/organizations').then(function(response){			
			//console.log(response.data);
			$scope.divisionList = response;			
		})
	};*/
	//$scope.getAllDivisions();
	
	//getAllLanguages will fetch the division name and division Id
	$scope.getAllLanguages = function () {
		genericService.getObjects('usermgmtrest/languages').then(function(response){
			//console.log(response.data);
			$scope.languageList = response;
		})
	};
	$scope.getAllLanguages();

	/**
	 * Getting User to be updated
	 */
	$scope.getUser = function(){
		genericService.getObjectById('usermgmtrest/getuser/'+$scope.userId+'/'+$scope.adminId).then(function(response){
			console.log(response);
			console.log("This is from get user "+angular.toJson(response));
			$scope.divisionList = response.userDivision;
			console.log("userDivision " +$scope.divisionList);
			//$scope.user.phone = response.phoneNumber;
			//console.log("user.phone: "+$scope.user.phone)
			$scope.user = response;
			//console.log('User with all organizations: ',$scope.user.userDivision);

			genericService.getObjectById('usermgmtrest/getactiveuser/'+$scope.userId).then(function(response){
					$scope.userActiveOrg = response;
					//console.log('User with active organizations: ',$scope.userActiveOrg);
				
				for(var i=0; i < $scope.user.userDivision.length; i++){
					for(var j=0; j < $scope.userActiveOrg.length; j++){
						if($scope.user.userDivision[i].organizationId == $scope.userActiveOrg[j].organizationId){
							$scope.user.userDivision[i].active = angular.copy($scope.userActiveOrg[j].active);
						}
					}
				}
				
				$scope.user.commonUserDivisions = [];
				for(var i=0; i < $scope.user.userDivision.length; i++){					
						if($scope.user.userDivision[i].active){
							$scope.user.commonUserDivisions.push($scope.user.userDivision[i]);
						}					
				}
				//console.log('Common of both',$scope.user.commonUserDivisions);
				
				for(var i=0;i<$scope.languageList.length; i++){
					if($scope.languageList[i].languageId == $scope.user.userLanguage){
						$scope.lang=$scope.languageList[i].languageDescription;
					}
				}
				console.log('Object',$scope.user);
				
				
			});
		});
	}
	$scope.getUser();
	/**
	 * UPDATING CODE GOES HERE -> API CALL
	 */
	$scope.submitUpdateForm = function(user){
		
		//Getting phone number with country code appended from UI
		var mobile = user.phoneNumber;
		console.log(mobile);
		
		var countryData = $("#mobile").intlTelInput("getSelectedCountryData");
		var Flag = countryData.dialCode;
		for(var i = 1; i<= Flag.length; i++){
			var mobileNumber = user.phoneNumber.slice(i);
		}
		
		//phoneNumber with out countryFlag
		user.phoneNumber = mobileNumber;
		
		//only countryFlag
		user.countryFlag = Flag;
		
		//alias name
		var aliasName = user.firstName+" "+user.lastName;
		user.alias = aliasName;
		user.currentLoggedUserId = $scope.adminId;
		alert("admin Id: "+user.currentLoggedUserId);
		user.userDivision = user.commonUserDivisions.slice(0);
		console.log('User RES', user.commonUserDivisions);
		console.log('User DIV', user.userDivision);
		console.log('The user to be updated is: ',user);
		console.log('The user: '+angular.toJson(user));
		genericService.putObject('usermgmtrest/updateuser',user).then(function(response){
			$scope.user = response;
			console.log('object',response);
			alert("User is updated successfuly");
			$.notify({icon: 'glyphicon glyphicon-ok',message:response.message },{ type: 'success', placement: {
				from: "top",
				align: "right"
			}});
			$state.go('usermgmt.divisionassignment')
		}, function(response){
			$.notify({icon: 'glyphicon glyphicon-warning-sign',message:response.message },{ type: 'danger', placement: {
				from: "top",
				align: "right"
			}});
		});
		$scope.goBackToDivision = function () {
			$state.go('usermgmt.divisionassignment')
		}
	}
	
	
}]);

updateuser.directive('allowPattern', [allowPatternDirective]);
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
