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

public class EditProfile_PO {
	
	WebDriver driver;
	private BaseUtil base;
	ScreenShotTaken  SCREENSHOT = new ScreenShotTaken(base);
	GenericFunctions GENERIC;

	public EditProfile_PO(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Edit profile
	@FindBy(xpath = "//input[@id='firstName']")	
	public WebElement edit_firstName_textbox;
	
	@FindBy(xpath = "//input[@id='lastName']")	
	public WebElement edit_lastName_textbox;
	
	@FindBy(xpath = "//button[contains(text(),'Change my username or password') and @type='button' and @onclick]")	
	public WebElement edit_ChangeUserNamePassword_button;
	
	@FindBy(xpath = "//button[contains(text(),'Save') and @type='button' and @onclick]")	
	public WebElement edit_Save_button;
	
	@FindBy(xpath = "//button[contains(text(),'Continue') and @type='button' and @onclick]")	
	public WebElement edit_Continue_button;
	
	
	//Saved Details
	@FindBy(xpath = "//div[@class='ces-page-subtext']")	
	public WebElement save_confirmation_header;
	
	@FindBy(xpath = "//button[contains(text(),'Done') and @type='button']")	
	public WebElement save_Done_button;
	
	
	public WebElement updatedUserName(String updateUserName){
		WebElement element=driver.findElement(By.xpath("//p[contains(text(),'"+updateUserName+"')]"));
		return element;
	}
		
	@FindBy(xpath = "//button[contains(text(),'Return to profile settings') and @type='button' and @onclick]")	
	public WebElement save_ReturnToSettings_button;
	
	
	// current password
	@FindBy(xpath = "//input[@id='currentPassword']")	
	public WebElement currentPassword_currentPassword_textbox;
	
	//update Username and password
	@FindBy(xpath = "//input[@id='userName']")	
	public WebElement update_userName_textbox;

	@FindBy(xpath = "//input[@id='newPassword']")	
	public WebElement update_newPassword_textbox;
	
	@FindBy(xpath = " //input[@id='confirmPassword']")	
	public WebElement update_confirmPassword_textbox;
	
	@FindBy(xpath = "//button[contains(text(),'Save') and @type='submit']")	
	public WebElement update_save_textbox;
	
	
	
	public static String updatedFirstName;
	public static String updatedLastName;
	public static String currentPassword;
	public static String updateCurrentPassword;
	public static String userNameToUpdate;

	public void updateFirstNLastName(){
		
			GENERIC=new GenericFunctions(driver);
			try{
			WebDriverWait objWait=new WebDriverWait(driver, 20);
			
			updatedFirstName=GENERIC.getProperty("UPDATED_DETAILS", "UPDATED_FIRSTNAME");
			updatedLastName=GENERIC.getProperty("UPDATED_DETAILS", "UPDATED_LASTNAME");
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.simpleSleep(1000);
			WebElement FNelement=objWait.until(ExpectedConditions.elementToBeClickable(edit_firstName_textbox));
			FNelement.clear();
			FNelement.sendKeys(updatedFirstName);
			GENERIC.simpleSleep(1000);
			WebElement LNelement=objWait.until(ExpectedConditions.elementToBeClickable(edit_lastName_textbox));
			LNelement.clear();
			LNelement.sendKeys(updatedLastName);
			GENERIC.simpleSleep(1000);
			objWait.until(ExpectedConditions.elementToBeClickable(edit_Save_button));
			edit_Save_button.click();
		} catch(Exception e){
			System.out.println(" Error in updating the firstName and LastName\n"+e);
			
		}
	}
	
	public boolean verifyupdatedFirstNLastName(){
		boolean flag=false;
		try{
			WebDriverWait objWait=new WebDriverWait(driver, 20);
			GENERIC=new GenericFunctions(driver);
			objWait.until(ExpectedConditions.elementToBeClickable(save_Done_button));
			String temp=updatedFirstName+" "+updatedLastName;
			String updatedName=updatedUserName(temp).getText().trim().toString();
			String updateMessge=GENERIC.getProperty("MESSAGES", "UPDATE_MESSAGE");
			String updateMessgaeFromUI=save_confirmation_header.getText().trim().toString();
			if(updateMessgaeFromUI.contains(updateMessge)) {
			if(updatedName.equals(temp)) {
				flag=true;
			} }
		} catch(Exception e){
			flag=false;
			System.out.println(" Error in updating the firstName and LastName\n"+e);
		}
		return flag;
	}
	
	public void updateUserNameNPassword() {
		try{
			WebDriverWait objWait=new WebDriverWait(driver, 20);
			GENERIC=new GenericFunctions(driver);
			currentPassword=GENERIC.getProperty("PASSWORD", "password");
			
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.simpleSleep(1000);
			WebElement UNelement=objWait.until(ExpectedConditions.elementToBeClickable(edit_ChangeUserNamePassword_button));
			UNelement.click();
			
			GENERIC.simpleSleep(1000);
			WebElement CPelement=objWait.until(ExpectedConditions.elementToBeClickable(currentPassword_currentPassword_textbox));
			CPelement.clear();
			CPelement.sendKeys(currentPassword);
			
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.simpleSleep(1000);
			WebElement CNelement=objWait.until(ExpectedConditions.elementToBeClickable(edit_Continue_button));
			CNelement.click();
			
			GENERIC.waitForthePageToLoad(driver);
			GENERIC.simpleSleep(1500);
			// String existingUserName=GENERIC.getProperty("CREDENTIALS", "OLBUSER");
			// String[] splitUN=existingUserName.split("@");
			// String[] splitAGAIN=splitUN[0].split("\\+");
			/*userNameToUpdate=splitAGAIN[1];
			WebElement userNameelement=objWait.until(ExpectedConditions.elementToBeClickable(update_userName_textbox));
			userNameelement.clear();
			userNameelement.sendKeys(userNameToUpdate);*/
			
			updateCurrentPassword=GENERIC.getProperty("UPDATED_DETAILS", "UPDATED_PASSWORD");
			WebElement password=objWait.until(ExpectedConditions.elementToBeClickable(update_newPassword_textbox));
			password.click();
			password.sendKeys(updateCurrentPassword);
			
			GENERIC.simpleSleep(1000);
			update_confirmPassword_textbox.clear();
			update_confirmPassword_textbox.sendKeys(updateCurrentPassword);
			
			GENERIC.simpleSleep(1000);
			WebElement saveelement=objWait.until(ExpectedConditions.elementToBeClickable(update_save_textbox));
			saveelement.click();
			
		} catch(Exception e){
			System.out.println(" Error in updating the username and password\n"+e);
			
		}
	}
	
	public boolean verifyupdatedUserName(){
		boolean flag=false;
		try{
			WebDriverWait objWait=new WebDriverWait(driver, 20);
			GENERIC=new GenericFunctions(driver);
			objWait.until(ExpectedConditions.elementToBeClickable(save_Done_button));
			String temp=updatedFirstName+" "+updatedLastName;
			String updatedName=updatedUserName(temp).getText().trim().toString();
			String updateMessge=GENERIC.getProperty("MESSAGES", "UPDATE_MESSAGE");
			//String updatedUserName=driver.findElement(By.xpath("(//h5[contains(text(),'"+userNameToUpdate+"')])[2]")).getText().trim().toString();
			String updateMessgaeFromUI=save_confirmation_header.getText().trim().toString();
			if(updateMessgaeFromUI.contains(updateMessge)) {
			if(updatedName.equals(temp)) {
				flag=true;
			} }
		} catch(Exception e){
			flag=false;
			System.out.println(" Error in updating the firstName and LastName\n"+e);
		}
		return flag;
	}

}
