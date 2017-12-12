package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.TestUtil.TestUtil;
import com.qa.httpclient.RestClient;

public class GetUrl extends TestBase{
	TestBase testBase;
	String url ;
	CloseableHttpResponse closeableHttpRepsopnse ;
	
	@BeforeClass
	public void setUP() {
		testBase = new TestBase();
		String endpointURL = prop.getProperty("URL");
		String serviceURL = prop.getProperty("ServiceURL");
		
		url = endpointURL+serviceURL;
		System.out.println("URL Testing---->:"+ url);
		

	}
	//check without header
	
	@Test
	public void getResponseCode_200() throws ClientProtocolException, IOException, JSONException{
		RestClient restClient = new RestClient();//working with rest client to create object of restclient
		closeableHttpRepsopnse =restClient.get(url);//restClient target endpoint url refrence to HttpResponse
		
		

		//getting status code
		int statuscode =closeableHttpRepsopnse.getStatusLine().getStatusCode();
		System.out.println("Status Code: ------> "+statuscode);
		
		
		Assert.assertEquals(statuscode, RESPONSE_CODE_200, "status code is not 200");
		
	}
	
	@Test(dependsOnMethods = {"getResponseCode_200"})
	public void getJasonResponse() throws JSONException, ParseException, IOException{
		//convert HttpResponse to string
		String responseString = EntityUtils.toString(closeableHttpRepsopnse.getEntity(), "UTF-8");
		
		//crating jason object --convert string innto jsonObject
		JSONObject jasonResponse = new JSONObject(responseString);
		//getting value from single 
		System.out.println("Jason Rspone from API -----> "+ jasonResponse);
		String perPage = TestUtil.getValueByJPath(jasonResponse,"/per_page");
		System.out.println("Per page record is ---->"+ perPage);
		Assert.assertEquals(perPage, "3");
		
		
		String totalPage = TestUtil.getValueByJPath(jasonResponse,"/total");
		System.out.println("Total page is ---->"+ totalPage);
		Assert.assertEquals(totalPage, "12");
		//How to get value from jason Array
		
		
		String dataLast_Name = TestUtil.getValueByJPath(jasonResponse,"/data[0]/last_name");
		System.out.println("Index 0 last Name is : ---->"+ dataLast_Name);
		Assert.assertEquals(dataLast_Name, "Bluth");
		
	
	}
	
	@Test(dependsOnMethods = {"getResponseCode_200"})
	public void getHeader() throws JSONException, ParseException, IOException{
		String headerValue= null;
		
		//Header
		Header[] headerArray = closeableHttpRepsopnse.getAllHeaders();
		//create hashmap<String, String> 
		HashMap<String, String> hasMap = new HashMap<String,String>();
		
		for(Header allHeades : headerArray){
			hasMap.put(allHeades.getName(), allHeades.getValue());
			//headerValue = hasMap.get(allHeades.getName());
			//if(headerValue.contains("chunked")){
			//	System.out.println("header Value : "+ hasMap.get(allHeades.getValue()));
			//	System.out.println("header Value : "+ headerValue);
			//	Assert.assertEquals(headerValue, "chunked");
		
			
			
			
			
		//
			}
			System.out.println("Size of allHeades :" + hasMap.size() );


			System.out.println("Print Header :" + hasMap );
			//System.out.println("print value at index 2: "+ hasMap)
	}
		
		
	
	
	//withheader
	@Test
	public void getApiTestWithHeader() throws ClientProtocolException, IOException, JSONException{
		RestClient restClient = new RestClient();
		
		HashMap <String, String> headers = new HashMap<String,String>();
		headers.put("content-type", "application/json");
		closeableHttpRepsopnse =restClient.get(url,headers);
		

		//getting status code
		int statuscode =closeableHttpRepsopnse.getStatusLine().getStatusCode();
		System.out.println("Status Code: ------> "+statuscode);
		
		
		Assert.assertEquals(statuscode, RESPONSE_CODE_200, "status code is not 200");
		
	}
}
	

