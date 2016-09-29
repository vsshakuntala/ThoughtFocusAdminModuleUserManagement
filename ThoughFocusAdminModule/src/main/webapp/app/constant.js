var appconstantsmodule = angular.module('tfadmin.appconstantsmodule',[]);

appconstantsmodule.constant('APPCONSTANTS', {
    COMMONMESSAGE: {
        EMAIL_MANDATORY:'Email is mandatory',
        PASSWORD_MANDATORY:'Password is mandatory',
        NOT_VALID_EMAIL:'Not a valid Email Id',
        FOOTER_CONTENT_COPYRIGHT:'Copyright',
        FOOTER_CONTENT_COPYRIGHT_ICON:'&copy;',
        FOOTER_CONTENT_COPYRIGHT_YEAR:'2016',
        FOOTER_CONTENT_COPYRIGHT_COMPANY_NAME:'ThoughtFocus',
        FOOTER_CONTENT_COPYRIGHT_RIGHTS:'All rights reserved.',
        VERSION:'Version 1.0.0.0'
        
        
        	
    },
    
    LOGINPAGE:{
    	LOGIN_LOGO_NAME_BOLD:'Thought',
    	LOGIN_LOGO_NAME_NORMAL:'Focus',
    	LOGIN_BOX_MESSAGE:'Sign in to start your session',
    	SIGN_IN_BUTTON_LABEL:'Sign In',
    	EMAIL_PLACEHOLDER:'Enter the email',
    	PASSWORD_PLACEHOLDER:'Enter the password',
    	COMPANY_LOGO:'app-content/img/tf.png'	
    },
    
    SIGNUP: {
    	PASSWORD_REQUIRED:'Password is Required',
    	CONFIRM_PASSWORD_MANDATORY:'Confirm Password is mandatory',
        PASSWORD_NOTMATCHED:'Wrong Password',
        SELECT_TERMS_CONDITIONS:'Please select terms and conditions',
        REGISTER:'Are you sure you want to register with this Email Id? : ',
        DISCRAD_CHANGES:'Are you sure you want to discard the changes?'
    },        
    });





appconstantsmodule.factory('datableConfigService',
		['$q',
		 'DTOptionsBuilder',
		 'DTColumnBuilder',
		 function($q, DTOptionsBuilder, DTColumnBuilder) {

             var factory = {};
             
             /**
              * This will allow to get all objects by calling backend API,
              * using http GET method,
              * @param url of api to be invoked
              */
             factory.getObjects = function(custName,custNumber) {
            	 
                 var obj = {};
                 obj.options = DTOptionsBuilder.newOptions()
                 .withOption('ajax', {
                     url: 'customerAssignmentReport',
                     type: 'GET',
                     data: {
                     	 'customerName': custName,
                     	 'customerNumber': custNumber            
                     }
                 })
                 .withDataProp('data')
                 .withOption('processing', true)
                 .withOption('serverSide', true)
                 .withPaginationType('full_numbers');
                 
                 obj.columns = [
                    DTColumnBuilder.newColumn(null).withTitle('Select').notSortable()
			            .renderWith(function(data, type, full, meta) {
			            	if(data=='CUSTOMER2'){
	                			  return '<input type="checkbox" disabled>';
	                		  }else{
	                			  return '<input type="checkbox">';
	                		  }
			            }).withOption('name','customerNumber'),
                    DTColumnBuilder.newColumn('customerNumber').withTitle('Customer #').withOption('name','customerNumber'),
                    DTColumnBuilder.newColumn('customerName').withTitle('Customer Name').withOption('name','customerName'),
                    DTColumnBuilder.newColumn('addressOne').withTitle('Addres1').withOption('name','addressOne'),
                    DTColumnBuilder.newColumn('city').withTitle('City').withOption('name','city'),
                    DTColumnBuilder.newColumn('state').withTitle('State').withOption('name','state'),
                    DTColumnBuilder.newColumn('postal').withTitle('Postal Code').withOption('name','postal').withOption('searchable',true)];
                 return obj;
             };
             return factory;
         }
     ]);