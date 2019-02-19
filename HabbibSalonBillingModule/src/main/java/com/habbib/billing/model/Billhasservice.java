package com.habbib.billing.model;

public class Billhasservice {

	private int idBillHasService;

	private int quantity;

	private Bill bill;

	private int serviceId;

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

	/**
	 * @return the serviceId
	 */
	public int getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	
}