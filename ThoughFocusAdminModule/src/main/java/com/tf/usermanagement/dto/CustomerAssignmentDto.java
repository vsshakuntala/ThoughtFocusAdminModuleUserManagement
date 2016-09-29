package com.tf.usermanagement.dto;

import java.io.Serializable;

public class CustomerAssignmentDto implements Serializable{
    
    private String customername;
    private String customernum;
    private String billtonum;
    private String addressone;
    private String city;
    private String state;
    private String postal;
    private String status;
    /**
     * @return the customername
     */
    public String getCustomername() {
        return customername;
    }
    /**
     * @param customername the customername to set
     */
    public void setCustomername(String customername) {
        this.customername = customername;
    }
    /**
     * @return the customernum
     */
    public String getCustomernum() {
        return customernum;
    }
    /**
     * @param customernum the customernum to set
     */
    public void setCustomernum(String customernum) {
        this.customernum = customernum;
    }
    /**
     * @return the billtonum
     */
    public String getBilltonum() {
        return billtonum;
    }
    /**
     * @param billtonum the billtonum to set
     */
    public void setBilltonum(String billtonum) {
        this.billtonum = billtonum;
    }
    /**
     * @return the addressone
     */
    public String getAddressone() {
        return addressone;
    }
    /**
     * @param addressone the addressone to set
     */
    public void setAddressone(String addressone) {
        this.addressone = addressone;
    }
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * @return the state
     */
    public String getState() {
        return state;
    }
    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }
    /**
     * @return the postal
     */
    public String getPostal() {
        return postal;
    }
    /**
     * @param postal the postal to set
     */
    public void setPostal(String postal) {
        this.postal = postal;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("CustomerAssignmentDto [customername=");
	builder.append(customername);
	builder.append(", customernum=");
	builder.append(customernum);
	builder.append(", billtonum=");
	builder.append(billtonum);
	builder.append(", addressone=");
	builder.append(addressone);
	builder.append(", city=");
	builder.append(city);
	builder.append(", state=");
	builder.append(state);
	builder.append(", postal=");
	builder.append(postal);
	builder.append(", status=");
	builder.append(status);
	builder.append("]");
	return builder.toString();
    }
    
    

}
