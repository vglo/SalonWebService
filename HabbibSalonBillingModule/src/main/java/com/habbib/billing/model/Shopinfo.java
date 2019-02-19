package com.habbib.billing.model;

import java.io.Serializable;
import java.util.List;


public class Shopinfo  {

	private int idShopInfo;

	private String address;

	private String name;

	private String phone1;

	private String phone2;

	private List<Bill> bills;

	private List<Salonservice> salonservices;

	private List<Moduleinfo> moduleinfos;

	private Shopinfo shopinfo;

	private List<Shopinfo> shopinfos;

	private Shoptype shoptype;

	private List<Campaign> campaigns;

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

	public List<Bill> getBills() {
		return this.bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
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