package com.tf.usermanagement.dto;

import java.io.Serializable;

public class GroupJsonDto implements Serializable{
    
    private GroupAllDto params;

    /**
     * @return the params
     */
    public GroupAllDto getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(GroupAllDto params) {
        this.params = params;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("GroupJsonDto [params=");
	builder.append(params);
	builder.append("]");
	return builder.toString();
    }
    
    

}
