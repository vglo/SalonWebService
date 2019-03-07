package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the usercredentials database table.
 * 
 */
@Entity
@Table(name="usercredentials")
@NamedQuery(name="Usercredential.findAll", query="SELECT u FROM Usercredential u")
public class Usercredential implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUserCredentials;

	private String password;

	private String salt;

	private String username;

	//bi-directional many-to-one association to Customerinfo
	@JsonIgnore
	@OneToMany(mappedBy="usercredential")
	private List<Customerinfo> customerinfos;

	//bi-directional many-to-one association to Staffinfo
	@JsonIgnore
	@OneToMany(mappedBy="usercredential")
	private List<Staffinfo> staffinfos;

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

	public List<Customerinfo> getCustomerinfos() {
		return this.customerinfos;
	}

	public void setCustomerinfos(List<Customerinfo> customerinfos) {
		this.customerinfos = customerinfos;
	}

	public Customerinfo addCustomerinfo(Customerinfo customerinfo) {
		getCustomerinfos().add(customerinfo);
		customerinfo.setUsercredential(this);

		return customerinfo;
	}

	public Customerinfo removeCustomerinfo(Customerinfo customerinfo) {
		getCustomerinfos().remove(customerinfo);
		customerinfo.setUsercredential(null);

		return customerinfo;
	}

	public List<Staffinfo> getStaffinfos() {
		return this.staffinfos;
	}

	public void setStaffinfos(List<Staffinfo> staffinfos) {
		this.staffinfos = staffinfos;
	}

	public Staffinfo addStaffinfo(Staffinfo staffinfo) {
		getStaffinfos().add(staffinfo);
		staffinfo.setUsercredential(this);

		return staffinfo;
	}

	public Staffinfo removeStaffinfo(Staffinfo staffinfo) {
		getStaffinfos().remove(staffinfo);
		staffinfo.setUsercredential(null);

		return staffinfo;
	}

}