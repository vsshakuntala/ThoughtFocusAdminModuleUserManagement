package com.tf.usermanagement.serviceimpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tf.usermanagement.dao.RoleDao;
import com.tf.usermanagement.dto.RoleDto;
import com.tf.usermanagement.service.RoleService;

/**
 * 
 * @author Rajendra
 *
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(RoleServiceImpl.class);

	@Override
	public List<RoleDto> getRoles() {
		List<RoleDto> rolIdNameList = roleDao.getRolNameAndId();
		LOGGER.info("Role list size :{}", rolIdNameList.size());
		return rolIdNameList;
	}
}
