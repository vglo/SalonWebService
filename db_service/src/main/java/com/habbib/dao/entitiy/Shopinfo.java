package com.habbib.dao.entitiy;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * The persistent class for the shopinfo database table.
 * 
 */
@Entity(name="ShopInfo")
@NamedQuery(name="ShopInfo.findAll", query="SELECT s FROM ShopInfo s")
public class Shopinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idShopInfo;

	private String address;

	private String name;

	private String phone1;

	private String phone2;

	//bi-directional many-to-one association to Appointment
	@JsonIgnore
	@OneToMany(mappedBy="shopinfo")
	private List<Appointment> appointments;

	//bi-directional many-to-one association to Bill
	@JsonIgnore
	@OneToMany(mappedBy="shopinfo")
	private List<Bill> bills;

	//bi-directional many-to-one association to Billhasservice
	@JsonIgnore
	@OneToMany(mappedBy="shopinfo")
	private List<Billhasservice> billhasservices;

	//bi-directional many-to-one association to Campaign
	@JsonIgnore
	@OneToMany(mappedBy="shopinfo")
	private List<Campaign> campaigns;

	//bi-directional many-to-one association to Customerinfo
	@JsonIgnore
	@OneToMany(mappedBy="shopinfo")
	private List<Customerinfo> customerinfos;

	//bi-directional many-to-one association to Salonservice
	@JsonIgnore
	@OneToMany(mappedBy="shopinfo")
	private List<Salonservice> salonservices;

	//bi-directional many-to-many association to Moduleinfo
	@JsonIgnore
	@ManyToMany
	@JoinTable(
		name="shopinfo_has_moduleinfo"
		, joinColumns={
			@JoinColumn(name="idShopInfo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idModuleInfo")
			}
		)
	private List<Moduleinfo> moduleinfos;

	//bi-directional many-to-one association to Shopinfo
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="parentShop")
	private Shopinfo shopinfo;

	//bi-directional many-to-one association to Shopinfo
	@JsonIgnore
	@OneToMany(mappedBy="shopinfo")
	private List<Shopinfo> shopinfos;

	//bi-directional many-to-one association to Shoptype
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="type")
	private Shoptype shoptype;

	//bi-directional many-to-one association to Staffinfo
	@JsonIgnore
	@OneToMany(mappedBy="shopinfo")
	private List<Staffinfo> staffinfos;

	public Shopinfo() {
	}

	public int getIdShopInfo() {
		return this.idShopInfo;
	}

	public void setIdShopInfo(int idShopInfo) {
		this.idShopInfo = idShopInfo;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone1() {
		return this.phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return this.phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public List<Appointment> getAppointments() {
		return this.appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Appointment addAppointment(Appointment appointment) {
		getAppointments().add(appointment);
		appointment.setShopinfo(this);

		return appointment;
	}

	public Appointment removeAppointment(Appointment appointment) {
		getAppointments().remove(appointment);
		appointment.setShopinfo(null);

		return appointment;
	}

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	public Bill addBill(Bill bill) {
		getBills().add(bill);
		bill.setShopinfo(this);

		return bill;
	}

	public Bill removeBill(Bill bill) {
		getBills().remove(bill);
		bill.setShopinfo(null);

		return bill;
	}

	public List<Billhasservice> getBillhasservices() {
		return this.billhasservices;
	}

	public void setBillhasservices(List<Billhasservice> billhasservices) {
		this.billhasservices = billhasservices;
	}

	public Billhasservice addBillhasservice(Billhasservice billhasservice) {
		getBillhasservices().add(billhasservice);
		billhasservice.setShopinfo(this);

		return billhasservice;
	}

	public Billhasservice removeBillhasservice(Billhasservice billhasservice) {
		getBillhasservices().remove(billhasservice);
		billhasservice.setShopinfo(null);

		return billhasservice;
	}

	public List<Campaign> getCampaigns() {
		return this.campaigns;
	}

	public void setCampaigns(List<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	public Campaign addCampaign(Campaign campaign) {
		getCampaigns().add(campaign);
		campaign.setShopinfo(this);

		return campaign;
	}

	public Campaign removeCampaign(Campaign campaign) {
		getCampaigns().remove(campaign);
		campaign.setShopinfo(null);

		return campaign;
	}

	public List<Customerinfo> getCustomerinfos() {
		return this.customerinfos;
	}

	public void setCustomerinfos(List<Customerinfo> customerinfos) {
		this.customerinfos = customerinfos;
	}

	public Customerinfo addCustomerinfo(Customerinfo customerinfo) {
		getCustomerinfos().add(customerinfo);
		customerinfo.setShopinfo(this);

		return customerinfo;
	}

	public Customerinfo removeCustomerinfo(Customerinfo customerinfo) {
		getCustomerinfos().remove(customerinfo);
		customerinfo.setShopinfo(null);

		return customerinfo;
	}

	public List<Salonservice> getSalonservices() {
		return this.salonservices;
	}

	public void setSalonservices(List<Salonservice> salonservices) {
		this.salonservices = salonservices;
	}

	public Salonservice addSalonservice(Salonservice salonservice) {
		getSalonservices().add(salonservice);
		salonservice.setShopinfo(this);

		return salonservice;
	}

	public Salonservice removeSalonservice(Salonservice salonservice) {
		getSalonservices().remove(salonservice);
		salonservice.setShopinfo(null);

		return salonservice;
	}

	public List<Moduleinfo> getModuleinfos() {
		return this.moduleinfos;
	}

	public void setModuleinfos(List<Moduleinfo> moduleinfos) {
		this.moduleinfos = moduleinfos;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

	public List<Shopinfo> getShopinfos() {
		return this.shopinfos;
	}

	public void setShopinfos(List<Shopinfo> shopinfos) {
		this.shopinfos = shopinfos;
	}

	public Shopinfo addShopinfo(Shopinfo shopinfo) {
		getShopinfos().add(shopinfo);
		shopinfo.setShopinfo(this);

		return shopinfo;
	}

	public Shopinfo removeShopinfo(Shopinfo shopinfo) {
		getShopinfos().remove(shopinfo);
		shopinfo.setShopinfo(null);

		return shopinfo;
	}

	public Shoptype getShoptype() {
		return this.shoptype;
	}

	public void setShoptype(Shoptype shoptype) {
		this.shoptype = shoptype;
	}

	public List<Staffinfo> getStaffinfos() {
		return this.staffinfos;
	}

	public void setStaffinfos(List<Staffinfo> staffinfos) {
		this.staffinfos = staffinfos;
	}

	public Staffinfo addStaffinfo(Staffinfo staffinfo) {
		getStaffinfos().add(staffinfo);
		staffinfo.setShopinfo(this);

		return staffinfo;
	}

	public Staffinfo removeStaffinfo(Staffinfo staffinfo) {
		getStaffinfos().remove(staffinfo);
		staffinfo.setShopinfo(null);

		return staffinfo;
	}

}