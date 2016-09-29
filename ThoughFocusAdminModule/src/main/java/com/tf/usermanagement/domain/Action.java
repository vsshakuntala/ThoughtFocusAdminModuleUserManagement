package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the ACTIONS database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="ACTIONS")
public class Action implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long actionId;
	private String description;
	private Set<ActionMetadata> actionMetadatas;

	@Id
	@Column(name = "ACTION_ID")
	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name="ACTION_ID")
	public Set<ActionMetadata> getActionMetadatas() {
		return actionMetadatas;
	}

	public void setActionMetadatas(Set<ActionMetadata> actionMetadatas) {
		this.actionMetadatas = actionMetadatas;
	}
}
