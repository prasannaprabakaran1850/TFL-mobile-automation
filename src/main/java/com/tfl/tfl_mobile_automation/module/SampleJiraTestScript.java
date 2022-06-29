package com.tfl.tfl_mobile_automation.module;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.atmecs.falcon.automation.jiracloud.tm.TestCase;
import com.atmecs.falcon.automation.jiracloud.tm.TestCycleMap;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.atmecs.falcon.automation.verifyresult.Verify;

/**
 * Use below class sample code when using Jira Test Results
 * 
 */
public class SampleJiraTestScript {
	
	private ReportLogService report = new ReportLogServiceImpl(SampleTestScript.class);
	
	Verify verifyJiraTest = new Verify();
	
	public final String moduleName = "sample";
	
	@BeforeTest(alwaysRun = true)
	@Parameters({ "device_udid", "device_type" })
	public void beforeTest(String device_udid, String device_type) {
		String mKey = device_udid + "_" + device_type;
		verifyJiraTest.setTestCycleKey(TestCycleMap.testCycleMap.get(mKey + moduleName));
		verifyJiraTest.setDeviceId(device_udid);
	}

	
	@Test
	@Parameters({ "device_udid", "device_type" })
	@TestCase(key = "TP-T1", ModuleName = moduleName)
	public void sampleJiraTest() {
		report.info("Jira test method");
		
		verifyJiraTest.setTestCaseKey("TP-T1");
		
		verifyJiraTest.verifyTrue(true, "step 1 ", "1", true);
		verifyJiraTest.verifyTrue(true, "step 2 ", "2", true);
		verifyJiraTest.verifyTrue(true, "step 3 ", "3", true);
	}
	
}
