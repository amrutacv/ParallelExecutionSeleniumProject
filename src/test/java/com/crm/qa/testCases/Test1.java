package com.crm.qa.testCases;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

public class Test1 {

	public static void main(String[] args) throws MalformedURLException {
		WebDriver driver;
		String nodeURL;
		
		 nodeURL = "http://localhost:5566/wd/hub";
         System.out.println("Chrome Browser Initiated");
         DesiredCapabilities capabilities = DesiredCapabilities.chrome();            
         capabilities.setBrowserName("chrome");
         capabilities.setPlatform(Platform.WINDOWS);
         capabilities.setVersion("88.0");
         driver = new RemoteWebDriver(new URL(nodeURL),capabilities);
         
         driver.get("https://www.freecrm.com/");
         driver.manage().window().maximize();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.quit();
	}

}
