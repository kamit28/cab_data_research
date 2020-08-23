package com.dr.assignment.model;

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
@ToString
@EqualsAndHashCode
public class CabTrip {

	private Long id;

	private String medallion;

	private String hackLicence;

	private String vendorId;

	private Integer rateCode;

	private String storeAndForwardFlag;

	private Date pickupDateTime;

	private Date dropoffDateTime;

	private Integer passengerCount;

	private Long tripTimeInSeconds;

	private Double tripDistance;

	private Double pickupLongitude;

	private Double pickupLatitude;

	private Double dropoffLongitude;

	private Double dropoffLatitude;
}
