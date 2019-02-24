package com.habbib.billing.dbrequest.model;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customerinfo database table.
 * 
 */

public class CustomerRequest{


	private String address;

	private Date dob;

	private String email;

	private String firstName;

	private String lastName;

	private String mobile;


	private int idShopInfo;


	public CustomerRequest() {
	}

	

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	

	/**
	 * @return the idShopInfo
	 */
	public int getIdShopInfo() {
		return idShopInfo;
	}

	/**
	 * @param idShopInfo the idShopInfo to set
	 */
	public void setIdShopInfo(int idShopInfo) {
		this.idShopInfo = idShopInfo;
	}

	

}