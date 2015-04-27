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
public class TestSuit_8 implements SauceOnDemandSessionIdProvider {
	
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String Tar, Mtype, Mrec, Aggtype, Email,Fullname,Orgname, Password;
	private String Pathofexcel ="./src/test/java/com/yourcompany/dataexcel.xlsx"; // path of your excel file
	/*String[][] SauceInfo = GetValue(Pathofexcel,"signup",11);
	String SauceUser = SauceInfo[0][0];
	String SauceAccessKey = SauceInfo[0][1]; */
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("testingapptrial", "d64b5203-e2a4-4108-bd1b-0d311b34dc5d");

    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);
    private String browser;
    private String os;
    private String version;
    private String sessionId;
    private WebDriver driver;
	//-----------------------------------------------------------------------------------------
	
	//-----------------------------------------------------------------------------------------
	
    public TestSuit_8(String os, String version, String browser) {
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
        capabilities.setCapability("name", "Chartlytics Test_CreateGroupWithUser");
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
	@Ignore
	 @Test 
	 public void testCreateGroupWithUser() throws Exception {
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
	Thread.sleep(5000);
	driver.findElement(By.xpath("html/body/nav/div/div/a/i")).click();
    driver.findElement(By.cssSelector("a[title=\"Groups\"] > span")).click();
    String[][] creatgroup = GetValue(Pathofexcel,"group",9);
	String grpname = creatgroup[0][0]; 
	String grpdescription = creatgroup[0][1];  
	String grplocation= creatgroup[0][2];
	String usrname= creatgroup[0][3];
	String usrrole= creatgroup[0][4];
	driver.findElement(By.id("newGroup")).click();
    driver.findElement(By.name("groupName")).clear();
    driver.findElement(By.name("groupName")).sendKeys(grpname);
    driver.findElement(By.cssSelector("input[name=\"description\"]")).clear();
    driver.findElement(By.cssSelector("input[name=\"description\"]")).sendKeys(grpdescription);
    driver.findElement(By.name("location")).clear();
    driver.findElement(By.name("location")).sendKeys(grplocation);
//--------------------------------------------Add User---------------------------------------------------------------------------------\\    
    driver.findElement(By.id("addUser")).click();
	Thread.sleep(3000);
    driver.findElement(By.xpath("//form/div[1]/div/div/a/span[1]")).click();
	Thread.sleep(3000);
    driver.findElement(By.xpath("html/body/div[8]/div/input")).sendKeys(usrname);
    driver.findElement(By.xpath("html/body/div[8]/ul/li/div")).click();
    if(usrrole.equals("Advisor"))
    {                                 
    	driver.findElement(By.xpath("//form/div[2]/div/div/label[1]")).click();
    }
    else
    {                             
    	driver.findElement(By.xpath("//form/div[2]/div/div/label[2]")).click(); 
    }
    driver.findElement(By.id("confirmAdd")).click();
    driver.findElement(By.id("cancelUserSelect")).click();
                                  
    String addedusrname= driver.findElement(By.cssSelector("h2.male")).getText();
    assertEquals(usrname,addedusrname);
    System.out.println("//-----------------------Added User Name------------------------------------//");
    System.out.println(addedusrname);
    driver.findElement(By.id("createGroup")).click();
    Thread.sleep(10000);
    driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
    String groupname = driver.findElement(By.xpath(".//*[@id='grid-view']/div/a/div/div[2]/div[1]/h2")).getText();
    assertEquals(groupname,grpname);
    System.out.println("//-----------------------Create The Group------------------------------------//");
    System.out.println(groupname);
  }
 

  
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








