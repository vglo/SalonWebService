package com.habbib.service.response.model;

import java.util.List;


/**
 * The persistent class for the salonservice database table.
 * 
 */
public class Salonservice {

	private int idSalonService;

	private String name;

	private double price;

	private List<Billhasservice> billhasservices;

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

	public List<Billhasservice> getBillhasservices() {
		return this.billhasservices;
	}

	public void setBillhasservices(List<Billhasservice> billhasservices) {
		this.billhasservices = billhasservices;
	}

	public Billhasservice addBillhasservice(Billhasservice billhasservice) {
		getBillhasservices().add(billhasservice);
		billhasservice.setSalonservice(this);

		return billhasservice;
	}

	public Billhasservice removeBillhasservice(Billhasservice billhasservice) {
		getBillhasservices().remove(billhasservice);
		billhasservice.setSalonservice(null);

		return billhasservice;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

}