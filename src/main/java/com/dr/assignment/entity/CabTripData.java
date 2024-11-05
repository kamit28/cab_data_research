package com.dr.assignment.entity;

import java.sql.Timestamp;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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
	private Timestamp pickupDateTime;

	@Column(name = "dropoff_datetime")
	private Timestamp dropoffDateTime;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMedallion() {
		return medallion;
	}

	public void setMedallion(String medallion) {
		this.medallion = medallion;
	}

	public String getHackLicence() {
		return hackLicence;
	}

	public void setHackLicence(String hackLicence) {
		this.hackLicence = hackLicence;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public Integer getRateCode() {
		return rateCode;
	}

	public void setRateCode(Integer rateCode) {
		this.rateCode = rateCode;
	}

	public String getStoreAndForwardFlag() {
		return storeAndForwardFlag;
	}

	public void setStoreAndForwardFlag(String storeAndForwardFlag) {
		this.storeAndForwardFlag = storeAndForwardFlag;
	}

	public Timestamp getPickupDateTime() {
		return pickupDateTime;
	}

	public void setPickupDateTime(Timestamp pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}

	public Timestamp getDropoffDateTime() {
		return dropoffDateTime;
	}

	public void setDropoffDateTime(Timestamp dropoffDateTime) {
		this.dropoffDateTime = dropoffDateTime;
	}

	public Integer getPassengerCount() {
		return passengerCount;
	}

	public void setPassengerCount(Integer passengerCount) {
		this.passengerCount = passengerCount;
	}

	public Long getTripTimeInSeconds() {
		return tripTimeInSeconds;
	}

	public void setTripTimeInSeconds(Long tripTimeInSeconds) {
		this.tripTimeInSeconds = tripTimeInSeconds;
	}

	public Double getTripDistance() {
		return tripDistance;
	}

	public void setTripDistance(Double tripDistance) {
		this.tripDistance = tripDistance;
	}

	public Double getPickupLongitude() {
		return pickupLongitude;
	}

	public void setPickupLongitude(Double pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}

	public Double getPickupLatitude() {
		return pickupLatitude;
	}

	public void setPickupLatitude(Double pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}

	public Double getDropoffLongitude() {
		return dropoffLongitude;
	}

	public void setDropoffLongitude(Double dropoffLongitude) {
		this.dropoffLongitude = dropoffLongitude;
	}

	public Double getDropoffLatitude() {
		return dropoffLatitude;
	}

	public void setDropoffLatitude(Double dropoffLatitude) {
		this.dropoffLatitude = dropoffLatitude;
	}

	public CabTripData() {
	}

	public CabTripData(Long id, String medallion, String hackLicence, String vendorId, Integer rateCode,
			String storeAndForwardFlag, Timestamp pickupDateTime, Timestamp dropoffDateTime, Integer passengerCount,
			Long tripTimeInSeconds, Double tripDistance, Double pickupLongitude, Double pickupLatitude,
			Double dropoffLongitude, Double dropoffLatitude) {
		this.id = id;
		this.medallion = medallion;
		this.hackLicence = hackLicence;
		this.vendorId = vendorId;
		this.rateCode = rateCode;
		this.storeAndForwardFlag = storeAndForwardFlag;
		this.pickupDateTime = pickupDateTime;
		this.dropoffDateTime = dropoffDateTime;
		this.passengerCount = passengerCount;
		this.tripTimeInSeconds = tripTimeInSeconds;
		this.tripDistance = tripDistance;
		this.pickupLongitude = pickupLongitude;
		this.pickupLatitude = pickupLatitude;
		this.dropoffLongitude = dropoffLongitude;
		this.dropoffLatitude = dropoffLatitude;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CabTripData))
			return false;
		CabTripData other = (CabTripData) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return String.format(
				"CabTripData [id=%s, medallion=%s, hackLicence=%s, vendorId=%s, rateCode=%s, storeAndForwardFlag=%s, pickupDateTime=%s, dropoffDateTime=%s, passengerCount=%s, tripTimeInSeconds=%s, tripDistance=%s, pickupLongitude=%s, pickupLatitude=%s, dropoffLongitude=%s, dropoffLatitude=%s]",
				id, medallion, hackLicence, vendorId, rateCode, storeAndForwardFlag, pickupDateTime, dropoffDateTime,
				passengerCount, tripTimeInSeconds, tripDistance, pickupLongitude, pickupLatitude, dropoffLongitude,
				dropoffLatitude);
	}
}
