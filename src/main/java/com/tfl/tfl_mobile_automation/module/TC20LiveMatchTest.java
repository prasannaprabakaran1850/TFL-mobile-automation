package com.tfl.tfl_mobile_automation.module;

import org.testng.annotations.Test;

import com.atmecs.falcon.automation.appium.manager.UserBaseTest;
import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.tfl.tfl_mobile_automation.pages.GuestUserPage;
import com.tfl.tfl_mobile_automation.pages.HomePage;
import com.tfl.tfl_mobile_automation.pages.WelcomePage;

/*
To verify, watch the live match
 */
public class TC20LiveMatchTest  extends UserBaseTest{
    private ReportLogService report = new ReportLogServiceImpl(TC20LiveMatchTest.class);

    @Test
    public void liveMatchVerification() throws InterruptedException {
        Browser browser = new Browser();
        browser.resetDriver(driver);
        
        report.info("Live match verification starts..."); 
        WelcomePage welcomePage =new WelcomePage(); 
        GuestUserPage guestUserPage = new GuestUserPage();
        welcomePage.clickAndVerifyGuestButton(browser);
        report.info("Click Guest button");
        guestUserPage.clickNext(browser);
        report.info("Click on Next");
        guestUserPage.clickSkippAll(browser);
        report.info("Click on Skipp All");
        HomePage homePage=new HomePage();
        //homePage.verifyLiveMatchBanner(browser);
        homePage.verifyLiveMatchTeamLogo(browser);
        report.info("Verify Live Match Team Logo exist");
        homePage.verifyLiveMatchTeam(browser);
        report.info("Verify Live Match Team exist");
        homePage.clickLiveMatch(browser);
        report.info("Click on Live Match");
        homePage.verifyWtachLiveButton(browser);
        report.info("Verify WtachLive button is displayed");
        browser.getWait().safeWait(9000);
    }
}