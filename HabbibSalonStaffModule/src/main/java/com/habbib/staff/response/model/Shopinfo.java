package com.habbib.staff.response.model;

import java.util.List;


/**
 * The persistent class for the shopinfo database table.
 * 
 */
public class Shopinfo  {

	private int idShopInfo;

	private String address;

	private String name;

	private String phone1;

	private String phone2;


	private Shoptype shoptype;

	private List<Staffinfo> staffinfos;

	public Shopinfo() {
	}

	public int getIdShopInfo() {
		return this.idShopInfo;
	}

	public void setIdShopInfo(int idShopInfo) {
		this.idShopInfo = idShopInfo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

		public Shoptype getShoptype() {
		return this.shoptype;
	}

	public void setShoptype(Shoptype shoptype) {
		this.shoptype = shoptype;
	}

	public List<Staffinfo> getStaffinfos() {
		return this.staffinfos;
	}

	public void setStaffinfos(List<Staffinfo> staffinfos) {
		this.staffinfos = staffinfos;
	}

	public Staffinfo addStaffinfo(Staffinfo staffinfo) {
		getStaffinfos().add(staffinfo);
		staffinfo.setShopinfo(this);

		return staffinfo;
	}

	public Staffinfo removeStaffinfo(Staffinfo staffinfo) {
		getStaffinfos().remove(staffinfo);
		staffinfo.setShopinfo(null);

		return staffinfo;
	}



}