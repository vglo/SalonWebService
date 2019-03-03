package com.habbib.sms.response.model;

/**
 * The persistent class for the role database table.
 * 
 */
public class Role  {

	private int idRole;

	private String description;

	private String role;

	public Role() {
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