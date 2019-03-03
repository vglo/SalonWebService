package com.habbib.sms.response.model;

import java.io.Serializable;


/**
 * The persistent class for the shoptype database table.
 * 
 */
public class Shoptype implements Serializable {

	private int idShopType;

	private String description;

	private String type;


	public Shoptype() {
	}

	public int getIdShopType() {
		return this.idShopType;
	}

	public void setIdShopType(int idShopType) {
		this.idShopType = idShopType;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

}