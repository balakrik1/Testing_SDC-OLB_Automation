package pageObjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseUtil;
import stepDefinition.GenericFunctions;
import stepDefinition.ScreenShotTaken;

public class AddedBook_PO {

	WebDriver driver;
	private BaseUtil base;
	ScreenShotTaken SCREENSHOT = new ScreenShotTaken(base);
	GenericFunctions GENERIC;

	public AddedBook_PO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// BOOK
	@FindBy(xpath = "//div[@class='olb-book-center two-block']//span[@class='olb-book-overlay-icon olb-book-open']")
	public WebElement book_launch_button;

	// book tools
	@FindBy(xpath = "//*[@id='my_iframe']")
	public WebElement book_iframe_main;

	@FindBy(xpath = "(//li[@title='Save and Close']//div[contains(text(),'Save and Close')])[1]")
	public WebElement book_SaveandCloase_link;

	@FindBy(xpath = "//*[@id='microscope-view']/div[1]/div[3]")
	public WebElement book_nextPageNavigate_link;
	
	@FindBy(xpath = "//form[@id='search-web']//button[@type='submit']")
	public WebElement book_searchbutton_button;
	
	@FindBy(xpath = "//input[@id='input-search-web']")
	public WebElement book_searchbar_texbox;
	
	
	public void searchBook() {
		try {
			GENERIC = new GenericFunctions(driver);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.simpleSleep(1500);
			GENERIC.enterTextByWebElement(book_searchbar_texbox, "A Midsummer Night's Dream Level 3 Oxford Bookworms Library");
			GENERIC.simpleSleep(1500);
			GENERIC.clickByWebElement(book_searchbutton_button);
			GENERIC.waitForthePageToLoad(driver);
			
		} catch (Exception e) {
			System.out.println("**Error in Launching the book" + e);
		}
	}

	public void launchTheBook() {
		try {
			GENERIC = new GenericFunctions(driver);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.simpleSleep(1500);
			searchBook();
			WebDriverWait objWait = new WebDriverWait(driver, 20);
			Actions hover=new Actions(driver);
			hover.moveToElement(book_launch_button).build().perform();
			WebElement book = objWait.until(ExpectedConditions.elementToBeClickable(book_launch_button));
			hover.moveToElement(book).click().build().perform();
			GENERIC.waitForthePageToLoad(driver);
		} catch (Exception e) {
			System.out.println("**Error in Launching the book" + e);
		}
	}

	public void NavigatePages() {
		try {
			GENERIC = new GenericFunctions(driver);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.simpleSleep(1500);
			getIframe();
			for (int i = 0; i < 3; i++) {
				Actions hover=new Actions(driver);
				hover.moveToElement(book_nextPageNavigate_link).build().perform();
				WebDriverWait objWait = new WebDriverWait(driver, 20);
				WebElement pageLink = objWait.until(ExpectedConditions.elementToBeClickable(book_nextPageNavigate_link));
				pageLink.click();
				GENERIC.simpleSleep(2500);
			}
		} catch (Exception e) {
			System.out.println("**Error in navigating the pages " + e);
		}
	}
	
	
	public void getIframe() {
	    final List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
	    for (WebElement iframe : iframes) {
	        System.out.println(iframe.getAttribute("id"));
	        driver.switchTo().frame(iframe.getAttribute("id"));
	    }
	}
	
	public void closetheBook() {
		try {
			GENERIC = new GenericFunctions(driver);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.waitForWebElement(driver, book_SaveandCloase_link);
			GENERIC.simpleSleep(1500);
			WebDriverWait objWait = new WebDriverWait(driver, 20);
			WebElement ele=objWait.until(ExpectedConditions.elementToBeClickable(book_SaveandCloase_link));
			ele.click();
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("**Error in closing the book" + e);
		}
	}

}
