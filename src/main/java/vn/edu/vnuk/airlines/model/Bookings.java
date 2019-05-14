package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class Bookings {
	
	@NotNull
	private Long id;
	
	@NotNull
	private int userId;
	
	@NotNull
	private int flightId;
	
	@NotNull
	private String seatNumber;
	
	@NotNull
	private int priceId;
	
	@NotNull
	private int paymentMethodId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getPriceId() {
		return priceId;
	}

	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	public int getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(int paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}
	

	
	
	
	
	
	

}
