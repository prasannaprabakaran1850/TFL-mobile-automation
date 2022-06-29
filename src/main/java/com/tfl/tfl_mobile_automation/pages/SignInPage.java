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

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;


public class SignInPage {
    PropertyParser propertyParser;
    public SignInPage() {
    	propertyParser = new PropertyParser(FilePathConstants.SIGNIN_LOCATOR_FILE_PATH);
    }
    
	
    public void clickSignInButton(Browser browser) {
		IClick click = browser.getClick();
		String buttonXPath = propertyParser.getPropertyValue(SignInPageKeys.SIGNIN_SIGNUP_BUTTON_KEY);
    	browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 5);
		click.waitandclick(LocatorType.XPATH, buttonXPath, 20);
    }
    
    public void enterEmail(Browser browser,String email) {
		String emailXpath=propertyParser.getPropertyValue(SignInPageKeys.EMAIL_TEXT_BOX_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH,emailXpath, 5);
		boolean status= browser.getDriver().findElement(By.xpath(emailXpath)).isDisplayed();
    	Verify.verifyBoolean(status, true,"Email text field displayed");
     	ITextField textField = browser.getTextField();
	    textField.enterTextField(LocatorType.XPATH, emailXpath, email);

    }
    public void clickContinueButton(Browser browser) {
    	IClick click = browser.getClick();
		String continueButtonXPath = propertyParser.getPropertyValue(SignInPageKeys.CONTINUE_BUTTON_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH,continueButtonXPath, 5);
		click.waitandclick(LocatorType.XPATH,continueButtonXPath, 20);
	    try {
	    	String invalidEmailXpath=propertyParser.getPropertyValue(SignInPageKeys.INVALID_EMAIL_ADDRESS_KEY);
	    	browser.getWait().waitForElementPresence(LocatorType.XPATH, invalidEmailXpath, 5);
	    	boolean status= browser.getDriver().findElement(By.xpath(invalidEmailXpath)).isDisplayed();
	    	Verify.verifyBoolean(status, true,"verify INVALID_EMAIL_ADDRESS displayed");
	    	browser.quitBrowser();
	   	 }
	    catch(Exception e){
	    	//e.printStackTrace();
	    }
    }
    public void verifyUsernameInPasswordPage(Browser browser,String expected_username) throws InterruptedException {
    	
    	String usernameXpath = propertyParser.getPropertyValue(SignInPageKeys.USERNAME_IN_PASSWORD_PAGE);
    	browser.getWait().waitForElementPresence(LocatorType.XPATH,usernameXpath, 10);
		boolean status= browser.getDriver().findElement(By.xpath(usernameXpath)).isDisplayed();
    	Verify.verifyBoolean(status, true,"username should be displayed");
    }
    public void verifyForgotPassword(Browser browser) {
    	Verify verify=  new Verify();
    	String forgotPasswordXpath=propertyParser.getPropertyValue(SignInPageKeys.FORGOT_PASSWORD_KEY);
    	browser.getWait().waitForElementPresence(LocatorType.XPATH,forgotPasswordXpath, 10);
		verify.verifyIsDisplayed(LocatorType.XPATH,forgotPasswordXpath, true, "forgot password not displayed");
    	
    }
    public void enterPassword(Browser browser,String password) {
    	String passwordTextFieldXPath = propertyParser.getPropertyValue(SignInPageKeys.PASSWORD_TEXT_BOX_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH,passwordTextFieldXPath, 10);
    	ITextField textField = browser.getTextField();
		textField.enterTextField(LocatorType.XPATH, passwordTextFieldXPath, password);

    }
    public void clickSignIn(Browser browser) throws InterruptedException {
    	IClick click = browser.getClick();
		String signInButtonXPath = propertyParser.getPropertyValue(SignInPageKeys.SIGN_IN_BUTTON_KEY);
		browser.getWait().waitForElementPresence(LocatorType.XPATH,signInButtonXPath, 15);
		click.waitandclick(LocatorType.XPATH, signInButtonXPath, 20);	
		try {
	    	String invalidPasswordXpath=propertyParser.getPropertyValue(SignInPageKeys.INVALID_PASSWORD_KEY);
			browser.getWait().waitForElementPresence(LocatorType.XPATH,invalidPasswordXpath, 5);
	    	boolean status= browser.getDriver().findElement(By.xpath(invalidPasswordXpath)).isDisplayed();
	    	Verify.verifyBoolean(status, true,"verify INVALID_PASSWORD displayed");
	    	browser.quitBrowser();
	   	 }
	    catch(Exception e){
	    	//e.printStackTrace();
	    }
    }
    public void verifyHomepage(Browser browser) {
    	String homepageXpath=propertyParser.getPropertyValue(SignInPageKeys.HOMEPAGEWINDOW_KEY);
    	browser.getWait().waitForElementPresence(LocatorType.XPATH,homepageXpath, 5);
    	boolean status= browser.getDriver().findElement(By.xpath(homepageXpath)).isDisplayed();
    	Verify.verifyBoolean(status, true,"verify home page displayed");
    	//verify.verifyIsDisplayed(LocatorType.XPATH,homepageXpath, true, "homepage not displayed");
    }
    
    public void clickProfile(Browser browser) {
    	IClick click = browser.getClick();
		String profileButtonXPath = propertyParser.getPropertyValue(SignInPageKeys.PROFILE_BUTTON_KEY);
    	browser.getWait().waitForElementPresence(LocatorType.XPATH,profileButtonXPath, 5);
		click.waitandclick(LocatorType.XPATH, profileButtonXPath, 20);	
		
    }
    public void clickSignOut(Browser browser) throws InterruptedException {
    	//browser.getWait().safeWait(3000);
    	//browser.getPageScroll().scrollToElementDown(browser,browser.getDriver().findElement(By.xpath("//android.widget.TextView[@text='Rate App']")) );
    	//String signoutButtonXPath = propertyParser.getPropertyValue(SignInPageKeys.SIGNOUT_BUTTON_KEY);
		MobileElement element = (MobileElement) browser.getDriver().findElement(MobileBy.AndroidUIAutomator(
		        "new UiScrollable(new UiSelector().scrollable(true))" +
		         ".scrollIntoView(new UiSelector().text(\"Sign out\"))"));
		element.click();
		//browser.getClick().waitandclick(LocatorType.XPATH, signoutButtonXPath, 20);
		
    }
    
    	
}