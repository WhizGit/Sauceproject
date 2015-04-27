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
public class TestSuit_004 implements SauceOnDemandSessionIdProvider{

	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	private String Tar, Mtype, Mrec, Aggtype, Email,Fullname,Orgname, Password;
	 private String Pathofexcel ="./src/test/java/com/yourcompany/dataexcel.xlsx"; // path of your excel file
	/*String[][] SauceInfo = GetValue(Pathofexcel,"signup",11);
	String SauceUser = SauceInfo[0][0];
	String SauceAccessKey = SauceInfo[0][1];*/
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
	
    public TestSuit_004(String os, String version, String browser) {
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
        capabilities.setCapability("name", "Chartlytics Test_PerformerSort");
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
	 public void PerformerSort() throws Exception {
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
 

   

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    
    @Override
    public String getSessionId() {
        return sessionId;
    }
}








