package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the customerinfo database table.
 * 
 */
@Entity
@NamedQuery(name="Customerinfo.findAll", query="SELECT c FROM Customerinfo c")
public class Customerinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idCustomerInfo;

	private String address;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private String email;

	private String firstName;

	private String lastName;

	private String mobile;

	//bi-directional many-to-one association to Bill
	@JsonIgnore
	@OneToMany(mappedBy="customerinfo")
	private List<Bill> bills;

	//bi-directional many-to-one association to Shopinfo
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="shopId")
	private Shopinfo shopinfo;

	//bi-directional many-to-one association to Appointment
	@JsonIgnore
	@OneToMany(mappedBy="customerinfo")
	private List<Appointment> appointments;

	public Customerinfo() {
	}

	public int getIdCustomerInfo() {
		return this.idCustomerInfo;
	}

	public void setIdCustomerInfo(int idCustomerInfo) {
		this.idCustomerInfo = idCustomerInfo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		bill.setCustomerinfo(this);

		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setCustomerinfo(null);

		return bill;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setCustomerinfo(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setCustomerinfo(null);

		return appointment;
	}

}