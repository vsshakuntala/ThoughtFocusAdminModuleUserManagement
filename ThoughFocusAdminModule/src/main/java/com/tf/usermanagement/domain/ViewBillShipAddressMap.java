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
 * @author Lakhan J
 * 
 */
@Entity
@Table(name="VW_BILL_SHIP_ADDRESS_MAP")
public class ViewBillShipAddressMap implements Serializable{

	private static final long serialVersionUID = 1L;

	private ViewBillShipPK id;
	private Long billToAddressId;
	private Long shipToAddressId;
	private Long customerId;
	private Boolean active = true;
	
	@EmbeddedId
	public ViewBillShipPK getId() {
		return this.id;
	}

	public void setId(ViewBillShipPK id) {
		this.id = id;
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
	
	@Column(name = "CUSTOMER_ID" ,insertable=false, updatable=false)
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
