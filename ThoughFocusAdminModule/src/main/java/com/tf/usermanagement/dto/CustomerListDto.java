package com.tf.usermanagement.dto;

import java.io.Serializable;
import java.util.List;

public class CustomerListDto implements Serializable{
    
    private List<Long> custList
    ;

    /**
     * @return the custList
     */
    public List<Long> getCustList() {
        return custList;
    }

    /**
     * @param custList the custList to set
     */
    public void setCustList(List<Long> custList) {
        this.custList = custList;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("CustomerListDto [custList=");
	builder.append(custList);
	builder.append("]");
	return builder.toString();
    }

    
}
