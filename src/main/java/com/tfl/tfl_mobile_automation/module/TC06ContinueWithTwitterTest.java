package com.tfl.tfl_mobile_automation.module;

import org.testng.annotations.Test;

import com.atmecs.falcon.automation.appium.manager.UserBaseTest;
import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.tfl.tfl_mobile_automation.pages.ContinueWithTwitterPage;
import com.tfl.tfl_mobile_automation.pages.HomePage;
import com.tfl.tfl_mobile_automation.pages.WelcomePage;

/*
To verify that Sign in through email
 */
public class TC06ContinueWithTwitterTest extends UserBaseTest{
    private ReportLogService report = new ReportLogServiceImpl(TC06ContinueWithTwitterTest.class);

    @Test   
    public void twitterSignInVerification() throws InterruptedException {
        Browser browser = new Browser();
        browser.resetDriver(driver);
        report.info("Launch TFL application"); 
        report.info("Twitter Sign in verification starts..."); 
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.clickSignInButton(browser);
        report.info("Click on Sign In/Sign Up button in the splash screen."); 
        ContinueWithTwitterPage continueWithTwitterPage = new ContinueWithTwitterPage();
        continueWithTwitterPage.clickContinueWithTwitter(browser);
        report.info("Click on the Continue with Twitter button in the Sign In page"); 
        continueWithTwitterPage.enterTwitterUsername(browser,"Demouser321");
        report.info("Enter valid user name "); 
        continueWithTwitterPage.enterTwitterPassword(browser,"Prasanna@123");
        report.info("Enter valid password"); 
        continueWithTwitterPage.clickAuthorizeButton(browser);
        report.info("Click on Authorise App button"); 
        HomePage homePage =new HomePage();
        homePage.verifyHomepage(browser);
        report.info("Verify the page should redirect to the landing page"); 
      
    }
        

}
