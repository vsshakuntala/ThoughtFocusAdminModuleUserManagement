package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PART_CROSS_REFERENCE")
public class CrossReferencePart implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private CrossReferencePartsPK id;
	private Part part;
	private Boolean active;
	
	@EmbeddedId
	public CrossReferencePartsPK getId() {
		return id;
	}
	public void setId(CrossReferencePartsPK id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="PART_ID", insertable=false, updatable=false)
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
}
