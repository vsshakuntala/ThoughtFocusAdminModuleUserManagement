package com.tf.usermanagement.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtil {
	
	public static java.sql.Timestamp getSqlTimeStamp(){

		Calendar calendar = Calendar.getInstance();
		java.util.Date now = calendar.getTime();
		java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());
		 return currentTimestamp;
	}
	
	public static java.sql.Date parseStringToSqlDate(String date) throws ParseException{
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date parsed = format.parse(date);
		java.sql.Date sql = new java.sql.Date(parsed.getTime());
		return sql;
	}

}
