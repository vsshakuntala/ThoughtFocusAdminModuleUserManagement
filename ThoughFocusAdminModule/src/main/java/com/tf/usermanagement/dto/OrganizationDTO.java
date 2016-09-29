package com.tf.usermanagement.dto;

/**
 * 
 * @author Narasingha
 *
 */

public class OrganizationDTO {
	
	private long organizationId;
	private String organizationName;
	
	
	
	public OrganizationDTO() {
		super();
	}
	
	public OrganizationDTO(long organizationId, String organizationName) {
		super();
		this.organizationId = organizationId;
		this.organizationName = organizationName;
	}

	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	@Override
	public String toString() {
		return "OrganizationDTO [organizationId=" + organizationId + ", organizationName=" + organizationName + "]";
	}
	
	

	
	
	

}
