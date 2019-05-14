package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class Cities {
	
	@NotNull
	private Long id;
	
	@NotNull
	private int countryId;

	@NotNull
	private String label;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
