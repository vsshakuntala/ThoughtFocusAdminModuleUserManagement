package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * 
 * @author Vishal.Agarwal
 * 
 */
@Embeddable
public class PartMetadataPK implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long partId;
	private Long metadataLabelId;
	
	@Column(name="PART_ID")
	public Long getPartId() {
		return partId;
	}
	public void setPartId(Long partId) {
		this.partId = partId;
	}
	
	@Column(name="METADATA_LABEL_ID")
	public Long getMetadataLabelId() {
		return metadataLabelId;
	}
	public void setMetadataLabelId(Long metadataLabelId) {
		this.metadataLabelId = metadataLabelId;
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.partId ^ (this.partId >>> 32)));
		hash = hash * prime
				+ ((int) (this.metadataLabelId ^ (this.metadataLabelId >>> 32)));

		return hash;
	}

	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof PartMetadataPK){
			PartMetadataPK cat=(PartMetadataPK)obj;
			result = (cat.getPartId().equals(this.partId));
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "PartMetadataPK [partId=" + partId + ", metadataLabelId=" + metadataLabelId + "]";
	}

}
