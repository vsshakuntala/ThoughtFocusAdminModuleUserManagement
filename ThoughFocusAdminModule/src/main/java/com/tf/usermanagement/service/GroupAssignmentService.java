package com.tf.usermanagement.service;

import java.util.List;

import com.tf.usermanagement.dto.GroupAllDto;
import com.tf.usermanagement.dto.GroupDto;

public interface GroupAssignmentService {

    List<GroupDto> getAssignedGroup(Long userId,Integer orgId);
    List<GroupDto> getUnAssignedGroup(Long userId,Integer orgId);
    GroupAllDto getAllGroup(Long userId,Integer orgId);
    String updateGroup(String jsonData,Long userId,Long loginId);
}
