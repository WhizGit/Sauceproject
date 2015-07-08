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
public class TestSuit_021 implements SauceOnDemandSessionIdProvider {
	
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
	
    public TestSuit_021(String os, String version, String browser) {
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
        capabilities.setCapability("name", "Chartlytics Test_VerifyAggreateValue");
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
	 public void Verify_Aggregate_Value() throws Exception {
    driver.get(baseUrl);
    driver.findElement(By.linkText("Log in")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);
    driver.findElement(By.xpath("//button[@type='submit']")).click();
	Thread.sleep(3000);
    driver.findElement(By.cssSelector("a[title=\"Performers\"] > span")).click();
    Thread.sleep(3000);
    driver.findElement(By.id("fifty")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[4]/label[4]")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div")).click();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//*[@id='Daily']/div[1]/div[1]/div/div[2]/div/div[4]/a")).click();
    // Get hold of static object underneath which you have your dynamic content like rows.
    WebElement tablebody= driver.findElement(By.xpath("/html/body/div[3]/div[2]/div[2]/div/div[2]/div[1]/div[2]/div/div/div[2]/table/tbody"));
        
    // Use By.tagName() to get all elements of desired tag underneath the above static element
      
     List<WebElement> rows  = tablebody.findElements(By.tagName("tr"));
    // Get the size of weblist to get number of rows    

     System.out.println("Total no of rows in table :- "+rows.size());
        int rowsnum= rows.size();
        String xpath =null;
        String cellval;
        String[] Frqarr = new String[rowsnum];
        float[] Frqacc = new float[rowsnum];
        float[] Frqdcc = new float[rowsnum];
        String[] spl = new String[rowsnum];
        double total=0;
       if (rowsnum>1)
       {
        for(int i = 0;i<rowsnum;i++){
            int t=i+1;
            xpath = "//*[@id='pinpoint0']/div/div/div[2]/table/tbody/tr[" + String.valueOf(t) + "]/td[5]" ;
            cellval = driver.findElement(By.xpath(xpath)).getText() ;
           
            
           Frqarr[i]= cellval ;
           System.out.println("Frequency value in row"+i+"  "+Frqarr[i]);
           String[] parts = cellval.split(" ");
           Frqacc[i]= Float.parseFloat(parts[0]);
           Frqdcc[i]= Float.parseFloat(parts[2]);
            if(cellval!= "" && cellval!= null){
            
               //System.out.println("String inside" + cellval);
              //  total = Double.parseDouble(cellval) + total;
            } 
        }// end of for loop
 
 // calculate median,Max,Min,Geo-mean,
     int Accfirst = (int) Frqacc[0];
     int Dccfirst = (int) Frqdcc[0];
     int Accmed = Math.round(Calmedian(Frqacc));
     int Dccmed = Math.round(Calmedian(Frqdcc));
     int Accmax = Math.round(Max(Frqacc));
     int Dccmin = Math.round(Max(Frqdcc));
     int Accmin = Math.round(Min(Frqacc));
     int Dccmax = Math.round(Min(Frqdcc));
     int Accsum = Math.round(Average(Frqacc));
     int Dccsum = Math.round(Average(Frqdcc));
     int Accgmean =  (int) geometricMean(Frqacc);
     int Dccgmean =  (int) geometricMean(Frqdcc);
     System.out.println("first :"+Accfirst+" "+ Dccfirst);
     System.out.println("Median :"+Accmed+" "+ Dccmed);
     System.out.println("Max :" +Accmax+" "+ Dccmax);
     System.out.println("Min :" +Accmin+" "+ Dccmin);
     System.out.println("Sum :" +Accsum+" "+ Dccsum);
     System.out.println("Geo mean :" +Accgmean+" "+ Dccgmean);
//Assertion here
    assertEquals(Accfirst+"  "+Dccfirst+" ", driver.findElement(By.cssSelector("div.info-block.first > p")).getText());
   // assertEquals(Accmed+"  "+Dccmed+" ", driver.findElement(By.cssSelector("div.info-block.median > p")).getText());
    assertEquals(Accgmean+"  "+Dccgmean+" ", driver.findElement(By.cssSelector("div.info-block.sum > p")).getText());
    assertEquals(Accmax+"  "+Dccmax+" ", driver.findElement(By.cssSelector("div.info-block.max > p")).getText());
    assertEquals(Accmin+"  "+Dccmin+" ", driver.findElement(By.cssSelector("div.info-block.min > p")).getText());
    // assertEquals(Accsum+"  "+Dccsum+" ", driver.findElement(By.xpath("//div[@id='pinpoint0']/div/div/div[2]/div[2]/div[6]/p")).getText());
       }else
       {
    	   System.out.println("Please enter some value for Acceleration and Deceleration");
       }
  }
     
  public static int Average(float[] frqacc)
  {
	  float sum=0;
	  for(int i=0;i<frqacc.length; i++)
		  sum = sum+frqacc[i];
	  int average = Math.round(sum/frqacc.length);
	  return average;
  }
  
  public static float Min(float[] frqacc)
  {
	  Arrays.sort(frqacc);
	  float max =frqacc[0];
	  return max;
  }
  
  public static float Max(float[] frqacc)
  {
	  Arrays.sort(frqacc);
	  float max =frqacc[frqacc.length-1];
	  return max;
  }

  private static float Calmedian(float[] frqacc) {
	  float median;
Arrays.sort(frqacc);
int middle = ((frqacc.length) / 2);
if(frqacc.length % 2 == 0){
 float medianA = frqacc[middle-1];
 float medianB = frqacc[middle];
 System.out.println();
 median =  ((medianA + medianB) / 2);
} else{
 median = Math.round(frqacc[middle]);
}
	   return median;
  }   
  public static double geometricMean(float[] frqacc) {
      int n = frqacc.length;
      double GM_log = 0.0d;
      for (int i = 0; i < n; ++i) {
          if (frqacc[i] == 0L) {
              return 0.0d;
          }
          GM_log += Math.log(frqacc[i]);
      }
     int Geomean =  (int) Math.round(Math.exp(GM_log / n));
      return Geomean;
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








