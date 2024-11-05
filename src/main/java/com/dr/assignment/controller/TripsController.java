package com.dr.assignment.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dr.assignment.model.CabTrip;
import com.dr.assignment.model.TripSearchRequest;
import com.dr.assignment.model.TripSearchResponse;
import com.dr.assignment.service.TripSearchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/ny_trips")
public class TripsController {

	private TripSearchService tripSearchService;

	private static final Logger log = LoggerFactory.getLogger(TripsController.class);

	@Autowired
	public TripsController(TripSearchService tripSearchService) {
		this.tripSearchService = tripSearchService;
	}

	private static DateTimeFormatter formatter =  
            new DateTimeFormatterBuilder().appendPattern("uuuu-MM-dd[ [HH][:mm][:ss][.SSS]]")
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
            .toFormatter();

	@PostMapping(value = "/search", consumes = "application/json", produces = { "application/json" })
	public @ResponseBody ResponseEntity<TripSearchResponse> search(
			@Valid @RequestBody(required = true) TripSearchRequest tripSearchRequest,
			@RequestParam(required = false) boolean useCache) throws Exception {
		log.info("Request received: " + tripSearchRequest.toString());

		var tripBookings = tripSearchService.search(tripSearchRequest.tripBookings(), useCache);
		tripBookings.forEach(booking -> {
			try {
				booking.add(linkTo(methodOn(TripsController.class).bookingDetails(booking.getMedallion(),
						formatter.format(booking.getPickUpDateTime()), useCache)).withSelfRel());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		});

		var response = new TripSearchResponse(tripBookings);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{medallion}/trips", produces = { "application/json" })
	public @ResponseBody ResponseEntity<TripSearchResponse> bookingDetails(
			@PathVariable(required = true) String medallion, @RequestParam(required = true) String tripDate,
			@RequestParam(required = false) boolean useCache) throws Exception {

		log.info("Request received for trips of medallion: {} on date: {} and usecache: {}", medallion, tripDate,
				useCache);

		var tripBookings = tripSearchService.getTrips(medallion, LocalDateTime.parse(tripDate, formatter), useCache);
		tripBookings.forEach(booking -> {
			try {
				booking.add(linkTo(methodOn(TripsController.class).getTrip(booking.getId(), useCache)).withSelfRel());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		});

		var response = new TripSearchResponse(tripBookings);

		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/trip/{id}", produces = { "application/json" })
	public @ResponseBody ResponseEntity<CabTrip> getTrip(@PathVariable Long id,
			@RequestParam(required = false) boolean useCache) throws Exception {
		log.info("Request received for getTrip for id: " + id);

		var cabTrip = tripSearchService.getTripDetails(id, useCache);

		return ResponseEntity.ok(cabTrip);
	}
}
