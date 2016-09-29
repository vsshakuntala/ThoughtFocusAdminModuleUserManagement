package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity

@Table(name="USER_ORG_BILL_SHIP_MAP")
public class UserOrgBillToShip implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long userOrgBillToShipId;
	private Long userorgId;
	private Long billToAddressId;
	private Long shipToAddressId;
	private Long modifiedBy;
	private Date modifiedDate;
	private Long createdBy;
	private Date createdDate;
	private UserOrganizationMap userOrgMap;
	private Boolean active = true;
	private Long customerId;
	
	@Column(name = "CUSTOMER_ID")
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	//private Address address;
	//private Organization organization;
	public UserOrgBillToShip() {
		super();
	}

	public UserOrgBillToShip(Long userOrgBillToShipId, Long userorgId,
			Long billToAddressId, Long shipToAddressId, Long modifiedBy,
			Date modifiedDate, Long createdBy, Date createdDate) {
		super();
		this.userOrgBillToShipId = userOrgBillToShipId;
		this.userorgId = userorgId;
		this.billToAddressId = billToAddressId;
		this.shipToAddressId = shipToAddressId;
		this.modifiedBy = modifiedBy;
		this.modifiedDate = modifiedDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}


	@Id
	@Column(name = "USER_BILL_SHIP_ID")
/*	@SequenceGenerator(name = "Userorgbilltoship", sequenceName = "SEQ_USERORGBILLTOSHIP")
	@GeneratedValue(generator="Userorgbilltoship")*/
	@GeneratedValue
	public Long getUserOrgBillToShipId() {
		return userOrgBillToShipId;
	}

	
	
	public void setUserOrgBillToShipId(Long userOrgBillToShipId) {
		this.userOrgBillToShipId = userOrgBillToShipId;
	}
	
	@Column(name="USER_ORG_ID")
	public Long getUserorgId() {
		return userorgId;
	}

	public void setUserorgId(Long userorgId) {
		this.userorgId = userorgId;
	}

	@Column(name="BILL_TO_ADDRESS_ID")
	public Long getBillToAddressId() {
		return billToAddressId;
	}

	public void setBillToAddressId(Long billToAddressId) {
		this.billToAddressId = billToAddressId;
	}
	
	@Column(name="SHIP_TO_ADDRESS_ID")
	public Long getShipToAddressId() {
		return shipToAddressId;
	}

	public void setShipToAddressId(Long shipToAddressId) {
		this.shipToAddressId = shipToAddressId;
	}
	
	@Column(name="MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	@Column(name="MODIFIED_DATE")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Column(name="CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name="CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	/*
	 * Keep this as lazy fetched to avoid n+1 select problem
	 * in retrieving addresses from bill-ship map 
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ORG_ID", insertable=false, updatable=false)
	public UserOrganizationMap getUserOrgMap() {
		return userOrgMap;
	}

	public void setUserOrgMap(UserOrganizationMap userOrgMap) {
		this.userOrgMap = userOrgMap;
	}
	
	
	
	@Override
	public String toString() {
		return "UserOrgBillToShip [userOrgBillToShipId=" + userOrgBillToShipId
				+ ", userorgId=" + userorgId + ", billToAddressId="
				+ billToAddressId + ", shipToAddressId=" + shipToAddressId
				+ ", modifiedBy=" + modifiedBy + ", modifiedDate="
				+ modifiedDate + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + "]";
	}

	
	
}