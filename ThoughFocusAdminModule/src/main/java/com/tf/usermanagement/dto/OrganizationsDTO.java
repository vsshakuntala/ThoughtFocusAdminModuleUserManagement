package com.tf.usermanagement.dto;

/**
 * @author Narasingha
 *
 */
public class OrganizationsDTO {

	private Integer organizationId;
	private String organizationName;
	
	public OrganizationsDTO() {
		super();
	}

	public OrganizationsDTO(Integer organizationId, String organizationName) {
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

	/**
	 * @param organizationName
	 */
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	
}
