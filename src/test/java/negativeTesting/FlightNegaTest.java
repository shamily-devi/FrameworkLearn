package negativeTesting;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.page.objectsClass.MainPageFlights;
import com.resources.Base;

import testWithMavenMakeMyTrip.MainPageFlightsTest;

public class FlightNegaTest extends Base {
	public WebDriver driver;
	public MainPageFlights f;
	public Actions a;
	MainPageFlightsTest pf;
	private static Logger log = LogManager.getLogger(MainPageFlightsTest.class.getName());

	
	@Test
	public void checkForSameFromAndToCity() throws IOException {
		String fromAndToCityArr[] = { "MAA", "MAA" };
		driver = browserCall();
		log.info("Browser Initilzatied Successfully");
		driver.get(prop.getProperty("url"));
		f = new MainPageFlights(driver);
		a=new Actions(driver);
		a.moveByOffset(1069, 355).click().build().perform();
		f.getFrom().click();
		log.debug("invoking toOrFromCitySelect method ");
		pf = new MainPageFlightsTest();

		pf.toOrFromCitySelect(fromAndToCityArr,f,driver);
		
		Assert.assertTrue(f.getErrMsgSameCity().getText().equals("From & To airports cannot be the same"));
		log.info("Porpoer error message is Shown ");

		
	}
	
	
	
	
	
	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

}
