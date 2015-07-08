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
<<<<<<< HEAD
public class TestSuit_019 implements SauceOnDemandSessionIdProvider {
=======
public class TestSuit_020 implements SauceOnDemandSessionIdProvider {
>>>>>>> 635096d23e10729f2a430f0aed1cd74aef3ee408
	
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
 //   public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("gsteam", "a7b52c33-af4c-4334-9486-75f4b13a9869");
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
	
<<<<<<< HEAD
    public TestSuit_019(String os, String version, String browser) {
=======
    public TestSuit_020(String os, String version, String browser) {
>>>>>>> 635096d23e10729f2a430f0aed1cd74aef3ee408
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
<<<<<<< HEAD
        capabilities.setCapability("name", "Chartlytics Test_AddPerformerToGroup");
=======
        capabilities.setCapability("name", "Chartlytics Test_Verify_Frequency_Accor_To_Acc_Dec_Point");
>>>>>>> 635096d23e10729f2a430f0aed1cd74aef3ee408
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
	 @Test 
	//--------------------------------------------------------------------------------------------

=======
	
	 @Test 
	//--------------------------------------------------------------------------------------------
	 public void Verify_Frequency_value() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.linkText("Log in")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
	Thread.sleep(2000);
    driver.findElement(By.cssSelector("a[title=\"Performers\"] > span")).click();
	Thread.sleep(2000);
    driver.findElement(By.cssSelector("//*[@id='grid-view']/div[1]/a/div")).click();
//-----------------------------------------------------------------------------------------------------------------
    File excel = new File(Pathofexcel);
   	FileInputStream fis = new FileInputStream(excel);
   	@SuppressWarnings("resource")
   	XSSFWorkbook wb = new XSSFWorkbook(fis);
   	
   	XSSFSheet ws = wb.getSheet("frequencytest");
    for(int y=19;y<=23;y++)// for loop for exceute no of row
    {
   //fetch data from excel sheet (testcommbination1.xlsx using Sheet2)
   
	int colNum = ws.getRow(19).getLastCellNum();
	String[][] data = new String[1][colNum];
	for (int i=1;i<=colNum-3;i++)// for loop for exceute no of coloum
	{
		XSSFRow row = ws.getRow(y);
		XSSFCell cell = row.getCell(i);
		data[0][i]= cellToString(cell);	

	}
	
	String CountTime = data [0][1];
	System.out.print(CountTime);
	String Acc = data [0][2];
	System.out.print(Acc);
	String Dec = data [0][3];
	System.out.print(Dec);
//-------------------------------------------------------------------------------------------------------------
	//Calculate the value of frequency
	float Counttym = Float.parseFloat(CountTime);
	int Acce;
	int Dcel;
	int acc;
	int dcc;
	if(Counttym<60)
	{
	float CT = (60/Counttym);
	System.out.println("count time change to min"+CT);
	 acc = Integer.parseInt(Acc);
	 Acce = (int) Math.round (acc * CT); // Acceleration frequency
	System.out.println(Acce);
	 dcc = Integer.parseInt(Dec);
	 Dcel = (int) Math.round(dcc * CT); // Deceleration frequency
	System.out.println(Dcel);
	}else
	{
		float CT = (Counttym/60);
		System.out.println("count time change to min"+CT);
		 acc = Integer.parseInt(Acc);
		 Acce = Math.round(acc / CT); // Acceleration frequency
		System.out.println(Acce);
		 dcc = Integer.parseInt(Dec);
		 Dcel = Math.round(dcc/CT); // Deceleration frequency
		System.out.println(Dcel);
		
	}
//----------------------------------------------------------------------------------------------------------------
	//Enter value in the Acceleration and Deceleration
    driver.findElement(By.name("recordFloor")).click();
    driver.findElement(By.name("recordFloor")).clear();
    driver.findElement(By.name("recordFloor")).sendKeys(""+Counttym);
    driver.findElement(By.name("correct")).click();
    driver.findElement(By.name("correct")).clear();
    driver.findElement(By.name("correct")).sendKeys(Acc);
    driver.findElement(By.name("incorrect")).click();
    driver.findElement(By.name("incorrect")).clear();
    driver.findElement(By.name("incorrect")).sendKeys(Dec);
    driver.findElement(By.xpath("//div[@id='Daily']/div/div/div/div[2]/div/div[4]/button[2]")).click();
	
	 //--------Verification Acc, dec and frequency----------------------------
	   //   assertEquals("1:21 AM", driver.findElement(By.cssSelector("td.measured")).getText());
		//    assertEquals("0:02:00", driver.findElement(By.cssSelector("td.recordFloor")).getText());
			assertEquals(Acc, driver.findElement(By.cssSelector("td.correct")).getText());
			 assertEquals(Dec, driver.findElement(By.cssSelector("td.incorrect")).getText());
			 assertEquals(Acce+"  "+Dcel+" ", driver.findElement(By.xpath("//*[@id='pinpoint0']/div/div/div[2]/table/tbody/tr[1]/td[5]")).getText());
	   
  }//end of for loop
  }
>>>>>>> 635096d23e10729f2a430f0aed1cd74aef3ee408
  
	
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
<<<<<<< HEAD
 

 
=======
>>>>>>> 635096d23e10729f2a430f0aed1cd74aef3ee408

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








