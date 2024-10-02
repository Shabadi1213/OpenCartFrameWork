package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterationFormPage {
	
     WebDriver driver;
     ElementUtil eleUtil;
     

	By registerAccountHeader = By.tagName("h1");
	By firstName = By.id("input-firstname");
	By lastName = By.id("input-lastname");
	By eMailid = By.id("input-email");
	By telephone = By.id("input-telephone");
	By password = By.id("input-password");
	By confpassword = By.id("input-confirm");
	By newsletterRadioBtn = By.name("newsletter");
	By privacyPolicyCheckBox = By.name("agree");
	By submitBtn = By.cssSelector("input.btn-primary");
	By accountCreatedSuccessHeader = By.cssSelector("#content h1");
	By logoutLink = By.linkText("Logout");
	By loginLink = By.linkText("Login");
	By registerLink = By.linkText("Register");
	

	
	public RegisterationFormPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public String getRegPageHeaderValue() {
		String headerValue = eleUtil.waitForElementVisible(registerAccountHeader, AppConstants.WAIT_FOR_MEDIUM_DURATION)
				.getText();
		System.out.println(headerValue);
		return headerValue;
	}
	

	public boolean fillRegisterationForm(String firstname,String lastname,String emailId,String mobileNumber,String pwd) {
		WebElement element = eleUtil.waitForElementVisible(firstName, AppConstants.WAIT_FOR_MEDIUM_DURATION);
		eleUtil.doSendKeys(element, firstname);
		eleUtil.doSendKeys(lastName, lastname);
		eleUtil.doSendKeys(eMailid, emailId);
		eleUtil.doSendKeys(telephone, mobileNumber);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doSendKeys(confpassword, pwd);
		eleUtil.doClick(newsletterRadioBtn);
		eleUtil.doClick(privacyPolicyCheckBox);
		eleUtil.doClick(submitBtn);
		
		
		String successMesg = eleUtil.waitForElementVisible(accountCreatedSuccessHeader, AppConstants.WAIT_FOR_SHORT_DURATION)
				.getText();
		System.out.println(successMesg);
		
		if(successMesg.contains(AppConstants.ACCOUNT_CREATED_SUCCESS_HEADER)) {
			eleUtil.doClick(logoutLink);
			eleUtil.doClick(registerLink);
			return true;
		}else {
			return false;
		}
			
		}
		
		
		
	
	

	
	
	
}
