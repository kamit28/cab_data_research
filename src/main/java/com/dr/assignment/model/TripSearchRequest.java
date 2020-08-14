package com.dr.assignment.model;

import java.util.List;

import javax.validation.Valid;

import lombok.Data;


@Data
public class TripSearchRequest {

	@Valid
	private List<TripBooking> tripBookings;
}
