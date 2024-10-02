package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductInfoPageTest extends BaseTest {
	
	@BeforeClass
	public void prodInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void ProductHeaderTest() {
		resultsPage = accPage.doSearch(AppConstants.SEARCH_KEY);
		prodInfoPage= resultsPage.selectProduct(AppConstants.PRODUCT);
		Assert.assertEquals(prodInfoPage.getproductHeader(), AppConstants.PRODUCT);
		
	}
	
	@DataProvider
	public Object[][] getProductImagesCountData() {
		return new Object[][] {
			{"MacBook","MacBook Pro",4},
			{"imac","iMac",3},
			{"Samsung", "Samsung SyncMaster 941BW",1},
			{"Samsung", "Samsung Galaxy Tab 10.1",7},
		};
	}
	
	@Test(dataProvider ="getProductImagesCountData" )
	public void productImagesCountTest(String searchKey,String productName,int imagesCount) {
		resultsPage = accPage.doSearch(searchKey);
		prodInfoPage= resultsPage.selectProduct(productName);
		int actualImagesCount = prodInfoPage.getProductImagesCount();
		Assert.assertEquals(actualImagesCount, imagesCount);
	} 
	
	
	
	@DataProvider
	public Object[][] getDataForProductInfo() {
		return new Object[][] {
			{"macbook","MacBook Pro","Apple","Product 18","800","In Stock","$2,000.00","$2,000.00"},
			{"macbook","MacBook","Apple","Product 16","600","In Stock","$602.00","$500.00"},
			{"macbook","MacBook Air","Apple","Product 17","700","Out Of Stock","$1,202.00","$1,000.00"},
		
		};
	}
	
	
	@Test(dataProvider ="getDataForProductInfo" )
	public void productInfoTest(String searchKey,String productName,String Brand,String productCode,String rewardPoint,String availability,String productPrice,String extraPrice) {
		resultsPage = accPage.doSearch(searchKey);
		prodInfoPage= resultsPage.selectProduct(productName);
		Map<String,String> actualProductDataMap=prodInfoPage.getProductData();
		
		
		SoftAssert.assertEquals(actualProductDataMap.get("Brand"),Brand);
		SoftAssert.assertEquals(actualProductDataMap.get("Product Code"),productCode);
		SoftAssert.assertEquals(actualProductDataMap.get("Reward Points"),rewardPoint);
		SoftAssert.assertEquals(actualProductDataMap.get("Availability"),availability);
		SoftAssert.assertEquals(actualProductDataMap.get("productPrice"),productPrice);
		SoftAssert.assertEquals(actualProductDataMap.get("extraPrice"),extraPrice);
		SoftAssert.assertAll();
	}
	
	public void enterProductQuantityTest() {
		prodInfoPage.enterProductQuantity("1");
	}
}


