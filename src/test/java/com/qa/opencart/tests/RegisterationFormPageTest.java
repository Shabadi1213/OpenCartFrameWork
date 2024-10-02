package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;

public class RegisterationFormPageTest extends BaseTest{
	
	
	@BeforeClass
	public void navigateToRegisterPage() {
		registerPage = loginPage.doNavigateToRegistrationPage();
	}
	
	

	

	@Test
	public void getRegisterPageHeaderTest() {
		String actualHeaderText = registerPage.getRegPageHeaderValue();
		Assert.assertEquals(actualHeaderText, AppConstants.EXPECTED_REGISTER_PAGE_HEADER);
	}
	
	
	
	@Step("Registration Data")
	@Severity(SeverityLevel.BLOCKER)
	@DataProvider
	public Object[][] registerFormData() {
		return new Object[][] {
			{"Java1","Selenium","7345263463","test@1234"},
			{"Java2","Selenium","7345263463","test@1234"},
			{"Java3","Selenium","7345263463","test@1234"},
			{"Java5","Selenium","7345263463","test@1234"}
			
		};
	}
	
	
	public String getRandomEmail() {
		return "uiautoamtion"+System.currentTimeMillis()+"@gmail.com";
	}
	
	@Test(dataProvider="registerFormData")
	public void registerFormTest(String fname,String lname,String contactNumber,String pwd) {
		registerPage.fillRegisterationForm(fname, lname, getRandomEmail(), contactNumber, pwd);
	}
	

	
	
	
}

