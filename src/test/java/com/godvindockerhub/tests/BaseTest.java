package com.godvindockerhub.tests;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.godvindockerhub.listeners.Listener;
import com.godvindockerhub.util.Config;
import com.godvindockerhub.util.Constants;

@Listeners({Listener.class})
public abstract class BaseTest {
	
	private static final Logger log= LoggerFactory.getLogger(BaseTest.class);
	
	protected  WebDriver driver;
	
	@BeforeSuite
	public void setupConfig() {
		Config.initializer();
	}
	
	@BeforeTest
	
	public  void setDriver(ITestContext ctx) throws MalformedURLException, URISyntaxException {
		
		
		this.driver=Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))?setRemoteDriver():setLocalDriver();
		ctx.setAttribute(Constants.DRIVER, this.driver);
		
		
	}
	
	
	public RemoteWebDriver setRemoteDriver() throws MalformedURLException, URISyntaxException {
		
		Capabilities capabilities= new ChromeOptions();
		if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER))) {
			
			capabilities= new FirefoxOptions();
		}
		String urlFormat=Config.get(Constants.GRID_URL_FORMAT);
		String hubHost=Config.get(Constants.GRID_HUBHOST);
		String url=String.format(urlFormat, hubHost);
		log.info("url is {} ",url);
		return new RemoteWebDriver( new URI(url).toURL(),capabilities);
	}
	
	public WebDriver setLocalDriver() {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
	
	
	
	@AfterTest
	public void closeDriver() {
		driver.quit();
	}

}
