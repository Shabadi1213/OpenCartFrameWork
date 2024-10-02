package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	By accsHeadrs = By.cssSelector("#content h2");
	By search = By.name("search");
	By searchBtn = By.cssSelector("#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getAccountsPageTitle() {
		String title = eleUtil.getTitle(AppConstants.ACCOUNTS_PAGE_TITLE, AppConstants.WAIT_FOR_SHORT_DURATION);
		System.out.println(title);
       
		return title;
	}

	public List<String> getAccountsHeaders() {
		List<WebElement> headresList = eleUtil.waitForElementsVisible(accsHeadrs,
				AppConstants.WAIT_FOR_MEDIUM_DURATION);
		List<String> eleStr = new ArrayList<String>();
		for (WebElement e : headresList) {
			String eleText = e.getText();
			eleStr.add(eleText);
		}
		System.out.println(eleStr);
		return eleStr;
	}

	public ResultsPage doSearch(String searchKey) {
		WebElement searchEle = eleUtil.waitForElementVisible(search, AppConstants.WAIT_FOR_SHORT_DURATION);
		eleUtil.doSendKeys(searchEle, searchKey);
		eleUtil.doClick(searchBtn);
		return new ResultsPage(driver);
	}
	
	

}
