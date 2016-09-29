package com.tf.usermanagement.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.http.HttpEntity;

/**
 * Class to send json to server
 * 
 * @author Utpal Kant
 *
 */
public class PostToServer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PostToServer.class);

    /**
     * Performs HTTP post.
     * 
     * @return String[0] -- Status Code from server,
     *         String[1] -- Data returned from server
     */
    public static synchronized String[] post(String url, String jsonToBeSent) {
	//String[0] -- Status Code from server, string[1] -- Data returned from server
	String[] serverResponse=new String[2];
	
	RequestConfig config=RequestConfig.copy(RequestConfig.DEFAULT)
            .setSocketTimeout(10*1000)
            .setConnectTimeout(10*1000)
            .setConnectionRequestTimeout(10*1000)
            .build();

	HttpClientBuilder builder = HttpClientBuilder.create();     
	builder.setDefaultRequestConfig(config);
	//HttpClient client = builder.build();
	
	HttpClient httpClient = builder.build(); // Use this
								    // instead
	HttpResponse response=null;
	LOGGER.info("Calling Big Data API -- "+ url + ", Posting to server -- ");
	
	try {
	    HttpPost httpPost = new HttpPost(url);
	    StringEntity stringEntity = new StringEntity(jsonToBeSent);
	    httpPost.setEntity(stringEntity);
	    httpPost.setHeader("Content-type", "application/json");

	    response = httpClient.execute(httpPost);

	    HttpEntity entity = response.getEntity();
	    String responseString = EntityUtils.toString(entity, "UTF-8");
	    
	    serverResponse[0]=String.valueOf(response.getStatusLine().getStatusCode());
	    serverResponse[1]=responseString;
	    
	    LOGGER.info("Status Code from Big Data -- "+response.getStatusLine().getStatusCode());
	    LOGGER.info("Response received form Big Data -- "+responseString);
	} catch (IOException e) {
		LOGGER.error("Could not post to server ", e);
	}
	return serverResponse;
    }

}
