package com.dr.assignment.repository;

import java.util.Date;

import com.dr.assignment.dto.TripBookingDto;

public interface TripRepositoryExt {
	TripBookingDto countByMedallionAndPickUpDate(String medallion, Date pickUpDate);
}
