package com.tf.usermanagement.dto;

import java.io.Serializable;

public class CustomerCount implements Serializable{
    
    private Long availableCustomerCount;
    private Long assignedCustomerCount;
    /**
     * @return the availableCustomerCount
     */
    public Long getAvailableCustomerCount() {
        return availableCustomerCount;
    }
    /**
     * @param availableCustomerCount the availableCustomerCount to set
     */
    public void setAvailableCustomerCount(Long availableCustomerCount) {
        this.availableCustomerCount = availableCustomerCount;
    }
    /**
     * @return the assignedCustomerCount
     */
    public Long getAssignedCustomerCount() {
        return assignedCustomerCount;
    }
    /**
     * @param assignedCustomerCount the assignedCustomerCount to set
     */
    public void setAssignedCustomerCount(Long assignedCustomerCount) {
        this.assignedCustomerCount = assignedCustomerCount;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("CustomerCount [availableCustomerCount=");
	builder.append(availableCustomerCount);
	builder.append(", assignedCustomerCount=");
	builder.append(assignedCustomerCount);
	builder.append("]");
	return builder.toString();
    }
   
}
