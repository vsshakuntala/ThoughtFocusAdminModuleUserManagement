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
	
	
}
