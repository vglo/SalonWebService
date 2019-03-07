package com.habbib.billing.dbrequest.model;

/**
 * The persistent class for the billhasservice database table.
 * 
 */
public class BillhasserviceRequest{


	private int quantity;

	private double price;
	
	private double amount;
	
	private String name;
	
	private int serviceStaffId;	
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the serviceStaffId
	 */
	public int getServiceStaffId() {
		return serviceStaffId;
	}

	/**
	 * @param serviceStaffId the serviceStaffId to set
	 */
	public void setServiceStaffId(int serviceStaffId) {
		this.serviceStaffId = serviceStaffId;
	}

}