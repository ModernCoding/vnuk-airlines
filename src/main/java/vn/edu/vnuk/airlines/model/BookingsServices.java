package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class BookingsServices {
	
	@NotNull
	private Long id;
	
	@NotNull
	private int bookingId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	@NotNull
	private int serviceId;
	

}
