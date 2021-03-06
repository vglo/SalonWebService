package com.habbib.sms.response.model;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customerinfo database table.
 * 
 */
public class Customerinfo  {

	private int idCustomerInfo;

	private String address;

	private Date dob;

	private String email;

	private String firstName;

	private String lastName;

	private String mobile;

	private Shopinfo shopinfo;

	private List<Appointment> appointments;

	public Customerinfo() {
	}

	public int getIdCustomerInfo() {
		return this.idCustomerInfo;
	}

	public void setIdCustomerInfo(int idCustomerInfo) {
		this.idCustomerInfo = idCustomerInfo;
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

	

	

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

	

}