/**
 * 
 */
package com.habbib.staff.feign.client.fallback;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.habbib.staff.feign.clients.DBServiceFeignClient;
import com.habbib.staff.request.model.StaffCrendentialRequest;
import com.habbib.staff.request.model.StaffUpdatedCredential;
import com.habbib.staff.request.model.StaffinfoRequest;
import com.habbib.staff.response.model.Role;
import com.habbib.staff.response.model.Shopinfo;
import com.habbib.staff.response.model.Staffinfo;
import com.habbib.staff.response.model.Usercredential;

/**
 * @author yash
 *
 */
@Component
public class DBServiceFallback implements DBServiceFeignClient {

	

	@Override
	public Staffinfo saveStaffInfo(StaffinfoRequest staffInfoReq) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Staffinfo> findStaffByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Staffinfo> validateStaff(String mobileNum, int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Staffinfo findStaffByid(int staffId,int shopid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Staffinfo findStaffByEmail(String email, int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Staffinfo> findStaffByRole(int roleId, int shopid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Shopinfo> findByShopId(int shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findRoleByid(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Staffinfo updateStaff(StaffinfoRequest staffInfoReq, int staffId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStaff(int staffId, int shopId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Staffinfo registerStaffInfo(StaffCrendentialRequest staff, String salt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Staffinfo findStaffByUsername(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Staffinfo saveStaffCredential(StaffUpdatedCredential staffCredential) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> findRoleListByIds(List<Integer> rolesId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkUsername(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usercredential findStaffCredentials(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkStaffCredentials(int idStaffInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changePassword(StaffUpdatedCredential newCredentials) {
		// TODO Auto-generated method stub
		return false;
	}

}
