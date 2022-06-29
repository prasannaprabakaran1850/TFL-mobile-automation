package com.tfl.tfl_mobile_automation.module;

import org.testng.annotations.Test;

import com.atmecs.falcon.automation.appium.manager.UserBaseTest;
import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.tfl.tfl_mobile_automation.pages.GuestUserPage;
import com.tfl.tfl_mobile_automation.pages.HomePage;
import com.tfl.tfl_mobile_automation.pages.SettingsPage;
import com.tfl.tfl_mobile_automation.pages.TeamPage;
import com.tfl.tfl_mobile_automation.pages.WelcomePage;
/*
To check the team players' details based on the user preferences
 */
public class UserPreferenceTeamTest  extends UserBaseTest{
    private ReportLogService report = new ReportLogServiceImpl(TC07GuestUserTest.class);

    @Test
    public void userPreferencesVerification() throws InterruptedException {
        Browser browser = new Browser();
        browser.resetDriver(driver);
        
        report.info("User Preferences team verification starts..."); 
        WelcomePage welcomePage =new WelcomePage(); 
        GuestUserPage guestUserPage = new GuestUserPage();
        welcomePage.clickAndVerifyGuestButton(browser);
        report.info("Click Guest button");
        guestUserPage.clickNext(browser);
        report.info("Click on Next button");
        guestUserPage.clickSkippAll(browser);
        report.info("Click on Skipp All");
        HomePage homePage=new HomePage();
        homePage.clickProfile(browser);
        report.info("Click on profile");
        SettingsPage settingsPage=new SettingsPage();
        settingsPage.clickAppPreferences(browser);
        report.info("Click on App Preferences");
        settingsPage.selectTeam(browser);
        report.info("Randomly select team");
        settingsPage.clickBackButton(browser);
        report.info("Click on Back button");
        settingsPage.clickBackButton(browser);
        report.info("Click on Back button");
        TeamPage teamPage =new TeamPage();
        teamPage.clickTeamPage(browser);
        report.info("Click on Team Page");
        report.info("Verify players name displayed");
        teamPage.checkPlayers(browser);
        
    }
}