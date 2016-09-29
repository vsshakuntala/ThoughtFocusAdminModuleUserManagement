package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the BILL_SHIP_ADDRESS_MAP database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="BILL_SHIP_ADDRESS_MAP")
public class BillShipAddressMap implements Serializable{

	private static final long serialVersionUID = 1L;

	private BillToShipToPK id;
	private Timestamp createdDate;
	private Long createdBy;
	private Timestamp modifiedDate;
	private Long modifiedBy;
	private Long billToAddressId;
	private Long shipToAddressId;
	/*private Address billToAddress;
	private Address shipToAddress;*/
	private Boolean active = true;
	
	@EmbeddedId
	public BillToShipToPK getId() {
		return this.id;
	}

	public void setId(BillToShipToPK id) {
		this.id = id;
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

	@Column(name = "BILL_TO_ADDRESS_ID", insertable=false, updatable=false)
	public Long getBillToAddressId() {
		return billToAddressId;
	}

	public void setBillToAddressId(Long billToAddressId) {
		this.billToAddressId = billToAddressId;
	}

	@Column(name = "SHIP_TO_ADDRESS_ID" ,insertable=false, updatable=false)
	public Long getShipToAddressId() {
		return shipToAddressId;
	}

	public void setShipToAddressId(Long shipToAddressId) {
		this.shipToAddressId = shipToAddressId;
	}

	
/*	public Address getBillToAddress() {
		return billToAddress;
	}

	public void setBillToAddress(Address billToAddress) {
		this.billToAddress = billToAddress;
	}

	public Address getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(Address shipToAddress) {
		this.shipToAddress = shipToAddress;
	}*/
	
	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
