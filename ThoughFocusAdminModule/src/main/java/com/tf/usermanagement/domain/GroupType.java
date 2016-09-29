package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the GROUP_TYPE database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="GROUP_TYPE")
public class GroupType implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer groupTypeId;
	private String description;

    public GroupType() {
    }

	@Id
	@Column(name="GROUP_TYPE_ID")
	public Integer getGroupTypeId() {
		return groupTypeId;
	}


	public void setGroupTypeId(Integer groupTypeId) {
		this.groupTypeId = groupTypeId;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "GroupType [groupTypeId=" + groupTypeId
				+ ", description=" + description + "]";
	}

}