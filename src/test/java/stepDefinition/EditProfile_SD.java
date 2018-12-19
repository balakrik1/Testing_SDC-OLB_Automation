package stepDefinition;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;

import base.BaseUtil;
import cucumber.api.java.en.Then;
import pageObjects.ActivationCode_PO;
import pageObjects.EditProfile_PO;
import pageObjects.OLBHome_PO;
import pageObjects.SigningINandOUT_PO;
import pageObjects.UserHome_PO;

public class EditProfile_SD  extends BaseUtil{
	
	private BaseUtil base;
	WebDriver driver;

	public EditProfile_SD(BaseUtil base)  {
		this.base=base;
	}
	

	GenericFunctions 	GENERIC;
	OLBHome_PO	     	OLB_HOME;
	SigningINandOUT_PO 	LOGIN;
	UserHome_PO			USER_HOME;
	ActivationCode_PO	ACTIVATIIONCODE;
	EditProfile_PO		EDIT;
	ScreenShotTaken  	SCREENSHOT = new ScreenShotTaken(base);
	
	@Then("^user updated the user profile$")
	public void user_updated_the_user_profile() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 EDIT=new EditProfile_PO(driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 LOGIN.clickEditProfile();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 EDIT.updateFirstNLastName();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 Assert.assertTrue(EDIT.verifyupdatedFirstNLastName());
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 GENERIC.clickByWebElement(EDIT.save_Done_button);
	}
	
	
	@Then("^user updates the firstname and lastname$")
	public void user_updates_the_firstname_and_lastname() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 EDIT=new EditProfile_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 LOGIN.clickEditProfile();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 EDIT.updateFirstNLastName();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 Assert.assertTrue(EDIT.verifyupdatedFirstNLastName());
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 GENERIC.clickByWebElement(EDIT.save_Done_button);
	}

	@Then("^user updates the username and password$")
	public void user_updates_the_username_and_password() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 EDIT=new EditProfile_PO(base.driver);
		 LOGIN.clickEditProfile();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 EDIT.updateUserNameNPassword();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 Assert.assertTrue( EDIT.verifyupdatedUserName());
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 GENERIC.clickByWebElement(EDIT.save_Done_button);
	}
	
	



}
