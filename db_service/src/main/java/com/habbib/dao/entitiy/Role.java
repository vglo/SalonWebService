package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRole;

	private String description;

	private String role;

	//bi-directional many-to-one association to Staffinfo
	@JsonIgnore
	@OneToMany(mappedBy="roleBean")
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