package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the CARRIER_CUSTOMER_MAP database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="CARRIER_CUSTOMER_MAP")
public class CarrierCustomerMap implements Serializable{

	private static final long serialVersionUID = 1L;

	private CarrierCustomerPK id;
	private Boolean active;
	
	@EmbeddedId
	public CarrierCustomerPK getId() {
		return this.id;
	}

	public void setId(CarrierCustomerPK id) {
		this.id = id;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
