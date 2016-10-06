package com.tf.usermanagement.report;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.spaneos.dtssp.AbstractDataTableReport;
import com.spaneos.dtssp.MSSql2008QueryTemplateBuilder;
import com.spaneos.dtssp.QueryFilterAndParams;
import com.spaneos.dtssp.QueryFilterAndParamsImpl;
import com.spaneos.dtssp.QueryTemplate;
import com.spaneos.dtssp.input.DataTablesInput;

@Component
public class CustomerUnAssignmentReport extends AbstractDataTableReport{
    
    public static final String Name_FILTER = "nameFilter";
    public static final String NUMBER_FILTER = "numberFilter";
    public static final String CITY_FILTER="cityFilter"; 
    public static final String ADDRESS_FILTER="addressOneFilter";
    public static final String STATE_FILTER="stateFilter";
    public static final String POSTAL_FILTER="postalFilter";
    public static final String BILLTO_FILTER="cBillFilter";
    public static final String USERID_FILTER="useridFilter";
    public static final String ORGID_FILTER="orgidFilter";
    public static final String CUSTOTG_FILTER = "custOrgActiveFilter";
    public static final String CUSTOTG_CUSTOMER_FILTER = "custOrgCustomerFilter";
    public static final String OutORGID_FILTER = "outcustOrgCustomerFilter";
    public static final String ORGID_QUERY_FILTER = "orgQueryFilter";
    private String userId;

