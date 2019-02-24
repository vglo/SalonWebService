package com.habbib.shop.request.model;

/**
 * The persistent class for the salonservice database table.
 * 
 */
public class SalonserviceRequest {

	private int idSalonService;

	private String name;

	private double price;


	private int shopId;

	public SalonserviceRequest() {
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