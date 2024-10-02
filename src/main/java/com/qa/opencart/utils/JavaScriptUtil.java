package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptUtil {

	private WebDriver driver;
	private JavascriptExecutor js;

	public JavaScriptUtil(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor) driver;
	}

	public String getTitleJavaScript() {
		return js.executeScript("return document.title;").toString();

	}

	public String getURLJavaScript() {
		return js.executeScript("return document.URL;").toString();
	}

	public void generateJsAlert(String message) {
		js.executeScript("alert('" + message + "')");
	}

	public String getPageInnerText() {
		return js.executeScript("return document.documentElement.innerText;").toString();

	}

	public void goBackWithJs() {
		js.executeScript("history.go(-1)");
	}

	public void goForwardwithJs() {
		js.executeScript("history.go(1)");
	}

	public void pageRefereshWithJs() {
		js.executeScript("history.go(0)");
	}

	public void pageZoom(String zoomPercentage) {
		String zoom = "document.body.style.zoom = '" + zoomPercentage + "%'";
		js.executeScript(zoom);
	}

	public void scrollPageDown() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}

	public void scrollPageUp() {
		js.executeScript("window.scrollTo(document.body.scrollHeight,0);");
	}

	public void scrollMiddlePage() {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight/2);");
	}

	public void scrollPageDown(String height) {
		js.executeScript("window.scrollTo(0,'" + height + "')");
	}

	public void scrollIntoViewElement(WebElement element) {
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor='" + color + "'", element);
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {

		}
	}

	public void flash(WebElement element) {
		String bgcolor = element.getCssValue("backgroundColor");
		for (int i = 0; i < 100; i++) {
			changeColor("rgb(0,200,0)", element);
			changeColor(bgcolor, element);
		}
	}

}
