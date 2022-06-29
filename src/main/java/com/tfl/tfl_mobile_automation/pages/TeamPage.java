package com.tfl.tfl_mobile_automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.enums.LocatorType;
import com.atmecs.falcon.automation.verifyresult.Verify;
import com.tfl.tfl_mobile_automation.constants.FilePathConstants;
import com.tfl.tfl_mobile_automation.page.keys.TeamPageKeys;
import com.tfl.tfl_mobile_automation.utils.PropertyParser;
import com.tfl.tfl_mobile_automation.utils.CommonFunctions;

public class TeamPage {
	PropertyParser propertyParser;
    public TeamPage() {
    	propertyParser = new PropertyParser(FilePathConstants.TEAMPAGE_FILE_PATH);
    }
    
    public void clickTeamPage(Browser browser){
    	String buttonXPath = propertyParser.getPropertyValue(TeamPageKeys.TEAM_PAGE_KEY);
    	browser.getWait().waitForElementPresence(LocatorType.XPATH, buttonXPath, 20);
    	WebElement element = browser.getDriver().findElement(By.xpath(buttonXPath));
    	browser.getMouse().mouseClick(element);
    } 
    
   public void checkPlayers(Browser browser) {
       String playersXpath=propertyParser.getPropertyValue(TeamPageKeys.PLAYERS_NAME);
 	   browser.getWait().waitForElementPresence(LocatorType.XPATH, playersXpath, 10);
 	   List<WebElement> elements = browser.getDriver().findElements(By.xpath(playersXpath));
	   for (WebElement element:elements) {
    		boolean result= CommonFunctions.isDisplayed(browser,element);
    		Verify.verifyBoolean(result, true, "players name displayed");
	   }
	   
   }
    

}
