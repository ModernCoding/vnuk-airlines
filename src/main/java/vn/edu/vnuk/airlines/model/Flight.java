package vn.edu.vnuk.airlines.model;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class Flight {
	
	@NotNull
	private Long id;
	
	@NotNull
	private Long routeId;
	
	@NotNull
	private Long dayId;
	
	@NotNull
	private Long planeModelId;
	
	@NotNull
	private Date departureTime;
	
	@NotNull
	private Date arrivalTime;
	
	private Route route;
	
	private Day day;
	
	private PlaneModel planeModel;

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public PlaneModel getPlaneModel() {
		return planeModel;
	}

	public void setPlaneModel(PlaneModel planeModel) {
		this.planeModel = planeModel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
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
