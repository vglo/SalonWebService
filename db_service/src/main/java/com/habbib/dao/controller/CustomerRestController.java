package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.CustomerInfoRepository;
import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.entitiy.Customerinfo;
import com.habbib.dao.entitiy.Shopinfo;
import com.habbib.dao.model.CustomerRequest;
import com.habbib.dao.service.DBService;

@RestController
@RequestMapping(value="/dao")
public class CustomerRestController {

	@Autowired
	private CustomerInfoRepository customerInfo;
	
	@Autowired
	private ShopInfoRepository shopInfo;
	
	@Autowired
	private DBService dbService;
	
	@RequestMapping(path="/save-customer",method=RequestMethod.POST)
	public Customerinfo saveCustomer(@RequestBody CustomerRequest cust) {
		Customerinfo custInfo = dbService.convertModelToEntityCustomer(cust);
		Customerinfo cust1 = customerInfo.save(custInfo);
		return cust1;
	}
	
	@RequestMapping(path="/delete-customer/{id}", method=RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable("id") int id) {
		customerInfo.deleteById(id);
	}
	
	@RequestMapping(path="/fetch/all-customer", method=RequestMethod.GET)
	public List<Customerinfo> findAllCustomer() {
		List<Customerinfo> customerList = customerInfo.findAll();
		return customerList;
	}
	
	@RequestMapping(path="/fetch/customerById/{id}", method=RequestMethod.GET)
	public Optional<Customerinfo> findByCustId(@PathVariable("id") int customerId) {
		Optional<Customerinfo> customer =  customerInfo.findById(customerId);
		return customer;
	}
	
	@RequestMapping(path="/update-customer",method=RequestMethod.PUT)
	public Customerinfo uddateCustomer(@RequestBody Customerinfo customer) {
		Customerinfo updatedCustomer = customerInfo.save(customer);
		return updatedCustomer;
	}
	
	@RequestMapping(path="/find-by-shop-id/{shopId}", method=RequestMethod.GET)
	public List<Customerinfo> findCustByShopId(@PathVariable int shopId) {
		Shopinfo shop = shopInfo.getOne(shopId);
		List<Customerinfo> customerList = customerInfo.findByShopinfo(shop);
		return customerList;
	}
	
	@RequestMapping(path="/find-by-mobile/{mobileNum}",method=RequestMethod.GET)
	public List<Customerinfo> findByCustomerMob(@PathVariable String mobileNum){
		List<Customerinfo> custList = customerInfo.findByMobile(mobileNum);
		return custList;
	}
}
