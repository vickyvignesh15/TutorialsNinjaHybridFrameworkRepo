package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base {

	RegisterPage rp;
	AccountSuccessPage asp;

	@BeforeMethod
	public void setUp() {
		HomePage hp = new HomePage();
		rp = hp.navigateToRegisterPage();
	}

	@Test(priority = 1)
	public void verifyRegisteringAnAccountWithMandatoryFields() {
		asp = rp.registerWithMandatoryFields(getTestdata("firstName"), getTestdata("lastName"),
				Utilities.genEmailDateTimeStamp(), getTestdata("telephone"), getTestdata("password"));

		Assert.assertEquals(asp.retriveAccountSucessfullyCreatedMessage(),
				getTestdata("accountSuccessfullyCreatedMessage"), "Account Success Page is not displayed..!");

	}

	@Test(priority = 2)
	public void verifyRegisteringAnAccountWithAllTheFields() {

		asp = rp.registerWithAllFields(getTestdata("firstName"), getTestdata("lastName"),
				Utilities.genEmailDateTimeStamp(), getTestdata("telephone"), getTestdata("password"));

		Assert.assertEquals(asp.retriveAccountSucessfullyCreatedMessage(),
				getTestdata("accountSuccessfullyCreatedMessage"), "Account Success Page is not displayed..!");

	}

	@Test(priority = 3)
	public void verifyRegisteringAccountWithExistingEmailAddress() {

		asp = rp.registerWithAllFields(getTestdata("firstName"), getTestdata("lastName"), getPropValue("validEmail"),
				getTestdata("telephone"), getTestdata("password"));

		Assert.assertTrue(
				rp.retriveEmailIsAlreadyRegisterdWarning().contains(getTestdata("emailIsAlreadyRegistredMessage")),
				"Warning message is not displayed");
	}

	@Test(priority = 4)
	public void verifyRegisteringAnAccountWithoutAnyData() {

		rp.clickOnSubmitButton();

		Assert.assertTrue(rp.displayStatusOfWarningMessages(getTestdata("privacyPolicyWarning"),
				getTestdata("firstNameWarning"), getTestdata("lastNameWarning"), getTestdata("emailWarning"),
				getTestdata("telephoneWarning"), getTestdata("passwordWarning")),
				"Waring message(s) are not displayed");

	}

}
