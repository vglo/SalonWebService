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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.billing.dbrequest.model.BillhasserviceRequest;
import com.habbib.billing.feign.clients.DBServiceFeignClient;
import com.habbib.billing.feign.clients.SmsServiceFeignClient;
import com.habbib.billing.model.Bill;
import com.habbib.billing.model.Paymenttype;
import com.habbib.billing.model.Salonservice;
import com.habbib.billing.request.model.BillHasService;
import com.habbib.billing.request.model.BillRequest;

import com.habbib.billing.service.Billing;
import com.habbib.billing.util.DateAndTimeUtil;
import com.habbib.utility.DefaultMessage;



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
	public ResponseEntity<DefaultMessage<Bill>> saveBill(@ModelAttribute BillRequest billRequest) {
		DefaultMessage<Bill> dfault = new DefaultMessage<Bill>();
		List<BillHasService> serviceList = new ArrayList<BillHasService>();
		com.habbib.billing.dbrequest.model.BillRequest billResponse = new com.habbib.billing.dbrequest.model.BillRequest();
		List<BillhasserviceRequest> billHasServiceList = new ArrayList<BillhasserviceRequest>();
		
		double totalBillAfterGST =0;
		Map<Optional<Salonservice>, Integer> serviceQuantityMap = new HashMap<Optional<Salonservice>, Integer>();
		try {
		LOG.info("Inside save bill method #BillingController");
		LOG.info("start Billing #BillingController");
		
		serviceList = billRequest.getBillHasService();
		for(BillHasService billHasService: serviceList ) {
		Optional<Salonservice> serviceInfoList = dbserviceFeignClient.getServiceInfo(billHasService.getServiceId());
		
		//Map for storing key as serviceInfo and Value as quantity
			if(null != serviceInfoList) {
				for(BillhasserviceRequest addObject : billHasServiceList) {
					addObject.setIdSalonService(serviceInfoList.get().getIdSalonService());
					addObject.setQuantity(billHasService.getQuant());
					billHasServiceList.add(addObject);
				}
				serviceQuantityMap.put(serviceInfoList, billHasService.getQuant());
			}
			
		}
		billResponse.setTotal(billing.calculateBill(serviceQuantityMap));
		billResponse.setBillhasservices(billHasServiceList);
		LOG.info("Total Service pay is"+billResponse.getTotal());
		
		
		if(0 != billRequest.getCgstPer() && 0 != billRequest.getSgstPer())
		{
			LOG.info("GST calculation");
			billResponse.setSgstVal(billResponse.getTotal()*billRequest.getSgstPer()/100);
			billResponse.setCsgtVal(billResponse.getTotal()*billRequest.getCgstPer()/100);
			billResponse.setGrandTotal(billResponse.getTotal()+billResponse.getSgstVal() + billResponse.getCsgtVal()); 
		}
		if(0 != billRequest.getDescountPer()) {
			LOG.info("DiscountCalculation "+totalBillAfterGST);
			billResponse.setDiscountVal(billResponse.getTotal()*(billRequest.getDescountPer()/100));
			billResponse.setGrandTotal(billResponse.getGrandTotal() - billResponse.getDiscountVal());
		}	
		
		if(0 != billResponse.getGrandTotal()) {
			billResponse.setDate(utilObj.formateDate());
			billResponse.setTime(LocalTime.now().toString());
			billResponse.setBillNo(utilObj.generateBillNumber());
			billResponse.setIdCustomerInfo(billRequest.getCustomerId());
			billResponse.setIdPaymentType(billRequest.getType());
			billResponse.setIdShopInfo(billRequest.getShopId());
			billResponse.setIdStaffInfo(billRequest.getServiceStaffId());
			
		}
		Bill bill = dbserviceFeignClient.saveBill(billResponse);
		 if(null != bill) {
			 dfault.setResponseCode("200");
			 dfault.setResponseMessage("The bill generated successfully");
			 dfault.setResponse(bill);
			 ResponseEntity<DefaultMessage<Bill>> responseEntity = ResponseEntity.ok(dfault);
			 return responseEntity;
			 
		 }else {
			 dfault.setResponseCode("400");
			 dfault.setResponseMessage("The bill not generated");
			 dfault.setResponse(null);
			 ResponseEntity<DefaultMessage<Bill>> responseEntity = ResponseEntity.ok(dfault);
			 return responseEntity;	
		 }
		  
				  
		}catch(Exception exception) {
			dfault.setResponseCode("201");
			dfault.setResponseMessage(exception.getLocalizedMessage());
			
			//return ResponseEntity.badRequest();
		}
		ResponseEntity<DefaultMessage<Bill>> responseEntity = ResponseEntity.ok(dfault);
		return responseEntity;
	}
	
	
	/*
	 * 
	 */
	@RequestMapping(path="/fetch-salon-services", method=RequestMethod.GET)
	public ResponseEntity<List<Salonservice>> fetchAllSalonService(@RequestParam(value="shopId", required=true) int shopId){
		List<Salonservice> listOfServices = dbserviceFeignClient.getSalonServices(shopId);
		if (null != listOfServices && listOfServices.size()>0) {
			ResponseEntity<List<Salonservice>> responseEntity = ResponseEntity.ok(listOfServices);
			return responseEntity;
		}
		return null;
	}
	
	
	@RequestMapping(path="/fetch-bill/date-range",method=RequestMethod.GET)
	public DefaultMessage<List<Bill>> filterByDateRange(@RequestParam String startDate,@RequestParam String endDate,@RequestParam int shopId){
		if(startDate == null || endDate == null)
			throw new NullPointerException();
		DefaultMessage<List<Bill>> defaultResponse = new DefaultMessage<List<Bill>>();
		List<Bill> listofBills = dbserviceFeignClient.filterByDateRange(startDate, endDate,shopId);
		if(listofBills.size()>=0 && listofBills != null) {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("PLease find the list of bills from given date range");
	 		
		}else {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("No bills are avilable for given date range");
		}
 		
		return defaultResponse;
	}
	
	
	@RequestMapping(path="/fetch-bill/today",method=RequestMethod.GET)
	public DefaultMessage<List<Bill>> getBillsofToday(@RequestParam int shopId){
		DefaultMessage<List<Bill>> defaultResponse = new DefaultMessage<List<Bill>>();
		List<Bill> listofBills = dbserviceFeignClient.filterByDate(shopId);
		if(listofBills.size()>=0 && listofBills != null) {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("PLease find the list of bills from given date range");
	 		
		}else {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("No bills are avilable. Bill list is empty");
		}
 		
		return defaultResponse;

	}
	
	
	@RequestMapping(value="/edit-bill/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public void editBill(@PathVariable("id") String id) {
		
	}
	
	@RequestMapping(path="/fetch-payment-type",method=RequestMethod.GET)
	public DefaultMessage<List<Paymenttype>> fetchPaymentTypes() {
		DefaultMessage<List<Paymenttype>> defaultResponse = new DefaultMessage<List<Paymenttype>>();
		List<Paymenttype> paymentTypeList = dbserviceFeignClient.fetchAllPaymentType();
		if(paymentTypeList.size()>=0 && paymentTypeList != null) {
			defaultResponse.setResponse(paymentTypeList);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("PLease find the list of all payment types");
	 		
		}else {
			defaultResponse.setResponse(paymentTypeList);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("payment type list is empty");
		}
 		
		return defaultResponse;

	}
	
	@RequestMapping(path="/fetch-bill/shop-id",method=RequestMethod.GET)
	public DefaultMessage<List<Bill>> fetchBillByShopId(@RequestParam int shopId){
		DefaultMessage<List<Bill>> defaultResponse = new DefaultMessage<List<Bill>>();
		List<Bill> listofBills = dbserviceFeignClient.findBillByShopId(shopId);
		if(listofBills.size()>=0 && listofBills != null) {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("PLease find the list of bills from given date range");
	 		
		}else {
			defaultResponse.setResponse(listofBills);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("No bills are avilable. Bill list is empty");
		}
 		
		return defaultResponse;
	}
	
}
