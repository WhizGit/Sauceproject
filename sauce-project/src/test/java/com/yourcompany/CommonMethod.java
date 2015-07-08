import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CommonMethod {
//--------------------Sign In Method--------------------------------------------------
	public static void SignIn(WebDriver dri, String burl, String Email, String Pwd )
		{
		  dri.get("http://"+ burl + "/signin");
		  dri.manage().window().maximize();
		  dri.findElement(By.name("username")).clear();
		  dri.findElement(By.name("username")).sendKeys(Email);
		  dri.findElement(By.name("password")).clear();
		  dri.findElement(By.name("password")).sendKeys(Pwd);
		  dri.findElement(By.xpath("//button[@type='submit']")).click();
		}
//-----------------------Frequency calculation---------------------------------------------
	public static  float[]  frquencycal(String couttym, String Accs, String Decs)
		{
		float Counttym = Float.parseFloat(couttym);
		float Acce;
		float Dcel;
		int acc;
		int dcc;
		if(Counttym<60)
		{
		float CT = (60/Counttym);
		System.out.println("count time change to min"+CT);
		 acc = Integer.parseInt(Accs);
		 Acce =  (acc * CT); // Acceleration frequency
		 if(Acce<1)
			 {
				 DecimalFormat df2 = new DecimalFormat("###.###");
				  Acce = Float.valueOf(df2.format(Acce));
			 }else
			 {
				 Acce = (int) Math.round(acc*CT);
				/* DecimalFormat df2 = new DecimalFormat("###");
				  Acce = Float.valueOf(df2.format(Acce));*/
			 }
		System.out.println("Frequency of acc :"+Acce);
		 dcc = Integer.parseInt(Decs);
		 Dcel =  (dcc * CT); // Deceleration frequency
			 if(Dcel<1)
			 {
				 DecimalFormat df2 = new DecimalFormat("###.###");
				  Dcel= Float.valueOf(df2.format(Dcel));
			 }else
			 {
				 Dcel = (int) Math.round(dcc*CT);
				/* DecimalFormat df2 = new DecimalFormat("###");
				  Dcel= Float.valueOf(df2.format(Dcel));*/
			 }
		System.out.println("Frequency of Dcel"+Dcel);
		
		}else
		{
			float CT = (Counttym/60);
			System.out.println("count time change to min"+CT);
			 acc = Integer.parseInt(Accs);
			 Acce = (acc / CT); // Acceleration frequency
			 	if(Acce<1)
			 		{
			 			DecimalFormat df2 = new DecimalFormat("###.###");
			 			Acce = Float.valueOf(df2.format(Acce));
			 		}else
			 			{
			 				Acce = (int) Math.round(acc/CT);
			 				/*DecimalFormat df2 = new DecimalFormat("###");
				 			Acce= Float.valueOf(df2.format(Acce));*/
			 			}
			System.out.println("Frequenct of acceleration :"+ Acce);
			 dcc = Integer.parseInt(Decs);
			 Dcel = (dcc/CT); // Deceleration frequency
			 	if(Dcel<1)
			 		{
			 			DecimalFormat df2 = new DecimalFormat("###.###");
			 			Dcel= Float.valueOf(df2.format(Dcel));
			 		}else
			 			{
			 			 Dcel = Math.round(Dcel) ;
			 				DecimalFormat df2 = new DecimalFormat("##");
				 			Dcel= Float.valueOf(df2.format(Dcel));
			 			}
			System.out.println("Frequncy of Dcel"+Dcel);
			
		}
		return new float[] {Acce, Dcel};
		}
	
//--------------------Method for read data from excel sheet------------------------------------------
	public static String[][] GetValue(String Pathfile, String sheetName, int startrow) throws IOException
		{
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
		  //----------------------Create performer---------------------------------------------------------
		   public static void CreatePerformer(WebDriver driver,String[][] getits) throws InterruptedException
		   {
			   driver.findElement(By.cssSelector("a[title=\"Performers\"] > span")).click();
				  Thread.sleep(5000);
				  driver.findElement(By.id("createPerformer")).click();
				  Thread.sleep(3000);
				  driver.findElement(By.name("performerName")).clear();
				  Thread.sleep(4000);
				  driver.findElement(By.name("performerName")).sendKeys(getits[0][0]);
				  Thread.sleep(4000);
				  driver.findElement(By.name("age")).clear();
				  Thread.sleep(4000);
				  driver.findElement(By.name("age")).sendKeys(getits[0][1]);
				  
				  if(getits[0][2].equals("M"))
				  {
					  driver.findElement(By.id("male")).click();
				  }else
				  {
			       driver.findElement(By.id("female")).click();
				  }
				  driver.findElement(By.name("grade")).clear();
				  driver.findElement(By.name("grade")).sendKeys(getits[0][3]);
				  driver.findElement(By.cssSelector("p.pull-right > button.btn.btn-primary")).click();
				  Thread.sleep(3000);
				  driver.findElement(By.id("fifty")).click();
				  Thread.sleep(5000);
				  driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
				  
				  //---------------Verify create performer----------------------------------------------------
				  Thread.sleep(3000);
				  String Per= driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).getText();
				  assertEquals(Per,getits[0][0]);
		   }
		   //---------------Create Pinpoint method-------------------------------
	public static void CreatePinpoint(WebDriver driver, String[][] datas, String[][] data,String Tar,String Mtype,String Mrec,String Aggtype) throws InterruptedException
	{
		 driver.findElement(By.cssSelector("a[title=\"Pinpoints+\"] > span")).click();
		  Thread.sleep(2000);
		  //-----------------------Read data from excel sheet for creating pinpoint---------------------
		//  String[][] datas = GetValue("./src/dataexcel.xlsx","charts",16);

		  String Action = datas [0][3];
		  String Noun = datas [0][4];
		  String Context = datas [0][5];
		  String Dct = datas [0][6];
		  String AAMi = datas [0][7];
		  String AAMx = datas [0][8];
		  String ADMi = datas [0][9];
		  String ADMx = datas [0][10];
		  String Daim  = datas [0][11];
		
		  driver.findElement(By.id("createPinpoint")).click();
		  Thread.sleep(2000);
		  //---------------------------Input value in the "Pinpont" Tab-----------------------------------------------------------//	
		  driver.findElement(By.id("actionVerb")).clear();
		  driver.findElement(By.id("actionVerb")).sendKeys(Action);
		  driver.findElement(By.id("objectNoun")).clear();
		  driver.findElement(By.id("objectNoun")).sendKeys(Noun);
		  driver.findElement(By.id("context")).clear();
		  driver.findElement(By.id("context")).sendKeys(Context);  
		  driver.findElement(By.cssSelector(".btn.btn-primary.btn-next")).click();
		  Thread.sleep(2000);

		  //----------------Select the combination for "Set Default" tab---------------------------------------------------------//	    
		    
		//  String[][] data = GetValue("./src/dataexcel.xlsx","charts",19);
		  String Acc = data [0][1];
		  System.out.println(Acc);
		  String Dec = data [0][2];
		  System.out.print(Dec);
		  String Frq = data [0][3];
		  System.out.print(Frq);
		  String Dur = data [0][4];
		  System.out.print(Dur);
		  String Lat = data [0][5];
		  System.out.print(Lat);
		  String Cou = data [0][6];
		  System.out.print(Cou);
		  String Dai = data [0][7];
		  System.out.print(Dai);
		  String Wee = data [0][8];
		  System.out.print(Wee);
		  String Mon = data [0][9];
		  System.out.print(Mon);
		  String Yr = data [0][10];
		  System.out.print(Yr);
		  String Fir = data [0][11];
		  System.out.print(Fir);
		  String Med = data [0][12];
		  System.out.print(Med);
		  String Geo = data [0][13];
		  System.out.print(Geo);
		  String Min = data [0][14];
		  System.out.print(Min);
		  String Max = data [0][15];
		  System.out.print(Max);
		  String Sum = data [0][16];
		  System.out.print(Sum);
		  String Sta = data [0][17];
		  System.out.print(Sta);
		  String tru = "Y";
		  String fal = "N";
		  //-------------------------- pinpoint goal selection---------------------------------------------
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
			
			//-----------------------Measurement type selection----------------------------------------------- 
			  
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
			
	//---------------------------------------------------------Click on the "Next" buttons-----------------------------------------------------//		    
			    driver.findElement(By.cssSelector(".btn.btn-primary.btn-next")).click();
			    Thread.sleep(5000);
			    driver.findElement(By.cssSelector(".btn.btn-primary.btn-next")).click();
			    Thread.sleep(5000);
			 
			 String pinName = driver.findElement(By.xpath("//form[@id='createPinpointForm']/div/div[4]/div/div/table/tbody/tr/td[2]")).getText();
	//----------------------------------------------------------Verification of value in "Review Summary"------------------------------------//
			    
			 	
				   
				   
				    if(Mtype=="duration" || Mtype=="latency")
				    	{
				    		assertEquals(AAMi, driver.findElement(By.id("correctAimMin")).getText());
				    		assertEquals(AAMx, driver.findElement(By.id("correctAimMax")).getText());
				    	} 
				     	else
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
				   
				    Thread.sleep(5000);
				    driver.findElement(By.cssSelector(".btn.btn-next.btn-success")).click();
				    Thread.sleep(5000);
	}

	//---------------------Assign pinpoint Method------------------------------------------------------------
	public static void AssignPinpoint(WebDriver driver, String[][] pinpnt) throws InterruptedException
	{
		driver.findElement(By.cssSelector("a[title=\"Performers\"] > span")).click();
		Thread.sleep(5000);
	    driver.findElement(By.id("fifty")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//a[contains(text(),'Pinpoints')]")).click();
	    Thread.sleep(5000);
	    driver.findElement(By.id("launchAssignPinpoint")).click();
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//form/div/div[1]/div/div/div/a/span[2]/b")).click();
	    
	   // String[][] pinpnt = GetValue("./src/dataexcel.xlsx","charts",30);
		String pinpoint = pinpnt[0][0]; 
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("html/body/div[9]/div/input")).sendKeys("- "+pinpoint);
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("html/body/div[9]/ul/li/div")).click();
	    Thread.sleep(3000);
	    String AssignPinpoint=driver.findElement(By.xpath("//form/div[1]/div/div/a/div/div[1]/div/h2")).getText();
	//    assertEquals(pinName ,AssignPinpoint);
	    driver.findElement(By.id("assignPinpoint")).click();
	    Thread.sleep(3000);
	    
	}
	
