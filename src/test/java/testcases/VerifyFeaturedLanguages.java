package testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import base.Testbase;

import pages.actions.Homepageactions;
import utilities.Utilities;

public class VerifyFeaturedLanguages extends Testbase {

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void verifyFeaturedLanguages(Hashtable<String, String> data) throws Exception {
		test = extent.createTest("VerifyFeaturedLanguages");
		Homepageactions HP = new Homepageactions();

		HP.VerifyLanguages(data);

		Thread.sleep(2000);

	}

}
