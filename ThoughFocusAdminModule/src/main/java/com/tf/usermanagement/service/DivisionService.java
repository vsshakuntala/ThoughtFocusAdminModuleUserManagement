package com.tf.usermanagement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.usermanagement.dao.DivisionDao;
import com.tf.usermanagement.dto.DivisionDto;

/**
 * 
 * @author Rajendra
 *
 */
@Service
public class DivisionService {
	@Autowired
	private DivisionDao divisionDao;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DivisionService.class);
	public List<DivisionDto> getAllDivision(){
		return divisionDao.getDivisions();
		
	}
}
