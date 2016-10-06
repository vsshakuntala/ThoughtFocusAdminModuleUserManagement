package com.tf.usermanagement.endpoint;

import java.security.Principal;
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
import com.tf.usermanagement.dto.UserEmail;
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
	    LOGGER.debug("start of calling organizations api");
		List<OrganizationsDTO> orgIdNameList = userManagementService.getDivisions();
		 LOGGER.debug("end of calling organizations api");
		return orgIdNameList;
	}

	/**
	 * getLanguageName() will return the languageId and languageDescription
	 */
	@RequestMapping(value = { "/languages" }, method = RequestMethod.GET)
	public List<LanguageDTO> getLanguageName() {
	    LOGGER.debug("start of calling languages api");
		List<LanguageDTO> langNameList = userManagementService.getLanguages();
		 LOGGER.debug("end of calling languages api");
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
	public List<OrganizationsDTO> getAssignedOrganization(@PathVariable Long adminId,Principal principal){
	    LOGGER.debug("start of calling getAssignedOrganization api");
	    adminId=Long.parseLong(principal.getName());
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
	    LOGGER.debug("start of calling checkemailexistance api");
		if (userManagementService.checkEmail(email)) {
		    LOGGER.debug("end of calling checkemailexistance api");
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
	public ResponseEntity<Message> saveUserData(@RequestBody UserDTO user,Principal principal) {
		user.setCurrentLoggedUserId(Long.parseLong(principal.getName()));
		LOGGER.info("The user is added: { }", user.toString());
		 LOGGER.debug("start of calling createuser api");
		if (userManagementService.saveUser(user)) {
			emailUtility.sendMailToUser(user.getEmail(), user.getPassword());
			 LOGGER.debug("end of calling createuser api");
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
	    LOGGER.debug("start of calling getactiveuser api");
		return userManagementService.getUserOrgActive(userId);
	}

	/**
	 * getUser() will return primary data of user
	 * 
	 * @param userId
	 */
	@RequestMapping(value = { "/getuser/{userId}/{adminId}" }, method = RequestMethod.GET)
	public UserDTO getUser(@PathVariable long userId, @PathVariable long adminId,Principal principal) {
		adminId=Long.parseLong(principal.getName());
		LOGGER.info("This userId is from controller" + userId + " And admin Id is :" + adminId);
		LOGGER.debug("start of calling getuser api");
		return userManagementService.getUserById(userId, adminId);

	}
	
	/**
	 * getUserEmailByUserId() will return email of user
	 * 
	 * @param userId
	 */
	@RequestMapping(value = { "/getuseremailbyid/{adminId}" }, method = RequestMethod.GET)
	public UserEmail getUserEmailByUserId(@PathVariable long adminId,Principal principal) {
		LOGGER.info("getUserEmailByUserId controller" + adminId );
		adminId=Long.parseLong(principal.getName());
		return userManagementService.getUserEmailByUserId(adminId);

	}

	/**
	 * API to update the existing user.
	 * 
	 * @param user
	 */
	@RequestMapping(value = { "/updateuser" }, method = RequestMethod.PUT)
	public ResponseEntity<Message> updateUserData(@RequestBody UserDTO user,Principal principal) {
		LOGGER.info("The user is updated : {}", user.toString());
		LOGGER.debug("start of calling updateuser api");
		user.setCurrentLoggedUserId(Long.parseLong(principal.getName()));
		if (userManagementService.updateUser(user)) {
		    LOGGER.debug("end of calling updateuser api");
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
		 LOGGER.debug("start of calling getfiltereduserlist api");
		DataTablesOutput obj = userFilterReport.fetchData(filterData);
		 LOGGER.debug("end of calling getfiltereduserlist api");
		return obj;
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public ResponseEntity<Message> resetPassword(@RequestParam String userEmail) {
		Message message=null;
		try {
		    LOGGER.debug("start of calling resetpassword api");
			userManagementService.resetPassword(userEmail);
			 LOGGER.debug("end of calling resetpassword api");
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
