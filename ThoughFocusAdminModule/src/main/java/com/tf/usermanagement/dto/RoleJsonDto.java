package com.tf.usermanagement.dto;

public class RoleJsonDto {

	private RoleAllDto params;

    /**
     * @return the params
     */
    public RoleAllDto getParams() {
        return params;
    }

    /**
     * @param params the params to set
     */
    public void setParams(RoleAllDto params) {
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
