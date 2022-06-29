package com.tfl.tfl_mobile_automation.module;

import org.testng.annotations.Test;

import com.atmecs.falcon.automation.appium.manager.UserBaseTest;
import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.tfl.tfl_mobile_automation.pages.ContinueWithFacebookPage;
import com.tfl.tfl_mobile_automation.pages.HomePage;

/*
To verify that Sign in through Continue with Facebook
 */
import com.tfl.tfl_mobile_automation.pages.WelcomePage;

public class TC12ContinueWithFacebookTest extends UserBaseTest{
    private ReportLogService report = new ReportLogServiceImpl(TC06ContinueWithTwitterTest.class);

    @Test
    public void facebookSignInVerification() throws InterruptedException {
        Browser browser = new Browser();
        browser.resetDriver(driver);
        report.info("Facebook Sign in verification starts..."); 
        report.info("Launch TFL application");
        WelcomePage welcomePage =new WelcomePage();
        welcomePage.clickSignInButton(browser);
        report.info("Click on Sign In/Sign Up button in the splach screen.");
        ContinueWithFacebookPage continueWithFacebookPage = new ContinueWithFacebookPage();
        continueWithFacebookPage.clickContinueWithFacebook(browser);
        report.info("Click on Continue with Facebook in the Sign In page");
        continueWithFacebookPage.enterFacebookUsername(browser,"9080239182");
        report.info("Enter valid user name ");
        continueWithFacebookPage.enterFacebookPassword(browser, "Demouser@123");
        report.info("Enter valid password");
        continueWithFacebookPage.clickLoginButton(browser);
        report.info("Click on login button");
        HomePage homePage =new HomePage();
        homePage.verifyHomepage(browser);
        report.info("Verify the page should redirect to the landing page"); 

    }
        

}