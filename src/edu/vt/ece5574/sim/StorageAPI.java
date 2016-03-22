package edu.vt.ece5574.sim;

/**
 * @author Vinit Gala
 *
 */

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class StorageAPI {
	
	URI uri;
	ResponseHandler<String> responseHandler ;
	CloseableHttpClient httpclient;
	
	public StorageAPI()
	{
		// Read URL from 
		try {
			uri = new URI ( "storage team needs to provide it");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		responseHandler = new ResponseHandler<String>() {

            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };
        
        httpclient = HttpClients.createDefault();
	}
	
	public String getRequest()
	{
		String responseBody = null ;
		HttpGet httpget = new HttpGet(uri);
		try {
			responseBody = httpclient.execute(httpget, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBody;		
	}
	
	public String deleteRequest()
	{
		String responseBody = null ;
		HttpDelete httpdelete = new HttpDelete(uri);
		try {
			responseBody = httpclient.execute(httpdelete, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBody;		
	}
	
	public String postRequest()
	{
		String responseBody = null ;
		HttpPost httppost = new HttpPost(uri);
		try {
			responseBody = httpclient.execute(httppost, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBody;		
	}
	
	public String putRequest()
	{
		String responseBody = null ;
		HttpPut httpput = new HttpPut(uri);
		try {
			responseBody = httpclient.execute(httpput, responseHandler);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBody;		
	}
}
