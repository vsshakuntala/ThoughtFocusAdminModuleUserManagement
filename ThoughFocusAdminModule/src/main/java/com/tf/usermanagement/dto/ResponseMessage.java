package com.tf.usermanagement.dto;

import java.io.Serializable;

public class ResponseMessage implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -6441912769850355124L;
    
    private String responseMessage;
    
    

    public ResponseMessage(String responseMessage) {
	super();
	this.responseMessage = responseMessage;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }    

}
