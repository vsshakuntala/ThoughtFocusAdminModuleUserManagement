
package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
/*@SqlResultSetMapping(name="billtoShiptoAddress",
entities={
    @EntityResult(entityClass=AdminAddress.class),
    @EntityResult(entityClass=BillShipAddressMap.class)
})*/
@Table(name = "ADDRESS")
public class AdminAddress implements Serializable,Comparable<Address> {

	private static final long serialVersionUID = 1L;

	private Long addressId;
	private Integer addressTypeId;
	private String addressReference;
	private String addressName;
/*	private Set<UserAddress> userAddresses;*/
/*	private Set<CustomerAddress> customerAddresses;*/
	private String phoneNumber;
	private String mobileNumber;
	private String address1;
	private String address2;
	private String address3;
	private String address4;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private Timestamp createdDate;
	private Long createdBy;
	private Timestamp modifiedDate;
	private Long modifiedBy;
	private Boolean active;
	private Long customerId;
	private Set<BillShipAddressMap> billShipAddressMap;
	private Set<GroupAddress> groupAddresses;
	private Customer customer;
	private String fax;
	//private UserOrgBillToShip userOrgBillToShip;
	//private AddressType addressType;
    
	
	@Id
	@Column(name = "ADDRESS_ID")
	/*@SequenceGenerator(name = "AddressSequence", sequenceName = "ADDRESS_SEQ")
	@GeneratedValue(generator="AddressSequence")*/
	@GeneratedValue
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	@Column(name = "PHONE_NUMBER")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "MOBILE_NUMBER")
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "ADDRESS1")
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "ADDRESS2")
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name = "CITY")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "ZIP_CODE")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name="ADDRESS_TYPE_ID")
	public Integer getAddressTypeId() {
		return addressTypeId;
	}

	public void setAddressTypeId(Integer addressTypeId) {
		this.addressTypeId = addressTypeId;
	}

	@Column(name="ADDRESS_REFERENCE")
	public String getAddressReference() {
		return addressReference;
	}

	public void setAddressReference(String addressReference) {
		this.addressReference = addressReference;
	}

	@Column(name="ADDRESS_NAME")
	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	/*@OneToMany(mappedBy="user",fetch=FetchType.LAZY)
	public Set<UserAddress> getUserAddresses() {
		return userAddresses;
	}

	public void setUserAddresses(Set<UserAddress> userAddresses) {
		this.userAddresses = userAddresses;
	}*/

	/*@OneToMany(mappedBy="customer",fetch=FetchType.LAZY)
	public Set<CustomerAddress> getCustomerAddresses() {
		return customerAddresses;
	}

	public void setCustomerAddresses(Set<CustomerAddress> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}*/

	@Column(name="ADDRESS3")
	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	@Column(name="ADDRESS4")
	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	@Column(name="STATE")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Column(name="CUSTOMER_ID")
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	@OneToMany
	@JoinColumn(name="BILL_TO_ADDRESS_ID", referencedColumnName="ADDRESS_ID")
	public Set<BillShipAddressMap> getBillShipAddressMap() {
		return billShipAddressMap;
	}

	public void setBillShipAddressMap(Set<BillShipAddressMap> billShipAddressMap) {
		this.billShipAddressMap = billShipAddressMap;
	}
	
	@OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
	public Set<GroupAddress> getGroupAddresses() {
		return groupAddresses;
	}

	public void setGroupAddresses(Set<GroupAddress> groupAddresses) {
		this.groupAddresses = groupAddresses;
	}
	
	/*
	 * Keep this as lazy fetched to avoid n+1 select problem
	 * in retrieving addresses from bill-ship map 
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CUSTOMER_ID", insertable=false, updatable=false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	/*	@ManyToOne
	@JoinColumn(name = "ADDRESS_TYPE_ID")
	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
*/
	
	
	
	public void mergeAddress(User user, Long modifiedBy) {

		for (Address address : user.fetchActiveAddresses()) {

			this.phoneNumber = address.getPhoneNumber();
			this.mobileNumber = address.getMobileNumber();
			this.address1 = address.getAddress1();
			this.address2 = address.getAddress2();
			this.address3 = address.getAddress1();
			this.address4 = address.getAddress2();
			this.city = address.getCity();
			this.state = address.getState();
			this.country = address.getCountry();
			this.zipCode = address.getZipCode();
			this.modifiedDate = new java.sql.Timestamp(
					new java.util.Date().getTime());
			this.modifiedBy = modifiedBy;
			
			break;
		}

	}
	
/*	@OneToOne
	@JoinColumn(name="ADDRESS_ID",insertable=false,updatable=false)
	public UserOrgBillToShip getUserOrgBillToShip() {
		return userOrgBillToShip;
	}

	public void setUserOrgBillToShip(UserOrgBillToShip userOrgBillToShip) {
		this.userOrgBillToShip = userOrgBillToShip;
	}*/

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", active=" + active + "]";
	}

	@Override
	public int compareTo(Address o) {
		return this.addressReference.compareTo(o.getAddressReference());
	}

	@Override
	public int hashCode(){
		return this.addressId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof Address){
			Address a=(Address)obj;
			result = (a.getAddressId().equals(this.addressId));
		}
		return result;
	}
}
