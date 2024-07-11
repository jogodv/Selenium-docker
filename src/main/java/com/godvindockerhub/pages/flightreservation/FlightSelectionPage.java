package com.godvindockerhub.pages.flightreservation;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.godvindockerhub.pages.AbstractPage;

public class FlightSelectionPage extends AbstractPage{
	
	@FindBy(name="departure-flight")
	private List<WebElement> depatureFlightOptions;
	
	@FindBy(name="arrival-flight")
	private List<WebElement> arrivalFlightOptions;
	
	@FindBy(id="confirm-flights")
	private WebElement confirmBtn;

	public FlightSelectionPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(confirmBtn));
		return confirmBtn.isDisplayed();
	}
	
	public void selectFlights() {
		int random = ThreadLocalRandom.current().nextInt(0,depatureFlightOptions.size());
		depatureFlightOptions.get(random).click();
		arrivalFlightOptions.get(random).click();
	}
	
	public void confirmFlight() {
		confirmBtn.click();
	}

}
