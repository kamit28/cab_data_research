package com.dr.assignment.model;

import java.util.List;

import jakarta.validation.Valid;

public record TripSearchRequest(@Valid	List<TripBooking> tripBookings){
}
