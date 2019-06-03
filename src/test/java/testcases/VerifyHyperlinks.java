package testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import base.Testbase;
import pages.actions.Homepageactions;
import pages.actions.Homepagehyperlinks;
import utilities.Utilities;

public class VerifyHyperlinks extends Testbase{
	
	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void verifyFeaturedLanguages(Hashtable<String, String> data) throws InterruptedException {
		test = extent.createTest("VerifyHyperlinks");
		Homepagehyperlinks HL = new Homepagehyperlinks();

		HL.VerifyLinks(data);

		Thread.sleep(2000);
		
	}

}
