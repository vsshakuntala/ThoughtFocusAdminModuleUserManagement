package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="PART_MEDIA")
public class PartMedia implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PartMediaPK id;
	private Part part;

	@EmbeddedId
	public PartMediaPK getId() {
		return id;
	}

	public void setId(PartMediaPK id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="PART_ID" , insertable=false,updatable=false)
	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}
	
	
	
}
