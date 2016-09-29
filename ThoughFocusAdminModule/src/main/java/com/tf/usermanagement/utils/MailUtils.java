package com.tf.usermanagement.utils;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.core.env.Environment;

public class MailUtils {
	private static final Logger LOGGER = Logger.getLogger(MailUtils.class);

	public synchronized void sendMail(String subject, String body, final String sender, final String senderPassword,
			String recipients, Environment environment, String cc) throws Exception {

		Properties props = new Properties();
		props.put("mail.smtp.auth", 
				StringUtils.isBlank
				(environment.getProperty("approve.mail.smtp.auth")) ? false : Boolean
						.parseBoolean(environment.getProperty("approve.mail.smtp.auth")));
		
		props.put("mail.smtp.starttls.enable", 
				StringUtils.isBlank
				(environment.getProperty("approve.email.mailStarttlsEnabled")) ? false : Boolean
						.parseBoolean(environment.getProperty("approve.email.mailStarttlsEnabled")));
		
		props.put("mail.smtp.host", environment.getProperty("approve.email.host"));
		props.put("mail.smtp.port", environment.getProperty("approve.email.port"));
		
		Boolean mailStarttlsEnabled = StringUtils
				.isBlank(environment
						.getProperty("approve.email.mailStarttlsEnabled")) ? 
								false : Boolean .parseBoolean
						(environment.getProperty("approve.email.mailStarttlsEnabled"));
		
		Boolean authIsEnabled = StringUtils
				.isBlank(environment.getProperty("approve.mail.smtp.auth")) ? false : Boolean
						.parseBoolean(environment.getProperty("approve.mail.smtp.auth"));
		
		if(mailStarttlsEnabled && authIsEnabled){
			
		} else{
			LOGGER.debug("Setting local session");
			
		}
		
		try {
			LOGGER.debug("Sending email");
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
	}

}
