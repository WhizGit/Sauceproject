<<<<<<< HEAD
spackage com.saucelabs;
=======
package com.saucelabs;
>>>>>>> 635096d23e10729f2a430f0aed1cd74aef3ee408

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
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.saucelabs.junit.Parallelized;
import com.saucelabs.junit.ConcurrentParameterized;
import com.saucelabs.junit.SauceOnDemandTestWatcher;
import java.util.concurrent.TimeUnit;
import java.net.URL;
import java.util.*;
import java.util.LinkedList;
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
public class TestSuit_023 implements SauceOnDemandSessionIdProvider {
	
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String Tar, Mtype, Mrec, Aggtype, Email,Fullname,Orgname, Password;
	private String Pathofexcel ="./src/test/java/com/yourcompany/dataexcel.xlsx"; // path of your excel file
	/*String[][] SauceInfo = GetValue(Pathofexcel,"signup",11);
	String SauceUser = SauceInfo[0][0];
	String SauceAccessKey = SauceInfo[0][1]; */
<<<<<<< HEAD
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("gsteam", "a7b52c33-af4c-4334-9486-75f4b13a9869");

=======
   // public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("gsteam", "a7b52c33-af4c-4334-9486-75f4b13a9869");
 public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("testingdummy", "31896c70-5384-4a59-82d7-c993f0182942");
>>>>>>> 635096d23e10729f2a430f0aed1cd74aef3ee408
    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);
    private String browser;
    private String os;
    private String version;
    private String sessionId;
    private WebDriver driver;
	//-----------------------------------------------------------------------------------------
	
	//-----------------------------------------------------------------------------------------
	
    public TestSuit_023(String os, String version, String browser) {
        super();
        this.os = os;
        this.version = version;
        this.browser = browser;
    }

    @ConcurrentParameterized.Parameters
    public static LinkedList browsersStrings() {
        LinkedList browsers = new LinkedList();
        browsers.add(new String[]{"Windows 7", "42", "chrome"});
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
        capabilities.setCapability("name", "Chartlytics Test_Invite_Verify_SignIn_As_User");
        this.driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
	String[][] getit = GetValue(Pathofexcel,"signup",2);
	baseUrl = getit[0][0]; 
	Fullname = getit[0][1]; 
	Email= getit[0][2];
	Password=getit[0][3];
	Orgname=getit[0][4];
	 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
<<<<<<< HEAD
	@Ignore
=======
	
>>>>>>> 635096d23e10729f2a430f0aed1cd74aef3ee408
	 @Test 
	//--------------------------------------------------------------------------------------------
	public void Invite_Verify_SignIn_As_User() throws Exception {
	   for(int y=2; y<=4;y++)
	    {
 String[][] getit = GetValue(Pathofexcel,"signup",2);
 baseUrl = getit[0][0]; 
  Email= getit[0][2];  
  Password=getit[0][3];

    driver.get("http://"+ baseUrl + "/signin");
    //driver.manage().window().maximize();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
//---------------------------------------Invite User---------------------------------------------------------------------------------//
 
   String[][] invusr = GetValue(Pathofexcel,"user",y);
   String EmailAdd = invusr[0][0]; 
   String Role= invusr[0][1]; 
   String Pass= invusr[0][2]; 
    driver.findElement(By.cssSelector("a[title=\"Settings\"] > span")).click();
    Thread.sleep(3000);
    driver.findElement(By.linkText("Manage Users")).click();
    driver.findElement(By.id("createUser")).click();
    Thread.sleep(5000);
    driver.findElement(By.name("email")).sendKeys(EmailAdd);
    driver.findElement(By.cssSelector(".select2-choices")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector(".select2-input")).sendKeys(Role);
    Thread.sleep(2000);
    driver.findElement(By.cssSelector(".select2-result-label")).click();
    Thread.sleep(2000);
    driver.findElement(By.cssSelector("p.pull-right > button.btn.btn-primary")).click();
   // Thread.sleep(1000);
   Thread.sleep(3000);
    
//----------------------------------------------------SignOut------------------------------------------------------------------------//
    driver.findElement(By.cssSelector(".user-info>a>span")).click();
    Thread.sleep(2000);
    driver.findElement(By.linkText("Logout")).click();
    Thread.sleep(20000);
//----------------------------------------------------Email Verification----------------------------------------------------------//
    
    driver.get("https://accounts.google.com/ServiceLogin?sacu=1&scc=1&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&hl=en&service=mail");
    if(y==2)
    {
    driver.findElement(By.id("Email")).clear();
    driver.findElement(By.id("Email")).sendKeys("testingapptrial@gmail.com");
    }
    driver.findElement(By.id("Passwd")).clear();
    driver.findElement(By.id("Passwd")).sendKeys("1234abcd@00");
    driver.findElement(By.id("signIn")).click();
    Thread.sleep(3000);
    driver.findElement(By.partialLinkText("Inbox")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//div [@class='y6']/span[contains(.,'Whiztest has invited you to Chartlytics')]")).click();
    driver.findElement(By.partialLinkText("http://dev.chartlytics.com/register/")).click();
    Thread.sleep(2000);
//----------------------------------------------------Create the account------------------------------------------------------------------//
    for(String winHandle : driver.getWindowHandles()){
        driver.switchTo().window(winHandle);
        }
   /* Thread.sleep(3000);*/
    String accnt = driver.findElement(By.xpath("html/body/div[1]/div[2]/div[2]/form/h3")).getText();
    System.out.println("//@@##--------------After verifying email it opens the ----------------##@@// ");
    System.out.println(accnt);
    driver.findElement(By.name("password")).sendKeys(Pass);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
//---------------------------------------------------------SingIn into using user-----------------------------------------------------//    

    String Suuccmes = driver.findElement(By.xpath("//form/div/div")).getText();
    System.out.println("//------------------After creating the account it open-------------------------//");
    System.out.println(Suuccmes);
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(EmailAdd);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Pass);
    
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(3000);
    driver.findElement(By.linkText("Start Using Chartlytics Now")).click();
    Thread.sleep(3000);
    String db = driver.findElement(By.xpath("//div[@id='app-main']/div/div/div")).getText();
    assertEquals("Dashboard", db);
    System.out.println("//--------------------After creating account & signin it open--------------------//");
    System.out.println(db);
    driver.findElement(By.cssSelector(".user-info>a>span")).click();
    Thread.sleep(3000);
    driver.findElement(By.linkText("Logout")).click();
    
    }//end of for loop
}


	
	//---------------------------------------------------------------------------------------------
  
	 private String[][] GetValue(String Pathfile, String sheetName, int startrow) throws IOException{
	  File excel= new File(Pathfile);
	  FileInputStream fis = new FileInputStream(excel);
	  @SuppressWarnings("resource")
	XSSFWorkbook wb = new XSSFWorkbook(fis);
	  XSSFSheet ws = wb.getSheet(sheetName);
	  int colNum = ws.getRow(startrow).getLastCellNum();
	  String [][] arrays = new String [1][colNum];
	  for(int i=0;i<colNum;i++){
		  XSSFRow row= ws.getRow(startrow);
		  XSSFCell cell = row.getCell(i);
		  arrays[0][i] = cellToString(cell);
		 // System.out.println(arrays[0][i]);
	  }
	  return arrays;
  }
  private static String cellToString(XSSFCell cell) {
		
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








