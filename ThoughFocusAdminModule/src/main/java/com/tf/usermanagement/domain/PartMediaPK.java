package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PartMediaPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long partId;
	private Long mediaId;
	
	@Column(name="PART_ID")
	public Long getPartId() {
		return partId;
	}
	public void setPartId(Long partId) {
		this.partId = partId;
	}
	
	@Column(name="MEDIA_ID")
	public Long getMediaId() {
		return mediaId;
	}
	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.partId ^ (this.partId >>> 32)));
		hash = hash * prime
				+ ((int) (this.mediaId ^ (this.mediaId >>> 32)));

		return hash;
	}
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof PartMediaPK){
			PartMediaPK cat=(PartMediaPK)obj;
			result = (cat.getPartId().equals(this.partId));
		}
		return result;
	}

	@Override
	public String toString() {
		return "PartMediaPK [partId=" + partId + ", mediaId=" + mediaId + "]";
	}
	
	
}
