package stepDefinition;

import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;

import apiRequest.VerifyGmailApi;
import base.BaseUtil;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.*;
import pageObjects.ActivationCode_PO;
import pageObjects.EAC_PO;
import pageObjects.EditProfile_PO;
import pageObjects.ForgetPassword_PO;
import pageObjects.OLBHome_PO;
import pageObjects.SigningINandOUT_PO;
import pageObjects.UserHome_PO;

public class LegacyUser_SD extends BaseUtil {
	
	private BaseUtil base;
	WebDriver driver;

	public LegacyUser_SD(BaseUtil base)  {
		this.base=base;
	}
	

	GenericFunctions 	GENERIC;
	OLBHome_PO	     	OLB_HOME;
	SigningINandOUT_PO 	LOGIN;
	UserHome_PO			USER_HOME;
	ActivationCode_PO	ACTIVATIIONCODE;
	EditProfile_PO		EDIT;
	ForgetPassword_PO	PASSWORD;
	VerifyGmailApi		MAIL;
	EAC_PO 				EAC;
	ScreenShotTaken  	SCREENSHOT = new ScreenShotTaken(base);
	
	@When("^user is on the EAC homepage after entering the \"([^\"]*)\" and \"([^\"]*)\" for \"([^\"]*)\" Environment$")
	public void user_is_on_the_EAC_homepage_after_entering_the_and(String username, String password,String env) throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 EAC=new EAC_PO(base.driver);
		 LOGIN.clickSignInLink();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 if(env.contains("PP")) {
			 username=GENERIC.getProperty("EAC", "EAC_PP_USERNAME");
			 password=GENERIC.getProperty("EAC", "EAC_PP_PASSWORD");
		 } else if(env.contains("TEST")) {
			 username=GENERIC.getProperty("EAC", "EAC_TEST_USERNAME");
			 password=GENERIC.getProperty("EAC", "EAC_TEST_PASSWORD");
		 } else if(env.contains("UAT")) {
			 username=GENERIC.getProperty("EAC", "EAC_UAT_USERNAME");
			 password=GENERIC.getProperty("EAC", "EAC_UAT_PASSWORD");
		 }
		 GENERIC.enterTextByWebElement(EAC.EAC_Username_textbox, username);
		 GENERIC.simpleSleep(1000);
		 GENERIC.enterTextByWebElement(EAC.EAC_Password_textbox, password);
		 GENERIC.simpleSleep(1000);
		 GENERIC.clickByWebElement(EAC.EAC_Submit_button);
	}

	@Then("^user creates a Legacy user$")
	public void user_creates_a_Legacy_user() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 EAC=new EAC_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 EAC.createLeagacyUser();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 Assert.assertEquals(true,EAC.verifyMessage());
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 base.driver.get("https://preprod.access.oup.com/eacAdmin/logout");
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 
	}

	@Then("^user provides a new Email address for the leagacy user$")
	public void user_provides_a_new_Email_address_for_the_leagacy_user() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 EAC=new EAC_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 EAC.enterNewUserName();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 Assert.assertEquals(true,EAC.verifyUpdatedLegacyUserMessage());
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 GENERIC.simpleSleep(1000);
		 GENERIC.clickByWebElement(EAC.leagacyuser_start_button);
		 
	}
	
	@When("^user is on the homepage after entering the legacy \"([^\"]*)\" and password$")
	public void user_is_on_the_homepage_after_entering_the_legacy_and_password(String arg1) throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 EAC=new EAC_PO(base.driver);
		 LOGIN.clickSignInLink();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 String password=GENERIC.getProperty("EAC", "EAC_PASSWORD");
		 String userNameFromFile=GenericFunctions.eacLegacyUsername;
		 LOGIN.enterLoginDetails(userNameFromFile, password);
	}



}
