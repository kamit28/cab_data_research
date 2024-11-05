package com.dr.assignment.repository;

import java.sql.Timestamp;

import com.dr.assignment.dto.TripBookingDto;

public interface TripRepositoryExt {
	TripBookingDto countByMedallionAndPickUpDate(String medallion, Timestamp pickUpDate);
}
