package com.tfl.tfl_mobile_automation.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.verifyresult.Verify;
import com.tfl.tfl_mobile_automation.constants.FilePathConstants;
import com.tfl.tfl_mobile_automation.page.keys.SettingsPageKeys;
import com.tfl.tfl_mobile_automation.utils.PropertyParser;

public class SettingsPage {
	PropertyParser propertyParser;
    public SettingsPage() {
    	propertyParser = new PropertyParser(FilePathConstants.SETTINGSPAGE_FILE_PATH);
    }

    
    public void clickAppPreferences(Browser browser){
    	String buttonXPath = propertyParser.getPropertyValue(SettingsPageKeys.APP_PREFERENCES_KEY);
    	browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 20);
    	WebElement element = browser.getDriver().findElement(By.xpath(buttonXPath));
    	browser.getMouse().mouseClick(element);
    }

    public void selectTeam(Browser browser){
    	String teamXpath=propertyParser.getPropertyValue(SettingsPageKeys.APP_PREFERENCES_FAVOURITE_TEAM_NAME_KEY);	    	
    	browser.getWait().waitForElementPresence(LocatorType.XPATH, teamXpath, 20);	    
    	List<WebElement> elements = browser.getDriver().findElements(By.xpath(teamXpath));
    	Random random = new Random();
    	int result = random.nextInt(elements.size());
    	browser.getMouse().mouseClick(elements.get(result));	    	
    }
    public void clickBackButton(Browser browser){
    	String buttonXPath = propertyParser.getPropertyValue(SettingsPageKeys.PROFILE_BACK_BUTTON_KEY);
    	browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 20);
    	WebElement element = browser.getDriver().findElement(By.xpath(buttonXPath));
    	browser.getClick().clickOn(element);
    }
	 public void verifyAppPreferenceTeam(Browser browser) {
		 String teamXpath=propertyParser.getPropertyValue(SettingsPageKeys.APP_PREFERENCES_FAVOURITE_TEAM_NAME_KEY);
		 String teamLogoXpath=propertyParser.getPropertyValue(SettingsPageKeys.APP_PREFERENCES_FAVOURITE_TEAM_LOGO_KEY);
	 	 browser.getWait().waitForElementPresence(LocatorType.XPATH, teamXpath, 10);
	 	  List<WebElement> team = browser.getDriver().findElements(By.xpath(teamXpath));
	 	  List<WebElement> teamLogo = browser.getDriver().findElements(By.xpath(teamLogoXpath));
		  for (int index=0;index<team.size();index++) {
			    String teamName=team.get(index).getText();
	    		boolean result= team.get(index).isDisplayed();
	    		Verify.verifyBoolean(result, true, "Team "+teamName+" displayed");
	    		boolean logo = teamLogo.get(index).isDisplayed();
	    		Verify.verifyBoolean(logo, true, "Team Logo of "+teamName+" displayed");
		   }   
	   }
	 public void verifyAppPreferenceFavouriteContent(Browser browser) {
		 browser.getPageScroll().scrollTo("Play Games");
		 String favouriteContentXpath=propertyParser.getPropertyValue(SettingsPageKeys.APP_PREFERENCES_FAVOURITE_CONTENT_KEY);
		 String favouriteContentButtonXpath=propertyParser.getPropertyValue(SettingsPageKeys.APP_PREFERENCES_FAVOURITE_CONTENT_BUTTON_KEY);
	 	 browser.getWait().waitForElementPresence(LocatorType.XPATH, favouriteContentXpath, 10);
	 	 List<WebElement> favouriteContent = browser.getDriver().findElements(By.xpath(favouriteContentXpath));
	 	 List<WebElement> favouriteContentButton = browser.getDriver().findElements(By.xpath(favouriteContentButtonXpath));
		  for (int index=0;index<favouriteContent.size();index++) {
			    String favouriteContentName=favouriteContent.get(index).getText();
	    		boolean result= favouriteContent.get(index).isDisplayed();
	    		Verify.verifyBoolean(result, true, favouriteContentName+" displayed");
	    		boolean logo = favouriteContentButton.get(index).isDisplayed();
	    		Verify.verifyBoolean(logo, true, favouriteContentName+" Button displayed");
	    		browser.getMouse().mouseClick(favouriteContentButton.get(index));
	    		browser.getMouse().mouseClick(favouriteContentButton.get(index));
		   }   
	   }
	 public void verifyAppPreferenceMostlyDo(Browser browser) {
		 String mostlyDoXpath=propertyParser.getPropertyValue(SettingsPageKeys.APP_PREFERENCES_MOSTLY_DO_KEY);
		 String mostlyDoButtonXpath=propertyParser.getPropertyValue(SettingsPageKeys.APP_PREFERENCES_MOSTLY_DO_BUTTON_KEY);
	 	 browser.getWait().waitForElementPresence(LocatorType.XPATH, mostlyDoXpath, 10);
	 	 List<WebElement> mostlyDoText = browser.getDriver().findElements(By.xpath(mostlyDoXpath));
	 	 List<WebElement> mostlyDoTextButton = browser.getDriver().findElements(By.xpath(mostlyDoButtonXpath));
		  for (int index=0;index<mostlyDoText.size();index++) {
			    String Name=mostlyDoText.get(index).getText();
	    		boolean result= mostlyDoText.get(index).isDisplayed();
	    		Verify.verifyBoolean(result, true,Name+" displayed");
	    		boolean logo = mostlyDoTextButton.get(index).isDisplayed();
	    		Verify.verifyBoolean(logo, true, Name+" Button displayed");
	    		browser.getMouse().mouseClick(mostlyDoTextButton.get(index));
	    		browser.getMouse().mouseClick(mostlyDoTextButton.get(index));
		   }   
	   }
}
