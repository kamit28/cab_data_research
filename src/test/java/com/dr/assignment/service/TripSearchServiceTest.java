package com.dr.assignment.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.dr.assignment.model.TripBooking;
import com.dr.assignment.repository.TripRepository;

@RunWith(MockitoJUnitRunner.class)
public class TripSearchServiceTest {

	@Mock
	RedisTemplate<String, TripBooking> bookingCache;

	@Mock
	private ValueOperations<String, TripBooking> valueOperations;

	@Mock
	private TripRepository repository;

	@InjectMocks
	private TripSearchService service;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

	@Before
	public void setUp() {
		when(bookingCache.opsForValue()).thenReturn(valueOperations);
	}

	@Test
	public void search_db_access_success() throws ParseException {
		List<TripBooking> bookings = new ArrayList<>();
		String dateInString = "2013-12-06";
		Date date = formatter.parse(dateInString);
		bookings.add(new TripBooking(null, "abcd", date, 0));

		when(repository.countByMedallionAndPickUpDate("abcd", date)).thenReturn(new TripBooking(1L, "abcd", date, 3));
		doNothing().when(valueOperations).set(anyString(), any());

		List<TripBooking> result = service.search(bookings, false);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.get(0).getId() == 1L);
		assertTrue(result.get(0).getMedallion().equals("abcd"));
		assertTrue(result.get(0).getPickUpDate().equals(date));
		assertTrue(result.get(0).getNumTrips() == 3);
	}

	@Test
	public void search_cache_access_success() throws ParseException {
		List<TripBooking> bookings = new ArrayList<>();
		String dateInString = "2013-12-06";
		Date date = formatter.parse(dateInString);

		TripBooking booking = new TripBooking(null, "abcd", date, 0);

		bookings.add(booking);
		var key = "search_" + booking.getMedallion() + "_" + booking.getPickUpDate().getTime();

		when(bookingCache.hasKey(key)).thenReturn(true);
		when(bookingCache.opsForValue()).thenReturn(valueOperations);

		when(repository.countByMedallionAndPickUpDate("abcd", date)).thenReturn(new TripBooking(1L, "abcd", date, 3));
		doNothing().when(valueOperations).set(anyString(), any());

		List<TripBooking> result = service.search(bookings, false);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.get(0).getId() == 1L);
		assertTrue(result.get(0).getMedallion().equals("abcd"));
		assertTrue(result.get(0).getPickUpDate().equals(date));
		assertTrue(result.get(0).getNumTrips() == 3);
	}

	@Test
	public void search_useCach_success() throws ParseException {
		List<TripBooking> bookings = new ArrayList<>();
		String dateInString = "2013-12-06";
		Date date = formatter.parse(dateInString);

		TripBooking booking = new TripBooking(null, "abcd", date, 0);

		bookings.add(booking);
		var key = "search_" + booking.getMedallion() + "_" + booking.getPickUpDate().getTime();

		when(bookingCache.hasKey(key)).thenReturn(true);
		when(bookingCache.opsForValue()).thenReturn(valueOperations);
		when(valueOperations.get(key)).thenReturn(new TripBooking(1L, "abcd", date, 3));

		List<TripBooking> result = service.search(bookings, true);

		assertNotNull(result);
		assertEquals(1, result.size());
		assertTrue(result.get(0).getId() == 1L);
		assertTrue(result.get(0).getMedallion().equals("abcd"));
		assertTrue(result.get(0).getPickUpDate().equals(date));
		assertTrue(result.get(0).getNumTrips() == 3);
	}
}