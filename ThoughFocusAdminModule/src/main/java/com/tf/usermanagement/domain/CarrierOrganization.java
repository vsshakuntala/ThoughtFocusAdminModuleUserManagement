package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the CARRIER_ORGANIZATION database table.
 * 
 * @author Arvind Rao
 * 
 */
@Entity
@Table(name="CARRIER_ORGANIZATION")
public class CarrierOrganization implements Serializable{

	private static final long serialVersionUID = 1L;

	private CarrierOrganizationPK id;
	private Boolean active;
	
	@EmbeddedId
	public CarrierOrganizationPK getId() {
		return this.id;
	}

	public void setId(CarrierOrganizationPK id) {
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
