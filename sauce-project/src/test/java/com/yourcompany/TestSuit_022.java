spackage com.saucelabs;

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
public class TestSuit_022 implements SauceOnDemandSessionIdProvider {
	
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
	
    public TestSuit_022(String os, String version, String browser) {
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
        capabilities.setCapability("name", "Chartlytics Test_AddconditionToPerformer");
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
	//--------------------------------------------------------------------------------------------
	 public void Addcondition() throws Exception {
 String[][] getit = GetValue(Pathofexcel,"addcondition",2);
 baseUrl = getit[0][0]; 
  Email= getit[0][1];  
  Password=getit[0][2];

    driver.get("http://"+ baseUrl + "/signin");
    driver.manage().window().maximize();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(Email);
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(Password);

    driver.findElement(By.xpath("//button[@type='submit']")).click();
    
    
  String[][] getits = GetValue(Pathofexcel,"addcondition",8);
  String PerName = getits[0][0];
  String age = getits[0][1];
  String Gen = getits[0][2];
  String Grade = getits[0][3];
  
   
    driver.findElement(By.cssSelector("a[title=\"Performers\"] > span")).click();
    driver.findElement(By.id("createPerformer")).click();
    Thread.sleep(5000);
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
    String p =driver.findElement(By.xpath("//*[@id='results-paging']/div/div[1]/button[1]")).getText();
    String item =driver.findElement(By.xpath("//*[@id='results-paging']/div/div[1]/button[2]")).getText();
   ;
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
  /*int pag = page+1;
  driver.findElement(By.xpath("//*[@id='results-paging']/div/div[1]/a["+pag+"]")).click();*/
  driver.findElement(By.id("fifty")).click(); 
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
 
//-------------------------------------------------Add Condition---------------------------------------------------------------------------//
    
    driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).click();
    driver.findElement(By.linkText("Conditions")).click();
    driver.findElement(By.id("createCondition")).click(); 
    Thread.sleep(5000);
    String[][] addcond = GetValue(Pathofexcel,"addcondition",22);
    String condtitle = addcond[0][0];
    String conddes = addcond[0][1];
    String condtype = addcond[0][2];
    String condpin = addcond[0][3];
    String condrecdate=addcond[0][4];
     conpath = addcond[0][5];
//---------------------------------------------Call the method condition to add condition---------------------------------------------------//    
    condition(condtitle, conddes, condtype, condpin, condrecdate, conpath);
   
    driver.findElement(By.xpath("html/body/div[6]/div[1]/div/div[2]/p/button")).click();
    Thread.sleep(5000);
    String condTitle= driver.findElement(By.xpath(".//*[@id='app-main']/div[2]/div[2]/table/tbody/tr/td[2]")).getText();
    assertEquals(condtitle, condTitle);
    System.out.println("//------------------Successfully Create Condition---------------//");
    System.out.println(condTitle);
//----------------------------------------------------------EDIT CONDITION-------------------------------------------------------------//    
    Thread.sleep(5000);
    driver.findElement(By.linkText("Edit")).click();
    Thread.sleep(5000);
    String[][] editcond = GetValue(Pathofexcel,"addcondition",25);
    String editcondtitle = editcond[0][0];
    String editconddes = editcond[0][1];
    String editcondtype = editcond[0][2];
    String editcondpin = editcond[0][3];
    String editcondrecdate=editcond[0][4];
     edipath= editcond[0][5];
    
//------------------------------------------------Call the method condition to edit condition-------------------------------------------//    
    condition(editcondtitle, editconddes, editcondtype, editcondpin, editcondrecdate, edipath);
    driver.findElement(By.xpath(".//*[@id='app-main']/form/div[2]/p/button")).click();
    String editcondTitle= driver.findElement(By.xpath("html/body/div[3]/div[2]/div[2]/table/tbody/tr/td[2]")).getText();
    assertEquals(editcondtitle, editcondTitle);
    System.out.println("//------------------Successfully Edit Condition---------------//");
    System.out.println(editcondTitle);
//-------------------------------------------------Delete the Condition-----------------------------------------------------------------//
   driver.findElement(By.xpath("html/body/div[3]/div[2]/div[2]/table/tbody/tr/td[6]/button")).click();
   Thread.sleep(10000);
   driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
   System.out.println("//---------------Successfully delete the Condition--------------//");
   
//---------------------------------------------------------DELETE PERFORMER-----------------------------------------------------------//    
  /* driver.findElement(By.xpath("//a[contains(text(),'Settings')]")).click();
    driver.findElement(By.id("deletePerformer")).click();
    Thread.sleep(10000);
    driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
    System.out.println("//--------------Suceessfully--------------// ");*/
  }

public  void condition(String title, String description, String type, String assopinpoint, String recorddate, String xpath)
{
	  driver.findElement(By.name("title")).clear();
	    driver.findElement(By.name("title")).sendKeys(title);
	    driver.findElement(By.cssSelector("input[name=\"description\"]")).clear();
	    driver.findElement(By.cssSelector("input[name=\"description\"]")).sendKeys(description); 
	    
	    if(type.equals("Annotation"))
	    {
	    	if(xpath.equals("cond"))
	    	{
	    	driver.findElement(By.xpath(".//*[@id='createConditionForm']/div/div/div/div[3]/div/div/label[1]")).click(); 
	    	}
	    	else
	    	{
	    		driver.findElement(By.xpath(".//*[@id='app-main']/form/div[1]/div/div/div[4]/div/div/label[1]")).click();
	    	}
	    }else
	    {
	    	if(xpath.equals("cond")){
	       driver.findElement(By.xpath(".//*[@id='createConditionForm']/div/div/div/div[3]/div/div/label[2]")).click();
	    	}
	    	else
	    	{
	    		driver.findElement(By.xpath(".//*[@id='app-main']/form/div[1]/div/div/div[4]/div/div/label[2]")).click();
	    	}
	    }
	    
	    new Select(driver.findElement(By.name("chart"))).selectByVisibleText(assopinpoint);
	    
	    String dateTime =recorddate ;
	   
	    
	    if(xpath.equals("cond"))
    	{
	    WebElement selectDate = driver.findElement(By.xpath(".//*[@id='datetimepickerInModal']/span/button[1]"));
	    selectDate.click();
    	}
	    
	    else{
	    	 WebElement selectDate = driver.findElement(By.xpath(".//*[@id='datetimepicker']/span/button[1]"));
	    	 selectDate.click();
	    }
	    
	  //button to move next in calendar
	    WebElement nextLink = driver.findElement(By.cssSelector("div.datepicker-months > table.table-condensed > thead > tr > th.next"));
	  //button to click in center of calendar header
	    WebElement midLink = driver.findElement(By.cssSelector("th.picker-switch"));
	    String year = midLink.getText();
	    String[] temp = year.split(" ");
	    int Cyr= Integer.parseInt(temp[1]);
	  //button to move previous month in calendar
	    WebElement previousLink = driver.findElement(By.cssSelector("div.datepicker-months > table.table-condensed > thead > tr > th.prev"));
	  //Split the date time to get only the date part
	    String date_yyyy_MM_dd[] = dateTime.split("/");
	  //get the year difference between current year and year to set in calander
	  int yearDiff = Integer.parseInt(date_yyyy_MM_dd[0])- Cyr;
	  
	   
	    midLink.click();

	    if(yearDiff!=0){

	        //if you have to move next year
	    	

	        if(yearDiff>0){

	            for(int i=0;i< yearDiff;i++){

	               
	                try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
	                nextLink.click();

	            }

	        }

	        //if you have to move previous year

	        else if(yearDiff<0){

	            for(int i=0;i< (yearDiff*(-1));i++){

	               
	                try {
						Thread.sleep(3000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                previousLink.click();

	            }

	        }

	    }

	     try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    //Get all months from calendar to select correct one
	    List<WebElement> list_AllMonthToBook =driver.findElements(By.cssSelector("span.month"));
	    list_AllMonthToBook.get(Integer.parseInt(date_yyyy_MM_dd[1])-1).click();
	    try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    //get all dates from calendar to select correct one
	 
	    if(xpath.equals("cond"))
	    {
	                                                              
	    List<WebElement> list_AllDateToBook = driver.findElements(By.xpath("html//body//div[6]//div[2]//div//div[1]//table//tbody//td[not(contains(@class,'day old'))]"));
	    try {
	 			Thread.sleep(3000);
	 		} catch (InterruptedException e1) {
	 		
	 		e1.printStackTrace();
	 	}
	    
	    list_AllDateToBook.get(Integer.parseInt(date_yyyy_MM_dd[2])-1).click();	
	    }else{
	    	
	    	 List<WebElement> list_AllDateToBook = driver.findElements(By.xpath("html//body//div[4]//div//div[1]//table//tbody//td[not(contains(@class,'day old'))]"));
	    	   try {
	   			Thread.sleep(3000);
	   		} catch (InterruptedException e1) {
	   			// TODO Auto-generated catch block
	   			e1.printStackTrace();
	   		}
	    	   
	    	 list_AllDateToBook.get(Integer.parseInt(date_yyyy_MM_dd[2])-1).click();	
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








