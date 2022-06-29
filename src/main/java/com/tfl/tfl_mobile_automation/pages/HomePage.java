package com.tfl.tfl_mobile_automation.pages;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.ui.seleniuminterfaces.IClick;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.verifyresult.Verify;
import com.tfl.tfl_mobile_automation.constants.FilePathConstants;
import com.tfl.tfl_mobile_automation.page.keys.HomePageKeys;
import com.tfl.tfl_mobile_automation.page.keys.SignInPageKeys;
import com.tfl.tfl_mobile_automation.utils.PropertyParser;

public class HomePage {
	   PropertyParser propertyParser;
	 
	    public HomePage() {
	    	propertyParser = new PropertyParser(FilePathConstants.HOMEPAGE_FILE_PATH);
	    }
	    public void verifyHomepage(Browser browser) {
	    	String homepageXpath=propertyParser.getPropertyValue(SignInPageKeys.HOMEPAGEWINDOW_KEY);
	    	boolean status= browser.getDriver().findElement(By.xpath(homepageXpath)).isDisplayed();
	    	Verify.verifyBoolean(status, true,"verify home page displayed");
	    	//verify.verifyIsDisplayed(LocatorType.XPATH,homepageXpath, true, "homepage not displayed");
	    }
	    public void clickProfile(Browser browser) {
	    	IClick click = browser.getClick();
			String profileButtonXPath = propertyParser.getPropertyValue(HomePageKeys.PROFILE_BUTTON_KEY);
			browser.getWait().waitForElementPresence(LocatorType.XPATH,profileButtonXPath , 20);
			click.waitandclick(LocatorType.XPATH, profileButtonXPath,5);	
			
	    }
	    
	    public void clickAppPreferences(Browser browser){
	    	String buttonXPath = propertyParser.getPropertyValue(HomePageKeys.APP_PREFERENCES_KEY);
	    	browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 20);
	    	WebElement element = browser.getDriver().findElement(By.xpath(buttonXPath));
	    	browser.getMouse().mouseClick(element);
	    }
	    public void unSelectTeam(Browser browser) throws InterruptedException {
	    	String column1TeamXpath=propertyParser.getPropertyValue(HomePageKeys.APP_PREFERENCES_FAVOURITE_TEAM_COUMN1);
	    	browser.getWait().waitForElementPresence(LocatorType.XPATH, column1TeamXpath, 20);
	    	WebElement element=browser.getDriver().findElement(By.xpath("(//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView)[1]"));
	    	browser.getMouse().mouseClick(element);
	    	/*List<WebElement> elements = browser.getDriver().findElements(By.xpath(column1TeamXpath));
	    	for(WebElement element:elements) {
	    		boolean result= element.isSelected();
	    		//Verify.verifyBoolean(result, false, "selected");
	    		if(result==true) {
	    			element.click();
	    			Thread.sleep(5000);	
	    		}	
	    	}*/
	    
	    }
	    public void selectTeam(Browser browser){
	    	String teamXpath=propertyParser.getPropertyValue(HomePageKeys.APP_PREFERENCES_FAVOURITE_TEAM_NAME_KEY);	    	
	    	browser.getWait().waitForElementPresence(LocatorType.XPATH, teamXpath, 20);	    
	    	List<WebElement> elements = browser.getDriver().findElements(By.xpath(teamXpath));
	    	Random random = new Random();
	    	int result = random.nextInt(elements.size());
	    	browser.getMouse().mouseClick(elements.get(result));	    	
	    }
	    public void clickBackButton(Browser browser){
	    	String buttonXPath = propertyParser.getPropertyValue(HomePageKeys.PROFILE_BACK_BUTTON_KEY);
	    	browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 20);
	    	WebElement element = browser.getDriver().findElement(By.xpath(buttonXPath));
	    	browser.getClick().clickOn(element);
	    } 

	   public void verifyLiveMatchBanner(Browser browser) {
		   String liveMatchBannerXpath=propertyParser.getPropertyValue(HomePageKeys.LIVE_MATCH_BANNER_KEY);
	 	   browser.getWait().waitForElementPresence(LocatorType.XPATH, liveMatchBannerXpath, 10);
	 	   WebElement element=browser.getDriver().findElement(By.xpath(liveMatchBannerXpath));
	    		boolean result= element.isDisplayed();
	    		Verify.verifyBoolean(result, true, "Live Match Banner displayed");
		   }
	   
	   public void verifyLiveMatchTeamLogo (Browser browser) {
	       String logoXpath=propertyParser.getPropertyValue(HomePageKeys.LIVE_MATCH_TEAM_LOGO_KEY);
	 	   browser.getWait().waitForElementPresence(LocatorType.XPATH, logoXpath, 10);
	 	   List<WebElement> elements = browser.getDriver().findElements(By.xpath(logoXpath));
		   for (WebElement element:elements) {
	    		boolean result= element.isDisplayed();
	    		Verify.verifyBoolean(result, true, "Team Logo displayed");
		   }   
	   }
	   public void verifyLiveMatchTeam (Browser browser) {
	       String playersXpath=propertyParser.getPropertyValue(HomePageKeys.LIVE_MATCH_TEAM_KEY);
	 	   browser.getWait().waitForElementPresence(LocatorType.XPATH, playersXpath, 10);
	 	   List<WebElement> elements = browser.getDriver().findElements(By.xpath(playersXpath));
		   for (WebElement element:elements) {
	    		boolean result= element.isDisplayed();
	    		Verify.verifyBoolean(result, true, "Team displayed");
		   }   
	   }
	  public void clickLiveMatch(Browser browser) {
  		String buttonXPath =propertyParser.getPropertyValue(HomePageKeys.LIVE_MATCH_BUTTON_KEY);
    	browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 10);
    	WebElement element = browser.getDriver().findElement(By.xpath(buttonXPath));
    	browser.getMouse().mouseClick(element);
	  }
	  public void verifyWtachLiveButton(Browser browser) {
	  		String buttonXPath =propertyParser.getPropertyValue(HomePageKeys.WATCH_LIVE_BUTTON_KEY);
	    	browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 10);
	    	WebElement element = browser.getDriver().findElement(By.xpath(buttonXPath));
	    	boolean result= element.isEnabled();
    		Verify.verifyBoolean(result, true, "Watch live button enabled");
	  }

}
