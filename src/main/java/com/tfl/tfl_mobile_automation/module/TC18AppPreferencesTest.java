package com.tfl.tfl_mobile_automation.module;

import org.testng.annotations.Test;

import com.atmecs.falcon.automation.appium.manager.UserBaseTest;
import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.tfl.tfl_mobile_automation.pages.GuestUserPage;
import com.tfl.tfl_mobile_automation.pages.HomePage;
import com.tfl.tfl_mobile_automation.pages.SettingsPage;
import com.tfl.tfl_mobile_automation.pages.WelcomePage;

/*
To Verify the App Preference
 */

public class TC18AppPreferencesTest  extends UserBaseTest{
    private ReportLogService report = new ReportLogServiceImpl(TC07GuestUserTest.class);

    @Test
    public void appPreferencesVerification() throws InterruptedException {
        Browser browser = new Browser();
        browser.resetDriver(driver);
        
        report.info("App Preferences verification starts..."); 
        WelcomePage welcomePage =new WelcomePage();
        GuestUserPage guestUserPage = new GuestUserPage();
        welcomePage.clickAndVerifyGuestButton(browser);
        report.info("Click on Guest button");
        guestUserPage.clickNext(browser);
        report.info("Click on Next");
        guestUserPage.clickSkippAll(browser);
        report.info("Click on Skipp All");
        HomePage homePage=new HomePage();
        homePage.clickProfile(browser);
        report.info("Click on profile");
        SettingsPage settingsPage =new SettingsPage();
        settingsPage.clickAppPreferences(browser);
        report.info("Click on App Preferences ");
        settingsPage.verifyAppPreferenceTeam(browser);
        report.info("Verify App Preference Team and logo");
        settingsPage.verifyAppPreferenceFavouriteContent(browser);
        report.info("Verify App Preference Favourite Content text and button");
        settingsPage.verifyAppPreferenceMostlyDo(browser);
        report.info("Verify App Preference Mostly Do text and button");
        browser.getWait().safeWait(9000);
        
    }
}