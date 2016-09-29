package com.tf.usermanagement.utils;

import java.util.Date;

import org.neo4j.cypher.internal.compiler.v2_1.functions.Str;

/**
 * 
 * @author Rajendra
 *
 */
public class UserListQueryBuilderForDownload {

	private static String UserListQuery1 ="SELECT usr.USER_ID AS userId,usr.USER_NAME as userName,"
			+ "usr.FIRST_NAME as firstName,usr.LAST_NAME as lastName, usr.EMAIL as email,"
			+ "usr.CREATED_DATE as createdDate,usr.PHONE_NUMBER as phoneNumber,usr.COMPANY_NAME as companyName,"
			+ "(select count(APPROVAL_STATUS) from USER_ORG_MAP where APPROVAL_STATUS=1 and USER_ID=Usr.USER_ID) as approved,"
			+ "(select count(APPROVAL_STATUS) from USER_ORG_MAP where APPROVAL_STATUS=0 and USER_ID=Usr.USER_ID) as pending "
			+ "FROM USERS usr LEFT JOIN USER_ORG_MAP usrOrg ON usr.USER_ID=usrOrg.USER_ID "
			+ "where";
	private static String Query2=" GROUP BY usr.COMPANY_NAME,usr.CREATED_DATE,usr.EMAIL,"
			+ "usr.FIRST_NAME,usr.LAST_NAME,usr.PHONE_NUMBER,usr.USER_NAME,usr.USER_ID";
	
	
	
	public static String getUserListQuery(String divisions,String roles,String status,Date from_date,Date To_date,String company,String name){
		StringBuilder builder = new StringBuilder();
		String active="true";
		String userOrgActive="1";
		String userRoleActive="1";
		builder.append(UserListQuery1);
		if(from_date != null){
			   
		      builder.append(" usr.CREATED_DATE >= ").append(from_date);
		   
		}
		if(To_date != null){
			if(from_date != null)
		      builder.append(" and usr.CREATED_DATE >= ").append(To_date);
			else
				builder.append(" usr.CREATED_DATE >= ").append(To_date);
		}
		if(roles != null && roles.trim().length()!=0){
			if(To_date != null)
		      builder.append(" and Usr.USER_ID in (select USER_ID from USER_ROLE usrRl where usrRl.ROLE_ID in ( ").append(roles).append(") and usrRl.ACTIVE = 1 )");
			else
				 builder.append(" Usr.USER_ID in (select USER_ID from USER_ROLE usrRl where usrRl.ROLE_ID in ( ").append(roles).append(") and usrRl.ACTIVE = 1 )");	
		   
		}
		if(divisions != null && divisions.trim().length()!=0 ){
			
			if(roles != null &&  roles.trim().length()!=0){
		      builder.append(" and usrOrg.ORGANIZATION_ID in (").append(divisions).append(") ");
			}else{
				builder.append(" usrOrg.ORGANIZATION_ID in (").append(divisions).append(") "); 
			}
		   
		}
		if(status != null && status.trim().length()!=0){
		
			String list = null;
			if (status.equalsIgnoreCase("Pending,Approved,Deleted")) {
				active = "'true','false'";
				list="'true','false'";
				
			} else if (status.equalsIgnoreCase("Pending,Deleted") || status.equalsIgnoreCase("Deleted,Pending")) {
				active ="'true','false'";
				list="'false'";
			} else if (status.equalsIgnoreCase("Approved,Deleted") || status.equalsIgnoreCase("Deleted,Approved")) {
				active = "'true','false'";
				list="'true'";
			}
			else if (status.equalsIgnoreCase("Pending,Approved") || status.equalsIgnoreCase("Approved,Pending")) {
				active = "'true'";
				list="'true','false'";
			}
			else
				if(status.equalsIgnoreCase("Approved")){
					list="'true'";
					active = "'true'";
				}
			else if (status.equalsIgnoreCase("Pending")) {
					list="'false'";
					active = "'true'";
				}
			else
				if (status.equalsIgnoreCase("Deleted")) {
					active = "'false'";
					list="'true','false'";
				}
			
			if(divisions != null && divisions.trim().length()!=0 )
		      builder.append(" and usrOrg.APPROVAL_STATUS in (").append(list).append(") ");
			else
				 builder.append(" usrOrg.APPROVAL_STATUS in (").append(list).append(") ");
		   
		}
		if(active != null){
			if(status != null && status.trim().length()!=0){ 
		      builder.append(" and usr.ACTIVE in (").append(active).append(") and usrOrg.ACTIVE= ").append(userOrgActive);
		      
			}
			else
				 builder.append(" usr.ACTIVE in (").append(active).append(") and usrOrg.ACTIVE= ").append(userOrgActive);
			      
		   
		}
		if(company != null){
			if(active != null)  
		      builder.append(" and usr.COMPANY_NAME like '%").append(company).append("%' ");
			else
				builder.append(" usr.COMPANY_NAME like '%").append(company).append("%' ");
		   
		}
		if(name != null){
			if(company != null) 
		      builder.append(" and usr.FIRST_NAME like '%").append(name).append("%' ");
			else
				 builder.append(" usr.FIRST_NAME like '%").append(name).append("%' ");
		   
		}
		
			builder.append(Query2);
			
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
