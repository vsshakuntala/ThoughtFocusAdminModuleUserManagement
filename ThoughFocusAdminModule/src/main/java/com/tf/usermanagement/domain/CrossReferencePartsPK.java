package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CrossReferencePartsPK implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long partId;
	private String crossRefNumber;
	private String type;
	
	public CrossReferencePartsPK(){

	}
	
	@Column(name="PART_ID")
	public Long getPartId() {
		return partId;
	}
	public void setPartId(Long partId) {
		this.partId = partId;
	}
	
	@Column(name="CROSS_REFERENCE_PART_NUMBER")
	public String getCrossRefNumber() {
		return crossRefNumber;
	}
	public void setCrossRefNumber(String crossRefNumber) {
		this.crossRefNumber = crossRefNumber;
	}
	
	@Column(name="CROSS_REFERENCE_TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CrossReferencePartsPK)) {
			return false;
		}
		CrossReferencePartsPK castOther = (CrossReferencePartsPK)other;
		return 
			(this.partId == castOther.partId)
			&& (this.crossRefNumber == castOther.crossRefNumber)
			&& (this.type == castOther.type);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.partId ^ (this.partId >>> 32)));
		hash = hash * prime + ((int) this.crossRefNumber.hashCode());
		hash = hash * prime + ((int) this.type.hashCode());
		
		return hash;
    }

}
