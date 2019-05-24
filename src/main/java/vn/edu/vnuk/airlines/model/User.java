package vn.edu.vnuk.airlines.model;

import java.sql.Date;

import javax.validation.constraints.NotNull;

public class User {
	
	@NotNull
	private Long id;
	
	@NotNull
	private Long userTypeId;
	
	@NotNull
	private String lastName;
	
	@NotNull
	private Date dateOfBirth;
	
	@NotNull
	private String address;
	
	@NotNull
	private String email;
	
	@NotNull
	private int phone;
	
	@NotNull
	private Long identificationTypeId;

	@NotNull
	private int identificationNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Long userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Long getIdentificationTypeId() {
		return identificationTypeId;
	}

	public void setIdentificationTypeId(Long identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	public int getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(int identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

}
