package com.page.objectsClass;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPageFlights {
	
	WebDriver driver;
	
	String serachBoxDOM = "return document.querySelector(\"input[aria-controls='react-autowhatever-1']\").getAttribute(\"value\");";
	
	@FindBy(id="fromCity") WebElement from;
	
	@FindBy(xpath="//input[@aria-controls='react-autowhatever-1']") WebElement searchBox;
	
	@FindBy(id="toCity") WebElement to;
	
	@FindBy(xpath="//label[@for='departure']") WebElement departure;
	
	@FindBy(xpath="//*[@aria-label='Next Month']") WebElement nextButton;
	
	@FindBy(xpath="(//*[@class='DayPicker-Caption'])[1]") WebElement depatureMonth;
	
	@FindBy(xpath="(//*[@class='DayPicker-Caption'])[2]") WebElement returnMonth;

	
	@FindBy(xpath = "(//div[@class='DayPicker-Month'])[1]/div/div/div/div") List<WebElement> depatureDays;
	
	@FindBy(xpath = "(//div[@class='DayPicker-Month'])[2]/div/div/div/div") List<WebElement> returnDays;

	
	@FindBy(xpath="//*[@data-cy='travellerText']") WebElement travellersClass;
	
	@FindBy(xpath = "//p[@data-cy='adultRange']/following-sibling::ul[1]/li") List<WebElement> noOfAldults;
	
	@FindBy(xpath = "//p[@data-cy='childrenRange']/following-sibling::ul/li") List<WebElement> noOfChildren;

	@FindBy(xpath = "//p[@data-cy='infantRange']/following-sibling::ul/li") List<WebElement> noOfInfants;
		
	@FindBy(xpath = "//p[@data-cy='chooseTravelClass']/following-sibling::ul") List<WebElement> travelClassList;

	@FindBy(xpath="//button[@data-cy='travellerApplyBtn']") WebElement apply;
	
	
	@FindBy(css="a[class*='widgetSearchBtn ']") WebElement search;
	
	@FindBy(css ="li[data-cy='oneWayTrip']") WebElement oneWay;


	@FindBy(css ="li[data-cy='roundTrip']") WebElement roundTrip;
	
	@FindBy(css ="li[data-cy='mulitiCityTrip']") WebElement multiCity;
	
	//div[@class='makeFlex hrtlCenter'] //div[contains(@class,'pushRight')]
	
	@FindBy(xpath ="//div[@class='makeFlex hrtlCenter'] //div[contains(@class,'pushRight')]") List<WebElement> suggestedToCity;

	@FindBy(xpath="//ul[@class='react-autosuggest__suggestions-list']") WebElement suggestionList;
	
	@FindBy(css = "[class='hsw_sectionTitle font12 latoBlack greyText']") WebElement popularCity;
	
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
