package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class Route {
	
	@NotNull
	private Long id;
	
	@NotNull
	private Long takeOffAirportId;
	
	@NotNull
	private Long landingAirportId;
	
	private Airport airport;

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTakeOffAirportId() {
		return takeOffAirportId;
	}

	public void setTakeOffAirportId(Long takeOffAirportId) {
		this.takeOffAirportId = takeOffAirportId;
	}

	public Long getLandingAirportId() {
		return landingAirportId;
	}

	public void setLandingAirportId(Long landingAirportId) {
		this.landingAirportId = landingAirportId;
	}

}
