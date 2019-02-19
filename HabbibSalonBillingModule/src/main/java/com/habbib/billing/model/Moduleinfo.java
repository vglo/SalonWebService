package com.habbib.billing.model;

import java.io.Serializable;
import java.util.List;
public class Moduleinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idModuleInfo;

	private String name;

	private List<Shopinfo> shopinfos;

	public Moduleinfo() {
	}

	public int getIdModuleInfo() {
		return this.idModuleInfo;
	}

	public void setIdModuleInfo(int idModuleInfo) {
		this.idModuleInfo = idModuleInfo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Shopinfo> getShopinfos() {
		return this.shopinfos;
	}

	public void setShopinfos(List<Shopinfo> shopinfos) {
		this.shopinfos = shopinfos;
	}

}