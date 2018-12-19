package stepDefinition;

import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;

import apiRequest.VerifyGmailApi;
import base.BaseUtil;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.*;
import pageObjects.ActivationCode_PO;
import pageObjects.EditProfile_PO;
import pageObjects.ForgetPassword_PO;
import pageObjects.OLBHome_PO;
import pageObjects.SigningINandOUT_PO;
import pageObjects.UserHome_PO;

public class ForgetPassword_SD extends BaseUtil {
	
	private BaseUtil base;
	WebDriver driver;

	public ForgetPassword_SD(BaseUtil base)  {
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
	ScreenShotTaken  	SCREENSHOT = new ScreenShotTaken(base);
	@When("^user enters the \"([^\"]*)\" in the sign in page$")
	public void user_enters_the_in_the_sign_in_page(String username) throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 EDIT=new EditProfile_PO(base.driver);
		 MAIL=new VerifyGmailApi();
		 MAIL.mailSeen(0);
		 LOGIN.clickSignInLink();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 String userNameFromFile=GENERIC.getProperty("CREDENTIALS", "OLBUSER");
		 GENERIC.enterTextByWebElement(LOGIN.SIGNIN_USERNAME_TEXTBOX, userNameFromFile);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	}

	@Then("^user clicks on 'Forgotten your username or password\\?' link to reset the password$")
	public void user_clicks_on_Forgotten_your_username_or_password_link_to_reset_the_password() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 EDIT=new EditProfile_PO(base.driver);
		 PASSWORD=new ForgetPassword_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 GENERIC.clickByWebElement(PASSWORD.forgetPassword_link);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	}

	@Then("^user clicks on 'Continue' to generate the reset mail$")
	public void user_clicks_on_Continue_to_generate_the_reset_mail() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 EDIT=new EditProfile_PO(base.driver);
		 PASSWORD=new ForgetPassword_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 GENERIC.clickByWebElement(PASSWORD.forgetPassword_Continue_button);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	}

	@Then("^user verifies the confimation message$")
	public void user_verifies_the_confimation_message() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 EDIT=new EditProfile_PO(base.driver);
		 PASSWORD=new ForgetPassword_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 Assert.assertEquals(true, PASSWORD.verifyMessage());
	}

	@Then("^user check the mail for the reset link$")
	public void user_check_the_mail_for_the_reset_link() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 EDIT=new EditProfile_PO(base.driver);
		 PASSWORD=new ForgetPassword_PO(base.driver);
		 base.driver.get(PASSWORD.passwordResetLink());
	}
	
	@When("^user enters the New password after navigating to the change password page$")
	public void user_enters_the_New_password_after_navigating_to_the_change_password_page() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 EDIT=new EditProfile_PO(base.driver);
		 PASSWORD=new ForgetPassword_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 PASSWORD.enterNewPassword();
	}

	@Then("^user validates the password change success message$")
	public void user_validates_the_password_change_success_message() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 EDIT=new EditProfile_PO(base.driver);
		 PASSWORD=new ForgetPassword_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 Assert.assertEquals(true, PASSWORD.verifyPasswordChangeMessage());
	}

	@When("^user is on the homepage after entering the \"([^\"]*)\" and updated password$")
	public void user_is_on_the_homepage_after_entering_the_and_updated_password(String arg1) throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 EDIT=new EditProfile_PO(base.driver);
		 PASSWORD=new ForgetPassword_PO(base.driver);
		 LOGIN.clickSignInLink();
		 String password=GENERIC.getProperty("UPDATED_DETAILS", "UPDATED_PASSWORD");
		 String userNameFromFile=GENERIC.getProperty("CREDENTIALS", "OLBUSER");
		 LOGIN.enterLoginDetails(userNameFromFile, password);
	}
	
	


}
