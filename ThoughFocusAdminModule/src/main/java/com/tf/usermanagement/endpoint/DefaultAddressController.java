package com.tf.usermanagement.endpoint;

/**
 * @author Biswajit
 */

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
			return salesCustomerService.salesgetAll(organizationId,paginationDto);
	}

	@RequestMapping(value="/getcatlist/{salesAreaId}/{userId}" ,method = RequestMethod.POST)
	public ResponseEntity<?> getAllCatList(@PathVariable("salesAreaId") long salesAreaId,
			@PathVariable("userId") long userId,@RequestBody PaginationDto paginationDto) {
		PaginationResult salesCustomerDtoList = null;
		try {
			salesCustomerDtoList = salesCustomerService.salescustgetAll(salesAreaId, userId, paginationDto);
			return new ResponseEntity<PaginationResult>(salesCustomerDtoList, HttpStatus.OK);
		} catch (EmptyListException e) {
			LOGGER.debug(e.getMessage());
			LOGGER.error(e);
			Message errorMessage = Message.statusCode(HttpStatus.NO_CONTENT).message("List is Empty!")
					.developerMsg("No data in Database").exception(e.getClass().getName()).build();
			throw new EmptyListException(errorMessage, e);
		}

	}

	@RequestMapping(value="/allcustomerlist/{userId}", method = RequestMethod.POST)
	public ResponseEntity<?> allCustomerList(@PathVariable("userId") long userId,@RequestBody PaginationDto paginationDto) {
		
		PaginationResult salesCustomerDtoList = null;
		try {
			salesCustomerDtoList = salesCustomerService.custgetAll(userId,paginationDto);
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
			billAddressInputDtoList = salesCustomerService.billaddgetAll(customerId, addressTypeId);
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
			shipAddressInputDtoList = salesCustomerService.shipaddgetAll(customerId, addressTypeId);
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
	public List<UserOrgMapDto> getUserOrgId(@RequestBody UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto) {
			return salesCustomerService.userorgidAll(userOrgBillShipSalesAreaDto);
	}

	@RequestMapping(value = "/getsalesareamap", method = RequestMethod.POST)
	public List<UserOrgBillShipSalesAreaDto> getAllSalesAreaMap(@RequestBody UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto) {
			return salesCustomerService.salesareamapAll(userOrgBillShipSalesAreaDto);
	}

	@RequestMapping(value = "/getbillshipmap", method = RequestMethod.POST)
	public List<UserOrgBillShipSalesAreaDto> getAllBillShipMapList(@RequestBody UserOrgBillShipSalesAreaDto userOrgBillShipSalesAreaDto) {
			return salesCustomerService.billshipmapAll(userOrgBillShipSalesAreaDto);
	}

}
