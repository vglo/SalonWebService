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

import com.habbib.dao.JPArepository.PaymentTypeRepository;
import com.habbib.dao.entitiy.Paymenttype;
import com.habbib.dao.model.PaymenttypeRequest;
import com.habbib.dao.service.DBService;

/**
 * @author yash
 *
 */
@RestController
@RequestMapping("/dao")
public class PaymentTypeController {

	@Autowired
	private PaymentTypeRepository paymentRepo;
	
	@Autowired
	private DBService dbService;
	
	@RequestMapping(path="/fetch-payment-types",method=RequestMethod.GET)
	public List<Paymenttype> fetchAllPaymentType() {
		List<Paymenttype> paymentList = paymentRepo.findAll();
		return paymentList;
	}
	
	@RequestMapping(path="/fetch-by-shop-id",method=RequestMethod.GET)
	public List<Paymenttype> fetchPaymentTypeByShopId(@RequestParam int shopId) {
		List<Paymenttype> paymentList = paymentRepo.findByshopId(shopId);
		return paymentList;
	}
	
	@RequestMapping(path="/fetch-all/type",method=RequestMethod.GET)
	public Paymenttype fetchByType(@RequestParam String type ){
		Optional<Paymenttype> allPayment= paymentRepo.findByType(type);
		if(allPayment.isPresent())
			return allPayment.get();
		return null;
	}
	
	
	@RequestMapping(path="/fetch/payment-type/{id}",method=RequestMethod.GET)
	public Paymenttype fetchByPaymentTypeID(@PathVariable int id,@RequestParam int shopId) {
		Optional<Paymenttype> paymentType = paymentRepo.findByIdPaymentTypeAndShopId(id, shopId);
		if(paymentType.isPresent())
			return paymentType.get();
		return null;
	}
	
	@RequestMapping(path="/save-payment" ,method=RequestMethod.POST)
	public Paymenttype savePaymentType(@RequestBody PaymenttypeRequest paymentReq) {
		Paymenttype paymentType = dbService.convertPaymentModelToEntity(paymentReq);
		Paymenttype payments = paymentRepo.save(paymentType);
		return payments;
	}
	
	@RequestMapping(path="/update-payment",method=RequestMethod.PUT)
	public Paymenttype updatePayment(@RequestBody PaymenttypeRequest paymentReq,@RequestParam int paymentId) {
		Paymenttype paymentType = dbService.convertPaymentModelToEntity(paymentReq);
		paymentType.setIdPaymentType(paymentId);
		Paymenttype payments = paymentRepo.save(paymentType);
		return payments;
	}
	
	@RequestMapping(path="/delete-payment",method=RequestMethod.DELETE)
	public void deletePayment(@RequestParam int paymentId,@RequestParam int shopId) {
		Optional<Paymenttype> paymentType = paymentRepo.findByIdPaymentTypeAndShopId(paymentId, shopId);
		if(paymentType.isPresent())
			paymentRepo.delete(paymentType.get());
	}
}
