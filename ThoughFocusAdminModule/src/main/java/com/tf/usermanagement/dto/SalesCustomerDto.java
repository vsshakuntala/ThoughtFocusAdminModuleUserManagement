package com.tf.usermanagement.dto;

/**
 * @author Biswajit
 */

public class SalesCustomerDto {

	private Long customerId;
	private String customerName;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "SalesCustomerDto [customerId=" + customerId + ", customerName="
				+ customerName + "]";
	}

}
