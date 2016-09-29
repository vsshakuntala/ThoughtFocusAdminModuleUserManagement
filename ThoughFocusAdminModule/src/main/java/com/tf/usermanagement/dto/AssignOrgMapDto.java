package com.tf.usermanagement.dto;

public class AssignOrgMapDto {
	private long organizationId;

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AssignOrgMapDto [organizationId=");
		builder.append(organizationId);
		builder.append("]");
		return builder.toString();
	}
	
	
}
