package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterationFormPage;
import com.qa.opencart.pages.ResultsPage;

import io.qameta.allure.Step;

public class BaseTest{
	WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accPage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage prodInfoPage;
	protected RegisterationFormPage registerPage;
	
	protected SoftAssert SoftAssert;
	
	@Step("Setup the browser :{0} and initalize the properties")
	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	public void setup(@Optional("chrome")String browserName,@Optional String browserversion,@Optional String testname) {
		df = new DriverFactory();
		prop = df.init_Prop();
		
		//Check if browser name coming from testng.xml
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserversion);
			prop.setProperty("testname", testname);
		}
		
		driver= df.init_driver(prop);
		loginPage = new LoginPage(driver);
		SoftAssert = new SoftAssert();
	}
	
	
	@Step("Closing the browser")
	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
