package com.habbib.dao.model;

import java.util.List;


/**
 * The persistent class for the moduleinfo database table.
 * 
 */
public class ModuleinfoRequest {

	private int idModuleInfo;

	private String name;


	public ModuleinfoRequest() {
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


}