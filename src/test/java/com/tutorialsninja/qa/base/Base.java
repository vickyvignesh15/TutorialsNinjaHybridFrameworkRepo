package com.tutorialsninja.qa.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.tutorialsninja.qa.utils.Utilities;

public class Base extends Utilities {

	@BeforeMethod
	public void setup() {
		initializeBrowser(getPropValue("browser"));
		launchUrl(getPropValue("url"));

	}

	@AfterMethod
	public void tearDown() {
		close();
	}

}
