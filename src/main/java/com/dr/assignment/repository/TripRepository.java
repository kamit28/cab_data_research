package com.dr.assignment.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dr.assignment.entity.CabTripData;

public interface TripRepository extends JpaRepository<CabTripData, Long>, TripRepositoryExt {

	@Query("FROM CabTripData WHERE medallion = ?1 AND DATE(pickupDateTime) = ?2")
	public List<CabTripData> findByMedallionAndPickupDateTime(String medallion, Date pickupDateTime);

}
