package com.tfl.tfl_mobile_automation.module;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import com.atmecs.falcon.automation.appium.manager.UserBaseTest;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.atmecs.falcon.automation.verifyresult.Verify;

public class SampleTestScript extends UserBaseTest {
	
	private ReportLogService report = new ReportLogServiceImpl(SampleTestScript.class);
	
	@Test
	public void testSearch() throws InterruptedException {
		
		  driver.get("https://www.google.com/");
		
		report.info("entering text: "+"Selenium");
		driver.findElement(By.name("q")).sendKeys("Selenium");
		report.info("pressing enter key");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		report.info("waiting for 2 second");
		driver.wait(2000);
		String text = driver.getTitle();
		report.info("verifying page title");
		Verify.verifyString(text, "Selenium - Google Search", "Verifying String Message ");
	}
}
