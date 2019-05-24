package vn.edu.vnuk.airlines.model;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class Flight {
	
	@NotNull
	private Long id;
	
	@NotNull
	private Long route_Id;
	
	@NotNull
	private Long dayId;
	
	@NotNull
	private Long planeModelId;
	
	@NotNull
	private Date departureTime;
	
	@NotNull
	private Date arrivalTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoute_Id() {
		return route_Id;
	}

	public void setRoute_Id(Long route_Id) {
		this.route_Id = route_Id;
	}

	public Long getDayId() {
		return dayId;
	}

	public void setDayId(Long dayId) {
		this.dayId = dayId;
	}

	public Long getPlaneModelId() {
		return planeModelId;
	}

	public void setPlaneModelId(Long planeModelId) {
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

}
