package com.tf.usermanagement.dao;

import java.util.List;

import com.tf.usermanagement.dto.GroupDto;

public interface GroupAssignmentDao {
    
    List<GroupDto> getAssignedGroup(Long userId,Integer orgId);
    List<GroupDto> getUnAssignedGroup(Long userId,Integer orgId);
    String updateGroup(List<Long> oldGroupList,List<Long> newGroupList,Long userId,Long loginId);
    

}
