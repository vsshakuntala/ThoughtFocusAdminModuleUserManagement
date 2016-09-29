package com.tf.usermanagement.dto;


/**
 * Basic information about pagination.
 * @author Utpal Kant Sharma
 *
 */
public class PaginationDto {
	
	String queryString;
	Integer pageNumber;
	Integer count;
	
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public Integer getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaginationDto [queryString=");
		builder.append(queryString);
		builder.append(", pageNumber=");
		builder.append(pageNumber);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}
	
	

}
