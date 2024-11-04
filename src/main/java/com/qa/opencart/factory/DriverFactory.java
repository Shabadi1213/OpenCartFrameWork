package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameWorkException;
import com.qa.opencart.logger.Log;

import io.qameta.allure.Step;


public class DriverFactory {
	
	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	
	public static String isHighlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This Method is use initliaze the browser on bases of browser name
	 * 
	 * @param browserName
	 * @return driver
	 */
	@Step("Initializing the browser with properties :{0}")
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");
//		System.out.println("Browser name is : " + browserName);
		Log.info(prop.getProperty("testname") + " and browser name is : " +  browserName);

		isHighlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			if(Boolean.parseBoolean(prop.getProperty("remote"))){
				init_remoteDriver("chrome");
			}else {
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			}
			break;
			
		case "firefox":
			if(Boolean.parseBoolean(prop.getProperty("remote"))){
				init_remoteDriver("firefox");
			}else {
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			}
			break;
			
		case "edge":
			
			if(Boolean.parseBoolean(prop.getProperty("remote"))){
				init_remoteDriver("edge");
			}else {
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			}
			break;
			
			default:
//			System.out.println("Please enter valid browser name : " + browserName);
			Log.error("Please enter valid browser name : " + browserName);
			throw new BrowserException("=======INVALID BROWSER NAME=======");
		}

		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	
	private void init_remoteDriver(String browserName) {
//		System.out.println("Running on the tests on Selenium grid with browser : " + browserName);
		Log.info("Running on the tests on Selenium grid with browser : " + browserName);
		
		try {
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
			break;
		case "firefox":
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
				break;
		case "edge":
			tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
			break;
		default:
//			System.out.println("Please get the right browser name : " + browserName);
			Log.error("Please pass the right browser name : " + browserName);
			throw new BrowserException("=====INVALID BROWSER NAME=================");
		}
	
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * This method will give the ThreadLocal driver
	 * @return tlDriver
	 */
	  public static WebDriver getDriver() {
		  return tlDriver.get();
		  
	  }
	
	/**
	 * This method is used to initialize the properties from config file
	 * 
	 * @return
	 */
	// mvn clean install -Denv="evn_name"

	public Properties init_Prop() {
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
//		System.out.println("Running on the test environment : " + envName);
		Log.info("Running on the test environment : " + envName);

		try {

			if (envName == null) {
//				System.out.println("Env is null..So running of the QA env");
				Log.warn("Env is null..So running tests on the QA env");
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
			} else {
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;

				default:
//					System.out.println("Please enter right environment name ==> " + envName);
					Log.error("Please enter right environment name ==> " + envName);
					throw new FrameWorkException("===== INVALID ENVIRONMENT NAME ========");
				}
			}

			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}
	
	
	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + " _" + System.currentTimeMillis()+ ".png ";
		File destination = new File(path);
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}
	
	
	
}
