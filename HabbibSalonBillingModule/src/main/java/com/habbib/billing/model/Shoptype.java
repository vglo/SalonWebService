package com.habbib.billing.model;

import java.io.Serializable;
import java.util.List;

public class Shoptype implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idShopType;

	private String description;

	private String type;

	private List<Shopinfo> shopinfos;

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

	public List<Shopinfo> getShopinfos() {
		return this.shopinfos;
	}

	public void setShopinfos(List<Shopinfo> shopinfos) {
		this.shopinfos = shopinfos;
	}

	public Shopinfo addShopinfo(Shopinfo shopinfo) {
		getShopinfos().add(shopinfo);
		shopinfo.setShoptype(this);

		return shopinfo;
	}

	public Shopinfo removeShopinfo(Shopinfo shopinfo) {
		getShopinfos().remove(shopinfo);
		shopinfo.setShoptype(null);

		return shopinfo;
	}

}