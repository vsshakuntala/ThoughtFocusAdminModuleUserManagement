package com.tf.usermanagement.service;

import java.util.List;

import com.tf.usermanagement.dto.AdminOrgListDto;
import com.tf.usermanagement.dto.AssignOrgDto;
import com.tf.usermanagement.dto.CatalogTotalCountDto;
import com.tf.usermanagement.dto.CustomerCatalogCountDto;
import com.tf.usermanagement.dto.CustomerTotalCountDto;
import com.tf.usermanagement.dto.DeAssignUserToOrgInputDto;
import com.tf.usermanagement.dto.DefaultAddressCheckDto;
import com.tf.usermanagement.dto.DefaultAddressCountDto;
import com.tf.usermanagement.dto.DivisionAssignmentDto;
import com.tf.usermanagement.dto.GroupCatalogCountDto;
import com.tf.usermanagement.dto.GroupCustomerCountDto;
import com.tf.usermanagement.dto.GroupTotalCountDto;
import com.tf.usermanagement.dto.OrganizationDTO;
import com.tf.usermanagement.dto.OrganizationRoleCountDto;
import com.tf.usermanagement.dto.UserNotesDto;
import com.tf.usermanagement.dto.UserUnassignedOrgDto;

public interface DivisionMgmtService {

	public List<OrganizationDTO> getAllOrganizations();

	public List<GroupCustomerCountDto> getCustomerCountForGroupsByOrganization(long userId);

	public List<GroupCatalogCountDto> getCatalogCountForGroupsByOrganization(long userId);

	public List<CustomerCatalogCountDto> getCustomerAndCatalogCount(long userId);

	//new API's for customer and catalog
	public List<CustomerTotalCountDto> getTotalCustomerCount(long userId);
	
	public List<CatalogTotalCountDto> getTotalCatalogCount(long userId);
	
	public List<GroupTotalCountDto> getGroupCount(long userId);
	
	public List<OrganizationRoleCountDto> getRoleCountByOrganization(long userId);
	
	public List<DefaultAddressCountDto> getDefaultAddressCountByOrganization(long userId);

	public List<DivisionAssignmentDto> getAllDivisionAssignment(long userId, long adminId);

	public List<UserUnassignedOrgDto> getUnassignedOrganizationsForUser(long userId, long adminId);

	public boolean deAssignAllAllocationsForUserByOrganization(DeAssignUserToOrgInputDto deAssignUserToOrgInputDto);

	public List<AdminOrgListDto> getOrganizationListOfAdmin(long adminId);

	public boolean insertOrUpdateUserOrganizationMap(AssignOrgDto deAssignUserToOrgInputDto);

	public List<UserNotesDto> getNotesListForUser(long userId);

	public boolean addNotesToUser(UserNotesDto userNotesDto);
	
	public DefaultAddressCheckDto getDefaultAddressForUserOrg(long userOrgId);
	
	
}
