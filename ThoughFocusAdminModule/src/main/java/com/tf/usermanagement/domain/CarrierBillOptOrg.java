package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * The persistent class for the CARRIER_BILL_OPT_ORG database table.
 * 
 * @author Arvind Rao
 * 
 */
@Entity
@Table(name = "CARRIER_BILL_OPT_ORG")
public class CarrierBillOptOrg implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private CarrierBillOptOrgPK carrierBillOptOrgPK;
	private Boolean active;

	@EmbeddedId
	public CarrierBillOptOrgPK getCarrierBillOptOrgPK() {
		return carrierBillOptOrgPK;
	}

	public void setCarrierBillOptOrgPK(CarrierBillOptOrgPK carrierBillOptOrgPK) {
		this.carrierBillOptOrgPK = carrierBillOptOrgPK;
	}

	@Column(name="ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
