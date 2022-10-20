package AppiumBasics;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;

public class AppiumWebTest 
{
	
	public static AppiumDriver appiumDriver;
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
			capabilities.setCapability("udid", "emulator-5554");
			capabilities.setCapability("platformName", "Android");
			capabilities.setCapability("platformVersion", "9");
			capabilities.setCapability("browserName", "Chrome");
			
			String appiumServerURL = "http://localhost:4723/wd/hub";
			appiumDriver = new AppiumDriver(new URL(appiumServerURL), capabilities);
			wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(10));
		}
		
		@Test
		public void Test() throws InterruptedException, IOException 
		{
			appiumDriver.navigate().to("https://demoqa.com/login");
			WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='userName']")));
			WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='password']")));
			WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='login']")));
			
			username.clear();
			password.clear();
			username.sendKeys("johnwick");
			password.sendKeys("Excommunicado123!");
			Thread.sleep(3000);
			password.sendKeys(Keys.TAB);
			
			JavascriptExecutor jsE = (JavascriptExecutor)appiumDriver;
			jsE.executeScript("arguments[0].scrollIntoView(true)", login);
			//Actions actions = new Actions(appiumDriver);
			//actions.moveToElement(login).click();
			login.click();
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
