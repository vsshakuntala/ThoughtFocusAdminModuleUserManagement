package com.tf.usermanagement.dto;

import java.util.Arrays;
import java.util.Map;

public class CreateUserWebServiceResponseModel {
	private String returnCode;
	private String[] returnMessages;
	private Map<String, String> externalReferenceMap;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String[] getReturnMessages() {
		return returnMessages;
	}

	public void setReturnMessages(String[] returnMessages) {
		this.returnMessages = returnMessages;
	}

	public Map<String, String> getExternalUserReference() {
		return externalReferenceMap;
	}

	public void setExternalUserReference(
			Map<String, String> externalReferenceMap) {
		/*for (Map.Entry<String, String> entry : externalUserReferenceMap
				.entrySet()) {
			this.externalUserReferenceMap.put(entry.getKey(), entry.getValue());
		}*/
		 this.externalReferenceMap = externalReferenceMap;

	}

	@Override
	public String toString() {
		return "CreateUserWebServiceResponseModel [returnCode=" + returnCode
				+ ", returnMessages=" + Arrays.toString(returnMessages)
				+ ", externalUserReference=" + externalReferenceMap + "]";
	}

}
