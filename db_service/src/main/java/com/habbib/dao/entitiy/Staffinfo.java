package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	//bi-directional many-to-one association to Bill
	@JsonIgnore
	@OneToMany(mappedBy="staffinfo")
	private List<Bill> bills;

	//bi-directional many-to-one association to Role
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="role")
	private Role roleBean;

	//bi-directional many-to-one association to Shopinfo
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="shopId")
	private Shopinfo shopinfo;

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

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public Bill addBill(Bill bill) {
		getBills().add(bill);
		bill.setStaffinfo(this);

		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setStaffinfo(null);

		return bill;
	}

	public Role getRoleBean() {
		return this.roleBean;
	}

	public void setRoleBean(Role roleBean) {
		this.roleBean = roleBean;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

}