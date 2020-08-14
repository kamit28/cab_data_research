package com.dr.assignment.repository;

import java.util.Date;

import com.dr.assignment.model.TripBooking;

public interface TripRepositoryExt {
	TripBooking countByMedallionAndPickUpDate(String medallion, Date pickUpDate);
}
