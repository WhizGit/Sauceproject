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
public class TestSuit_003 implements SauceOnDemandSessionIdProvider {

	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String Tar, Mtype, Mrec, Aggtype, Email,Fullname,Orgname, Password;

	private String Pathofexcel ="./src/test/java/com/yourcompany/dataexcel.xlsx"; // path of your excel file
	/*	String[][] SauceInfo = GetValue(Pathofexcel,"signup",11);
	String SauceUser = SauceInfo[0][0];
	String SauceAccessKey = SauceInfo[0][1]; */
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("gsteam", "a7b52c33-af4c-4334-9486-75f4b13a9869");

    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);
    private String browser;
    private String os;
    private String version;
    private String sessionId;
    private WebDriver driver;
	//-----------------------------------------------------------------------------------------
	
	//-----------------------------------------------------------------------------------------
	
    public TestSuit_003(String os, String version, String browser) {
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
        capabilities.setCapability("name", "Chartlytics Test_CreatePerformer");
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
	
	@Test // Create Performer
   public void CreatePerformer() throws Exception {
     driver.get("http://"+ baseUrl + "/signin");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);

    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
    for(int i=2; i<=7;i++){
    String[][] getits = GetValue(Pathofexcel,"performer",i);
		String PerName = getits [0][0];
		String age = getits [0][1];
		String Gen = getits [0][2];
		String Grade = getits [0][3];
		
   
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
    Thread.sleep(3000);
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
	
	if(page==1 || items<=10)
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
		int pag = page+1;
		driver.findElement(By.xpath("//*[@id='results-paging']/div/div[1]/a["+pag+"]")).click();
		driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[4]/label[4]")).click();
			  Thread.sleep(10000);
		      assertEquals(PerName , driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).getText());
		      assertEquals("Age: "+age+"   "+"Grade: "+Grade, driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/p")).getText());
		      System.out.println("successfully created Performer " + PerName );
		   
	} //end of else
  } // of for loop
  }// end of test
  
  
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








