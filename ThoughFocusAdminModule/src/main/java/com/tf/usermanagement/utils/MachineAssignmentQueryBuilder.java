package com.tf.usermanagement.utils;

import org.springframework.stereotype.Component;

import com.tf.usermanagement.exceptions.InsufficientDataException;


@Component
public class MachineAssignmentQueryBuilder {
	 
    
    
	private final String ASSIGNEDQUERY="select  Cat.CATALOG_ID as catalogId from USERS Usr "
		+ "inner join USER_ORG_MAP UsrOrg on Usr.USER_ID = UsrOrg.USER_ID AND  UsrOrg.ACTIVE = 1 "
		+ "inner join ORGANIZATION Org on Org.ORGANIZATION_ID = UsrOrg.ORGANIZATION_ID "
		+ "inner join CUSTOMER_ORGANIZATION_MAP CustOrg on UsrOrg.ORGANIZATION_ID =CustOrg.ORGANIZATION_ID AND CustOrg.ACTIVE = 1 "
		+ "inner join CUSTOMER Cust on Cust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND Cust.ACTIVE = 1 "
		+ "inner join USER_CUSTOMER UsrCust on CustOrg.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND UsrCust.ACTIVE = 1 AND Usr.USER_ID = UsrCust.USER_ID "
		+ "LEFT join CATALOG Cat on UsrCust.CUSTOMER_ID = Cat.CUSTOMER_ID AND Cat.ORGANIZATION_ID = CustOrg.ORGANIZATION_ID AND Cat.ACTIVE = 1 "
		+ "INNER JOIN USER_CATALOG UsrCat on Usr.USER_ID = UsrCat.USER_ID AND Cat.CATALOG_ID=UsrCat.CATALOG_ID and UsrCat.ACTIVE=1 where Usr.USER_ID = ";
    	   
    /**
     * This is used to get the query for customer which has assigned based on filter criteria
     * @param userId
     * @param orgId
     * @param custName
     * @param custNumber
     * @param billToNumber
     * @param addressOne
     * @param city
     * @param state
     * @param postalCode
     * @param status
     * @return 
     */
    public String allMachineRemoveFromUser(Long userId,Long orgId,Long catId,String model,String catalog_reference,String customer_name,
	    String status,String group_name){
	
	StringBuilder builder = new StringBuilder();
	
	if(userId == null || userId < 1){
	    
	    throw new InsufficientDataException("User id is mandatory ");
	}
	
	if(orgId == null || orgId < 1){
	    throw new InsufficientDataException("orgId is mandatory ");
	}
	
	builder.append(ASSIGNEDQUERY).append(userId).append(" AND UsrOrg.ORGANIZATION_ID= ").append(orgId);
	
	
	if(catId != null && catId< 1){
	   
	       builder.append(" AND cat.CATALOG_NAME LIKE '").append(catId).append("'");
	   
	}
	
	if(model != null && model.trim().length() > 0){
	    builder.append(" AND cat.model LIKE '").append(getBuilderString(model)).append("'");
	    }
	if(catalog_reference != null && catalog_reference.trim().length() > 0){
	    builder.append(" AND cat.catalog_reference LIKE '").append(getBuilderString(catalog_reference)).append("'");
	    }
	if(customer_name != null && customer_name.trim().length() > 0){
	    builder.append(" AND cus.customer_name LIKE '").append(getBuilderString(customer_name)).append("'");
	}
	if(group_name != null && group_name.trim().length() > 0){
	    builder.append(" AND grp.group_name '").append(getBuilderString(group_name)).append("'");
	    }
	
	
	return builder.toString();
    
    }
    
    public String allMachineAssignToUser(Long userId,Long orgId,Long catId,String model,String catalog_reference,String customer_name,
    	    String status,String group_name){
	
    	StringBuilder builder = new StringBuilder();
    	
    	if(userId == null || userId < 1){
    	    
    	    throw new InsufficientDataException("User id is mandatory ");
    	}
    	
    	if(orgId == null || orgId < 1){
    	    throw new InsufficientDataException("orgId is mandatory ");
    	}
    	
    	builder.append(ASSIGNEDQUERY).append(userId).append(" AND UsrOrg.ORGANIZATION_ID= ").append(orgId);
    	
    	
    	if(catId != null && catId< 1){
    	   
    	       builder.append(" AND cat.CATALOG_NAME LIKE '").append(catId).append("'");
    	   
    	}
    	
    	if(model != null && model.trim().length() > 0){
    	    builder.append(" AND cat.model LIKE '").append(getBuilderString(model)).append("'");
    	    }
    	if(catalog_reference != null && catalog_reference.trim().length() > 0){
    	    builder.append(" AND cat.catalog_reference LIKE '").append(getBuilderString(catalog_reference)).append("'");
    	    }
    	if(customer_name != null && customer_name.trim().length() > 0){
    	    builder.append(" AND cus.customer_name LIKE '").append(getBuilderString(customer_name)).append("'");
    	}
    	if(group_name != null && group_name.trim().length() > 0){
    	    builder.append(" AND grp.group_name '").append(getBuilderString(group_name)).append("'");
    	    }
    	
    	
    	return builder.toString();
    }
    
    public String getBuilderString(String str){

	   if(str.contains("*") && str.contains("?")){
	       str= str.replaceAll("\\*", "\\%");
	       str= str.replaceAll("\\?", "\\_");
	       return str;
	   }else if(str.contains("*")){
	       str= str.replaceAll("\\*", "\\%");
	       return str;
	    }else if(str.contains("?")){
		str= str.replaceAll("\\?", "\\_");
		return str;
	    }else{
		return "%"+str+"%";
	    }	
    }
}
