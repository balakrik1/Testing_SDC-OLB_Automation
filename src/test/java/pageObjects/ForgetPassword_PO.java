package pageObjects;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import apiRequest.VerifyGmailApi;
import base.BaseUtil;
import stepDefinition.GenericFunctions;
import stepDefinition.ScreenShotTaken;

public class ForgetPassword_PO {

	WebDriver driver;
	private BaseUtil base;
	ScreenShotTaken  SCREENSHOT = new ScreenShotTaken(base);
	GenericFunctions GENERIC;
	VerifyGmailApi MAIL;

	public ForgetPassword_PO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@id='reset-password-link']")	
	public WebElement forgetPassword_link;
	
	@FindBy(xpath = "//button[@id='forgotten-password']")	
	public WebElement forgetPassword_Continue_button;
	
	@FindBy(xpath = "//h2[contains(text(),'We have sent you an email with a link to change yo')]")	
	public WebElement forgetPassword_message_header;
	
	@FindBy(xpath = "//input[@id='password']")	
	public WebElement forgetPassword_password_textbox;
	
	@FindBy(xpath = "//input[@id='password-confirmation']")	
	public WebElement forgetPassword_confrimPassword_textbox;
	
	@FindBy(xpath = "//button[@type='button' and span='Change password']")	
	public WebElement forgetPassword_changePassword_button;
	
	@FindBy(xpath = "//h2[contains(text(),'Your password has been changed.')]")	
	public WebElement forgetPassword_success_message;
	
	@FindBy(xpath = "//*[@id='link-to-login']")	
	public WebElement forgetPassword_singintoOLB_link;
	
	

	
	public boolean verifyMessage() {
		boolean flag=false;
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.simpleSleep(1500);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.waitForWebElement(driver, forgetPassword_message_header);
			WebDriverWait objWait=new WebDriverWait(driver, 20);
			objWait.until(ExpectedConditions.elementToBeClickable(forgetPassword_message_header));
			if(forgetPassword_message_header.isDisplayed()){
				flag=true;
			}
		} catch(Exception e) {
			flag=false;
		}
		return flag;
	}
	
	
	public void enterNewPassword() {
		try{
			GENERIC=new GenericFunctions(driver);
			GENERIC.simpleSleep(1500);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.waitForWebElement(driver, forgetPassword_password_textbox);
			String updatedPassword=GENERIC.getProperty("UPDATED_DETAILS", "UPDATED_PASSWORD");
			GENERIC.enterTextByWebElement(forgetPassword_password_textbox, updatedPassword);
			GENERIC.simpleSleep(500);
			GENERIC.enterTextByWebElement(forgetPassword_confrimPassword_textbox, updatedPassword);
			GENERIC.simpleSleep(500);
			GENERIC.clickByWebElement(forgetPassword_changePassword_button);
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	public boolean verifyPasswordChangeMessage() {
		boolean flag=false;
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.simpleSleep(1500);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.waitForWebElement(driver, forgetPassword_success_message);
			WebDriverWait objWait=new WebDriverWait(driver, 30);
			objWait.until(ExpectedConditions.elementToBeClickable(forgetPassword_success_message));
			if(forgetPassword_success_message.isDisplayed()){
				flag=true;
			}
		} catch(Exception e) {
			flag=false;
		}
		return flag;
	}
	
	public String passwordResetLink() {
		String resetLink=null;
		try {
			 GENERIC=new GenericFunctions(driver);
			 MAIL=new VerifyGmailApi();
			 List<String> mailbody=MAIL.verifymailinbox(0, "noreply@preprod.account.oup.com", "Your Oxford University Press password");
			 resetLink= extractLinkFromBody(mailbody.get(0));
		} catch(Exception e) {
			System.out.println(e);
		}
		return resetLink;
	}
	
	public String extractLinkFromBody(String mailbody) {
		String resetLink=null;
		try{
			Document doc=Jsoup.parse(mailbody);
			Elements links = doc.select("a[href]");
			List<String> linkFromMail=null;
			  for (Element link : links) {
	                // get the value from href attribute
	                System.out.println("\nlink : " + link.attr("href"));	
	            }
			  resetLink=links.get(1).attr("href");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return resetLink;
	}
}
