/**
 * 
 */
package com.tf.usermanagement.utils;

import org.springframework.stereotype.Component;

import com.tf.usermanagement.exceptions.InsufficientDataException;

/**
 * @author Santosh
 *
 */
@Component
public class CustomerAssignmentQueryBuilderForDownload {   
    
    
	private final String UPDATEDUNASSIGNEDQUERY1="select CustomerNum,customerReference,Name,Address1,City,State,Postal,Country,billto,Status,Type,active "
		+ "from (select com.CUSTOMER_ID AS customerNum,c.CUSTOMER_REFERENCE AS customerReference,"
		+ "(Select CUSTOMER_NAME FROM CUSTOMER WHERE CUSTOMER_ID = com.CUSTOMER_ID) AS Name,"
		+ "(SELECT TOP 1 ADDRESS1 FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS Address1,"
		+ "(SELECT TOP 1 CITY FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS City,"
		+ "(SELECT TOP 1 STATE FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS State,"
		+ "(SELECT TOP 1 ZIP_CODE FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS Postal,"
		+ "(SELECT TOP 1 COUNTRY FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS Country,"
		+ "(SELECT TOP 1 ADDRESS_REFERENCE FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS billto,"
		+ "(CASE com.ACTIVE WHEN 1 THEN 'UnASSIGNED' END) AS Status,'' AS Type,com.active AS active "
		+ "from CUSTOMER_ORGANIZATION_MAP com inner join CUSTOMER c on c.CUSTOMER_ID=com.CUSTOMER_ID AND c.ACTIVE=1 "
		+ "where com.ORGANIZATION_ID=";
    private final String UPDATEDUNASSIGNEDQUERY2 =" and com.ACTIVE=1 and com.CUSTOMER_ID not in "
		+ "(SELECT distinct UsrCust.CUSTOMER_ID AS ASSIGNEDID_CUSTID FROM USERS Usr "
		+ "INNER JOIN USER_CUSTOMER UsrCust ON Usr.USER_ID = UsrCust.USER_ID AND UsrCust.ACTIVE = 1 "
		+ "INNER JOIN CUSTOMER Cust ON Cust.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND Cust.ACTIVE = 1 "
		+ "INNER JOIN CUSTOMER_ORGANIZATION_MAP CustOrg ON UsrCust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND CustOrg.ACTIVE = 1 "		
		+ "WHERE Usr.USER_ID = ";       
    
    
    private final String updatedAssignQuery1 = "SELECT CustomerNum,customerReference,Name,Type,Active,Address1,City,State,Postal,Country,billto "
    		+ "from ((SELECT UsrCust.CUSTOMER_ID AS CustomerNum,cust.CUSTOMER_REFERENCE AS customerReference,Cust.CUSTOMER_NAME AS Name, '' as Type,Usr.active as Active ,"
    	+ " (SELECT TOP 1 ADDRESS1 FROM ADDRESS WHERE CUSTOMER_ID = UsrCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS Address1,"
        + "(SELECT TOP 1 CITY FROM ADDRESS WHERE CUSTOMER_ID = UsrCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS City,"
        + "(SELECT TOP 1 STATE FROM ADDRESS WHERE CUSTOMER_ID = UsrCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS State,"
        + "(SELECT TOP 1 ZIP_CODE FROM ADDRESS WHERE CUSTOMER_ID = UsrCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS Postal,"
        + "(SELECT TOP 1 COUNTRY FROM ADDRESS WHERE CUSTOMER_ID = UsrCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS Country,"
        + "(SELECT TOP 1 ADDRESS_REFERENCE FROM ADDRESS WHERE CUSTOMER_ID = UsrCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS billto "
    	+ "FROM   USERS Usr INNER JOIN USER_CUSTOMER UsrCust ON Usr.USER_ID = UsrCust.USER_ID AND UsrCust.ACTIVE = 1 "
    	+ "INNER JOIN CUSTOMER Cust ON Cust.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND Cust.ACTIVE = 1 "
    	+ "INNER JOIN CUSTOMER_ORGANIZATION_MAP CustOrg ON UsrCust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND CustOrg.ACTIVE = 1 "    	
    	+ "WHERE  Usr.USER_ID = ";
    
