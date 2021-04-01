package com.crm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	@FindBy(xpath="//a[contains(@href,'contacts')]")
	WebElement contactsIcon;
	
	@FindBy(xpath="//a[contains(@href,'contacts')]//following-sibling::button")
	WebElement addContact;
	
	@FindBy(xpath="//div[text()='Contacts activity stream']")
	WebElement contactsActivityStream;
	
	WebDriver driver;
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyHomePage() {
		return contactsActivityStream.isDisplayed();
	}
	
	public ContactsPage clickAddContact() throws InterruptedException {
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(contactsIcon));
		Actions action = new Actions(driver);
		action.moveToElement(contactsIcon).build().perform();
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(addContact));
		addContact.click();
		return new ContactsPage(driver);
	}
}
