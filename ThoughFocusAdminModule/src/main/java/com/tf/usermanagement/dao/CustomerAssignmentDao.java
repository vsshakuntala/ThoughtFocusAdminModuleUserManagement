package com.tf.usermanagement.dao;

import java.util.List;

import com.tf.usermanagement.dto.CustomerCount;
import com.tf.usermanagement.dto.CustomerDownloadDto;

public interface CustomerAssignmentDao {

    String assignCustomer(Long loginUserId,Long userId,List<Long> customerList);
    String removeCustomer(Long loginUserId,Long userId,List<Long> customerList);
    String assignAllCustomer(Long loginUserId,Long userId,String query,List<Long> custIdList);
    String removeAllCustomer(Long loginUserId,Long userId,String query);
    String updateAllCustomer(Long loginUserId,Long userId,List<Long> custIdList);    
    List<Long> getUnassignedList(String query);
    List<CustomerDownloadDto> getAllCustomer(String query);
    List<CustomerDownloadDto> getAllCustomerForUnassigned(String sqlquery);
    List<CustomerCount> getavailableCustomerCount(Long userId, Long orgId);
    List<CustomerCount> getAssignedCustomerCount(Long userId, Long orgId);
}
