package com.tf.usermanagement.endpoint;

import java.security.Principal;
import java.util.List;

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

import com.tf.usermanagement.dto.GroupAllDto;
import com.tf.usermanagement.dto.GroupDto;
import com.tf.usermanagement.dto.ResponseMessage;
import com.tf.usermanagement.service.GroupAssignmentService;

/**
 * 
 * @author Santosh
 *
 */
@RestController
@RequestMapping("/groupassignment")
public class GroupAssignmentController {
    
    @Autowired
    private GroupAssignmentService groupAssignmentService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupAssignmentController.class);
    
    /**
     * This method is used to get the all assigned group for a user based on division id
     *    
     */
    @RequestMapping(value="/getassigned", method=RequestMethod.GET)
    public List<GroupDto> getAssignedGroup(){
	Long userId=110L;
	Integer orgId=1;
	List<GroupDto> grpList = groupAssignmentService.getAssignedGroup(userId,orgId);
	return grpList;
    }
    
    /**
     * This method is used to get the all unassigned group for a user based on division id
     */
    @RequestMapping(value="/getunassigned", method=RequestMethod.GET)
    public List<GroupDto> getUnAssignedGroup(){
	Long userId=110L;
	Integer orgId=1;
	List<GroupDto> grpList = groupAssignmentService.getUnAssignedGroup(userId,orgId);
	return grpList;
    }
    
    /**
     * This method is used to get the all assigned and unassigned group for a user based on division id
     *    user_id+'/'+organization_id+'/'+login_user_id,
     */
    @RequestMapping(value="/getgroup/{user_id}/{organization_id}/{login_user_id}", method=RequestMethod.GET)
    public GroupAllDto getGroup(@PathVariable("user_id") Long userId,@PathVariable("organization_id") Integer orgId,
	    @PathVariable("login_user_id") Long loginId){
	//Long userId=110L;
	//Integer orgId=1;
	LOGGER.info("userId "+userId+" orgId "+orgId+" loginId "+loginId);
	  LOGGER.debug("srart of calling the get all group");
	GroupAllDto grpList = groupAssignmentService.getAllGroup(userId,orgId);
	  LOGGER.debug("End of calling the get all group");
	return grpList;
    }
    
    /**
     * This method is used to update the group for a user
     */
    @RequestMapping(value="/updategroup/{user_id}/{organization_id}/{login_user_id}", method=RequestMethod.POST)
    public ResponseEntity<?> updateGroup(@PathVariable("user_id") Long userId,@PathVariable("organization_id") Integer orgId,
	    @PathVariable("login_user_id") Long loginId,@RequestBody String jsonData,Principal principal){
	
	try{    
	    loginId = Long.parseLong(principal.getName());
	    //Long userId=110L;
	    LOGGER.info("userId "+userId+" orgId "+orgId+" loginId "+loginId);
	    LOGGER.debug("srart of calling the update all group");
	String grpList = groupAssignmentService.updateGroup(jsonData,userId,loginId);
	  LOGGER.debug("end of calling the update all group");
	LOGGER.info("message "+grpList);
	   
	return new ResponseEntity<>(new ResponseMessage(grpList), HttpStatus.OK);		
	    
	}catch(Exception e){
	    return new ResponseEntity<>(new ResponseMessage("error while assigning group"),HttpStatus.BAD_REQUEST);
	}	
    }    
}
