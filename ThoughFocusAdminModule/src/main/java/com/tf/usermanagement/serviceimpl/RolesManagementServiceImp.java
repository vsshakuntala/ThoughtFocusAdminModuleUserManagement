package com.tf.usermanagement.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tf.usermanagement.dao.RoleManagementDao;
import com.tf.usermanagement.dto.PermissionsDto;
import com.tf.usermanagement.dto.RoleJsonDto;
import com.tf.usermanagement.dto.RolesDto;
import com.tf.usermanagement.exceptions.EmptyListException;
import com.tf.usermanagement.service.RolesManagementService;

@Service
public class RolesManagementServiceImp implements RolesManagementService {

	@Autowired
	private RoleManagementDao roleMgmtDao;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RolesManagementServiceImp.class);
	
	@Override
	public List<RolesDto> getAssignedRoles(Long userId, Long orgId) {
		List<RolesDto> RolesDtoList = new ArrayList<>();
		RolesDtoList=roleMgmtDao.getAssignedRoles(userId, orgId);
		return RolesDtoList;
	}

	@Override
	public List<RolesDto> getUnAssignedRoles(Long userId, Long orgId) {
		List<RolesDto> RolesDtoList = new ArrayList<>();
		RolesDtoList=roleMgmtDao.getUnAssignedRoles(userId, orgId);
		return RolesDtoList;
	}

	public List<PermissionsDto> getRolePermissions(Long roleId){
		List<PermissionsDto> PermissionsDtoList = new ArrayList<>();
		PermissionsDtoList=roleMgmtDao.getRolePermissions(roleId);
		if(PermissionsDtoList!=null && !PermissionsDtoList.isEmpty()){
		return PermissionsDtoList;
		}
		else{
			throw new EmptyListException(EmptyListException.EMPTY_LIST);
		}
	}

	@Override
	public boolean updateRole(String jsonData, long userId, long orgId) {
		List<Long> oldRoleList = new ArrayList<Long>();
		List<Long> dupOldRoleList = new ArrayList<Long>();
		List<Long> newRoleList = new ArrayList<Long>();
		
		LOGGER.info("jsonData "+jsonData);
		
		Gson gson = new Gson();
		RoleJsonDto roleList= gson.fromJson(jsonData, RoleJsonDto.class);
		
		if(roleList.getParams().getAssigned() != null && !roleList.getParams().getAssigned().isEmpty()){
		    
		    for(RolesDto role :roleList.getParams().getAssigned()){
			oldRoleList.add(role.getRoleId());
			dupOldRoleList.add(role.getRoleId());
		    }
		}
		
		if(roleList.getParams().getUnAssigned() != null && !roleList.getParams().getUnAssigned().isEmpty()){
		    
		    for(RolesDto grp :roleList.getParams().getUnAssigned()){
		    	newRoleList.add(grp.getRoleId());
		    }
		}
		
		LOGGER.info("before removing oldGrpList "+oldRoleList);
		LOGGER.info("before removing newGrpList "+newRoleList);
		
		//oldRoleList.removeAll(newRoleList);
		//newRoleList.removeAll(dupOldRoleList);

		LOGGER.info("after removing oldGrpList "+oldRoleList);
		LOGGER.info("after removing newGrpList "+newRoleList);
		
		boolean msg =roleMgmtDao.updateRole(oldRoleList,newRoleList, userId, orgId);
		
		return msg;
	}
	
	@Override
	public List<PermissionsDto> getPermissionForLogedInUser(String adminemail) {
		return roleMgmtDao.getPermissionForLogedInUser(adminemail);
	}

}
