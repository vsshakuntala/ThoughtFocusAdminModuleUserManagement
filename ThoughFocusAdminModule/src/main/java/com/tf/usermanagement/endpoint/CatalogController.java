package com.tf.usermanagement.endpoint;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tf.usermanagement.exception.CatalogException;
import com.tf.usermanagement.service.CatalogService;

@RestController
@RequestMapping("/catalogmgmt")
public class CatalogController {

	private static final Logger LOGGER = Logger.getLogger(CatalogController.class);

	@Autowired
	private CatalogService catalogService;

	@RequestMapping(value = "/catalogcount/{orgId}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getCatalogCountForOrg(@PathVariable Long orgId) {
		Integer count = null;
		try {
		    LOGGER.debug("start of calling catalogcount api");
			count = catalogService.catalogCountBasedOnOrg(orgId);
			  LOGGER.debug("end of calling catalogcount api");
			if (count != null) {
				return new ResponseEntity<Integer>(count, HttpStatus.OK);
			}
			return new ResponseEntity<Integer>(Integer.valueOf("No Machines For Selected Org").intValue(),
					HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (CatalogException e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<Integer>(Integer.valueOf("No Machines For Selected Org").intValue(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/catalogcountforactiveorg/{orgId}/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Integer> getCatalogCountForActiveOrg(@PathVariable long orgId, @PathVariable long userId) {
		Integer count = null;
		try {
		    LOGGER.debug("start of calling catalogcountforactiveorg api");
			count = catalogService.getCatalogCount(orgId, userId);
			  LOGGER.debug("end of calling catalogcountforactiveorg api");
			return new ResponseEntity<Integer>(count, HttpStatus.OK);
		} catch (CatalogException e) {
			LOGGER.error(e.getMessage());

			return new ResponseEntity<Integer>(HttpStatus.NO_CONTENT);
		}

	}
	
	@RequestMapping(value = "/catalogAssignedCount/{orgId}/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Long> getCatalogAssignedCount(@PathVariable long orgId, @PathVariable long userId){
		Long count = null;
		try {
		    LOGGER.debug("start of calling catalogAssignedCount api");
			count = catalogService.getCatalogAssignedCount(orgId, userId);
			  LOGGER.debug("end of calling catalogAssignedCount api");
			return new ResponseEntity<Long>(count, HttpStatus.OK);
		} catch (CatalogException e) {
			LOGGER.error(e.getMessage());

			return new ResponseEntity<Long>(HttpStatus.NO_CONTENT);
		}

	}
	
	



}

