package com.dr.assignment.model;

import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import com.dr.assignment.dto.TripBookingDto;

public class TripSearchResponse extends RepresentationModel<TripSearchResponse> {
	List<TripBookingDto> trips;

	public TripSearchResponse() {
	}

	public TripSearchResponse(Iterable<Link> initialLinks) {
	}

	public TripSearchResponse(Link initialLink) {
		super(initialLink);
		// TODO Auto-generated constructor stub
	}

	public TripSearchResponse(List<TripBookingDto> trips) {
		this.trips = trips;
	}

	public final List<TripBookingDto> getTrips() {
		return trips;
	}

	public final void setTrips(List<TripBookingDto> trips) {
		this.trips = trips;
	}

	@Override
	public String toString() {
		return String.format("TripSearchResponse [trips=%s]", trips);
	}
}
