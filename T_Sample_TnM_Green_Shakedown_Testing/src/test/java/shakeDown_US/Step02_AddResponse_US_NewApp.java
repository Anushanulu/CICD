package shakeDown_US;



import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import generics.Screenshots;
import lib.Excel;

public class Step02_AddResponse_US_NewApp
{

	private WebDriver driver;
	public static String xlsFilePath = System.getProperty("user.dir") + "\\src/test/resources\\testdata\\testdata.xls";
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

	//Add Response and details	
	@FindBy (xpath ="//button[contains(text(),'Add Response')]") private WebElement btn_Add_response; 
	@FindBy (xpath ="//span[@class='headerTitle' and contains(text(),'Attachments')]") private WebElement Attachments_Panel; 
	@FindBy (xpath ="//button[contains(@for,'file-uploader') and contains(text(),' Add Response File ')]//parent::div/input" ) private WebElement ResponseFile ;

	@FindBy (xpath ="//button[@class='_hj-OO1S1__styles__openStateToggle']") private WebElement feedback_PopUp; 

	@FindBy (xpath ="//span[@class='headerTitle' and contains(text(),'Response Details')]") private WebElement ResponseDetails_Panel; 


	@FindBy ( xpath= "//input[@formcontrolname='firstName']" ) private WebElement First_Name ;
	@FindBy ( xpath= "//input[@formcontrolname='middleName']" ) private WebElement Middle_Name ;
	@FindBy ( xpath= "//input[@formcontrolname='lastName']" ) private WebElement LastName_Surname ;
	@FindBy ( id="dobPickerOverlay") private WebElement DOB_Field ;
	@FindBy (xpath= "//mat-calendar[contains(@id,'mat-datepicker')]/div/mat-month-view/table/tbody/tr[4]/td[3]") private WebElement DOB_InCalender;

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

	@FindBy ( xpath="//clapp-skill-response-detail[@class='ng-star-inserted']/div[1]/button[2]") private WebElement Save_Button ;


	//Submit Page
	@FindBy (xpath = "//input[@id='selectAll']/parent::label[@class='container']/span[@class='checkmark']") private WebElement SelectAll_Checkbox;
	@FindBy (xpath = "//input[@id='checkBoxId-001-0']/parent::label/span[@class='checkmark']") private WebElement Resp1_Checkbox;
	@FindBy (xpath = "//table[@class='custom-grid']/div[@class='ng-star-inserted']/tr[1]/td[2]/a") private WebElement Resp1_name;


	@FindBy (xpath = "//button[@ibmbutton='secondary' and contains(text(),'Submit')]") private WebElement Submit_Button;
	@FindAll({@FindBy(xpath = "//div[@class='mat-drawer-backdrop ng-star-inserted mat-drawer-shown']")}) private List<WebElement>  ResponseForm_OverLay ;
	@FindAll({@FindBy(xpath = "//section[@class='bx--modal bx--modal-tall is-visible']")}) private List<WebElement>  ActionWindow_OverLay ;
	@FindBy (xpath = "//button[@ibmbutton='primary' and contains(text(),'Confirm submit')]") private WebElement ConfirmSubmit_Button;

