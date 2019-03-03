/**
 * 
 */
package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.RoleRepository;
import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.JPArepository.StaffInfoRepository;
import com.habbib.dao.entitiy.Role;
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
	private RoleRepository roleInfo;
	
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
	
	@RequestMapping(path="/find-staff/{staffId}", method=RequestMethod.GET)
	public Staffinfo findStaffByid(@PathVariable int staffId,@RequestParam int shopId){
		Shopinfo shop = shopInfo.getOne(shopId);
		Optional<Staffinfo> staffinfo = staffInfo.findByIdStaffInfoAndShopinfo(staffId, shop);
		if(staffinfo.isPresent())
			return staffinfo.get();
		return null;
	}
	
	@RequestMapping(path="/update-staff",method=RequestMethod.PUT)
	public Staffinfo updateStaff(@RequestBody StaffinfoRequest staffInfoReq,@RequestParam int staffId) {
		if(staffInfoReq == null)
			throw new NullPointerException();
		
		Staffinfo staffDetails = dbService.convertstaffModelToEntity(staffInfoReq);
		staffDetails.setIdStaffInfo(staffId);
		Staffinfo staff = staffInfo.save(staffDetails);
		return staff;
	}
	
	@RequestMapping(path="/delete-staff",method=RequestMethod.DELETE)
	public void deleteStaff(@RequestParam int staffId,@RequestParam int shopId) {
		try {
		Shopinfo shop = shopInfo.getOne(shopId);
		Optional<Staffinfo> staffinfo = staffInfo.findByIdStaffInfoAndShopinfo(staffId, shop);
		if(staffinfo.isPresent())
			staffInfo.delete(staffinfo.get());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping(path="/find-staff/email",method=RequestMethod.GET)
	public Staffinfo findStaffByEmail(@RequestParam String email,@RequestParam int shopId) {
		Shopinfo shop = shopInfo.getOne(shopId);
		Optional<Staffinfo> staffinfo = staffInfo.findByEmailAndShopinfo(email, shop);
		if(staffinfo.isPresent())
			return staffinfo.get();
		return null;
	}
	
	@RequestMapping(path="/find-staff/roles",method=RequestMethod.GET)
	public List<Staffinfo> findStaffByRole(@RequestParam int roleId,@RequestParam int shopid){
		Shopinfo shop = shopInfo.getOne(shopid);
		Role role = roleInfo.getOne(roleId);
		List<Staffinfo> staffList = staffInfo.findByRoleBeanAndShopinfo(role, shop);
		if(staffList != null)
			return staffList;
		return null;
		
	}
}
