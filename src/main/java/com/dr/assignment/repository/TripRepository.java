package com.dr.assignment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dr.assignment.entity.CabTripData;

public interface TripRepository extends JpaRepository<CabTripData, Long>, TripRepositoryExt {

	@Query("FROM CabTripData d WHERE d.medallion = :medallion AND DATE(d.pickupDateTime) = :pickupDateTime")
	public List<CabTripData> findByMedallionAndPickupDateTime(String medallion, LocalDate pickupDateTime);

}
