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
public class MachineUnAssignmentReport extends AbstractDataTableReport{

	 public static final String USER_FILTER = "user_id";
	 public static final String ORGANIZATION_FILTER = "organization_id";
	 public static final String CATALOG_FILTER = "catalog_id";
	 public static final String MODEL_FILTER = "model";
	 public static final String CATALOG_REF_FILTER = "catlog_reference";
	 public static final String CUSTOMER_FILTER = "customer_name";
	 public static final String GROUP_FILTER = "group_name";
	 public static final String CATFILTER = "catalog_id";
	 public static final String CATALOG_ID = "catalog_id_in";
	 
	/* public static final String QUERY1="(select  Cat.CATALOG_ID as catalogId from USERS Usr"
						+" inner join USER_ORG_MAP UsrOrg on Usr.USER_ID = UsrOrg.USER_ID AND  UsrOrg.ACTIVE = 1 "
						+" inner join ORGANIZATION Org on Org.ORGANIZATION_ID = UsrOrg.ORGANIZATION_ID "
						+" inner join CUSTOMER_ORGANIZATION_MAP CustOrg on UsrOrg.ORGANIZATION_ID =CustOrg.ORGANIZATION_ID AND CustOrg.ACTIVE = 1 "
						+" inner join CUSTOMER Cust on Cust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND Cust.ACTIVE = 1 "
						+" inner join USER_CUSTOMER UsrCust on CustOrg.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND UsrCust.ACTIVE = 1 AND Usr.USER_ID = UsrCust.USER_ID "
						+" LEFT join CATALOG Cat on UsrCust.CUSTOMER_ID = Cat.CUSTOMER_ID AND Cat.ORGANIZATION_ID = CustOrg.ORGANIZATION_ID AND Cat.ACTIVE = 1 "
						+" INNER JOIN USER_CATALOG UsrCat on Usr.USER_ID = UsrCat.USER_ID AND Cat.CATALOG_ID=UsrCat.CATALOG_ID and UsrCat.ACTIVE=1 WHERE Usr.USER_ID =:user_id)";	*/
	 
	
	@Override
	protected QueryTemplate attachQueryTemplate() {
		MSSql2008QueryTemplateBuilder qtb = new MSSql2008QueryTemplateBuilder();
		 String UNASSSIGNEDQUERY1="(select cat.CATALOG_ID AS catalog_id,cat.CATALOG_NAME AS catalog_name,cat.MODEL as model,cat.CATALOG_REFERENCE as catalog_reference,"
		 		+ "cust.CUSTOMER_NAME AS customer_name,'' AS group_name, cat.ORGANIZATION_ID AS organization_id,Usr.USER_ID AS user_id "
		 		+ "From USERS Usr INNER JOIN USER_CUSTOMER UsrCust ON Usr.USER_ID = UsrCust.USER_ID AND UsrCust.ACTIVE = 1 "
		 		+ "INNER JOIN CUSTOMER Cust ON Cust.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND Cust.ACTIVE = 1 "
		 		+ "INNER JOIN CUSTOMER_ORGANIZATION_MAP CustOrg ON UsrCust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND CustOrg.ACTIVE = 1 "
		 		+ "INNER JOIN CATALOG cat on cat.CUSTOMER_ID=UsrCust.CUSTOMER_ID and cat.ACTIVE=1 and cat.ORGANIZATION_ID=:organization_id "
		 		+ "WHERE Usr.USER_ID =:user_id and CustOrg.ORGANIZATION_ID=:organization_id AND cat.CATALOG_ID not in ( "
		 		+ "select distinct cat.CATALOG_ID From USERS Usr "
		 		+ "INNER JOIN USER_CUSTOMER UsrCust ON Usr.USER_ID = UsrCust.USER_ID AND UsrCust.ACTIVE = 1 "
		 		+ "INNER JOIN CUSTOMER Cust ON Cust.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND Cust.ACTIVE = 1 "
		 		+ "INNER JOIN CUSTOMER_ORGANIZATION_MAP CustOrg ON UsrCust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND CustOrg.ACTIVE = 1 "
		 		+ "INNER JOIN CATALOG cat on cat.CUSTOMER_ID=UsrCust.CUSTOMER_ID and cat.ACTIVE=1 and cat.ORGANIZATION_ID=:organization_id "
		 		+ "INNER JOIN USER_CATALOG userCat on cat.CATALOG_ID=userCat.CATALOG_ID and userCat.ACTIVE=1 and UserCat.USER_ID=Usr.USER_ID "
		 		+ "WHERE Usr.USER_ID =:user_id and CustOrg.ORGANIZATION_ID =:organization_id )) t";
		
		QueryTemplate queryTemplate = qtb.fetchColumns("catalog_id,catalog_name,model,catalog_reference,customer_name,group_name,organization_id,user_id")
				.from(UNASSSIGNEDQUERY1)
				.addFilterExpressionWithKey(ORGANIZATION_FILTER, "organization_id =:organization_id")
				.addFilterExpressionWithKey(USER_FILTER,"user_id =:user_id")
				.addFilterExpressionWithKey(CATALOG_FILTER, "catalog_name LIKE :catalog_id")
				.addFilterExpressionWithKey(MODEL_FILTER, "model LIKE :model")
				.addFilterExpressionWithKey(CATALOG_REF_FILTER, "catalog_reference LIKE :catalog_reference")
				.addFilterExpressionWithKey(CUSTOMER_FILTER, "customer_name LIKE :customer_name")
				.addFilterExpressionWithKey(GROUP_FILTER, "group_name LIKE :group_name")
				.searchTheseColumns("catalog_name", "model","catalog_reference","customer_name","group_name")
				.build();				
		return queryTemplate;
	}

