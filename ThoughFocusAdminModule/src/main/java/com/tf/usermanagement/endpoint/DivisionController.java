package com.tf.usermanagement.endpoint;

import java.util.List;

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
	@Autowired
	private DivisionService divisionService;
	@RequestMapping(value="/getalldivisions",method=RequestMethod.GET)
	public List<DivisionDto> getDivisions(){
		List<DivisionDto> orgIdNameList = divisionService.getAllDivision();
		return orgIdNameList;
	}
}
