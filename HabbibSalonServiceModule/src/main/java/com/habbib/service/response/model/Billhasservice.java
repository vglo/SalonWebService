package com.habbib.service.response.model;

/**
 * The persistent class for the billhasservice database table.
 * 
 */
public class Billhasservice {

	private int idBillHasService;

	private int quantity;

	private Salonservice salonservice;

	public Billhasservice() {
	}

	public int getIdBillHasService() {
		return this.idBillHasService;
	}

	public void setIdBillHasService(int idBillHasService) {
		this.idBillHasService = idBillHasService;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	public Salonservice getSalonservice() {
		return this.salonservice;
	}

	public void setSalonservice(Salonservice salonservice) {
		this.salonservice = salonservice;
	}

}