	@Override
	protected Map<String, String> mapDataTableColNameToDBCol() {
		Map<String, String> map = new HashMap<>();
		map.put("catalog_id", "catalog_id");
		map.put("model", "model");
		map.put("catalog_reference", "catalog_reference");
		map.put("customer_name", "customer_name");
		//map.put("status","status");
		map.put("group_name", "group_name");
		 map.put("catalog_name", "catalog_name");
		return map;
	}
	
	  public static class MachineAssignmentDtInput extends DataTablesInput{
		  	private Long user_id;
		  	
			private Integer organization_id;
		  	private String catalog_id;
			private String model;
			private String catalog_reference;
			private String customer_name;
			private String group_name;
			//private String status;
			
			public Long getUser_id() {
				return user_id;
			}
			public void setUser_id(Long user_id) {
				this.user_id = user_id;
			}
			public Integer getOrganization_id() {
				return organization_id;
			}
			public void setOrganization_id(Integer organization_id) {
				this.organization_id = organization_id;
			}
			public String getCatalog_id() {
				return catalog_id;
			}
			public void setCatalog_id(String catalog_id) {
				this.catalog_id = '%'+catalog_id+'%';
			}
			public String getModel() {
				return model;
			}
			public void setModel(String model) {
				this.model = '%'+model+'%';
			}
			public String getCatalog_reference() {
				return catalog_reference;
			}
			public void setCatalog_reference(String catalog_reference) {
				this.catalog_reference = '%'+catalog_reference+'%';
			}
			public String getCustomer_name() {
				return customer_name;
			}
			public void setCustomer_name(String customer_name) {
				this.customer_name = '%'+customer_name+'%';
			}
			public String getGroup_name() {
				return group_name;
			}
			public void setGroup_name(String group_name) {
				this.group_name = '%' +group_name+'%';
			}
			
			@Override
			public QueryFilterAndParams toFilterParams() {
				QueryFilterAndParams params = new QueryFilterAndParamsImpl();
				if (user_id != null) {
					params.addFilterParamValues( USER_FILTER, "user_id", user_id);
				}
				if (organization_id != null) {
					params.addFilterParamValues(ORGANIZATION_FILTER, "organization_id", organization_id);
				}
				if (catalog_id != null) {
					params.addFilterParamValues(CATALOG_FILTER, "catalog_id", catalog_id);
				}
				if (model != null) {
					params.addFilterParamValues(MODEL_FILTER, "model", model);
				}
				if (catalog_reference != null) {
					params.addFilterParamValues(CATALOG_REF_FILTER, "catalog_reference", catalog_reference);
				}
				if (customer_name != null) {
					params.addFilterParamValues(CUSTOMER_FILTER, "customer_name", customer_name);
				}
				if (group_name != null) {
					params.addFilterParamValues(GROUP_FILTER, "group_name", group_name);
				}
				String srchVal = getSearch().getValue();
	            if (srchVal != null && !srchVal.trim().isEmpty()) {
	                params.addGlobalSearchValue(srchVal);
	            }
				return params;
				
			}
			/*public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}*/
	  }
}
