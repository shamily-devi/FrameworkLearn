package testWithMavenMakeMyTrip;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.page.objectsClass.MainPageFlights;
import com.resources.Base;
import com.resources.ExcelDataConfig;
import com.resources.RowBOs;

public class MainPageFlightsTest extends Base {
	private WebDriver driver;
	MainPageFlights f;
	// private WebDriverWait wait;
	private JavascriptExecutor js;
	private Actions a;
	private static Logger log = LogManager.getLogger(MainPageFlightsTest.class.getName());

	@Test(dataProvider = "getData")
	public void oneWayTrip(String fromCity, String toCity, String deptMon, String deptDate, String adlts, String chld,
			String infants) throws IOException, InterruptedException {
		driver = browserCall();
		log.info("Browser Initilzatied Successfully");
		driver.get(prop.getProperty("url"));
		a = new Actions(driver);
		js = (JavascriptExecutor) driver;
		f = new MainPageFlights(driver);
		// wait = new WebDriverWait(driver, 40);
		a.moveByOffset(1069, 355).click().build().perform();
		// wait.until(ExpectedConditions.elementToBeClickable(f.getTravellersClass()));
		a.moveToElement(f.getTo()).build().perform();
		a.moveToElement(f.getFrom()).build().perform();
		f.getFrom().click();

		// String textBoxValue = (String) js.executeScript(f.getSearchBoxDOM());
		
		log.debug("invoking toOrFromCitySelect method ");

		toOrFromCitySelect(fromCity, f, driver);
		log.info("toOrFromCitySelect method successfully completed for from City");
		log.debug("invoking dateSelection method ");

		toOrFromCitySelect(toCity, f, driver);
		log.info("toOrFromCitySelect method successfully completed for To city");
		log.debug("invoking dateSelection method ");
		
		dateSelection(deptMon,deptDate);
		log.info("dateSelection method successfully completed ");

		log.debug("invoking travellersClass method ");

		travellersClass(adlts,chld,infants);
		log.info("travellersClass method successfully completed ");

		log.debug("invoking getSearch method ");

		f.getSearch().click();
		log.info("getSearch method successfully completed ");

	}

	@DataProvider
	public static String[][] getData() throws Exception {
		String filePath = "C:\\Users\\A Shamily Devi\\git\\repository2\\MakeMyTrip\\inputfiles\\MakeMyTrip.xlsx";
		String sheetName = "Sheet1";
 		List<RowBOs> rowBOs = ExcelDataConfig.readWorkbook(filePath, sheetName);

		int cellCount = ExcelDataConfig.getCellCount();
		String[][] data = new String[rowBOs.size()-1][cellCount];
		for (int i = 1; i <= rowBOs.size()-1; i++) {
			
			RowBOs rowBO = rowBOs.get(i);
			for (int j =0 ; j < rowBO.getCells().size(); j++) {
				Cell cell = rowBO.getCells().get(j);
				CellType cellType = cell.getCellType();
				
				String cellValue = null;
				if (cellType == CellType.BOOLEAN) {
					boolean value = cell.getBooleanCellValue();
					
					if(value==true) {
						cellValue = "T";
					} else {
						cellValue ="F";
					}
				} else if (cellType == CellType.STRING) {
					cellValue = cell.getStringCellValue();
				} else if (cellType == CellType.NUMERIC) {
					if (DateUtil.isCellInternalDateFormatted(cell)) {
						DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
						cellValue = dateFormat.format(cell.getDateCellValue());
					} else {
						String st = Double.toString(cell.getNumericCellValue());
						String[] a = st.split("\\.");
						//System.out.println(a);
						cellValue = a[0];
					}
				}
				data[i-1][j] = cellValue;
			}
		}
		return data;

	}

	public void toOrFromCitySelect(String cityName, final MainPageFlights f, WebDriver driver) {
		log.info("Entered into getSearch method successfully ");


			f.getSearchBox().sendKeys(cityName);

			System.out.println(f.getPopularCity().getText());

			log.info("getSearch method successfully completed --GitHub");

			Wait<WebDriver> fluentwait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(NoSuchElementException.class);

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
			log.debug("invoking getSearch method -test");

			for (int i = 0; i < f.getSuggestedToCity().size(); i++) {
				String toCity = f.getSuggestedToCity().get(i).getText();
				System.out.println(toCity);
				if (toCity.equals(cityName)) {
					while (!f.getSuggestedToCity().get(i).isDisplayed()) {
						js.executeScript("arguments[0].scrollIntoView(true);", f.getSuggestedToCity().get(i));
						f.getSuggestedToCity().get(i).click();
					}

					f.getSuggestedToCity().get(i).click();
					break;
				}
			}	

	}

	public void dateSelection(String reqMonth,String reqdate) {
		while (!f.getDepatureMonth().getText().contains(reqMonth)) {
			f.getNextButton().click();
		}
		log.info("getSearch method successfully completed-For ");

		for (int j = 0; j < f.getDepatureDays().size(); j++) {
			WebElement day = f.getDepatureDays().get(j);
			String[] dateArray = f.getDepatureDays().get(j).getText().split("\n");
			String date = dateArray[0];
			// System.out.println(f.getDepatureDays().get(j).getText());
			if (date.equals(reqdate)) {
				day.click();
				break;
			}
		}

	}

	public void travellersClass(String adlts, String chld,
			String infants) {
		f.getTravellersClass().click();

		for (int i = 0; i < f.getNoOfAldults().size(); i++) {
			System.out.println(f.getNoOfAldults().get(i).getText());
			String ad = f.getNoOfAldults().get(i).getText();
			if (ad.equals(adlts)) {
				f.getNoOfAldults().get(i).click();
				break;
			}
		}

		for (int i = 0; i < f.getNoOfChildren().size(); i++) {
			String ch = f.getNoOfChildren().get(i).getText();
			if (ch.equals(chld)) {
				f.getNoOfChildren().get(i).click();
				break;
			}
		}

		for (int i = 0; i < f.getNoOfInfants().size(); i++) {
			String inf = f.getNoOfInfants().get(i).getText();
			if (inf.equals(infants)) {
				f.getNoOfInfants().get(i).click();
				break;
			}
		}

		f.getApply().click();

	}

	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

}
