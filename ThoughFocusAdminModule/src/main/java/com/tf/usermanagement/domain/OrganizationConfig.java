package com.tf.usermanagement.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The persistent class for the ORGANIZATION_CONFIG database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="ORGANIZATION_CONFIG")
public class OrganizationConfig implements Serializable {

	private static final long serialVersionUID = 1L;

	private OrganizationConfigPK id;
	private Integer organizationId;
	private String configName;
	private String configValue;
	private String description;
	private Boolean active = true;

	@EmbeddedId
	public OrganizationConfigPK getId() {
		return id;
	}

	public void setId(OrganizationConfigPK id) {
		this.id = id;
	}

	@Column(name = "ORGANIZATION_ID", insertable=false, updatable=false)
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}
	
	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "CONFIG_NAME", insertable=false, updatable=false)
	public String getConfigName() {
		return configName;
	}

	public void setConfigName(String configName) {
		this.configName = configName;
	}

	@Column(name = "CONFIG_VALUE", insertable=false, updatable=false)
	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}


}
