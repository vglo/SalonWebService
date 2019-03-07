package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the staffinfo database table.
 * 
 */
@Entity
@NamedQuery(name="Staffinfo.findAll", query="SELECT s FROM Staffinfo s")
public class Staffinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idStaffInfo;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private String email;

	private String firstName;

	private String lastName;

	private String mobile;

	//bi-directional many-to-one association to Billhasservice
	@JsonIgnore
	@OneToMany(mappedBy="staffinfo")
	private List<Billhasservice> billhasservices;

	//bi-directional many-to-many association to Role
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="staffinfo_has_role"
		, joinColumns={
			@JoinColumn(name="StaffInfo_idStaffInfo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Role_idRole")
			}
		)
	private List<Role> roles;

	//bi-directional many-to-one association to Shopinfo
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="shopId")
	private Shopinfo shopinfo;

	//bi-directional many-to-one association to Usercredential
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userCredentials")
	private Usercredential usercredential;

	public Staffinfo() {
	}

	public int getIdStaffInfo() {
		return this.idStaffInfo;
	}

	public void setIdStaffInfo(int idStaffInfo) {
		this.idStaffInfo = idStaffInfo;
	}

	public Date getDob() {
		return this.dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<Billhasservice> getBillhasservices() {
		return this.billhasservices;
	}

	public void setBillhasservices(List<Billhasservice> billhasservices) {
		this.billhasservices = billhasservices;
	}

	public Billhasservice addBillhasservice(Billhasservice billhasservice) {
		getBillhasservices().add(billhasservice);
		billhasservice.setStaffinfo(this);

		return billhasservice;
	}

	public Billhasservice removeBillhasservice(Billhasservice billhasservice) {
		getBillhasservices().remove(billhasservice);
		billhasservice.setStaffinfo(null);

		return billhasservice;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

	public Usercredential getUsercredential() {
		return this.usercredential;
	}

	public void setUsercredential(Usercredential usercredential) {
		this.usercredential = usercredential;
	}

}