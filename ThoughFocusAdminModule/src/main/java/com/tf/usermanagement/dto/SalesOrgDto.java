package com.tf.usermanagement.dto;

/**
 * @author Biswajit
 */


public class SalesOrgDto {

	
	private Long salesAreaId;
	private String salesOrgName;
	private String distributionChannelName;

	

	public Long getSalesAreaId() {
		return salesAreaId;
	}

	public void setSalesAreaId(Long salesAreaId) {
		this.salesAreaId = salesAreaId;
	}

	public String getSalesOrgName() {
		return salesOrgName;
	}

	public void setSalesOrgName(String salesOrgName) {
		this.salesOrgName = salesOrgName;
	}

	public String getDistributionChannelName() {
		return distributionChannelName;
	}

	public void setDistributionChannelName(String distributionChannelName) {
		this.distributionChannelName = distributionChannelName;
	}

	@Override
	public String toString() {
		return "DefaultAddressInputDto [salesAreaId=" + salesAreaId
				+ ", salesOrgName=" + salesOrgName
				+ ", distributionChannelName=" + distributionChannelName + "]";
	}

}
