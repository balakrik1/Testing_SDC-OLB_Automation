package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseUtil;
import stepDefinition.GenericFunctions;
import stepDefinition.ScreenShotTaken;

public class ActivationCode_PO {
	WebDriver driver;
	private BaseUtil base;
	ScreenShotTaken  SCREENSHOT = new ScreenShotTaken(base);
	GenericFunctions GENERIC;

	public ActivationCode_PO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//a[@id='btnAddBook' and @onclick]")	
	public WebElement activationcode_addbook_link;
	
	@FindBy(xpath = "//input[@id='redeemCode' and @type='text']")	
	public WebElement activationcode_entercode_textbox;
	
	@FindBy(xpath = "//button[contains(text(),'Add') and @type='button' and @onclick]")	
	public WebElement activationcode_add_button;
	
	@FindBy(xpath = "//li[@id='addBookErrorMsg']")	
	public WebElement activationcode_ERROR_message;

	@FindBy(xpath = "//button[contains(text(),'Close')and @data-dismiss='modal']")	
	public WebElement activationcode_addbookpopupClose_button;
	
	//After Adding Activation codes
	public WebElement addBookConfimationMessage(String addBook){
		WebElement element=driver.findElement(By.xpath("(//h1[contains(text(),'"+addBook+"')])[3]"));
		return element;
	}

	
	@FindBy(xpath = "(//button[contains(text(),'Done') and @data-dismiss='modal' and @onclick])[3]")	
	public WebElement addCode_done_button;
	
	@FindBy(xpath = "(//button[@type='button'][contains(text(),'Close')])[3]")	
	public WebElement addCode_Close_button;
	

	
	
	
	public void clickAddBookLink() {
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.waitForWebElement(driver, activationcode_addbook_link);
			GENERIC.simpleSleep(1500);
			WebDriverWait objWait=new WebDriverWait(driver, 30);
			WebElement element = objWait.until(ExpectedConditions.elementToBeClickable(activationcode_addbook_link));
			element.click();
		} catch(Exception e) {
			System.out.println("**Error in clicking the Add book link"+e);
		}
	}
	
	public boolean verifyErrorMessgaes() {
		boolean flag1=false;
		boolean flag2=false;
		boolean flag3=false;
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.waitForWebElement(driver, activationcode_addbook_link);
			GENERIC.simpleSleep(1500);
			WebDriverWait objWait=new WebDriverWait(driver, 30);
			WebElement element = objWait.until(ExpectedConditions.elementToBeClickable(activationcode_add_button));
			element.click();
			GENERIC.simpleSleep(1500);
			objWait.until(ExpectedConditions.visibilityOf(activationcode_ERROR_message));
			String noCodeError=activationcode_ERROR_message.getText().trim().toString();
			String noCodeErrorFromFile=GENERIC.getProperty("MESSAGES", "NO_CODE_ERROR_MESSAGE");
			if(noCodeError.contains(noCodeErrorFromFile)) {
				flag1=true;
			}
			GENERIC.simpleSleep(1500);
			activationcode_entercode_textbox.clear();
			activationcode_entercode_textbox.sendKeys("123ABC");
			GENERIC.simpleSleep(500);
			element.click();
			GENERIC.simpleSleep(1500);
			objWait.until(ExpectedConditions.visibilityOf(activationcode_ERROR_message));
			String InvalidCodeError=activationcode_ERROR_message.getText().trim().toString();
			String InvalidErrorFromFile=GENERIC.getProperty("MESSAGES", "INVALID_CODE_ERROR_MESSAGE");
			if(InvalidCodeError.contains(InvalidErrorFromFile)) {
				flag2=true;
			}
			if(flag1 && flag2) {
				flag3=true;
			}
			objWait.until(ExpectedConditions.elementToBeClickable(activationcode_addbookpopupClose_button));
			activationcode_addbookpopupClose_button.click();
			GENERIC.simpleSleep(1500);
		} catch(Exception e) {
			flag3=false;
			System.out.println("**Error in validating error messages"+e);
		}
		return flag3;
	}
	
	
	public void addNewBook(String url) {
		String activationCode=null;
		url=driver.getCurrentUrl();
		try {
			GENERIC=new GenericFunctions(driver);
			String codeCounterValue=GENERIC.getProperty("CODE_COUNTER", "CODE");
			if(url.contains("preprod")) {
				 activationCode=GENERIC.getProperty("ACTIVATION_CODES_PP", codeCounterValue);
			}if(url.contains("uat")) {
				 activationCode=GENERIC.getProperty("ACTIVATION_CODES_UAT", codeCounterValue);
			}if(url.contains("test")) {
				 activationCode=GENERIC.getProperty("ACTIVATION_CODES_TEST", codeCounterValue);
			}if(url.contains("dev")) {
				 activationCode=GENERIC.getProperty("ACTIVATION_CODES_DEV", codeCounterValue);
			}
			int temp=Integer.parseInt(codeCounterValue)+1;
			String updatedCounterValue=String.valueOf(temp);
			GENERIC.writeDateinCODECOUNTER("CODE_COUNTER", "CODE", updatedCounterValue);
			enterValidActivationCode(activationCode);
		} catch(Exception e) {
			System.out.println("**Error in retriving the code"+e);
		}
	}
	
	public void enterValidActivationCode(String code) {
		try {
			GENERIC=new GenericFunctions(driver);
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.waitForWebElement(driver, activationcode_addbook_link);
			GENERIC.simpleSleep(1500);
			WebDriverWait objWait=new WebDriverWait(driver, 30);
			WebElement element = objWait.until(ExpectedConditions.elementToBeClickable(activationcode_add_button));
			GENERIC.simpleSleep(1500);
			activationcode_entercode_textbox.clear();
			activationcode_entercode_textbox.sendKeys(code);
			GENERIC.simpleSleep(500);
			element.click();
		} catch(Exception e) {
			System.out.println("**Error in adding code to the book"+e);
		}
	}
	
	
	public boolean verifyAddedNewBook(){
		boolean flag=false;
		try{
			WebDriverWait objWait=new WebDriverWait(driver, 20);
			GENERIC=new GenericFunctions(driver);
			WebElement done=objWait.until(ExpectedConditions.elementToBeClickable(addCode_done_button));
			String bookMessage=GENERIC.getProperty("MESSAGES", "ADD_BOOK_MESSAGE");
			String bookConfirmationMessgae=addBookConfimationMessage(bookMessage).getText().trim().toString();
			if(bookConfirmationMessgae.contains(bookMessage)) {
				flag=true;
				GENERIC.simpleSleep(1000);
				done.click();
			} 
		} catch(Exception e){
			flag=false;
			System.out.println(" Error in adding the book\n"+e);
		}
		return flag;
	}
	
	
	
	
}
