package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class PriceType {
	
	@NotNull
	private Long id;
	
	@NotNull
	private String label;
	
	@NotNull
	private String description;
	
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
