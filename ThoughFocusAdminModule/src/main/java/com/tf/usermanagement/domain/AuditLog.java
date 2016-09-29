package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "AUDITLOG")
public class AuditLog implements Serializable{
 
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "AUDIT_LOG_ID")
	/*@SequenceGenerator(name = "AuditSequence", sequenceName = "AUDIT_SEQ")
	@GeneratedValue(generator="AuditSequence")*/
	@GeneratedValue
	private Long auditLogId;
	
	@Column(name = "ACTION")
	private String action;
	
	@Column(name = "DETAIL")
	private String detail;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "ENTITY_ID")
	private long entityId;
	
	@Column(name = "ENTITY_NAME")
	private String entityName;
	
	@Column(name = "MESSAGE")
	private String message;
	
	
	
	public AuditLog() {
		super();
	}

	public AuditLog( String action, String detail,
			Date createdDate, long entityId, String entityName,String message) {
		super();
		this.action = action;
		this.detail = detail;
		this.createdDate = createdDate;
		this.entityId = entityId;
		this.entityName = entityName;
		this.message=message;
	}

	public Long getAuditLogId() {
		return auditLogId;
	}
	public void setAuditLogId(Long auditLogId) {
		this.auditLogId = auditLogId;
	}
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public long getEntityId() {
		return entityId;
	}
	public void setEntityId(long entityId) {
		this.entityId = entityId;
	}
	
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
