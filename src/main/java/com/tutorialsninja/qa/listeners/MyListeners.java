package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners extends Utilities implements ITestListener {

	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		generateExtendReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " started executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName() + " sucessfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String screenShotPath = "";
		try {
			screenShotPath = takeScreenshot(result.getName());
		} catch (IOException e) {

			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(screenShotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " got failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " got skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();

		String patOfExtentReport = System.getProperty("user.dir") + "\\ExtentReports\\extentReport.html";
		File extentReport = new File(patOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
