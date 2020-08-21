package com.dr.assignment.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dr.assignment.model.TripSearchRequest;
import com.dr.assignment.model.TripSearchResponse;
import com.dr.assignment.service.TripSearchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/")
public class TripsController {

	private TripSearchService tripSearchService;

	@Autowired
	public TripsController(TripSearchService tripSearchService) {
		this.tripSearchService = tripSearchService;
	}

	@PostMapping(value = "/search", consumes = "application/json", produces = { "application/json" })
	public @ResponseBody ResponseEntity<TripSearchResponse> search(
			@Valid @RequestBody(required = true) TripSearchRequest tripSearchRequest,
			@RequestParam(required = false) boolean useCache) throws Exception {
		log.info("Request received: " + tripSearchRequest.toString());

		var tripBookings = tripSearchService.search(tripSearchRequest.getTripBookings(), useCache);

		var response = new TripSearchResponse(tripBookings);

		return ResponseEntity.ok(response);
	}
}
