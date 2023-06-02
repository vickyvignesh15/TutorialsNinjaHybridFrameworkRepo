package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utils.Utilities;

public class SearchPage extends Utilities {

	@FindBy(linkText = "HP LP3065")
	WebElement validHPProduct;
	
	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]")
	WebElement invalidProductMessage;
	
	public SearchPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean displayStatusOfValidHPProduct() {
		return validHPProduct.isDisplayed();
	}
	
	public String retriveNoProductSearchMessageText() {
		return invalidProductMessage.getText();
	}
}
