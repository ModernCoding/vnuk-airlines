package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class PlaneModels {
	
	@NotNull
	private long id;
	
	@NotNull
	private int planeManufacturerId;
	
	@NotNull
	private String label;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPlaneManufacturerId() {
		return planeManufacturerId;
	}

	public void setPlaneManufacturerId(int planeManufacturerId) {
		this.planeManufacturerId = planeManufacturerId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
