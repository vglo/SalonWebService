package com.habbib.sms.response.model;

/**
 * The persistent class for the salonservice database table.
 * 
 */
public class Salonservice {

	private int idSalonService;

	private String name;

	private double price;


	private Shopinfo shopinfo;

	public Salonservice() {
	}

	public int getIdSalonService() {
		return this.idSalonService;
	}

	public void setIdSalonService(int idSalonService) {
		this.idSalonService = idSalonService;
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



	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

}