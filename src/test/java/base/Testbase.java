package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import utilities.ExcelReader;
import utilities.GetFullPageScreenShot;

public class Testbase {

	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public final static int TIMEOUT = 30;
	public final static int PAGE_LOAD_TIMEOUT = 30;
	public static String browser;
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	// public ExtentReports rep = ExtentManager.getInstance();
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	// Extent
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;


	@BeforeSuite
	public void Setup() throws IOException {
		// Extent

		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "\\target\\surefire-reports\\MyOwnReport.html");
		System.out.println(System.getProperty("user.dir") + "\\target\\surefire-reports\\MyOwnReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("OS", "Windows 10 Pro");
		extent.setSystemInfo("Host Name", "Daniel Joel");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Daniel joel");

		htmlReporter.config().setChartVisibilityOnOpen(true);
		htmlReporter.config().setDocumentTitle("Vlocity Take Home Test");
		htmlReporter.config().setReportName("My Own Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);

		if (driver == null) {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			Config.load(fis);
			log.debug("config file loaded");

			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			OR.load(fis);
			log.debug("OR file loaded !!!");


			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
				browser = System.getenv("browser");
			} else {
				browser = Config.getProperty("browser");
			}

			Config.setProperty("browser", browser);

			if (browser.equalsIgnoreCase("Chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();

				driver.get(Config.getProperty("URL"));
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
				wait = new WebDriverWait(driver, 5);
				log.debug("Chrome Launched !!!");
				

			}



		}
	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenShotPath = GetFullPageScreenShot.capture(driver, "MyFullPageScreenshot");
			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable());
			test.fail("Snapshot below: " + test.addScreenCaptureFromPath(screenShotPath));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
		} else {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
		}
	}

	public static void clickbutton(String locator) {
		if (locator.endsWith("Xpath")) {
			driver.findElement(By.xpath(locator)).click();
		}

	}

	public static void type(String locator, String value) {
		if (locator.endsWith("xpath")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).clear();
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		}

	}

	public static void clicklink(String locator) {
		if (locator.endsWith("xpath")) {
			driver.findElement(By.partialLinkText(OR.getProperty(locator))).click();
		}
		else {
			driver.findElement(By.partialLinkText(locator)).click();
		}
	}

	@AfterSuite
	public void TearDown() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
		extent.flush();
	}
	

}
