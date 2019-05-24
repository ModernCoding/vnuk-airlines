package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class PlaneModel {
	
	@NotNull
	private Long id;
	
	@NotNull
	private Long planeManufacturerId;
	
	@NotNull
	private String label;
	
	private PlaneManufacturer planeManufacturer;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getPlaneManufacturerId() {
		return planeManufacturerId;
	}

	public void setPlaneManufacturerId(Long planeManufacturerId) {
		this.planeManufacturerId = planeManufacturerId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public PlaneManufacturer getPlaneManufacturer() {
		return planeManufacturer;
	}

	public void setPlaneManufacturer(PlaneManufacturer planeManufacturer) {
		this.planeManufacturer = planeManufacturer;
	}

}
