package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base {

	SearchPage searchPage;
	HomePage hp;

	@BeforeMethod
	public void setUp() {
		hp = new HomePage();
	}

	@Test(priority = 1)
	public void verifySearchingWithValidProduct() {

		searchPage = hp.searchProduct(getTestdata("validProduct"));
		Assert.assertTrue(searchPage.displayStatusOfValidHPProduct());
	}

	@Test(priority = 2)
	public void verifySearchingWithInvalidProduct() throws InterruptedException {

		searchPage = hp.searchProduct(getTestdata("invalidProduct"));
		Assert.assertEquals(searchPage.retriveNoProductSearchMessageText(), getTestdata("invalidProductMessage"),
				"No product in search results message is not displayed");
		
	}

	@Test(priority = 3, dependsOnMethods = { "verifySearchingWithValidProduct", "verifySearchingWithInvalidProduct" })
	public void verifySearchWithoutProvidingProduct() {
		searchPage = hp.searchProduct("");
		Assert.assertEquals(searchPage.retriveNoProductSearchMessageText(), getTestdata("invalidProductMessage"),
				"No product in search results message is not displayed");
	}

}
