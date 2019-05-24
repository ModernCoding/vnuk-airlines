package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class BookingsService {
	
	@NotNull
	private Long id;
	
	@NotNull
	private Long bookingId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
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
