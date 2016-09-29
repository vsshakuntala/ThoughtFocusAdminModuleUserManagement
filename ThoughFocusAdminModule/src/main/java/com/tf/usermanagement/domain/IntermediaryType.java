package com.tf.usermanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="INTERMEDIARY_TYPE")
public class IntermediaryType {
	
	private Integer IntermediaryTypeId;
	private String IntermediaryName;
	private String IntermediaryDescription;
	
	
	@Id
	@Column(name="INTERMEDIARY_TYPE_ID")
	public Integer getIntermediaryTypeId() {
		return this.IntermediaryTypeId;
	}
	
	public void setIntermediaryTypeId(Integer intermediaryTypeId) {
		this.IntermediaryTypeId = intermediaryTypeId;
	}
	
	
	@Column(name="INTERMEDIARY_NAME")
	public String getIntermediaryName() {
		return this.IntermediaryName;
	}
	
	public void setIntermediaryName(String intermediaryName) {
		this.IntermediaryName = intermediaryName;
	}
	
	@Column(name="INTERMEDIARY_DESCRIPTION")
	public String getIntermediaryDescription() {
		return this.IntermediaryDescription;
	}
	
	
	public void setIntermediaryDescription(String intermediaryDescription) {
		this.IntermediaryDescription = intermediaryDescription;
	}
	
	
	@Override
	public String toString(){
		return "IntermediaryType [IntermediaryName=" + IntermediaryName + ", IntermediaryTypeId=" + IntermediaryTypeId 
		+ ", IntermediaryDescription=" + IntermediaryDescription+ "]";
	}
	
}
