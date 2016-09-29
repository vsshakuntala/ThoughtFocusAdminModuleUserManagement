package com.tf.usermanagement.dto;

/**
 * @author Biswajit
 */

public class BillAddressInputDto {

	private Long addressId;
	private String addressName;

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	@Override
	public String toString() {
		return "DefaultAddressInputDto [addressId=" + addressId
				+ ", addressName=" + addressName + "]";
	}

}
