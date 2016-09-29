package com.tf.usermanagement.dto;

/**
 * @author Narasingha
 *
 */
public class UserOrgActiveDTO {
	private Long userId;
	private long organizationId;
	private String organizationName;
	private boolean active;

	public UserOrgActiveDTO() {
		super();
	}

	public UserOrgActiveDTO(Long userId, long organizationId, String organizationName, boolean active) {
		super();
		this.userId = userId;
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.active = active;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
