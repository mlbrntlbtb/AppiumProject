package AppiumBasics;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.appium.java_client.AppiumDriver;
import Utilities.AppiumServerHandler;
import Utilities.EmulatorHandler;


public class AppiumBaseTest 
{
	//WebDriver AutoDriver; // Use this to test apps on web browser
	public static AppiumDriver appiumDriver; // Use this to test native apps
	//AndroidDriver androidDriver; // Use this specific for android apps
	public static WebDriverWait wait;
	
	@BeforeClass
	public void Initialize() throws IOException 
	{
		//Start appium server
		//AppiumServerHandler.startServer();
		
		//Start emulator
		//EmulatorHandler.startEmulator();
		
		//Set mobile capabilities
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Emulator");
		//capabilities.setCapability("udid", "ce05171558dc7d2c03");
		capabilities.setCapability("udid", "emulator-5554");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "9");
		//capabilities.setCapability("browserName", "Chrome");
		capabilities.setCapability("appPackage", "com.android.calculator2"); //Calculator app
		capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
		
		String appiumServerURL = "http://localhost:4723/wd/hub";
		appiumDriver = new AppiumDriver(new URL(appiumServerURL), capabilities);
		wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(10));
	}
	
	@Test
	public void Test() throws InterruptedException, IOException 
	{
		WebElement button_one = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/digit_1")));
		WebElement button_two = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/digit_2")));
		WebElement button_add = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/op_add")));
		WebElement button_equals = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/eq")));
		WebElement result_field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.android.calculator2:id/result")));
		
		button_one.click();
		button_add.click();
		button_two.click();
		button_equals.click();
		String result = result_field.getText();
		
		Assert.assertEquals("3", result);
	}
	
	@AfterClass
	public void EndTest() throws IOException 
	{	
		//Stop appium server
		//AppiumServerHandler.stopServer();
		
		//Stop emulator
		//EmulatorHandler.stopEmulator();
	}
}
