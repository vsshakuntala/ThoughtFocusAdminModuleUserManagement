package com.tf.usermanagement.endpoint;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tf.usermanagement.dto.DivisionDto;
import com.tf.usermanagement.service.DivisionService;

/**
 * 
 * @author Rajendra
 *
 */
@RestController
public class DivisionController {
    
    private static final Logger LOGGER = Logger.getLogger(DivisionController.class);
	@Autowired
	private DivisionService divisionService;
	@RequestMapping(value="/getalldivisions",method=RequestMethod.GET)
	public List<DivisionDto> getDivisions(){
	    LOGGER.debug("start of calling getalldivisions api");
		List<DivisionDto> orgIdNameList = divisionService.getAllDivision();
		 LOGGER.debug("start of calling getalldivisions api");
		return orgIdNameList;
	}
}
