package com.tf.usermanagement.domain;

import java.io.Serializable;


public interface IAuditLog {

	public Long getId();	
	public String getLogDetail();
	public Serializable getCompositeKey();
	
}
