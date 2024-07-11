package com.godvindockerhub.tests.flightreservations.modal;

public record FlightReservationsTestData(String firstName,
										 String lastName,
										 String email,
										 String password,
										 String street,
										 String city,
										 String zip,
										 String passengersCount,
										 String expectedPrice) {

}
