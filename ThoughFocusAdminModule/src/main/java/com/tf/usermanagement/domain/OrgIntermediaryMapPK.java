package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class OrgIntermediaryMapPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private Long intermediaryId;
	
	@Column(name="USER_ID",updatable=false,insertable=false)
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@Column(name="INTERMEDIARY_ID")
	public Long getIntermediaryId() {
		return intermediaryId;
	}
	public void setIntermediaryId(Long intermediaryId) {
		this.intermediaryId = intermediaryId;
	}
	
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.userId ^ (this.userId >>> 32)));
		hash = hash * prime
				+ ((int) (this.intermediaryId ^ (this.intermediaryId >>> 32)));

		return hash;
	}
	
	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof OrgIntermediaryMapPK){
			OrgIntermediaryMapPK cat=(OrgIntermediaryMapPK)obj;
			result = (cat.getUserId().equals(this.userId));
		}
		return result;
	}

	@Override
	public String toString() {
		return "OrgIntermediaryMapPK [userId=" + userId + ", userId=" + intermediaryId + "]";
	}
}
