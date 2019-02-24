package com.habbib.dao.model;

import java.util.List;


/**
 * The persistent class for the shoptype database table.
 * 
 */
public class ShoptypeRequest {

	private int idShopType;

	private String description;

	private String type;


	public ShoptypeRequest() {
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