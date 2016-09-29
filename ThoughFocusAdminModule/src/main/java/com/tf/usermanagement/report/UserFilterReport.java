package com.tf.usermanagement.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spaneos.dtssp.AbstractDataTableReport;
import com.spaneos.dtssp.MSSql2008QueryTemplateBuilder;
import com.spaneos.dtssp.QueryFilterAndParams;
import com.spaneos.dtssp.QueryFilterAndParamsImpl;
import com.spaneos.dtssp.QueryTemplate;
import com.spaneos.dtssp.input.DataTablesInput;;

/**
 * 
 * @author Rajendra
 *
 */
@Component
public class UserFilterReport extends AbstractDataTableReport {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserFilterReport.class.getName());

	public static final String DIVISIONLIST_FILTER = "divisions";
	public static final String ROLEIDLIST_FILTER = "roles";
	public static final String STATUSIDLIST_FILTER = "status";
	private static final String FROM_DATE_FILTER = "from_date";
	private static final String TO_DATE_FILTER = "to_date";
	private static final String COMPANY_NAME_FILTER = "companyName";
	private static final String NAME_FILTER = "name";
	private static final String ACTIVE_FILTER = "active";
	//private static final String USR_ORG_ACTIVE_FILTER = "userOrgActive";
	//private static final String USR_ROLE_ACTIVE_FILTER = "userRoleActive";

	@Override
	protected QueryTemplate attachQueryTemplate() {
		MSSql2008QueryTemplateBuilder qtb = new MSSql2008QueryTemplateBuilder();
		QueryTemplate qt = qtb
				.fetchColumns("usr.USER_ID as userId", "usr.LAST_LOGIN_DATE as last_date", "usr.USER_NAME as userName",
						"usr.FIRST_NAME as firstName", "usr.LAST_NAME as lastName", " usr.EMAIL as email",
						"usr.ACTIVE as active", " usr.CREATED_DATE as createdDate", " usr.COUNTRY_FLAG as countryFlag",
						"usr.PHONE_NUMBER as phoneNumber", "usr.COMPANY_NAME as companyName",
						"(select count(APPROVAL_STATUS) from USER_ORG_MAP where APPROVAL_STATUS=1 and USER_ID=Usr.USER_ID) as Approved",
						"(select count(APPROVAL_STATUS) from USER_ORG_MAP where APPROVAL_STATUS=0 and ACTIVE =1 and USER_ID=Usr.USER_ID) as Pending")
				.from("USERS usr").joinOn("USER_ORG_MAP usrOrg", "usr.USER_ID=usrOrg.USER_ID")

				.addFilterExpressionWithKey(ACTIVE_FILTER, "usr.ACTIVE in (:active)")
				//.addFilterExpressionWithKey(USR_ORG_ACTIVE_FILTER, "usrOrg.ACTIVE = :userOrgActive")
				.addFilterExpressionWithKey(DIVISIONLIST_FILTER, "usrOrg.ORGANIZATION_ID in (:divisions)")
				.addFilterExpressionWithKey(ROLEIDLIST_FILTER,
						"Usr.USER_ID in (select USER_ID from USER_ROLE usrRl where usrRl.ROLE_ID in (:roles) and usrRl.ACTIVE = 1)")
				.addFilterExpressionWithKey(STATUSIDLIST_FILTER, "usrOrg.APPROVAL_STATUS in (:status)")
				.addFilterExpressionWithKey(FROM_DATE_FILTER, "usr.CREATED_DATE >= :from_date")
				.addFilterExpressionWithKey(TO_DATE_FILTER, "usr.CREATED_DATE <= :to_date")
				.addFilterExpressionWithKey(COMPANY_NAME_FILTER, "usr.COMPANY_NAME like :companyName")
				.addFilterExpressionWithKey(NAME_FILTER, "usr.FIRST_NAME like :name")
				.groupBy("usr.USER_ID", "usr.USER_NAME", "usr.FIRST_NAME", "usr.LAST_NAME", "usr.EMAIL",
						"usr.CREATED_DATE", "usr.COUNTRY_FLAG", "usr.PHONE_NUMBER", "usr.COMPANY_NAME", "usr.ACTIVE",
						"usr.LAST_LOGIN_DATE")
				.searchTheseColumns("usr.COMPANY_NAME", "usr.PHONE_NUMBER","usr.FIRST_NAME","usr.LAST_NAME","usr.EMAIL","usr.USER_NAME")
				.build();

		return qt;
	}

	@Override
	protected Map<String, String> mapDataTableColNameToDBCol() {
		Map<String, String> map = new HashMap<>();
		map.put("userId", "userId");
		map.put("last_date", "last_date");
		map.put("userName", "userName");
		map.put("firstName", "firstName");
		map.put("lastName", "lastName");
		map.put("email", "email");
		map.put("active", "active");
		map.put("createdDate", "createdDate");
		map.put("countryFlag", "countryFlag");
		map.put("phoneNumber", "phoneNumber");
		map.put("companyName", "companyName");
		map.put("Approved", "Approved");
		map.put("Pending", "Pending");
		return map;
	}

	public static class UserFilterReportDtInput extends DataTablesInput {

		private String userOrgActive = "1";
		private String userRoleActive = "1";
		private String active;
		private String divisions;
		private String roles;
		private String status;
		private Date from_date = null;
		private Date to_date = null;
		private String companyName = null;
		private String name = null;

		public List<String> getActive() {
			if (active != null)
				return Arrays.asList(active.split(","));
			return null;
		}

		public void setActive(String active) {
			this.active = active;
		}

		public List<String> getDivisions() {
			if (divisions != null)
				return Arrays.asList(divisions.split(","));
			return null;
		}

		public void setDivisions(String divisions) {
			this.divisions = divisions;
		}

		public List<String> getRoles() {
			if (roles != null)
				return Arrays.asList(roles.split(","));
			return null;
		}

		public void setRoles(String roles) {
			this.roles = roles;
		}

		public List<String> getStatus() {
			if (status != null) {
				LOGGER.info("Status input :{}", status);
				List<String> statusList = Arrays.asList(status.split(","));
				List<String> list = new ArrayList<>();
				if (status.equalsIgnoreCase("Pending,Approved,Deleted")) {
					active = "true,false";
					list.add("true");
					list.add("false");
				} else if (status.equalsIgnoreCase("Pending,Deleted") || status.equalsIgnoreCase("Deleted,Pending")) {
					active = "true,false";
					list.add("false");
				} else if (status.equalsIgnoreCase("Approved,Deleted") || status.equalsIgnoreCase("Deleted,Approved")) {
					active = "true,false";
					list.add("true");
				} else if (status.equalsIgnoreCase("Pending,Approved") || status.equalsIgnoreCase("Approved,Pending")) {
					active = "true";
					list.add("true");
					list.add("false");
				} else if (status.equalsIgnoreCase("Approved")) {
					list.add("true");
					active = "true";
				} else if (status.equalsIgnoreCase("Pending")) {
					list.add("false");
					active = "true";
				} else if (status.equalsIgnoreCase("Deleted")) {
					active = "false";
					list.add("true");
					list.add("false");
				}

				return list;
			}
			return null;

		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Date getFrom_date() {
			return from_date;
		}

		public void setFrom_date(Date from_date) {
			this.from_date = from_date;
		}

		public Date getTo_date() {
			return to_date;
		}

		public void setTo_date(Date to_date) {
			this.to_date = to_date;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public QueryFilterAndParams toFilterParams() {
			QueryFilterAndParams params = new QueryFilterAndParamsImpl();

			if (!divisions.isEmpty()) {
				params.addFilterParamValues(DIVISIONLIST_FILTER, "divisions", getDivisions());
			}
			if (!roles.isEmpty()) {
				params.addFilterParamValues(ROLEIDLIST_FILTER, "roles", getRoles());
			}
			if (!status.isEmpty()) {
				params.addFilterParamValues(STATUSIDLIST_FILTER, "status", getStatus());
			}
			params.addFilterParamValues(ACTIVE_FILTER, "active", getActive());
			//params.addFilterParamValues(USR_ORG_ACTIVE_FILTER, "userOrgActive", userOrgActive);
			// params.addFilterParamValues(USR_ROLE_ACTIVE_FILTER,
			// "userRoleActive", userRoleActive);

			if (from_date != null) {
				params.addFilterParamValues(FROM_DATE_FILTER, "from_date", getFrom_date());
			}
			if (to_date != null) {
				params.addFilterParamValues(TO_DATE_FILTER, "to_date", getTo_date());
			}
			if (from_date!=null && to_date!=null && from_date.compareTo(to_date) == 0) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(to_date);
				calendar.add(Calendar.DATE, 1);
				params.addFilterParamValues(TO_DATE_FILTER, "to_date", calendar.getTime());

			}
			if (companyName != null) {
				params.addFilterParamValues(COMPANY_NAME_FILTER, "companyName", "%" + getCompanyName() + "%");
			}
			if (name != null) {
				params.addFilterParamValues(NAME_FILTER, "name", "%" + getName() + "%");
			}

			String srchVal = getSearch().getValue();
			if (srchVal != null && !srchVal.trim().isEmpty()) {
				params.addGlobalSearchValue(srchVal);
			}
			return params;
		}
	}
}
