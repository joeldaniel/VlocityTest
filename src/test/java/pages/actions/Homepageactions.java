package pages.actions;

import base.Testbase;

import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import static org.testng.Assert.assertEquals;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class Homepageactions extends Testbase{
	
	//public Homepagelocators login;
	
	/*public Homepageactions(){
		
		this.login = new Homepagelocators();
		AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver,10);
		PageFactory.initElements(factory, this.login);
		
	}*/
	
	public void VerifyLanguages(Hashtable<String, String> data) {
		
		
		//for(String key:data.keySet()) {
			System.out.println(data.get("Language"));
			try {
				Assert.assertEquals(data.get("Language"), driver.findElement(By.xpath("//*[@id=\"js-link-box-"+data.get("Code")+"\"]/strong")).getText());
				 test.log(Status.PASS, "Language : "+data.get("Language")+" Verified Sucessfully" );
			}
		catch(AssertionError e) {
     	   test.log(Status.FAIL, "Language : "+data.get("Language")+" verification failed" );
		}
			
		//}
		

		

		
	}

}
