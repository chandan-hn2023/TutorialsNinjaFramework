package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.exec.OS;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReporter {

	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReports = new ExtentReports(); //1
		File extentReporterFile = new File((System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html"));//3
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReporterFile); //2
		
		//Setting Configuration 
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorials Ninja Test Automation Report");
		sparkReporter.config().setDocumentTitle("Tutorials Ninja Automation");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		//Attaching Extent reports and Spark reporters object 
		extentReports.attachReporter(sparkReporter);
			
		//Invoking URL name,Browser name,Email and Password from .properties file
		Properties configProp = new Properties();
		File configFile = new File(System.getProperty("user.dir")
				+ "//src//main//java//com//tutorialsninja//qa//properties//globalProperties.properties");// 4
		try {
		FileInputStream fisConfigProp = new FileInputStream(configFile);// 3
		configProp.load(fisConfigProp);// 2
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		extentReports.setSystemInfo("Application URL", configProp.getProperty("url"));
		extentReports.setSystemInfo("Browser name", configProp.getProperty("browser"));
		extentReports.setSystemInfo("Email Address", configProp.getProperty("validEmailID"));
		extentReports.setSystemInfo("Password", configProp.getProperty("validPassword"));
		
		//Getting User,OS and Java version details
		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("User", System.getProperty("user.name")); 
		extentReports.setSystemInfo("Java version", System.getProperty("java.version"));
		
		//Returning the Extent report
		return extentReports;
	}
}
