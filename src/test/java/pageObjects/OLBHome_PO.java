package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseUtil;
import stepDefinition.GenericFunctions;
import stepDefinition.ScreenShotTaken;

public class OLBHome_PO {

	WebDriver driver;
	private BaseUtil base;
	ScreenShotTaken  SCREENSHOT = new ScreenShotTaken(base);
	GenericFunctions GENERIC;

	public OLBHome_PO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// REGISTER AND SIGN IN BUTTON
	@FindBy(xpath = "(//button[@type='button' and @href='/modal/user/register.html'])[1]")	
	public WebElement HOME_REGISTER_BUTTON;
	
	@FindBy(xpath = "//button[@title='Sign in' and @type='button']")	
	public WebElement HOME_SIGN_BUTTON;
	
	// REGISTRATION PAGE
	@FindBy(xpath = "//input[@id='firstName']")	
	public WebElement REGISTER_FIRSTNAME_TEXTBOX;
	
	@FindBy(xpath = "//input[@id='lastName']")	
	public WebElement REGISTER_LASTNAME_TEXTBOX;
	
	@FindBy(xpath = "//input[@id='userName']")	
	public WebElement REGISTER_USERNAME_TEXTBOX;
	
	@FindBy(xpath = " //input[@id='password']")	
	public WebElement REGISTER_PASSWORD_TEXTBOX;
	
	@FindBy(xpath = "//input[@id='confirmPassword']")	
	public WebElement REGISTER_CONFIRMPASSWORD_TEXTBOX;
	
	@FindBy(xpath = "//input[@type='checkbox' and @name='toc']")	
	public WebElement REGISTER_TERMS_CHECKBOX;
	
	@FindBy(xpath = "//button[@type='submit' and text()='Create my account']")	
	public WebElement REGISTER_CREATEMYACCOUNT_BUTTON;
	
	//AFTER REG SUCCESS MESSAGE
	@FindBy(xpath = "(//h1)[1]")	
	public WebElement REGISTER_SUCCESSMESSGAE_HEADER;
	
	@FindBy(xpath = "//*[@id='signupSuccessModal']//button[@type='button' and @onclick]")	
	public WebElement REGISTER_STARTOLB_BUTTON;
	
	
	
	/*public void registerNewUser() {
		try {
			GENERIC=new GenericFunctions(driver);
		} catch(Exception e) {
			System.out.println("** Error in registerNewUser"+e);
		}
	}*/
	
	public void updateEmailIDCounter() {
		try {
			GENERIC=new GenericFunctions(driver);
			String valueFromCounter=GENERIC.getProperty("EMAIL_COUNTER", "COUNTER");
			int temp=Integer.parseInt(valueFromCounter);
			temp=temp+1;
			String valueToCounter=String.valueOf(temp);
			GENERIC.removeProperty("EMAIL_COUNTER", "COUNTER");
			GENERIC.writeDateinPropertyFile("EMAIL_COUNTER", "COUNTER",valueToCounter);
			
		} catch(Exception e) {
			System.out.println("Error in Updating the counter"+e);
		}
	}
			
