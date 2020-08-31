package testWithMavenMakeMyTrip;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.page.objectsClass.MainPageFlights;
import com.resources.Base;

public class MainPageFlightsTest extends Base {
	public WebDriver driver;
	public MainPageFlights f;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	public Actions a;
	private static Logger log = LogManager.getLogger(MainPageFlightsTest.class.getName());

	@Test
	public void oneWayTrip() throws IOException, InterruptedException {
		driver = browserCall();
		log.info("Browser Initilzatied Successfully");
		driver.get(prop.getProperty("url"));
		Actions a = new Actions(driver);
		js = (JavascriptExecutor) driver;
		f = new MainPageFlights(driver);
		wait = new WebDriverWait(driver, 40);
		a.moveByOffset(1069, 355).click().build().perform();
		wait.until(ExpectedConditions.elementToBeClickable(f.getTravellersClass()));
		a.moveToElement(f.getTo()).build().perform();
		a.moveToElement(f.getFrom()).build().perform();
		f.getFrom().click();

		String textBoxValue = (String) js.executeScript(f.getSearchBoxDOM());
		System.out.println(textBoxValue);
		log.debug("invoking toOrFromCitySelect method ");

		toOrFromCitySelect();
		log.info("toOrFromCitySelect method successfully completed ");
		log.debug("invoking dateSelection method ");


		dateSelection();
		log.info("dateSelection method successfully completed ");

		log.debug("invoking travellersClass method ");

		travellersClass();
		log.info("travellersClass method successfully completed ");

		log.debug("invoking getSearch method ");
		
		f.getSearch().click();
		log.info("getSearch method successfully completed ");


	}

	public void toOrFromCitySelect() {
		log.info("Entered into getSearch method successfully ");

		String fromAndToCityArr[] = { "MAA", "DEL" };
		List<String> fromAndToCityList = Arrays.asList(fromAndToCityArr);

		for (String fromAndToCity : fromAndToCityList) {
			f.getSearchBox().sendKeys(fromAndToCity);

			System.out.println(f.getPopularCity().getText());

			int secondsToweight = fromAndToCity.length();
			Wait<WebDriver> fluentwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
					.pollingEvery(Duration.ofSeconds(secondsToweight)).ignoring(NoSuchElementException.class);

			List<WebElement> foo = fluentwait.until(new Function<WebDriver, List<WebElement>>() {
				public List<WebElement> apply(WebDriver driver) {
					if (f.getPopularCity().getText().equals("SUGGESTIONS")) {
						return f.getSuggestedToCity();
					} else {
						return null;
					}
				}
			});
			System.out.println(f.getPopularCity().getText());

			for (int i = 0; i < f.getSuggestedToCity().size(); i++) {
				String toCity = f.getSuggestedToCity().get(i).getText();
				System.out.println(toCity);
				if (toCity.equals(fromAndToCity)) {
					while (!f.getSuggestedToCity().get(i).isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView(true);", f.getSuggestedToCity().get(i));
						f.getSuggestedToCity().get(i).click();
					}

					f.getSuggestedToCity().get(i).click();
					break;
				}
			}

		}

	}

	public void dateSelection() {
		String reqMonth = "September";
		while (!f.getDepatureMonth().getText().contains(reqMonth)) {
			f.getNextButton().click();
		}
		log.info("getSearch method successfully completed-For ");

		for (int j = 0; j < f.getDepatureDays().size(); j++) {
			WebElement day = f.getDepatureDays().get(j);
			String[] dateArray = f.getDepatureDays().get(j).getText().split("\n");
			String date = dateArray[0];
			//System.out.println(f.getDepatureDays().get(j).getText());
			if (date.equalsIgnoreCase("15")) {
				day.click();
				break;
			}
		}

	}
	
	public void travellersClass() {
		f.getTravellersClass().click();
		//Select Aldults
		for(int i=0;i<f.getNoOfAldults().size();i++) {
			System.out.println(f.getNoOfAldults().get(i).getText());
			if(f.getNoOfAldults().get(i).getText().equals("4")) {
				f.getNoOfAldults().get(i).click();
				break;
			}
		}
		
		for(int i=0;i<f.getNoOfAldults().size();i++) {
			System.out.println(f.getNoOfAldults().get(i).getText());
			if(f.getNoOfAldults().get(i).getText().equals("4")) {
				f.getNoOfAldults().get(i).click();
				break;
			}
		}
		
		for(int i=0;i<f.getNoOfChildren().size();i++) {
			if(f.getNoOfChildren().get(i).getText().equals("2")) {
				f.getNoOfChildren().get(i).click();
				break;
			}
		}
		
		
		for(int i=0;i<f.getNoOfInfants().size();i++) {
			if(f.getNoOfInfants().get(i).getText().equals("1")) {
				f.getNoOfInfants().get(i).click();
				break;
			}
		}
		
		f.getApply().click();
		
	}
	
	

}
