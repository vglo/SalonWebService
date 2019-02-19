package com.habbib.billing.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class Staffinfo  {

	private int idStaffInfo;

	private Date dob;

	private String email;

	private String firstName;

	private String lastName;

	private String mobile;

	private List<Bill> bills;

	private Role roleBean;

	private Shopinfo shopinfo;

	public Staffinfo() {
	}

	public int getIdStaffInfo() {
		return this.idStaffInfo;
	}

	public void setIdStaffInfo(int idStaffInfo) {
		this.idStaffInfo = idStaffInfo;
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

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	

	public Role getRoleBean() {
		return this.roleBean;
	}

	public void setRoleBean(Role roleBean) {
		this.roleBean = roleBean;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

}