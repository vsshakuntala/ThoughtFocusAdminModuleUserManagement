package com.tf.usermanagement.dto;

/**
 * this Dto class is used to capture the Catalog count with respective
 * to all the organizations that user belongs to
 * @author Manideep
 *
 */
public class GroupCatalogCountDto {
	
	private long organizationId;
	private long groupCatalogCount;

	//getters and setters
	public long getGroupCatalogCount() {
		return groupCatalogCount;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}

	public void setGroupCatalogCount(long groupCatalogCount) {
		this.groupCatalogCount = groupCatalogCount;
	}

	@Override
	public String toString() {
		return "GroupCatalogCountDto [organizationId=" + organizationId + ", groupCatalogCount=" + groupCatalogCount
				+ "]";
	}
	
	
}
