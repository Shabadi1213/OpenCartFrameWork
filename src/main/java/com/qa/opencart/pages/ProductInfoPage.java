package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	WebDriver driver;
	ElementUtil eleUtil;
	
	
	By productHeader = By.tagName("h1");
	By productImageCount = By.cssSelector("#content ul img");
	By inputQuantity = By.name("quantity");
	By addToCartBtn = By.id("button-cart");
	By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	
	Map<String,String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getproductHeader() {
		String productHeaderValue = eleUtil.waitForElementVisible(productHeader, AppConstants.WAIT_FOR_MEDIUM_DURATION).getText();
		System.out.println("The Product name displayed is :" + productHeaderValue);
		return productHeaderValue;
	}


	public int getProductImagesCount() {
		int imageCount = eleUtil.waitForElementsVisible(productImageCount,AppConstants.WAIT_FOR_MEDIUM_DURATION).size();
		System.out.println("Images Count  : " + imageCount);
		return imageCount;
	}
	
   
	
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		productMap = new LinkedHashMap<String, String>();
		for (WebElement meta : metaList) {
			String metaText = meta.getText();
			String metaData[] = metaText.split(":");
			String metaKey = metaData[0].trim();
			String metaValue = metaData[1].trim();
			productMap.put(metaKey, metaValue);
		}
	}
		
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String price = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();
		productMap.put("productPrice", price);
		productMap.put("extraPrice", exTaxPrice);
		
	}
	
	
	public Map<String,String> getProductData() {
		productMap = new HashMap<String, String>();
		productMap.put("ProductHeader", getproductHeader());
		getProductMetaData();
		getProductPriceData();
		System.out.println("Product Data : " + productMap);
		return productMap;
	}   
	
	
	public void enterProductQuantity(String quantity) {
		eleUtil.waitForElementVisible(inputQuantity, AppConstants.WAIT_FOR_SHORT_DURATION).sendKeys(quantity);
		eleUtil.doClick(addToCartBtn);
		
	}
	
	
}