    private final String updatedAssignQuery2=" )UNION ALL "
    		+ "( SELECT DISTINCT GrpCust.CUSTOMER_ID AS CustomerNum,c.CUSTOMER_REFERENCE AS customerReference,c.customer_name AS Name,Grp.GROUP_NAME AS Type ,usr.active as Active,"
    	+ " (SELECT TOP 1 ADDRESS1 FROM ADDRESS WHERE CUSTOMER_ID = GrpCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1) AS Address1,"
        + "(SELECT TOP 1 CITY FROM ADDRESS WHERE CUSTOMER_ID = GrpCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1) AS City,"
        + "(SELECT TOP 1 STATE FROM ADDRESS WHERE CUSTOMER_ID = GrpCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1) AS State,"
        + "(SELECT TOP 1 ZIP_CODE FROM ADDRESS WHERE CUSTOMER_ID = GrpCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1) AS Postal,"
        + "(SELECT TOP 1 COUNTRY FROM ADDRESS WHERE CUSTOMER_ID = GrpCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1) AS Country,"
        + "(SELECT TOP 1 ADDRESS_REFERENCE FROM ADDRESS WHERE CUSTOMER_ID = GrpCust.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1) AS billto "
    	+ "from users usr "
    	+ "INNER join user_group ug on usr.user_id= ug.user_id  and  ug.active=1 "
    	+ "inner join groups grp on grp.group_id=ug.group_id  and grp.active=1 "
    	+ "inner  JOIN GROUP_CUSTOMER GrpCust ON ug.GROUP_ID = GrpCust.GROUP_ID AND   GrpCust.ACTIVE = 1 "
    	+ "INNER JOIN CUSTOMER c on c.customer_ID=GrpCust.customer_ID  and c.active=1 "    	
    	+ "where usr.user_id=";
    	   
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
    public String allCustomerRemoveFromUser(Long userId,Long orgId,String custName,String custNumber,String billToNumber,String addressOne,
	    String city,String state,String postalCode, String status){
	
	StringBuilder builder = new StringBuilder();
	
	if(userId == null || userId < 1){
	    
	    throw new InsufficientDataException("User id is mandatory ");
	}
	
	if(orgId == null || orgId < 1){
	    throw new InsufficientDataException("orgId is mandatory ");
	}
	
	builder.append(updatedAssignQuery1);
	builder.append(userId).append(" AND CustOrg.ORGANIZATION_ID = ").append(orgId);
	builder.append(updatedAssignQuery2);
	builder.append(userId).append(" AND grp.ORGANIZATION_ID=").append(orgId).append(")) t where t.Active=1 ");
	
	if(custName != null && custName.trim().length() > 0){
	   
	       builder.append("AND t.Name LIKE '").append(getBuilderString(custName)).append("'");
	   
	}
	
	if(custNumber != null && custNumber.trim().length() > 0){
	    builder.append("AND t.customerReference LIKE '").append(getBuilderString(custNumber)).append("'");
	    }
	
	
	builder.append(getBuilderString(billToNumber,addressOne,city,state,postalCode));
	
	return builder.toString();
    
    }
    
    /**
     * This is used to get the query for customer which has unassigned based on filter criteria
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
    public String allCustomerAssignToUser(Long userId,Long orgId,String custName,String custNumber,String billToNumber,String addressOne,
	    String city,String state,String postalCode, String status){
	
	StringBuilder builder = new StringBuilder();
	
	if(userId == null || userId < 1){
	    
	    throw new InsufficientDataException("User id is mandatory ");
	}
	
	if(orgId == null || orgId < 1){
	    throw new InsufficientDataException("orgId is mandatory ");
	}
	builder.append(UPDATEDUNASSIGNEDQUERY1);	
	builder.append(orgId).append(UPDATEDUNASSIGNEDQUERY2).append(userId).append(" AND CustOrg.ORGANIZATION_ID =").append(orgId);
	builder.append(")) t where t.active=1 ");
	
	if(custName != null && custName.trim().length() > 0){
	   
	       builder.append("AND t.Name LIKE '").append(getBuilderString(custName)).append("' ");
	   
	}
	
	if(custNumber != null && custNumber.trim().length() > 0){
	    builder.append("AND t.customerReference LIKE '").append(getBuilderString(custNumber)).append("' ");
	    }
	
	builder.append(getBuilderStringUnAssign(billToNumber,addressOne,city,state,postalCode));
	return builder.toString();
    }
    
    public String getBuilderStringUnAssign(String billToNumber,String addressOne,
	    String city,String state,String postalCode){
	StringBuilder builder = new StringBuilder();
	if(billToNumber != null && billToNumber.trim().length() > 0){
	    builder.append(" AND t.billto LIKE '").append(getBuilderString(billToNumber)).append("'");
	    }
	
	if(addressOne != null && addressOne.trim().length() > 0){
	    builder.append(" AND t.address1 LIKE '").append(getBuilderString(addressOne)).append("'");
	}
	
	if(city != null && city.trim().length() > 0){
	    builder.append(" AND t.City LIKE '").append(getBuilderString(city)).append("'");
		}
	
	if(state != null && state.trim().length() > 0){
	    builder.append(" AND t.State LIKE '").append(getBuilderString(state)).append("'");
		}
	
	if(postalCode != null && postalCode.trim().length() > 0){
	    builder.append(" AND t.Postal LIKE '").append(getBuilderString(postalCode)).append("'");
		}
	return builder.toString();
    }
    
    
    public String getBuilderString(String billToNumber,String addressOne,
	    String city,String state,String postalCode){
	
	StringBuilder builder = new StringBuilder();
	
	if(billToNumber != null && billToNumber.trim().length() > 0){
	    builder.append("AND t.billto LIKE '").append(getBuilderString(billToNumber)).append("'");
	    }
	if(addressOne != null && addressOne.trim().length() > 0){
	    builder.append("AND t.Address1 LIKE '").append(getBuilderString(addressOne)).append("'");
	}
	
	if(city != null && city.trim().length() > 0){
	    builder.append("AND t.City LIKE '").append(getBuilderString(city)).append("'");
		}
	
	if(state != null && state.trim().length() > 0){
	    builder.append("AND t.State LIKE '").append(getBuilderString(state)).append("'");
		}
	
	if(postalCode != null && postalCode.trim().length() > 0){
	    builder.append("AND t.Postal LIKE '").append(getBuilderString(postalCode)).append("'");
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
