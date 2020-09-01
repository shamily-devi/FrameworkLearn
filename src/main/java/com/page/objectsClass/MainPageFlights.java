package com.page.objectsClass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPageFlights {
	
	WebDriver driver;
	
	private static String serachBoxDOM = "return document.querySelector(\"input[aria-controls='react-autowhatever-1']\").getAttribute(\"value\");";
	
	@FindBy(id="fromCity") 
	private static WebElement from;
	
	@FindBy(xpath="//input[@aria-controls='react-autowhatever-1']") 
	private static WebElement searchBox;
	
	@FindBy(id="toCity") WebElement to;
	
	@FindBy(xpath="//label[@for='departure']") 
	private static WebElement departure;
	
	@FindBy(xpath="//*[@aria-label='Next Month']") 
	private static WebElement nextButton;
	
	@FindBy(xpath="(//*[@class='DayPicker-Caption'])[1]") 
	private static WebElement depatureMonth;
	
	@FindBy(xpath="(//*[@class='DayPicker-Caption'])[2]") 
	private static WebElement returnMonth;

	
	@FindBy(xpath = "(//div[@class='DayPicker-Month'])[1]/div/div/div/div") 
	private static List<WebElement> depatureDays;
	
	@FindBy(xpath = "(//div[@class='DayPicker-Month'])[2]/div/div/div/div") 
	private static List<WebElement> returnDays;

	
	@FindBy(xpath="//*[@data-cy='travellerText']") 
	private static WebElement travellersClass;
	
	@FindBy(xpath = "//p[@data-cy='adultRange']/following-sibling::ul[1]/li") 
	private static List<WebElement> noOfAldults;
	
	@FindBy(xpath = "//p[@data-cy='childrenRange']/following-sibling::ul/li") 
	private static List<WebElement> noOfChildren;

	@FindBy(xpath = "//p[@data-cy='infantRange']/following-sibling::ul/li") 
	private static List<WebElement> noOfInfants;
		
	@FindBy(xpath = "//p[@data-cy='chooseTravelClass']/following-sibling::ul") 
	private static List<WebElement> travelClassList;

	@FindBy(xpath="//button[@data-cy='travellerApplyBtn']") 
	private static WebElement apply;
	
	
	@FindBy(css="a[class*='widgetSearchBtn ']") 
	private static WebElement search;
	
	@FindBy(css ="li[data-cy='oneWayTrip']") 
	private static WebElement oneWay;


	@FindBy(css ="li[data-cy='roundTrip']") 
	private static WebElement roundTrip;
	
	@FindBy(css ="li[data-cy='mulitiCityTrip']") 
	private static WebElement multiCity;
	
	//div[@class='makeFlex hrtlCenter'] //div[contains(@class,'pushRight')]
	
	@FindBy(xpath ="//div[@class='makeFlex hrtlCenter'] //div[contains(@class,'pushRight')]") 
	private static List<WebElement> suggestedToCity;

	@FindBy(xpath="//ul[@class='react-autosuggest__suggestions-list']") 
	private static WebElement suggestionList;
	
	@FindBy(css = "[class='hsw_sectionTitle font12 latoBlack greyText']") 
	private static WebElement popularCity;
	
	@FindBy(css="span[data-cy='sameCityError']")
	private static WebElement errMsgSameCity;
	
	public MainPageFlights(WebDriver driver) {		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getSuggestedToCity() {
		return suggestedToCity;
	}

	public WebElement getPopularCity() {
		return popularCity;
	}
	public WebElement getErrMsgSameCity() {
		return errMsgSameCity;
	}

	public WebElement getSuggestionList() {
		return suggestionList;
	}

	public WebElement getFrom() {
		return from;
	}

	public String getSearchBoxDOM() {
		return serachBoxDOM;
	}

	public WebElement getSearchBox() {
		return searchBox;
	}


	public WebElement getTo() {
		return to;
	}


	public WebElement getDeparture() {
		return departure;
	}


	public WebElement getNextButton() {
		return nextButton;
	}


	public WebElement getDepatureMonth() {
		return depatureMonth;
	}
	
	public WebElement getReturnMonth() {
		return returnMonth;
	}


	public List<WebElement> getDepatureDays() {
		return depatureDays;
	}
	
	public List<WebElement> getReturnDays() {
		return returnDays;
	}


	public WebElement getTravellersClass() {
		return travellersClass;
	}


	public List<WebElement> getNoOfAldults() {
		return noOfAldults;
	}


	public List<WebElement> getNoOfChildren() {
		return noOfChildren;
	}


	public List<WebElement> getNoOfInfants() {
		return noOfInfants;
	}


	public List<WebElement> getTravelClassList() {
		return travelClassList;
	}


	public WebElement getApply() {
		return apply;
	}


	public WebElement getSearch() {
		return search;
	}


	public WebElement getOneWay() {
		return oneWay;
	}


	public WebElement getRoundTrip() {
		return roundTrip;
	}


	public WebElement getMultiCity() {
		return multiCity;
	}



}
