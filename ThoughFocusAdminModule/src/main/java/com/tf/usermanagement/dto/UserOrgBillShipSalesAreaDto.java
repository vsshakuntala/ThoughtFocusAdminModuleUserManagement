package com.tf.usermanagement.dto;

/**
 * @author Biswajit
 */

public class UserOrgBillShipSalesAreaDto {

	private Long adminId;
	private Long userId;
	private Long organizationId;
	private Long userOrgId;
	private Long billToAddressId;
	private Long shipToAddressId;
	private Long customerId;
	private Long salesAreaId;
	

	
	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public Long getBillToAddressId() {
		return billToAddressId;
	}

	public void setBillToAddressId(Long billToAddressId) {
		this.billToAddressId = billToAddressId;
	}

	public Long getShipToAddressId() {
		return shipToAddressId;
	}

	public void setShipToAddressId(Long shipToAddressId) {
		this.shipToAddressId = shipToAddressId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getSalesAreaId() {
		return salesAreaId;
	}

	public void setSalesAreaId(Long salesAreaId) {
		this.salesAreaId = salesAreaId;
	}

	@Override
	public String toString() {
		return "UserOrgBillShipSalesAreaDto [adminId=" + adminId + ", userId=" + userId + ", organizationId="
				+ organizationId + ", userOrgId=" + userOrgId + ", billToAddressId=" + billToAddressId
				+ ", shipToAddressId=" + shipToAddressId + ", customerId=" + customerId + ", salesAreaId=" + salesAreaId
				+ "]";
	}

	

	

}
