package com.godvindockerhub.pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.godvindockerhub.pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {
	
	
	
	@FindBy(id="go-to-flights-search")
	private WebElement flightsSearchBtn;
	
	@FindBy(css="#registration-confirmation-section p b")
	private WebElement firstNameCheck;
	
	public RegistrationConfirmationPage(WebDriver driver) {
		super(driver);
	}
	
	public void goToFlightsSearch() {
		flightsSearchBtn.click();
	}
	
	public String getFirstName() {
		return firstNameCheck.getText();
	}
	
	@Override
	public boolean isAt() {
		this.wait.until(ExpectedConditions.visibilityOf(flightsSearchBtn));
		return flightsSearchBtn.isDisplayed();
	}
	

}
