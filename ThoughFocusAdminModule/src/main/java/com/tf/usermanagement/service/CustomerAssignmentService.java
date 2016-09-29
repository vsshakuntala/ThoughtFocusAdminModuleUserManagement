package com.tf.usermanagement.service;

import javax.servlet.http.HttpServletResponse;

import com.tf.usermanagement.dto.CustomerCount;


public interface CustomerAssignmentService {
    
    String assignCustomer(Long loginUserId,Long userId,Long orgId,String customerList) throws Exception;
    String removeCustomer(Long loginUserId,Long userId,Long orgId,String customerList) throws Exception;
    String assignAllCustomer(Long loginUserId,Long userId,Long orgId,String customer);
    String removeAllCustomer(Long loginUserId,Long userId,Long orgId,String customer);
    String downloadCustomerResult(HttpServletResponse response,Long loginUserId, Long userId, Long orgId,
	    String searhfilter);
    CustomerCount getavailableCustomerCount(Long userId, Long orgId);
    CustomerCount getAssignedCustomerCount(Long userId, Long orgId);

}
