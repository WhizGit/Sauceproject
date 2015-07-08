package com.saucelabs;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.saucelabs.common.SauceOnDemandAuthentication;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.saucelabs.junit.Parallelized;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;

import java.util.concurrent.TimeUnit;
import java.net.URL;
import java.util.*;

import com.saucelabs.common.SauceOnDemandAuthentication;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//-------------------------------------------------------
@RunWith(ConcurrentParameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSuit_001E implements SauceOnDemandSessionIdProvider {

	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String Tar, Mtype, Mrec, Aggtype, Email,Fullname,Orgname, Password,Type;
	private String Pathofexcel ="./src/test/java/com/yourcompany/dataexcel.xlsx"; // path of your excel file
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("TestingAutomate", "189a089e-07f7-4a79-ac18-b8082be2fa72");

    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);
    private String browser;
    private String os;
    private String version;
    private String sessionId;
    private WebDriver driver;
	//-----------------------------------------------------------------------------------------
	
	//-----------------------------------------------------------------------------------------
	
    public TestSuit_001E(String os, String version, String browser) {
        super();
        this.os = os;
        this.version = version;
        this.browser = browser;
    }

    @ConcurrentParameterized.Parameters
    public static LinkedList browsersStrings() {
        LinkedList browsers = new LinkedList();
        browsers.add(new String[]{"Windows 7", "37", "firefox"});
      //  browsers.add(new String[]{"OSX 10.8", "6", "safari"});
        return browsers;
    }

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.BROWSER_NAME, browser);
        if (version != null) {
            capabilities.setCapability(CapabilityType.VERSION, version);
        }
        capabilities.setCapability(CapabilityType.PLATFORM, os);
        capabilities.setCapability("name", "Chartlytics Test_CreateOrg");
        this.driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
	String[][] getit = CommonMethod.GetValue(Pathofexcel,"signup",2);
	baseUrl = getit[0][0]; 
	Fullname = getit[0][1]; 
	Email= getit[0][2];  
	Password=getit[0][3];
	Orgname=getit[0][4];
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
	
	@Test
	public void testA_CreateAnOrganisation() throws Exception {
//--------------------------------------------------Create An Organization---------------------------------------------------------------//	  
		
		 String[][] getit = CommonMethod.GetValue("./src/dataexcel.xlsx","signup",2);
		
		 
		 baseUrl = getit[0][0]; 
		 Fullname = getit[0][1]; 
		 Email= getit[0][2];  
		 Password=getit[0][3];
		 Orgname=getit[0][4];
		 Type = getit[0][5];
		  System.out.println(Type);
		  
	    driver.get("http://"+baseUrl + "/");
	    driver.findElement(By.name("fullname")).clear();
	    driver.findElement(By.name("fullname")).sendKeys(Fullname);
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys(Email);
	    WebElement emailid = driver.findElement(By.name("email"));
	    String id = emailid.getAttribute("value");
	    driver.findElement(By.name("password")).clear();
	    driver.findElement(By.name("password")).sendKeys(Password);
	    if(Type.equalsIgnoreCase("Organization"))
	    {
	    driver.findElement(By.xpath("//label")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.name("orgName")).clear();
	    driver.findElement(By.name("orgName")).sendKeys(Orgname);
	    }else
	    {
	    driver.findElement(By.xpath("//label[2]")).click();
	    }
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    
	    try {
	    	String msg = driver.findElement(By.xpath("//form/div/div")).getText();
	    	String del = " ";
	    	String[] temps= msg.split(del);
	    	System.out.println(temps[9]);
	      assertEquals(temps[9], id) ;
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
//--------------------------------------------------------------------SignIn In Created Orgazation-------------------------------------------------------------------//    
   
	}
	@Ignore
	@Test
	public void testB_UpdateTimeZone() throws Exception {
  driver.get("http://"+ baseUrl + "/signin");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);

    driver.findElement(By.xpath("//button[@type='submit']")).click();
	 for(String winHandle : driver.getWindowHandles()){
    driver.switchTo().window(winHandle);
    }
  driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//a[contains(@href, '/')]")).click();
    Thread.sleep(5000);
    String db = driver.findElement(By.xpath("//div[@id='app-main']/div/div/div")).getText();
    assertEquals("Dashboard", db);
	}
	
	@Ignore
    @Test
	public void testB_OrganisationVerification() throws Exception {
//------------------------------------------Email Verification---------------------------------------------------------------------------//
    driver.get("https://accounts.google.com/ServiceLogin?sacu=1&scc=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&hl=en&service=mail");
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys("testingapptrial@gmail.com");
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys("1234abcd@00");
    driver.findElement(By.id("signIn")).click();
    Thread.sleep(10000);
    driver.findElement(By.partialLinkText("Inbox")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//div [@class='y6']/span[contains(.,'Chartlytics: Confirm your email address')]")).click();
    driver.findElement(By.partialLinkText("http://dev.chartlytics.com/activate/")).click();
    Thread.sleep(5000);
    String emailver = driver.findElement(By.xpath("//form/div/div")).getText();
    System.out.println("//@@##--------------After Verifying Email----------------##@@// ");
    System.out.println(emailver);
    
//--------------------------------------------------------------------SignIn In Created Orgazation-------------------------------------------------------------------//    
    for(String winHandle : driver.getWindowHandles()){
    driver.switchTo().window(winHandle);
    }
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//a[contains(@href, '/')]")).click();
    Thread.sleep(5000);
    String db = driver.findElement(By.xpath("//div[@id='app-main']/div/div/div")).getText();
    assertEquals("Dashboard", db);
    System.out.println("//--------------------After the reset password & signin it open--------------------//");
    System.out.println(db);
  }
  
  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
   

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    
    @Override
    public String getSessionId() {
        return sessionId;
    }
}








