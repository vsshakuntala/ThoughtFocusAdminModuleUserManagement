package com.tf.usermanagement.service;

import java.util.List;

import com.tf.usermanagement.dto.PermissionsDto;
import com.tf.usermanagement.dto.RolesDto;

public interface RolesManagementService {

	List<RolesDto> getAssignedRoles(Long userId,Long orgId);
    List<RolesDto> getUnAssignedRoles(Long userId,Long orgId);
    public List<PermissionsDto> getRolePermissions(Long roleId);
    
    public boolean updateRole(String roleDtoList,long userId,long orgId);
    List<PermissionsDto> getPermissionForLogedInUser(String adminemail);
	
}
