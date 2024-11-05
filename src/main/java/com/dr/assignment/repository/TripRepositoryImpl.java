package com.dr.assignment.repository;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.dr.assignment.dto.TripBookingDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class TripRepositoryImpl implements TripRepositoryExt {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public TripBookingDto countByMedallionAndPickUpDate(String medallion, Timestamp pickUpDate) {
		var query = entityManager.createNativeQuery(
				"select count(1) from cab_trip_data where medallion = ? and DATE(pickup_datetime) = ?");
		query.setParameter(1, medallion);
		query.setParameter(2, pickUpDate);
		int count = ((Number) query.getSingleResult()).intValue();
		return new TripBookingDto(null, medallion, pickUpDate.toLocalDateTime(), count);
	}

}
