package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utils.Utilities;

public class AccountPage extends Utilities{

	
	@FindBy(linkText = "Edit your account information")
	private WebElement editYourAccountInformationOption;
	
	
	public AccountPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean getDisplayStatusOfEditYourAccountOption() {
		return editYourAccountInformationOption.isDisplayed();
	}
}

