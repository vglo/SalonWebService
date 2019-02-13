package com.habbib.billing.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Billing")
public class BillingController {
	
	
	@RequestMapping(value="/CreateBill")
	public String MakeBill() {
		
		return "New Billing module";
	}
	
	@RequestMapping(value="/editBill/{id}")
	public String editBill(@PathVariable("id") String id) {
		return "Edit bill" + id  + "New  id ";
	}
	
}
