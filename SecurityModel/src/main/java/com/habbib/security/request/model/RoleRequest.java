package com.habbib.security.request.model;

/**
 * The persistent class for the role database table.
 * 
 */
public class RoleRequest  {

	private String description;

	private String role;


	public RoleRequest() {
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