/**
 * 
 */
package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.JPArepository.StaffInfoRepository;
import com.habbib.dao.entitiy.Shopinfo;
import com.habbib.dao.entitiy.Staffinfo;
import com.habbib.dao.model.StaffinfoRequest;
import com.habbib.dao.service.DBService;

/**
 * @author yash
 *
 */
@RestController
@RequestMapping("/dao")
public class StaffDBController {

	@Autowired
	private ShopInfoRepository shopInfo;
	
	@Autowired
	private StaffInfoRepository staffInfo;
	
	@Autowired
	private DBService dbService;
	
	@RequestMapping(path="/find-staff/mobile&shopId",method=RequestMethod.GET)
	public Optional<Staffinfo> validateStaff(@RequestParam String mobileNum,@RequestParam int shopId){
		if(shopId == 0 || mobileNum == null)
			throw new NullPointerException();
		
		Shopinfo shop = shopInfo.getOne(shopId);
		if(shop != null) {
			Optional<Staffinfo> staffList = staffInfo.findByMobileAndShopinfo(mobileNum,shop);
			return staffList;
		}
		return null;
		
	}
	
	@RequestMapping(path="/save-staff",method=RequestMethod.POST)
	public Staffinfo saveStaffInfo(@RequestBody StaffinfoRequest staffInfoReq) {
		if(staffInfoReq == null)
			throw new NullPointerException();
		
		Staffinfo staffDetails = dbService.convertstaffModelToEntity(staffInfoReq);
		Staffinfo staff = staffInfo.save(staffDetails);
		return staff;
	}
	
	
	@RequestMapping(path="/find-staff/shop-id",method=RequestMethod.GET)
	public List<Staffinfo> findStaffByShopId(@RequestParam int shopId){
		Shopinfo shop = shopInfo.getOne(shopId);
		if(shop != null) {
			List<Staffinfo> staffList = staffInfo.findByShopinfo(shop);
			return staffList;
		}
		return null;
	}
}
