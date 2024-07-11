package com.godvindockerhub.tests.flightreservations;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.godvindockerhub.pages.flightreservation.FlightConfirmationPage;
import com.godvindockerhub.pages.flightreservation.FlightSearchPage;
import com.godvindockerhub.pages.flightreservation.FlightSelectionPage;
import com.godvindockerhub.pages.flightreservation.RegistrationConfirmationPage;
import com.godvindockerhub.pages.flightreservation.RegistrationPage;
import com.godvindockerhub.tests.BaseTest;
import com.godvindockerhub.tests.flightreservations.modal.FlightReservationsTestData;
import com.godvindockerhub.util.Config;
import com.godvindockerhub.util.Constants;
import com.godvindockerhub.util.JsonUtil;

public class FlightReservationTest extends BaseTest{
	
	
	private FlightReservationsTestData testData;
	
	@BeforeTest(dependsOnMethods="setDriver")
	@Parameters({"testDataPath"})
	public  void setParameters(String testDataPath) throws IOException {
		
		this.testData=JsonUtil.getTestData(testDataPath, FlightReservationsTestData.class);
		
	}
	@Test
	public void userRegisterationTest() {
		
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
		Assert.assertTrue(registrationPage.isAt());
		registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
		registrationPage.enterUserCredentials(testData.email(), testData.password());
		registrationPage.enterUserAddress(testData.street(),testData.city(), testData.zip());
		registrationPage.register();
	}
	
	@Test(dependsOnMethods="userRegisterationTest")
	public void registrationConfirmationTest() {
		
		RegistrationConfirmationPage registrationConfirmationPage =new RegistrationConfirmationPage(driver);
		Assert.assertTrue(registrationConfirmationPage.isAt());
		Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.firstName());
		registrationConfirmationPage.goToFlightsSearch();
	}
	
	@Test(dependsOnMethods="registrationConfirmationTest")
	public void flightsSearchTest()  {
		FlightSearchPage flightSearchPage =new FlightSearchPage(driver);
		Assert.assertTrue(flightSearchPage.isAt());
		flightSearchPage.slectPassengers(testData.passengersCount());
		
		flightSearchPage.searchForFlight();
	}
	
	@Test(dependsOnMethods="flightsSearchTest")
	public void flightSelectionMethod() {
		FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
		Assert.assertTrue(flightSelectionPage.isAt());
		flightSelectionPage.selectFlights();
		flightSelectionPage.confirmFlight();
	}
	
	@Test(dependsOnMethods="flightSelectionMethod")
	public void flightConfirmationTest() {
		FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
		Assert.assertTrue(flightConfirmationPage.isAt());
		Assert.assertEquals(flightConfirmationPage.getPrice(), testData.expectedPrice());
	}
	
	

}
