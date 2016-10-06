package com.tf.usermanagement.endpoint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tf.usermanagement.dto.RoleDto;
import com.tf.usermanagement.service.RoleService;

/**
 * 
 * @author Rajendra
 *
 */
@RestController
@RequestMapping("/role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserManagementController.class);

	@RequestMapping(value = { "/getallroles" }, method = RequestMethod.GET)
	public List<RoleDto> getDivisions() {
	    LOGGER.debug("start of calling getallroles api");
		List<RoleDto> rolIdNameList = roleService.getRoles();
		 LOGGER.debug("end of calling getallroles api");
		return rolIdNameList;
	}
}