    @Override
    protected QueryTemplate attachQueryTemplate() {
	MSSql2008QueryTemplateBuilder qtb = new MSSql2008QueryTemplateBuilder();
	String query="select customerNumber,customerName,addressOne,city,state,postal,country,billto,Status,Type,active from (select com.CUSTOMER_ID AS customerNum,"
		+ "(Select CUSTOMER_NAME FROM CUSTOMER WHERE CUSTOMER_ID = com.CUSTOMER_ID) AS customerName,"
		+ "(SELECT TOP 1 ADDRESS1 FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 2) AS addressOne,"
		+ "(SELECT TOP 1 CITY FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 2) AS city,"
		+ "(SELECT TOP 1 STATE FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 2) AS state,"
		+ "(SELECT TOP 1 ZIP_CODE FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 2) AS postal,"
		+ "(SELECT TOP 1 COUNTRY FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 2) AS country,"
		+ "(SELECT TOP 1 ADDRESS_REFERENCE FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 2) AS billto,"
		+ "(CASE com.ACTIVE WHEN 1 THEN 'UnASSIGNED' END) AS Status,'NA' AS Type,com.active AS active "
		+ "from CUSTOMER_ORGANIZATION_MAP com "
		+ "where com.ORGANIZATION_ID=4 and com.ACTIVE=1 and com.CUSTOMER_ID not in "
		+ "(SELECT distinct UsrCust.CUSTOMER_ID AS ASSIGNEDID_CUSTID FROM USERS Usr "
		+ "INNER JOIN USER_CUSTOMER UsrCust ON Usr.USER_ID = UsrCust.USER_ID AND UsrCust.ACTIVE = 1 "
		+ "INNER JOIN CUSTOMER Cust ON Cust.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND Cust.ACTIVE = 1 "
		+ "LEFT JOIN CUSTOMER_ORGANIZATION_MAP CustOrg ON UsrCust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND CustOrg.ACTIVE = 1 "
		+ "INNER JOIN ADDRESS Addr ON UsrCust.CUSTOMER_ID =  Addr.CUSTOMER_ID AND Addr.ACTIVE = 1 AND Addr.ADDRESS_TYPE_ID = 2 "
		+ "LEFT JOIN USER_GROUP UsrGrp ON Usr.USER_ID = UsrGrp.USER_ID AND UsrGrp.ACTIVE = 1 "
		+ "LEFT JOIN GROUPS Grp ON Grp.GROUP_ID = UsrGrp.GROUP_ID AND Grp.ACTIVE = 1 "
		+ "WHERE Usr.USER_ID = 110 AND CustOrg.ORGANIZATION_ID = 4)) t where t.active=1 AND t.customerName like '%cust%'";
	
	String newIq="(select c.CUSTOMER_REFERENCE AS customerNumber,c.CUSTOMER_ID AS customerId,"
		+ "(Select CUSTOMER_NAME FROM CUSTOMER WHERE CUSTOMER_ID = com.CUSTOMER_ID) AS customerName,"
		+ "(SELECT TOP 1 ADDRESS1 FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS addressOne,"
		+ "(SELECT TOP 1 CITY FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS city,"
		+ "(SELECT TOP 1 STATE FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS state,"
		+ "(SELECT TOP 1 ZIP_CODE FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS postal,"
		+ "(SELECT TOP 1 COUNTRY FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS country,"
		+ "(SELECT TOP 1 ADDRESS_REFERENCE FROM ADDRESS WHERE CUSTOMER_ID = com.CUSTOMER_ID AND ADDRESS_TYPE_ID = 1 AND ACTIVE=1) AS billto,"
		+ "(CASE com.ACTIVE WHEN 1 THEN 'UnASSIGNED' END) AS Status,'' AS Type,com.active AS active,com.ORGANIZATION_ID AS orgId   "
		+ "from CUSTOMER_ORGANIZATION_MAP com inner join CUSTOMER c on c.CUSTOMER_ID=com.CUSTOMER_ID AND c.ACTIVE=1 "
		+ "where com.ORGANIZATION_ID=:orgId and com.ACTIVE=1 and com.CUSTOMER_ID not in "
		+ "(SELECT distinct UsrCust.CUSTOMER_ID AS ASSIGNEDID_CUSTID FROM USERS Usr "
		+ "INNER JOIN USER_CUSTOMER UsrCust ON Usr.USER_ID = UsrCust.USER_ID AND UsrCust.ACTIVE = 1 "
		+ "INNER JOIN CUSTOMER Cust ON Cust.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND Cust.ACTIVE = 1 "
		+ "INNER JOIN CUSTOMER_ORGANIZATION_MAP CustOrg ON UsrCust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND CustOrg.ACTIVE = 1 " 	
		+ "where CustOrg.ORGANIZATION_ID = :orgId AND Usr.USER_ID =:userId)) t ";
	
	
	QueryTemplate queryTemplate = qtb.fetchColumns("customerNumber,customerId,customerName,addressOne,city,state,postal,country,billto,Status,Type,active,orgId")
		.from(newIq)	
		
		.addFilterExpressionWithKey(ORGID_FILTER,"t.orgId=:orgId")		
		.addFilterExpressionWithKey(NUMBER_FILTER, "t.customerNumber Like :customerNumber")
		.addFilterExpressionWithKey(CITY_FILTER, "t.city Like :cCity")
		.addFilterExpressionWithKey(ADDRESS_FILTER, "t.addressOne Like :addressOne")
		.addFilterExpressionWithKey(STATE_FILTER, "t.state Like :cState")
		.addFilterExpressionWithKey(POSTAL_FILTER, "t.postal Like :cPostal")
		.addFilterExpressionWithKey(BILLTO_FILTER, "t.billto Like :cBill")
		.addFilterExpressionWithKey(Name_FILTER, "t.customerName Like :customerName")
		.searchTheseColumns("t.customerName", "t.customerNumber","t.city","t.state","t.postal","t.country","t.addressOne","t.Type")
		.build();
	return queryTemplate;
    }
    
    @Override
    protected Map<String, String> mapDataTableColNameToDBCol() {
	Map<String, String> map = new HashMap<>();
	map.put("customerName", "customerName");
	map.put("customerNumber", "customerNumber");
	map.put("country", "country");
	map.put("addressOne", "addressOne");
	map.put("city", "city");
	map.put("state", "state");
	map.put("postal", "postal");
	map.put("Type", "Type");
	map.put("customerId", "customerId");
	
	return map;
    }
	
