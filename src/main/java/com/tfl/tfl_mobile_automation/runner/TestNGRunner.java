package com.tfl.tfl_mobile_automation.runner;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedSet;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.XmlSuite;

import com.atmecs.falcon.automation.appium.manager.AppiumParallelTest;
import com.atmecs.falcon.automation.dataprovider.TestDataProvider;
import com.atmecs.falcon.automation.jiracloud.tm.JiraCloudManager;
import com.atmecs.falcon.automation.jiracloud.tm.TestCycleListner;
import com.atmecs.falcon.automation.jiracloud.tm.TestCycleMap;
import com.atmecs.falcon.automation.run.mode.RunModeFactory;
import com.atmecs.falcon.automation.slack.SlackReportListener;
import com.atmecs.falcon.automation.testng.engine.AbstractTestNGEngineMobile;
import com.atmecs.falcon.automation.testng.engine.TestNGEngineFactoryMobile;
import com.atmecs.falcon.automation.testng.engine.TestNGEngineTemplateTypeMobile;
import com.atmecs.falcon.automation.util.parser.PropertyParser;
import com.atmecs.falcon.automation.util.reporter.ReportLogService;
import com.atmecs.falcon.automation.util.reporter.ReportLogServiceImpl;
import com.atmecs.falcon.automation.util.reporter.TestReportUploadClient;
import com.atmecs.falcon.automation.utils.general.PropertyReader;
import com.atmecs.smartfix.helper.SmartFixPageHelper;

/**
 * @author 
 */
public class TestNGRunner {

	private static ReportLogService report = new ReportLogServiceImpl(TestNGRunner.class);

	private static AbstractTestNGEngineMobile testNGEngine = new TestNGEngineFactoryMobile()
			.getTestNGEngine(TestNGEngineTemplateTypeMobile.DESIRED_SUITE_FOR_GIVEN_MODULES);
	private static String filename = System.getProperty("user.dir"
			+ "") + File.separator + "src"
			+ File.separator + "main" + File.separator + "resources" + File.separator
			+ "config.properties";
	@SuppressWarnings("rawtypes")
	private static List<Class<? extends ITestNGListener>> listeners = Lists.newArrayList();
	private static TestNG testng = new TestNG();
	private static List<XmlSuite> suitesToRun = null;
	private static Properties props = null;
	private static TestDataProvider dataProvider = TestDataProvider.getInstance();

	private static boolean isAppiumServerRunning = false;

	/**
	 * String Constants
	 * **/
	static final String SUITE_FILES="SUITE_FILES";
	static final String MODULE_NAMES="MODULE_NAMES";

	/**
	 * @author 
	 * @Description: reads the values from properties file i.e. user is provided the testng.xml or provided the packages
	 * If the packages are provided checks the packages are provided in the project  
	 * If the packages are available pass the package names separated by "," to testApp function for further process
	 * 
	 * **/
	public static void main(String[] args) throws Exception {
		AppiumParallelTest.runMode = RunModeFactory.getRunMode(RunModeFactory.getRunModeType());
		
		// Loading properties file
		props = dataProvider.loadProperties(filename);
		
		setSmartFixProperties();
		try 
		{
			suitesToRun =
					testNGEngine.getSuitesToRunFor(readEnvOrConfigProperty("SuiteFileName"),
							readEnvOrConfigProperty("ClientName"),
							readEnvOrConfigProperty("ModuleName"),
							readEnvOrConfigProperty("AndroidDevices"));

			initialize();
			
			testng.setXmlSuites(suitesToRun);
			testng.run();
			
			if(PropertyReader.readEnvOrConfigProperty("upload.result").equalsIgnoreCase("true"))
				uploadTestNGResultsXml();

		} catch (final Exception e) {
			e.printStackTrace();
		}finally {
			if (isAppiumServerRunning) {
				close(); 	//Close the appium servers
			}
		}
	}


	private static void initialize() throws Exception {
		// Custom Listener to testng
		if (PropertyParser.readEnvOrConfigProperty("jira.testresults").equals("true")) {
			listeners.add(TestCycleListner.class);
			initTestCycle();
		} else {
			report.info(
					"Test Results are not uploading to JIRA. If you want to upload please set value of key 'jira.testresults' to 'true' in config.properties");
		}
		listeners.add(AppiumParallelTest.class);
		listeners.add(SlackReportListener.class);
		
		testng.setListenerClasses(listeners);
		
		AppiumParallelTest.runMode.startAppiumServersForDevices(null);
		isAppiumServerRunning = true;

	}

	private static void close() {
		AppiumParallelTest.runMode.stopAppiumServersForDevices(null);
	}


