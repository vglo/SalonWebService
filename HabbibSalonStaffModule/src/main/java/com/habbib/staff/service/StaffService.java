/**
 * 
 */
package com.habbib.staff.service;

import java.security.NoSuchAlgorithmException;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.habbib.staff.config.PasswordEncoder;
import com.habbib.staff.request.model.StaffCrendentialRequest;
import com.habbib.staff.request.model.StaffUpdatedCredential;

/**
 * @author yash
 *
 */
@Service
public class StaffService {
	
	
	public StaffCrendentialRequest  generateCredential(StaffCrendentialRequest inputCredential,String salt) throws NoSuchAlgorithmException {
		
		inputCredential.setPassword(BCrypt.hashpw(inputCredential.getPassword(), salt));
		return inputCredential;
	}

	public StaffUpdatedCredential generateStaffUpdatedReq(StaffCrendentialRequest staffCredential, int idStaffInfo) throws NoSuchAlgorithmException {
		String salt = PasswordEncoder.getSalt();
		StaffUpdatedCredential staff = new StaffUpdatedCredential();
		staff.setStaffId(idStaffInfo);
		staff.setPassword(BCrypt.hashpw(staffCredential.getPassword(), salt));
		staff.setUsername(staffCredential.getUserName());
		staff.setSalt(salt);
		
		return staff;
	}

	
}
