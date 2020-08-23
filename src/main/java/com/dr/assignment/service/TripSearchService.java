package com.dr.assignment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.dr.assignment.entity.CabTripData;
import com.dr.assignment.exception.ResourceNotFoundException;
import com.dr.assignment.model.CabTrip;
import com.dr.assignment.model.TripBooking;
import com.dr.assignment.repository.TripRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TripSearchService {

	private TripRepository repository;
	private final RedisTemplate<String, TripBooking> bookingCache;

	@Autowired
	public TripSearchService(TripRepository repository, RedisTemplate<String, TripBooking> bookingCache) {
		this.repository = repository;
		this.bookingCache = bookingCache;
	}

	public List<TripBooking> search(List<TripBooking> bookings, boolean useCache) {

		var trips = new ArrayList<TripBooking>(bookings.size());

		for (TripBooking booking : bookings) {
			final var key = "search_" + booking.getMedallion() + "_" + booking.getPickUpDate().getTime();
			final var operations = bookingCache.opsForValue();
			final boolean hasKey = bookingCache.hasKey(key);
			TripBooking resultBooking;

			if (useCache && hasKey) {
				resultBooking = operations.get(key);
				log.debug("search() : cache booking >> " + resultBooking.toString());
			} else {
				var result = Optional
						.of(repository.countByMedallionAndPickUpDate(booking.getMedallion(), booking.getPickUpDate()));
				if (result.isPresent()) {
					resultBooking = result.get();
					log.debug("search() : cache insert >> " + resultBooking.toString());
					operations.set(key, resultBooking);
				} else {
					throw new ResourceNotFoundException("Trip not found for medallion: " + booking.getMedallion());
				}
			}
			trips.add(resultBooking);
		}

		return trips;
	}

	public List<TripBooking> tripDetails(String medallion, Date tripDate, boolean useCache) {

		log.debug("Request received for trips of medallion: {} on date: {} and usecache: {}", medallion, tripDate, useCache);
		Optional<List<CabTripData>> trips = Optional
				.of(repository.findByMedallionAndPickupDateTime(medallion, tripDate));
		var bookings = new ArrayList<TripBooking>();

		if (trips.isPresent()) {
			trips.get().forEach(trip -> {
				TripBooking booking = new TripBooking(trip.getId(), trip.getMedallion(), trip.getPickupDateTime(), 0);
				bookings.add(booking);
			});
		}

		return bookings;
	}

	public CabTrip getTripDetails(Long id, boolean useCache) {
		
		log.debug("Request received for trip: {} and usecache: {}",id, useCache);
		
		var cabTripData = repository.findById(id);
		
		if (cabTripData.isPresent()) {
			CabTrip cabTrip = new CabTrip();
			BeanUtils.copyProperties(cabTripData.get(), cabTrip);
			return cabTrip;
		} else {
			throw new ResourceNotFoundException("No Trip information found for id: " + id);
		}
	}
}
