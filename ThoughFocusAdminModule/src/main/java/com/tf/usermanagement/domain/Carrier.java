package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * 
 * The persistent class for the CARRIER database table.
 * 
 * @author Arvind Rao
 * 
 */
@Entity(name="CARRIER")
public class Carrier implements Serializable,Comparable<Carrier> {

	private static final long serialVersionUID = 1L;
	
	private Integer carrierId;
	private String carrierReference;
	private String carrierDescription;
	private Boolean isExpedited;
	
	private Set<Cart> carts;
	private String carrierTrackingURL;
	@Id
	@Column(name="CARRIER_ID")
	public Integer getCarrierId() {
		return carrierId;
	}
	public void setCarrierId(Integer carrierId) {
		this.carrierId = carrierId;
	}

	@Column(name="CARRIER_REFERENCE")
	public String getCarrierReference() {
		return carrierReference;
	}
	public void setCarrierReference(String carrierReference) {
		this.carrierReference = carrierReference;
	}

	@Column(name="CARRIER_DESCRIPTION")
	public String getCarrierDescription() {
		return carrierDescription;
	}
	public void setCarrierDescription(String carrierDescription) {
		this.carrierDescription = carrierDescription;
	}
	
	public void setCarts(Set<Cart> carts) {
		this.carts = carts;
	}
	
	@OneToMany(mappedBy="carrier")
	public Set<Cart> getCarts() {
		return carts;
	}
	@Column(name="CARRIER_TRACKING_URL")
	public String getCarrierTrackingURL() {
		return carrierTrackingURL;
	}
	public void setCarrierTrackingURL(String carrierTrackingURL) {
		this.carrierTrackingURL = carrierTrackingURL;
	}
	@Override
	public int hashCode(){
		return this.carrierId.hashCode();
	}
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof Carrier){
			Carrier c=(Carrier)obj;
			result = (c.getCarrierId().equals(this.carrierId));
		}
		return result;
	}

	@Override
	public int compareTo(Carrier o) {
		return this.carrierDescription.compareTo(o.getCarrierDescription());
	}

	@Column(name="IS_EXPEDITED")
	public Boolean getIsExpedited() {
		return isExpedited;
	}
	public void setIsExpedited(Boolean isExpedited) {
		this.isExpedited = isExpedited;
	}

}
