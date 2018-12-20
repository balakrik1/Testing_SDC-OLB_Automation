package stepDefinition;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import base.BaseUtil;
import base.Constant;
import cucumber.api.java.Before;

public class InvokeBrowser extends BaseUtil{

	private BaseUtil base;
	public InvokeBrowser(BaseUtil base) {
		this.base=base;
	}
	Properties prop ;
	InputStream input = null;
	
	@SuppressWarnings("deprecation")
	@Before
	public WebDriver driverreturn() {

		if (base.driver != null) {
			return base.driver;
		} else {
			String result = "";
			prop = new Properties();
			try {
				input = new FileInputStream(Constant.BrowserProperties);
				prop.load(input);
				result = prop.getProperty("Browser");
				if (result.equalsIgnoreCase("chrome")) {
					System.setProperty("webdriver.chrome.driver", Constant.ChromePath);
					base.driver = new ChromeDriver();
					base.driver.manage().window().maximize();
					base.driver.manage().deleteAllCookies();
				//	base.driver.get(prop.getProperty("URL"));

				}

				if (result.equalsIgnoreCase("IE")) {
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					System.setProperty("webdriver.ie.driver", Constant.IEPath);
					capabilities.setJavascriptEnabled(true);
					capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					capabilities.setCapability("requireWindowFocus", true);
					capabilities.setCapability("nativeEvents", false);
					capabilities.setCapability("ignoreProtectedModeSettings", true);
					capabilities.setCapability("disable-popup-blocking", true);
					capabilities.setCapability("enablePersistentHover", true);
					capabilities.setCapability("ignoreZoomSetting", true);
					capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
					base.driver = new InternetExplorerDriver(capabilities);
					base.driver.manage().window().maximize();
					base.driver.manage().deleteAllCookies();
					//base.driver.get(prop.getProperty("URL"));
				}

				if (result.equalsIgnoreCase("Firefox")) {
					System.setProperty("webdriver.gecko.driver", Constant.geckodriverPath);
					/*ProfilesIni profile = new ProfilesIni();
					FirefoxProfile myprofile = profile.getProfile("myProfile");*/
					base.driver = new FirefoxDriver();
					base.driver.manage().window().maximize();
					base.driver.manage().deleteAllCookies();
					//base.driver.get(prop.getProperty("URL"));

				}  
				
				if (result.equalsIgnoreCase("EDGE")) {
					
					System.setProperty("webdriver.edge.driver", Constant.EDGEdriverPath);
					base.driver = new EdgeDriver();
					base.driver.manage().window().maximize();
					base.driver.manage().deleteAllCookies();
					//base.driver.get(prop.getProperty("URL"));

				}  

			} catch (IOException ex) {
				ex.printStackTrace();

			}
			return base.driver;
		}

	}
	
	
	
	public WebDriver driverreturn1() {

		if (base.driver != null) {
			return base.driver;
		} else {
			String result = "";
			Properties prop = new Properties();

			InputStream input = null;

			try {
				String propertiesPath = System.getProperty("user.dir") + "\\Resources\\Browser.properties";
				input = new FileInputStream(propertiesPath);
				// load a properties file
				prop.load(input);
				result = prop.getProperty("Browser");

				if (result.equalsIgnoreCase("IE")) {
					String Path = System.getProperty("user.dir") + "\\Additional Jars\\Driver\\IEDriverServer.exe";
					DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
					capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					System.setProperty("webdriver.ie.driver", Path);
					// capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
					base.driver = new InternetExplorerDriver(capabilities);

					// base.driver = new InternetExplorerDriver();
					base.driver.manage().window().maximize();
					base.driver.manage().deleteAllCookies();
					base.driver.get(prop.getProperty("URL"));

				}

			} catch (IOException ex) {
				ex.printStackTrace();

			}
			return base.driver;
		}

	}

	public void closebrowser() {
		try {
			base.driver.manage().deleteAllCookies();
			base.driver.quit();

		} catch (Exception e) {

		}
		base.driver = null;
	}

}
