package apiRequest;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
public class VerifyGmailApi {

	public static String st=null;	
public List<String> verifymailinbox(int choice, String mailFrom, String mailSub) throws Exception, Exception{
	List<String>  finalreturn =new ArrayList<String>();
	String downloadedfile;
	Properties prop = new Properties();
  	 InputStream input = null;
  	 String propertiesPath = System.getProperty("user.dir") + "\\src\\main\\java\\GmailAPI.properties";
			input = new FileInputStream(propertiesPath);
			prop.load(input);
		
	//AceessApi ac=new AceessApi();
	String Rmessage="Success";
	
	//st=	AceessApi.getApiToken();
	//Changed by Pratik 21st June
	st=	AceessApi.getApiToken(choice);
	//String st="ya29.GlylBVyvdZLvHxPuf0wfPjJZ-hlC4RpWqw9o_Q-lTPXaX3vzqJZN-rRrPa2saa84_43lNEPsMh7hGJzO6KIF5pJyoUdCKxzJabyTzdXL9v_udaNIk2Zf_YE7O-Xurw";
	RestAssured.baseURI="https://www.googleapis.com/gmail/v1/users";
	RestAssured.useRelaxedHTTPSValidation();
	Response res=given().proxy(prop.getProperty("Corp_PROXY"), Integer.parseInt(prop.getProperty("Proxy_PORT"))).auth().preemptive().oauth2(st).get("/me/messages?q=\"is:unread\"");
	System.out.println(res.asString());
	
	final JSONObject obj = new JSONObject(res.asString());
	try{
	JSONArray geodata = obj.getJSONArray("messages");
	
	String msgst ="{\"addLabelIds\": [],\"removeLabelIds\": [\"UNREAD\"]}";
	JSONObject jsonObj = new JSONObject(msgst);
	
	
	for (int i = 0; i < geodata.length(); ++i) {
		RestAssured.baseURI="https://www.googleapis.com/gmail/v1/users/me/messages";
		JSONObject msg = geodata.getJSONObject(i);
		String mfrom,msub;
		mfrom=null;
		msub=null;
		RequestSpecification request = RestAssured.given();
		request.proxy(prop.getProperty("Corp_PROXY"), Integer.parseInt(prop.getProperty("Proxy_PORT")));
		request.header("Content-Type", "application/json");
		request.auth().preemptive().oauth2(st);
		request.body(msgst);
		
		
		//System.out.println(response.asString());
		
		Response response1 = given().proxy(prop.getProperty("Corp_PROXY"), Integer.parseInt(prop.getProperty("Proxy_PORT")))
				.auth().preemptive().oauth2(st).get("/"+msg.get("id"));

		//System.out.println(response1.asString());
		 
		 JSONObject readMsg = new JSONObject(response1.asString());
		 JSONArray array = readMsg.getJSONObject("payload").getJSONArray("headers");
		 Iterator<Object> iterator = array.iterator();
	        while (iterator.hasNext()) {
	            JSONObject jsonObject = (JSONObject) iterator.next();
	            for (String key : jsonObject.keySet()) {
	               // System.out.println(key + ":" + jsonObject.get(key));
	            	
	            	if (jsonObject.get(key).equals("From")){
	            		
	            		System.out.println(jsonObject.get("value"));
	            		mfrom=(String) jsonObject.get("value");
	            	}
                       if (jsonObject.get(key).equals("Subject")){
	            		
	            		System.out.println(jsonObject.get("value"));
	            		msub=(String) jsonObject.get("value");
	            	}
                       
                       
	            }
	            
	            }
	        if(mfrom.contains(mailFrom)&& msub.toLowerCase().contains(mailSub.toLowerCase())){
	        try {
	        	Response response = request.post("/"+msg.get("id")+"/modify");
	        	finalreturn=ReadJson.ReadContent(readMsg);
	        	downloadedfile=ReadJson.ReadAttachment((String)msg.get("id"), st,response1.asString());
	        	if(downloadedfile.equals("")){
	        		finalreturn.add("NO FILES");
	        	}else{finalreturn.add(downloadedfile);}
	        	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
	                                 }
	        }else{Rmessage="No email has been found";
	        finalreturn.add("BADRETURN");
	        finalreturn.add("BADRETURN");
	        finalreturn.add("BADRETURN");
	        finalreturn.add(Rmessage);
	        
	        }
	        finalreturn.add(Rmessage);
	
}

	}catch(Exception e){Rmessage=e.getMessage();
	
	finalreturn.add("BADRETURN");
    finalreturn.add("BADRETURN");
    finalreturn.add("BADRETURN");
    finalreturn.add(Rmessage);
    e.printStackTrace();
    
	}
	
	return finalreturn;
}

public  void mailSeen(int choice) throws Exception, Exception{
 	Properties prop = new Properties();
  	 InputStream input = null;
  	 String propertiesPath = System.getProperty("user.dir") + "\\src\\main\\java\\GmailAPI.properties";
			input = new FileInputStream(propertiesPath);
			prop.load(input);
		
	//AceessApi ac=new AceessApi();
		String st=null;
	//st=	AceessApi.getApiToken();
	//Changed by Pratik 21st June,2018
	st=	AceessApi.getApiToken(choice);
	//String st="ya29.GlylBVyvdZLvHxPuf0wfPjJZ-hlC4RpWqw9o_Q-lTPXaX3vzqJZN-rRrPa2saa84_43lNEPsMh7hGJzO6KIF5pJyoUdCKxzJabyTzdXL9v_udaNIk2Zf_YE7O-Xurw";
	RestAssured.baseURI="https://www.googleapis.com/gmail/v1/users";
	RestAssured.useRelaxedHTTPSValidation();
	Response res=given().proxy(prop.getProperty("Corp_PROXY"), Integer.parseInt(prop.getProperty("Proxy_PORT")))
			.auth().preemptive().oauth2(st).get("/me/messages?q=\"is:unread\"");
	System.out.println(res.asString());
	
	final JSONObject obj = new JSONObject(res.asString());
	try{
	JSONArray geodata = obj.getJSONArray("messages");
	
	String msgst ="{\"addLabelIds\": [],\"removeLabelIds\": [\"UNREAD\"]}";
	JSONObject jsonObj = new JSONObject(msgst);
	
	
	for (int i = 0; i < geodata.length(); ++i) {
		RestAssured.baseURI="https://www.googleapis.com/gmail/v1/users/me/messages";
		JSONObject msg = geodata.getJSONObject(i);
		
		RequestSpecification request = RestAssured.given();
		request.proxy(prop.getProperty("Corp_PROXY"), Integer.parseInt(prop.getProperty("Proxy_PORT")));
		request.header("Content-Type", "application/json");
		request.auth().preemptive().oauth2(st);
		request.body(msgst);
		Response response = request.post("/"+msg.get("id")+"/modify");
		
	}}catch(Exception e){
		//e.printStackTrace();
	}


}


}
