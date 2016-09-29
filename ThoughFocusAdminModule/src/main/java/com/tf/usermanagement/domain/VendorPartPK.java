package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class VendorPartPK implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long partId;
	private String vendorPartNumber;
	
	public VendorPartPK(){
		
	}
	
	@Column(name="PART_ID")
	public Long getPartId() {
		return partId;
	}
	public void setPartId(Long partId) {
		this.partId = partId;
	}
	
	@Column(name="VENDOR_PART_NUMBER")
	public String getVendorPartNumber() {
		return vendorPartNumber;
	}
	public void setVendorPartNumber(String vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof VendorPartPK)) {
			return false;
		}
		VendorPartPK castOther = (VendorPartPK)other;
		return 
			(this.partId == castOther.partId)
			&& (this.vendorPartNumber == castOther.vendorPartNumber);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.partId ^ (this.partId >>> 32)));
		hash = hash * prime + ((int) this.vendorPartNumber.hashCode());
		
		return hash;
    }
	
}
