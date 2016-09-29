package com.tf.usermanagement.dto;
/**
 * this Dto class is used to capture the individual assignments of
 * customer and catalog count 
 * to all the organizations that user belongs to
 * @author Manideep
 *
 */
public class CustomerCatalogCountDto {
	
	private long organizationId;
	private long status;
	private long customersCount;
	private long catalogsCount;
	
	//getters and setters
	public long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
	}
	public long getCustomersCount() {
		return customersCount;
	}
	public void setCustomersCount(long customersCount) {
		this.customersCount = customersCount;
	}
	public long getCatalogsCount() {
		return catalogsCount;
	}
	public void setCatalogsCount(long catalogsCount) {
		this.catalogsCount = catalogsCount;
	}
	public long getStatus() {
		return status;
	}
	public void setStatus(long status) {
		this.status = status;
	}
	//to string
	@Override
	public String toString() {
		return "CustomerCatalogCountDto [organizationId=" + organizationId + ", customersCount=" + customersCount
				+ ", catalogsCount=" + catalogsCount + "]";
	}
	
	
	
	
}
