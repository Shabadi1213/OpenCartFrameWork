package com.qa.opencart.tests;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	WebDriver driver;
	
	@BeforeClass
	public void accsSetUp() {
		accPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	

	@Test()
	public void getAccountsPageTitleTest() {
		String actualTitle = accPage.getAccountsPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNTS_PAGE_TITLE);	
	}
	
	@Test
	public void getHeadersTest() {
		List<String> actualHeaderList = accPage.getAccountsHeaders();
		Assert.assertEquals(actualHeaderList, AppConstants.HEADER_LIST);
		
	}
	
	
	@DataProvider
	public Object[][]  getSearchKey() {
		return new Object[][] {
			{"Macbook",3},
			{"imac",1},
			{"Samsung",2}
		};
	}   
	
	
	@Test(dataProvider = "getSearchKey")
	public void searchCountTest(String searchKey,int searchtCount) {
		resultsPage = accPage.doSearch(searchKey);
		Assert.assertEquals(resultsPage.getSearchResultsCount(),searchtCount);
	}
	
	
   
	
	@DataProvider
	public Object[][]  getproductName() {
		return new Object[][] {
			{"macbook","MacBook"},
			{"macbook","MacBook Air"},
			{"imac","iMac"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"}
		};
	}
	
	@Test(dataProvider = "getproductName")
	public void searchTest(String searchKey,String productName) {
		resultsPage = accPage.doSearch(searchKey);
		prodInfoPage = resultsPage.selectProduct(productName);
		Assert.assertEquals(prodInfoPage.getproductHeader(),productName);
	}
	

}
