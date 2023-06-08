package com.tutorials.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

public class base {

	WebDriver driver;
	public Properties prop;
	public Properties dataProp;

	public void loadPropertiesFile() throws IOException {
		prop = new Properties();// 1
		File file = new File(System.getProperty("user.dir")
				+ "//src//main//java//com//tutorialsninja//qa//properties//globalProperties.properties");// 4
		FileInputStream fis = new FileInputStream(file);// 3
		prop.load(fis);// 2

		dataProp = new Properties();//1
		File dataFile = new File(System.getProperty("user.dir")
				+ "//src//main//java//com//tutorialsninja//qa//testdata//testData.properties");//4
		FileInputStream dataFis = new FileInputStream(dataFile);//3
		dataProp.load(dataFis);//2
	}

	public WebDriver intializeBrowserAndOpenApplication(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}

		// driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.ImplicitWait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.LoadWait));
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
