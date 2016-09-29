package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the ACTION_METADATA database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="ACTION_METADATA")
public class ActionMetadata implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long actionMetadataId;
	private String description;
	private Long actionId;

	@Id
	@Column(name = "ACTION_METADATA_ID")
	public Long getActionMetadataId() {
		return actionMetadataId;
	}

	public void setActionMetadataId(Long actionMetadataId) {
		this.actionMetadataId = actionMetadataId;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "ACTION_ID")
	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

}
