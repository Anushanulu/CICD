package shakeDown_US_main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import lib.Excel;
import shakeDown_US.Step01_CreateRequest;
import shakeDown_US.Step02_AddResponse_US_NewApp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

@Listeners(generics.TestNG_Listeners.class)
public class Shakedown_CreateReq_AddResp_Test
{
	// TestNG logger

	//public static Logger log = Logger.getLogger("TnM");

	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\testdata.xls";
	public String sheet="Login"; 
	public String url;
	public String url1;
	public String id;
	public String paswd;


	public WebDriver driver;

	@BeforeTest
	public void setup() throws ClassNotFoundException, SQLException
	{
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +"\\geckodriver.exe");
		// to run Firefox in Headless mode
		FirefoxOptions op=new FirefoxOptions();
		op.setHeadless(true);
		driver=new FirefoxDriver(op);

		   //here//
       // ((RemoteWebDriver) driver).setLogLevel(Level.INFO);
        
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\SanjayKushwaha\\Desktop\\Selenium\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();
       // DesiredCapabilities cap =new DesiredCapabilities();
       // cap.chrome().setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        //
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(160, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		url = Excel.getCellValue(xlsFilePath, sheet, 4, 2);
		driver.get(url);
		driver.manage().window().maximize();
	}	

	// test to Login to the application as RIPC
	@Test(priority=0)
	public void RIPC_Login() throws InterruptedException 
	{
	
		Step01_CreateRequest login = new Step01_CreateRequest(driver);
		try {
			login.login();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test(priority=1)
	public void Create_Req() 
	{

		Step01_CreateRequest createreq = new Step01_CreateRequest(driver);
		createreq.Create_New_Request();
	}
	@Test(priority=2)
	public void skill_Req() 
	{

		Step01_CreateRequest skillreq = new Step01_CreateRequest(driver);
		skillreq.Skill_Request();
	}
	@Test(priority=3)
	public void select_Req() 
	{

		Step01_CreateRequest selectreq = new Step01_CreateRequest(driver);
		try {
			selectreq.Select_Requestor();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority=4)
	public void select_jrss() 
	{

		Step01_CreateRequest selectreq = new Step01_CreateRequest(driver);
		selectreq.Select_JRSS();
	}

	@Test(priority=5)
	public void req_detail() 
	{

		Step01_CreateRequest reqdetail = new Step01_CreateRequest(driver);
		reqdetail.Request_detailpage();
	}
	@Test(priority=6)
	public void skill_loc() 
	{

		Step01_CreateRequest skillloc = new Step01_CreateRequest(driver);
		skillloc.Skill_detailLocationpage();
	}
	@Test(priority=7)
	public void alert_check() 
	{

		Step01_CreateRequest alertchk = new Step01_CreateRequest(driver);
		alertchk.isAlertPresent();
	}
	@Test(priority=8)
	public void skill_price() throws InterruptedException 
	{

		Step01_CreateRequest skillprice = new Step01_CreateRequest(driver);
		skillprice.Skill_detail_skillpricepage();
	}
	@Test(priority=9)
	public void supp() throws InterruptedException 
	{

		Step01_CreateRequest supplier = new Step01_CreateRequest(driver);
		supplier.SupplierSelectionPage();
	}

	@Test(priority=10)
	public void OpenNewGreen() 
	{

		url1 = Excel.getCellValue(xlsFilePath, sheet, 1, 2);
		driver.navigate().to(url1);
	}

	@Test(priority=11)
	public void add_response() throws IOException, InterruptedException
	{


		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.login();
		addresp.openRequest();
	
		for (int i=1; i<2; i++)
		{
			if(i==1)
			{

				addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "10", "10");
			}  
			else if(i==2)
			{

				addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "10", "10");
			}
			else
			{

				addresp.FillRespdetails("fResp"+i , "US" , "lResp"+i, "10", "10");

			}
		}
		addresp.Submit();
	}

	@Test(priority=12, dependsOnMethods="add_response")
	public void Withdraw() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.WithdrawResponse();

	}

	@Test(priority=13, dependsOnMethods="Withdraw")
	public void Reactivate() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.ReactivateResponse();
	}

	@Test(priority=14, dependsOnMethods="Reactivate")
	public void Resubmit() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.EditReactivatedResponse("20");
		addresp.Submit();
	}
	@Test(priority=15)
	public void Reprice() throws InterruptedException
	{

		Step02_AddResponse_US_NewApp addresp = new Step02_AddResponse_US_NewApp(driver); 
		addresp.Reprice("30");

	}


}


