package com.godvindockerhub.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.godvindockerhub.pages.AbstractPage;

public class FlightSearchPage extends AbstractPage{
	
	@FindBy(id="passengers")
	WebElement passengerSelect;
	
	@FindBy(id="search-flights")
	WebElement searchFlightBtn;

	public FlightSearchPage(WebDriver driver) {
		super(driver);
		
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(searchFlightBtn));
		
		return searchFlightBtn.isDisplayed();
	}
	
	public void slectPassengers(String noOfPassengers) {
		Select s =new Select(passengerSelect);
		s.selectByValue(noOfPassengers);
	}
	
	public void searchForFlight() {
		searchFlightBtn.click();
	}
	
	

}
