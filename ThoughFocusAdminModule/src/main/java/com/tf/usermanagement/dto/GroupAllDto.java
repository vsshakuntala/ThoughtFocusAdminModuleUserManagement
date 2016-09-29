package com.tf.usermanagement.dto;

import java.io.Serializable;
import java.util.List;

public class GroupAllDto implements Serializable{
    
    private List<GroupDto> assigned;
    private List<GroupDto> unAssigned;
    /**
     * @return the assigned
     */
    public List<GroupDto> getAssigned() {
        return assigned;
    }
    /**
     * @param assigned the assigned to set
     */
    public void setAssigned(List<GroupDto> assigned) {
        this.assigned = assigned;
    }
    /**
     * @return the unAssigned
     */
    public List<GroupDto> getUnAssigned() {
        return unAssigned;
    }
    /**
     * @param unAssigned the unAssigned to set
     */
    public void setUnAssigned(List<GroupDto> unAssigned) {
        this.unAssigned = unAssigned;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("GroupAllDto [assigned=");
	builder.append(assigned);
	builder.append(", unAssigned=");
	builder.append(unAssigned);
	builder.append("]");
	return builder.toString();
    }
    
    

}
