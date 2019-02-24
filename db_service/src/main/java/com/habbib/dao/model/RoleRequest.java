package com.habbib.dao.model;

import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
public class RoleRequest  {

	private int idRole;

	private String description;

	private String role;


	public RoleRequest() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	

}