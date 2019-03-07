package com.habbib.billing.model;

/**
 * The persistent class for the billhasservice database table.
 * 
 */
public class Billhasservice {

	private double amount;

	private String name;

	private double price;

	private int quantity;

	private Shopinfo shopinfo;

	private Staffinfo staffinfo;

	public Billhasservice() {
	}

	

	public double getAmount() {
		return this.amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

	public Staffinfo getStaffinfo() {
		return this.staffinfo;
	}

	public void setStaffinfo(Staffinfo staffinfo) {
		this.staffinfo = staffinfo;
	}

}