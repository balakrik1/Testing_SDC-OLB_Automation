package apiRequest;



import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;






import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePart;
import com.google.api.services.gmail.model.MessagePartBody;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.List;
import java.util.Properties;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;





public class ReadJson {
	@Test
	public static String ReadAttachment(String mid,String st,String jsonResponsetxt) throws IOException, Exception{
		String Filenames="";
		Properties prop = new Properties();
   	 InputStream input = null;									//\src\main\java\GmailAPI.properties
   	 String propertiesPath = System.getProperty("user.dir") + "/src/main/java/GmailAPI.properties";
			input = new FileInputStream(propertiesPath);
			prop.load(input);
		//String mid="162e2bbc0d1df658";
		//String st="ya29.GlylBfm2MU7rsTamndYh4XHPIhzyIJe7N8K2l2OAgLkTx1xCAiR08G6KiACH3o8vrK86VJIwOhk8Y_W3QQ7kjk2R-t53iRfzAwyCNQl6reKJGu409-cqMNML8ooXHg";
		//String text = new String(Files.readAllBytes(Paths.get("C:\\Users\\Moumita\\Desktop\\html2.json")), StandardCharsets.UTF_8);
		
		
		 JSONObject obj = new JSONObject(jsonResponsetxt);
		//System.out.println();
		 int check_attachment =0;
		 try{
			 check_attachment=obj.getJSONObject("payload").getJSONArray("parts").length();
		 }catch(Exception e){
			 check_attachment=0;
		 }
		
		for (int i=0;i<check_attachment;i++){
		JSONObject innerObj = obj.getJSONObject("payload").getJSONArray("parts").getJSONObject(i);
		RestAssured.baseURI="https://www.googleapis.com/gmail/v1/users/me/messages/";
		for(Iterator it = innerObj.keys(); it.hasNext(); ) {
            String key = (String)it.next();
            
            if (key.toString().equals("filename")){
            String fname =(String) innerObj.get(key);
            	if (fname.length()>0){
            		//System.out.println(key + ":" + innerObj.get(key));
            		String aid=(String) obj.getJSONObject("payload").getJSONArray("parts").getJSONObject(i).getJSONObject("body").get("attachmentId");
            		//System.out.println(aid);
            		/// call download function (innerObj.get(key),aid)
            		//System.out.println(innerObj.get(key) +":::"+aid);
            		Filenames=Filenames+innerObj.get(key)+",";
            		
            		Response res=given().proxy(prop.getProperty("Corp_PROXY"), Integer.parseInt(prop.getProperty("Proxy_PORT")))
            				.auth()
            				.preemptive().oauth2(st).get(mid+"/attachments/"+aid);
            	
           		 JSONObject downloadobj = new JSONObject(res.asString());
           		 String filename =(String)innerObj.get(key);
           		 String adata=(String)downloadobj.get("data");
           		DownloadfromGmail.attachDownload(adata,filename );
            		
            	}
            	
            }
        }

		}
		return Filenames;
	}//////////////////////////////////////////
	
   // @Test
	public static List<String> ReadContent(JSONObject obj) throws IOException{	
	int checkpart =0;
	List<String>  readmsgret =new ArrayList<String>();
//String text = new String(Files.readAllBytes(Paths.get("C:\\Users\\Moumita\\Desktop\\html2.json")), StandardCharsets.UTF_8);
		
		
		 //JSONObject obj = new JSONObject(text);
		try{
		 checkpart= obj.getJSONObject("payload").getJSONArray("parts").length();
		}catch(Exception W){}
		
		if (checkpart>0){
			String con=null;
			//System.out.println("download content");
			try{con=(String) obj.getJSONObject("payload").getJSONArray("parts").getJSONObject(0).getJSONObject("body").get("data");
					}catch(Exception e){
			 con=(String) obj.getJSONObject("payload").getJSONArray("parts").getJSONObject(0).getJSONArray("parts").getJSONObject(0).getJSONObject("body").get("data");
			}
			readmsgret=	 AceessApi.Readmailbody(con);
		}else {
			String con =(String) obj.getJSONObject("payload").getJSONObject("body").get("data");
			readmsgret=AceessApi.Readmailbody(con);
			
		}
		
		return readmsgret;
}
	




}
