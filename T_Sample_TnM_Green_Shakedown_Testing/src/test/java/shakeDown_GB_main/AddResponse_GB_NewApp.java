package shakeDown_GB_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


import lib.Excel;
import shakeDown_GB.Step02_AddResponse_GB_NewApp;


public class AddResponse_GB_NewApp
{
	// TestNG logger

	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login"; 
	public String url;
	public String id;
	public String paswd_green;
	public String id_green;
	public String paswd;
	public String url_green;
	public String url2;

	public WebDriver driver;


	@BeforeTest
	public void setup()
	{
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\SanjayKushwaha\\Desktop\\Selenium\\geckodriver-v0.26.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();

		//System.setProperty("webdriver.chrome.driver","C:\\Users\\SanjayKushwaha\\Desktop\\Selenium\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 2, 2);
		driver.get(url); 
		driver.manage().window().maximize();
	}	



	// Test to add response
	@Test(priority=0)
	public void add_response() throws IOException, InterruptedException
	{
	
		Step02_AddResponse_GB_NewApp addresp = new Step02_AddResponse_GB_NewApp(driver); 
		addresp.login();
		//addresp.openRequest();
		addresp.OpenRequestWorkaround();
		for (int i=1; i<4; i++)
		{
			if(i==1)
			{
				
				addresp.FillRespdetails("fResp"+i , "GB" , "lResp"+i, "10");
			}  
			else if(i==2)
			{
				//addresp.login();
				addresp.FillRespdetails("fResp"+i , "GB" , "lResp"+i, "20");
			}
			else
			{
				//addresp.login();
				addresp.FillRespdetails("fResp"+i , "GB" , "lResp"+i, "30");
				
			}
		}
		addresp.Submit();
	}
}




