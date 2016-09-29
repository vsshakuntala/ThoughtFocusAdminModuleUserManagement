package com.tf.usermanagement.dto;

import java.io.Serializable;

public class RemoveMachineDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MachineAssignmentDto params;

	public MachineAssignmentDto getParams() {
		return params;
	}

	public void setParams(MachineAssignmentDto params) {
		this.params = params;
	}

	public RemoveMachineDto(MachineAssignmentDto params) {
		super();
		this.params = params;
	}

	@Override
	public String toString() {
		return "RemoveMachineDto [params=" + params + "]";
	}

}
