package com.tf.usermanagement.dto;

/**
 * 
 * @author Manideep
 *
 */
public class GroupTotalCountDto {
	private long organizationId;
	private long totalGroupCount;
	
	//getters and setters
	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	public long getTotalGroupCount() {
		return totalGroupCount;
	}
	public void setTotalGroupCount(long totalGroupCount) {
		this.totalGroupCount = totalGroupCount;
	}
	
	//to string
	@Override
	public String toString() {
		return "GroupTotalCountDto [organizationId=" + organizationId + ", totalGroupCount=" + totalGroupCount + "]";
	}
	
	
	
}
