package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="PART_DESCRIPTION")
public class PartDescription implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private PartDescriptionPK id;
	private String engineeringDescription;
	private String commercialDescription;
	
	private Part part;
	
	@EmbeddedId
	public PartDescriptionPK getId() {
		return id;
	}
	public void setId(PartDescriptionPK id) {
		this.id = id;
	}
	
	@Column(name = "ENGINEERING_DESCRIPTION")
	public String getEngineeringDescription() {
		return engineeringDescription;
	}
	public void setEngineeringDescription(String engineeringDescription) {
		this.engineeringDescription = engineeringDescription;
	}
	
	@Column(name = "COMMERCIAL_DESCRIPTION")
	public String getCommercialDescription() {
		return commercialDescription;
	}
	public void setCommercialDescription(String commercialDescription) {
		this.commercialDescription = commercialDescription;
	}
	
	@OneToOne
	@JoinColumn(name="PART_ID" , insertable=false,updatable=false)
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	
	

}
