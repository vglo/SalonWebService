/**
 * 
 */
package com.habbib.dao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.PaymentTypeRepository;
import com.habbib.dao.entitiy.Paymenttype;

/**
 * @author yash
 *
 */
@RestController
@RequestMapping("/dao")
public class PaymentTypeController {

	@Autowired
	private PaymentTypeRepository paymentRepo;
	
	@RequestMapping(path="/fetch-payment-types",method=RequestMethod.GET)
	public List<Paymenttype> fetchAllPaymentType() {
		List<Paymenttype> paymentList = paymentRepo.findAll();
		return paymentList;
	}
	
	@RequestMapping(path="/fetch-by-shop-id",method=RequestMethod.GET)
	public List<Paymenttype> fetchPaymentTypeByShopId(int shopId) {
		List<Paymenttype> paymentList = paymentRepo.findByshopId(shopId);
		return paymentList;
	}
	
	@RequestMapping(path="/fetch-all/type/{type}",method=RequestMethod.GET)
	public List<Paymenttype> fetchByType(@PathVariable String type ){
		List<Paymenttype> allPayment= paymentRepo.findByType(type);
		return allPayment;
	}
	
	@RequestMapping(path="/save-payment" ,method=RequestMethod.POST)
	public Paymenttype savePaymentType(@RequestBody Paymenttype paymentType) {
		Paymenttype payments = paymentRepo.save(paymentType);
		return payments;
	}
	
}
