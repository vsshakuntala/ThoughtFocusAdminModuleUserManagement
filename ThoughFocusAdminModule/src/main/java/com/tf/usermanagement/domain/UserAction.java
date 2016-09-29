package com.tf.usermanagement.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * The persistent class for the USER_ACTION database table.
 * 
 * @author Manaswita Mishra
 * 
 */
@Entity
@Table(name="USER_ACTION")
public class UserAction implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long userActionId;
	private User user;
	private Long userId;
	private Long actionId;
	private Action action;
	private Timestamp startTime;
	private Timestamp endTime;
	private Timestamp createdDate;
	private Long createdBy;
	private Timestamp modifiedDate;
	private Long modifiedBy;
	private Set<UserActionMetadataValue> userActionMetadataValues;

	@Id
	@Column(name = "USER_ACTION_ID")
	/*@SequenceGenerator(name = "UserActionSequence", sequenceName = "USER_ACTION_SEQ")
	@GeneratedValue(generator="UserActionSequence")*/
	@GeneratedValue
	public Long getUserActionId() {
		return userActionId;
	}

	public void setUserActionId(Long userActionId) {
		this.userActionId = userActionId;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "ACTION_ID")
	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "ACTION_ID", insertable = false, updatable = false)
	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	

	@Column(name = "START_TIME")
	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	@Column(name = "END_TIME")
	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	@Column(name = "CREATED_DATE")
	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_BY")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "MODIFIED_DATE")
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Column(name = "MODIFIED_BY")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	@Override
	public int hashCode(){
		return this.userActionId.hashCode();
	}
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
	@JoinColumn(name="USER_ACTION_ID")
	public Set<UserActionMetadataValue> getUserActionMetadataValues() {
		return userActionMetadataValues;
	}

	public void setUserActionMetadataValues(Set<UserActionMetadataValue> userActionMetadataValues) {
		this.userActionMetadataValues = userActionMetadataValues;
	}

	@Override
	public boolean equals(Object obj){
		boolean result=false;
		if (obj instanceof UserAction){
			UserAction a=(UserAction)obj;
			result = (a.getUserActionId().equals(this.userActionId));
		}
		return result;
	}
	
	
}
