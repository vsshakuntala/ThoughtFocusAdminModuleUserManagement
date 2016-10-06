package com.tf.usermanagement.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author Rajendra
 *
 */
public class UserListQueryBuilderForDownload {

	private static String UserListQuery1 ="SELECT usr.USER_ID AS userId,usr.USER_NAME as userName,"
			+ "usr.FIRST_NAME as firstName,usr.LAST_NAME as lastName, usr.EMAIL as email,"
			+ "usr.CREATED_DATE as createdDate,usr.PHONE_NUMBER as phoneNumber,usr.COMPANY_NAME as companyName,usr.USER_ID,usr.ACTIVE,usr.COUNTRY_FLAG,usr.LAST_LOGIN_DATE,"
			+ "(select count(APPROVAL_STATUS) from USER_ORG_MAP where APPROVAL_STATUS=1 and USER_ID=Usr.USER_ID) as approved,"
			+ "(select count(APPROVAL_STATUS) from USER_ORG_MAP where APPROVAL_STATUS=0 and ACTIVE =1 and USER_ID=Usr.USER_ID) as pending "
			+ "FROM USERS usr JOIN USER_ORG_MAP usrOrg ON usr.USER_ID=usrOrg.USER_ID "
			+ "where";
	private static String Query2=" GROUP BY usr.ACTIVE,usr.COMPANY_NAME,usr.COUNTRY_FLAG,usr.CREATED_DATE,usr.EMAIL,usr.FIRST_NAME,usr.LAST_LOGIN_DATE,usr.LAST_NAME,usr.PHONE_NUMBER,usr.USER_ID,usr.USER_NAME ";
	
	
	
	public static String getUserListQuery(String divisions,String roles,String status,String from_date,String To_date,String company,String name) throws ParseException{
		StringBuilder builder = new StringBuilder();
		String active="true";		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		
		builder.append(UserListQuery1);
		if(from_date != null){
		    
		      builder.append(" usr.CREATED_DATE >= '").append(from_date).append("' ");
		   
		}
		if(To_date != null){
		    Date date = sdf.parse(To_date);		    	    
			if(from_date != null){
			    Date date1 = sdf.parse(from_date);
			    if(date1.compareTo(date) == 0){
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(date);
				calendar.add(Calendar.DATE, 1);
				String newDate =sdf.format(calendar.getTime()) ;
				builder.append(" and usr.CREATED_DATE <= '").append(newDate).append("' ");
			    }else{
				
				builder.append(" and usr.CREATED_DATE <= '").append(To_date).append("' ");
			    }
			    
			}
			else
				builder.append(" usr.CREATED_DATE <= '").append(To_date).append("' ");
		}
		
		
		if(roles != null && roles.trim().length()!=0){
			if(To_date != null || from_date != null)
		      builder.append(" and Usr.USER_ID in (select USER_ID from USER_ROLE usrRl where usrRl.ROLE_ID in ( ").append(roles).append(") and usrRl.ACTIVE = 1 )");
			else
				 builder.append(" Usr.USER_ID in (select USER_ID from USER_ROLE usrRl where usrRl.ROLE_ID in ( ").append(roles).append(") and usrRl.ACTIVE = 1 )");	
		   
		}
		if(divisions != null && divisions.trim().length()!=0 ){
			
			if((roles != null &&  roles.trim().length()!=0) || To_date != null || from_date != null){
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
			
			if((divisions != null && divisions.trim().length()!=0) || (roles != null &&  roles.trim().length()!=0) || To_date != null || from_date != null)
		      builder.append(" and usrOrg.APPROVAL_STATUS in (").append(list).append(") ");
			else
				 builder.append(" usrOrg.APPROVAL_STATUS in (").append(list).append(") ");
		   
		}
		if(active != null){
			if(status != null && status.trim().length()!=0){ 
		      builder.append(" and usr.ACTIVE in (").append(active).append(") ");
		      
			}
			else
				 builder.append(" usr.ACTIVE in (").append(active).append(")");
			      
		   
		}
		if(company != null){
			if(active != null || (divisions != null && divisions.trim().length()!=0) || (roles != null &&  roles.trim().length()!=0) || To_date != null || from_date != null)  
		      builder.append(" and usr.COMPANY_NAME like '%").append(company).append("%' ");
			else
				builder.append(" usr.COMPANY_NAME like '%").append(company).append("%' ");
		   
		}
		if(name != null){
			if(company != null || active != null || (divisions != null && divisions.trim().length()!=0) || (roles != null &&  roles.trim().length()!=0) || To_date != null || from_date != null) 
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
