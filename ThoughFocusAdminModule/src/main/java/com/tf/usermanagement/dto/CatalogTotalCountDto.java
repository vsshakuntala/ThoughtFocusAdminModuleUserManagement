package com.tf.usermanagement.dto;

/**
 * 
 * @author Manideep
 *
 */
public class CatalogTotalCountDto {

	private long organizationId;
	private long totalCatalogCount;
	
	//getters and setters
	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	public long getTotalCatalogCount() {
		return totalCatalogCount;
	}
	public void setTotalCatalogCount(long totalCatalogCount) {
		this.totalCatalogCount = totalCatalogCount;
	}
	
	//to string
	@Override
	public String toString() {
		return "CatalogTotalCountDto [organizationId=" + organizationId + ", totalCatalogCount=" + totalCatalogCount
				+ "]";
	}
	
	
	
	
}
