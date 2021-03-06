package com.habbib.dao.model;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the staffinfo database table.
 * 
 */
public class StaffinfoRequest {

	private Date dob;

	private String email;

	private String firstName;

	private String lastName;

	private String mobile;

	private List<Integer> roleId;

	private int shopId;

	public StaffinfoRequest() {
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
	 * @return the shopId
	 */
	public int getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}


	/**
	 * @return the roleId
	 */
	public List<Integer> getRoleId() {
		return roleId;
	}


	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(List<Integer> roleId) {
		this.roleId = roleId;
	}

	

}