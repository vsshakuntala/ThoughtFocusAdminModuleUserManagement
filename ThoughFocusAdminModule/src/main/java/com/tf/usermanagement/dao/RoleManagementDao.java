package com.tf.usermanagement.dao;

import java.util.List;

import com.tf.usermanagement.dto.PermissionsDto;
import com.tf.usermanagement.dto.RolesDto;

public interface RoleManagementDao {

	List<RolesDto> getAssignedRoles(Long userId,Long orgId);
    List<RolesDto> getUnAssignedRoles(Long userId,Long orgId);
    public List<PermissionsDto> getRolePermissions(long roleId);
    
    public boolean updateRole(List<Long> oldGroupList,List<Long> newGroupList,long userId,long orgId);
	List<PermissionsDto> getPermissionForLogedInUser(String adminemail);
    
}
