package com.tf.usermanagement.endpoint;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tf.usermanagement.dto.PermissionsDto;
import com.tf.usermanagement.dto.RolesDto;
import com.tf.usermanagement.errorhandler.Message;
import com.tf.usermanagement.exceptions.EmptyListException;
import com.tf.usermanagement.service.RolesManagementService;

@RestController
public class RoleManagementController {

	@Autowired
	private RolesManagementService roleManagementService;

	private static final Logger LOGGER = Logger.getLogger(RolesManagementService.class);

	@RequestMapping(value = { "/rolemgnt/roleAssignedlist/{userId}/{orgId}" }, method = RequestMethod.GET)
	public List<RolesDto> getAssignedRolesList(@PathVariable("userId") long userId,
			@PathVariable("orgId") long orgId) {
		List<RolesDto> rolesList = null;
		 LOGGER.debug("start of calling /rolemgnt/roleAssignedlist/ api");
		rolesList = roleManagementService.getAssignedRoles(userId, orgId);
		 LOGGER.debug("end of calling /rolemgnt/roleAssignedlist/ api");
		return rolesList;
	}

	@RequestMapping(value = { "/rolemgnt/roleUnAssignedlist/{userId}/{orgId}" }, method = RequestMethod.GET)
	public List<RolesDto> getUnAssignedRolesList(@PathVariable("userId") long userId,
			@PathVariable("orgId") long orgId) {
		List<RolesDto> rolesList = null;
		 LOGGER.debug("start of calling /rolemgnt/roleUnAssignedlist/ api");
		rolesList = roleManagementService.getUnAssignedRoles(userId, orgId);
		 LOGGER.debug("end of calling /rolemgnt/roleUnAssignedlist/ api");
		return rolesList;
	}

	@RequestMapping(value = { "/rolemgnt/rolePermissions/{roleId}" }, method = RequestMethod.GET)
	public ResponseEntity<List<PermissionsDto>> getRolePermissions(@PathVariable long roleId) {
		List<PermissionsDto> rolespermissionsList = null;
		try {
		    LOGGER.debug("start of calling rolemgnt/rolePermissions api");
			rolespermissionsList = roleManagementService.getRolePermissions(roleId);
			 LOGGER.debug("end of calling rolemgnt/rolePermissions api");
			return new ResponseEntity<List<PermissionsDto>>(rolespermissionsList, HttpStatus.OK);
		} catch (EmptyListException e) {
			LOGGER.debug(e.getMessage());
			LOGGER.error(e);
			Message errorMessage = Message.statusCode(HttpStatus.NO_CONTENT).message("List is Empty!")
					.developerMsg("No data in Database").exception(e.getClass().getName()).build();
			throw new EmptyListException(errorMessage, e);
		}
	}

	@RequestMapping(value = "/rolemgnt/updaterole/{userId}/{orgId}", method = RequestMethod.POST)
	public ResponseEntity<Message> updateGroup(@PathVariable("userId") long userId, @PathVariable("orgId") long orgId,
			@RequestBody String roleDtoList1) {
	    LOGGER.debug("start of calling rolemgnt/updaterole api");
		if (roleManagementService.updateRole(roleDtoList1, userId, orgId)) {
		    LOGGER.debug("end of calling rolemgnt/updaterole api");
			Message message = Message.statusCode(HttpStatus.OK).message("Updated successfully!").build();
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} else {
			Message errorMessage = Message.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
					.message("Unable to update").developerMsg("Unable to update")
					.exception("Unable to update").build();
			return new ResponseEntity<Message>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

}
