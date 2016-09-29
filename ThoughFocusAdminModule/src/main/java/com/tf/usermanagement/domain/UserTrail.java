package com.tf.usermanagement.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the USER_TRAIL database table.
 * 
 * @author Arvind.C
 * 
 */
@Entity
@Table(name="USER_TRAIL")
public class UserTrail implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long trailId;
	private String trailNewValue;
	private String trailOldValue;
	private String trailType;
	private User user;

    public UserTrail() {
    }


	@Id
	@Column(name="TRAIL_ID")
	public Long getTrailId() {
		return this.trailId;
	}

	public void setTrailId(Long trailId) {
		this.trailId = trailId;
	}


	@Column(name="TRAIL_NEW_VALUE")
	public String getTrailNewValue() {
		return this.trailNewValue;
	}

	public void setTrailNewValue(String trailNewValue) {
		this.trailNewValue = trailNewValue;
	}


	@Column(name="TRAIL_OLD_VALUE")
	public String getTrailOldValue() {
		return this.trailOldValue;
	}

	public void setTrailOldValue(String trailOldValue) {
		this.trailOldValue = trailOldValue;
	}


	@Column(name="TRAIL_TYPE")
	public String getTrailType() {
		return this.trailType;
	}

	public void setTrailType(String trailType) {
		this.trailType = trailType;
	}


	//bi-directional many-to-one association to User
    @ManyToOne
	@JoinColumn(name="USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "UserTrail [trailId=" + trailId + ", trailNewValue="
				+ trailNewValue + ", trailOldValue=" + trailOldValue
				+ ", trailType=" + trailType + "]";
	}
	
	
	
}