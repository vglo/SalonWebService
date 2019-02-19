package com.habbib.billing.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.billing.feign.clients.DBServiceFeignClient;
import com.habbib.billing.feign.clients.SmsServiceFeignClient;
import com.habbib.billing.model.Bill;
import com.habbib.billing.model.Billhasservice;
import com.habbib.billing.model.Customerinfo;
import com.habbib.billing.model.Salonservice;
import com.habbib.billing.response.DefaultMessage;
import com.habbib.billing.service.Billing;
import com.habbib.billing.util.DateAndTimeUtil;


/*
 * Billing controller 
 * @Author Yash Agrawal
 * 
 */
@RestController
@RequestMapping("billing")
public class BillingController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BillingController.class);
	
	@Autowired
	private DBServiceFeignClient dbserviceFeignClient;
	
	@Autowired
	private Billing billing;
	
	@Autowired
	private SmsServiceFeignClient smsService;
	
	@Autowired
	private DateAndTimeUtil utilObj;
	
	//private BillingValidation billValidate;
	
	@RequestMapping(value="/save-bill",method=RequestMethod.POST)
	@ExceptionHandler
	public ResponseEntity<DefaultMessage<Bill>> saveBill(@RequestBody Bill bill) {
		DefaultMessage<Bill> dfault = new DefaultMessage<Bill>();
		List<Integer> billServiceIds = new ArrayList<Integer>();
		List<Billhasservice> serviceList = new ArrayList<Billhasservice>();
		double totalBillAfterGST =0;
		Map<Optional<Salonservice>, Integer> serviceQuantityMap = new HashMap<Optional<Salonservice>, Integer>();
		try {
		LOG.info("Inside save bill method #BillingController");
		LOG.info("start Billing #BillingController");
		
		serviceList = bill.getBillHasService();
		for(Billhasservice billHasService: serviceList ) {
		Optional<Salonservice> serviceInfoList = dbserviceFeignClient.getServiceInfo(billHasService.getServiceId());
			//Map for storing key as serviceInfo and Value as quantity
			if(null != serviceInfoList) 
			serviceQuantityMap.put(serviceInfoList, billHasService.getQuantity());
		}
		bill.setTotal(billing.calculateBill(serviceQuantityMap));
		LOG.info("Total Service pay is"+bill.getTotal());
		
		if(0 != bill.getCgstPer() && 0 != bill.getSgstPer())
		{
			LOG.info("GST calculation");
			bill.setSgstVal(bill.getTotal()*bill.getSgstPer()/100);
			bill.setCsgtVal(bill.getTotal()*bill.getCgstPer()/100);
			bill.setGrandTotal(bill.getTotal()+bill.getSgstVal() + bill.getCsgtVal()); 
		}
		if(0 != bill.getDiscountPer()) {
			LOG.info("DiscountCalculation "+totalBillAfterGST);
			bill.setDiscountVal(bill.getTotal()*(bill.getDiscountPer()/100));
			bill.setGrandTotal(bill.getGrandTotal() - bill.getDiscountVal());
		}	
		
		if(0 != bill.getGrandTotal()) {
			bill.setDate(utilObj.formateDate());
			bill.setTime(LocalTime.now().toString());
			bill.setBillNo(utilObj.generateBillNumber());
			dbserviceFeignClient.saveBill(bill);
		}
		 Optional<Bill> checkNullBill =  
	                 dbserviceFeignClient.findByBillNum(bill.getBillNo());   
		 if(checkNullBill.isPresent()) {
			 dfault.setResponseCode("200");
			 dfault.setResponseMessage("The bill generated successfully");
			 dfault.setResponse(checkNullBill.get());
			 ResponseEntity<DefaultMessage<Bill>> responseEntity = ResponseEntity.ok(dfault);
			 Optional<Customerinfo> custObj = dbserviceFeignClient.findByCustId(bill.getCustomerId());
			 if(custObj.isPresent()) {
				 String msg = "Thank You For Visiting our Salon"+custObj.get().getFirstName()+"\n We Hoped you like our service.\n Total: "+bill.getGrandTotal()+"\n Please Visit Again. Thanks";
				// smsService.emailSenderWithoutAttch(custObj.get().getEmail(),msg , "Your Invoice of current purchase is");
			 }
			 return responseEntity;
			 
		 }else {
			 dfault.setResponseCode("400");
			 dfault.setResponseMessage("The bill not generated");
			 dfault.setResponse(null);
			 ResponseEntity<DefaultMessage<Bill>> responseEntity = ResponseEntity.ok(dfault);
			 return responseEntity;	
		 }
		  
				  
		}catch(Exception exception) {
			dfault.setResponseMessage(exception.getLocalizedMessage());
			//return ResponseEntity.badRequest();
		}
		return null;
	}
	
	/*
	 * 
	 */
	@RequestMapping(path="/fetch-salon-services/{id}", method=RequestMethod.GET)
	public ResponseEntity<List<Salonservice>> fetchAllSalonService(@PathVariable("id") int shopId){
		List<Salonservice> listOfServices = dbserviceFeignClient.getSalonServices(shopId);
		if (null != listOfServices && listOfServices.size()>0) {
			ResponseEntity<List<Salonservice>> responseEntity = ResponseEntity.ok(listOfServices);
			return responseEntity;
		}
		return null;
	}
	
	
	@RequestMapping(path="/fetch-customer-list/{shopId}", method=RequestMethod.GET)
	public ResponseEntity<List<Customerinfo>> fetchAllCustomerByShopId(@PathVariable int shopId){
		List<Customerinfo> customerList = dbserviceFeignClient.findByShopId(shopId);
		ResponseEntity<List<Customerinfo>> responseEntity = ResponseEntity.ok(customerList);
		return responseEntity;
	}
	
	
	@RequestMapping(value="/edit-bill/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void editBill(@PathVariable("id") String id) {
		
	}
	
}
