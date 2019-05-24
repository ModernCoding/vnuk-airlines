package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class Price {
	
	@NotNull
	private Long id;
	
	@NotNull
	private Long flightId;

	@NotNull
	private Long classId;

	@NotNull
	private Long priceTypeId;

	@NotNull
	private Long value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getPriceTypeId() {
		return priceTypeId;
	}

	public void setPriceTypeId(Long priceTypeId) {
		this.priceTypeId = priceTypeId;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
	
}
