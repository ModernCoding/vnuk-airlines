package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class Routes {
	
	@NotNull
	private Long id;
	
	@NotNull
	private int takeOffAirportId;
	
	@NotNull
	private int landingAirportId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getTakeOffAirportId() {
		return takeOffAirportId;
	}

	public void setTakeOffAirportId(int takeOffAirportId) {
		this.takeOffAirportId = takeOffAirportId;
	}

	public int getLandingAirportId() {
		return landingAirportId;
	}

	public void setLandingAirportId(int landingAirportId) {
		this.landingAirportId = landingAirportId;
	}
	
}
