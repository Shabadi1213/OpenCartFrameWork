package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Step;




public class ElementUtil {
	
	private WebDriver driver;
	private Actions act;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(driver);
		jsUtil = new JavaScriptUtil(driver);
	}
	
	
	public void checkElementHighlight(WebElement element) {
		if(Boolean.parseBoolean(DriverFactory.isHighlight)) {
			jsUtil.flash(element);
		}
	}
	
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		checkElementHighlight(element);
		 return element;
	} 
	
	
	
	
	public void doSendKeys(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	public void doSendKeys(WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
		
	}
	
	public void doClickIfValue(By locator, String value) {
		String s=null;
		if(value.equals(s)) {
		getElement(locator).click();

	}}
	
	
	public void doClick(By locator) {
		getElement(locator).click();

	}


	public void doClearAndUpdate(By locator, String value) {
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}
	
	public void doClear(By locator) {
		getElement(locator).clear();
		
	}
	
	
	
	public String getText(By locator) {
		return getElement(locator).getText();
	}
	
	

	public boolean isElementDisplayed(By locator) {
		try {

			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("No Element found " + locator);
		}
		return false;

	}
	
	
	public boolean isElementEnabled(By locator) {
		return getElement(locator).isEnabled();
	}
	
	
	
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	 
	
	
	public List<String> getElementsText(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e:eleList) {
			String eletext = e.getText();
			if(eletext.length()!=0) {
			eleTextList.add(eletext);
			}
		}
		return eleTextList;
	}
	
	
	public String doElementAttribute(By locator,String value) {
		return getElement(locator).getAttribute(value);
	}
	
	
	
	public List<String> getFooterLinkText(String name) {
		List<WebElement>eleList = getElements(By.xpath("//h5[text()='"+name+"']/following-sibling::ul//a"));
		List<String> elestr = new ArrayList<String>();
		for(WebElement e:eleList) {
			String eleText = e.getText();
			elestr.add(eleText);
			System.out.println(eleText);
		} 
		 return elestr;
	}

	
	public void doSearch(By searchfield, By eleList, String searchKey, String matchValue) throws InterruptedException {
		boolean flag = false;
		doSendKeys(searchfield, searchKey);
		Thread.sleep(3000);
		List<WebElement> eles = getElements(eleList);
		int totalSuggestion = eles.size();
		System.out.println("Total number of suggestions == " + eles.size());

		if (totalSuggestion == 0) {
			throw new BrowserException(" ==== NO SUGGESTIONS FOUND ==== ");
		}

		for (WebElement e : eles) {
			String eleText = e.getText();
			System.out.println(eleText);
			if (eleText.contains(matchValue)) {
				e.click();
				Thread.sleep(3000);
				flag = true;
				break;
			}

		}

		if (flag) {
			System.out.println(matchValue + " found in suggestion list");
		} else {
			System.out.println(matchValue + " not found in suggestion list");
		}
		
	}
	
	
	
	public void SelectByText(By locator,String visibleText) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(visibleText);
	}
	
	
	public void SelectByValue(By locator,String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	
	public void SelectByIndex(By locator,int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	
	
	public void selectdropdownOptionvalue(List<WebElement> optionlist,String value) throws InterruptedException {
		for(WebElement e: optionlist) {
			String eleText = e.getText();
			System.out.println(eleText);
			if(eleText.contains(value)) {
				e.click();
				Thread.sleep(2000);
				break;
			}
		}
	}
	
	
	public List<String> getDropDownSelectOptionTextList(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionList = select.getOptions();
		List<String> strList = new ArrayList<String>();
		for(WebElement e:optionList) {
			String Text = e.getText();
			strList.add(Text);
		}
		 return strList;
	}
	
	
	
	// ******************Action Utils**************************** //
	
	/**
	 * 4 level Move to Element Parent to Child using By Locator
	 * @param level1
	 * @param level2
	 * @param level3
	 * @param level4
	 * @throws InterruptedException
	 */
	
	public void parentChildMenu(By level1, By level2,By level3,By level4) throws InterruptedException {
		
		doClick(level1);
	    Thread.sleep(1000);
	    act.moveToElement(getElement(level2)).perform();
	    Thread.sleep(1000);
	    act.moveToElement(getElement(level3)).perform();
	    Thread.sleep(1000);
	    String title = driver.getTitle();
	    System.out.println(title);
	}
	
	
	
	
	public  void doActionsSendKeys(By locator,String value) {
		act.sendKeys(getElement(locator), value).perform();
	}
	
	public void doActionsClick(By locator) {
		act.click(getElement(locator)).perform();
	}
	
	
	
//	public void doContextClick(By locator,By locator2,String optionName) throws InterruptedException {
//		Actions act = new Actions(driver);
//		act.contextClick(getElement(locator)).perform();
//		Thread.sleep(2000);
//		
//		List<WebElement> eleList = getElements(locator2);
//		for(WebElement e:eleList) {
//			String eleText= e.getText();
//			System.out.println(eleText);
//			if(eleText.equals(optionName)){
//				e.click();
//				break;
//			}
//		}
	
		
		
		
		public  void doContextClick(By locator1,String value) throws InterruptedException {
			act.contextClick(getElement(locator1)).perform();
			act.click(getElement(By.xpath("//span[text()='"+value+"']"))).perform();
			Thread.sleep(2000);
		}
		
		/**
		 * This will Enter the Character by Character with pause time 
		 * @param locator
		 * @param value
		 * @param delaytime
		 */
		
		public void ActionSendKeysPause(By locator,String value,int pauseTime) {
			char ch[] = value.toCharArray();
			for(char c: ch) {
				act.sendKeys(getElement(locator), String.valueOf(c)).pause(pauseTime).perform();
			}
		}
		
		
		
		
		//*************************Wait utils**********************************//
		
		
		
		public List<WebElement> getElements(By locator,int timeOut) {
			return waitForElementsVisible(locator, timeOut);
			}
		
		
		public  WebElement waitForElementPresence(By loator,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			WebElement element =  wait.until(ExpectedConditions.presenceOfElementLocated(loator));
			checkElementHighlight(element);
			return element;
		}
		@Step("waiting for Webelement using locator : {0} with timeout{1}")
		public  WebElement waitForElementVisible(By locator,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			WebElement element= wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			checkElementHighlight(element);
			return element;
		}
		
		
		@Step("clicking on element using locator :{0} within the timeout:{1}")
		public void doClick(By locator,int timeOut) {
			waitForElementVisible(locator, timeOut).click();
			
		}
		@Step("entering value :{1} into locator:{0} within timeout: {2}")
		public void doSendKeys(By locator,String value,int timeOut) {
			waitForElementVisible(locator, timeOut).sendKeys(value);
		}
		
		
		public List<WebElement> waitForElementsVisible(By locator,int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}
		
		
		
		
		public String  getPageUrl(String ExpectedUrl,int timeout) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			try {
			wait.until(ExpectedConditions.titleIs(ExpectedUrl));
			return driver.getCurrentUrl();
			}catch(TimeoutException e) {
				System.out.println("URL MISMATCH FOUND");
			}
			return "-1";

		}
		
		
		
			
			public String getUrlContains(String fractionUrl,int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				try {
		        if(wait.until(ExpectedConditions.urlContains(fractionUrl))) {
		        	String url = driver.getCurrentUrl();
		        return url;
				}}
				catch(TimeoutException e) {
					System.out.println("URL MisMatch found");
				}
				return "-1";	
		}
			
			
			
			
			public String getTitle(String expectedTitle,int timeOut) {
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    	try {
		    	if(wait.until(ExpectedConditions.titleIs(expectedTitle))) {
		    		String title = driver.getTitle();
//		    		System.out.println(title);
		    		return title;
		    	}}catch(TimeoutException e) {
		    		System.out.println("Mismatch in title");	
		    		
		    	}
		    	return "-1";
		    	} 
			
			
			
			public void getTitleContains(String fractionTitle,int timeOut) {
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    	try {
		    	if(wait.until(ExpectedConditions.titleContains(fractionTitle))) {
		    		String title = driver.getTitle();
		    		System.out.println(title);
		    	}}catch(TimeoutException e) {
		    		System.out.println("Mismatch in title");
		    	}
		    		
		    	}
			
			
			
			
			public Alert waitForAlertAndSwitch(int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
				return wait.until(ExpectedConditions.alertIsPresent());
			}

			public  String getAlertText(int timeOut) {
				return waitForAlertAndSwitch(timeOut).getText();
			}

			public  void acceptAlert(int timeOut) {
				waitForAlertAndSwitch(timeOut).accept();
			}

			public  void dismisstAlert(int timeOut) {
				waitForAlertAndSwitch(timeOut).dismiss();
			}
		   
			public  void enterValueOnAlert(String value,int timeOut) {
				waitForAlertAndSwitch(timeOut).sendKeys(value);
			}
		
			
			
			
			//Wait for frame
			
			
			public void waitForFrameLocatorAndSwitchToIt(By frameLocator,int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
			}
			
			
			public void waitForFrameLocatorAndSwitchToIt(int frameIndex,int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
			}
			
			public void waitForFrameLocatorAndSwitchToIt( String nameOrId,int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
			}
			
			public void waitForFrameLocatorAndSwitchToIt( WebElement frameElement,int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
			}
			
			
			
			
			public boolean waitForNewWindowOrTab(int expectedNumberofWindows,int timeOut) {
				WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
				try {
				if(wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberofWindows))) {
					return true;
				}
				}
				catch(TimeoutException e) {
					System.out.println("Number of Windows didn't Match...");
				}	return false;
				

			
			
}
	
}
	
	

		 
	



