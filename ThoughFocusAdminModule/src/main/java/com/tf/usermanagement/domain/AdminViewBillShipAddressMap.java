package com.tf.usermanagement.domain;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="VW_BILL_SHIP_ADDRESS_MAP")
public class AdminViewBillShipAddressMap implements Serializable{

	private static final long serialVersionUID = 1L;
	private ViewBillShipPK id;
	private Long billToAddressId;
	private Long shipToAddressId;
	
	private AdminAddress billToAddress;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private AdminAddress shipToAddress;
	
	
	private Long customerId;
	private Boolean active = true;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "BILL_TO_ADDRESS_ID", insertable=false, updatable=false)
	public AdminAddress getBillToAddress() {
		return billToAddress;
	}

	public void setBillToAddress(AdminAddress billToAddress) {
		this.billToAddress = billToAddress;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SHIP_TO_ADDRESS_ID", insertable=false, updatable=false)
	public AdminAddress getShipToAddress() {
		return shipToAddress;
	}

	public void setShipToAddress(AdminAddress shipToAddress) {
		this.shipToAddress = shipToAddress;
	}
	
	
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
