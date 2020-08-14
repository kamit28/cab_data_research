package com.dr.assignment.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
public class TripBooking {

	private Long id;

	@NotEmpty(message = "medallion can not be null or empty")
	private String medallion;

	@NotNull(message = "date can not be null")
	private Date pickUpDate;

	private int numTrips;

}