	/**
	 *@author 
	 *@exception Error in Network connection 
	 *@Description: upload the generated testng-results.xml to the report server
	 * **/
	public static void uploadTestNGResultsXml() {
		try {
			String uploadUrl = PropertyReader.readEnvOrConfigProperty("testreport.uploadurl");
			String testNGResultsXmlFilePath =
					System.getProperty("user.dir") + File.separator + "test-output"
							+ File.separator + "testng-results.xml";
			TestReportUploadClient testReportUploadClient = new TestReportUploadClient(uploadUrl);
			report.info("Started Uploading Results to Report Server...");

			String projectName = PropertyReader.readEnvOrConfigProperty("ProjectName");
			String response =
					testReportUploadClient.upload("1000",projectName, "Mobile", "QA", "Regression",
							"Local", "", "Android", "", testNGResultsXmlFilePath);
			report.info("Response : " + response);

		} catch (Exception e) {
			report.info("Unknown error : Cannot Upload the testng-results.xml " + e.getMessage());
		}
	}


	/**
	 * @return returns the module name
	 * e.g. @param = com/tfl/tfl_mobile_automation/tfl_mobile_automation/tfl_mobile_automation/module
	 *  return = module
	 * */
	public static String getModuleNameForPackage(String p)
	{
		String pattern = Pattern.quote(System.getProperty("file.separator"));
		String[] mArr=p.split(pattern);
		String moduleName=mArr[mArr.length - 1];
		return moduleName;
	}
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static String readEnvOrConfigProperty(String key) {
		// first pref for env, next for config file
		String value = System.getProperty(key);
		if (value == null || value.trim().length() == 0) {
			value = props.getProperty(key);
		}
		return value;
	}
	
	/**
	 * Purpose: Method to create a test cycle and update Map with browserCap along
	 * with testCycleKey
	 * 
	 * @throws JSONException
	 */
	private static void initTestCycle() throws JSONException {
		String projectKey = PropertyParser.readEnvOrConfigProperty("jira.projectkey");
		String rootFolderName = PropertyParser.readEnvOrConfigProperty("jira.root.folder");
		String testEnvironment = PropertyParser.readEnvOrConfigProperty("testEnvironment");
		String[] moduleNamesArray = PropertyParser.readEnvOrConfigProperty("ModuleName").split(",");
		Map<String, SortedSet<String>> devicesListMap = testNGEngine.getDevicesMap();
		JiraCloudManager jco = new JiraCloudManager();

		if (jco.projectExistsForKey(projectKey)) {

			int rootFolderId = jco.getRootFolderId(rootFolderName);
			System.out.println("ROOT_FOLDER_ID: " + rootFolderId);

			if (rootFolderId == 0) {
				rootFolderId = jco.createTestCycleFolder(0, rootFolderName, projectKey);
			}

			int runFolderCount = jco.getRunFolderCount(rootFolderId);
			System.out.println("runFolderCount: " + runFolderCount);
			runFolderCount++;
			int runFolderId = jco.createTestCycleFolder(rootFolderId, "Run " + runFolderCount, projectKey);
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
			for (String key : devicesListMap.keySet()) {
				SortedSet<String> devicesSet = devicesListMap.get(key);
				String platform = key.equalsIgnoreCase("AndroidDevices") ? "android" : "ios";
				for (String device_udid : devicesSet) {
					int browserFolderId = jco.createTestCycleFolder(runFolderId,
							platform + "_" + (device_udid.length() > 10 ? device_udid.substring(0,10) : device_udid) , projectKey);
					String browserCap = device_udid+"_"+platform;
					for (String moduleName : moduleNamesArray) {
						String moduleFolderName = moduleName.substring(0, 1).toUpperCase()
								+ moduleName.substring(1).toLowerCase();
						int moduleFolderId = jco.createTestCycleFolder(browserFolderId, moduleFolderName, projectKey);
						String testCycleName = testEnvironment+"-"+platform + "-" + moduleFolderName + "-"
								+ dateFormat.format(new Date());
						report.info("Creating Test Cycle " + testCycleName);
						String response = jco.createTestCycle(projectKey, testCycleName, "", moduleFolderId);
						System.out.println("Create Cycle Response: " + response);
						JSONObject testCycleJsonObject = new JSONObject(response);
						TestCycleMap.testCycleMap.put(browserCap + moduleName, testCycleJsonObject.getString("key"));
						System.out.println("Runner_testCycleMap:" + TestCycleMap.testCycleMap);
					}
				}
			}

		} else {
			report.info("No Project exists on Jira for Project Key: " + projectKey);
		}
	}
	
	/**
	 * 
	 */
	public static void setSmartFixProperties() {
		SmartFixPageHelper.setPlatformName(readEnvOrConfigProperty("DEVICE"));
		SmartFixPageHelper.setIsSmartFixEnabled(readEnvOrConfigProperty("smartfix.enable"));
	}
}
