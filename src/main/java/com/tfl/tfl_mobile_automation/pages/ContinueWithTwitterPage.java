package com.tfl.tfl_mobile_automation.pages;

import org.openqa.selenium.By;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.ui.selenium.Verify;
import com.atmecs.falcon.automation.ui.seleniuminterfaces.IClick;
import com.atmecs.falcon.automation.ui.seleniuminterfaces.ITextField;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.tfl.tfl_mobile_automation.constants.FilePathConstants;
import com.tfl.tfl_mobile_automation.page.keys.SignInPageKeys;
import com.tfl.tfl_mobile_automation.utils.PropertyParser;

public class ContinueWithTwitterPage {
	PropertyParser propertyParser;
	public ContinueWithTwitterPage() {
		propertyParser = new PropertyParser(FilePathConstants.SIGNIN_LOCATOR_FILE_PATH);
	}
	public void clickContinueWithTwitter(Browser browser) {
		IClick click = browser.getClick();
		String buttonXPath = propertyParser.getPropertyValue(SignInPageKeys.CONTINUE_WITH_TWITTER_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 20);
		boolean status= browser.getDriver().findElement(By.xpath(buttonXPath)).isDisplayed();
    	Verify.verifyBoolean(status, true,"verify CONTINUE_WITH_TWITTER displayed");
    	click.waitandclick(LocatorType.XPATH, buttonXPath, 20);
		
	}
	public void enterTwitterUsername(Browser browser,String username) {
    	
		String usernameXpath=propertyParser.getPropertyValue(SignInPageKeys.TWITTER_USERNAME_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH, usernameXpath, 20);
		boolean status= browser.getDriver().findElement(By.xpath(usernameXpath)).isDisplayed();
		Verify.verifyBoolean(status, true,"verify user name text box displayed");
     	ITextField textField = browser.getTextField();
	    textField.enterTextField(LocatorType.XPATH, usernameXpath, username);
	}
	public void enterTwitterPassword(Browser browser,String password) {
    	
		String passwordXpath=propertyParser.getPropertyValue(SignInPageKeys.TWITTER_PASSWORD_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH,passwordXpath, 20);
		boolean status= browser.getDriver().findElement(By.xpath(passwordXpath)).isDisplayed();
		Verify.verifyBoolean(status, true,"verify password text box displayed");
     	ITextField textField = browser.getTextField();
	    textField.enterTextField(LocatorType.XPATH, passwordXpath, password);
	}
	public void clickAuthorizeButton(Browser browser) {
		IClick click = browser.getClick();
		String authorizeButtonXpath = propertyParser.getPropertyValue(SignInPageKeys.AUTHORIZE_APP_BUTTON_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH, authorizeButtonXpath, 20);
		boolean status= browser.getDriver().findElement(By.xpath(authorizeButtonXpath)).isEnabled();
		Verify.verifyBoolean(status, true,"verify AuthorizeButton enabled");
		click.waitandclick(LocatorType.XPATH,authorizeButtonXpath, 20);
	}
	

}
