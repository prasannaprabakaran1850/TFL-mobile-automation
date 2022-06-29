package com.tfl.tfl_mobile_automation.module;

import org.testng.annotations.Test;

import com.atmecs.falcon.automation.appium.manager.UserBaseTest;
import com.atmecs.falcon.automation.ui.selenium.Browser;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.tfl.tfl_mobile_automation.dataprovider.ExcelUtils;
import com.tfl.tfl_mobile_automation.pages.HomePage;
import com.tfl.tfl_mobile_automation.pages.SignInPage;
import com.tfl.tfl_mobile_automation.pages.WelcomePage;

/*To verify that Sign in through email*/
public class TC04SignInTest extends UserBaseTest {
    private ReportLogService report = new ReportLogServiceImpl(TC04SignInTest.class);
    
    // dataProvider = "logindata",dataProviderClass = ExcelUtils.class
    // String username,String password
    @Test(dataProvider = "logindata",dataProviderClass = ExcelUtils.class)
    public void signInVerification(String username,String password) throws InterruptedException {
        Browser browser = new Browser();
        browser.resetDriver(driver);  
        report.info("Launch TFL application"); 
        report.info("Sign in verification starts..."); 
        WelcomePage welcomePage = new WelcomePage();
        welcomePage.clickSignInButton(browser);
        report.info("Click on the \"Sign In/Sign Up\" button. "); 
        SignInPage signInPage = new SignInPage();
        signInPage.enterEmail(browser, username);
        report.info("Enter existing valid email id"); 
        signInPage.clickContinueButton(browser);
        report.info("click on Continue button"); 
        signInPage.verifyUsernameInPasswordPage(browser, username);
        report.info("Verify user name exist in password page"); 
        signInPage.enterPassword(browser, password);
        report.info("Enter Valid Password on Enter your password field"); 
        signInPage.clickSignIn(browser);
        report.info("Click on Sign In button"); 
        HomePage homePage =new HomePage();
        homePage.verifyHomepage(browser);
        report.info("Verify the page should redirect to the landing page"); 
        signInPage.clickProfile(browser);
        report.info("Click on Profile button"); 
        signInPage.clickSignOut(browser);  
        report.info("Click on SignOut button"); 
    }
}