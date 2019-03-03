package com.habbib.sms.response.model;

/**
 * The persistent class for the billhasservice database table.
 * 
 */
public class Billhasservice{

	private int idBillHasService;

	private int quantity;

	private Bill bill;

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

	public Bill getBill() {
		return this.bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public Salonservice getSalonservice() {
		return this.salonservice;
	}

	public void setSalonservice(Salonservice salonservice) {
		this.salonservice = salonservice;
	}

}