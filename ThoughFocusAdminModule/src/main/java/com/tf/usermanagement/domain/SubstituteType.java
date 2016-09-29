package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SUBSTITUTE_TYPE database table.
 * 
 */
@Entity
@Table(name="SUBSTITUTE_TYPE")
public class SubstituteType implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer substituteTypeId;

	private String description;

    public SubstituteType() {
    }

	@Id
	@Column(name="SUBSTITUTE_TYPE_ID")
	public Integer getSubstituteTypeId() {
		return this.substituteTypeId;
	}

	public void setSubstituteTypeId(Integer substituteTypeId) {
		this.substituteTypeId = substituteTypeId;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}