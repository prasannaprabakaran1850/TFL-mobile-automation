package com.tfl.tfl_mobile_automation.pages;

import org.openqa.selenium.By;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.ui.selenium.Click;
import com.atmecs.falcon.automation.ui.selenium.Verify;
import com.atmecs.falcon.automation.ui.seleniuminterfaces.IClick;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.tfl.tfl_mobile_automation.constants.FilePathConstants;
import com.tfl.tfl_mobile_automation.page.keys.WelcomePageKeys;
import com.tfl.tfl_mobile_automation.utils.PropertyParser;

public class WelcomePage {
	PropertyParser propertyParser;
    public WelcomePage() {
    	propertyParser = new PropertyParser(FilePathConstants.WELCOMEPAGE_FILE_PATH);
    }
    
    public void clickSignInButton(Browser browser) {
		IClick click = browser.getClick();
		String buttonXPath = propertyParser.getPropertyValue(WelcomePageKeys.SIGNIN_SIGNUP_BUTTON_KEY);
		click.waitandclick(LocatorType.XPATH, buttonXPath, 20);
    }
    public void clickAndVerifyGuestButton(Browser browser) {
		Click click = browser.getClick();
		String buttonXPath = propertyParser.getPropertyValue(WelcomePageKeys.DISCOVER_GUEST_BUTTON_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 20);
		boolean status= browser.getDriver().findElement(By.xpath(buttonXPath)).isDisplayed();
    	Verify.verifyBoolean(status, true,"verify DISCOVER AS A GUEST displayed");
		click.waitandclick(LocatorType.XPATH, buttonXPath, 20);
    }

}
