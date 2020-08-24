package com.dr.assignment.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TripBookingDto {

	private Long id;

	private String medallion;

	private Date pickUpDateTime;

	private int numTrips;

}
