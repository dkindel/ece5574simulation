package virginia.tech.HTTPClientExample;

import java.io.IOException;
import org.apache.commons.lang.StringUtils;
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


public class APICaller implements steppable {
int userID;
int messageID;

public void step(SimState state) {
	
}
 
//Call both get and delete if messageID is known
public void callPushSystemAPI(String userID,String messageID) throws Exception{
    CloseableHttpClient httpclient = HttpClients.createDefault();
    String url="https://55izr0k3b7.execute-api.us-east-1.amazonaws.com/test/"+userID+"/messages/"+messageID;
    //String url="http://team7restapi.appspot.com/api/robots/1";
    try {
    	
        HttpGet httpget = new HttpGet(url);
        HttpDelete httpdelete = new HttpDelete(url);
       // System.out.println("Executing request " + httpget.getRequestLine());

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
        
        //System.out.println(myObject);
        //System.out.println("Executing request " + httpdelete.getRequestLine());
        httpclient.execute(httpdelete, responseHandler);
        
        //because response is not coming in correct format as of now
        responseBody="{\"messageId\" : \"1aB32-SE324-22Oa\",\"body\" : { \"msg_type\": Fire, \"body\": {\"field\" : \"<stuff here>\"}}}";
        
        //Strip response to get only required data
        String responseBodyStripped=responseBody.substring(StringUtils.ordinalIndexOf(responseBody, "{", 2), StringUtils.ordinalIndexOf(responseBody, "}", 1)+1);
        
        //CREATE EVENT
        String msg_type=Event.getEventType(responseBodyStripped);
        switch(msg_type){
        case "Fire": 
        	FireEvent fire = new FireEvent();
        	if(!fire.init(responseBodyStripped))
        		System.out.println("Improper JSON format");
        	break;
        
        case "Intruder":
        	IntruderEvent intruder = new IntruderEvent();
        	if(!intruder.init(responseBodyStripped))
        		System.out.println("Improper JSON format");
            break;
            
        case "WaterLeak":
        	WaterLeakEvent waterleak = new WaterLeakEvent();
        	if(!waterleak.init(responseBodyStripped))
        		System.out.println("Improper JSON format");
            break;
        }
        
        
    } finally {
        httpclient.close();
    }
}







}
