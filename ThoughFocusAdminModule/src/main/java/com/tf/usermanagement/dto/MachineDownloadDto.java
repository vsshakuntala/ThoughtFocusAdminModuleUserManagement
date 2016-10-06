package com.tf.usermanagement.dto;

public class MachineDownloadDto {
	
	private Long catalog_id;
	private String model;
	private String catalog_reference;
	private String customer_name;
	private String status;
	private String group_name;
	private String catalog_name;
	
	
	public MachineDownloadDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getCatalog_id() {
		return catalog_id;
	}


	public void setCatalog_id(Long catalog_id) {
		this.catalog_id = catalog_id;
	}


	public String getModel() {
		return model;
	}


	public void setModel(String model) {
		this.model = model;
	}


	public String getCatalog_reference() {
		return catalog_reference;
	}


	public void setCatalog_reference(String catalog_reference) {
		this.catalog_reference = catalog_reference;
	}


	public String getCustomer_name() {
		return customer_name;
	}


	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getGroup_name() {
		return group_name;
	}


	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}


	public MachineDownloadDto(Long catalog_id, String model, String catalog_reference, String customer_name,
			String status, String group_name) {
		super();
		this.catalog_id = catalog_id;
		this.model = model;
		this.catalog_reference = catalog_reference;
		this.customer_name = customer_name;
		this.status = status;
		this.group_name = group_name;
	}


	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	    StringBuilder builder = new StringBuilder();
	    builder.append("MachineDownloadDto [catalog_id=");
	    builder.append(catalog_id);
	    builder.append(", model=");
	    builder.append(model);
	    builder.append(", catalog_reference=");
	    builder.append(catalog_reference);
	    builder.append(", customer_name=");
	    builder.append(customer_name);
	    builder.append(", status=");
	    builder.append(status);
	    builder.append(", group_name=");
	    builder.append(group_name);
	    builder.append(", catalog_name=");
	    builder.append(catalog_name);
	    builder.append("]");
	    return builder.toString();
	}


	public String getCatalog_name() {
	    return catalog_name;
	}


	public void setCatalog_name(String catalog_name) {
	    this.catalog_name = catalog_name;
	}


	
}
