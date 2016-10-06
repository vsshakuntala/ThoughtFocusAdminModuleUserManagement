package com.tf.usermanagement.endpoint;

/**
 * @author Biswajit
 */

import java.security.Principal;
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

import com.tf.usermanagement.dto.BillAddressInputDto;
import com.tf.usermanagement.dto.DeAssignUserToOrgInputDto;
import com.tf.usermanagement.dto.DefaultAddressCheckDto;
import com.tf.usermanagement.dto.PaginationDto;
import com.tf.usermanagement.dto.PaginationResult;
import com.tf.usermanagement.dto.ShipAddressInputDto;
import com.tf.usermanagement.dto.UserOrgBillShipSalesAreaDto;
import com.tf.usermanagement.dto.UserOrgMapDto;
import com.tf.usermanagement.errorhandler.Message;
import com.tf.usermanagement.exceptions.EmptyListException;
import com.tf.usermanagement.service.DefaultAddressService;

@RestController
@RequestMapping(value = "/addassgn")
public class DefaultAddressController {
	private static final Logger LOGGER = Logger.getLogger(DefaultAddressController.class);
	@Autowired
	private DefaultAddressService salesCustomerService;

	@RequestMapping(value="/getsalesarealist/{organizationId}", method = RequestMethod.POST)
	public PaginationResult getAllsales(@PathVariable("organizationId") long organizationId,@RequestBody PaginationDto paginationDto) {
	    LOGGER.debug("start of calling getsalesarealist api");	
	    return salesCustomerService.salesgetAll(organizationId,paginationDto);
	}

	@RequestMapping(value="/getcatlist/{salesAreaId}/{userId}" ,method = RequestMethod.POST)
	public ResponseEntity<?> getAllCatList(@PathVariable("salesAreaId") long salesAreaId,
			@PathVariable("userId") long userId,@RequestBody PaginationDto paginationDto) {
		PaginationResult salesCustomerDtoList = null;
		try {
		    LOGGER.debug("start of calling getcatlist api");
			salesCustomerDtoList = salesCustomerService.salescustgetAll(salesAreaId, userId, paginationDto);
			 LOGGER.debug("end of calling getcatlist api");
			return new ResponseEntity<PaginationResult>(salesCustomerDtoList, HttpStatus.OK);
		} catch (EmptyListException e) {
			LOGGER.debug(e.getMessage());
			LOGGER.error(e);
			Message errorMessage = Message.statusCode(HttpStatus.NO_CONTENT).message("List is Empty!")
					.developerMsg("No data in Database").exception(e.getClass().getName()).build();
			throw new EmptyListException(errorMessage, e);
		}

	}

	@RequestMapping(value="/allcustomerlist/{userId}/{organizationId}", method = RequestMethod.POST)
	public ResponseEntity<?> allCustomerList(@PathVariable("userId") long userId,
			@PathVariable("organizationId") long organizationId,
			@RequestBody PaginationDto paginationDto) {
		
		PaginationResult salesCustomerDtoList = null;
		try {
		    LOGGER.debug("start of calling allcustomerlist api");
			salesCustomerDtoList = salesCustomerService.custgetAll(userId,organizationId,paginationDto);
			 LOGGER.debug("end of calling allcustomerlist api");
			return new ResponseEntity<PaginationResult>(salesCustomerDtoList, HttpStatus.OK);
		} catch (EmptyListException e) {
			LOGGER.debug(e.getMessage());
			LOGGER.error(e);
			Message errorMessage = Message.statusCode(HttpStatus.NO_CONTENT).message("List is Empty!")
					.developerMsg("No data in Database").exception(e.getClass().getName()).build();
			throw new EmptyListException(errorMessage, e);
		}
	}

