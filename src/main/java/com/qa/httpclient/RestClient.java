package com.qa.httpclient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class RestClient {
	
	//1. Get methods without header
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException, JSONException
	{
		CloseableHttpClient httpClients = HttpClients.createDefault();//this will create connection to server
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse closeableHttpRepsopnse = httpClients.execute(httpGet);
		
		return closeableHttpRepsopnse;
		
		
		
	}

	//1. Get methods with header
	public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException, JSONException
	{
		CloseableHttpClient httpClients = HttpClients.createDefault();//this will create connection to server
		HttpGet httpGet = new HttpGet(url);
		
		for(Map.Entry<String, String> entry: headerMap.entrySet())
		{
			
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpRepsopnse = httpClients.execute(httpGet);
		
		return closeableHttpRepsopnse;
		
		
		
	}
//2. post method
	public CloseableHttpResponse post(String url, String entity, HashMap<String, String> headerMap) throws ClientProtocolException, IOException, JSONException
	{	//setting url
		CloseableHttpClient httpClients = HttpClients.createDefault();//this will create connection to server
		HttpPost httpPost = new HttpPost(url);
		//payload
		httpPost.setEntity(new StringEntity(entity));
		
		//header
		for(Map.Entry<String, String> entry: headerMap.entrySet())
		{
			
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpRepsopnse = httpClients.execute(httpPost);
		
		return closeableHttpRepsopnse;
		
		
	
	}
	
}
