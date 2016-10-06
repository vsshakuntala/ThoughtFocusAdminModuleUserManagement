package com.tf.usermanagement.service;

import java.util.List;

import com.tf.usermanagement.dto.AdminOrgListDto;
import com.tf.usermanagement.dto.LanguageDTO;
import com.tf.usermanagement.dto.OrganizationsDTO;
import com.tf.usermanagement.dto.UserDTO;
import com.tf.usermanagement.dto.UserEmail;
import com.tf.usermanagement.dto.UserOrgActiveDTO;

/**
 * @author Narasingha
 *
 */
public interface UserManagementService {
	
	public List<OrganizationsDTO> getDivisions();

	public List<LanguageDTO> getLanguages();
	
	public boolean saveUser(UserDTO user);
	
	public List<UserOrgActiveDTO> getUserOrgActive(Long userId);
	
	public UserDTO getUserById(Long userId,Long adminId);
	
	public boolean updateUser(UserDTO user);
	
	public boolean checkEmail(String email);
	
	public List<AdminOrgListDto> getOrganizationListOfAdmin(long adminId);

	List<OrganizationsDTO> getAssignedOrganization(long adminId);
	
	void resetPassword(String email);
	
	public UserEmail getUserEmailByUserId(Long userId);
}
