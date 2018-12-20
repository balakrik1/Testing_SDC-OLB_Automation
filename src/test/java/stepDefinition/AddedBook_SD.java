package stepDefinition;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;

import base.BaseUtil;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.ActivationCode_PO;
import pageObjects.AddedBook_PO;
import pageObjects.OLBHome_PO;
import pageObjects.SigningINandOUT_PO;
import pageObjects.UserHome_PO;

public class AddedBook_SD extends BaseUtil {
	
	private BaseUtil base;
	WebDriver driver;


	public AddedBook_SD(BaseUtil base) {
		this.base=base;
	}
	

	GenericFunctions 	GENERIC;
	OLBHome_PO	     	OLB_HOME;
	SigningINandOUT_PO 	LOGIN;
	UserHome_PO			USER_HOME;
	ActivationCode_PO	ACTIVATIIONCODE;
	AddedBook_PO		ADD;
	ScreenShotTaken  	SCREENSHOT = new ScreenShotTaken(base);
	
	@When("^user provides the valid activation code for \"([^\"]*)\"$")
	public void user_provides_the_valid_activation_code(String url) throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 ADD=new AddedBook_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 ACTIVATIIONCODE.clickAddBookLink();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 ACTIVATIIONCODE.addNewBook(url);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	}

	@Then("^user should be the find the new book added to the collections$")
	public void user_should_be_the_find_the_new_book_added_to_the_collections() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 ADD=new AddedBook_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 ACTIVATIIONCODE.verifyAddedNewBook();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	    
	}

	@When("^user launches the book$")
	public void user_launched_the_book() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 ADD=new AddedBook_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 ADD.launchTheBook();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	    
	}

	@Then("^user should be able to switch pages$")
	public void user_should_be_able_to_switch_pages() throws Throwable {
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 ADD=new AddedBook_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 ADD.NavigatePages();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	    
	}

	@Then("^user closes the book$")
	public void user_closes_the_book() throws Throwable {
	    
		 GENERIC=new GenericFunctions(base.driver);
		 OLB_HOME=new OLBHome_PO(base.driver);
		 LOGIN=new SigningINandOUT_PO(base.driver);
		 USER_HOME=new UserHome_PO(base.driver);
		 ACTIVATIIONCODE=new ActivationCode_PO(base.driver);
		 ADD=new AddedBook_PO(base.driver);
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
		 ADD.closetheBook();
		 Reporter.addScreenCaptureFromPath(SCREENSHOT.getscreenshot(base.driver));
	}



}
