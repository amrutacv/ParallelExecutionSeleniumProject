package com.crm.qa.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver;
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
		driver.manage().window().maximize();
		driver.get("https://www.freecrm.com/");
		driver.findElement(By.xpath("//span[text()='Log In']/parent::a")).click();
	}

}
