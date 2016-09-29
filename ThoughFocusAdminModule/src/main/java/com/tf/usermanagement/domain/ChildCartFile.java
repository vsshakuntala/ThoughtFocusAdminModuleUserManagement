package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the CHILD_CART_FILE database table.
 * 
 * @author Arvind Rao
 * 
 */
@Entity
@Table(name="CHILD_CART_FILE")
public class ChildCartFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long childCartFileId;
	private Long childCartId;
	private String fileName;
	private Blob fileData;
	private Boolean active;
	private Long createdBy;
	private Date createdDate;
	private Long modifiedBy;
	private Date modifiedDate;

	public ChildCartFile() {
	}

	@Id
	@Column(name = "CHILD_CART_FILE_ID")
	/*@SequenceGenerator(name = "childCartFileSequence", sequenceName = "SEQ_CHILDCARTFILE")
	@GeneratedValue(generator="childCartFileSequence")*/
	@GeneratedValue
	public Long getChildCartFileId() {
		return childCartFileId;
	}

	public void setChildCartFileId(Long childCartFileId) {
		this.childCartFileId = childCartFileId;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Column(name = "MODIFIED_DATE")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@Column(name="FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Column(name="FILE_DATA")
	public Blob getFileData() {
		return fileData;
	}

	public void setFileData(Blob fileData) {
		this.fileData = fileData;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ChildCartFile)) {
			return false;
		}
		ChildCartFile castOther = (ChildCartFile) other;
		return (this.childCartFileId.equals(castOther.childCartFileId))
				&& (this.childCartFileId.equals(castOther.childCartFileId));

	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.childCartFileId ^ (this.childCartFileId >>> 32)));
		return hash;
	}

	@Column(name="CHILD_CART_ID")
	public Long getChildCartId() {
		return childCartId;
	}

	public void setChildCartId(Long childCartId) {
		this.childCartId = childCartId;
	}
}