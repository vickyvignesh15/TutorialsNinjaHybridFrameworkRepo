package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base {

	LoginPage lp;

	@BeforeMethod
	public void setUp() {
		HomePage hp = new HomePage();
		hp.clickOnMyAccount();
		lp = hp.selectLoginOption();

	}

	@DataProvider(name = "validCredentials")
	public Object[][] supplyData() {
		Object[][] data = getTestData("Login");
		return data;
	}

	@Test(priority = 1, dataProvider = "validCredentials")
	public void verifyLoginWithValidCredentials(String email, String password) {

		AccountPage ap = lp.login(email, password);

		Assert.assertTrue(ap.getDisplayStatusOfEditYourAccountOption(), "Edit Your Account Option is not displayed");

	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials() {

		lp.login(Utilities.genEmailDateTimeStamp(), getTestdata("invalidPassword"));

		Assert.assertTrue(lp.retriveEmailPasswordNoMatchWarningWarningMessage()
				.contains(getTestdata("emailPasswordNoMatchMessage")));

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword() {

		lp.login(Utilities.genEmailDateTimeStamp(), getPropValue("validPassword"));

		Assert.assertTrue(lp.retriveEmailPasswordNoMatchWarningWarningMessage()
				.contains(getTestdata("emailPasswordNoMatchMessage")));
	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword() {

		lp.login(getPropValue("validEmail"), getTestdata("invalidPassword"));

		Assert.assertTrue(lp.retriveEmailPasswordNoMatchWarningWarningMessage()
				.contains(getTestdata("emailPasswordNoMatchMessage")));
	}

	@Test(priority = 5)
	public void verifyLoginWithoutCredentials() {

		lp.login("", "");

		Assert.assertTrue(lp.retriveEmailPasswordNoMatchWarningWarningMessage()
				.contains(getTestdata("emailPasswordNoMatchMessage")));
	}

}
