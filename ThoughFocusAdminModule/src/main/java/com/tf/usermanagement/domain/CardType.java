package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CARD_TYPE database table.
 * 
 */
@Entity
@Table(name="CARD_TYPE")
public class CardType implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer cardTypeId;
	private String reference;
	private String description;

    public CardType() {
    }

	@Id
	@Column(name="CARD_TYPE_ID")
	public Integer getCardTypeId() {
		return this.cardTypeId;
	}

	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}

	@Column(name="REFERENCE")
	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	@Column(name="DESCRIPTION")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}