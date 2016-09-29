package com.tf.usermanagement.endpoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spaneos.dtssp.output.DataTablesOutput;
import com.tf.usermanagement.dto.ResponseMessage;
import com.tf.usermanagement.exceptions.InsufficientDataException;
import com.tf.usermanagement.report.CustomerAssignmentReport;
import com.tf.usermanagement.report.CustomerUnAssignmentReport;
import com.tf.usermanagement.service.CustomerAssignmentService;

/**
 * 
 * @author Santosh
 *
 */

@RestController
public class CustomerAssignmentController {
    
    @Autowired
    private CustomerAssignmentReport customerAssignmentReport;
    
    @Autowired
    private CustomerUnAssignmentReport customerUnAssignmentReport;
    
    @Autowired
    private CustomerAssignmentService customerAssignmentService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerAssignmentController.class);
    
    /**
     * used to get the report for customer assignment based on the filter
     * @param inputDt
     * @return
     */
  
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/customerAssignmentReport",method=RequestMethod.GET)
    public DataTablesOutput custAssignReport(CustomerAssignmentReport.CustomerAssignputDtInput inputDt){
    		DataTablesOutput obj = customerAssignmentReport.fetchData(inputDt);
    	return obj;
    }
    
    /**
     * used to get the report for customer Unassignment based on the filter
     * @param inputDt
     * @return
     */
  
    @SuppressWarnings("rawtypes")
    @RequestMapping(value="/customerUnAssignmentReport",method=RequestMethod.GET)
    public DataTablesOutput custUnAssignReport(CustomerUnAssignmentReport.CustomerUnAssignputDtInput inputDt){
	customerUnAssignmentReport.setUserId(inputDt.getUserId());
    		DataTablesOutput obj = customerUnAssignmentReport.fetchData(inputDt);
    	return obj;
    }
    
    /**
     * used to assign the customer for a user
     * input- user id, list of customers which have to assign for user
     * 
     * 
     */
    @RequestMapping(value="/assigncustomer/{user_id}/{organization_id}/{login_user_id}",method=RequestMethod.POST)
    public ResponseEntity<?> assignCustomer(@PathVariable("user_id") Long userId,@PathVariable("organization_id") Long orgId,
	    @PathVariable("login_user_id") Long loginUserId,@RequestBody String jsonData){
	
	try{    
	    String msg= customerAssignmentService.assignCustomer(loginUserId,userId,orgId, jsonData);
	   
	return new ResponseEntity<>(new ResponseMessage(msg), HttpStatus.OK);		
	    
	}catch(NullPointerException e){
	    return new ResponseEntity<>(new ResponseMessage("error while assigning the selected customer"),HttpStatus.BAD_REQUEST);
	}catch(InsufficientDataException e){
		return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.BAD_REQUEST);
	}catch(Exception e){
	    return new ResponseEntity<>(new ResponseMessage("error while assigning the customer"),HttpStatus.BAD_REQUEST);
	}	
	
    }
    
    /**
     * used to assign all customer for a user
     * input- user id, list of all customers which have to assign for user
     * 
     */
    @RequestMapping(value="/assignallcustomer/{user_id}/{organization_id}/{login_user_id}",method=RequestMethod.POST)
    public ResponseEntity<?> assignAllCustomer(@PathVariable("user_id") Long userId,@PathVariable("organization_id") Long orgId,
	    @PathVariable("login_user_id") Long loginUserId,@RequestBody String customer){
	
	try{    
	    LOGGER.info("userId "+userId+" orgId "+orgId+" loginId "+loginUserId);
	    LOGGER.info("customer "+customer);
	    String msg= customerAssignmentService.assignAllCustomer(loginUserId,userId,orgId,customer);
	   
	return new ResponseEntity<>(new ResponseMessage(msg), HttpStatus.OK);		
	    
	}catch(NullPointerException e){
	    return new ResponseEntity<>(new ResponseMessage("error while assigning the all customer"),HttpStatus.BAD_REQUEST);
	}catch(InsufficientDataException e){
		return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.BAD_REQUEST);
	}catch(Exception e){
	    return new ResponseEntity<>(new ResponseMessage("error while assigning the customers"),HttpStatus.BAD_REQUEST);
	}
    }
    
    /**
     * used to remove the customer for a user
     * input- user id, list of customers which have to remove for user
     * 
     */
    @RequestMapping(value="/removecustomer/{user_id}/{organization_id}/{login_user_id}",method=RequestMethod.POST)
    public ResponseEntity<?> removeCustomer(@PathVariable("user_id") Long userId,@PathVariable("organization_id") Long orgId,
	    @PathVariable("login_user_id") Long loginUserId,@RequestBody String jsonData){	
		
	try{    
	    String msg= customerAssignmentService.removeCustomer(loginUserId,userId,orgId, jsonData);
	   
	return new ResponseEntity<>(new ResponseMessage(msg), HttpStatus.OK);		
	    
	}catch(NullPointerException e){
	    return new ResponseEntity<>(new ResponseMessage("error while removing the selected customer"),HttpStatus.BAD_REQUEST);
	}catch(InsufficientDataException e){
		return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.BAD_REQUEST);
	}catch(Exception e){
	    return new ResponseEntity<>(new ResponseMessage("error while removing the customer"),HttpStatus.BAD_REQUEST);
	}	
    }
    
    /**
     * used to remove all customer for a user
     * input- user id, list of all customers which have to remove for user
     * 
     */
    @RequestMapping(value="/removeallcustomer/{user_id}/{organization_id}/{login_user_id}",method=RequestMethod.POST)
    public ResponseEntity<?> removeAllCustomer(@PathVariable("user_id") Long userId,@PathVariable("organization_id") Long orgId,
	    @PathVariable("login_user_id") Long loginUserId,@RequestBody String searhfilter){
	
	try{    
	    String msg= customerAssignmentService.removeAllCustomer(loginUserId,userId,orgId,searhfilter);
	   
	return new ResponseEntity<>(new ResponseMessage(msg), HttpStatus.OK);		
	    
	}catch(NullPointerException e){
	    return new ResponseEntity<>(new ResponseMessage("error while removing all customer"),HttpStatus.BAD_REQUEST);
	}catch(InsufficientDataException e){
		return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.BAD_REQUEST);
	}catch(Exception e){
	    return new ResponseEntity<>(new ResponseMessage("error while removing the customer"),HttpStatus.BAD_REQUEST);
	}
    }
    
    /**
     * This method is used to download the customer report based on filter criteria
     * @param request
     * @param response
     * @param userId
     * @param orgId
     * @param loginUserId
     * @param searhfilter
     * @return
     */
    @RequestMapping(value = "/downloaddocument/{user_id}/{organization_id}/{login_user_id}",method=RequestMethod.POST)
    public ResponseEntity<?> downloadDocument(HttpServletRequest request,
	    HttpServletResponse response,@PathVariable("user_id") Long userId,@PathVariable("organization_id") Long orgId,
	    @PathVariable("login_user_id") Long loginUserId,@RequestBody String searhfilter) {
	try {
	    LOGGER.info("in download ");
	    
	   LOGGER.info("userId "+userId+" orgId "+orgId+" loginId "+loginUserId);     
	   LOGGER.info("in download searchfilter : "+searhfilter);
	   return new ResponseEntity<>(customerAssignmentService.downloadCustomerResult(response,loginUserId,userId,orgId,searhfilter),HttpStatus.OK);
	   // new FileDownloadUtil().doDownload(request, response, docDetails);
	} catch (Exception e) {
	    LOGGER.error("error while downloading the fiel"+e.getMessage());
	    return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.BAD_REQUEST);
	}

    }
    
    /**
     * used to get the number of available customer and assigned customer for a division
     * @param userId
     * @param orgId
     * @return
     */
    @RequestMapping(value="/getCustomerCount/{user_id}/{organization_id}", method=RequestMethod.GET)
    public ResponseEntity<?> getavailableCustomerCount(@PathVariable("user_id") Long userId,@PathVariable("organization_id") Long orgId){
	try{
	    return new ResponseEntity<>(customerAssignmentService.getavailableCustomerCount(userId,orgId),HttpStatus.OK);
	    
	}catch(Exception e){
	    LOGGER.error("error while getting the customer count"+e.getMessage());
	    return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.BAD_REQUEST);
	}
    }
    
    /**
     * used to get the number of assigned customer for a division
     * @param userId
     * @param orgId
     * @return
     */
    @RequestMapping(value="/getAssignedCustomerCount/{user_id}/{organization_id}", method=RequestMethod.GET)
    public ResponseEntity<?> getAssignedCustomerCount(@PathVariable("user_id") Long userId,@PathVariable("organization_id") Long orgId){
	try{
	    return new ResponseEntity<>(customerAssignmentService.getAssignedCustomerCount(userId,orgId),HttpStatus.OK);
	    
	}catch(Exception e){
	    LOGGER.error("error while getting the customer count"+e.getMessage());
	    return new ResponseEntity<>(new ResponseMessage(e.getMessage()),HttpStatus.BAD_REQUEST);
	}
    }

}
