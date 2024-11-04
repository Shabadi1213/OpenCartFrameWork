package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	
	//By locators
	
	By userName = By.id("input-email");
	By password = By.id("input-password");
	By logo = By.cssSelector("img.img-responsive");
	By submitBtn = By.xpath("//input[@type='submit']");
	By registerLink = By.linkText("Register");
	By loginCredentilasError = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//Constructor..
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		
	}
	
	//Page Actions
      @Step("Featching the login page title")
	 public String getLoginPageTitle() {
		 String pageTitle = eleUtil.getTitle(AppConstants.LOGIN_PAGE_TITLE, AppConstants.WAIT_FOR_SHORT_DURATION);
		 System.out.println(pageTitle);
		 return pageTitle;
		 
	 }
	 
      @Step("Featching the login page title")
	 public String getLoginPageUrl() {
		String fractionalUrl =  eleUtil.getUrlContains(AppConstants.LOGIN_URL,AppConstants.WAIT_FOR_SHORT_DURATION);
		System.out.println(fractionalUrl);
		return fractionalUrl;
		
	 }
	 
//	 public String  getLoginPageUrl() {
//		String url =  eleUtil.getUrlContains(AppConstants.LOGIN_PAGE_TITLE,AppConstants.WAIT_FOR_SHORT_DURATION);
//		 System.out.println(url);
////		 return url;
//		 
//	 }
      @Step("Login in to with user name : {0} and password: {1}")
	 public AccountsPage doLogin(String username,String pwd) {
		 System.out.println("Crden are:" + username+ ":"+ pwd);
		 eleUtil.waitForElementVisible(userName, AppConstants.WAIT_FOR_SHORT_DURATION).sendKeys(username);
		 eleUtil.waitForElementVisible(password, AppConstants.WAIT_FOR_SHORT_DURATION).sendKeys(pwd);
		 eleUtil.doClick(submitBtn);
		 return new AccountsPage(driver);
	 }
	
      
      // Negative login Method
      @Step("Login in to with user name : {0} and password: {1}")
 	 public boolean doInvalidLogin(String username,String pwd) {
 		 System.out.println("Crden are:" + username+ ":"+ pwd);
 		 WebElement userNameEle = eleUtil.waitForElementVisible(userName, AppConstants.WAIT_FOR_SHORT_DURATION);
 		 eleUtil.doSendKeys(userNameEle, username);
 		 eleUtil.waitForElementVisible(password, AppConstants.WAIT_FOR_SHORT_DURATION).sendKeys(pwd);
 		 eleUtil.doClick(submitBtn);
 		 String errormsg = eleUtil.waitForElementVisible(loginCredentilasError, AppConstants.WAIT_FOR_MEDIUM_DURATION).getText();
 		 Log.info("Login error : " + errormsg);
 		 if(errormsg.contains(AppConstants.LOGIN_ERROR)) {
 			 return true;
 		 }
 		  	return false;
 	 }
      
      
     @Step("Navigating to Register page")
	 public RegisterationFormPage doNavigateToRegistrationPage() {
		 eleUtil.doClick(registerLink, AppConstants.WAIT_FOR_SHORT_DURATION);
		 return new RegisterationFormPage(driver);

	 }
}
