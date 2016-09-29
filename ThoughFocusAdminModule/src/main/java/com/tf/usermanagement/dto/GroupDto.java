package com.tf.usermanagement.dto;

import java.io.Serializable;

public class GroupDto implements Serializable{
    
    private long groupId;
    private String groupName;
    
    
    
    /**
     * 
     */
    public GroupDto() {
	
    }
    /**
     * @param groupId
     * @param groupName
     */
    public GroupDto(Long groupId, String groupName) {
	super();
	this.groupId = groupId;
	this.groupName = groupName;
    }
    /**
     * @return the groupId
     */
    public long getGroupId() {
        return groupId;
    }
    /**
     * @param groupId the groupId to set
     */
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
    /**
     * @return the groupName
     */
    public String getGroupName() {
        return groupName;
    }
    /**
     * @param groupName the groupName to set
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("GroupDto [groupId=");
	builder.append(groupId);
	builder.append(", groupName=");
	builder.append(groupName);
	builder.append("]");
	return builder.toString();
    }
    
    

}
