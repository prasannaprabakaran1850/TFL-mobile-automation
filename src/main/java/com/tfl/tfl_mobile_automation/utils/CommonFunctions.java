package com.tfl.tfl_mobile_automation.utils;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;


public class CommonFunctions {

	public static boolean isDisplayed(Browser browser, WebElement element) {
		try {
			element.isDisplayed();
			return true;
		}

		catch (StaleElementReferenceException e) {
			return false;
		}
	}
	
	
}
