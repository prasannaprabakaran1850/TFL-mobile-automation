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

public class ContinueWithFacebookPage {
	PropertyParser propertyParser;
	public ContinueWithFacebookPage() {
		propertyParser = new PropertyParser(FilePathConstants.SIGNIN_LOCATOR_FILE_PATH);
	}
	public void clickContinueWithFacebook(Browser browser) {
		IClick click = browser.getClick();
		String buttonXPath = propertyParser.getPropertyValue(SignInPageKeys.CONTINUE_WITH_FACEBOOK_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 20);
		boolean status= browser.getDriver().findElement(By.xpath(buttonXPath)).isDisplayed();
    	Verify.verifyBoolean(status, true,"verify CONTINUE_WITH_FACEBOOK displayed");
		click.waitandclick(LocatorType.XPATH, buttonXPath, 20);
		
	}
	public void enterFacebookUsername(Browser browser,String username) {
    	Verify verify=  new Verify();
		String usernameXpath=propertyParser.getPropertyValue(SignInPageKeys.FACEBOOK_USERNAME_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH, usernameXpath, 20);
		verify.verifyIsEnabled(LocatorType.XPATH,usernameXpath, true, "text enabled");
     	ITextField textField = browser.getTextField();
	    textField.enterTextField(LocatorType.XPATH, usernameXpath, username);
	}
	public void enterFacebookPassword(Browser browser,String password) {
    	Verify verify=  new Verify();
		String passwordXpath=propertyParser.getPropertyValue(SignInPageKeys.FACEBOOK_PASSWORD_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH, passwordXpath, 20);
		verify.verifyIsEnabled(LocatorType.XPATH,passwordXpath, true, "text enabled");
     	ITextField textField = browser.getTextField();
	    textField.enterTextField(LocatorType.XPATH, passwordXpath, password);
	}
	public void clickLoginButton(Browser browser) {
		IClick click = browser.getClick();
		String authorizeButtonXpath = propertyParser.getPropertyValue(SignInPageKeys.FACEBOOK_LOGIN_BUTTON_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH, authorizeButtonXpath, 20);
		Verify verify=  new Verify();
		verify.verifyIsDisplayed(LocatorType.XPATH,authorizeButtonXpath,true,"Authorize App Button should be displayed");
		click.waitandclick(LocatorType.XPATH,authorizeButtonXpath, 20);
	}
}
