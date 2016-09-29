package com.tf.usermanagement.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tf.usermanagement.dao.GroupAssignmentDao;
import com.tf.usermanagement.dto.GroupAllDto;
import com.tf.usermanagement.dto.GroupDto;
import com.tf.usermanagement.dto.GroupJsonDto;
import com.tf.usermanagement.service.GroupAssignmentService;

/**
 * 
 * @author Santosh
 *
 */

@Service
public class GroupAssignmentServiceImpl implements GroupAssignmentService{

    @Autowired
    private GroupAssignmentDao groupAssignmentDao;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupAssignmentServiceImpl.class);
    
    /**
     * This method is used to get the all assigned group for a user based on division id
     *    
     */
    @Override
    public List<GroupDto> getAssignedGroup(Long userId,Integer orgId) {
	List<GroupDto> grpAssignedList = groupAssignmentDao.getAssignedGroup(userId,orgId);
	return grpAssignedList;
    }

    /**
     * This method is used to get the all unassigned group for a user based on division id
     */
    @Override
    public List<GroupDto> getUnAssignedGroup(Long userId,Integer orgId) {
	List<GroupDto> grpUnAssignedList = groupAssignmentDao.getUnAssignedGroup(userId,orgId);
	return grpUnAssignedList;
    }

    /**
     * This method is used to get the all assigned and unassigned group for a user based on division id
     *    
     */
    
    @Override
    public GroupAllDto getAllGroup(Long userId,Integer orgId) {
	
	GroupAllDto grpList= new GroupAllDto();
	
	List<GroupDto> grpAssignedList = groupAssignmentDao.getAssignedGroup(userId,orgId);
	
	if(grpAssignedList != null)
	
	grpList.setAssigned(grpAssignedList);
	grpList.setUnAssigned(groupAssignmentDao.getUnAssignedGroup(userId,orgId));;
	
	return grpList;
    }

    /**
     * This method is used to update the group for a user
     */
    @Override
    public String updateGroup(String jsonData,Long userId,Long loginId) {
	
	List<Long> oldGrpList = new ArrayList<Long>();
	List<Long> dupOldGrpList = new ArrayList<Long>();
	List<Long> newGrpList = new ArrayList<Long>();
	
	
	LOGGER.info("jsonData "+jsonData);
	
	Gson gson = new Gson();
	GroupJsonDto grpList= gson.fromJson(jsonData, GroupJsonDto.class);
	
	if(grpList.getParams().getAssigned() != null && !grpList.getParams().getAssigned().isEmpty()){
	    
	    for(GroupDto grp :grpList.getParams().getAssigned()){
		oldGrpList.add(grp.getGroupId());
		dupOldGrpList.add(grp.getGroupId());
	    }
	}
	
	if(grpList.getParams().getUnAssigned() != null && !grpList.getParams().getUnAssigned().isEmpty()){
	    
	    for(GroupDto grp :grpList.getParams().getUnAssigned()){
		newGrpList.add(grp.getGroupId());
	    }
	}
	
	
	LOGGER.info("before removing oldGrpList "+oldGrpList);
	LOGGER.info("before removing newGrpList "+newGrpList);
	
	oldGrpList.removeAll(newGrpList);
	newGrpList.removeAll(dupOldGrpList);

	LOGGER.info("after removing oldGrpList "+oldGrpList);
	LOGGER.info("after removing newGrpList "+newGrpList);
	
	String msg =groupAssignmentDao.updateGroup(oldGrpList,newGrpList,userId,loginId);
	
	return msg;
    }
        

}
