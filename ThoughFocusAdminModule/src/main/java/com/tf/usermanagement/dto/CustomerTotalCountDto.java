package com.tf.usermanagement.dto;

/**
 * 
 * @author Manideep
 *
 */
public class CustomerTotalCountDto {

	private long organizationId;
	private long totalCustomerCount;
	
	//setters and getters
	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	public long getTotalCustomerCount() {
		return totalCustomerCount;
	}
	public void setTotalCustomerCount(long totalCustomerCount) {
		this.totalCustomerCount = totalCustomerCount;
	}
	
	//to string
	@Override
	public String toString() {
		return "CustomerTotalCountDto [organizationId=" + organizationId + ", totalCustomerCount=" + totalCustomerCount
				+ "]";
	}
	
	
	
}
