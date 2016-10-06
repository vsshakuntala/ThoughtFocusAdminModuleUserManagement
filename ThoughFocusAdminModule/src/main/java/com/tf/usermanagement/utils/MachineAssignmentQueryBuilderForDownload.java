package com.tf.usermanagement.utils;

import org.springframework.stereotype.Component;

import com.tf.usermanagement.exceptions.InsufficientDataException;

@Component
public class MachineAssignmentQueryBuilderForDownload {

	private static final String UNASSIGNEDQUERYONE = "select  catalog_id,catalog_name,model,catalog_reference,customer_name,group_name,organization_id,user_id,active from "
			+ "(select cat.CATALOG_ID AS catalog_id,cat.CATALOG_NAMED AS catalog_name,cat.MODEL as model,cat.CATALOG_REFERENCE as catalog_reference,cust.CUSTOMER_NAME AS customer_name,'' AS group_name, cat.ORGANIZATION_ID AS organization_id,Usr.USER_ID AS user_id,cat.ACTIVE as active "
			+ "From USERS Usr "
			+ "INNER JOIN USER_CUSTOMER UsrCust ON Usr.USER_ID = UsrCust.USER_ID AND UsrCust.ACTIVE = 1 "
			+ "INNER JOIN CUSTOMER Cust ON Cust.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND Cust.ACTIVE = 1 "
			+ "INNER JOIN CUSTOMER_ORGANIZATION_MAP CustOrg ON UsrCust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND CustOrg.ACTIVE = 1 "
			+ "INNER JOIN CATALOG cat on cat.CUSTOMER_ID=UsrCust.CUSTOMER_ID and cat.ACTIVE=1 and cat.ORGANIZATION_ID= ";

	private static final String UNASSIGNEDQUERYTWO = " WHERE Usr.USER_ID = ";
	private static final String UNASSIGNEDQUERYTHREE = " and CustOrg.ORGANIZATION_ID= ";
	private static final String UNASSIGNEDQUERYFOUR = " AND cat.CATALOG_ID not in (select distinct cat.CATALOG_ID "
			+ "From USERS Usr "
			+ "INNER JOIN USER_CUSTOMER UsrCust ON Usr.USER_ID = UsrCust.USER_ID AND UsrCust.ACTIVE = 1 "
			+ "INNER JOIN CUSTOMER Cust ON Cust.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND Cust.ACTIVE = 1 "
			+ "INNER JOIN CUSTOMER_ORGANIZATION_MAP CustOrg ON UsrCust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND CustOrg.ACTIVE = 1 "
			+ "INNER JOIN CATALOG cat on cat.CUSTOMER_ID=UsrCust.CUSTOMER_ID and cat.ACTIVE=1 and cat.ORGANIZATION_ID= ";
	private static final String UNASSIGNEDQUERYFIVE = " INNER JOIN USER_CATALOG userCat on cat.CATALOG_ID=userCat.CATALOG_ID and userCat.ACTIVE=1 and UserCat.USER_ID=Usr.USER_ID WHERE Usr.USER_ID = ";
	private static final String UNASSIGNEDQUERYSIX = " and CustOrg.ORGANIZATION_ID= ";

	private static final String ASSIGNEDQUERYONE = " select catalog_id,catalog_name,model,catalog_reference,customer_name,group_name,user_id,organization_id,active from "
			+ "((select distinct cat.CATALOG_ID AS catalog_id,cat.CATALOG_NAME AS catalog_name,cat.MODEL AS model,cat.CATALOG_REFERENCE AS catalog_reference,cust.CUSTOMER_NAME AS customer_name,"
			+ "'' AS group_name,Usr.USER_ID AS user_id,CustOrg.ORGANIZATION_ID AS organization_id,cat.active as active "
			+ "From USERS Usr INNER JOIN USER_CUSTOMER UsrCust ON Usr.USER_ID = UsrCust.USER_ID AND UsrCust.ACTIVE = 1 "
			+ "INNER JOIN CUSTOMER Cust ON Cust.CUSTOMER_ID = UsrCust.CUSTOMER_ID AND Cust.ACTIVE = 1 "
			+ "INNER JOIN CUSTOMER_ORGANIZATION_MAP CustOrg ON UsrCust.CUSTOMER_ID = CustOrg.CUSTOMER_ID AND CustOrg.ACTIVE = 1 "
			+ "INNER JOIN CATALOG cat on cat.CUSTOMER_ID=UsrCust.CUSTOMER_ID and cat.ACTIVE=1 and cat.ORGANIZATION_ID= ";
	private static final String ASSIGNEDQUERYTWO = " INNER JOIN USER_CATALOG userCat on cat.CATALOG_ID=userCat.CATALOG_ID and userCat.ACTIVE=1 and UserCat.USER_ID=Usr.USER_ID "
			+ "WHERE Usr.USER_ID = ";
	private static final String ASSIGNEDQUERYTHREE = " and CustOrg.ORGANIZATION_ID= ";
	private static final String ASSIGNEDQUERYFOUR = " ) UNION ALL( "
			+ "select distinct cat.CATALOG_ID AS catalog_id,cat.CATALOG_NAME AS catalog_name,cat.MODEL AS model,cat.CATALOG_REFERENCE AS catalog_reference,'' AS customer_name,"
			+ "grp.GROUP_NAME AS group_name,usr.user_id AS user_id,grp.organization_id AS organization_id,cat.active as active from users usr "
			+ "INNER join user_group ug on usr.user_id= ug.user_id and ug.active=1 "
			+ "inner join groups grp on grp.group_id=ug.group_id and grp.active=1 "
			+ "inner join group_catalog grpcat on grp.group_id=grpcat.group_id and grpcat.active=1 "
			+ "inner join catalog cat on cat.catalog_id=grpcat.catalog_id and cat.active=1  and cat.ORGANIZATION_ID= ";
	private static final String ASSIGNEDQUERYFIVE=" where usr.user_id= ";

