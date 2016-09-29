package com.tf.usermanagement.dao;

import java.util.List;

import com.tf.usermanagement.dto.DivisionsDTO;
import com.tf.usermanagement.dto.LanguageDTO;
import com.tf.usermanagement.dto.OrganizationsDTO;
import com.tf.usermanagement.dto.UserDTO;
import com.tf.usermanagement.dto.UserOrgActiveDTO;
import com.tf.usermanagement.dto.UserUnassignedOrgDto;


/**
 * @author Narasingha
 *
 */
public interface UserManagementDao {
	
	public List<OrganizationsDTO> getOrgNameAndId();

	public List<LanguageDTO> getLanguages();
	
	public boolean saveUser(UserDTO user);
	
	public List<UserOrgActiveDTO> getUserOrgActive(Long userId);
	
	public UserDTO getUserById(Long userId);
	
	public boolean updateUser(UserDTO user);
	
	public boolean updateUserOrganizations(List<DivisionsDTO> userOrgs,Long userId,Long currentLoggedUserId);
	
	public boolean upateUserPreferences(Long userId,Long  languageId,Long currentLoggedUserId);
	
	public boolean checkEmail(String email);

	List<OrganizationsDTO> getAssignedOrganization(long adminId);
}