	public void enterUserDetails(String fname, String lname, String email) {
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.simpleSleep(1000);
			String password=GENERIC.getProperty("PASSWORD", "password");
			String confirmPassword=GENERIC.getProperty("PASSWORD", "password");
			String temp=GENERIC.getProperty("EMAIl_COUNTER", "COUNTER");
			String emailIDFromFile=GENERIC.getProperty(email, email);
			email=emailIDFromFile+temp+"@gmail.com";
			GENERIC.waitForWebElement(driver, REGISTER_FIRSTNAME_TEXTBOX);
			GENERIC.simpleSleep(1500);
			REGISTER_FIRSTNAME_TEXTBOX.clear();
			REGISTER_FIRSTNAME_TEXTBOX.sendKeys(fname);
			GENERIC.simpleSleep(1500);
			REGISTER_LASTNAME_TEXTBOX.clear();
			REGISTER_LASTNAME_TEXTBOX.sendKeys(lname);
			GENERIC.simpleSleep(1500);
			REGISTER_USERNAME_TEXTBOX.clear();
			REGISTER_USERNAME_TEXTBOX.sendKeys(email);
			GENERIC.simpleSleep(1500);
			REGISTER_PASSWORD_TEXTBOX.clear();
			REGISTER_PASSWORD_TEXTBOX.sendKeys(password);
			GENERIC.simpleSleep(1500);
			REGISTER_CONFIRMPASSWORD_TEXTBOX.clear();
			REGISTER_CONFIRMPASSWORD_TEXTBOX.sendKeys(confirmPassword);
			GENERIC.simpleSleep(1500);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", REGISTER_TERMS_CHECKBOX);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", REGISTER_TERMS_CHECKBOX);
		} catch(Exception e) {
			System.out.println("** Error in enterUserDetails"+e);
		}
	}
	
	public void registerNewUser(String fname, String lname, String email) {
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.simpleSleep(1500);
			if(fname.contains("NONENG")) {
				enterUserDetails( "اختبار",  "المستعمل",  email);
			} else {
				enterUserDetails( fname,  lname,  email);
			}
			
			GENERIC.simpleSleep(1500);
			GENERIC.waitForWebElement(driver, REGISTER_CREATEMYACCOUNT_BUTTON);
			GENERIC.clickByWebElement(REGISTER_CREATEMYACCOUNT_BUTTON);
			updateEmailIDCounter();
		} catch(Exception e) {
			System.out.println("** Error in registerNewUser"+e);
		}
	}
	
	public boolean verifyConfirmationMessage(String messageName,WebElement element) {
		boolean temp=false;
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.simpleSleep(1500);
			WebDriverWait objWait=new WebDriverWait(driver, 30);
			objWait.until(ExpectedConditions.elementToBeClickable(REGISTER_STARTOLB_BUTTON));
			GENERIC.waitForWebElement(driver, element);
			String messageFromFile=GENERIC.getProperty("MESSAGES", messageName);
			String messageFromUI=element.getText().trim().replaceAll("\n", " ").toString();
			if(messageFromUI.contains(messageFromFile)) {
				temp=true;
			}
			
		} catch(Exception e) {
			temp=false;
			System.out.println("** Error in Verifying the message"+e);
		}
		return temp;
	}
	
	public void storeUserNametoPropFile(String fileName, String propertyName) {
		try {
			GENERIC=new GenericFunctions(driver);
			try{
			GENERIC.removeProperty(fileName, propertyName);
			} catch(Exception e) { System.err.println("**Issue in removing the property"); }
			String EmailIDCounterfromFile=GENERIC.getProperty("EMAIL_COUNTER", "COUNTER");
			int temp=Integer.parseInt(EmailIDCounterfromFile)-1;
			String tempCounter=String.valueOf(temp);
			String propertyValue=GENERIC.getProperty("EMAIL_ID", "EMAIL_ID");
			String userName=propertyValue+tempCounter+"@gmail.com";
			GENERIC.writeDateinPropertyFile(fileName, propertyName, userName);
		} catch(Exception e) {
			System.out.println("** Error in storing the credential to the file"+e);
		}
	}
	
	public void storeUserNametoFile(String fileName, String propertyName) {
		try {
			GENERIC=new GenericFunctions(driver);
			String EmailIDCounterfromFile=GENERIC.getProperty("EMAIL_COUNTER", "COUNTER");
			int temp=Integer.parseInt(EmailIDCounterfromFile)-1;
			String tempCounter=String.valueOf(temp);
			String propertyValue=GENERIC.getProperty("EMAIL_ID", "EMAIL_ID");
			String userName=propertyValue+tempCounter+"@gmail.com";
			propertyName=propertyName+"_"+tempCounter;
			GENERIC.writeDateinPropertyFile(fileName, propertyName, userName);
		} catch(Exception e) {
			System.out.println("** Error in storing the credential to the file"+e);
		}
	}
	
	public String getUserNameFromFile(String propertyValue) {
		String userNameFormFile=null;
		try {
			GENERIC=new GenericFunctions(driver);
			String EmailIDCounterfromFile=GENERIC.getProperty("EMAIL_COUNTER", "COUNTER");
			int temp=Integer.parseInt(EmailIDCounterfromFile)-1;
			String tempCounter=String.valueOf(temp);
			propertyValue=propertyValue+"_"+tempCounter;
			userNameFormFile=GENERIC.getProperty("CREDENTIALS", propertyValue);
		} catch(Exception e) {
			System.out.println("** Error in retriving the credential from the file"+e);
		}
		return userNameFormFile;
	}
	
			
			
			
			
}
