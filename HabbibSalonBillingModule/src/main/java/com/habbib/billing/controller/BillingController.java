package com.habbib.billing.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.billing.dbrequest.model.BillRequest;
import com.habbib.billing.dbrequest.model.BillhasserviceRequest;
import com.habbib.billing.feign.clients.DBServiceFeignClient;
import com.habbib.billing.model.Bill;
import com.habbib.billing.model.Customerinfo;
import com.habbib.billing.model.Paymenttype;
import com.habbib.billing.model.Salonservice;
import com.habbib.billing.model.Shopinfo;
import com.habbib.billing.model.Staffinfo;
import com.habbib.billing.request.model.BillClientRequest;
import com.habbib.billing.request.model.BillHasService;
import com.habbib.billing.service.BillingService;
import com.habib.utility.DefaultMessage;



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
	private BillingService billService;
	
	

	@RequestMapping(value="/save-bill",method=RequestMethod.POST)
	@ExceptionHandler
	public ResponseEntity<DefaultMessage<Bill>> saveBill(@Valid @ModelAttribute BillClientRequest billRequest) {
		DefaultMessage<Bill> dfault = new DefaultMessage<Bill>();
		List<BillHasService> billHasServiceList = new ArrayList<BillHasService>();
		List<BillhasserviceRequest> dbBillHasServiceList = new ArrayList<BillhasserviceRequest>();
		double totalServicePay = 0;
		BillRequest dbBillRequest = new BillRequest();
		LOG.info("Inside save bill method #BillingController");
		LOG.info("start Billing #BillingController");
		
		try {
			Optional<Customerinfo> customerInfo = dbserviceFeignClient.findByCustId(billRequest.getCustomerId());
			if(!customerInfo.isPresent()) {
				dfault.setResponseCode("400");
				dfault.setResponseMessage("Customer with given id is not registered, please register the customer");
				return new ResponseEntity<DefaultMessage<Bill>>(dfault,HttpStatus.BAD_REQUEST);
			}
			dbBillRequest.setCustId(billRequest.getCustomerId());
			Optional<Shopinfo> shopInfo = dbserviceFeignClient.findByShopId(billRequest.getShopId());
			if(!shopInfo.isPresent()) {
				dfault.setResponseCode("400");
				dfault.setResponseMessage("Shop with given id is not present/registered, please enter valid shop id");
				return new ResponseEntity<DefaultMessage<Bill>>(dfault,HttpStatus.BAD_REQUEST);
			}
			dbBillRequest.setShopId(billRequest.getShopId());
			Staffinfo staff = dbserviceFeignClient.findStaffByid(billRequest.getServiceStaffId());
			if(staff == null) {
				dfault.setResponseCode("400");
				dfault.setResponseMessage("Staff with given id is not present/registered, please enter valid staff id");
				return new ResponseEntity<DefaultMessage<Bill>>(dfault,HttpStatus.BAD_REQUEST);
			}
			dbBillRequest.setServingStaff(billRequest.getServiceStaffId());
			Paymenttype paymentType = dbserviceFeignClient.fetchByPaymentTypeID(billRequest.getType());
			if(paymentType ==null) {
				dfault.setResponseCode("400");
				dfault.setResponseMessage("Payment method with given id is not avialable, please choose valid payment type");
				return new ResponseEntity<DefaultMessage<Bill>>(dfault,HttpStatus.BAD_REQUEST);
			}
			dbBillRequest.setPaymentType(billRequest.getType());
			billHasServiceList = billRequest.getBillHasService();
			for(BillHasService billHasService: billHasServiceList ) {
			Optional<Salonservice> salonService = dbserviceFeignClient.getServiceInfo(billHasService.getServiceId());
			
				if(salonService.isPresent()) {
					totalServicePay += salonService.get().getPrice()*billHasService.getQuant();
					BillhasserviceRequest dbBillHasServiceRequest = new BillhasserviceRequest();
					dbBillHasServiceRequest.setIdSalonService(billHasService.getServiceId());
					dbBillHasServiceRequest.setQuantity(billHasService.getQuant());
					dbBillHasServiceList.add(dbBillHasServiceRequest);
				}else {
					dfault.setResponseCode("404");
					dfault.setResponseMessage("Salon service with given id is not avialable, please choose active service");
					return new ResponseEntity<DefaultMessage<Bill>>(dfault,HttpStatus.NOT_FOUND);
				}
				
			}
			dbBillRequest.setTotal(totalServicePay);
			dbBillRequest.setBillhasservices(dbBillHasServiceList);
			
			LOG.info("Total Service pay is"+dbBillRequest.getTotal());
			  
			dbBillRequest = billService.setRequestToResponse(dbBillRequest, billRequest);
			  
			Bill bill = dbserviceFeignClient.saveBill(dbBillRequest);
			 
			if(null != bill) {
				 //BillHasService save
				
				 dfault.setResponseMessage("The bill saved successfully");
				 dfault.setResponseCode("200");
				 dfault.setResponse(bill);
				 return new  ResponseEntity<DefaultMessage<Bill>>(dfault,HttpStatus.OK);
				 
			}else {
				 dfault.setResponseMessage("The bill not saved");
				 dfault.setResponseCode("400");
				 dfault.setResponse(null);
				 return new  ResponseEntity<DefaultMessage<Bill>>(HttpStatus.BAD_REQUEST);
			}
				  
		}catch(Exception exception) {
			dfault.setResponseMessage(exception.getLocalizedMessage());
			dfault.setResponseCode("403");
			return new ResponseEntity<DefaultMessage<Bill>>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	
	@RequestMapping(path="/fetch-bill/date-range",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Bill>>> filterByDateRange(@RequestParam String startDate,@RequestParam String endDate,@RequestParam int shopId){
		if(startDate == null || endDate == null)
			throw new NullPointerException();
		DefaultMessage<List<Bill>> defaultResponse = new DefaultMessage<List<Bill>>();
		List<Bill> listofBills = dbserviceFeignClient.filterByDateRange(startDate, endDate,shopId);
		if(listofBills.size()>=0 && listofBills != null) {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("PLease find the list of bills from given date range");
	 		return new ResponseEntity<DefaultMessage<List<Bill>>>(defaultResponse,HttpStatus.OK);
	 		
		}else {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("404");
	 		defaultResponse.setResponseMessage("No bills are avilable for given date range");
	 		return new ResponseEntity<DefaultMessage<List<Bill>>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
 		

	}
	
	@RequestMapping(path="/fetch-bill/today",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Bill>>> getBillsOfCurrentDate(@RequestParam int shopId){
		DefaultMessage<List<Bill>> defaultResponse = new DefaultMessage<List<Bill>>();
		List<Bill> listofBills = dbserviceFeignClient.filterByDate(shopId);
		if(listofBills.size()>=0 && listofBills != null) {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("PLease find the list of bills from given date range");
	 		return new ResponseEntity<DefaultMessage<List<Bill>>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("204");
	 		defaultResponse.setResponseMessage("No bills are avilable. Bill list is empty");
	 		return new ResponseEntity<DefaultMessage<List<Bill>>>(defaultResponse,HttpStatus.NO_CONTENT);
		}
 		

	}
	
	@RequestMapping(value="/edit-bill/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void editBill(@PathVariable("id") String id) {
		
	}
	

	
	@RequestMapping(path="/fetch-bill/shop-id",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Bill>>> fetchBillByShopId(@RequestParam int shopId){
		DefaultMessage<List<Bill>> defaultResponse = new DefaultMessage<List<Bill>>();
		Optional<Shopinfo> shopInfo = dbserviceFeignClient.findByShopId(shopId);
		if(!shopInfo.isPresent()) {
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("Shop with given id is not present/registered, please enter valid shop id");
			return new ResponseEntity<DefaultMessage<List<Bill>>>(defaultResponse,HttpStatus.BAD_REQUEST);
		}
		List<Bill> listofBills = dbserviceFeignClient.findBillByShopId(shopId);
		if(listofBills.size()>=0 && listofBills != null) {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("PLease find the list of bills from given date range with count"+listofBills.size());
	 		return new ResponseEntity<DefaultMessage<List<Bill>>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("204");
	 		defaultResponse.setResponseMessage("No bills are avilable. Bill list is empty");
	 		return new ResponseEntity<DefaultMessage<List<Bill>>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
 		
	}
	
	@RequestMapping(path="/find-bill/bill-number",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Bill>> fetchBillByBillNum(@RequestParam String billNum){
		DefaultMessage<Bill> defaultResponse = new DefaultMessage<Bill>();
		Optional<Bill> listofBill = dbserviceFeignClient.findByBillNum(billNum);
		if(listofBill.isPresent()) {
			defaultResponse.setResponse(listofBill.get());
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("Bill is present with given bill Number:"+billNum);
	 		return new ResponseEntity<DefaultMessage<Bill>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(listofBill.get());
	 		defaultResponse.setResponseCode("204");
	 		defaultResponse.setResponseMessage("No bill is avilable. Bill list is empty");
	 		return new ResponseEntity<DefaultMessage<Bill>>(defaultResponse,HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(path="/find-bill/cust-id",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Bill>>> fetchBillByCustId(@RequestParam int custId){
		DefaultMessage<List<Bill>> defaultResponse = new DefaultMessage<List<Bill>>();
		Optional<Customerinfo> customerInfo = dbserviceFeignClient.findByCustId(custId);
		if(!customerInfo.isPresent()) {
			defaultResponse.setResponseCode("404");
			defaultResponse.setResponseMessage("Customer with given id is not registered, please register the customer");
			return new ResponseEntity<DefaultMessage<List<Bill>>>(defaultResponse,HttpStatus.BAD_REQUEST);
		}
		List<Bill> listofBills = dbserviceFeignClient.fetchBillByCustId(custId);
		if(listofBills.size()>=0 && listofBills != null) {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("PLease find the list of bills with given customer id, count:" + listofBills.size());
	 		return new ResponseEntity<DefaultMessage<List<Bill>>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("204");
	 		defaultResponse.setResponseMessage("No bills are avilable. Bill list is empty");
	 		return new ResponseEntity<DefaultMessage<List<Bill>>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
 		
	}
	
	@RequestMapping(path="/find-bill/bill-id",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Bill>> fetchBillById(@RequestParam int billId){
		DefaultMessage<Bill> defaultResponse = new DefaultMessage<Bill>();
		Optional<Bill> listofBill = dbserviceFeignClient.findByBillId(billId);
		if(listofBill.isPresent()) {
			defaultResponse.setResponse(listofBill.get());
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("Bill is present with given bill Number:"+billId);
	 		return new ResponseEntity<DefaultMessage<Bill>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(listofBill.get());
	 		defaultResponse.setResponseCode("204");
	 		defaultResponse.setResponseMessage("No bill is avilable. Bill list is empty");
	 		return new ResponseEntity<DefaultMessage<Bill>>(defaultResponse,HttpStatus.NO_CONTENT);
		}
	}
	
}
