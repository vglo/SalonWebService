package com.habbib.staff.response.model;

/**
 * The persistent class for the usercredentials database table.
 * 
 */
public class Usercredential  {

	private int idUserCredentials;

	private String password;

	private String salt;

	private String username;

	public Usercredential() {
	}

	public int getIdUserCredentials() {
		return this.idUserCredentials;
	}

	public void setIdUserCredentials(int idUserCredentials) {
		this.idUserCredentials = idUserCredentials;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}