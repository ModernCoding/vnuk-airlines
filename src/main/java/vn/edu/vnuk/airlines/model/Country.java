package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class Country {
	
	@NotNull
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@NotNull
	private String label;

}
