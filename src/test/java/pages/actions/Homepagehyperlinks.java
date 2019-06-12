package pages.actions;

import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import base.Testbase;
//import io.restassured.RestAssured;
//import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.given;

public class Homepagehyperlinks extends Testbase{
	
	public void VerifyLinks(Hashtable<String, String> data) {
		/*for(Map.Entry entry: data.entrySet()){
	           try {
	        	   //RestAssured.baseURI="https://"+entry.getValue()+".wikipedia.org";
	        	   test.info("https://"+entry.getValue()+".wikipedia.org");
	        	   given().
	        	   when().
	        	   get("https://"+entry.getValue()+".wikipedia.org").
	        	   then().
	        	   assertThat().statusCode(200);
	        	   test.log(Status.PASS, "Language Hyperlink: "+entry.getKey()+" Returned status code 200" );
	        	   
	        	   
	           }catch(AssertionError e) {
	        	   test.log(Status.FAIL, "Language Hyperlink: "+entry.getKey()+" verification failed" );
	           }
	            
	        }	*/
		//for(String key:data.keySet()) {
			System.out.println(data.get("Language"));
			try {
				//RestAssured.baseURI="https://"+entry.getValue()+".wikipedia.org";
	        	   test.info("https://"+data.get("Code")+".wikipedia.org");
	        	   given().
	        	   when().
	        	   get("https://"+data.get("Code")+".wikipedia.org").
	        	   then().
	        	   assertThat().statusCode(200);
	        	   test.log(Status.PASS, "Language Hyperlink: "+data.get("Language")+" Returned status code 200" );
			}
		catch(AssertionError e) {
			 test.log(Status.FAIL, "Language Hyperlink: "+data.get("Language")+" verification failed" );
		}
			
		//}
	}


}
