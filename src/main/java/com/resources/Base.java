package com.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class Base {
	public static WebDriver driver;
	public static Properties prop;

	public static WebDriver browserCall() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\resources\\data.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser");
		//String browserName = prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		
		else if(browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\msedgedriver.exe");
			driver = new EdgeDriver();
			driver.manage().window().maximize();

			
		}

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		return driver;
	}
	
	public void getScreenShots(String testCaseName,WebDriver driver) throws IOException {
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String des = System.getProperty("user.dir")+"\\Screenshots\\"+testCaseName+".png";
		FileUtils.copyFile(src,new File (des));
	}

}
