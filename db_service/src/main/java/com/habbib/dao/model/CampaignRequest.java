package com.habbib.dao.model;

import java.util.Date;


/**
 * The persistent class for the campaign database table.
 * 
 */

public class CampaignRequest{

	private int idCampaign;

	private int campaignId;

	private String description;

	private double discountPer;

	private Date endDate;

	private int servingStaff;

	private Date startDate;

	private String title;

	
	private int shopId;

	public CampaignRequest() {
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

	/**
	 * @return the shopId
	 */
	public int getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	

}