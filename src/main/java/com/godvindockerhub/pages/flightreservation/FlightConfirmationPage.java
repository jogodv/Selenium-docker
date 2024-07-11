package com.godvindockerhub.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.godvindockerhub.pages.AbstractPage;

public class FlightConfirmationPage extends AbstractPage{
	
	private static final Logger log =LoggerFactory.getLogger(FlightConfirmationPage.class);
	
	@FindBy(css="#flights-confirmation-section .card-body .row:first-child .col:last-child")
	private WebElement flightConfirmationElement;
	
	@FindBy(css="#flights-confirmation-section .card-body .row:last-child .col:last-child")
	private WebElement totalPriceElement;

	public FlightConfirmationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(flightConfirmationElement));
		return flightConfirmationElement.isDisplayed();
	}
	
	public String getPrice() {
		
		String confirmation= flightConfirmationElement.getText();
		String price=totalPriceElement.getText();
		
		log.info("Flight Confirmation# : {}", confirmation);
		log.info("Total Price# : {}", price);
		return totalPriceElement.getText();
		
		
		
	}

}
