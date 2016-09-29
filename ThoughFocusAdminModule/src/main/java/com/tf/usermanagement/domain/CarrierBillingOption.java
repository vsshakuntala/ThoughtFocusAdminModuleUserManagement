package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * The persistent class for the CARRIER_BILLING_OPTION database table.
 * 
 * @author Sunny Mishra
 * 
 */
@Entity
@Table(name = "CARRIER_BILLING_OPTION")
public class CarrierBillingOption implements Serializable,Comparable<CarrierBillingOption> {

	private static final long serialVersionUID = 1L;
	
	private Integer carrierBillingOptionId;
	private String reference;
	private String description;

	@Id
	@Column(name="CARRIER_BILLING_OPTION_ID")
	public Integer getCarrierBillingOptionId() {
		return carrierBillingOptionId;
	}

	public void setCarrierBillingOptionId(Integer carrierBillingOptionId) {
		this.carrierBillingOptionId = carrierBillingOptionId;
	}

	@Column(name="REFERENCE")
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}


	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode(){
		return this.carrierBillingOptionId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof CarrierBillingOption){
			CarrierBillingOption c = (CarrierBillingOption)obj;
			result = (c.getCarrierBillingOptionId().equals(this.carrierBillingOptionId));
		}
		return result;
	}

	@Override
	public int compareTo(CarrierBillingOption o) {
		return this.description.compareTo(o.getDescription());
	}


}
