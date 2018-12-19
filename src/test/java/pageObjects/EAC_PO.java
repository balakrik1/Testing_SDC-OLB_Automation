package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseUtil;
import stepDefinition.GenericFunctions;
import stepDefinition.ScreenShotTaken;

public class EAC_PO {
	

	WebDriver driver;
	private BaseUtil base;
	ScreenShotTaken SCREENSHOT = new ScreenShotTaken(base);
	GenericFunctions GENERIC;
	OLBHome_PO HOME;
	

	public EAC_PO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='j_username']")
	public WebElement EAC_Username_textbox;
	
	@FindBy(xpath="//input[@id='j_password']")
	public WebElement EAC_Password_textbox;
	
	@FindBy(xpath="//*[@id='submit']")
	public WebElement EAC_Submit_button;
	
	@FindBy(xpath="//a[@href='/eacAdmin/customer/create']")
	public WebElement EAC_create_link;
	
	@FindBy(xpath="//input[@id='customerFirstName']")
	public WebElement EAC_customerFirstName_textbox;
	
	@FindBy(xpath="//input[@id='customerFamilyName']")
	public WebElement EAC_customerFamilyName_textbox;
	
	@FindBy(xpath="//input[@id='customerEmailAddress']")
	public WebElement EAC_customerEmailAddress_textbox;
	
	@FindBy(xpath="//button[@id='save']")
	public WebElement EAC_save_button;
	
	@FindBy(xpath=" //a[@href='#pagetab-1']")
	public WebElement EAC_customer_tab;
	
	@FindBy(xpath=" //a[@href='#pagetab-2']")
	public WebElement EAC_credential_tab;
	
	@FindBy(xpath="//input[@id='customerUsername']")
	public WebElement EAC_customerUsername_textbox;
	
	
	@FindBy(xpath="//input[@id='password']")
	public WebElement EAC_credPassword_textbox;
	
	@FindBy(xpath="//input[@id='passwordAgain']")
	public WebElement EAC_credConfirmPassword_textbox;
	
	@FindBy(xpath="//div[@class='success' and contains(text(),'Customer was created successfully')]")
	public WebElement EAC_customerAdded_header;
	
	@FindBy(xpath="//input[@id='userName']")
	public WebElement leagacyuser_username_textbox;
	
	@FindBy(xpath="//*[@id='partialUpdateForm']/div[2]/div/label")
	public WebElement leagacyuser_toc_checkbox;
	
	@FindBy(xpath="//button[contains(text(),'Update my account')]")
	public WebElement leagacyuser_update_button;
	
	@FindBy(xpath="//h2[contains(text(),'Please click on the button below to continue')]")
	public WebElement leagacyuser_success_header;
	
	@FindBy(xpath="//button[@type='button' and @onclick and contains(text(),'Start')]")
	public WebElement leagacyuser_start_button;
	
	
	public void enterNewUserName() {
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.simpleSleep(1000);
			GENERIC.waitForthePageToLoad(driver);
			GenericFunctions.newLegacyUserName="testaces.auto+olbleagcy_"+GENERIC.getProperty("EMAIL_COUNTER", "COUNTER")+"@gmail.com";
			GENERIC.simpleSleep(1500);
			GENERIC.enterTextByWebElement(leagacyuser_username_textbox, GenericFunctions.newLegacyUserName);
			GENERIC.simpleSleep(1500);
			Actions onjActions=new Actions(driver);
			onjActions.moveToElement(leagacyuser_toc_checkbox).click().build().perform();
			GENERIC.simpleSleep(1500);
			GENERIC.clickByWebElement(leagacyuser_update_button);
		}  catch(Exception e) {
			System.out.println("** Error in filling  NewUserName\n"+e);
		}
	}
	
	
	public boolean verifyUpdatedLegacyUserMessage() {
		boolean flag=false;
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.simpleSleep(1500);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.waitForWebElement(driver, leagacyuser_success_header);
			WebDriverWait objWait=new WebDriverWait(driver, 20);
			objWait.until(ExpectedConditions.elementToBeClickable(leagacyuser_success_header));
			if(leagacyuser_success_header.isDisplayed()){
				flag=true;
			}
		} catch(Exception e) {
			flag=false;
		}
		return flag;
	}
	
	
	
	
	
	public void fillCustomerDetails(){
		try{
			GENERIC=new GenericFunctions(driver);
			GENERIC.simpleSleep(500);
			GENERIC.clickByWebElement(EAC_create_link);
			GENERIC.clickByWebElement(EAC_customer_tab);
			GENERIC.simpleSleep(500);
			GENERIC.enterTextByWebElement(EAC_customerFirstName_textbox, "legacyuser");
			GENERIC.simpleSleep(500);
			GENERIC.enterTextByWebElement(EAC_customerFamilyName_textbox, "legacyfamily");
			GENERIC.simpleSleep(500);
			GENERIC.enterTextByWebElement(EAC_customerEmailAddress_textbox, GENERIC.getProperty("EAC", "EAC_EMAIL"));
			GENERIC.simpleSleep(500);
		} catch(Exception e) {
			System.out.println("** Error in filling  CustomerDetails\n"+e);
		}
	}
	
	public void fillCustomerCredentials(){
		try{
			GENERIC=new GenericFunctions(driver);
			HOME=new OLBHome_PO(driver);
			GENERIC.simpleSleep(500);
			GENERIC.clickByWebElement(EAC_credential_tab);
			GENERIC.simpleSleep(500);
			HOME.updateEmailIDCounter();
			GenericFunctions.eacLegacyUsername="legacyuser"+GENERIC.getProperty("EMAIL_COUNTER", "COUNTER");
			GENERIC.enterTextByWebElement(EAC_customerUsername_textbox, GenericFunctions.eacLegacyUsername);
			GENERIC.simpleSleep(500);
			GENERIC.enterTextByWebElement(EAC_credPassword_textbox, GENERIC.getProperty("EAC", "EAC_PASSWORD"));
			GENERIC.simpleSleep(500);
			GENERIC.enterTextByWebElement(EAC_credConfirmPassword_textbox, GENERIC.getProperty("EAC", "EAC_PASSWORD"));
			GENERIC.simpleSleep(500);
			GENERIC.clickByWebElement(EAC_save_button);
		} catch(Exception e) {
			System.out.println("** Error in filling  CustomerDetails\n"+e);
		}
	}
	

	public boolean verifyMessage() {
		boolean flag=false;
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.simpleSleep(1500);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.waitForWebElement(driver, EAC_customerAdded_header);
			WebDriverWait objWait=new WebDriverWait(driver, 20);
			objWait.until(ExpectedConditions.elementToBeClickable(EAC_customerAdded_header));
			if(EAC_customerAdded_header.isDisplayed()){
				flag=true;
			}
		} catch(Exception e) {
			flag=false;
		}
		return flag;
	}
	
	public void createLeagacyUser() {
		try{
			fillCustomerDetails();
			fillCustomerCredentials();
			
		} catch(Exception e) {
			System.out.println("** Error in creating Legacy User\n"+e);
		}
	}
	

}
