package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="INTERMEDIARY")
public class Intermediary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long intermediaryId;
	private String intermediaryName;
	private String intermediaryDesc;
	private Long billToAddress;
	private Long shipToAddress;
	private Boolean active;
	private Long intermediaryTypeId;
	private Long intermediaryCode;
	private String city;
	private String state;
	private Long zipCode;
	private String phoneNumber;
	private String address1;
	private String address2;
	private String address3;
	private String country;
	private String fax;
	private String email;

	public Intermediary() { 
		super();
	}
	
	public Intermediary(Long intermediaryId, String intermediaryName,
			String intermediaryDesc, Long billToAddress, Long shipToAddress,
			Boolean active, Long intermediaryTypeId, Long intermediaryCode,
			String city, String state, Long zipCode) {
		super();
		this.intermediaryId = intermediaryId;
		this.intermediaryName = intermediaryName;
		this.intermediaryDesc = intermediaryDesc;
		this.billToAddress = billToAddress;
		this.shipToAddress = shipToAddress;
		this.active = active;
		this.intermediaryTypeId = intermediaryTypeId;
		this.intermediaryCode = intermediaryCode;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}
	@Id
	@GeneratedValue
	@Column(name="INTERMEDIARY_ID")
	public Long getIntermediaryId() {
		return intermediaryId;
	}
	public void setIntermediaryId(Long intermediaryId) {
		this.intermediaryId = intermediaryId;
	}
	
	@Column(name="INTERMEDIARY_NAME")
	public String getIntermediaryName() {
		return intermediaryName;
	}
	public void setIntermediaryName(String intermediaryName) {
		this.intermediaryName = intermediaryName;
	}
	
	@Column(name="INTERMEDIARY_DESCRIPTION")
	public String getIntermediaryDesc() {
		return intermediaryDesc;
	}
	public void setIntermediaryDesc(String intermediaryDesc) {
		this.intermediaryDesc = intermediaryDesc;
	}
	
	@Column(name="BILL_TO_ADDRESS_ID")
	public Long getBillToAddress() {
		return billToAddress;
	}
	public void setBillToAddress(Long billToAddress) {
		this.billToAddress = billToAddress;
	}
	
	@Column(name="SHIP_TO_ADDRESS_ID")
	public Long getShipToAddress() {
		return shipToAddress;
	}
	public void setShipToAddress(Long shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	
	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Column(name="INTERMEDIARY_TYPE_ID")
	public Long getIntermediaryTypeId() {
		return intermediaryTypeId;
	}
	public void setIntermediaryTypeId(Long intermediaryTypeId) {
		this.intermediaryTypeId = intermediaryTypeId;
	}
	
	@Column(name="INTERMEDIARY_CODE")
	public Long getIntermediaryCode() {
		return intermediaryCode;
	}
	public void setIntermediaryCode(Long intermediaryCode) {
		this.intermediaryCode = intermediaryCode;
	}
	
	@Column(name="CITY")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name="ZIPCODE")
	public Long getZipCode() {
		return zipCode;
	}
	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}
	
	@Column(name="PHONE_NUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name="ADDRESS1")
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name="ADDRESS2")
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name="ADDRESS3")
	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	@Column(name="COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name="FAX")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Intermediary [intermediaryId=" + intermediaryId
				+ ", intermediaryName=" + intermediaryName
				+ ", intermediaryDesc=" + intermediaryDesc + ", billToAddress="
				+ billToAddress + ", shipToAddress=" + shipToAddress
				+ ", active=" + active + ", intermediaryTypeId="
				+ intermediaryTypeId + ", intermediaryCode=" + intermediaryCode
				+ ", city=" + city + ", state=" + state + ", zipCode="
				+ zipCode + "]";
	}
 
	

}
