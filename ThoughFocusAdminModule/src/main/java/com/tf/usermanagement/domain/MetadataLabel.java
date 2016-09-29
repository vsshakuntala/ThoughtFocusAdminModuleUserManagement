package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the MetadataLabel database table.
 * 
 * @author Vishal.Agarwal
 * 
 */
@Entity
@Table(name="METADATA_LABEL")
public class MetadataLabel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long metadataLableId;
	private Language language;
	private String metadataLableName;
	private String metadataLableValue;
	
	@Id
	@Column(name="METADATA_LABEL_ID")
	/*@SequenceGenerator(name = "MetadataLabelSequence", sequenceName = "SEQ_METADATALABEL")
	@GeneratedValue(generator="MetadataLabelSequence")*/
	@GeneratedValue
	public Long getMetadataLableId() {
		return metadataLableId;
	}
	public void setMetadataLableId(Long metadataLableId) {
		this.metadataLableId = metadataLableId;
	}
	
	@OneToOne
	@JoinColumn(name="LANGUAGE_ID")
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	
	@Column(name="METADATA_LABEL_NAME")
	public String getMetadataLableName() {
		return metadataLableName;
	}
	public void setMetadataLableName(String metadataLableName) {
		this.metadataLableName = metadataLableName;
	}
	
	@Column(name="METADATA_LABEL_VALUE")
	public String getMetadataLableValue() {
		return metadataLableValue;
	}
	public void setMetadataLableValue(String metadataLableValue) {
		this.metadataLableValue = metadataLableValue;
	}

}
