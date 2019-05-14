package vn.edu.vnuk.airlines.model;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class Flights {
	
	@NotNull
	private Long id;
	
	@NotNull
	private int route_Id;
	
	@NotNull
	private int dayId;
	
	@NotNull
	private int planeModelId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRoute_Id() {
		return route_Id;
	}

	public void setRoute_Id(int route_Id) {
		this.route_Id = route_Id;
	}

	public int getDayId() {
		return dayId;
	}

	public void setDayId(int dayId) {
		this.dayId = dayId;
	}

	public int getPlaneModelId() {
		return planeModelId;
	}

	public void setPlaneModelId(int planeModelId) {
		this.planeModelId = planeModelId;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@NotNull
	private Date departureTime;
	
	@NotNull
	private Date arrivalTime;

}
