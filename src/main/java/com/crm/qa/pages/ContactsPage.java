package com.crm.qa.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactsPage{
	@FindBy(name="first_name")
	WebElement firstName;
	
	@FindBy(name="last_name")
	WebElement lastName;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	WebElement save;
	
	@FindBy(xpath="//a[contains(@href,'contacts')]")
	WebElement contactsIcon;
		
	@FindBy(xpath="//table[@class='ui celled sortable striped table custom-grid']")
	WebElement contactTable;
	
	@FindBy(xpath="//i[@class='edit icon']/parent::button[@class='ui icon button']")
	WebElement editIcon;
	
	@FindBy(xpath="//button[contains(text(),'Show Filters')]")
	WebElement showFilters;
	
	WebDriver driver;
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyCreateNewContact() {
		return firstName.isDisplayed();
	}
	public void createNewContact(String firstNameVal,String lastNameVal) {
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(save));
		firstName.sendKeys(firstNameVal);
		lastName.sendKeys(lastNameVal);
		save.click();
		new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(editIcon));
	}
	
	public boolean verifyContactAdded(String firstNameVal,String lastNameVal) {
		
		contactsIcon.click();
		new WebDriverWait(driver,10).until(ExpectedConditions.visibilityOf(contactTable));
		boolean bln = false;
		List<WebElement> name = driver.findElements(By.xpath("//table/tbody/tr/td[2]/a"));
		for(WebElement ele : name) {
			if(ele.getText().equalsIgnoreCase(firstNameVal + " " + lastNameVal)) {
				bln = true;
				break;
			}
			
		}
		return bln;
	}
}
