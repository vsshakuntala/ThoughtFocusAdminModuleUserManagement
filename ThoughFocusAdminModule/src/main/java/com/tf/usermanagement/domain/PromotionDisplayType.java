package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROMOTION_DISPLAY_TYPE")
public class PromotionDisplayType implements Serializable{

 
	private static final long serialVersionUID = 1L;
	private Long displayTypeId;
	private String displayTypeDescription;
	
	@Id
	@Column(name="PROMOTION_DISPLAY_TYPE_ID")
	public Long getDisplayTypeId() {
		return displayTypeId;
	}
	public void setDisplayTypeId(Long displayTypeId) {
		this.displayTypeId = displayTypeId;
	}
	
	@Column(name="PROMOTION_DISPLAY_DESCRIPTION")
	public String getDisplayTypeDescription() {
		return displayTypeDescription;
	}
	public void setDisplayTypeDescription(String displayTypeDescription) {
		this.displayTypeDescription = displayTypeDescription;
	}
	
	
}
