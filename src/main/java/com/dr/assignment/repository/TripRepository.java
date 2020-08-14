package com.dr.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dr.assignment.entity.CabTripData;

public interface TripRepository extends JpaRepository<CabTripData, Long>, TripRepositoryExt {

}
