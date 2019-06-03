package rough;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.Testbase;

public class CheckwithAbs extends Testbase{
	
	
	@Test
	public void test() {
		String s=driver.findElement(By.xpath("//*[@id=\"js-link-box-zh\"]/strong")).getText();
		
		System.out.println(s);
	}
}
