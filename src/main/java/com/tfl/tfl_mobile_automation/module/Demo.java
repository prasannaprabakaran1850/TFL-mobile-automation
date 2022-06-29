package com.tfl.tfl_mobile_automation.module;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Pixel 2");
        capabilities.setCapability("platformVersion", "9.0");
        
	
	}

}
