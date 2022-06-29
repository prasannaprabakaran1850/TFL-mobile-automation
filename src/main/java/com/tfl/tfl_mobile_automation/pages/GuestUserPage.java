package com.tfl.tfl_mobile_automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.ui.selenium.Click;
import com.atmecs.falcon.automation.ui.selenium.Verify;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.tfl.tfl_mobile_automation.constants.FilePathConstants;
import com.tfl.tfl_mobile_automation.page.keys.GuestUserPageKeys;
import com.tfl.tfl_mobile_automation.utils.PropertyParser;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class GuestUserPage {
    PropertyParser propertyParser;
    public GuestUserPage() {
    	propertyParser = new PropertyParser(FilePathConstants.GUEST_USER_FILE_PATH);
    }

    public void clickNext(Browser browser){
    	try {
    		browser.getPageScroll().scrollTo("Next");
    		String nextButtonXPath = propertyParser.getPropertyValue(GuestUserPageKeys.NEXT_BUTTON_KEY);
        	browser.getWait().waitForElementPresence(LocatorType.XPATH, nextButtonXPath, 10);
        	WebElement element = browser.getDriver().findElement(By.xpath(nextButtonXPath));
        	browser.getMouse().mouseClick(element);
    	}
    	catch(Exception e) {
    	
		MobileElement element = (MobileElement) browser.getDriver().findElement(MobileBy.AndroidUIAutomator(
		        "new UiScrollable(new UiSelector().scrollable(true))" +
		         ".scrollIntoView(new UiSelector().text(\"Next\"))"));
		element.click();
    	}
		
    }
    public void clickFinish(Browser browser){
    	String finishButtonXPath = propertyParser.getPropertyValue(GuestUserPageKeys.FINISH_BUTTON_KEY);
    	browser.getWait().waitForElementPresence(LocatorType.XPATH, finishButtonXPath, 20);
    	WebElement element = browser.getDriver().findElement(By.xpath(finishButtonXPath));
    	browser.getMouse().mouseClick(element);
    	
    	
    }	
    
    public void clickSkippAll(Browser browser){
    	String buttonXPath = propertyParser.getPropertyValue(GuestUserPageKeys.SKIP_ALL_BUTTON_KEY);
    	browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 20);
    	WebElement element = browser.getDriver().findElement(By.xpath(buttonXPath));
    	browser.getMouse().mouseClick(element);
    	
    	
    }
    public void verifyElementClickable(Browser browser) {
    	String favouriteContentXpath = propertyParser.getPropertyValue(GuestUserPageKeys.FAVOURITE_ACTIVITIES_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH,"//android.widget.ScrollView", 20);
		//browser.getWait().waitForElementToBeClickable(LocatorType.XPATH, "(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[1]", 5);
		List<WebElement> elements=browser.getDriver().findElements(By.xpath(favouriteContentXpath));
		for(int i=2;i<elements.size();i++) {
			browser.getWait().waitForElementToBeClickable(LocatorType.XPATH,"(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)['"+i+"']", 5);

		}
		for (WebElement newelement : elements){
			boolean status=newelement.isEnabled();
	    	Verify.verifyBoolean(status, true,"verify enabled");
			newelement.click();
			
		}
		 	
		
    }
}
