package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;



@Epic("Epic MDE-1234 -Open Cart Login test" )
@Feature("Feature 101: Login feature")
@Story("MDE-234:Allfeatures releated to OpenCart")
public class LoginPageTest extends BaseTest{
	
	@Severity(SeverityLevel.MINOR)
	@Description("Login page test...")
	@Feature("Feature-MDE234:Title feature")
	@Owner("RAvi Shabadi")
	@Link(name ="Login Page",url = "https://naveenautomationlabs.com/opencart/index.php?route=account/login")
	@Test
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
//	@Test
//	public void loginPageUrlTest() {
//	String actualUrl = loginPage.getLoginPageUrl();
//	Assert.assertTrue(actualUrl.contains(AppConstants.LOGIN_URL));
//	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("Featching page url")
	@Feature("Feature MDE 345: Url Test")
	@Issue("MDP-2345")
	@Test
	public void getLoginPageUrlTest() {
		String pageUrl = loginPage.getLoginPageUrl();
		Assert.assertTrue(pageUrl.contains(AppConstants.LOGIN_URL));
	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = Integer.MAX_VALUE)
	public void loginToPageTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		String actualTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

}