    public static class CustomerUnAssignputDtInput extends DataTablesInput{
	
	
	private String customerName;
	private String customerNumber;
	private String addressOne;
	private String cCity;
	private String cState;
	private String cStatus;
	private String cPostal;
	private String cBill;
	private String userId;
	private String orgId;
	
	


	/**
	 * @return the cBill
	 */
	public String getcBill() {
	    return cBill;
	}


	/**
	 * @param cBill the cBill to set
	 */
	public void setcBill(String cBill) {
	    this.cBill = cBill;
	}


	


	/**
	 * @return the orgId
	 */
	public String getOrgId() {
	    return orgId;
	}


	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(String orgId) {
	    this.orgId = orgId;
	}


	/**
	 * @return the customerNumber
	 */
	public String getCustomerNumber() {
	    return customerNumber;
	}


	/**
	 * @param customerNumber the customerNumber to set
	 */
	public void setCustomerNumber(String customerNumber) {
	    this.customerNumber = '%'+customerNumber+'%';
	}


	@Override
	public QueryFilterAndParams toFilterParams() {

		QueryFilterAndParams params = new QueryFilterAndParamsImpl();
		
		
		if (orgId != null) {
			params.addFilterParamValues(ORGID_FILTER, "orgId", orgId);
		}
		
		
		if (customerName != null) {
			params.addFilterParamValues(Name_FILTER, "customerName", customerName);
		}
		if (customerNumber != null) {
			params.addFilterParamValues(NUMBER_FILTER, "customerNumber", customerNumber);
		}
		if (cCity != null) {
			params.addFilterParamValues(CITY_FILTER, "cCity", cCity);
		}
		if (addressOne != null) {
			params.addFilterParamValues(ADDRESS_FILTER, "addressOne", addressOne);
		}
		if (cState != null) {
			params.addFilterParamValues(STATE_FILTER, "cState", cState);
		}
		if (cPostal != null) {
			params.addFilterParamValues(POSTAL_FILTER, "cPostal", cPostal);
		}
		if (cBill != null) {
			params.addFilterParamValues(BILLTO_FILTER, "cBill", cBill);
		}
		params.addNonFilterKeyParameters("userId",userId);
		
		String srchVal = getSearch().getValue();
	            if (srchVal != null && !srchVal.trim().isEmpty()) {
	                params.addGlobalSearchValue(srchVal);
	            }
		
		return params;
	
	}


	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
	    return customerName;
	}


	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
	    this.customerName = '%'+customerName+'%';
	}	

	/**
	 * @return the cAddress
	 */
	public String getAddressOne() {
	    return addressOne;
	}


	/**
	 * @param cAddress the cAddress to set
	 */
	public void setAddressOne(String addressOne) {
	    this.addressOne = '%'+addressOne+'%';
	}


	/**
	 * @return the cCity
	 */
	public String getcCity() {
	    return cCity;
	}


	/**
	 * @param cCity the cCity to set
	 */
	public void setcCity(String cCity) {
	    this.cCity = '%'+cCity+'%';
	}


	/**
	 * @return the cState
	 */
	public String getcState() {
	    return cState;
	}


	/**
	 * @param cState the cState to set
	 */
	public void setcState(String cState) {
	    this.cState = '%'+cState+'%';
	}


	/**
	 * @return the cStatus
	 */
	public String getcStatus() {
	    return cStatus;
	}


	/**
	 * @param cStatus the cStatus to set
	 */
	public void setcStatus(String cStatus) {
	    this.cStatus = '%'+cStatus+'%';
	}


	/**
	 * @return the cPostal
	 */
	public String getcPostal() {
	    return cPostal;
	}

	/**
	 * @param cPostal the cPostal to set
	 */
	public void setcPostal(String cPostal) {
	    this.cPostal = '%'+cPostal+'%';
	}


	public  String getUserId() {
	    return userId;
	}


	public void setUserId(String userId) {
	    this.userId = userId;
	}	
	
    }

    public void setUserId(String userId) {
	this.userId=userId;
	
    }

}
