package stepDefinition;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;

import stepDefinition.InvokeBrowser;

public class Afterclass extends BaseUtil {

	private BaseUtil base;

	public Afterclass(BaseUtil base) {
		this.base = base;
	}

	InvokeBrowser brow;

	@After
	public void closebrowser(Scenario scenario) {
		String currenturl=driver.getCurrentUrl();
		try {
			if (scenario.isFailed()) {
				scenario.embed(((TakesScreenshot) base.driver).getScreenshotAs(OutputType.BYTES), "image/png");
				Thread.sleep(2000);
				if(currenturl.contains("preprod")) {
					base.driver.get("https://preprod.secure.account.oup.com/idp/profile/Logout");
				} if(currenturl.contains("dev")) {
					base.driver.get("https://dev.secure.account.oup.com/idp/profile/Logout");
				} if(currenturl.contains("uat")) {
					base.driver.get("https://uat.secure.account.oup.com/idp/profile/Logout");
				} if(currenturl.contains("test")) {
					base.driver.get("https://test.secure.account.oup.com/idp/profile/Logout");
				}
			}
			base.driver.manage().deleteAllCookies();
			base.driver.quit();
		} catch (Exception e) {
			System.out.println("Error in screenshot or closing browser");
		}

	}

}
