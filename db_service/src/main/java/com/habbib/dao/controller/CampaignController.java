package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.CampaignRepository;
import com.habbib.dao.JPArepository.ModuleInfoRepository;
import com.habbib.dao.entitiy.Campaign;
import com.habbib.dao.entitiy.Moduleinfo;

@RestController
@RequestMapping(path="/dao")
public class CampaignController {

	@Autowired
	private CampaignRepository campaignInfo;
	
	@RequestMapping(path="/save-campaign-detail")
	public void saveCampaignDetails(@RequestBody Campaign campaign) {
		
		campaignInfo.save(campaign);
	}
	
	@RequestMapping(path="/delete-campaign/{id}")
	public void deletecampaign(@PathVariable("id") int id) {
		campaignInfo.deleteById(id);
	}
	
	@RequestMapping(path="/fetch/all-campaign")
	public List<Campaign> findAllCampaigns() {
		List<Campaign> campaignList = campaignInfo.findAll();
		return campaignList;
	}
	
	@RequestMapping(path="/fetch/campaignById/{id}")
	public Optional<Campaign> findByCampaignId(@PathVariable("id") int campaignId) {
		Optional<Campaign> campaign =  campaignInfo.findById(campaignId);
		return campaign;
	}
	
	@RequestMapping(path="/update-campaign")
	public void uddateCampaign(@RequestBody Campaign campaign) {
		campaignInfo.save(campaign);
	}
	
	
}