	@FindBy (xpath = "//button[@class='bx--btn bx--btn--secondary' and contains(text(),'Withdraw')]") private WebElement Withdraw_Button;
	@FindBy (xpath = "//button[@class='bx--btn bx--btn--primary ng-star-inserted' and contains(text(),'Confirm withdraw')]") private WebElement ConfirmWithdraw_Button;
	@FindBy (xpath = "//button[@class='bx--list-box__field']") private WebElement Withdraw_Reason_dropdown;
	@FindBy (xpath = "//div[@class='bx--list-box__menu-item__option' and contains(text(),'Correction required')]") private WebElement Withdraw_Reason_value;
	@FindBy (xpath = "//button[@class='bx--btn bx--btn--secondary' and contains(text(),'Reactivate')]") private WebElement Reactivate_Button;
	@FindBy (xpath = "//button[@class='bx--btn bx--btn--primary ng-star-inserted' and contains(text(),'Confirm reactivation')]") private WebElement Confirm_Reactivation;
	@FindBy ( xpath="//clapp-skill-response-detail[@class='ng-star-inserted']/div[1]/button[2]") private WebElement Reprice_Button ;
	// Initialize the web elements 
	public Step02_AddResponse_US_NewApp (WebDriver driver)
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
			//UsernameField.clear();
			UsernameField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 0));

			ContinueButton.click();

			WebDriverWait wait9 = new WebDriverWait(driver, 160);
			wait9.until(ExpectedConditions.visibilityOf(SigninButton));

			PasswordField.clear();
			PasswordField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 1));

			/*Taking screenshot */
			Screenshots shot=new Screenshots(driver);
			shot.ScreenShot_Shakedown_US();

			SigninButton.click();
			System.out.println("Sign in button clicked after entering credentials");
		} else
		{

			WebDriverWait wait2 = new WebDriverWait(driver, 160);
			wait2.until(ExpectedConditions.visibilityOf(UsernameField));

			UsernameField.clear();
			UsernameField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 0));

			PasswordField.clear();
			PasswordField.sendKeys(Excel.getCellValue(xlsFilePath, sheet, 1, 1));

			/*Taking screenshot */
			Screenshots shot1=new Screenshots(driver);
			shot1.ScreenShot_Shakedown_US();
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
		shot.ScreenShot_Shakedown_US();
		Request_Number_link.click();

		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.visibilityOf(btn_Add_response));

		System.out.println("searched and Opened the request "+Excel.getCellValue(xlsFilePath, "Request_creation", 1, 15));
		if (feedback_PopUp.isDisplayed())
		{
			feedback_PopUp.click();
			System.out.println("feedback PopUp was Present and minimised");
		}
		else
		{	System.out.println("feedback PopUp was not Present");
		};
	}


	//Add Response and details
	public void FillRespdetails(String First_Name_temp , String Middle_Name_temp , String LastName_Surname_temp, String rate, String wage) throws IOException, InterruptedException 
	{

		waitForFormOverlayDisappear();
		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.elementToBeClickable(btn_Add_response));
	
		/*Taking screenshot */
		Screenshots shot=new Screenshots(driver);
		shot.ScreenShot_Shakedown_US();
		//Thread.sleep(8000);
		btn_Add_response.click();

		//Attachment Panel
		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(Attachments_Panel));
		System.out.println("Candidate form opened");
		
		Attachments_Panel.click();

		WebDriverWait wait5 = new WebDriverWait(driver, 160);
		wait5.until(ExpectedConditions.visibilityOf(ResponseFile));

		System.out.println("found response file button");
		String AttachmentPath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata\\Response attachment.txt";
		Thread.sleep(500);
		ResponseFile.sendKeys(AttachmentPath);
		System.out.println("attachment uploaded--Filled info in Attachment Panel");
	
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_Shakedown_US();

		//response details panel
		ResponseDetails_Panel.click();
		First_Name.sendKeys(First_Name_temp);
		//Middle_Name.sendKeys(Middle_Name_temp);
		LastName_Surname.sendKeys(LastName_Surname_temp);
		DOB_Field.click();
		Thread.sleep(1000);
		DOB_InCalender.click();

		WebDriverWait wait14 = new WebDriverWait(driver, 160);
		wait14.until(ExpectedConditions.elementToBeClickable(Citizenship_Field));
		Citizenship_Field.click();
		Thread.sleep(1000);
		Citizenship_Field.sendKeys("united states");

		Thread.sleep(1000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Citizenship_Country);
		PreviousClientEmp_None.click();

		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_Shakedown_US();
		System.out.println("Filled info in response details Panel");
		
		//Requirements attribute panel
		
		executor.executeScript("arguments[0].click();", RequirementAttributes_Panel);
		executor.executeScript("arguments[0].click();", FromDate_field);
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();", FromDate_InCalender);
		executor.executeScript("arguments[0].click();", EndDate_Field);
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();", EndDate_InCalender);
		executor.executeScript("arguments[0].click();", Skill_Level);
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();", Skill_Level_Value);

		/*Taking screenshot */
		Screenshots shot3=new Screenshots(driver);
		shot3.ScreenShot_Shakedown_US();
		System.out.println("Filled info in Requirements attribute  Panel");
		
		//pricing panel
		ResponsePricing_Panel.click();
		ST_BillRate.clear();
		ST_BillRate.sendKeys(rate);

		ST_Wage.clear();
		ST_Wage.sendKeys(wage);
		/*Taking screenshot */
		Screenshots shot4=new Screenshots(driver);
		shot4.ScreenShot_Shakedown_US();
		System.out.println("Filled info in pricing Panel");
		
		Save_Button.click();
		System.out.println("Response created and saved");

	}

	public void Submit() throws InterruptedException
	{
		waitForFormOverlayDisappear();
		isloadComplete(driver);
		WebDriverWait wait0 = new WebDriverWait(driver, 160);
		wait0.until(ExpectedConditions.visibilityOf(Resp1_Checkbox));

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Resp1_Checkbox);
		
		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.elementToBeClickable(Submit_Button));
		Thread.sleep(500);
		executor.executeScript("arguments[0].click();", Submit_Button);
		
		WebDriverWait wait4 = new WebDriverWait(driver, 160);
		wait4.until(ExpectedConditions.visibilityOf(ConfirmSubmit_Button));	

		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_Shakedown_US();
		executor.executeScript("arguments[0].click();", ConfirmSubmit_Button);

		System.out.println("Response submitted to Requester");
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_Shakedown_US();

	}

	public void WithdrawResponse() throws InterruptedException
	{
		waitForFormOverlayDisappear();
		waitForActionWindowOverlayDisappear();

		isloadComplete(driver);
	
		WebDriverWait wait0 = new WebDriverWait(driver, 160);
		wait0.until(ExpectedConditions.visibilityOf(Resp1_Checkbox));

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Resp1_Checkbox);
		
		Thread.sleep(1000);
		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.elementToBeClickable(Withdraw_Button));
		executor.executeScript("arguments[0].click();", Withdraw_Button);
		
		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.elementToBeClickable(ConfirmWithdraw_Button));
		executor.executeScript("arguments[0].click();", Withdraw_Reason_dropdown);
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();", Withdraw_Reason_value);

		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_Shakedown_US();
		executor.executeScript("arguments[0].click();", ConfirmWithdraw_Button);

		System.out.println("Response withdrawn");

	}
	public void ReactivateResponse() throws InterruptedException
	{
		waitForActionWindowOverlayDisappear();
		isloadComplete(driver);

		WebDriverWait wait0 = new WebDriverWait(driver, 160);
		wait0.until(ExpectedConditions.visibilityOf(Resp1_Checkbox));

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Resp1_Checkbox);
		
		//Actions actions=new Actions(driver);
		//actions.moveToElement(Reactivate_Button).sendKeys(Keys.PAGE_DOWN).build().perform();;
		
		
		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.elementToBeClickable(Reactivate_Button));
		Thread.sleep(1000);
		executor.executeScript("arguments[0].click();", Reactivate_Button);


		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.visibilityOf(Confirm_Reactivation));
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_Shakedown_US();
		executor.executeScript("arguments[0].click();", Confirm_Reactivation);

		System.out.println("Response Reactivated");
	}
	public void EditReactivatedResponse(String resubmitSTrate) throws InterruptedException
	{
		waitForActionWindowOverlayDisappear();
	
		isloadComplete(driver);
		//Thread.sleep(10000);
		WebDriverWait wait0 = new WebDriverWait(driver, 160);
		wait0.until(ExpectedConditions.visibilityOf(Resp1_name));

		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Resp1_name);
		
		Thread.sleep(1000);
		WebDriverWait wait6 = new WebDriverWait(driver, 160);
		wait6.until(ExpectedConditions.visibilityOf(Attachments_Panel));
		executor.executeScript("arguments[0].click();", Attachments_Panel);

		//pricing panel
		executor.executeScript("arguments[0].click();", ResponsePricing_Panel);
	
		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.visibilityOf(ST_BillRate));
		/*Taking screenshot */
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_Shakedown_US();
		
		ST_BillRate.clear();
		ST_BillRate.sendKeys(resubmitSTrate);
		ST_Wage.clear();
		ST_Wage.sendKeys(resubmitSTrate);
		/*Taking screenshot */
		Screenshots shot2=new Screenshots(driver);
		shot2.ScreenShot_Shakedown_US();
		
		Save_Button.click();
		System.out.println("Reactivated Response was modified and saved");
	}

	public void Reprice(String rate) throws InterruptedException
	{
		waitForFormOverlayDisappear();
	
		isloadComplete(driver);
		//Thread.sleep(10000);
		WebDriverWait wait0 = new WebDriverWait(driver, 160);
		wait0.until(ExpectedConditions.visibilityOf(Resp1_name));
		Thread.sleep(1000);
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Resp1_name);
		
		WebDriverWait wait1 = new WebDriverWait(driver, 160);
		wait1.until(ExpectedConditions.visibilityOf(Attachments_Panel));
		executor.executeScript("arguments[0].click();", Attachments_Panel);

		//pricing panel
		executor.executeScript("arguments[0].click();", ResponsePricing_Panel);

		WebDriverWait wait2 = new WebDriverWait(driver, 160);
		wait2.until(ExpectedConditions.visibilityOf(ST_BillRate));
		ST_BillRate.clear();
		ST_BillRate.sendKeys(rate);
		ST_Wage.clear();
		ST_Wage.sendKeys(rate);
		Screenshots shot1=new Screenshots(driver);
		shot1.ScreenShot_Shakedown_US();
		Reprice_Button.click();
		System.out.println("Response repriced");

	}
	public static boolean isloadComplete(WebDriver driver)
	{
		return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("loaded")
				|| ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}

	public void waitForFormOverlayDisappear() throws InterruptedException
	{
		int count=0;
		while(ResponseForm_OverLay.size()!=0 && count<20)
		{
			Thread.sleep(1000);
			count++;
		}}
	public void waitForActionWindowOverlayDisappear() throws InterruptedException
	{
		int count=0;
		while(ActionWindow_OverLay.size()!=0 && count<20)
		{
			Thread.sleep(1000);
			count++;
		}}

	public static void Wraperclick(WebDriver driver, WebElement element) {
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
}