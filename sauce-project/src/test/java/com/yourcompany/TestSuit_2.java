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

/**
 * Demonstrates how to write a JUnit test that runs tests against Sauce Labs using multiple browsers in parallel.
 * <p/>
 * The test also includes the {@link SauceOnDemandTestWatcher} which will invoke the Sauce REST API to mark
 * the test as passed or failed.
 *
 * @author Ross Rowe
 */
@RunWith(ConcurrentParameterized.class)
public class TestSuit_2 implements SauceOnDemandSessionIdProvider {

  public String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  public String Tar, Mtype, Mrec, Aggtype, Email, Password, Fullname, Orgname;
  private String Pathofexcel ="./src/test/java/com/yourcompany/dataexcel.xlsx"; // path of your excel file

    /**
     * Constructs a {@link SauceOnDemandAuthentication} instance using the supplied user name/access key.  To use the authentication
     * supplied by environment variables or from an external file, use the no-arg {@link SauceOnDemandAuthentication} constructor.
     */
    public SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication("vardhanvarun", "9efa0336-55b5-4e46-9802-ca8f825a55c6");

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
    public TestSuit_2(String os, String version, String browser) {
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
        browsers.add(new String[]{"Windows 7", "37", "firefox"});
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
        capabilities.setCapability("name", "Chartlytics TestSuit_2");
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

    /**
     * Runs a simple test verifying the title of the amazon.com homepage.
     * @throws Exception
     */
    @Test // Test 1 Create pinpoint
    public void test1_createpinpoint() throws Exception {
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
	String[][] data = GetValue(Pathofexcel,"ReviewSummary",c);
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
	String[][] datas = GetValue(Pathofexcel,"Pinpoint",k);	
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

		
    //click on the Create Pinpoint
	Thread.sleep(2000);
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
   @Test // Test 2 Create Performer
   public void test2_createperformer() throws Exception {
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
		  try {
			  Thread.sleep(10000);
		      assertEquals(PerName , driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).getText());
		      assertEquals("Age: "+age+"   "+"Grade: "+Grade, driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/p")).getText());
		      System.out.println("successfully created Performer " + PerName );
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
	} //end of else
  } // of for loop
  }// end of test
  
  @Test // Test 3 Delete performer
   public void test3_deleteperformer() throws Exception {
	 String[][] Per = GetValue(Pathofexcel,"performer",8);
	 String PerName = Per[0][0];
	 String Age = Per[0][1];
	 String Gen = Per[0][2];
	 String Grade = Per[0][3];
    driver.get("http://"+baseUrl + "/");
    driver.manage().window().maximize();
    driver.findElement(By.linkText("Log in")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("a[title=\"Performers\"] > span")).click();
    driver.findElement(By.id("createPerformer")).click();
    driver.findElement(By.name("performerName")).clear();
    driver.findElement(By.name("performerName")).sendKeys(PerName);
    driver.findElement(By.name("age")).clear();
    driver.findElement(By.name("age")).sendKeys(Age);
    if(Gen.equals("M"))
    {
    driver.findElement(By.id("male")).click();
    }
    else
    {
    	driver.findElement(By.id("female")).click();
    }
    driver.findElement(By.name("grade")).clear();
    driver.findElement(By.name("grade")).sendKeys(Grade);
    driver.findElement(By.cssSelector("p.pull-right > button.btn.btn-primary")).click();
    driver.findElement(By.id("fifty")).click();
    driver.findElement(By.xpath("//div[@id='app-main']/div[2]/div/div/div[4]/label[4]")).click();
    driver.findElement(By.cssSelector("h2.male")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Settings')]")).click();
    driver.findElement(By.id("deletePerformer")).click();
    Thread.sleep(10000);
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
  
  }
  @Ignore
  @Test  // Test 4 Performer sort
   public void test4_performersort() throws Exception {
    driver.get("http://"+baseUrl + "/signin");
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    driver.findElement(By.cssSelector("a[title=\"Performers\"] > span")).click();
    // some variable intialisation which is used in if-else loop
	String tru = "Y";
	String fals= "N";	
	String item =driver.findElement(By.xpath("//*[@id='results-paging']/div/div[1]/button[2]")).getText();
	String del = " ";
	String[] temps= item.split(del);
	int items =Integer.parseInt(temps[3]);
	List<String> expected = new ArrayList<String>();
    List<String> actual= new ArrayList<String>();
    String[] arr = new String[items];
  for(int y=14;y<=17;y++){
 		 String[][] data = GetValue(Pathofexcel,"performer",y);
 		 
 		System.out.println("Input from Orgdata sheet(Perfomer_sort) : ");
 		String Arc = data [0][0];
 		System.out.print(Arc);
 		String Grid = data [0][1];
 		System.out.print(Grid);
 		String Table = data [0][2];
 		System.out.print(Table);
 		String NmAtoZ = data [0][3];
 		System.out.print(NmAtoZ);
 		String NmZtoA = data [0][4];
 		System.out.print(NmZtoA);
 		String Id1to9 = data [0][5];
 		System.out.print(Id1to9);
 		String Id9to1 = data [0][6];
 		System.out.print(Id9to1);
 		String Itemperpage = data [0][7];
 		System.out.print(Itemperpage);
    if(Table.equals(tru) && Grid.equals(fals))
    {
    	driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[3]/label[2]")).click();
    	Thread.sleep(2000);
    	 if(NmAtoZ.equals(tru) && NmZtoA.equals(fals))
    		{
    	    
    	    for(int i=0; i<items; i++)
    	    {
    	    	int a = 1+i;
    	    arr[i] = driver.findElement(By.xpath("//*[@id='results-rows']/tr["+a+"]/td[1]")).getText();
    	    actual.add(arr[i]);
    	    }
    	  Collections.sort(actual);
    		  System.out.println(actual);
    	  

    		driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[4]/label[1]")).click();
//    		System.out.println("click on name a to z ");
    		  String[] arrs = new String[arr.length];
    		    for(int i=0; i<arr.length; i++)
    		    {
    		    	int a = 1+i;
    		    arrs[i] = driver.findElement(By.xpath("//*[@id='results-rows']/tr["+a+"]/td[1]")).getText();
    		    expected.add(arrs[i]);
    		    }
    		    //xpath div[9] is not in page
    		   
    		    assertThat(actual, is(expected));
    		    expected.clear();
    		    actual.clear();
    			}	
    	    else//(NmZtoA.equals(tru) && NmAtoZ.equals(fals) )
    	    {	    
    	    	//System.out.println("hi"+actual);
    	    	  for(int i=0; i<items; i++)
    	    	    {
    	    	    	int a = 1+i;
    	    	    arr[i] = driver.findElement(By.xpath("//*[@id='results-rows']/tr["+a+"]/td[1]")).getText();
    	    	    actual.add(arr[i]);
    	    	    }
    	    	  //Collections.sort(actual, Collections.reverseOrder(actual));
    	    	    Collections.reverse(actual);
    	    	  System.out.println(actual);

    	    		driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[4]/label[2]")).click();
//    	    		System.out.println("click on name z to a ");
    	    		  String[] arrs = new String[arr.length];
    	    		    for(int i=0; i<arr.length; i++)
    	    		    {
    	    		    	int a = 1+i;
    	    		    arrs[i] = driver.findElement(By.xpath("//*[@id='results-rows']/tr["+a+"]/td[1]")).getText();
    	    		    expected.add(arrs[i]);
    	    		    }
    	    		 
    	    		    assertThat(actual, is(expected));
    	    		    expected.clear();
    	    		    actual.clear();
    	    }//end of if loop for table
    }
    else
    {
    //if NameAtoZ button is Y in the excel sheet 
    if(NmAtoZ.equals(tru))
	{
    
    for(int i=0; i<items; i++)
    {
    	int a = 1+i;
    arr[i] = driver.findElement(By.xpath("//*[@id='grid-view']/div["+a+"]/a/div/div[2]/div[1]/h2")).getText();
    actual.add(arr[i]);
    }
  Collections.sort(actual);
  

	driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[4]/label[1]")).click();
//	System.out.println("click on name a to z ");
	  String[] arrs = new String[arr.length];
	    for(int i=0; i<arr.length; i++)
	    {
	    	int a = 1+i;
	    arrs[i] = driver.findElement(By.xpath("//*[@id='grid-view']/div["+a+"]/a/div/div[2]/div[1]/h2")).getText();
	    expected.add(arrs[i]);
	    }
	    //xpath div[9] is not in page
	   
	    assertThat(actual, is(expected));
	    actual.clear();
	    expected.clear();
		}	
    else if(NmZtoA.equals(tru))
    {	    
    	    for(int i=0; i<items; i++)
    	    {
    	    	int a = 1+i;
    	    arr[i] = driver.findElement(By.xpath("//*[@id='grid-view']/div["+a+"]/a/div/div[2]/div[1]/h2")).getText();
    	    actual.add(arr[i]);
    	    }
    	  Collections.sort(actual, Collections.reverseOrder());
    	  System.out.println("other loop");

    		driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[4]/label[2]")).click();
//    		System.out.println("click on name a to z ");
    		  String[] arrs = new String[10];
    		    for(int i=0; i<items; i++)
    		    {
    		    	int a = 1+i;
    		    arrs[i] = driver.findElement(By.xpath("//*[@id='grid-view']/div["+a+"]/a/div/div[2]/div[1]/h2")).getText();
    		    expected.add(arrs[i]);
    		    }
    		 
    		    assertThat(actual, is(expected));
    		    actual.clear();
    		    expected.clear();
    }//end of else loop for grid
    else
    	{
    	
    		System.out.println("not in loop");
    	}
    }
	}// end of for loop
 }// end of test 
   @Test // Test 5 Archive Performer
  public void test5_archiveperformer() throws Exception {
    driver.get("http://"+ baseUrl + "/signin");
    driver.manage().window().maximize();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);

    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
    
    String[][] getits = GetValue(Pathofexcel,"performer",9);
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
    try {
     Thread.sleep(10000);
        assertEquals(PerName , driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).getText());
        assertEquals("Age: "+age+"   "+"Grade: "+Grade, driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/p")).getText());
        System.out.println("//----------Successfully create Performer----------//" );
        System.out.println( PerName );
      } catch (Error e) {
        verificationErrors.append(e.toString());
      }
 } //end of else
 
//-------------------------------------------------ARCHIVE PERFORMER---------------------------------------------------------------------------//
    
    driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Settings')]")).click();
    driver.findElement(By.id("archive")).click();
    Thread.sleep(10000);
    driver.findElement(By.xpath("(//button[@type='submit'])[3]")).click();
    System.out.println("//----------Successfullly archived the performer----------//");
    
//-------------------------------------------------VERIFY ARCHIVED PERFORMER--------------------------------------------------------------------//    
    driver.findElement(By.xpath("//*[@id='app-sidebar']/ul/li[4]/a")).click();
	Thread.sleep(5000);
    driver.findElement(By.id("fifty")).click();
    driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[4]/label[4]")).click();
    driver.findElement(By.id("showArchived")).click();
   String archper = driver.findElement(By.xpath(".//*[@id='grid-view']/div[1]")).getAttribute("data-archived");
   System.out.println("//----------Shows the message performer is archived or not archived----------//");
    
    if(archper.equals("true"))
    		{
    	
    	   System.out.println("Performer is Archived");
    	    }
    else
            {
    	   System.out.println("Performer is Not Archived");
    	    }
  }// end of test
  	@Test
	  public void test6_CreateGroup() throws Exception {
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
    String[][] creatgroup = GetValue(Pathofexcel,"group",2);
	String grpname = creatgroup[0][0]; 
	String grpdescription = creatgroup[0][1];  
	String grplocation= creatgroup[0][2];
	driver.findElement(By.id("newGroup")).click();
    driver.findElement(By.name("groupName")).clear();
    driver.findElement(By.name("groupName")).sendKeys(grpname);
    driver.findElement(By.cssSelector("input[name=\"description\"]")).clear();
    driver.findElement(By.cssSelector("input[name=\"description\"]")).sendKeys(grpdescription);
    driver.findElement(By.name("location")).clear();
    driver.findElement(By.name("location")).sendKeys(grplocation);
    driver.findElement(By.id("createGroup")).click();
    Thread.sleep(10000);
    driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
    String groupname = driver.findElement(By.xpath(".//*[@id='grid-view']/div/a/div/div[2]/div[1]/h2")).getText();
    assertEquals(groupname,grpname);
    System.out.println("//-----------------------Create The Group------------------------------------//");
    System.out.println(groupname);
  }
	@Test
	 public void test7_CreateGroupWithUser() throws Exception {
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
    driver.findElement(By.xpath("//form/div[1]/div/div/a/span[1]")).click();
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
  @Test
   public void test8_DeleteGroupUser() throws Exception {
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
	driver.findElement(By.id("newGroup")).click();
    String[][] creatgroup = GetValue(Pathofexcel,"group",28);
	String grpname = creatgroup[0][0]; 
	String grpdescription = creatgroup[0][1];  
	String grplocation= creatgroup[0][2];
	String usrname= creatgroup[0][3];
	String usrrole= creatgroup[0][4];
	driver.findElement(By.name("groupName")).clear();
    driver.findElement(By.name("groupName")).sendKeys(grpname);
    driver.findElement(By.cssSelector("input[name=\"description\"]")).clear();
    driver.findElement(By.cssSelector("input[name=\"description\"]")).sendKeys(grpdescription);
    driver.findElement(By.name("location")).clear();
    driver.findElement(By.name("location")).sendKeys(grplocation);
    driver.findElement(By.id("createGroup")).click();
    Thread.sleep(10000);
    driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
    String groupname = driver.findElement(By.xpath(".//*[@id='grid-view']/div/a/div/div[2]/div[1]/h2")).getText();
    assertEquals(groupname,grpname);
    System.out.println("//-----------------------Create The Group------------------------------------//");
    System.out.println(groupname);
//---------------------------------------------Add User To Group through Access Control--------------------------------------------------//    
    driver.findElement(By.xpath(".//*[@id='grid-view']/div/a/div/div[2]/div[1]/h2")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Access Control')]")).click();
    driver.findElement(By.id("addUser")).click();
    driver.findElement(By.xpath("//form/div[1]/div/div/a/span[1]")).click(); 
    driver.findElement(By.xpath("html/body/div[6]/div/input")).sendKeys(usrname);
    driver.findElement(By.xpath("html/body/div[6]/ul/li/div")).click();
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
//------------------------------------------------------Delete Group user--------------------------------------------------------------------//    
    driver.findElement(By.xpath("//button[@type='button']")).click();
    
    System.out.println("//---------Delete the added user------------//");
    
  }
  @Test
    public void test9_AddUSerGroupwithAccessControl() throws Exception {
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
    String[][] creatgroup = GetValue(Pathofexcel,"group",14);
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
    driver.findElement(By.id("createGroup")).click();
    Thread.sleep(10000);
    driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
    String groupname = driver.findElement(By.xpath(".//*[@id='grid-view']/div/a/div/div[2]/div[1]/h2")).getText();
    assertEquals(groupname,grpname);
    System.out.println("//-----------------------Create The Group------------------------------------//");
    System.out.println(groupname);
//---------------------------------------------Add User To Group through Access Control--------------------------------------------------//    
    driver.findElement(By.xpath(".//*[@id='grid-view']/div/a/div/div[2]/div[1]/h2")).click();
    driver.findElement(By.xpath("//a[contains(text(),'Access Control')]")).click();
    driver.findElement(By.id("addUser")).click();
    driver.findElement(By.xpath("//form/div[1]/div/div/a/span[1]")).click(); 
    driver.findElement(By.xpath("html/body/div[6]/div/input")).sendKeys(usrname);
    driver.findElement(By.xpath("html/body/div[6]/ul/li/div")).click();
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
   
    
  }
   public void test10_UpdateGroupProfile() throws Exception {
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
    String[][] creatgroup = GetValue(Pathofexcel,"group",18);
	String grpname = creatgroup[0][0]; 
	String grpdescription = creatgroup[0][1];  
	String grplocation= creatgroup[0][2];
	driver.findElement(By.id("newGroup")).click();
    driver.findElement(By.name("groupName")).clear();
    driver.findElement(By.name("groupName")).sendKeys(grpname);
    driver.findElement(By.cssSelector("input[name=\"description\"]")).clear();
    driver.findElement(By.cssSelector("input[name=\"description\"]")).sendKeys(grpdescription);
    driver.findElement(By.name("location")).clear();
    driver.findElement(By.name("location")).sendKeys(grplocation);
    driver.findElement(By.id("createGroup")).click();
    Thread.sleep(10000);
    driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
    String groupname = driver.findElement(By.xpath(".//*[@id='grid-view']/div/a/div/div[2]/div[1]/h2")).getText();
    assertEquals(groupname,grpname);
    System.out.println("//-----------------------Create The Group------------------------------------//");
    System.out.println("Name="+groupname);
    System.out.println("Description="+grpdescription);
    System.out.println("Location="+grplocation);
//-------------------------------------------------Update the Group Profile--------------------------------------------------------//    
    driver.findElement(By.xpath(".//*[@id='grid-view']/div/a/div/div[2]/div[1]/h2")).click();
    driver.findElement(By.xpath("(//a[contains(text(),'Profile')])[3]")).click();
    String[][] updtgroup = GetValue(Pathofexcel,"group",22);
   	String updtname = updtgroup[0][0]; 
   	String updtdescription = updtgroup[0][1];  
   	String updtlocation= updtgroup[0][2];
    driver.findElement(By.name("groupName")).clear();
    driver.findElement(By.name("groupName")).sendKeys(updtname);
    driver.findElement(By.cssSelector("input[name=\"description\"]")).clear();
    driver.findElement(By.cssSelector("input[name=\"description\"]")).sendKeys(updtdescription);
    driver.findElement(By.name("location")).clear();
    driver.findElement(By.name("location")).sendKeys(updtlocation);
    driver.findElement(By.id("saveGroupProfile")).click();
    driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
    String editname = driver.findElement(By.xpath(".//*[@id='grid-view']/div/a/div/div[2]/div[1]/h2")).getText();
    assertEquals(editname,updtname);
    System.out.println("//-----------------------Update Group------------------------------------//");
    System.out.println("Name="+editname);
    System.out.println("Description="+updtdescription);
    System.out.println("Location="+updtlocation);
    
  }
  
  
 
	
 
  // Methode GetValue is used to read the data from the excel sheet
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
