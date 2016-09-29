package com.tf.usermanagement.dto;

/**
 * this Dto class is used to capture the customer count with respective
 * to all the organizations that user belongs to
 * @author Manideep
 *
 */
public class GroupCustomerCountDto {

	private long organizationId;
	private String organizationName;
	private long groupCount;
	private long groupCustomerCount;
	
	//setters and getters
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	public long getGroupCount() {
		return groupCount;
	}
	public void setGroupCount(long groupCount) {
		this.groupCount = groupCount;
	}
	public long getGroupCustomerCount() {
		return groupCustomerCount;
	}
	public void setGroupCustomerCount(long groupCustomerCount) {
		this.groupCustomerCount = groupCustomerCount;
	}
	@Override
	public String toString() {
		return "GroupCustomerCountDto [organizationId=" + organizationId + ", organizationName=" + organizationName
				+ ", groupCount=" + groupCount + ", groupCustomerCount=" + groupCustomerCount + "]";
	}
	
	
	
	
	
}
