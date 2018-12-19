package stepDefinition;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.cucumber.listener.Reporter;

import base.BaseUtil;
import testRunner.TestRun;

public class ScreenShotTaken extends BaseUtil {

	public static String screenshotfolder;
	private BaseUtil base;
	WebDriver driver;

	public ScreenShotTaken(BaseUtil base) {
	}

	public String getscreenshot(WebDriver driver2) throws Exception {
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
			File scrFile = ((TakesScreenshot) driver2).getScreenshotAs(OutputType.FILE);
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
