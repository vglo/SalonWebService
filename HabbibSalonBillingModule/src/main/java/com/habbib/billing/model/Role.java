package com.habbib.billing.model;

import java.io.Serializable;
import java.util.List;


public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private int idRole;

	private String description;

	private String role;

	private List<Staffinfo> staffinfos;

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

	public List<Staffinfo> getStaffinfos() {
		return this.staffinfos;
	}

	public void setStaffinfos(List<Staffinfo> staffinfos) {
		this.staffinfos = staffinfos;
	}

	public Staffinfo addStaffinfo(Staffinfo staffinfo) {
		getStaffinfos().add(staffinfo);
		staffinfo.setRoleBean(this);

		return staffinfo;
	}

	public Staffinfo removeStaffinfo(Staffinfo staffinfo) {
		getStaffinfos().remove(staffinfo);
		staffinfo.setRoleBean(null);

		return staffinfo;
	}

}