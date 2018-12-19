package pageObjects;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseUtil;
import stepDefinition.GenericFunctions;
import stepDefinition.ScreenShotTaken;

public class UserHome_PO {
	
	WebDriver driver;
	private BaseUtil base;
	GenericFunctions GENERIC;
	ScreenShotTaken SCREENSHOT = new ScreenShotTaken(base);

	public UserHome_PO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//WELCOME POP-UP
	@FindBy(xpath = "(//*[@id='onboardingModal']//button)[1]")	
	public WebElement POPUP_CLOSE_BUTTON;
	
	@FindBy(xpath = "//button[contains(text(),'OK, got it!') and @data-dismiss='modal']")	
	public WebElement POPUP_CLOSE_BUTTON_2;
	
	
	
	public void closePopUp() throws NoSuchElementException, ElementNotVisibleException {
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.simpleSleep(1500);
			WebDriverWait objWait=new WebDriverWait(driver, 20);
			WebElement element=objWait.until(ExpectedConditions.elementToBeClickable(POPUP_CLOSE_BUTTON));
			if(element.isDisplayed()) {
				element.click();
			}
			WebElement element1=objWait.until(ExpectedConditions.elementToBeClickable(POPUP_CLOSE_BUTTON_2));
			if(element1.isDisplayed()) {
				element.click();
			}
		} catch(Exception e) {
			System.out.println(" No Pop-up found"+e);
		}
	}

}
