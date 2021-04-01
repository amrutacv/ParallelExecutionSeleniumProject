package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest {
//	public static WebDriver driver;
	public static Properties prop;
	public BaseTest() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\AcVyawahare\\eclipse-workspace\\MavenProject3\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * public static void initialize() { WebDriverManager.chromedriver().setup();
	 * ChromeOptions options = new ChromeOptions();
	 * options.addArguments("start-maximized");
	 * options.addArguments("enable-automation"); driver = new
	 * ChromeDriver(options); driver.manage().deleteAllCookies();
	 * driver.manage().timeouts().pageLoadTimeout(TestUtil.pageLoadTimeOut,
	 * TimeUnit.SECONDS);
	 * driver.manage().timeouts().implicitlyWait(TestUtil.implicitlyWait,
	 * TimeUnit.SECONDS); driver.get(prop.getProperty("url"));
	 * 
	 * }
	 */
}
