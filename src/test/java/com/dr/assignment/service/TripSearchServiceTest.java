package com.dr.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dr.assignment.dto.TripBookingDto;
import com.dr.assignment.model.TripBooking;
import com.dr.assignment.repository.TripRepository;

@ExtendWith(SpringExtension.class)
public class TripSearchServiceTest {

	@Mock
	RedisTemplate<String, Object> bookingCache;

	@Mock
	private ValueOperations<String, Object> valueOperations;

	@Mock
	private TripRepository repository;

	@InjectMocks
	private TripSearchService service;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@BeforeEach
	public void setUp() {
		when(bookingCache.opsForValue()).thenReturn(valueOperations);
	}

	@Test
	public void search_db_access_success() throws ParseException {
		List<TripBooking> bookings = new ArrayList<>();
		String dateInString = "2013-12-06";
		LocalDate date = LocalDate.parse(dateInString, formatter);
		bookings.add(new TripBooking(null, "abcd", date, 0));

		when(repository.countByMedallionAndPickUpDate("abcd",
				Timestamp.valueOf(LocalDate.parse(dateInString).atStartOfDay())))
				.thenReturn(new TripBookingDto(1L, "abcd", date.atStartOfDay(), 3));
		doNothing().when(valueOperations).set(anyString(), any());

		List<TripBookingDto> result = service.search(bookings, false);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.get(0).getId() == 1L);
		assertTrue(result.get(0).getMedallion().equals("abcd"));
		assertTrue(result.get(0).getPickUpDateTime().equals(date.atStartOfDay()));
		assertTrue(result.get(0).getNumTrips() == 3);
	}

	@Test
	public void search_cache_access_success() throws ParseException {
		List<TripBooking> bookings = new ArrayList<>();
		String dateInString = "2013-12-06";
		LocalDate date = LocalDate.parse(dateInString, formatter);

		TripBooking booking = new TripBooking(null, "abcd", date, 0);

		bookings.add(booking);
		var key = "search_" + booking.getMedallion() + "_" + booking.getPickUpDate();

		when(bookingCache.hasKey(key)).thenReturn(true);
		when(bookingCache.opsForValue()).thenReturn(valueOperations);

		when(repository.countByMedallionAndPickUpDate("abcd",
				Timestamp.valueOf(LocalDate.parse(dateInString, formatter).atStartOfDay())))
				.thenReturn(new TripBookingDto(1L, "abcd", date.atStartOfDay(), 3));
		doNothing().when(valueOperations).set(anyString(), any());

		List<TripBookingDto> result = service.search(bookings, false);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.get(0).getId() == 1L);
		assertTrue(result.get(0).getMedallion().equals("abcd"));
		assertTrue(result.get(0).getPickUpDateTime().equals(date.atStartOfDay()));
		assertTrue(result.get(0).getNumTrips() == 3);
	}

	@Test
	public void search_useCach_success() throws ParseException {
		List<TripBooking> bookings = new ArrayList<>();
		String dateInString = "2013-12-06";
		LocalDate date = LocalDate.parse(dateInString, formatter);

		TripBooking booking = new TripBooking(null, "abcd", date, 0);

		bookings.add(booking);
		var key = "search_" + booking.getMedallion() + "_" + booking.getPickUpDate();

		when(bookingCache.hasKey(key)).thenReturn(true);
		when(bookingCache.opsForValue()).thenReturn(valueOperations);
		when(valueOperations.get(key)).thenReturn(new TripBookingDto(1L, "abcd", date.atStartOfDay(), 3));

		List<TripBookingDto> result = service.search(bookings, true);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.get(0).getId() == 1L);
		assertTrue(result.get(0).getMedallion().equals("abcd"));
		assertTrue(result.get(0).getPickUpDateTime().equals(date.atStartOfDay()));
		assertTrue(result.get(0).getNumTrips() == 3);
	}
}
