package com.crm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	@FindBy(xpath="//span[text()='Log In']/parent::a")
	WebElement login;
	
	@FindBy(xpath="//a[@title='free crm home']")
	WebElement logo;
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyLandingPageDisplayed() {
		return logo.isEnabled();
	}
	public LoginPage clickLogin() {
		login.click();
		return new LoginPage(driver);
	}
	
}
