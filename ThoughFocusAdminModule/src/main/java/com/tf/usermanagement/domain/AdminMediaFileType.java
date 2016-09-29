package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="MEDIA_FILE_TYPE")
public class AdminMediaFileType implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long mediaFileTypeId;
	private String description;
	
	@Id
	@Column(name="MEDIA_FILE_TYPE_ID")
	public Long getMediaFileTypeId() {
		return mediaFileTypeId;
	}
	public void setMediaFileTypeId(Long mediaFileTypeId) {
		this.mediaFileTypeId = mediaFileTypeId;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
