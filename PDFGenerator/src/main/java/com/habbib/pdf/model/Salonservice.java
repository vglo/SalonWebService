package com.habbib.pdf.model;

import java.io.Serializable;
import java.util.List;

public class Salonservice implements Serializable {
	private static final long serialVersionUID = 1L;

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

	
	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

}