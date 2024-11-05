package com.dr.assignment.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class CabTrip {

	private Long id;

	private String medallion;

	private String hackLicence;

	private String vendorId;

	private Integer rateCode;

	private String storeAndForwardFlag;

	private LocalDateTime pickupDateTime;

	private LocalDateTime dropoffDateTime;

	private Integer passengerCount;

	private Long tripTimeInSeconds;

	private Double tripDistance;

	private Double pickupLongitude;

	private Double pickupLatitude;

	private Double dropoffLongitude;

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

	public LocalDateTime getPickupDateTime() {
		return pickupDateTime;
	}

	public void setPickupDateTime(LocalDateTime pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}

	public LocalDateTime getDropoffDateTime() {
		return dropoffDateTime;
	}

	public void setDropoffDateTime(LocalDateTime dropoffDateTime) {
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

	@Override
	public int hashCode() {
		return Objects.hash(dropoffDateTime, dropoffLatitude, dropoffLongitude, hackLicence, id, medallion,
				passengerCount, pickupDateTime, pickupLatitude, pickupLongitude, rateCode, storeAndForwardFlag,
				tripDistance, tripTimeInSeconds, vendorId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof CabTrip))
			return false;
		CabTrip other = (CabTrip) obj;
		return Objects.equals(dropoffDateTime, other.dropoffDateTime)
				&& Objects.equals(dropoffLatitude, other.dropoffLatitude)
				&& Objects.equals(dropoffLongitude, other.dropoffLongitude)
				&& Objects.equals(hackLicence, other.hackLicence) && Objects.equals(id, other.id)
				&& Objects.equals(medallion, other.medallion) && Objects.equals(passengerCount, other.passengerCount)
				&& Objects.equals(pickupDateTime, other.pickupDateTime)
				&& Objects.equals(pickupLatitude, other.pickupLatitude)
				&& Objects.equals(pickupLongitude, other.pickupLongitude) && Objects.equals(rateCode, other.rateCode)
				&& Objects.equals(storeAndForwardFlag, other.storeAndForwardFlag)
				&& Objects.equals(tripDistance, other.tripDistance)
				&& Objects.equals(tripTimeInSeconds, other.tripTimeInSeconds)
				&& Objects.equals(vendorId, other.vendorId);
	}

	@Override
	public String toString() {
		return String.format(
				"CabTrip [id=%s, medallion=%s, hackLicence=%s, vendorId=%s, rateCode=%s, storeAndForwardFlag=%s, pickupDateTime=%s, dropoffDateTime=%s, passengerCount=%s, tripTimeInSeconds=%s, tripDistance=%s, pickupLongitude=%s, pickupLatitude=%s, dropoffLongitude=%s, dropoffLatitude=%s]",
				id, medallion, hackLicence, vendorId, rateCode, storeAndForwardFlag, pickupDateTime, dropoffDateTime,
				passengerCount, tripTimeInSeconds, tripDistance, pickupLongitude, pickupLatitude, dropoffLongitude,
				dropoffLatitude);
	}
}
