package com.habbib.billing.model;

import java.io.Serializable;
import java.util.Date;
public class Campaign implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idCampaign;

	private int campaignId;

	private String description;

	private double discountPer;

	private Date endDate;

	private int servingStaff;

	private Date startDate;

	private String title;

	private Shopinfo shopinfo;

	public Campaign() {
	}

	public int getIdCampaign() {
		return this.idCampaign;
	}

	public void setIdCampaign(int idCampaign) {
		this.idCampaign = idCampaign;
	}

	public int getCampaignId() {
		return this.campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getDiscountPer() {
		return this.discountPer;
	}

	public void setDiscountPer(double discountPer) {
		this.discountPer = discountPer;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getServingStaff() {
		return this.servingStaff;
	}

	public void setServingStaff(int servingStaff) {
		this.servingStaff = servingStaff;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Shopinfo getShopinfo() {
		return this.shopinfo;
	}

	public void setShopinfo(Shopinfo shopinfo) {
		this.shopinfo = shopinfo;
	}

}