package testRunner;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.cucumber.listener.ExtentProperties;
import com.cucumber.listener.Reporter;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumber;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.github.mkolisnyk.cucumber.runner.RetryAcceptance;

import cucumber.api.CucumberOptions;

@RunWith(ExtendedCucumber.class)

@ExtendedCucumberOptions(
		retryCount=1,
        includeCoverageTags={"@OLBREG"}
		)

@CucumberOptions(
		strict=true,
		features = "src\\test\\java\\feature",
		plugin = { "pretty","com.cucumber.listener.ExtentCucumberFormatter:","rerun:/failedScenarios/rerun.txt"}, 
		monochrome = true,
		tags={"@OLBREG"},
		glue = { "stepDefinition" }
		)
	
public class failedScenarioRunner {

	public static String folderPath = "";

	@BeforeClass
	public static void setup() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
		final LocalDateTime now = LocalDateTime.now();
		String st = dtf.format(now);
		ExtentProperties extentProperties = ExtentProperties.INSTANCE;
		String path = "result_" + st;
		File file = new File(System.getProperty("user.dir") + "\\target\\" + path);
		file.mkdirs();
		extentProperties.setReportPath("target\\" + path + "\\cucumber-html-report-" + st + ".html");
	}

	@AfterClass
	public static void teardown() {
		String Path1 = System.getProperty("user.dir") + "\\Resources\\extent-config.xml";
		Reporter.loadXMLConfig(new File(Path1));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setTestRunnerOutput("Sample test runner output message");
	}
	
	public static int retries=0;
	public failedScenarioRunner() {}
	
	@RetryAcceptance
	public static boolean retryCheck(Throwable e) {
		return !e.getMessage().contains("Configuration failed");
	}

}
