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
import java.util.*;
import java.util.LinkedList;
import static org.junit.Assert.assertEquals;
import com.saucelabs.common.SauceOnDemandSessionIdProvider;
import org.junit.Rule;
//------------------------
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

//import org.apache.poi.poi.3/.11.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//-------------------------------------------------------------


@RunWith(ConcurrentParameterized.class)
public class TestSuit_002 implements SauceOnDemandSessionIdProvider {
	
  public String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public String Tar, Mtype, Mrec, Aggtype, Email, Password, Fullname, Orgname;
  private String Pathofexcel ="./src/test/java/com/yourcompany/dataexcel.xlsx"; // path of your excel file
	/*String[][] SauceInfo = GetValue(Pathofexcel,"signup",11);
	String SauceUser = SauceInfo[0][0];
	String SauceAccessKey = SauceInfo[0][1];*/
   
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("TestingAuto", "11d6c831-6e67-4978-936f-3709d55aa962");

    
    @Rule
    public SauceOnDemandTestWatcher resultReportingTestWatcher = new SauceOnDemandTestWatcher(this, authentication);
    private String browser;
    private String os;
    private String version;
    private String sessionId;
    private WebDriver driver;

    public TestSuit_002(String os, String version, String browser) {
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
        capabilities.setCapability("name", "Chartlytics Test_CreatePinpoint");
        this.driver = new RemoteWebDriver(
                new URL("http://" + authentication.getUsername() + ":" + authentication.getAccessKey() + "@ondemand.saucelabs.com:80/wd/hub"),
                capabilities);
        this.sessionId = (((RemoteWebDriver) driver).getSessionId()).toString();
		
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    /**
     * Runs a simple test verifying the title of the amazon.com homepage.
     * @throws Exception
     */
	
    @Test // Test 1 Create pinpoint
    public void CreatePinpoint() throws Exception {
  String[][] getit = CommonMethod.GetValue(Pathofexcel,"signup",2);
	baseUrl = getit[0][0]; 
	 Email= getit[0][2];  
	 Password=getit[0][3];
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    CommonMethod.SignIn(driver, baseUrl, Email, Password);
    // click on the pinpoint on the dashboard
    driver.findElement(By.cssSelector("a[title=\"Pinpoints+\"] > span")).click();
	  for(int c=5; c<10; c++) // we can start test case from testcase 1
		{
	String[][] data = CommonMethod.GetValue(Pathofexcel,"ReviewSummary",c);
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


	//--------------------- read data for set default using excel sheet-----------------------
	
int k = 0;
k=c-1;
System.out.println(k);

	String[][] datas = CommonMethod.GetValue(Pathofexcel,"Pinpoint",k);

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
	System.out.println("Create pinpoint:"+Action);
		
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
    //selection for pinpoint goal 
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
	    
	      assertEquals(Mtype, driver.findElement(By.id("type")).getText());
	      assertEquals(Mrec, driver.findElement(By.id("recurrence")).getText());
	      assertEquals(Tar, driver.findElement(By.id("target")).getText());
	      assertEquals(Aggtype, driver.findElement(By.id("aggregateType")).getText());
	      assertEquals(Action, driver.findElement(By.id("actionVerb")).getText());
	      assertEquals(Noun, driver.findElement(By.id("noun")).getText());
	      assertEquals(Context, driver.findElement(By.id("context")).getText());
	      assertEquals(Daim, driver.findElement(By.id("daysToAim")).getText());
	   
	   
	    if(Mtype=="duration" || Mtype=="latency")
	    {
	    	
	    
	    		assertEquals(AAMi, driver.findElement(By.id("correctAimMin")).getText());
	    		assertEquals(AAMx, driver.findElement(By.id("correctAimMax")).getText());
	    
	    }else
	    {
	    
	    		 if(Mtype=="frequence")
	    		 {
	    			 assertEquals(Dct, driver.findElement(By.xpath("//*[@id='recordFloorTR']/td[2]")).getText());
	    		 }
	    		
	 	       assertEquals(AAMi, driver.findElement(By.id("correctAimMin")).getText());
	 	       assertEquals(AAMx, driver.findElement(By.id("correctAimMax")).getText());
	 	       assertEquals(ADMi, driver.findElement(By.id("incorrectAimMin")).getText());
	 	       assertEquals(ADMx, driver.findElement(By.id("incorrectAimMax")).getText());
	 	    
	    	
	    }
	    driver.findElement(By.cssSelector(".btn.btn-next.btn-success")).click();
	    Thread.sleep(2000);
	    int CountPin= driver.findElements(By.xpath("//*[@id='app-main']/div[2]/div[2]/div[2]/div")).size();

	    
	    	  assertEquals(PinName, driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[2]/div[2]/div["+CountPin+"]/a/div/div/div[1]/h2")).getText());
	
	 if(c==16)
	 {
		 driver.quit();
	 }
  } //for loop end
  } // end of test
   
 
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
