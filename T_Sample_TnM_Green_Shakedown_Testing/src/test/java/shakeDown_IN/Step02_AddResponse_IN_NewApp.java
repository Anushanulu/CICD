package shakeDown_IN;

import generics.Screenshots;
import lib.Excel;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Step02_AddResponse_IN_NewApp
{

	private WebDriver driver;
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src\\testdata\\testdata.xls";
	public String sheet="Login";

	// Define the element 
	@FindBy (xpath = "//button[contains(text(),'Log in')]") private WebElement Login_Button ;
	@FindBy ( xpath = ".//button[@id='continue-button']" ) private WebElement ContinueButton ;
	@FindBy ( id = "username" ) private WebElement UsernameField ;
	@FindBy ( id = "password" ) private WebElement PasswordField ;
	@FindBy ( id = "signinbutton" ) private WebElement SigninButton ;
	@FindBy (xpath = "//button[@name='search']" ) private WebElement search_tab ;
	@FindBy ( xpath= "//input[@placeholder='Request No']" ) private WebElement Req_Num_field ;
	@FindBy (xpath ="//*[@touranchor='search-form']/div/div[1]/div/button[2] ") private WebElement Search_btn ; 
	@FindBy ( xpath = " //mat-table/mat-row[1]/mat-cell[1]" ) private WebElement Request_Number_link ;

	@FindBy ( xpath = "//select[@id='pagination-select-items-per-page-3']" ) private WebElement Navigation ;
	@FindBy ( xpath = "//a[contains(text(),'U22TTL')]" ) private WebElement RequestId ;

	//Add Response and details
	@FindBy (xpath ="//button[contains(text(),'Add Response')]") private WebElement btn_Add_response; 
	@FindBy (xpath ="//span[@class='headerTitle' and contains(text(),'Attachments')]") private WebElement Attachments_Panel; 
	@FindBy (xpath ="//button[contains(@for,'file-uploader') and contains(text(),' Add Response File ')]") private WebElement Add_ResponseFile; 
	@FindBy (xpath ="//button[@class='_hj-OO1S1__styles__openStateToggle']") private WebElement feedback_PopUp; 

	@FindBy (xpath ="//span[@class='headerTitle' and contains(text(),'Response Details')]") private WebElement ResponseDetails_Panel; 


	@FindBy ( xpath= "//input[@formcontrolname='firstName']" ) private WebElement First_Name ;
	@FindBy ( xpath= "//input[@formcontrolname='middleName']" ) private WebElement Middle_Name ;
	@FindBy ( xpath= "//input[@formcontrolname='lastName']" ) private WebElement LastName_Surname ;
	@FindBy ( id="dobPickerOverlay") private WebElement DOB_Field ;
	@FindBy (xpath= "//mat-calendar[contains(@id,'mat-datepicker')]/div/mat-month-view/table/tbody/tr[4]/td[3]") private WebElement DOB_InCalender;
	@FindBy (xpath= "//input[@formcontrolname='candidId']") private WebElement CandGID_field;
	@FindBy (xpath= "//input[@formcontrolname='candRefCode']") private WebElement CandReference_Field;

	@FindBy ( xpath= "//*[@formcontrolname='flowDownTerm']/div/ibm-radio/input[@id='radio-10']" ) private WebElement Flowdown_No ;
	@FindBy (xpath= "//*[@class='bx--checkbox-wrapper bx--form-item ng-star-inserted' and @ng-reflect-id='noneOfAbove']") private WebElement PreviousClientEmp_None ;

	@FindBy ( xpath="//input[@id='citizenShip']") private WebElement Citizenship_Field ;
	@FindBy (xpath= "//ul[@class='bx--list-box__menu bx--multi-select']/li[1]/div[1]") private WebElement Citizenship_Country;

	@FindBy ( xpath="//span[contains(text(),'Requirement Attributes')]") private WebElement RequirementAttributes_Panel ;
	@FindBy ( xpath="//span[@class='attribute-field-title' and contains(text(),'Planned Start Date')]/parent::div/following-sibling::div/span") private WebElement Requested_FromDate ;
	@FindBy ( xpath="//span[@class='attribute-field-title' and contains(text(),'Planned End Date')]/parent::div/following-sibling::div/span") private WebElement Requested_EndDate ;
	@FindBy ( xpath="//input[@ng-reflect-name='expectedEndDate']") private WebElement EndDate_Field ;
	@FindBy (xpath= "//input[@ng-reflect-name='expectedStartDate']") private WebElement FromDate_field;

	@FindBy (xpath= "//mat-calendar[contains(@id,'mat-datepicker')]/div/mat-month-view/table/tbody/tr[2]/td[5]") private WebElement FromDate_InCalender;
	@FindBy (xpath= "//mat-calendar[contains(@id,'mat-datepicker')]/div/mat-month-view/table/tbody/tr[4]/td[5]") private WebElement EndDate_InCalender;



	@FindBy (xpath= "//button[@class='bx--list-box__field']") private WebElement Skill_Level;
	@FindBy (xpath= "//div[@class='bx--list-box__menu-item__option' and contains(text(),'Band 6')]") private WebElement Skill_Level_Value;

	@FindBy ( xpath="//span[@class='headerTitle' and contains(text(),'Response Pricing')]") private WebElement ResponsePricing_Panel ;
	@FindBy ( xpath="//input[@formcontrolname='straightTimeBillRate']") private WebElement ST_BillRate ;
	@FindBy ( xpath="//input[@formcontrolname='straightWages']") private WebElement ST_Wage ;	
	@FindBy ( xpath="//clapp-skill-response-detail[@class='ng-star-inserted']/div[3]/button[2]") private WebElement Save_Button ;


	//Submit Page
	@FindBy (xpath = "//input[@id='selectAll']/parent::label[@class='container']/span[@class='checkmark']") private WebElement SelectAll_Checkbox;
	@FindBy (xpath = "//input[@id='checkBoxId-001-0']/parent::label[@class='container']/span[@class='checkmark']") private WebElement Resp1_Checkbox;
	@FindBy (xpath = "//table[@class='custom-grid']/div[@class='ng-star-inserted']/tr[1]/td[2]/a") private WebElement Resp1_name;


	@FindBy (xpath = "//button[@ibmbutton='secondary' and contains(text(),'Submit')]") private WebElement Submit_Button;
	@FindBy (xpath = "//button[@ibmbutton='primary' and contains(text(),'Confirm submit')]") private WebElement ConfirmSubmit_Button;



	@FindBy (xpath = ".//input[@value='Withdraw response']") private WebElement Withdraw_response;


	// Initialize the web elements 
	public Step02_AddResponse_IN_NewApp (WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	// Function to login to the application

	public void login(){

		WebDriverWait wait0 = new WebDriverWait(driver, 160);
		wait0.until(ExpectedConditions.visibilityOf(Login_Button));

		Login_Button.click();

		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(UsernameField));

		if (ContinueButton.isDisplayed())
		{
			System.out.println("continue button is Present");  

			//UsernameField.clear();
			UsernameField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 3, 0));

			ContinueButton.click();

			WebDriverWait wait9 = new WebDriverWait(driver, 160);
			wait9.until(ExpectedConditions.visibilityOf(SigninButton));

			PasswordField.clear();
			PasswordField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 3, 1));


			/*Taking screenshot */
			Screenshots shot=new Screenshots(driver);
			shot.ScreenShot_Shakedown_IN();

			SigninButton.click();
			System.out.println("Sign in button clicked after entering credentials");
		} else
		{
			System.out.println("Continue button is not Present");  

			WebDriverWait wait2 = new WebDriverWait(driver, 160);
			wait2.until(ExpectedConditions.visibilityOf(UsernameField));

			UsernameField.clear();
			UsernameField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 3, 0));

			PasswordField.clear();
			PasswordField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 3, 1));

			/*Taking screenshot */
			Screenshots shot1=new Screenshots(driver);
			shot1.ScreenShot_Shakedown_IN();
			SigninButton.click();
			System.out.println("Sign in button clicked after entering credentials");
		}  

	}

	public void openRequest()
	{

		WebDriverWait wait3 = new WebDriverWait(driver, 160);
		wait3.until(ExpectedConditions.visibilityOf(search_tab));
		search_tab.click();

		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Req_Num_field));

		Req_Num_field.sendKeys(Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
		Search_btn.click();

		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(Request_Number_link));

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_Shakedown_IN();
		Request_Number_link.click();

		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.visibilityOf(btn_Add_response));

		System.out.println("searched and Opened the request");
		if (feedback_PopUp.isDisplayed())
		{
			feedback_PopUp.click();
			System.out.println("feedback PopUp is Present and minimised");
		}
	}


	//Add Response and details
	public void FillRespdetails(String First_Name_temp , String Middle_Name_temp , String LastName_Surname_temp, String rate) throws IOException, InterruptedException 
	{

		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.elementToBeClickable(btn_Add_response));

		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_Shakedown_IN();
		Thread.sleep(1000);
		btn_Add_response.click();

		//Attachment Panel
		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Attachments_Panel));

		Attachments_Panel.click();

		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(Add_ResponseFile));

		Add_ResponseFile.click();
		String AttachmentPath = System.getProperty("user.dir") + "\\src\\testdata\\Response attachment.txt";

		//put path to your file in a clipboard
		StringSelection ss = new StringSelection(AttachmentPath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		try {
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(500);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_Shakedown_IN();

		//response details panel
		ResponseDetails_Panel.click();
		First_Name.sendKeys(First_Name_temp);
		//Middle_Name.sendKeys(Middle_Name_temp);
		LastName_Surname.sendKeys(LastName_Surname_temp);
		DOB_Field.click();
		Thread.sleep(1000);
		DOB_InCalender.click();
		/*String dateValue="Nov 8";
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("document.getElementById('dobPickerOverlay').value='"+dateValue+"'");*/

		WebDriverWait wait14 = new WebDriverWait(driver, 160);
		wait14.until(ExpectedConditions.elementToBeClickable(Citizenship_Field));
		Citizenship_Field.click();
		Thread.sleep(1000);

		// to make element visible:
		/*JavascriptExecutor js =(JavascriptExecutor)driver;
		String country="United States";
		String citizenship = "arguments[0].setAttribute('value','"+country+"')";
	    js.executeScript(citizenship, Citizenship_Field);	
		js.executeScript("document.getElementById('citizenShip').value='united States'");*/
		Citizenship_Field.sendKeys("India");
		//Citizenship_Field.sendKeys("test");

		Thread.sleep(1000);
		Citizenship_Country.click();
		PreviousClientEmp_None.click();
		String req_id=Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15);

		CandGID_field.sendKeys(req_id+"_"+new Random().nextInt(10000));
		CandReference_Field.sendKeys(req_id);

		//to scroll to an element
		// js.executeScript("arguments[0].scrollIntoView();", Flowdown_No);
		//Flowdown_No.click();

		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_Shakedown_IN();

		//Requirements attribute panel
		RequirementAttributes_Panel.click();
		FromDate_field.click();
		Thread.sleep(1000);
		FromDate_InCalender.click();
		EndDate_Field.click();
		Thread.sleep(1000);
		EndDate_InCalender.click();

		/*String FromDateValue = Requested_FromDate.getText().trim();
		js.executeScript("document.getElementById('mat-input-0').value='"+FromDateValue+"'");

		String EndDateValue = Requested_EndDate.getText().trim();
		//String ToDate = "arguments[0].setAttribute('value','"+EndDateValue+"')";
		js.executeScript("arguments[0].value='"+EndDateValue+"'", EndDate_Field);*/	

		Skill_Level.click(); 
		Thread.sleep(1000);
		Skill_Level_Value.click();

		/*Taking screenshot */
		Screenshots shot3=new Screenshots(driver);
		shot3.ScreenShot_Shakedown_IN();

		//pricing panel
		ResponsePricing_Panel.click();
		ST_BillRate.sendKeys(rate);
		
		/*Taking screenshot */
		Screenshots shot4=new Screenshots(driver);
		shot4.ScreenShot_Shakedown_IN();
		Save_Button.click();
		System.out.println("Response added and saved");

	}

	public void Submit() throws InterruptedException
	{

		WebDriverWait wait0 = new WebDriverWait(driver, 160);
		wait0.until(ExpectedConditions.elementToBeClickable(btn_Add_response));

		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver drive) {
				return Resp1_name.getText().length() != 0;
			}
		});

		SelectAll_Checkbox.click();
		Thread.sleep(1000);
		Submit_Button.click();
		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(ConfirmSubmit_Button));	

		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_Shakedown_IN();
		ConfirmSubmit_Button.click();

		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.elementToBeClickable(Withdraw_response));

		System.out.println("Response submitted");
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_Shakedown_IN();

	}

	public void Datepicker(String dateValue )
	{
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("document.getElementById('dobPickerOverlay').value='"+dateValue+"'");
	}

	public void OpenRequestWorkaround()
	{
		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Navigation));	
		Navigation.click();
		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(RequestId));	
		RequestId.click();
		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.visibilityOf(btn_Add_response));

		System.out.println("searched and Opened the request");
		if (feedback_PopUp.isDisplayed())
		{
			feedback_PopUp.click();
			System.out.println("feedback PopUp is Present and minimised");
		}
	}

}
