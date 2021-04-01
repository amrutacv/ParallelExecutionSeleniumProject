package com.crm.qa.util;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverInstance {
	private static HashMap<Long, WebDriver> map = new HashMap<>();

    public static WebDriver getDriverInstance() {
        return map.get(Thread.currentThread().getId());
    }
    
    public static void startDriver(String browser) throws Exception {
        WebDriver driver;
        switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("enable-automation");
			driver = new ChromeDriver(options);
			map.put(Thread.currentThread().getId(), driver);
			break;
		case "ie":
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			map.put(Thread.currentThread().getId(), driver);
			break;
		default:
			   driver = null;
			   map.put(Thread.currentThread().getId(), driver);
			   break;
		}
    }
    
    public static void stopDriver() {
        WebDriver driver = map.get(Thread.currentThread().getId());
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }
}
