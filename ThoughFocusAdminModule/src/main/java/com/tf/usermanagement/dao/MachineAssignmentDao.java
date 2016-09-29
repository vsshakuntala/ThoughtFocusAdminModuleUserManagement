package com.tf.usermanagement.dao;

import java.util.List;

import com.tf.usermanagement.dto.CatalogCountForOrgDto;
import com.tf.usermanagement.dto.MachineDownloadDto;

public interface MachineAssignmentDao {
	
	String assignMachine(Long loginUserId,Long userId,Long orgId,List<Long> machineList);
    String removeMachine(Long loginUserId,Long userId,Long orgId,List<Long> machineList);
    String assignAllMachine(Long loginUserId,Long userId,String query,Long org_id);
    String removeAllMachine(Long loginUserId,Long userId,String query);
    String updateAllMachine(Long loginUserId,Long userId,List<Long> catIdList);
	List<Long> getUnassignedListCat(String query);
	List<MachineDownloadDto> getAllAssignedMachineList(String query);
	List<MachineDownloadDto> getAllUnAssignedMachineList(String query); 
	CatalogCountForOrgDto getCatalogsCountForOrganization(Long usrId,Long orgId);
	Long getCatalogAssignedCount(Long orgId, Long userId);
}
