package com.crm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(name="email")
	WebElement username;
	
	@FindBy(name="password")
	WebElement pwd;
	
	@FindBy(xpath="//div[contains(text(),'Login')]")
	WebElement login;
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyLoginPage() {
		return username.isDisplayed();
	}
	public HomePage login(String user,String password) {
		username.sendKeys(user);
		pwd.sendKeys(password);
		login.click();
		return new HomePage(driver);
	}
}
