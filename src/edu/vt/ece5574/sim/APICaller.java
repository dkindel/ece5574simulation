package virginia.tech.HTTPClientExample;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class APICaller {
int userID;
int messageID;

 
//Call both get and delete if messageID is known
public void callPushSystemAPI(int userID,int messageID) throws Exception{
    CloseableHttpClient httpclient = HttpClients.createDefault();
    String url="https://55izr0k3b7.execute-api.us-east-1.amazonaws.com/test/"+userID+"/messages/"+messageID;
    //String url="http://team7restapi.appspot.com/api/robots/1";
    try {
    	
        HttpGet httpget = new HttpGet(url);
        HttpDelete httpdelete = new HttpDelete(url);
        System.out.println("Executing request " + httpget.getRequestLine());

        // Create a custom response handler
        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

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
        String responseBody = httpclient.execute(httpget, responseHandler);
        
        JSONObject myObject = new JSONObject(responseBody);
        System.out.println(myObject);
        System.out.println("Executing request " + httpdelete.getRequestLine());
        httpclient.execute(httpdelete, responseHandler);
        
    } finally {
        httpclient.close();
    }
}

//call get on all the messages for the user when messageID is not known
public void callPushSystemAPI(int userID) throws Exception{
	CloseableHttpClient httpclient = HttpClients.createDefault();
    String url="https://55izr0k3b7.execute-api.us-east-1.amazonaws.com/test/"+userID+"/messages/";
	//String url="http://team7restapi.appspot.com/api/robots/1";
    try{
    	 HttpGet httpget = new HttpGet(url);
         System.out.println("Executing request " + httpget.getRequestLine());

         // Create a custom response handler
         ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

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
         String responseBody = httpclient.execute(httpget, responseHandler);
         JSONObject myObject = new JSONObject(responseBody);
         System.out.println(myObject);
      
    }
    finally {
        httpclient.close();
    }
    
    
}





}
