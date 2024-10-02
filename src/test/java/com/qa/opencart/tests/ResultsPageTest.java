package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ResultsPageTest extends BaseTest {
	
	@BeforeClass
	public void resPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test
	public void getResultsPageHeader() {
		resultsPage = accPage.doSearch(AppConstants.PRODUCT);
		String searchResultsHeaderValue= resultsPage.getSearchResultsHeader();
		Assert.assertEquals(searchResultsHeaderValue, AppConstants.SEARCH_RESULTS_HEADER);
	}
	
	
	@Test
	public void selectSearchedProductTest() {
		prodInfoPage = resultsPage.selectProduct(AppConstants.PRODUCT);
		String actualHeaderValue = prodInfoPage.getproductHeader();
		Assert.assertEquals(actualHeaderValue, AppConstants.PRODUCT);
	}
	
	
	
	

}
