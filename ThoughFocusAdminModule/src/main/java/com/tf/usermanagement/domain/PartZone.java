package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the PART_ZONE database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name = "PART_ZONE")
public class PartZone implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long zoneId;
	private boolean active;
	private Integer bottomCoordinate;
	private Long createdBy;
	private Timestamp createdDate;
	private Integer entryId;
	private Integer leftCoordinate;
	private Long modifiedBy;
	private Timestamp modifiedDate;
	private Integer rightCoordinate;
	private Integer topCoordinate;
	private Bom bom;
	private Set<Media> media;

	public PartZone() {
	}

	@Id
	@Column(name = "ZONE_ID")
	@GeneratedValue
	public Long getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(Long zoneId) {
		this.zoneId = zoneId;
	}

	@Column(name = "ACTIVE")
	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Column(name = "BOTTOM_COORDINATE")
	public Integer getBottomCoordinate() {
		return this.bottomCoordinate;
	}

	public void setBottomCoordinate(Integer bottomCoordinate) {
		this.bottomCoordinate = bottomCoordinate;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "CREATED_DATE")
	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "ENTRY_ID")
	public Integer getEntryId() {
		return this.entryId;
	}

	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}

	@Column(name = "LEFT_COORDINATE")
	public Integer getLeftCoordinate() {
		return this.leftCoordinate;
	}

	public void setLeftCoordinate(Integer leftCoordinate) {
		this.leftCoordinate = leftCoordinate;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "RIGHT_COORDINATE")
	public Integer getRightCoordinate() {
		return this.rightCoordinate;
	}

	public void setRightCoordinate(Integer rightCoordinate) {
		this.rightCoordinate = rightCoordinate;
	}

	@Column(name = "TOP_COORDINATE")
	public Integer getTopCoordinate() {
		return this.topCoordinate;
	}

	public void setTopCoordinate(Integer topCoordinate) {
		this.topCoordinate = topCoordinate;
	}

	// bi-directional many-to-one association to Bom
	@ManyToOne
	@JoinColumn(name = "BOM_ID")
	public Bom getBom() {
		return this.bom;
	}

	public void setBom(Bom bom) {
		this.bom = bom;
	}

	@OneToMany
	@JoinColumn(name="MEDIA_ID")
	public Set<Media> getMedia() {
		return media;
	}
	
	public void setMedia(Set<Media> media) {
		this.media = media;
	}

	@Override
	public String toString() {
		return "PartZone [zoneId=" + zoneId + ", active=" + active
				+ ", bottomCoordinate=" + bottomCoordinate + ", entryId="
				+ entryId + ", leftCoordinate=" + leftCoordinate
				+ ", rightCoordinate=" + rightCoordinate + ", topCoordinate="
				+ topCoordinate + "]";
	}
	
}