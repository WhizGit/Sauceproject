package com.saucelabs;


import com.saucelabs.common.SauceOnDemandAuthentication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.saucelabs.junit.Parallelized;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import java.net.URL;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import org.junit.Rule;
//------------------------
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.*;



//-------------------------------------------------------------

/**
 * Demonstrates how to write a JUnit test that runs tests against Sauce Labs using multiple browsers in parallel.
 * <p/>
 * The test also includes the {@link SauceOnDemandTestWatcher} which will invoke the Sauce REST API to mark
 * the test as passed or failed.
 *
 * @author Ross Rowe
 */
@RunWith(ConcurrentParameterized.class)
public class TestVerifyTitle implements SauceOnDemandSessionIdProvider {

  public String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public String Tar, Mtype, Mrec, Aggtype, Email, Password;

    /**
     * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link SauceOnDemandAuthentication} constructor.
     */
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("ervarunvardhan", "dc9703ef-ad60-4c15-9caa-1279e5171c26");

    /**
     * JUnit Rule which will mark the Sauce Job as passed/failed when the test succeeds or fails.
     */
    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);

    /**
     * Represents the browser to be used as part of the test run.
     */
    private String browser;
    /**
     * Represents the operating system to be used as part of the test run.
     */
    private String os;
    /**
     * Represents the version of the browser to be used as part of the test run.
     */
    private String version;
    /**
     * Instance variable which contains the Sauce Job Id.
     */
    private String sessionId;

    /**
     * The {@link WebDriver} instance which is used to perform browser interactions with.
     */
    private WebDriver driver;

    /**
     * Constructs a new instance of the test.  The constructor requires three string parameters, which represent the operating
     * system, version and browser to be used when launching a Sauce VM.  The order of the parameters should be the same
     * as that of the elements within the {@link #browsersStrings()} method.
     * @param os
     * @param version
     * @param browser
     */
    public TestVerifyTitle(String os, String version, String browser) {
        super();
        this.os = os;
        this.version = version;
        this.browser = browser;
    }

    /**
     * @return a LinkedList containing String arrays representing the browser combinations the test should be run against. The values
     * in the String array are used as part of the invocation of the test constructor
     */
    @ConcurrentParameterized.Parameters
    public static LinkedList browsersStrings() {
        LinkedList browsers = new LinkedList();
        browsers.add(new String[]{"Windows 7", "30", "firefox"});
      //  browsers.add(new String[]{"OSX 10.8", "6", "safari"});
        return browsers;
    }


    /**
     * Constructs a new {@link RemoteWebDriver} instance which is configured to use the capabilities defined by the {@link #browser},
     * {@link #version} and {@link #os} instance variables, and which is configured to run against ondemand.saucelabs.com, using
     * the username and access key populated by the {@link #authentication} instance.
     *
     * @throws Exception if an error occurs during the creation of the {@link RemoteWebDriver} instance.
     */
    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) {
            capabilities.setCapability(CapabilityType.VERSION, version);
        }
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", "Chartlytics Sample Test");
        this.driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();

    }

    /**
     * Runs a simple test verifying the title of the amazon.com homepage.
     * @throws Exception
     */
    @Test
   public void testVerifyTitle() throws Exception {
    driver.get(baseUrl + "http://dev.chartlytics.com");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("testingapptrial@gmail.com");
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("1234abcd@00");

    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
    File Defaultvaluepinpoitsheet = new File("Performer_creator.xlsx");
	  FileInputStream fis_default = new FileInputStream(Defaultvaluepinpoitsheet);
	  @SuppressWarnings("resource")
	XSSFWorkbook wb_def = new XSSFWorkbook(fis_default);
	  XSSFSheet ws_def = wb_def.getSheet("Input");
	  int colNums = ws_def.getRow(0).getLastCellNum();
	  int rowNums = ws_def.getLastRowNum();
		String[][] datas = new String[1][colNums];
	for(int c=1;c<=rowNums;c++)
	{
		for (int i=0;i<colNums;i++)
		{
			XSSFRow rows = ws_def.getRow(c);
			XSSFCell cell = rows.getCell(i);
			datas[0][i]= cellToString(cell);	
		}
		
		
		String PerName = datas [0][0];
		String age = datas [0][1];
		
		String Gen = datas [0][2];
		String Grade = datas [0][3];
		
   
    driver.findElement(By.cssSelector("a[title=\"Performers\"] > span")).click();
    driver.findElement(By.id("createPerformer")).click();
    driver.findElement(By.name("performerName")).clear();
    driver.findElement(By.name("performerName")).sendKeys(PerName);
    driver.findElement(By.name("age")).clear();
    driver.findElement(By.name("age")).sendKeys(""+age);
    if(Gen.equals("M"))
    {
    driver.findElement(By.id("male")).click();
    }else
    {
    	  driver.findElement(By.id("female")).click();
    }
    driver.findElement(By.name("grade")).clear();
    driver.findElement(By.name("grade")).sendKeys(Grade);
    driver.findElement(By.cssSelector("p.pull-right > button.btn.btn-primary")).click();
  //  driver.findElement(By.linkText("Last")).click();
    String p =driver.findElement(By.xpath("//*[@id='results-paging']/div/div[1]/button[1]")).getText();
    String item =driver.findElement(By.xpath("//*[@id='results-paging']/div/div[1]/button[2]")).getText();
   // System.out.println(p);
    //System.out.println(item);
    String[] temp, temps;
    String delimiter = " ";
    String del = " ";
	temp = p.split(delimiter);
	int page =Integer.parseInt(temp[3]);
	temps= item.split(del);

	
	int items =Integer.parseInt(temps[5]);
	
	if(page==1 || items<10)
	{
	
    try {
    	Thread.sleep(10000);
    	driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[4]/label[4]")).click();
    	assertEquals(PerName , driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).getText());
    	assertEquals("Age: "+age+"   "+"Grade: "+Grade , driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/p")).getText());
    	System.out.println("successfully created");
    	} catch (Error e) {
    		verificationErrors.append(e.toString());
    	}
	}// end of if
	else
	{
		int pag = page-1;
		driver.findElement(By.xpath("//*[@id='results-paging']/div/div[1]/a[3]")).click();
		driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[4]/label[4]")).click();
		  try {
			  Thread.sleep(10000);
		      assertEquals(PerName , driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).getText());
		      assertEquals("Age: "+age+"   "+"Grade: "+Grade, driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/p")).getText());
		      System.out.println("successfully created Performer " + PerName );
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
	} //end of else
	}//end of for loop
  }// end of test
  private String cellToString(XSSFCell cell) {
			Object result;
			int type = cell.getCellType();
	
			switch(type)
			{
			case 0:
				result = cell.getNumericCellValue();
				break;
			case 1:
				result = cell.getStringCellValue();
				break;
			default:
				throw new RuntimeException("there are no support for this type of cell");
			}
			
			return result.toString();

	}
    /**
     * Closes the {@link WebDriver} session.
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }
	
    /*
     * @return the value of the Sauce Job id.
     */
    @Override
    public String getSessionId() {
        return sessionId;
    }
}