//-------------------------------------Create Performer Methode--------------------------------------------------
	public static void CreatePerformer(WebDriver driver,String PerName,String age,String Gen,String Grade ) throws InterruptedException
	{
		 driver.findElement(By.cssSelector("a[title=\"Performers\"] > span")).click();
		    driver.findElement(By.id("createPerformer")).click();
		    Thread.sleep(2000);
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
		    	Thread.sleep(10000);
		    	driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[4]/label[4]")).click();
		    	assertEquals(PerName , driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).getText());
		    	assertEquals("Age: "+age+"   "+"Grade: "+Grade , driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/p")).getText());
		   
		   
			}// end of if
			else
			{
				int pag = page+1;
				driver.findElement(By.xpath("//*[@id='results-paging']/div/div[1]/a["+pag+"]")).click();
				driver.findElement(By.xpath("//*[@id='app-main']/div[2]/div[1]/div/div[4]/label[4]")).click();
				 
					  Thread.sleep(10000);
				      assertEquals(PerName , driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).getText());
				      assertEquals("Age: "+age+"   "+"Grade: "+Grade, driver.findElement(By.xpath("//*[@id='grid-view']/div[1]/a/div/div[2]/p")).getText());
				  
				    
			} //end of else
	}//----------------------------- end of Create performer method--------------------------------------
	
	//---------------------------------Create Category--------------------------------------------------
	public static void CreateCategory(WebDriver driver,String CatName) throws InterruptedException
	{
		  
		   driver.findElement(By.id("createCategory")).click();
		  // String[][] CatName = CommonMethod.GetValue("./src/dataexcel.xlsx", "Category", 2);
		    driver.findElement(By.name("catName")).clear();
		    driver.findElement(By.name("catName")).sendKeys(CatName);
		    driver.findElement(By.xpath("//button[@type='submit']")).click();
		   Thread.sleep(3000);
		    assertEquals(CatName, driver.findElement(By.xpath(".//*[@id='app-main']/div[2]/div[2]/div[1]/a/div")).getText());
		  
	}// end od create category
	
	//-------------------------Create Group-------------------------------------------------------------
	public static void CreateGroup(WebDriver driver, String[][] GroupData) throws InterruptedException
	{
		driver.findElement(By.cssSelector("a[title=\"Groups\"] > span")).click();
		driver.findElement(By.id("newGroup")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("groupName")).sendKeys(GroupData[0][0]);
		Thread.sleep(3000);
		driver.findElement(By.xpath(".//*[@id='createGroupForm']/div/div/div[2]/input")).sendKeys(GroupData[0][1]);
		Thread.sleep(3000);
		driver.findElement(By.name("location")).sendKeys(GroupData[0][1]);
		Thread.sleep(3000);
		driver.findElement(By.id("createGroup")).click();	
		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='app-main']/div[2]/div[1]/div/div[3]/label[4]")).click();	
		Thread.sleep(2000);
		   String page = driver.findElement(By.xpath(".//*[@id='results-paging']/div/div/button[1]")).getText();
		    String[] Page=page.split(" "); // divide the string and get total no of page
		    String item = driver.findElement(By.xpath(".//*[@id='results-paging']/div/div/button[2]")).getText();
		    String[] Item = item.split(" ");
		    int itm = Integer.parseInt(Item[5]);
		    Thread.sleep(2000);
		    int pg = Integer.parseInt(Page[3]);
		    if(pg<10)
		    {
		    	 driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
		    	 Thread.sleep(2000);
		    	 if(itm==1)
		    	 {
		    	 assertEquals(GroupData[0][0], driver.findElement(By.xpath(".//*[@id='grid-view']/div/a/div/div[2]/div[1]/h2")).getText());
		    	 }else
		    	 {
		    	assertEquals(GroupData[0][0], driver.findElement(By.xpath(".//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).getText());
		    	 }
		    }else
		    {
		    driver.findElement(By.name("page")).sendKeys(""+pg);
		    driver.findElement(By.xpath(".//*[@id='results-paging']/div/div[2]/form/div/div/span/button")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.cssSelector("i.fa.fa-sort-numeric-desc")).click();
		    Thread.sleep(2000);
			 if(itm==1)
			 {
			 assertEquals(GroupData[0][0], driver.findElement(By.xpath(".//*[@id='grid-view']/div/a/div/div[2]/div[1]/h2")).getText());
			 }else
			 {
			assertEquals(GroupData[0][0], driver.findElement(By.xpath(".//*[@id='grid-view']/div[1]/a/div/div[2]/div[1]/h2")).getText());
			 }
		    
		    
		    }
		
	}
}

