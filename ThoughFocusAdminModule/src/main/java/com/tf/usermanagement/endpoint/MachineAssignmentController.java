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
import com.tf.usermanagement.dto.CatalogCountForOrgDto;
import com.tf.usermanagement.dto.ResponseMessage;
import com.tf.usermanagement.exception.CatalogException;
import com.tf.usermanagement.exceptions.InsufficientDataException;
import com.tf.usermanagement.report.MachineReport;
import com.tf.usermanagement.report.MachineUnAssignmentReport;
import com.tf.usermanagement.service.MachineAssignmentService;

@RestController
public class MachineAssignmentController {

	@Autowired
	private MachineReport machineAssignmentReport;

	@Autowired
	private MachineUnAssignmentReport machineUnAssignmentReport;

	@Autowired
	private MachineAssignmentService machineAssignmentService;

	private static final Logger LOGGER = LoggerFactory.getLogger(MachineAssignmentController.class);

	/**
	 * used to remove the customer for a user input- user id, list of customers
	 * which have to remove for user
	 * 
	 */
	@RequestMapping(value = "/removemachine/{user_id}/{org_id}/{login_user_id}", method = RequestMethod.POST)
	public ResponseEntity<?> removeMachine(@PathVariable("user_id") Long userId, @PathVariable("org_id") Long orgId,
			@PathVariable("login_user_id") Long loginUserId, @RequestBody String assignedCatalogList) {
		LOGGER.info("In removeMachine :" + assignedCatalogList);
		try {
			String msg = machineAssignmentService.removeMachine(loginUserId, userId, orgId, assignedCatalogList);

			return new ResponseEntity<>(new ResponseMessage(msg), HttpStatus.OK);

		} catch (NullPointerException e) {
			return new ResponseEntity<>(new ResponseMessage("error while removing the selected customer"),
					HttpStatus.BAD_REQUEST);
		} catch (InsufficientDataException e) {
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("error while removing the customer"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/downloadmachinedocument/{user_id}/{organization_id}/{login_user_id}", method = RequestMethod.POST)
	public ResponseEntity<?> downloadDocument(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") Long userId, @PathVariable("organization_id") Long orgId,
			@PathVariable("login_user_id") Long loginUserId, @RequestBody String searhfilter) {
		try {
			LOGGER.info("in download machines");

			LOGGER.info(
					"userId " + userId + " orgId " + orgId + " loginId " + loginUserId + "searhfilter|" + searhfilter);

			return new ResponseEntity<>(
					machineAssignmentService.downloadMachineResult(response, loginUserId, userId, orgId, searhfilter),
					HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error("error while downloading the fiel" + e.getMessage());
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		}

	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/machineassignmentreport", method = RequestMethod.GET)
	public DataTablesOutput empReport(MachineReport.MachineAssignmentDtInput inputDt) {
		DataTablesOutput obj = machineAssignmentReport.fetchData(inputDt);
		return obj;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/machineunassignmentreport", method = RequestMethod.GET)
	public DataTablesOutput empReport(MachineUnAssignmentReport.MachineAssignmentDtInput inputDt) {
		DataTablesOutput obj = machineUnAssignmentReport.fetchData(inputDt);
		return obj;
	}

	@RequestMapping(value = "/assignmachine/{user_id}/{org_id}/{logIn_user_id}", method = RequestMethod.POST)
	public ResponseEntity<?> assignMachine(@PathVariable("user_id") Long userId, @PathVariable("org_id") Long orgId,
			@PathVariable("logIn_user_id") Long loginUserId, @RequestBody String assignedCatalogList) {

		try {
			String msg = machineAssignmentService.assignMachine(loginUserId, userId, orgId, assignedCatalogList);

			return new ResponseEntity<>(new ResponseMessage(msg), HttpStatus.OK);

		} catch (NullPointerException e) {
			return new ResponseEntity<>(new ResponseMessage("error while assigning the selected machine"),
					HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("error while assigning the machine"),
					HttpStatus.BAD_REQUEST);
		}

	}

	/**
	 * used to assign all customer for a user input- user id, list of all
	 * customers which have to assign for user
	 * 
	 */
	@RequestMapping(value = "/assignallmachine/{userId}/{organizationId}/{loginUserId}", method = RequestMethod.POST)
	public ResponseEntity<?> assignAllMachine(@PathVariable("loginUserId") Long loginUserId,
			@PathVariable("userId") Long userId, @PathVariable("organizationId") Long organizationId,
			@RequestBody String machine) {
		try {
			String msg = machineAssignmentService.assignAllMachine(loginUserId, userId, organizationId, machine);

			return new ResponseEntity<>(new ResponseMessage(msg), HttpStatus.OK);

		} catch (NullPointerException e) {
			return new ResponseEntity<>(new ResponseMessage("error while assigning the all machine"),
					HttpStatus.BAD_REQUEST);
		} catch (InsufficientDataException e) {
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("error while assigning the machines"),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * used to remove all customer for a user input- user id, list of all
	 * customers which have to remove for user
	 * 
	 */
	@RequestMapping(value = "/removeallmachine/{userId}/{organizationId}/{loginUserId}", method = RequestMethod.POST)
	public ResponseEntity<?> removeAllMachine(@PathVariable("loginUserId") Long loginUserId,
			@PathVariable("userId") Long userId, @PathVariable("organizationId") Long organizationId,
			@RequestBody String machine) {
		try {
			String msg = machineAssignmentService.removeAllMachine(loginUserId, userId, organizationId, machine);

			return new ResponseEntity<>(new ResponseMessage(msg), HttpStatus.OK);

		} catch (NullPointerException e) {
			return new ResponseEntity<>(new ResponseMessage("error while removing all machine"),
					HttpStatus.BAD_REQUEST);
		} catch (InsufficientDataException e) {
			return new ResponseEntity<>(new ResponseMessage(e.getMessage()), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("error while removing the machine"),
					HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/getCatalogCount/{usr_id}/{org_id}", method = RequestMethod.GET)
	public CatalogCountForOrgDto getCatalogCount(@PathVariable("usr_id") Long usrId,
			@PathVariable("org_id") Long orgId) {
		try {
			CatalogCountForOrgDto catalogCount = machineAssignmentService.getCatalogsCountForOrganization(usrId, orgId);
			return catalogCount;

		} catch (NullPointerException e) {
			return null;
		} catch (Exception e) {
			return null;
		}
	}
	
	@RequestMapping(value = "/catalogAssignedCount/{orgId}/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Long> getCatalogAssignedCount(@PathVariable long orgId, @PathVariable long userId){
		Long count = null;
		try {
			
			count = machineAssignmentService.getCatalogAssignedCount(orgId, userId);
			System.out.println("COUNT in controller:"+count);
			return new ResponseEntity<Long>(count, HttpStatus.OK);
		} catch (CatalogException e) {
			LOGGER.error(e.getMessage());

			return new ResponseEntity<Long>(HttpStatus.NO_CONTENT);
		}

	}

}
