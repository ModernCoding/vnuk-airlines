package vn.edu.vnuk.airlines.model;

import javax.validation.constraints.NotNull;

public class Prices {
	
	@NotNull
	private long id;
	
	@NotNull
	private String label;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}


}
