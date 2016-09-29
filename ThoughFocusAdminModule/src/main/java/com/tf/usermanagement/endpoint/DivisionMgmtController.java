package com.tf.usermanagement.endpoint;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tf.usermanagement.dto.AssignOrgDto;
import com.tf.usermanagement.dto.DeAssignUserToOrgInputDto;
import com.tf.usermanagement.dto.DefaultAddressCheckDto;
import com.tf.usermanagement.dto.DivisionAssignmentDto;
import com.tf.usermanagement.dto.UserNotesDto;
import com.tf.usermanagement.dto.UserUnassignedOrgDto;
import com.tf.usermanagement.errorhandler.Message;
import com.tf.usermanagement.exceptions.EmptyListException;
import com.tf.usermanagement.service.DivisionMgmtService;

/**
 * 
 * @author Manideep
 * 
 *         This is a division assignment controller which contains the API's
 *         related to division assignment of a selected user my admin,
 * 
 *         In general user is assigned to multiple divisions and in each
 *         Divisions/Organizations he is assigned to multiple
 *         Customers,Machines,Roles,Groups
 */

@RestController
@RequestMapping("/divisionAssignment")
public class DivisionMgmtController {
	
	private static final Logger LOGGER = Logger.getLogger(DivisionMgmtController.class);
	
	@Autowired
	private DivisionMgmtService divisionMgmtService;

	// getters and setters
	public DivisionMgmtService getDivisionMgmtService() {
		return divisionMgmtService;
	}

	public void setDivisionMgmtService(DivisionMgmtService divisionMgmtService) {
		this.divisionMgmtService = divisionMgmtService;
	}

	/**
	 * this is end point where we can get all the customer assigned group wise
	 * to an organization
	 * 
	 */
	@RequestMapping(value = "/getDivisionAssignments/{userId}/{adminId}", method = RequestMethod.GET)
	private ResponseEntity<List<DivisionAssignmentDto>> getAllDivisionsAssignedForUser(@PathVariable long userId,
			@PathVariable long adminId) {
		List<DivisionAssignmentDto> divisionAssignmentDtoList = null;
		try{
			divisionAssignmentDtoList= divisionMgmtService.getAllDivisionAssignment(userId, adminId);
			return new ResponseEntity<List<DivisionAssignmentDto>>(divisionAssignmentDtoList,HttpStatus.OK);
		}
		catch(EmptyListException e){
			LOGGER.debug(e.getMessage());
			LOGGER.error(e);
			Message errorMessage = Message.statusCode(HttpStatus.NO_CONTENT)
					.message("There are no assigned divisions for this user!")
					.developerMsg("No data in Database")
					.exception(e.getClass().getName()).build();
			throw new EmptyListException(errorMessage, e);
		}
	}

	@RequestMapping(value = "/deAssignAllUserAllocations", method = RequestMethod.POST)
	private ResponseEntity<Message> deAssignAllUserAllocationsForOrganization(
			@RequestBody DeAssignUserToOrgInputDto deAssignUserToOrgInputDto) {
		if( divisionMgmtService.deAssignAllAllocationsForUserByOrganization(deAssignUserToOrgInputDto)){
			Message message = Message.statusCode(HttpStatus.OK).message("User is de-assigned to division successfully!")
					.build();
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}else{
			Message errorMessage = Message.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
					.message("Unable to deAssign All User Allocations division for the user").developerMsg("Unable to add division for the user")
					.exception("Unable to add division for the user").build();
			return new ResponseEntity<Message>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUnAssignedDivisionsForUser/{userId}/{adminId}", method = RequestMethod.GET)
	private ResponseEntity<List<UserUnassignedOrgDto>>  getUnassignedOrganizationsForUser(@PathVariable long userId,
			@PathVariable long adminId) {
		List<UserUnassignedOrgDto> unassignedOrgDtoList = null;
		System.out.println("getUnAssignedDivisionsForUser in controller");
		try{
			unassignedOrgDtoList= divisionMgmtService.getUnassignedOrganizationsForUser(userId, adminId);
			return new ResponseEntity<List<UserUnassignedOrgDto>>(unassignedOrgDtoList,HttpStatus.OK);
		}catch(EmptyListException e){
			LOGGER.debug(e.getMessage());
			LOGGER.error(e);
			Message errorMessage = Message.statusCode(HttpStatus.NO_CONTENT)
					.message("No division found")
					.developerMsg("No data in Database")
					.exception(e.getClass().getName()).build();
			throw new EmptyListException(errorMessage, e);
		}
	
	}

	@RequestMapping(value = "/addOrAssignUserToOrg", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<Message> assignOrAddUserUserToOrganization(
			@RequestBody AssignOrgDto deAssignUserToOrgInputDto) {
		if (divisionMgmtService.insertOrUpdateUserOrganizationMap(deAssignUserToOrgInputDto)) {

			Message message = Message.statusCode(HttpStatus.OK).message("User is assigned to division successfully!")
					.build();
			return new ResponseEntity<Message>(message, HttpStatus.OK);

		} else {

			Message errorMessage = Message.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
					.message("Unable to add division for the user").developerMsg("Unable to add division for the user")
					.exception("Unable to add division for the user").build();
			return new ResponseEntity<Message>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@RequestMapping(value = "/getNotesOfUser/{userId}", method = RequestMethod.GET)
	private ResponseEntity<List<UserNotesDto>> getNotesListForUser(@PathVariable long userId) {
		List<UserNotesDto> userNotesDtoList = null;
		try{
			userNotesDtoList= divisionMgmtService.getNotesListForUser(userId);
			return new ResponseEntity<List<UserNotesDto>>(userNotesDtoList,HttpStatus.OK);
		}catch(EmptyListException e){
			LOGGER.debug(e.getMessage());
			LOGGER.error(e);
			Message errorMessage = Message.statusCode(HttpStatus.NO_CONTENT)
					.message("No notes for this user")
					.developerMsg("No data in Database")
					.exception(e.getClass().getName()).build();
			throw new EmptyListException(errorMessage, e);
		}
}

	@RequestMapping(value = "/addNotesForUser", method = RequestMethod.POST)
	private ResponseEntity<Message> addNotesToUser(@RequestBody UserNotesDto userNotesDto) {
		
		if (divisionMgmtService.addNotesToUser(userNotesDto)) {
			Message message = Message.statusCode(HttpStatus.OK).message("Notes added successfully!").build();
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} else {
			Message errorMessage = Message.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
					.message("Unable to add notes for the user").developerMsg("Unable to add notes for the user")
					.exception("Unable to add notes for the user").build();
			return new ResponseEntity<Message>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getDefaultAddressForUserOrg/{userOrgId}", method = RequestMethod.GET)
	private DefaultAddressCheckDto getDefaultAddressForUserOrg(@PathVariable long userOrgId){
		return divisionMgmtService.getDefaultAddressForUserOrg(userOrgId);
	}
	
	
}
