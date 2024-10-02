package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	WebDriver driver;
	ElementUtil eleUtil;
	
	
	
	By searchHeader = By.cssSelector("div#content h2");
	By seachResults = By.cssSelector("div.product-thumb ");
	By selectProduct = By.linkText("MacBook Pro");
	
	

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getSearchResultsHeader() {
		String searchHeaderValue= eleUtil.waitForElementVisible(searchHeader, AppConstants.WAIT_FOR_SHORT_DURATION).getText();
		System.out.println(searchHeaderValue);
		return searchHeaderValue;
	}
	
	
	public int getSearchResultsCount() {
		int productCount = eleUtil.waitForElementsVisible(seachResults, AppConstants.WAIT_FOR_MEDIUM_DURATION).size();
		System.out.println("Search results count ==> " + productCount);
		return productCount;
	}
	
	public ProductInfoPage selectProduct(String productName) {
		eleUtil.doClick(By.linkText(productName));
		return new ProductInfoPage(driver);
	
	}
	
	
	

}
