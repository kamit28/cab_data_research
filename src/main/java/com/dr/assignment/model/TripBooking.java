package com.dr.assignment.model;

import java.time.LocalDate;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@JsonRootName(value = "TripBooking")
public final class TripBooking extends RepresentationModel<TripBooking> {

	@JsonIgnore
	private Long id;

	@NotEmpty(message = "medallion can not be null or empty")
	private String medallion;

	@NotNull(message = "date can not be null")
	private LocalDate pickUpDate;

	private int numTrips;

	public TripBooking() {
	}

	public TripBooking(Long id, @NotEmpty(message = "medallion can not be null or empty") String medallion,
			@NotNull(message = "date can not be null") LocalDate pickUpDate, int numTrips) {
		this.id = id;
		this.medallion = medallion;
		this.pickUpDate = pickUpDate;
		this.numTrips = numTrips;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMedallion() {
		return medallion;
	}

	public void setMedallion(String medallion) {
		this.medallion = medallion;
	}

	public LocalDate getPickUpDate() {
		return pickUpDate;
	}

	public void setPickUpDate(LocalDate pickUpDate) {
		this.pickUpDate = pickUpDate;
	}

	public int getNumTrips() {
		return numTrips;
	}

	public void setNumTrips(int numTrips) {
		this.numTrips = numTrips;
	}
}
