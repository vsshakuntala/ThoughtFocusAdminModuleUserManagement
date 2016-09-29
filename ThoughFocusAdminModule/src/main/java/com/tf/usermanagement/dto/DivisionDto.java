package com.tf.usermanagement.dto;

/**
 * 
 * @author Rajendra
 *
 */
public class DivisionDto {
	private Integer organizationId;
	private String organizationName;
	
	
	public DivisionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DivisionDto(Integer organizationId, String organizationName) {
		super();
		this.organizationId = organizationId;
		this.organizationName = organizationName;
	}
	public Integer getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Integer organizationId) {
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
		return "DivisionDto [organizationId=" + organizationId
				+ ", organizationName=" + organizationName + "]";
	}
}
