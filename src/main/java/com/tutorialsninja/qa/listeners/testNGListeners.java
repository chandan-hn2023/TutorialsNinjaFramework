package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.extentReporter;

public class testNGListeners implements ITestListener {

	ExtentReports extentReports;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		// ExtentReports ExtentReports = extentReporter.generateExtentReport(); - Make it global
		extentReports = extentReporter.generateExtentReport();
	}

//Before a particular test method gets started, the below method will be invoked and it gets the name of the method
	@Override
	public void onTestStart(ITestResult result) {
		// ExtentTest extentTest = extentReports.createTest(testName); - Make it global
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + "-----Started executing-----");
	}

//Once the test is passed, this method will be invoked
	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS, result.getName() + "-----Successfully executed-----");
	}

//Once the test is failed, this method will be invoked
	@Override
	public void onTestFailure(ITestResult result) {

		// Screenshot
		WebDriver driver = null;
		// To get the driver of the Class where we have the issue
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Copying the screenshot from source location to our desired folder in the
		// project
		// Create a folder manually in Eclipse (project level)
		String destinationScreenShotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + result.getName() + ".png";

		// To copy screenshot from source location to our desired folder in the project
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenShotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Attaching screenshot into Extent report
		extentTest.addScreenCaptureFromPath(destinationScreenShotPath);

		// This will print exception details
		extentTest.log(Status.INFO, result.getThrowable());

		// To print failed Test name in the Report and Console
		extentTest.log(Status.FAIL, result.getName() + "-----Failed-----");
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		// This will print exception details
		extentTest.log(Status.INFO, result.getThrowable()); 
		extentTest.log(Status.SKIP, result.getName() + "-----Skipped-----");
	}

	@Override
	public void onFinish(ITestContext context) {

		// .flush is mandatory, otherwise all the above info won't be added to the report
		extentReports.flush();
		
		String extentReportPath = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";//2
		File extentReport = new File(extentReportPath);//3
		try {
			Desktop.getDesktop().browse(extentReport.toURI()); //1
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
