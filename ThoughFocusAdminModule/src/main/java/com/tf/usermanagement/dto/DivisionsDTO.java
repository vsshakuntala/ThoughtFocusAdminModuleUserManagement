package com.tf.usermanagement.dto;

import java.io.Serializable;

/**
 * @author Narasingha
 *
 */
public class DivisionsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer organizationId;
	private String organizationName;
	private boolean active;
	
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

	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "DivisionsDTO [organizationId=" + organizationId + ", organizationName=" + organizationName + ", active="
				+ active + "]";
	}

	

	

}
