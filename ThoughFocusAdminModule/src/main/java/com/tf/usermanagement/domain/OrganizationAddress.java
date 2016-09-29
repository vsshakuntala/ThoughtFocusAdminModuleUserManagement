package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "ORGANIZATION_ADDRESS")
public class OrganizationAddress implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer organizationAddressId;
	private Integer organizationId;
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String phoneNumber;
	private String fax;
	private Boolean active;
	private String site;
	private String siteUrl;
    private Organization organization;

	
	
	@Id
	@Column(name="ORGANIZATION_ADDRESS_ID")
	public Integer getOrganizationAddressId() {
		return organizationAddressId;
	}
	public void setOrganizationAddressId(Integer organizationAddressId) {
		this.organizationAddressId = organizationAddressId;
	}
	
	@Column(name="ORGANIZATION_ID")
	public Integer getOrganizationId() {
		return organizationId;
	}
	
	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
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
	
	@Column(name="COUNTRY")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	@Column(name="ZIP_CODE")
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Column(name="PHONE_NUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Column(name="FAX")
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@Column(name="SITE")
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
	@Column(name="SITE_URL")
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	
	 @ManyToOne
	 @JoinColumn(name="ORGANIZATION_ID", insertable=false, updatable=false)
	 public Organization getOrganization() {
			return organization;
		}
		public void setOrganization(Organization organization) {
			this.organization = organization;
		}
	
	@Override
	public String toString(){
	
		return "OrganizationAddress [organizationId=" + organizationId + ", address1=" + address1 + ",address2=" + address2 +", address3=" + address3 +", city="
		+ city +", state=" + state +",country=" + country +", zipCode=" + zipCode +", phoneNumber=" + phoneNumber + ",fax=" + fax + ", active=" 
				+ active +",site="+ site +", siteUrl=" + siteUrl +" ]";
}
}
