package com.dr.assignment.repository;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.dr.assignment.model.TripBooking;

@Component
public class TripRepositoryImpl implements TripRepositoryExt {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public TripBooking countByMedallionAndPickUpDate(String medallion, Date pickUpDate) {
		var query = entityManager.createNativeQuery(
				"select count(1) from cab_trip_data where medallion = ? and DATE(pickup_datetime) = ?");
		query.setParameter(1, medallion);
		query.setParameter(2, pickUpDate);
		int count = ((Number) query.getSingleResult()).intValue();
		return new TripBooking(null, medallion, pickUpDate, count);
	}

}
