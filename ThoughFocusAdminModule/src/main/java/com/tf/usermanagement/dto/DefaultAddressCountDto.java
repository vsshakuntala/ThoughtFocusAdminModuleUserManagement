package com.tf.usermanagement.dto;

/**
 * 
 * @author Manideep
 *
 */
public class DefaultAddressCountDto {

	private long organizationId;
	private long defaultAddressCount;
	
	//getters and setters
	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	public long getDefaultAddressCount() {
		return defaultAddressCount;
	}
	public void setDefaultAddressCount(long defaultAddressCount) {
		this.defaultAddressCount = defaultAddressCount;
	}
	
	//to string
	@Override
	public String toString() {
		return "DefaultAddressCountDto [organizationId=" + organizationId + ", defaultAddressCount="
				+ defaultAddressCount + "]";
	}
	
	

}
