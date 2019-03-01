package com.habbib.billing.model;

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

	private List<Bill> bills;

	private Shoptype shoptype;

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

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	
	
	public Shoptype getShoptype() {
		return this.shoptype;
	}

	public void setShoptype(Shoptype shoptype) {
		this.shoptype = shoptype;
	}

	
}