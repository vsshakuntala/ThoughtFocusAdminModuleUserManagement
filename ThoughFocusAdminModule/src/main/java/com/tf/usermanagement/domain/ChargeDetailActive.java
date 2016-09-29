package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * The persistent class for the database view VW_CHARGE_DETAIL_ACTIVE.
 * 
 * @author Arvind Rao
 * 
 */
@Entity
@Table(name="VW_CHARGE_DETAIL_ACTIVE")
public class ChargeDetailActive implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer chargeId;
	private Integer organizationId;
	private String chargeReference;
	private Integer chargeTypeId;
	private BigDecimal defaultAmount;
	private String defaultCurrencyCode;
	private Long languageId; 
	private String name;
	private String description;
	private Boolean autoApply;
	private ChargeDetailActivePK id;
	
	public ChargeDetailActive() {
	}

	@EmbeddedId
	public ChargeDetailActivePK getId() {
		return id;
	}

	public void setId(ChargeDetailActivePK id) {
		this.id = id;
	}

	@Column(name = "CHARGE_ID", insertable=false, updatable=false)
	public Integer getChargeId() {
		return chargeId;
	}

	public void setChargeId(Integer chargeId) {
		this.chargeId = chargeId;
	}

	@Column(name = "ORGANIZATION_ID", insertable=false, updatable=false)
	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "CHARGE_REFERENCE")
	public String getChargeReference() {
		return chargeReference;
	}

	public void setChargeReference(String chargeReference) {
		this.chargeReference = chargeReference;
	}

	@Column(name = "CHARGE_TYPE_ID")
	public Integer getChargeTypeId() {
		return chargeTypeId;
	}

	public void setChargeTypeId(Integer chargeTypeId) {
		this.chargeTypeId = chargeTypeId;
	}

	@Column(name = "DEFAULT_AMOUNT")
	public BigDecimal getDefaultAmount() {
		return defaultAmount;
	}

	public void setDefaultAmount(BigDecimal defaultAmount) {
		this.defaultAmount = defaultAmount;
	}

	@Column(name = "DEFAULT_CURRENCY_CODE")
	public String getDefaultCurrencyCode() {
		return defaultCurrencyCode;
	}

	public void setDefaultCurrencyCode(String defaultCurrencyCode) {
		this.defaultCurrencyCode = defaultCurrencyCode;
	}

	@Column(name = "LANGUAGE_ID", insertable=false, updatable=false)
	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "AUTO_APPLY")
	public Boolean getAutoApply() {
		return autoApply;
	}

	public void setAutoApply(Boolean autoApply) {
		this.autoApply = autoApply;
	}
}