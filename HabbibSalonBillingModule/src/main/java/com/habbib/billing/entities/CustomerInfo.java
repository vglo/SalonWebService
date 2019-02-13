package com.habbib.billing.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CustomerInfo {

	@Id
	private int customerId;
	
	private String customerName;
	
	private long mobNumber;
	
	private String address;
	
	private Date birthDate;
	
	private int numberOfVist;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public long getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(long mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getNumberOfVist() {
		return numberOfVist;
	}

	public void setNumberOfVist(int numberOfVist) {
		this.numberOfVist = numberOfVist;
	}
	
	
	
}