	@RequestMapping("/getbilladd/{customerId}/{addressTypeId}")
	public ResponseEntity<List<BillAddressInputDto>> getAllBillList(@PathVariable("customerId") long customerId,
			@PathVariable("addressTypeId") Integer addressTypeId) {
		List<BillAddressInputDto> billAddressInputDtoList = null;
		try {
		    LOGGER.debug("start of calling getbilladd api");
			billAddressInputDtoList = salesCustomerService.billaddgetAll(customerId, addressTypeId);
			 LOGGER.debug("end of calling getbilladd api");
			return new ResponseEntity<List<BillAddressInputDto>>(billAddressInputDtoList, HttpStatus.OK);
		} catch (EmptyListException e) {
			LOGGER.debug(e.getMessage());
			LOGGER.error(e);
			Message errorMessage = Message.statusCode(HttpStatus.NO_CONTENT).message("List is Empty!")
					.developerMsg("No data in Database").exception(e.getClass().getName()).build();
			throw new EmptyListException(errorMessage, e);
		}
		
	}

	@RequestMapping("/getshipadd/{customerId}/{addressTypeId}")
	public ResponseEntity<List<ShipAddressInputDto>> getAllShipList(@PathVariable("customerId") long customerId,
			@PathVariable("addressTypeId") Integer addressTypeId) {
		List<ShipAddressInputDto> shipAddressInputDtoList = null;
		try {
		    LOGGER.debug("start of calling getshipadd api");
			shipAddressInputDtoList = salesCustomerService.shipaddgetAll(customerId, addressTypeId);
			 LOGGER.debug("end of calling getshipadd api");
			return new ResponseEntity<List<ShipAddressInputDto>>(shipAddressInputDtoList, HttpStatus.OK);
		} catch (EmptyListException e) {
			LOGGER.debug(e.getMessage());
			LOGGER.error(e);
			Message errorMessage = Message.statusCode(HttpStatus.NO_CONTENT).message("List is Empty!")
					.developerMsg("No data in Database").exception(e.getClass().getName()).build();
			throw new EmptyListException(errorMessage, e);
		}
	}

	@RequestMapping("/getuserorgid")
	public List<UserOrgMapDto> getUserOrgId(@RequestBody UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto,Principal principal) {
	    LOGGER.debug("start of calling getuserorgid api");	
	    userOrgBillShipSalesAreaDto.setAdminId(Long.parseLong(principal.getName()));
	    return salesCustomerService.userorgidAll(userOrgBillShipSalesAreaDto);
	}

	@RequestMapping(value = "/getsalesareamap", method = RequestMethod.POST)
	public List<UserOrgBillShipSalesAreaDto> getAllSalesAreaMap(@RequestBody UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto,Principal principal) {
	    LOGGER.debug("start of calling getsalesareamap api");	
	    userOrgBillShipSalesAreaDto.setAdminId(Long.parseLong(principal.getName()));
	    return salesCustomerService.salesareamapAll(userOrgBillShipSalesAreaDto);
	}

	@RequestMapping(value = "/getbillshipmap", method = RequestMethod.POST)
	public List<UserOrgBillShipSalesAreaDto> getAllBillShipMapList(@RequestBody UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto,Principal principal) {
	    LOGGER.debug("start of calling getbillshipmap api");	
	    userOrgBillShipSalesAreaDto.setAdminId(Long.parseLong(principal.getName()));
	    return salesCustomerService.billshipmapAll(userOrgBillShipSalesAreaDto);
	}
	
	@RequestMapping(value = "/saveOrUpdateDefaultAddress", method = RequestMethod.POST)
	private ResponseEntity<Message> saveOrUpdateDefaultAddressForUser(
			@RequestBody DefaultAddressCheckDto defaultAddressCheckDto,Principal principal) {
	    
	    LOGGER.debug("start of calling saveOrUpdateDefaultAddress api");
	    defaultAddressCheckDto.setAdminId(Long.parseLong(principal.getName()));
		if(salesCustomerService.saveOrUpdateDefaultAddressForUser(defaultAddressCheckDto)){
		    LOGGER.debug("end of calling saveOrUpdateDefaultAddress api");
			Message message = Message.statusCode(HttpStatus.OK).message("Default Address for User is assigned successfully")
					.build();
			return new ResponseEntity<Message>(message, HttpStatus.OK);
		}else{
			Message errorMessage = Message.statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
					.message("unable to assign Default Address for User").developerMsg("unable to assign Default Address for User")
					.exception("unable to assign Default Address for User").build();
			return new ResponseEntity<Message>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
