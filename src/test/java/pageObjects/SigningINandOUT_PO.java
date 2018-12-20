package pageObjects;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;

import base.BaseUtil;
import stepDefinition.GenericFunctions;
import stepDefinition.ScreenShotTaken;


public class SigningINandOUT_PO {
	
	WebDriver driver;
	private BaseUtil base;
	GenericFunctions GENERIC;
	ScreenShotTaken SCREENSHOT = new ScreenShotTaken(base);

	public SigningINandOUT_PO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//SIGN IN PAGE
	@FindBy(xpath = "//*[@id='username']")	
	public WebElement SIGNIN_USERNAME_TEXTBOX;
	
	@FindBy(xpath = "//*[@id='password']")	
	public WebElement SIGNIN_PASSOWORD_TEXTBOX;
	
	@FindBy(xpath = "//button[@id='signin-button']")	
	public WebElement SIGNIN_SIGNIN_BUTTON;
	
	@FindBy(xpath = "//button[@title='Sign in' and @type='button' and @onclick]")	
	public WebElement SIGNIN_BUTTON;
	
	
	//ACCOUNT WIDGET
	@FindBy(xpath = " //a[@id='profileDropdown' and @role='button' and @data-toggle='dropdown']")
	public WebElement ACCOUNTWIDGET_LINK_DROPDOWN;
	
	@FindBy(xpath = "//div[@id='edit-profile-button']")
	public WebElement ACCOUNTWIDGET_EDIT_LINK;
	
	@FindBy(xpath = "(//h5)[2]")
	public WebElement ACCOUNTWIDGET_USERNAME_HEADER;
	
	@FindBy(xpath = "//div[@id='signout-button']")
	public WebElement ACCOUNTWIDGET_SIGNOUT_LINK;
	
	
	public void clickSignInLink() {
		try {
			GENERIC= new GenericFunctions(driver);
			WebDriverWait objWait=new WebDriverWait(driver, 10);
			Thread.sleep(1100);
			WebElement ele=objWait.until(ExpectedConditions.elementToBeClickable(SIGNIN_BUTTON));
			if(SIGNIN_BUTTON.isDisplayed()) {
				ele.click();
				Thread.sleep(1100);
			}
		} catch(Exception e) {
			System.out.println(" No Sign In Button");
			
		}
	}
	
	public void enterLoginDetails(String username, String password) throws Exception {
	
		GENERIC= new GenericFunctions(driver);
		try {
			GENERIC.waitForthePageToLoad(driver);
			WebDriverWait objWait=new WebDriverWait(driver, 30);
			Assert.assertTrue(GENERIC.verifyElement(SIGNIN_USERNAME_TEXTBOX));
			objWait.until(ExpectedConditions.elementToBeClickable(SIGNIN_USERNAME_TEXTBOX));
			Thread.sleep(3000);
			SIGNIN_USERNAME_TEXTBOX.clear();
			SIGNIN_USERNAME_TEXTBOX.sendKeys(username);
			Thread.sleep(2000);
			SIGNIN_PASSOWORD_TEXTBOX.clear();
			SIGNIN_PASSOWORD_TEXTBOX.sendKeys(password);
			Thread.sleep(2000);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", SIGNIN_SIGNIN_BUTTON);
		} catch (Exception e) {
			System.err.println("** Error in signin\n"+e);
			
		}
	}
	
	public boolean verifyUserName(String userNameFromFile) throws IOException, Exception {
		boolean flag=true;
		try {
			GENERIC= new GenericFunctions(driver);
			GENERIC.simpleSleep(1000);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.waitForWebElement(driver, ACCOUNTWIDGET_LINK_DROPDOWN);
			WebDriverWait objWait=new WebDriverWait(driver, 30);
			WebElement ele=objWait.until(ExpectedConditions.visibilityOf(ACCOUNTWIDGET_LINK_DROPDOWN));
			ele=objWait.until(ExpectedConditions.elementToBeClickable(ACCOUNTWIDGET_LINK_DROPDOWN));
			ele.click();
			String userNameFromUI=ACCOUNTWIDGET_USERNAME_HEADER.getText().trim().toString();
			if(userNameFromUI.contains(userNameFromFile)) {
				flag=true;
			}
		} catch(Exception e) {
			flag=false;
		}
		return flag;
	}
	
	public void signOut() throws IOException, Exception {
		String currenturl=null;
		try {
			GENERIC= new GenericFunctions(driver);
			GENERIC.simpleSleep(1000);
			GENERIC.waitForWebElement(driver, ACCOUNTWIDGET_LINK_DROPDOWN);
			GENERIC.clickByWebElement(ACCOUNTWIDGET_LINK_DROPDOWN);
			GENERIC.simpleSleep(1000);
			GENERIC.waitForWebElement(driver, ACCOUNTWIDGET_SIGNOUT_LINK);
			GENERIC.clickByWebElement(ACCOUNTWIDGET_SIGNOUT_LINK);
		} catch(Exception e) {
			currenturl=driver.getCurrentUrl();
			if(currenturl.contains("preprod")) {
				driver.get("https://preprod.secure.account.oup.com/idp/profile/Logout");
			} if(currenturl.contains("dev")) {
				driver.get("https://dev.secure.account.oup.com/idp/profile/Logout");
			} if(currenturl.contains("uat")) {
				driver.get("https://uat.secure.account.oup.com/idp/profile/Logout");
			} if(currenturl.contains("test")) {
				driver.get("https://test.secure.account.oup.com/idp/profile/Logout");
			}
			
			System.err.println(" ** Error in Signing Out from the browser\n"+e);
		}
	}
	
	public void clickEditProfile() {
		try {
			GENERIC= new GenericFunctions(driver);
			GENERIC.simpleSleep(1000);
			GENERIC.waitForWebElement(driver, ACCOUNTWIDGET_LINK_DROPDOWN);
			ACCOUNTWIDGET_LINK_DROPDOWN.click();
			GENERIC.simpleSleep(1000);
			WebDriverWait objWait=new WebDriverWait(driver, 20);
			WebElement ele=objWait.until(ExpectedConditions.elementToBeClickable(ACCOUNTWIDGET_EDIT_LINK));
			ele.click();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	

	

}
