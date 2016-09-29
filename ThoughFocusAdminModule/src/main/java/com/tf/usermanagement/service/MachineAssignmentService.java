package com.tf.usermanagement.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.tf.usermanagement.dto.CatalogCountForOrgDto;

public interface MachineAssignmentService {
	String assignMachine(Long loginUserId,Long userId,Long orgId,String catalogList) throws Exception;
    String removeMachine(Long loginUserId,Long userId,Long orgId,String catalogList) throws Exception;
   String assignAllMachine(Long loginUserId,Long userId,Long orgId,String machine);
    String removeAllMachine(Long loginUserId,Long userId,Long orgId,String machine);
	Object downloadMachineResult(HttpServletResponse response, Long loginUserId, Long userId, Long orgId,
			String searhfilter);
	CatalogCountForOrgDto getCatalogsCountForOrganization(Long usrId,Long orgId);
	
	Long getCatalogAssignedCount(Long orgId,Long userId);

}
