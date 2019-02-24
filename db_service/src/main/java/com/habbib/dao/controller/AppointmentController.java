/**
 * 
 */
package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.CustAppoitmentRepository;
import com.habbib.dao.JPArepository.CustomerInfoRepository;
import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.entitiy.Appointment;
import com.habbib.dao.entitiy.Customerinfo;
import com.habbib.dao.entitiy.Shopinfo;
import com.habbib.dao.model.AppointmentRequest;
import com.habbib.dao.service.DBService;

/**
 * @author yash
 *
 */
@RestController
@RequestMapping("/dao")
public class AppointmentController {

	@Autowired
	private CustAppoitmentRepository custAppointment;
	
	@Autowired
	private ShopInfoRepository shopRespo;
	
	@Autowired
	private CustomerInfoRepository custRepo;
	
	@Autowired
	private DBService dbService;
	
	@RequestMapping(path="/fetch-appoitment/shop-id",method=RequestMethod.GET)
	public List<Appointment> fetchAppointmentByshopId(@RequestParam int shopId) {
		Optional<Shopinfo> shopinfo = shopRespo.findById(shopId);
		List<Appointment> appointList = custAppointment.findByShopinfo(shopinfo.get());
		return appointList;
	}
	
	@RequestMapping(path="/find-appointment/custmer-id",method=RequestMethod.GET)
	public List<Appointment> fetchByCustomerId(@RequestParam int custid){
		Optional<Customerinfo> custinfo = custRepo.findById(custid);
		List<Appointment> appointList = custAppointment.findByCustomerinfo(custinfo.get());
		return appointList;
	}
	
	@RequestMapping(path="/create-appointment",method=RequestMethod.POST)
	public Appointment saveAppointment(@RequestBody AppointmentRequest appointmentReq) {
		Appointment appointment = dbService.convertAppoitmentEntityToModel(appointmentReq);
		Appointment reminder = custAppointment.save(appointment);
		return reminder;
	}
	
	@RequestMapping(path="/fetch-all",method=RequestMethod.GET)
	public List<Appointment> fetchAllAppoitment(){
		List<Appointment> appoitList = custAppointment.findAll();
		return appoitList;
	}
}
