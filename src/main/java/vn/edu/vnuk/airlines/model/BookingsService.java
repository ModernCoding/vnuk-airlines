package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class BookingsService {
	
	@NotNull
	private Long id;
	
	@NotNull
	private Long bookingId;
	
	@NotNull
	private Long serviceId;
	
	private Booking booking;
	
	private Service service;
	
	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

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

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	

}
