package com.tf.usermanagement.dto;

/**
 * This DTO contains the fields that is used to check if user have 
 * default address assigned
 * @author Manideep
 *
 */
public class DefaultAddressCheckDto {

	private long salesAreaId;
	private long billToAddressId;
	private long shipToAddressId;
	private long customerId;
	private String salesAreaName;
	private String customerName;
	private long userOrgBillShipId;
	private long userOrgSalesAreaId;
	private long userOrgId;
	private long adminId;
	
	
	
	public long getAdminId() {
		return adminId;
	}
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}
	public long getUserOrgId() {
		return userOrgId;
	}
	public void setUserOrgId(long userOrgId) {
		this.userOrgId = userOrgId;
	}
	//getters and setters
	public long getSalesAreaId() {
		return salesAreaId;
	}
	public void setSalesAreaId(long salesAreaId) {
		this.salesAreaId = salesAreaId;
	}
	public long getBillToAddressId() {
		return billToAddressId;
	}
	public void setBillToAddressId(long billToAddressId) {
		this.billToAddressId = billToAddressId;
	}
	public long getShipToAddressId() {
		return shipToAddressId;
	}
	public void setShipToAddressId(long shipToAddressId) {
		this.shipToAddressId = shipToAddressId;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getSalesAreaName() {
		return salesAreaName;
	}
	public void setSalesAreaName(String salesAreaName) {
		this.salesAreaName = salesAreaName;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getUserOrgBillShipId() {
		return userOrgBillShipId;
	}
	public void setUserOrgBillShipId(long userOrgBillShipId) {
		this.userOrgBillShipId = userOrgBillShipId;
	}
	public long getUserOrgSalesAreaId() {
		return userOrgSalesAreaId;
	}
	public void setUserOrgSalesAreaId(long userOrgSalesAreaId) {
		this.userOrgSalesAreaId = userOrgSalesAreaId;
	}
	@Override
	public String toString() {
		return "DefaultAddressCheckDto [salesAreaId=" + salesAreaId + ", billToAddressId=" + billToAddressId
				+ ", shipToAddressId=" + shipToAddressId + ", customerId=" + customerId + ", salesAreaName="
				+ salesAreaName + ", customerName=" + customerName + ", userOrgBillShipId=" + userOrgBillShipId
				+ ", userOrgSalesAreaId=" + userOrgSalesAreaId + ", userOrgId=" + userOrgId + ", adminId=" + adminId
				+ "]";
	}
	
	//to string
	
	
	
	
}
