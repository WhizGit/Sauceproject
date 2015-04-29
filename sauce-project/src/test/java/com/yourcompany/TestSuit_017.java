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
public class TestSuit_017 implements SauceOnDemandSessionIdProvider {
	
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String Tar, Mtype, Mrec, Aggtype, Email,Fullname,Orgname, Password;
	private String Pathofexcel ="./src/test/java/com/yourcompany/dataexcel.xlsx"; // path of your excel file
	/*String[][] SauceInfo = GetValue(Pathofexcel,"signup",11);
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
	
    public TestSuit_017(String os, String version, String browser) {
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
        capabilities.setCapability("name", "Chartlytics Test_chartfrequencyvalue");
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
	 @Test 
	//--------------------------------------------------------------------------------------------
	 public void VerifyFrequency() throws Exception {
 String[][] getit = GetValue(Pathofexcel,"signup",2);
 baseUrl = getit[0][0]; 
  Email= getit[0][2];  
  Password=getit[0][3];

    driver.get("http://"+ baseUrl + "/signin");
    driver.manage().window().maximize();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);

    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
    
  String[][] getits = GetValue(Pathofexcel,"frequencytest",2);
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
    Thread.sleep(3000);
    driver.findElement(By.id("fifty")).click();
    Thread.sleep(3000);
    driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
    String Per= driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).getText();
    assertEquals(Per,PerName);
    System.out.println("//---------------Create Performer--------------//");
    System.out.println(Per);
 
//-------------------------------------------------Create Pinpoint--------------------------------------------------------------------//
    driver.findElement(By.cssSelector("a[title=\"Pinpoints+\"] > span")).click();
    //String[][] datas = GetValue("./src/dataexcel.xlsx","assignpinpoint",8);
    String[][] datas = GetValue(Pathofexcel,"frequencytest",8);
	
	
	//String SIn = datas [0][0];
	//String Pout = datas [0][1];
	String Action = datas [0][2];
	String Noun = datas [0][3];
	String Context = datas [0][4];
	String Dct = datas [0][5];
	String AAMi = datas [0][6];
	String AAMx = datas [0][7];
	String ADMi = datas [0][8];
	String ADMx = datas [0][9];
    String Daim  = datas [0][10];
	
	driver.findElement(By.id("createPinpoint")).click();
//---------------------------Input value in the "Pinpont" Tab-----------------------------------------------------------//	
	 driver.findElement(By.id("actionVerb")).clear();
	    driver.findElement(By.id("actionVerb")).sendKeys(Action);
	    
	    // clear and enter object
	    driver.findElement(By.id("objectNoun")).clear();
	    driver.findElement(By.id("objectNoun")).sendKeys(Noun);
	    
	    //Enter and clear context
	    driver.findElement(By.id("context")).clear();
	    driver.findElement(By.id("context")).sendKeys(Context);
	    
	    driver.findElement(By.cssSelector(".btn.btn-primary.btn-next")).click();

//------------------------------------------------------------Select the combination for "Set Default" tab--------------------------------------------------------------------//	    
	    String[][] data = GetValue(Pathofexcel,"frequencytest",11);
	    		
		String Acc = data [0][0];
		System.out.print(Acc);
		String Dec = data [0][1];
		System.out.print(Dec);
		String Frq = data [0][2];
		System.out.print(Frq);
		String Dur = data [0][3];
		System.out.print(Dur);
		String Lat = data [0][4];
		System.out.print(Lat);
		String Cou = data [0][5];
		System.out.print(Cou);
		String Dai = data [0][6];
		System.out.print(Dai);
		String Wee = data [0][7];
		System.out.print(Wee);
		String Mon = data [0][8];
		System.out.print(Mon);
		String Yr = data [0][9];
		System.out.print(Yr);
		String Fir = data [0][10];
		System.out.print(Fir);
		String Med = data [0][11];
		System.out.print(Med);
		String Geo = data [0][12];
		System.out.print(Geo);
		String Min = data [0][13];
		System.out.print(Min);
		String Max = data [0][14];
		System.out.print(Max);
		String Sum = data [0][15];
		System.out.print(Sum);
		String Sta = data [0][16];
		System.out.print(Sta);
		 String tru = "Y";
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
//------------------------------------------------------------Input value in "Set Default Input field"------------------------------------------//
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
//---------------------------------------------------------Click on the "Next" buttons-----------------------------------------------------//		    
		    driver.findElement(By.cssSelector(".btn.btn-primary.btn-next")).click();
		    Thread.sleep(5000);
		    driver.findElement(By.cssSelector(".btn.btn-primary.btn-next")).click();
		    Thread.sleep(5000);
//----------------------------------------------------------Verification of value in "Review Summary"------------------------------------//
		 //   String pinpointNm = driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[2]/h5[1]/div/div[1]/table/tbody/tr[1]/td[2]/span")).getText();
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
			    Thread.sleep(5000);
			    driver.findElement(By.cssSelector(".btn.btn-next.btn-success")).click();
			    Thread.sleep(5000);
//-------------------------------------------------------------------------"Assign pinpoint" to perforemer-------------------------------------//
			    String[][] pinpnt = GetValue(Pathofexcel,"frequencytest",15);
	    		String pinpoint = pinpnt[0][0]; 
	    		driver.findElement(By.cssSelector("a[title=\"Performers\"] > span")).click();
	    		Thread.sleep(5000);
			    driver.findElement(By.id("fifty")).click();
			    Thread.sleep(3000);
			    driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
			    Thread.sleep(3000);
			    driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).click();
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("//a[contains(text(),'Pinpoints')]")).click();
			    Thread.sleep(3000);
			    driver.findElement(By.id("launchAssignPinpoint")).click();
			    Thread.sleep(3000);
			    driver.findElement(By.xpath("//form/div/div[1]/div/div/div/a/span[2]/b")).click();
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("html/body/div[9]/div/input")).sendKeys(pinpoint);
			    Thread.sleep(5000);
			    driver.findElement(By.xpath("html/body/div[9]/ul/li/div")).click();
			    Thread.sleep(3000);
			    String AssignPinpoint=driver.findElement(By.xpath("//form/div[1]/div/div/a/div/div[1]/div/h2")).getText();
			 
			    driver.findElement(By.id("assignPinpoint")).click();
//----------------------------------------Enter value in Acceleration and Deceleration in worksheet-------------------------
			    Thread.sleep(5000);
			    driver.findElement(By.linkText("Worksheet")).click();
			    
			    //Get value methode
			    //------------------------------
			    File excel = new File("./src/dataexcel.xlsx");
			   	FileInputStream fis = new FileInputStream(excel);
			   	@SuppressWarnings("resource")
			   	XSSFWorkbook wb = new XSSFWorkbook(fis);
			   	
			   	XSSFSheet ws = wb.getSheet("frequencytest");
			    for(int y=19;y<=23;y++)// for loop for exceute no of row
			    {
		
				int colNum = ws.getRow(19).getLastCellNum();
				String[][] values = new String[1][colNum];
				for (int i=1;i<=colNum-3;i++)// for loop for exceute no of coloum
				{
					XSSFRow row = ws.getRow(y);
					XSSFCell cell = row.getCell(i);
					values[0][i]= cellToString(cell);	

				}
				
				String CountTime = values [0][1];
				System.out.println(CountTime);
				String Accs = values [0][2];
				System.out.println(Accs);
				String Decs = values [0][3];
				System.out.println(Decs);
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
				 acc = Integer.parseInt(Accs);
				 Acce = (int) Math.round (acc * CT); // Acceleration frequency
				System.out.println(Acce);
				 dcc = Integer.parseInt(Decs);
				 Dcel = (int) Math.round(dcc * CT); // Deceleration frequency
				System.out.println(Dcel);
				}else
				{
					float CT = (Counttym/60);
					System.out.println("count time change to min"+CT);
					 acc = Integer.parseInt(Accs);
					 Acce = Math.round(acc / CT); // Acceleration frequency
					System.out.println(Acce);
					 dcc = Integer.parseInt(Decs);
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
			    driver.findElement(By.name("correct")).sendKeys(Accs);
			    driver.findElement(By.name("incorrect")).click();
			    driver.findElement(By.name("incorrect")).clear();
			    driver.findElement(By.name("incorrect")).sendKeys(Decs);
			    driver.findElement(By.xpath("//div[@id='Daily']/div/div/div/div[2]/div/div[4]/button[2]")).click();
				
				 try {
				   //   assertEquals("1:21 AM", driver.findElement(By.cssSelector("td.measured")).getText());
					//    assertEquals("0:02:00", driver.findElement(By.cssSelector("td.recordFloor")).getText());
					 Thread.sleep(3000);
						assertEquals(Accs, driver.findElement(By.cssSelector("td.correct")).getText());
						 assertEquals(Decs, driver.findElement(By.cssSelector("td.incorrect")).getText());
						 assertEquals(Acce+"  "+Dcel+" ", driver.findElement(By.xpath("//*[@id='pinpoint0']/div/div/div[2]/table/tbody/tr[1]/td[5]")).getText());
						 
				    } catch (Error e) {
				      verificationErrors.append(e.toString());
				    }
			  }//end of for loop
			    
			    //----------------------------------
			    
			    
			    
  }// end of test
  
	
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








