package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the METADATA_VALUE database table.
 * 
 * @author Arvind Chauhan
 * 
 */
@Entity
@Table(name = "METADATA_VALUE")
public class MetadataValue implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long metadataValueId;
	private Bom bom;
	private Metadata metadata;
	private String metadataValue;

	@Id
	@Column(name = "MEATDATA_VALUE_ID")
	public Long getMetadataValueId() {
		return metadataValueId;
	}

	public void setMetadataValueId(Long metadataValueId) {
		this.metadataValueId = metadataValueId;
	}

	// bi-directional many-to-one association to Bom
	@ManyToOne
	@JoinColumn(name = "BOM_ID")
	public Bom getBom() {
		return bom;
	}

	public void setBom(Bom bom) {
		this.bom = bom;
	}

	// bi-directional many-to-one association to Metadata
	@ManyToOne
	@JoinColumn(name = "METADATA_ID")
	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Column(name = "MEATDATA_VALUE")
	public String getMetadataValue() {
		return metadataValue;
	}

	public void setMetadataValue(String metadataValue) {
		this.metadataValue = metadataValue;
	}

	@Override
	public String toString() {
		return "MetadataValue [metadataValueId=" + metadataValueId
				+ ", metadataValue=" + metadataValue + "]";
	}

}