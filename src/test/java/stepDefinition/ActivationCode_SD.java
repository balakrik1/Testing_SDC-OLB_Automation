package stepDefinition;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;

import base.BaseUtil;
import cucumber.api.java.en.Then;
import pageObjects.ActivationCode_PO;
import pageObjects.OLBHome_PO;
import pageObjects.SigningINandOUT_PO;
import pageObjects.UserHome_PO;

public class ActivationCode_SD extends BaseUtil {
	
	private BaseUtil base;
	WebDriver driver;


	public ActivationCode_SD(BaseUtil base) {
		this.base=base;
	}
	

	GenericFunctions 	GENERIC;
	OLBHome_PO	     	OLB_HOME;
	SigningINandOUT_PO 	LOGIN;
	UserHome_PO			USER_HOME;
	ActivationCode_PO	ACTIVATIIONCODE;
	ScreenShotTaken  	SCREENSHOT = new ScreenShotTaken(base);

	@Then("^user provides the invalid \"([^\"]*)\" to add the book and validates the appropriate error messgae$")
	public void user_provides_the_invalid_to_add_the_book_and_validates_the_appropriate_error_messgae(String arg1) throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 ACTIVATIIONCODE.clickAddBookLink();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 Assert.assertTrue(" Issue with validating error messages",ACTIVATIIONCODE.verifyErrorMessgaes());
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	}
}
