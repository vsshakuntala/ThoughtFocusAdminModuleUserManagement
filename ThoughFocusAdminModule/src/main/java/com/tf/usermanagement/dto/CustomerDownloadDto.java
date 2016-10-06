/**
 * 
 */
package com.tf.usermanagement.dto;

/**
 * @author user
 *
 */
public class CustomerDownloadDto {
    
    private String Name;
    private Long CustomerNum;
    private String Country;
    private String Address1;
    private String City;
    private String State;
    private String Postal;
    private String Status;
    private String Type;
    private String customerReference;
    
    
    /**
     * @return the type
     */
    public String getType() {
        return Type;
    }
    /**
     * @param type the type to set
     */
    public void setType(String type) {
        Type = type;
    }
    /**
     * @return the name
     */
    public String getName() {
        return Name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        Name = name;
    }
    /**
     * @return the customerNum
     */
    public Long getCustomerNum() {
        return CustomerNum;
    }
    /**
     * @param customerNum the customerNum to set
     */
    public void setCustomerNum(Long customerNum) {
        CustomerNum = customerNum;
    }
    /**
     * @return the country
     */
    public String getCountry() {
        return Country;
    }
    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        Country = country;
    }
    /**
     * @return the address1
     */
    public String getAddress1() {
        return Address1;
    }
    /**
     * @param address1 the address1 to set
     */
    public void setAddress1(String address1) {
        Address1 = address1;
    }
    /**
     * @return the city
     */
    public String getCity() {
        return City;
    }
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        City = city;
    }
    /**
     * @return the state
     */
    public String getState() {
        return State;
    }
    /**
     * @param state the state to set
     */
    public void setState(String state) {
        State = state;
    }
    /**
     * @return the postal
     */
    public String getPostal() {
        return Postal;
    }
    /**
     * @param postal the postal to set
     */
    public void setPostal(String postal) {
        Postal = postal;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return Status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        Status = status;
    }
   
   
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("CustomerDownloadDto [Name=");
	builder.append(Name);
	builder.append(", CustomerNum=");
	builder.append(CustomerNum);
	builder.append(", Country=");
	builder.append(Country);
	builder.append(", Address1=");
	builder.append(Address1);
	builder.append(", City=");
	builder.append(City);
	builder.append(", State=");
	builder.append(State);
	builder.append(", Postal=");
	builder.append(Postal);
	builder.append(", Status=");
	builder.append(Status);
	builder.append(", Type=");
	builder.append(Type);
	builder.append(", customerReference=");
	builder.append(customerReference);
	builder.append("]");
	return builder.toString();
    }
    public String getCustomerReference() {
	return customerReference;
    }
    public void setCustomerReference(String customerReference) {
	this.customerReference = customerReference;
    }
    
    

}
