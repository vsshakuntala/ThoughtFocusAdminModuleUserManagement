package com.tf.usermanagement.dto;

import java.util.List;

public class RoleAllDto {

	private List<RolesDto> assigned;
    private List<RolesDto> unAssigned;
    /**
     * @return the assigned
     */
    public List<RolesDto> getAssigned() {
        return assigned;
    }
    /**
     * @param assigned the assigned to set
     */
    public void setAssigned(List<RolesDto> assigned) {
        this.assigned = assigned;
    }
    /**
     * @return the unAssigned
     */
    public List<RolesDto> getUnAssigned() {
        return unAssigned;
    }
    /**
     * @param unAssigned the unAssigned to set
     */
    public void setUnAssigned(List<RolesDto> unAssigned) {
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
