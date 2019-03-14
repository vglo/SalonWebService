package com.habbib.staff.feign.clients;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.habbib.staff.feign.client.fallback.DBServiceFallback;
import com.habbib.staff.request.model.StaffCrendentialRequest;
import com.habbib.staff.request.model.StaffUpdatedCredential;
import com.habbib.staff.request.model.StaffinfoRequest;
import com.habbib.staff.response.model.Role;
import com.habbib.staff.response.model.Shopinfo;
import com.habbib.staff.response.model.Staffinfo;
import com.habbib.staff.response.model.Usercredential;


@FeignClient(name="db-service", fallback = DBServiceFallback.class)
@RibbonClient(name="db-service")
public interface DBServiceFeignClient {
	
	@RequestMapping(path="/dao/find-staff/mobile&shopId",method=RequestMethod.GET)
	public Optional<Staffinfo> validateStaff(@RequestParam String mobileNum,@RequestParam int shopId);
	
	@RequestMapping(path="/dao/register-staffDetails",method=RequestMethod.POST)
	public Staffinfo registerStaffInfo(@RequestBody StaffCrendentialRequest crendential,@RequestParam String salt);
	
	@RequestMapping(path="/dao/save-staff",method=RequestMethod.POST)
	public Staffinfo saveStaffInfo(@RequestBody StaffinfoRequest staffInfoReq);
	
	@RequestMapping(path="/dao/find-staff/shop-id",method=RequestMethod.GET)
	public List<Staffinfo> findStaffByShopId(@RequestParam int shopId);
	
	@RequestMapping(path="/dao/find-staff/{staffId}", method=RequestMethod.GET)
	public Staffinfo findStaffByid(@PathVariable int staffId,@RequestParam int shopId);
	
	@RequestMapping(path="/dao/update-staff",method=RequestMethod.PUT)
	public Staffinfo updateStaff(@RequestBody StaffinfoRequest staffInfoReq,@RequestParam int staffId);
	
	@RequestMapping(path="/dao/delete-staff",method=RequestMethod.DELETE)
	public void deleteStaff(@RequestParam int staffId,@RequestParam int shopId);
	
	@RequestMapping(path="/dao/find-staff/email",method=RequestMethod.GET)
	public Staffinfo findStaffByEmail(@RequestParam String email,@RequestParam int shopId);
	
	@RequestMapping(path="/dao/find-staff/roles",method=RequestMethod.GET)
	public List<Staffinfo> findStaffByRole(@RequestParam int roleId,@RequestParam int shopid);
	
	@RequestMapping(path="/dao/fetch/shopById/{shopId}",method=RequestMethod.GET)
	public Optional<Shopinfo> findByShopId(@PathVariable() int shopId);
	
	@RequestMapping(path="/dao/find-role/{id}",method=RequestMethod.GET)
	public Role findRoleByid(@PathVariable int id);
	
	@RequestMapping(path="/dao/find-staff/username",method=RequestMethod.GET)
	public Staffinfo findStaffByUsername(@RequestParam String username,@RequestParam String password);

	@RequestMapping(path="/dao/update-staff/credentials",method=RequestMethod.PUT)
	public Staffinfo saveStaffCredential(@RequestBody StaffUpdatedCredential staffCredential);
	
	@RequestMapping(path="/dao/find-role-list",method=RequestMethod.GET)
	public List<Role> findRoleListByIds(@RequestParam List<Integer> rolesId);

	@RequestMapping(path="/dao/check-username",method=RequestMethod.GET)
	public boolean checkUsername(@RequestParam String userName);

	@RequestMapping(path="/dao/find-credentials",method=RequestMethod.GET)
	public Usercredential findStaffCredentials(@RequestParam String username);

	@RequestMapping(path="/dao/check-staff/credential",method=RequestMethod.GET)
	public boolean checkStaffCredentials(@RequestParam int idStaffInfo);
	
	@RequestMapping(path="/dao/change-pass",method=RequestMethod.POST)
	public boolean changePassword(@RequestBody StaffUpdatedCredential newCredentials);
	
}


