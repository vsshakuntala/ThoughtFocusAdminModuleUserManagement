package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

/**
 * The persistent class for the METADATA database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name = "METADATA")
public class Metadata implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long metadataId;
	private String metadataLabel;
	private Category category;
	private Set<MetadataValue> metadataValues;

	public Metadata() {
	}

	@Id
	@Column(name = "MEATDATA_ID")
	public Long getMetadataId() {
		return this.metadataId;
	}

	public void setMetadataId(Long metadataId) {
		this.metadataId = metadataId;
	}

	@Column(name = "METADATA_LABEL")
	public String getMetadataLabel() {
		return this.metadataLabel;
	}

	public void setMetadataLabel(String metadataLabel) {
		this.metadataLabel = metadataLabel;
	}

	// bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="metadata")
	public Set<MetadataValue> getMetadataValues() {
		return metadataValues;
	}

	public void setMetadataValues(Set<MetadataValue> metadataValues) {
		this.metadataValues = metadataValues;
	}

	@Override
	public String toString() {
		return "Metadata [metadataId=" + metadataId + ", metadataLabel="
				+ metadataLabel + "]";
	}

}