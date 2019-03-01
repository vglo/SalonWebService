package com.habbib.billing.model;

/**
 * The persistent class for the paymenttype database table.
 * 
 */
public class Paymenttype{

	private int idPaymentType;

	private String integrationKey;

	private int shopId;

	private String type;


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

	

	
}