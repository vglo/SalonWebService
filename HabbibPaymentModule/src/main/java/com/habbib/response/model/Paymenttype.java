package com.habbib.response.model;

import java.util.List;


/**
 * The persistent class for the paymenttype database table.
 * 
 */
public class Paymenttype {

	private int idPaymentType;

	private String integrationKey;

	private int shopId;

	private String type;

	private List<Bill> bills;

	public Paymenttype() {
	}

	public int getIdPaymentType() {
		return this.idPaymentType;
	}

	public void setIdPaymentType(int idPaymentType) {
		this.idPaymentType = idPaymentType;
	}

	public String getIntegrationKey() {
		return this.integrationKey;
	}

	public void setIntegrationKey(String integrationKey) {
		this.integrationKey = integrationKey;
	}

	public int getShopId() {
		return this.shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public Bill addBill(Bill bill) {
		getBills().add(bill);
		bill.setPaymenttype(this);

		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setPaymenttype(null);

		return bill;
	}

}