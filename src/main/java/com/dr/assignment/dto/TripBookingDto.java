package com.dr.assignment.dto;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.dr.assignment.model.TripBooking;

public class TripBookingDto extends RepresentationModel<TripBooking> {
	Long id;
	String medallion;
	LocalDateTime pickUpDateTime;
	int numTrips;

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

	public LocalDateTime getPickUpDateTime() {
		return pickUpDateTime;
	}

	public void setPickUpDateTime(LocalDateTime pickUpDateTime) {
		this.pickUpDateTime = pickUpDateTime;
	}

	public int getNumTrips() {
		return numTrips;
	}

	public void setNumTrips(int numTrips) {
		this.numTrips = numTrips;
	}

	public TripBookingDto() {
	}

	public TripBookingDto(Long id, String medallion, LocalDateTime pickUpDateTime, int numTrips) {
		this.id = id;
		this.medallion = medallion;
		this.pickUpDateTime = pickUpDateTime;
		this.numTrips = numTrips;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((medallion == null) ? 0 : medallion.hashCode());
		result = prime * result + numTrips;
		result = prime * result + ((pickUpDateTime == null) ? 0 : pickUpDateTime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		TripBookingDto other = (TripBookingDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (medallion == null) {
			if (other.medallion != null)
				return false;
		} else if (!medallion.equals(other.medallion))
			return false;
		if (numTrips != other.numTrips)
			return false;
		if (pickUpDateTime == null) {
			if (other.pickUpDateTime != null)
				return false;
		} else if (!pickUpDateTime.equals(other.pickUpDateTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("TripBookingDto [id=%s, medallion=%s, pickUpDateTime=%s, numTrips=%s]", id, medallion,
				pickUpDateTime, numTrips);
	}

}