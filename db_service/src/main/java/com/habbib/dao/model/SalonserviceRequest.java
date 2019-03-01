package com.habbib.dao.model;

/**
 * The persistent class for the salonservice database table.
 * 
 */
public class SalonserviceRequest {


	private String name;

	private double price;

	private int shopId;

	public SalonserviceRequest() {
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

	

}