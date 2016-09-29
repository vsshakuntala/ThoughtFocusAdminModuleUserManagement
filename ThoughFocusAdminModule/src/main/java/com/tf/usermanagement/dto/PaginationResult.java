package com.tf.usermanagement.dto;



/**
 * Default structure for pagination result.
 * @author Utpal Kant Sharma
 *
 */
public class PaginationResult {
	
	private Object data;
	private boolean hasNext;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaginationResult [data=");
		builder.append(data);
		builder.append(", hasNext=");
		builder.append(hasNext);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
