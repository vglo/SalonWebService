package com.habbib.customer.request.model;

import java.util.Date;

public class RequestCustomer {
	
	private String address;
	
	private Date dob;
	
	private String email;
	
	private String firstName;
	
	private String LastName;
	
	private String mobnumber;

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}

	/**
	 * @return the mobnumber
	 */
	public String getMobnumber() {
		return mobnumber;
	}

	/**
	 * @param mobnumber the mobnumber to set
	 */
	public void setMobnumber(String mobnumber) {
		this.mobnumber = mobnumber;
	}
	
	
}
