package com.tf.usermanagement.dao;

import java.util.List;

import com.tf.usermanagement.dto.RoleDto;

/**
 * 
 * @author Rajendra
 *
 */
public interface RoleDao {

	List<RoleDto> getRolNameAndId();
}
