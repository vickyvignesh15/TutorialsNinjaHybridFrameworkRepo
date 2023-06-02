package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tutorialsninja.qa.utils.Utilities;

public class HomePage extends Utilities{
	
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenu ;
	
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
	@FindBy(name = "search")
	WebElement searchBox;
	
	@FindBy(xpath = "//div[@id='search']/descendant::button")
	WebElement searchButton;
	
	public HomePage() {
	
		PageFactory.initElements(driver, this);  
	}
	
	//Actions
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
	}
	
	public  LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage();
	}
	
	
	public SearchPage searchProduct(String productName) {
		searchBox.sendKeys(productName);
		searchButton.click();
		return new SearchPage();
	}
	
	public SearchPage clickOnSearchButton() {
		searchButton.click();
		return new SearchPage();
	}
	
	public RegisterPage navigateToRegisterPage() {
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage();
	}
}
