package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the PartMetadata database table.
 * 
 * @author Vishal.Agarwal
 * 
 */
@Entity
@Table(name="PART_METADATA")
public class PartMetadata implements Serializable {

	private static final long serialVersionUID = 1L;
	private PartMetadataPK id;
	private String metadataLabelValue;
	
	private Part part;
	private MetadataLabel metadataLabel;
	
	@EmbeddedId
	public PartMetadataPK getId() {
		return id;
	}
	public void setId(PartMetadataPK id) {
		this.id = id;
	}
	
	@Column(name="METADATA_LABEL_VALUE")
	public String getMetadataLabelValue() {
		return metadataLabelValue;
	}
	public void setMetadataLabelValue(String metadataLabelValue) {
		this.metadataLabelValue = metadataLabelValue;
	}
	
	/*@OneToOne
	@JoinColumn(name="PART_ID")
	public Part getPart() {
		return part;
	}
	public void setPart(Part part) {
		this.part = part;
	}
	
	@OneToOne
	@JoinColumn(name="METADATA_LABEL_ID")
	public MetadataLabel getMetadataLabel() {
		return metadataLabel;
	}
	public void setMetadataLabel(MetadataLabel metadataLabel) {
		this.metadataLabel = metadataLabel;
	}
	
	*/
	

}
