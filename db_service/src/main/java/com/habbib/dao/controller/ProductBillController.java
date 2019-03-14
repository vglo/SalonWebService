package com.habbib.dao.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.entitiy.Productbill;

@RestController
@RequestMapping("dao")
public class ProductBillController {
	
	/*
	 * @Autowired private ProductBillRepository productBillRepo;
	 */
	@RequestMapping(path="/save-product-bill",method=RequestMethod.POST)
	public Productbill saveProductBill() {
		return null;
		
	}
	
	@RequestMapping(path="/fetch-product-bill/id",method=RequestMethod.GET)
	public Productbill fetchProductBillById(@RequestParam int billId,@RequestParam int shopId) {
		return null;
		
	}
	
	@RequestMapping(path="/fetch-product-bills/shop-id",method=RequestMethod.GET)
	public List<Productbill> fetchBillsByShopId(@RequestParam int shopId){
		return null;
		
	}
	
	@RequestMapping(path="/fetch-product-bill/bill-num",method=RequestMethod.GET)
	public Productbill fetchProductBillByBillNum(@RequestParam String billNumber,@RequestParam int shopId) {
		return null;
		
	}
	
	@RequestMapping(path="/fetch-product-bills/cust-id",method=RequestMethod.GET)
	public List<Productbill> fetchProductBillsByCustId(@RequestParam int custId) {
		return null;
		
	}
	
	@RequestMapping(path="/filter-product-bills/date-range",method=RequestMethod.GET)
	public List<Productbill> filterByDateRange(@RequestParam String startDate,@RequestParam String endDate,@RequestParam int shopId){
		return null;
		
	}
	
	@RequestMapping(path="/filter-product-bill/date",method=RequestMethod.GET)
	public List<Productbill> filterByDate(@RequestParam int shopId){
		return null;
			}
}

