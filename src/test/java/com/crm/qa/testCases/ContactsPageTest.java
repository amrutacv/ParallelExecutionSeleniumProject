package com.crm.qa.testCases;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import com.crm.qa.base.BaseTest;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LandingPage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends BaseTest{
	WebDriver driver;
	LandingPage landingPage;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	String sheetName = "Contacts";
	
	public ContactsPageTest() {
		super();
	}
	@BeforeMethod()
	@Parameters({"browser"})
	public void launch(String browser) throws Exception {
		/*
		 * WebDriverInstance.startDriver(browser); driver =
		 * WebDriverInstance.getDriverInstance();
		 */
		driver = TestUtil.getDriver(browser);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.pageLoadTimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.implicitlyWait, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		landingPage = new LandingPage(driver);
		Assert.assertTrue(landingPage.verifyLandingPageDisplayed(),"CRM application not launched");
		loginPage = landingPage.clickLogin();
		Assert.assertTrue(loginPage.verifyLoginPage(),"Login page not displayed");
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(homePage.verifyHomePage(),"User not logged in");
	}
	
	@DataProvider(name = "data")
	public Object[][] getCRMTestData() throws InvalidFormatException{
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	@Test(dataProvider = "data")
	 public void AddContactsTest(Map<Object,Object> map) throws InterruptedException {
		contactsPage = homePage.clickAddContact();
		Assert.assertTrue(contactsPage.verifyCreateNewContact(),"Create New Contact page not displayed");
		contactsPage.createNewContact(map.get("FirstName").toString(), map.get("LastName").toString());
		Assert.assertTrue(contactsPage.verifyContactAdded(map.get("FirstName").toString(), map.get("LastName").toString()),"Contact Not Added");
	 }
	 
	 @AfterMethod()
	 public void tearDown() {
		 //WebDriverInstance.stopDriver();
		 driver.quit();
	 }
}
