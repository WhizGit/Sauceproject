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
	public class Create_Pinpoint implements SauceOnDemandSessionIdProvider {
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String Tar, Mtype, Mrec, Aggtype, Email, Password;
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("ervarunvardhan", "dc9703ef-ad60-4c15-9caa-1279e5171c26");

    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);
    private String browser;
    private String os;
    private String version;
    private String sessionId;
    private WebDriver driver;

    public Create_Pinpoint(String os, String version, String browser) {
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
        capabilities.setCapability("name", "Chartlytics Sample Test");
        this.driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();

    }

    @Test
    public void createpinpoint() throws Exception {
	String[][] getit = GetValue("./src/dataexcel.xlsx","signup",2);
	baseUrl = getit[0][0]; 
	 Email= getit[0][2];  
	 Password=getit[0][3];
	
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.get("http://"+baseUrl + "/");
    driver.findElement(By.linkText("Log in")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    // click on the pinpoint on the dashboard
    driver.findElement(By.cssSelector("a[title=\"Pinpoints+\"] > span")).click();
     for(int c=5; c<=8; c++) // we can start test case from testcase 1
		{
	String[][] data = GetValue("./src/dataexcel.xlsx","ReviewSummary",c);
	String Acc = data [0][1];
	String Dec = data [0][2];
	String Frq = data [0][3];
	String Dur = data [0][4];
	String Lat = data [0][5];
	String Cou = data [0][6];
	String Dai = data [0][7];
	String Wee = data [0][8];
	String Mon = data [0][9];
	String Yr = data [0][10];
	String Fir = data [0][11];
	String Med = data [0][12];
	String Geo = data [0][13];
	String Min = data [0][14];
	String Max = data [0][15];
	String Sum = data [0][16];
	String Sta = data [0][17];
	System.out.print("Create pinpoint:");

	//--------------------- read data for set default using excel sheet-------
	int k=0;
	k = c-1;
	String[][] datas = GetValue(".src/test/java/com/yourcompany/dataexcel.xlsx","Pinpoint",k);	
		String SIn = datas [0][1];
		String Pout = datas [0][2];
		String Action = datas [0][3];
		String Noun = datas [0][4];
		String Context = datas [0][5];
		String Dct = datas [0][6];
		String AAMi = datas [0][7];
		String AAMx = datas [0][8];
		String ADMi = datas [0][9];
		String ADMx = datas [0][10];
		String Daim  = datas [0][11];
	System.out.println("Create pinpoint:"+ Action);
		
    //click on the Create Pinpoint
    driver.findElement(By.id("createPinpoint")).click();
    // click value from the drop down list
//---------------------------------------------------------------------------------------------------------------------  
 //   driver.findElement(By.id("s2id_autogen1")).click();
    // click value from the drop down list
	
 //   driver.findElement(By.id("s2id_autogen2")).click();
//---------------------------------------------------------------------------------------------------------------------    
    // clear and enter adverb
    driver.findElement(By.id("actionVerb")).clear();
    driver.findElement(By.id("actionVerb")).sendKeys(Action);
    
    // clear and enter object
    driver.findElement(By.id("objectNoun")).clear();
    driver.findElement(By.id("objectNoun")).sendKeys(Noun);
    
    //Enter and clear context
    driver.findElement(By.id("context")).clear();
    driver.findElement(By.id("context")).sendKeys(Context);
    
    WebElement PinpointName = driver.findElement(By.name("pinpointName"));
    String PinName = PinpointName.getAttribute("value");
    // enter next button
    driver.findElement(By.cssSelector(".btn.btn-primary.btn-next")).click();
  //  driver.findElement(By.xpath("html/body/div[7]/div/div/div[5]/p/button[2]")).click();
    
    // Here is used permutataion of excel file
    String tru = "y";
    String fal = "N";
  
// pinpoint goal selection
  if((Acc.equals(tru)) && (Dec.equals(fal)))
  {
    driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[1]/div/div[1]/div/div/label[1]")).click();
     Tar ="acceleration";
  }
  else if((Acc.equals(fal)) && (Dec.equals(tru)))
     {
    
    	driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[1]/div/div[1]/div/div/label[2]")).click();
    	Tar="deceleration";
     }
  else
  {
	  System.out.println("Wrong pinpoint goal input in the excel sheet");
  }
  
 
 //Measurement type selection 
  if((Frq.equals(tru)) && (Dur.equals(fal)) && (Lat.equals(fal)) && (Cou.equals(fal)) )
  {
	  driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[1]/div/div[2]/div/div/label[1]")).click();
	  Mtype="frequency";
  }
  else if((Frq.equals(fal)) && (Dur.equals(tru)) && (Lat.equals(fal)) && (Cou.equals(fal)))
  {
	  driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[1]/div/div[2]/div/div/label[2]")).click();
	  Mtype="duration";
  }
  else if((Frq.equals(fal)) && (Dur.equals(fal)) && (Lat.equals(tru)) && (Cou.equals(fal)))
		 {
	  driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[1]/div/div[2]/div/div/label[3]")).click();
	  Mtype="latency";
		  }
  else if((Frq.equals(fal)) && (Dur.equals(fal)) && (Lat.equals(fal)) && (Cou.equals(tru)))
		  {
	  driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[1]/div/div[2]/div/div/label[4]")).click();
	  Mtype="countOnly";
		  }
  else
  {
	  System.out.println("Wrong Measurement input in the excel sheet");
  }
 
// Measurement Recurrence
  
 if((Dai.equals(tru)) && (Wee.equals(fal)) && (Mon.equals(fal)) && (Yr.equals(fal)))
 {
	 driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[1]/div/div[3]/div/div/label[1]")).click();
	 Mrec="daily";
 }
 else if((Dai.equals(fal)) && (Wee.equals(tru)) && (Mon.equals(fal)) && (Yr.equals(fal)))
 {
	 driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[1]/div/div[3]/div/div/label[2]")).click();
	 Mrec="weekly";
 }
 else if((Dai.equals(fal)) && (Wee.equals(fal)) && (Mon.equals(tru)) && (Yr.equals(fal)))
 {
	 driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[1]/div/div[3]/div/div/label[3]")).click();
	 Mrec="monthly";
 }
 else if((Dai.equals(fal)) && (Wee.equals(fal)) && (Mon.equals(fal)) && (Yr.equals(tru)))
 {
	 driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[1]/div/div[3]/div/div/label[4]")).click();
	 Mrec="yearly";
 }
 else
 {
	  System.out.println("Wrong Measurement Recurrence input in the excel sheet");
 }

//Selection of pinpoint default  
//--------------------------------------------------------------------------------------------------------------
 if(Mtype=="duration")
 {
 driver.findElement(By.id("durationAimMin")).clear();
 driver.findElement(By.id("durationAimMin")).sendKeys(AAMi);
 driver.findElement(By.id("durationAimMax")).clear();
 driver.findElement(By.id("durationAimMax")).sendKeys(AAMx);
 }
 else if(Mtype=="latency")
 {
	 driver.findElement(By.id("latencyAimMin")).clear();
	 driver.findElement(By.id("latencyAimMin")).sendKeys(AAMi);
	 driver.findElement(By.id("latencyAimMax")).clear();
	 driver.findElement(By.id("latencyAimMax")).sendKeys(AAMx); 
 }else if(Mtype=="frequency")
 {
	 driver.findElement(By.id("recordFloor")).clear();
	 driver.findElement(By.id("recordFloor")).sendKeys(Dct);
	 driver.findElement(By.name("correctAimMin")).clear();
	 driver.findElement(By.name("correctAimMin")).sendKeys(AAMi);
	 driver.findElement(By.name("correctAimMax")).clear();
	 driver.findElement(By.name("correctAimMax")).sendKeys(AAMx);
	 driver.findElement(By.name("incorrectAimMin")).clear();
	 driver.findElement(By.name("incorrectAimMin")).sendKeys(ADMi);
	 driver.findElement(By.name("incorrectAimMax")).clear();
	 driver.findElement(By.name("incorrectAimMax")).sendKeys(ADMx);
 }else
 {
	 driver.findElement(By.name("correctAimMin")).clear();
	 driver.findElement(By.name("correctAimMin")).sendKeys(AAMi);
	 driver.findElement(By.name("correctAimMax")).clear();
	 driver.findElement(By.name("correctAimMax")).sendKeys(AAMx);
	 driver.findElement(By.name("incorrectAimMin")).clear();
	 driver.findElement(By.name("incorrectAimMin")).sendKeys(ADMi);
	 driver.findElement(By.name("incorrectAimMax")).clear();
	 driver.findElement(By.name("incorrectAimMax")).sendKeys(ADMx);
 }



 driver.findElement(By.name("daysToAim")).clear();
 driver.findElement(By.name("daysToAim")).sendKeys(Daim);
 //------------------------------------------------------------------------------------------------------------------
    

//Section of Aggregate type
 
    if((Fir.equals(tru)) && (Med.equals(fal)) && (Geo.equals(fal)) && (Min.equals(fal)) && (Max.equals(fal)) && (Sum.equals(fal)) && (Sta.equals(fal)))
    {
    	 driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[4]/div/div/div/div/label[1]")).click();
    	 Aggtype="first";
    }
    else if((Fir.equals(fal)) && (Med.equals(tru)) && (Geo.equals(fal)) && (Min.equals(fal)) && (Max.equals(fal)) && (Sum.equals(fal)) && (Sta.equals(fal)))
    {
    	 driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[4]/div/div/div/div/label[2]")).click();
    	 Aggtype="median";
    }
    else if((Fir.equals(fal)) && (Med.equals(fal)) && (Geo.equals(tru)) && (Min.equals(fal)) && (Max.equals(fal)) && (Sum.equals(fal)) && (Sta.equals(fal)))
    {
    	 driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[4]/div/div/div/div/label[3]")).click();
    	 Aggtype="geoMean";
    }
    else if((Fir.equals(fal)) && (Med.equals(fal)) && (Geo.equals(fal)) && (Min.equals(tru)) && (Max.equals(fal)) && (Sum.equals(fal)) && (Sta.equals(fal)))
    {
    	 driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[4]/div/div/div/div/label[4]")).click();
    	 Aggtype="min";
    }
    else if((Fir.equals(fal)) && (Med.equals(fal)) && (Geo.equals(fal)) && (Min.equals(fal)) && (Max.equals(tru)) && (Sum.equals(fal)) && (Sta.equals(fal)))
    {
    	 driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[4]/div/div/div/div/label[5]")).click();
    	 Aggtype="max";
    }
    else if((Fir.equals(fal)) && (Med.equals(fal)) && (Geo.equals(fal)) && (Min.equals(fal)) && (Max.equals(fal)) && (Sum.equals(tru)) && (Sta.equals(fal)))
    {
    	 driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[4]/div/div/div/div/label[6]")).click();
    	 Aggtype="summative";
    }
    else if((Fir.equals(fal)) && (Med.equals(fal)) && (Geo.equals(fal)) && (Min.equals(fal)) && (Max.equals(fal)) && (Sum.equals(fal)) && (Sta.equals(tru)))
    {
    	 driver.findElement(By.xpath("//*[@id='createPinpointForm']/div/div[2]/div[4]/div/div/div/div/label[7]")).click();
    	 Aggtype="stacked";
    }
    else
    {
    	System.out.println("Wrong Aggregate input in the excel sheet");
    }

    driver.findElement(By.cssSelector(".btn.btn-primary.btn-next")).click();
    driver.findElement(By.cssSelector(".btn.btn-primary.btn-next")).click();
   //------------------ handle assertion -------------------
	    try {
	      assertEquals(Mtype, driver.findElement(By.id("type")).getText());
	      assertEquals(Mrec, driver.findElement(By.id("recurrence")).getText());
	      assertEquals(Tar, driver.findElement(By.id("target")).getText());
	      assertEquals(Aggtype, driver.findElement(By.id("aggregateType")).getText());
	      assertEquals(Action, driver.findElement(By.id("actionVerb")).getText());
	      assertEquals(Noun, driver.findElement(By.id("noun")).getText());
	      assertEquals(Context, driver.findElement(By.id("context")).getText());
	      assertEquals(Daim, driver.findElement(By.id("daysToAim")).getText());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	   
	    if(Mtype=="duration" || Mtype=="latency")
	    {
	    	try {
	    
	    		assertEquals(AAMi, driver.findElement(By.id("correctAimMin")).getText());
	    		assertEquals(AAMx, driver.findElement(By.id("correctAimMax")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	    }else
	    {
	    	 try {
	    		 if(Mtype=="frequence")
	    		 {
	    			 assertEquals(Dct, driver.findElement(By.xpath("//*[@id='recordFloorTR']/td[2]")).getText());
	    		 }
	    		
	 	       assertEquals(AAMi, driver.findElement(By.id("correctAimMin")).getText());
	 	       assertEquals(AAMx, driver.findElement(By.id("correctAimMax")).getText());
	 	       assertEquals(ADMi, driver.findElement(By.id("incorrectAimMin")).getText());
	 	       assertEquals(ADMx, driver.findElement(By.id("incorrectAimMax")).getText());
	 	      } catch (Error e) {
	 	        verificationErrors.append(e.toString());
	 	      }
	    	
	    }
	    
	    driver.findElement(By.cssSelector(".btn.btn-next.btn-success")).click();
	    
	    Thread.sleep(3000);
	    int CountPin= driver.findElements(By.xpath("//*[@id='app-main']/div[2]/div[2]/div[2]/div")).size();

	    try{  
	    	  assertEquals("-"+PinName, driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[2]/div[2]/div["+CountPin+"]/a/div/div/div[1]/h2")).getText());
	    }catch (Error e)
	    {
	    	verificationErrors.append(e.toString());
	    }
	
	 //   driver.quit();
  } //for loop end
  } // end of test
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
  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
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







