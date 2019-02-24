package com.habbib.billing.dbrequest.model;

/**
 * The persistent class for the billhasservice database table.
 * 
 */
public class BillhasserviceRequest{


	private int quantity;

	private int idSalonService;

	public BillhasserviceRequest() {
	}

	
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the idSalonService
	 */
	public int getIdSalonService() {
		return idSalonService;
	}

	/**
	 * @param idSalonService the idSalonService to set
	 */
	public void setIdSalonService(int idSalonService) {
		this.idSalonService = idSalonService;
	}

	
}