package stepDefinition;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;

import base.BaseUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.OLBHome_PO;
import pageObjects.SigningINandOUT_PO;
import pageObjects.UserHome_PO;

public class SelfRegister_SD  extends BaseUtil {
	
	private BaseUtil base;
	WebDriver driver;


	public SelfRegister_SD(BaseUtil base) {
		this.base=base;
	}
	

	GenericFunctions 	GENERIC;
	OLBHome_PO	     	OLB_HOME;
	SigningINandOUT_PO 	LOGIN;
	UserHome_PO			USER_HOME;
	ScreenShotTaken  SCREENSHOT = new ScreenShotTaken(base);
	
	@Given("^user navigates to \"([^\"]*)\"$")
	public void user_navigates_to(String url) throws Throwable {
	   GENERIC=new GenericFunctions(base.driver);
	   OLB_HOME=new OLBHome_PO(base.driver);
	   String navigateURL=GENERIC.getProperty("ENVIRONMENT", url);
	   Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	   base.driver.get(navigateURL);
	   Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	}

	@When("^user clicks on 'Register' button$")
	public void user_clicks_on_Register_button() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 try {
			 JavascriptExecutor jse=(JavascriptExecutor)base.driver;
			 jse.executeScript("arguments[0].style.border='2px solid red'", OLB_HOME.HOME_REGISTER_BUTTON);
			 GENERIC.simpleSleep(1000);
			 File file=((TakesScreenshot)base.driver).getScreenshotAs(OutputType.FILE);
			 FileUtils.copyFile(file, new File("D:\\sample.png"),true);
			 } catch(Exception e) {
				 e.printStackTrace();
			 }
		 GENERIC.clickByWebElement(OLB_HOME.HOME_REGISTER_BUTTON);
		
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	}

	@Then("^user fills the \"([^\"]*)\",\"([^\"]*)\" and \"([^\"]*)\" to create an user$")
	public void user_fills_the_and_to_create_an_user(String fname, String lname, String email) throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 OLB_HOME.registerNewUser(fname, lname, email);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 OLB_HOME.storeUserNametoPropFile("CREDENTIALS","OLBUSER");
	}

	@Then("^user should be able to find the confirmation message$")
	public void user_should_be_able_to_find_the_confirmation_message() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 Assert.assertTrue("Error in Verifying the Self-Register Confirmation Message", OLB_HOME.verifyConfirmationMessage("SELF-REGISTER_MESSGAE",OLB_HOME.REGISTER_SUCCESSMESSGAE_HEADER));
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 GENERIC.clickByWebElement(OLB_HOME.REGISTER_STARTOLB_BUTTON);	 
	}

	@When("^user is on the homepage after entering the \"([^\"]*)\" and password$")
	public void user_is_on_the_homepage_after_entering_the_and_password(String username) throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 LOGIN.clickSignInLink();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 String password=GENERIC.getProperty("PASSWORD", "password");
		 String userNameFromFile=GENERIC.getProperty("CREDENTIALS", "OLBUSER");
		 LOGIN.enterLoginDetails(userNameFromFile, password);
	}

	@Then("^user finds a welcome alert message$")
	public void user_finds_a_welcome_alert_message() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 USER_HOME.closePopUp();
	  
	}

	@Then("^user verifies the the \"([^\"]*)\"$")
	public void user_verifies_the_the(String userNameFromDropDown) throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 String userNameFromFile=GENERIC.getProperty("CREDENTIALS","OLBUSER");
		 Assert.assertTrue(" USERNAME MISMATCH ", LOGIN.verifyUserName(userNameFromFile));
	}

	@Then("^user clicks on 'Sign out' to terminate the session$")
	public void user_clicks_on_Sign_out_to_terminate_the_session() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 LOGIN.signOut();
	}

	@Then("^user closes the browser$")
	public void user_closes_the_browser() throws Throwable {
		
	}



}
