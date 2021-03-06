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

import com.google.common.collect.ImmutableList;
import com.habbib.dao.JPArepository.RoleRepository;
import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.JPArepository.StaffInfoRepository;
import com.habbib.dao.JPArepository.UserCrendentialRepository;
import com.habbib.dao.entitiy.QStaffinfo;
import com.habbib.dao.entitiy.Role;
import com.habbib.dao.entitiy.Shopinfo;
import com.habbib.dao.entitiy.Staffinfo;
import com.habbib.dao.entitiy.Usercredential;
import com.habbib.dao.model.StaffCrendentialRequest;
import com.habbib.dao.model.StaffUpdatedCredential;
import com.habbib.dao.model.StaffinfoRequest;
import com.habbib.dao.service.DBService;
import com.querydsl.core.types.Predicate;

/**
 * @author yash
 *
 */
@RestController
@RequestMapping("/dao")
public class StaffDBController {

	@Autowired
	private UserCrendentialRepository userCrendentialRepo;
	
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
	
	@RequestMapping(path="find-staff/username",method=RequestMethod.GET)
	public Staffinfo findStaffByUsername(@RequestParam String username,@RequestParam String password) {
		QStaffinfo staff  =  QStaffinfo.staffinfo;
		Optional<Usercredential> user = userCrendentialRepo.findByUsername(username);
		Predicate predicate;
		if(user.isPresent()) {
			 predicate = staff.usercredential.eq(user.get());
		}else {
			 predicate = staff.mobile.eq(username).or(staff.email.eq(username));
		}
		Iterable<Staffinfo> staffList = staffInfo.findAll(predicate);
		List<Staffinfo> mutableList = ImmutableList.copyOf(staffList);
		if(mutableList != null && mutableList.size()>0)
			return mutableList.get(0);  
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
		  QStaffinfo qSatff = QStaffinfo.staffinfo;
		  Optional<Role> role = roleInfo.findById(roleId);
		  Optional<Shopinfo> shop = shopInfo.findById(shopid);
		  if(role != null && shop != null) {
			Predicate predicate = qSatff.roles.contains(role.get()).and(qSatff.shopinfo.eq(shop.get()));
			Iterable<Staffinfo> staffList = staffInfo.findAll(predicate);
			List<Staffinfo> mutableList = ImmutableList.copyOf(staffList);
			return mutableList;  
		  }
		  return null;
		  
	  }
	 
	@RequestMapping(path="/register-staffDetails",method=RequestMethod.POST)
	public Staffinfo registerStaffInfo(@RequestBody StaffCrendentialRequest crendential,@RequestParam String salt) {
		if(crendential == null && salt == null)
			throw new NullPointerException();
		
		Staffinfo staffDetails = dbService.convertstaffCredentialModelToEntity(crendential);
		Usercredential user = new Usercredential();
		user.setPassword(crendential.getPassword());
		user.setSalt(salt);
		user.setUsername(crendential.getUserName());
		staffDetails.setUsercredential(user);
		Staffinfo staff = staffInfo.save(staffDetails);
		return staff;
	}
	

	@RequestMapping(path="/update-staff/credentials",method=RequestMethod.PUT)
	public Staffinfo saveStaffCredential(@RequestBody StaffUpdatedCredential staffCredential) {
	
		Optional<Staffinfo> staffOptional = staffInfo.findById(staffCredential.getStaffId());
		if(staffOptional.isPresent()) {
			Staffinfo staff = staffOptional.get();
			Usercredential user = new Usercredential();
			user.setUsername(staffCredential.getUsername());
			user.setPassword(staffCredential.getPassword());
			user.setSalt(staffCredential.getSalt());
			staff.setUsercredential(user);
			Staffinfo newStaff = staffInfo.save(staff);
			return newStaff;
		}
		return null;
	}
	
	@RequestMapping(path="/check-staff/credential",method=RequestMethod.GET)
	public boolean checkStaffCredentials(@RequestParam int idStaffInfo) {
		Optional<Staffinfo> staff = staffInfo.findById(idStaffInfo);
		if(null != staff.get().getUsercredential())
			return true;
		return false;
	}
	
}
