package com.dr.assignment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "cab_trip_data")
public class CabTripData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "medallion", nullable = false)
	private String medallion;

	@Column(name = "hack_license", nullable = false)
	private String hackLicence;

	@Column(name = "vendor_id", nullable = false)
	private String vendorId;

	@Column(name = "rate_code")
	private Integer rateCode;

	@Column(name = "store_and_fwd_flag", nullable = false)
	private String storeAndForwardFlag;

	@Column(name = "pickup_datetime")
	private Date pickupDateTime;

	@Column(name = "dropoff_datetime")
	private Date dropoffDateTime;

	@Column(name = "passenger_count")
	private Integer passengerCount;

	@Column(name = "trip_time_in_secs")
	private Long tripTimeInSeconds;

	@Column(name = "trip_distance")
	private Double tripDistance;

	@Column(name = "pickup_longitude")
	private Double pickupLongitude;

	@Column(name = "pickup_latitude")
	private Double pickupLatitude;

	@Column(name = "dropoff_longitude")
	private Double dropoffLongitude;

	@Column(name = "dropoff_latitude")
	private Double dropoffLatitude;
}
