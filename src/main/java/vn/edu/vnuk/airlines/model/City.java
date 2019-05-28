package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class City {
	
	@NotNull
	private Long id;
	
	@NotNull
	private Long countryId;

	@NotNull
	private String label;
	
	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	private Country country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
