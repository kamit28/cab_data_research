package com.dr.assignment.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.dr.assignment.dto.TripBookingDto;
import com.dr.assignment.entity.CabTripData;
import com.dr.assignment.exception.ResourceNotFoundException;
import com.dr.assignment.model.CabTrip;
import com.dr.assignment.model.TripBooking;
import com.dr.assignment.repository.TripRepository;

@Service
public class TripSearchService {

	private TripRepository repository;
	private final RedisTemplate<String, Object> bookingCache;

	private static final Logger log = LoggerFactory.getLogger(TripSearchService.class);

	@Autowired
	public TripSearchService(TripRepository repository, RedisTemplate<String, Object> bookingCache) {
		this.repository = repository;
		this.bookingCache = bookingCache;
	}

	public List<TripBookingDto> search(List<TripBooking> bookings, boolean useCache) {

		var trips = new ArrayList<TripBookingDto>(bookings.size());

		for (TripBooking booking : bookings) {
			final var key = "search_" + booking.getMedallion() + "_" + booking.getPickUpDate();
			final var operations = bookingCache.opsForValue();
			final boolean hasKey = bookingCache.hasKey(key);
			TripBookingDto resultBooking;

			if (useCache && hasKey) {
				resultBooking = (TripBookingDto) operations.get(key);
				log.debug("search() : cache booking >> " + resultBooking.toString());
			} else {
				var result = Optional.of(repository.countByMedallionAndPickUpDate(booking.getMedallion(),
						Timestamp.valueOf(booking.getPickUpDate().atStartOfDay())));
				if (result.isPresent()) {
					resultBooking = result.get();
					log.debug("search() : cache insert >> " + resultBooking.toString());
					operations.set(key, resultBooking);
				} else {
					throw new ResourceNotFoundException("Trip not found for medallion: " + booking.getMedallion());
				}
			}
			trips.add(new TripBookingDto(resultBooking.getId(), resultBooking.getMedallion(),
					resultBooking.getPickUpDateTime(), resultBooking.getNumTrips()));
		}

		return trips;
	}

	@SuppressWarnings("unchecked")
	public List<TripBookingDto> getTrips(String medallion, LocalDateTime tripDate, boolean useCache) {

		log.debug("Request received for trips of medallion: {} on date: {} and usecache: {}", medallion, tripDate,
				useCache);

		final var key = "trips_" + medallion + "_" + tripDate.toLocalDate();
		final var operations = bookingCache.opsForValue();
		final boolean hasKey = bookingCache.hasKey(key);
		List<TripBookingDto> resultBookings;

		if (useCache && hasKey) {
			resultBookings = (List<TripBookingDto>) operations.get(key);
			log.debug("tripDetails() : cache trips >> " + resultBookings.toString());
		} else {
			List<CabTripData> trips = repository.findByMedallionAndPickupDateTime(medallion,
					tripDate.toLocalDate());
			resultBookings = trips.stream().map(trip -> new TripBookingDto(trip.getId(), trip.getMedallion(),
						trip.getPickupDateTime().toLocalDateTime(), 0)).collect(Collectors.toList());

			log.debug("search() : cache insert >> " + resultBookings.toString());
			operations.set(key, resultBookings);
		}

		return resultBookings;
	}

	public CabTrip getTripDetails(Long id, boolean useCache) {

		log.debug("Request received for trip: {} and usecache: {}", id, useCache);

		final var key = "trip_" + id;
		final var operations = bookingCache.opsForValue();
		final boolean hasKey = bookingCache.hasKey(key);
		Optional<CabTripData> trip;
		if (useCache && hasKey) {
			trip = Optional.ofNullable((CabTripData) operations.get(key));
			log.debug("getTripDetails() : cache trip >> " + trip.get().toString());
		} else {

			trip = repository.findById(id);

			if (trip.isPresent()) {
				log.debug("getTripDetails() : cache insert >> " + trip.get().toString());
				operations.set(key, trip.get());
			} else {
				throw new ResourceNotFoundException("No Trip information found for id: " + id);
			}
		}
		CabTrip cabTrip = new CabTrip();
		BeanUtils.copyProperties(trip.get(), cabTrip);
		return cabTrip;
	}
}
