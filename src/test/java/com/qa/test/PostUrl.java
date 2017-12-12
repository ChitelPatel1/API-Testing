package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.TestBase;
import com.qa.data.User;
import com.qa.httpclient.RestClient;

import junit.framework.Assert;

public class PostUrl extends TestBase{
	
	TestBase testBase;
	String url ;
	CloseableHttpResponse closeableHttpRepsopnse ;
	RestClient restClient;
	
	@BeforeClass
	public void setUP() {
		testBase = new TestBase();
		String endpointURL = prop.getProperty("URL");
		String serviceURL = prop.getProperty("ServiceURL");
		
		url = endpointURL+serviceURL;
		System.out.println("URL Testing---->:"+ url);
		

	}
	@Test
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException, JSONException{
		
		restClient = new RestClient();
		HashMap <String, String> headers = new HashMap<String,String>();
		headers.put("content-type", "application/json");
		
		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User("morpheus","leader");//expacted
		
		objectMapper.writeValue(new File("//Users//chrislenk//Documents//MyProject//restapi//src//main//java//com//qa//data//User.jason"), user);
	
		String userString = objectMapper.writeValueAsString(user);
		System.out.println(userString);
		
		closeableHttpRepsopnse=restClient.post(url, userString, headers);
		
		//staus code
		
		int status = closeableHttpRepsopnse.getStatusLine().getStatusCode();
		Assert.assertEquals(status, testBase.RESPONSE_CODE_201);
		
		//jason String
		
        String responseString = EntityUtils.toString(closeableHttpRepsopnse.getEntity(), "UTF-8");
		
		//crating jason object 
		JSONObject jasonResponse = new JSONObject(responseString);
		//getting value from single 
		System.out.println("Jason Rspone from API -----> "+ jasonResponse);
		User userObject = objectMapper.readValue(responseString, User.class);//actual
		System.out.println(userObject);
		
		Assert.assertTrue(user.getName().equals(userObject.getName()));
		Assert.assertTrue(user.getJob().equals(user.getJob()));
		System.out.println(userObject.getId());
		System.out.println(userObject.getCreatedAt());
		
		
		
	}

}