	private static final String ASSIGNEDQUERYSIX = " and grp.organization_id= ";

	public String getAssignedMachineQueryForUser(Long userId, Long orgId, Long catId, String model,
			String catalog_reference, String customer_name, String status, String group_name) {

		StringBuilder builder = new StringBuilder();

		if (userId == null || userId < 1) {

			throw new InsufficientDataException(" User id is mandatory ");
		}

		if (orgId == null || orgId < 1) {
			throw new InsufficientDataException("orgId is mandatory ");
		}

		builder.append(ASSIGNEDQUERYONE).append(orgId).append(ASSIGNEDQUERYTWO).append(userId)
				.append(ASSIGNEDQUERYTHREE).append(orgId).append(ASSIGNEDQUERYFOUR).append(orgId)
				.append(ASSIGNEDQUERYFIVE).append(userId).append(ASSIGNEDQUERYSIX).append(orgId).append(" )) t where t.active=1 ");

		if (catId != null && catId > 0) {

			builder.append(" AND t.catalog_name LIKE '").append(catId).append("'");

		}

		if (model != null && model.trim().length() > 0) {
			builder.append(" AND t.model LIKE '").append(getBuilderString(model)).append("'");
		}
		if (catalog_reference != null && catalog_reference.trim().length() > 0) {
			builder.append(" AND t.catalog_reference LIKE '").append(getBuilderString(catalog_reference)).append("'");
		}
		if (customer_name != null && customer_name.trim().length() > 0) {
			builder.append(" AND t.customer_name LIKE '").append(getBuilderString(customer_name)).append("'");
		}
		if (group_name != null && group_name.trim().length() > 0) {
			builder.append(" AND t.group_name LIKE '").append(getBuilderString(group_name)).append("'");
		}
		return builder.toString();
	}

	public String getBuilderString(String str) {

		if (str.contains("*") && str.contains("?")) {
			str = str.replaceAll("\\*", "\\%");
			str = str.replaceAll("\\?", "\\_");
			return str;
		} else if (str.contains("*")) {
			str = str.replaceAll("\\*", "\\%");
			return str;
		} else if (str.contains("?")) {
			str = str.replaceAll("\\?", "\\_");
			return str;
		} else {
			return "%" + str + "%";
		}
	}

	public String getUnAssignedMachineQueryForUser(Long userId, Long orgId, Long catalog_id, String model,
			String catalog_reference, String customer_name, String status, String group_name) {

		StringBuilder builder = new StringBuilder();

		if (userId == null || userId < 1) {

			throw new InsufficientDataException("User id is mandatory ");
		}

		if (orgId == null || orgId < 1) {
			throw new InsufficientDataException("orgId is mandatory ");
		}

		builder.append(UNASSIGNEDQUERYONE).append(orgId).append(UNASSIGNEDQUERYTWO).append(userId).append(UNASSIGNEDQUERYTHREE).append(orgId).append(UNASSIGNEDQUERYFOUR).append(orgId).append(UNASSIGNEDQUERYFIVE).append(userId).append(UNASSIGNEDQUERYSIX).append(orgId).append(")) t where t.active=1 ");

		if (catalog_id != null && catalog_id > 0) {

			builder.append(" AND t.catalog_name LIKE '%").append(catalog_id).append("%'");

		}

		if (model != null && model.trim().length() > 0) {
			builder.append(" AND t.model LIKE '%").append(getBuilderString(model)).append("%'");
		}
		if (catalog_reference != null && catalog_reference.trim().length() > 0) {
			builder.append(" AND t.catalog_reference LIKE '%").append(getBuilderString(catalog_reference))
					.append("%'");
		}
		if (customer_name != null && customer_name.trim().length() > 0) {
			builder.append(" AND t.customer_name LIKE '%").append(getBuilderString(customer_name)).append("%'");
		}
		if (group_name != null && group_name.trim().length() > 0) {
			builder.append(" AND t.group_name LIKE '%").append(getBuilderString(group_name)).append("%'");
		}
		return builder.toString();
	}

}
