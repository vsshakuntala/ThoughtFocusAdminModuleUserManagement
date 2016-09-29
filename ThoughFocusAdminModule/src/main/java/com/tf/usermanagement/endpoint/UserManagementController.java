package com.tf.usermanagement.endpoint;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spaneos.dtssp.output.DataTablesOutput;
import com.tf.usermanagement.dto.LanguageDTO;
import com.tf.usermanagement.dto.OrganizationsDTO;
import com.tf.usermanagement.dto.UserDTO;
import com.tf.usermanagement.dto.UserOrgActiveDTO;
import com.tf.usermanagement.errorhandler.Message;
import com.tf.usermanagement.report.UserFilterReport;
import com.tf.usermanagement.service.UserManagementService;
import com.tf.usermanagement.utils.EmailUtility;

/**
 * @author Narasingha
 *
 */
@RestController
@RequestMapping("/usermgmtrest")
public class UserManagementController {
	@Autowired
	private UserManagementService userManagementService;
	@Autowired
	private UserFilterReport userFilterReport;

	@Autowired
	private EmailUtility emailUtility;

	@Resource
	private Environment environment;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementController.class);

	/**
	 * getDivisions() will return the organizationId and organizationName
	 */
	@RequestMapping(value = { "/organizations" }, method = RequestMethod.GET)
	public List<OrganizationsDTO> getDivisions() {
		List<OrganizationsDTO> orgIdNameList = userManagementService.getDivisions();
		return orgIdNameList;
	}

	/**
	 * getLanguageName() will return the languageId and languageDescription
	 */
	@RequestMapping(value = { "/languages" }, method = RequestMethod.GET)
	public List<LanguageDTO> getLanguageName() {
		List<LanguageDTO> langNameList = userManagementService.getLanguages();
		return langNameList;

	}

	@RequestMapping(value = "/testcheck/{abc}", method = RequestMethod.GET)
	public String test(@PathVariable String abc) {
		return abc;
	}
	
	/**
	 * API to get all assigned division with role permission 4
	 * @param adminId
	 * @return
	 */
	@RequestMapping(value = "/getAssignedOrganization/{adminId}", method = RequestMethod.GET)
	public List<OrganizationsDTO> getAssignedOrganization(@PathVariable Long adminId){
		return userManagementService.getAssignedOrganization(adminId);
	}

	/**
	 * API to check email
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/checkemailexistance", method = RequestMethod.GET)
	public ResponseEntity<Message> checkEmailExistance(@RequestParam String email) {
		if (userManagementService.checkEmail(email)) {
			Message errorMessage = Message.statusCode(HttpStatus.INTERNAL_SERVER_ERROR).message("Email already exists")
					.developerMsg("Email already exists in the database").exception("Email already exists").build();
			return new ResponseEntity<Message>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

		} else {
			Message message = Message.statusCode(HttpStatus.OK).message("Email selection success ").build();
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}
	}

	/**
	 * API to save a new User.
	 * 
	 * @param user
	 */
	@RequestMapping(value = { "/createuser" }, method = RequestMethod.POST)
	public ResponseEntity<Message> saveUserData(@RequestBody UserDTO user) {
		LOGGER.info("The user is added: { }", user.toString());
		if (userManagementService.saveUser(user)) {
			emailUtility.sendMailToUser(user.getEmail(), user.getPassword());
			Message message = Message.statusCode(HttpStatus.OK).message("User is created successfully !!").build();
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} else {
			Message errorMessage = Message.statusCode(HttpStatus.INTERNAL_SERVER_ERROR).message("Unable to create user")
					.developerMsg("Unable to create user in DB").exception("Unable to create user").build();
			return new ResponseEntity<Message>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * getActivatedUser() will return the organizations for which the user is
	 * assigned.
	 * 
	 * @param userId
	 */
	@RequestMapping(value = { "/getactiveuser/{userId}" }, method = RequestMethod.GET)
	public List<UserOrgActiveDTO> getActivatedUser(@PathVariable long userId) {
		return userManagementService.getUserOrgActive(userId);
	}

	/**
	 * getUser() will return primary data of user
	 * 
	 * @param userId
	 */
	@RequestMapping(value = { "/getuser/{userId}/{adminId}" }, method = RequestMethod.GET)
	public UserDTO getUser(@PathVariable long userId, @PathVariable long adminId) {
		LOGGER.info("This userId is from controller" + userId + " And admin Id is :" + adminId);
		return userManagementService.getUserById(userId, adminId);

	}

	/**
	 * API to update the existing user.
	 * 
	 * @param user
	 */
	@RequestMapping(value = { "/updateuser" }, method = RequestMethod.PUT)
	public ResponseEntity<Message> updateUserData(@RequestBody UserDTO user) {
		LOGGER.info("The user is updated : {}", user.toString());
		if (userManagementService.updateUser(user)) {
			Message message = Message.statusCode(HttpStatus.OK).message("User is updated successfully !!").build();
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} else {
			Message errorMessage = Message.statusCode(HttpStatus.INTERNAL_SERVER_ERROR).message("Unable to update user")
					.developerMsg("Unable to update user in DB").exception("Unable to update user").build();
			return new ResponseEntity<Message>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getfiltereduserlist", method = RequestMethod.GET)
	public DataTablesOutput getFiltereduser(UserFilterReport.UserFilterReportDtInput filterData) {
		LOGGER.info("User report input :{}", filterData.getRoles());
		DataTablesOutput obj = userFilterReport.fetchData(filterData);
		return obj;
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public ResponseEntity<Message> resetPassword(@RequestParam String userEmail) {
		Message message=null;
		try {
			emailUtility.sendRestPasswordToUser(userEmail);
			message=Message.statusCode(HttpStatus.OK).message("Reset Password Sent successfully !!").build();
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("Exception in resetpassword " + e.getMessage());
			Message errorMessage = Message.statusCode(HttpStatus.INTERNAL_SERVER_ERROR).message("Unable to send reset password !")
					.exception("Unable to send reset password !").build();
			return new ResponseEntity<Message>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
