package stepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cucumber.listener.Reporter;
import com.google.common.base.Function;

import base.BaseUtil;
import testRunner.TestRun;


public class GenericFunctions extends BaseUtil {
	
	WebDriver driver;
	private BaseUtil base;
	public static String screenshotfolder;
	public static String eacLegacyUsername;
	public static String newLegacyUserName;
	
	public GenericFunctions(WebDriver driver) {
		this.driver=driver;
	}
	
	ScreenShotTaken SCREENSHOT = new ScreenShotTaken(base);
	
	
		
	
	/**
	 * @return string 
	 * @Description It fetches the value from the property file
	 * @throws Exception
	 */
	
	public void waitForWebElement(WebDriver driver, WebElement elementpath) throws Exception {
		FluentWait<WebElement> wait = new FluentWait<WebElement>(elementpath);
		wait.withTimeout(30, TimeUnit.SECONDS);
		wait.pollingEvery(5, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		Function<WebElement, Boolean> f = new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement elementpath) {
				if (elementpath.isDisplayed() && elementpath.isEnabled() == true) {
					return true;
				}
				return false;
			}
		};
		wait.until(f);
	}
	
	
	public String getProperty(String propertyFileName, String propertyName) {
		String property = null;
		String propertiesPath=System.getProperty("user.dir") + "\\Resources\\"+propertyFileName+".properties";
		
		try {
			Properties prop = new Properties();
			InputStream input = new FileInputStream(propertiesPath);
			prop.load(input);
			property = prop.getProperty(propertyName);
		} catch(Exception e) {
			System.err.println("Invalid File/property name");
			System.out.println(e.getMessage());
		}
		return property;
	}
	
	
	/**
	 * @return boolean 
	 * @throws IOException 
	 * @Description It verifies the webelement text with the text in the property file
	 * @throws Exception
	 */
	
	public boolean verifyText(String propertyFileName,String propertyName,WebElement element) throws IOException, Exception {
		boolean flag=false;
		waitForthePageToLoad(driver);
		WebDriverWait objWait=new WebDriverWait(driver,20);
		objWait.until(ExpectedConditions.elementToBeClickable(element));
		Thread.sleep(500);
		String textPresentInThePage=element.getText().trim().toString();
		String property=getProperty(propertyFileName, propertyName).trim();
		 try {
		
			 if(textPresentInThePage.equals(property)) {
				 flag=true;
			 }
		 } catch (NoSuchElementException e) {
			 flag=false;
			 e.printStackTrace();
		 }
		return flag;
	}
	
	/**
	 * @return void 
	 * @throws Exception 
	 * @Description It send the keys to the textbox
	 * @throws Exception
	 */
	
	public void enterTextByWebElement(WebElement element,String input) throws Exception {
		try {
			waitForWebElement(driver,element);
			simpleSleep(500);
			WebDriverWait objWait=new WebDriverWait(driver,20);
			WebElement ele=objWait.until(ExpectedConditions.elementToBeClickable(element));
			ele.clear();
			ele.sendKeys(input);
		} catch (NoSuchElementException e) {
			 System.err.println("No such "+input+" element");
			 System.out.println(e.getMessage());
		 }
	}
	
	/**
	 * @return void 
	 * @throws Exception 
	 * @Description It clicks the button/link
	 * @throws Exception
	 */
	
	public void clickByWebElement(WebElement element) throws Exception {
		try {
			simpleSleep(1000);
			WebDriverWait objWait=new WebDriverWait(driver,20);
			WebElement input=objWait.until(ExpectedConditions.elementToBeClickable(element));
			waitForWebElement(driver,element);
			input.click();
		} catch (NoSuchElementException e) {
			 System.err.println("No such "+element+" element");
			 System.out.println(e);
		 }
	}
	
	public boolean verifyElement(WebElement element) {
		boolean flag=false;
		try {
			simpleSleep(1500);
			waitForthePageToLoad(driver);
			waitForWebElement(driver, element);
			WebDriverWait objWait=new WebDriverWait(driver, 20);
			objWait.until(ExpectedConditions.elementToBeClickable(element));
			if(element.isDisplayed()){
				flag=true;
			}
		} catch(Exception e) {
			System.out.println(e);
			flag=false;
		}
		return flag;
	}
	
	
	public boolean verifyByWebElement(WebElement element) throws Exception {
		boolean flag=false;
		try {
			simpleSleep(1000);
			WebDriverWait objWait=new WebDriverWait(driver,20);
			objWait.until(ExpectedConditions.visibilityOf(element));
			WebElement input=objWait.until(ExpectedConditions.elementToBeClickable(element));
			waitForWebElement(driver,element);
			input.click();
		} catch (NoSuchElementException e) {
			 System.err.println("No such "+element+" element");
			 System.out.println(e);
		 }
		return flag;
	}
	
	/**
	 * @return void 
	 * @Description wait till page loads
	 * @throws Exception
	 */
	
	public void waitForthePageToLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }
	
	/**
	 * @return void 
	 * @Description scroll page down
	 */
	
	public void scrollPageDown(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250)"); 
	}

	
	/**
	 * @return void 
	 * @Description scroll page up
	 */
	
	public void scrollPageUp(WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(250, 0)"); 
	}


	  /**
	    * Sleep in multiple of 100 ms and it shows a dot on console output as a progress
	    * 
	    * @version 1.0 
	    */
	 public void simpleSleep(int nMilliSeconds){
			int nThreshold = nMilliSeconds/100;
			System.out.println();
	    	try{
	    		for (int cnt=0; cnt< nThreshold; cnt++){
	        			System.out.print(".");
	        			Thread.sleep(100);
	    		}
	    	}
	    	catch(Exception p){}
	    	System.out.println();
	    }
	
	 	public HashMap<String, String> propertiesHashMap 	= new HashMap<String, String>();
	    
	    /**
	    * This method reads properties file and stores the content into hashmap
	    * Also the same hash map can be used further to add any runtime variables in key:value fashion
	    * 
	    */
		public HashMap<String, String> UtilitiesGeneralGetAllProperties(String fileName) {
			String mainPath = new File("").getAbsoluteFile().toString();
			String propertiesFilePath = mainPath + File.separator + "Resources" + File.separator + fileName+".properties";
										
			Properties prop = new Properties();
			try {
				prop.load(new FileInputStream(propertiesFilePath));
			}
			catch (Exception eProp) {
				System.out.println("***Error : Exception while processing properties file");
				return new HashMap<String, String>();
			}
			
			for(String key : prop.stringPropertyNames()) {
				if(null==prop.getProperty(key))	{
					propertiesHashMap.put(key, "");
				}
				else {
					propertiesHashMap.put(key, prop.getProperty(key));
				}
			}
			if(0 == propertiesHashMap.size()) {
				System.out.println("***Error : No parameters found in properties file");
			}
			
			return propertiesHashMap;
		}

	    /**
	    * With this method individual hashmap element can be access
	    * 
	    */
	    public String UtilitiesGeneralGetSingleProperty(String strSinglePropertyName) {
	    	return propertiesHashMap.get(strSinglePropertyName);
	    }

	    /**
	    * With this method additional individual hashmap element can be added
	    * 
	    */
	    public String UtilitiesGeneralSetSingleProperty(String strSinglePropertyName, String strSinglePropertyValue) {
	    	return propertiesHashMap.put(strSinglePropertyName,strSinglePropertyValue);
	    }
	    
	    
	 
	    	/**
		    * With this method write properties to property file
		    * 
		    */
	    public void writeDateinPropertyFile(String fileName, String propertyName, String propertyValue) {
		
	    Properties prop = new Properties();
		OutputStream output = null;
		String propertiesPath=System.getProperty("user.dir") + "\\Resources\\"+fileName+".properties";

		try {

			output = new FileOutputStream(propertiesPath,true);
			
					
			// set the properties value
			prop.setProperty(propertyName, propertyValue);

			// save properties to project root folder
			prop.store(output, null);
		

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	    
		/**
		    * With this method write properties to property file
		    * 
		    */
	    public void writeDateinCODECOUNTER(String fileName, String propertyName, String propertyValue) {
		
	    Properties prop = new Properties();
		OutputStream output = null;
		String propertiesPath=System.getProperty("user.dir") + "\\Resources\\"+fileName+".properties";

		try {

			output = new FileOutputStream(propertiesPath);
			
					
			// set the properties value
			prop.setProperty(propertyName, propertyValue);

			// save properties to project root folder
			prop.store(output, null);
		

		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}
	    
	    public void updateCounterValue(String inputCounterValue) {
			try {
				int temp=Integer.parseInt(inputCounterValue);
				temp=temp+1;
				String ouputCounterValue=String.valueOf(temp);
				getUpdateCounterValue("EMAIL_COUNTER","COUNTER",ouputCounterValue);
			} catch(Exception e) {
				System.out.println("Error in Updating the counter"+e);
			}
		}
	    
	    public void getUpdateCounterValue(String fileName, String propertyName, String propertyValue) {
	    	Properties prop = new Properties();
	 		OutputStream output = null;
	 		String propertiesPath=System.getProperty("user.dir") + "\\Resources\\"+fileName+".properties";
	 		try {
	 			output = new FileOutputStream(propertiesPath);
	 			prop.setProperty(propertyName, propertyValue);
	 			prop.store(output, null);
	 		} catch (IOException io) {
	 			io.printStackTrace();
	 		} finally {
	 			if (output != null) {
	 				try {
	 					output.close();
	 				} catch (IOException e) {
	 					e.printStackTrace();
	 				}
	 			}
	 		}
	    }
	    
	    public void removeProperty(String fileName, String propertyName) {
	    	
		    Properties prop = new Properties();
			OutputStream output = null;
			String propertiesPath=System.getProperty("user.dir") + "\\Resources\\"+fileName+".properties";

			try {

				output = new FileOutputStream(propertiesPath,true);
				
				//remove the old contents
				try {
					prop.remove(propertyName);
				} catch(Exception e) {
					System.out.println("** No Such property");
				}
				try (OutputStream out = Files.newOutputStream(Paths.get(propertiesPath), StandardOpenOption.TRUNCATE_EXISTING)) {
					prop.store(out, null);
			    }
						

			} catch (IOException io) {
				io.printStackTrace();
			} finally {
				if (output != null) {
					try {
						output.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			}
	    }

	    
	    public String getscreenshot(WebDriver driver) throws Exception {
			String dest = null;
			String st = null;
			screenshotfolder=TestRun.folderPath;
			DateTimeFormatter dtf;
			File dir = new File(System.getProperty("user.dir") + "\\target");
		    File[] files = dir.listFiles();
		    File lastModified = Arrays.stream(files).filter(File::isDirectory).max(Comparator.comparing(File::lastModified)).orElse(null);
			try {
				dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
				final LocalDateTime now = LocalDateTime.now();
				st = dtf.format(now);
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				dest = lastModified+"\\";
				FileUtils.copyFile(scrFile, new File(dest+screenshotfolder+ st + ".png"));
				com.cucumber.listener.Reporter.addScreenCaptureFromPath(st + ".png");
				return dest+screenshotfolder+ st + ".png";
			} catch (Exception e) {
				Reporter.addStepLog("Error while taking screenshot");
				System.out.println(e.getMessage());
			}
			return dest + st + ".png";
		}
	    
	    		    
}
