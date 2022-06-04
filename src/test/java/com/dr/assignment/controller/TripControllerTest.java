package com.dr.assignment.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.dr.assignment.model.TripBooking;
import com.dr.assignment.model.TripSearchRequest;
import com.dr.assignment.model.TripSearchResponse;
import com.dr.assignment.service.TripSearchService;

public class TripControllerTest {

	private TripSearchService service;

	private TripsController controller;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

	@BeforeEach
	public void setUp() {
		service = mock(TripSearchService.class);
		controller = new TripsController(service);
	}

	@Test
	public void search_success() throws Exception {
		TripSearchRequest request = new TripSearchRequest();

		String dateInString = "2013-12-06";
		Date date = formatter.parse(dateInString);
		request.setTripBookings(new ArrayList<>());
		TripBooking bookingRequest = new TripBooking(null, "abcd", date, 0);
		request.getTripBookings().add(bookingRequest);

		TripBooking bookingResponse = new TripBooking(105L, "abcd", date, 3);
		List<TripBooking> bookings = new ArrayList<>();
		bookings.add(bookingResponse);

		when(service.search(request.getTripBookings(), false)).thenReturn(bookings);

		ResponseEntity<TripSearchResponse> response = controller.search(request, false);

		assertNotNull(response);
		assertThat(response.getStatusCode().equals(HttpStatus.OK));
		assertThat(response.getBody() instanceof TripSearchResponse);
		assertEquals(1, response.getBody().getTrips().size());
		long numTrips = response.getBody().getTrips().get(0).getNumTrips();
		assertEquals(3, numTrips);
	}
}
