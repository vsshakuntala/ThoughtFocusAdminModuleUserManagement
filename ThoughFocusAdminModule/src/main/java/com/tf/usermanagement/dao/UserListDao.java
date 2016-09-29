package com.tf.usermanagement.dao;

import java.util.List;

import com.tf.usermanagement.dto.UserListResultDto;

public interface UserListDao {
	List<UserListResultDto> getAllUsers(String query);

	List<String> getOrganizationNameById(List<Integer> organizationIds);
}
