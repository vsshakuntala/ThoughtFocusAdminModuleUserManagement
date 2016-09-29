package com.tf.usermanagement.dto;

import java.io.Serializable;
import java.util.List;

public class MachineJsonDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Long> params;
	

	public MachineJsonDto(List<Long> params) {
		super();
		this.params = params;
	}

	public List<Long> getParams() {
		return params;
	}

	public void setParams(List<Long> params) {
		this.params = params;
	}

	@Override
	public String toString() {
		return "MachineJsonDto [params=" + params + "]";
	}
	
	
	
}
