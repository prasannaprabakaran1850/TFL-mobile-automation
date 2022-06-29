package com.tfl.tfl_mobile_automation.module;

import org.testng.annotations.Test;

import com.atmecs.falcon.automation.appium.manager.UserBaseTest;
import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.tfl.tfl_mobile_automation.pages.GuestUserPage;
import com.tfl.tfl_mobile_automation.pages.WelcomePage;

/*
To verify user is able to enter discover as a guest 

 */
public class TC07GuestUserTest extends UserBaseTest{
    private ReportLogService report = new ReportLogServiceImpl(TC07GuestUserTest.class);

    @Test
    public void guestUserVerification() throws InterruptedException {
        Browser browser = new Browser();
        browser.resetDriver(driver);
        report.info("Guest User verification starts..."); 
        WelcomePage welcomePage =new WelcomePage();
        GuestUserPage guestUserPage = new GuestUserPage();
        welcomePage.clickAndVerifyGuestButton(browser);
        report.info("Select Discover as a Guest");
        guestUserPage.clickNext(browser);
        report.info("Click the Next button.");
        guestUserPage.verifyElementClickable(browser);
        report.info("verify Elements are clickable");
        guestUserPage.clickNext(browser);
        report.info("Click the Next button.");
        guestUserPage.verifyElementClickable(browser);
        report.info("verify Elements are clickable");
        guestUserPage.clickNext(browser);
        report.info("Click the Next button.");
        guestUserPage.verifyElementClickable(browser);
        report.info("verify Elements are clickable");
        guestUserPage.clickFinish(browser);
        report.info("Click the Finish button.");
        browser.getWait().safeWait(5000);
        
    }
        

}