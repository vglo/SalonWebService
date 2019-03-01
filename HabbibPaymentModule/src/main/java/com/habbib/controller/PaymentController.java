/**
 * 
 */
package com.habbib.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.feign.client.DBServiceFeignClient;
import com.habbib.request.model.PaymentTypeRequest;
import com.habbib.response.model.Paymenttype;
import com.habib.utility.DefaultMessage;

/**
 * @author yash
 *
 */
@RestController
@RequestMapping("payment")
public class PaymentController {
	
	@Autowired
	private DBServiceFeignClient dbserviceFeignClient;
	
	@RequestMapping(path="/find-payments/shop",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Paymenttype>>> getPaymentTypeByShopId(@RequestParam(required=true) int shopId){
		DefaultMessage<List<Paymenttype>> defaultResponse = new DefaultMessage<List<Paymenttype>>();
		List<Paymenttype> paymentTypeList = dbserviceFeignClient.fetchPaymentTypeByShopId(shopId);
		if(paymentTypeList.size()>=0 && paymentTypeList != null) {
			defaultResponse.setResponse(paymentTypeList);
	 		defaultResponse.setResponseCode("302");
	 		defaultResponse.setResponseMessage("PLease find the list of all payment types");
	 		return new ResponseEntity<DefaultMessage<List<Paymenttype>>>(defaultResponse,HttpStatus.FOUND);
		}else {
			defaultResponse.setResponse(paymentTypeList);
	 		defaultResponse.setResponseCode("204");
	 		defaultResponse.setResponseMessage("payment type list is empty");
	 		return new ResponseEntity<DefaultMessage<List<Paymenttype>>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
 		
	}
	
	@RequestMapping(path="/save-type",method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<Paymenttype>> saveNewPaymentType(@Valid @ModelAttribute PaymentTypeRequest paymentType ) {
		DefaultMessage<Paymenttype> defaultResponse = new DefaultMessage<Paymenttype>();
		Paymenttype newpaymentType = dbserviceFeignClient.savePaymentType(paymentType);
		if(newpaymentType != null) {
			defaultResponse.setResponse(newpaymentType);
	 		defaultResponse.setResponseCode("201");
	 		defaultResponse.setResponseMessage("PLease find the list of all payment types");
	 		return new ResponseEntity<DefaultMessage<Paymenttype>>(defaultResponse,HttpStatus.CREATED);
		}else {
			defaultResponse.setResponse(newpaymentType);
	 		defaultResponse.setResponseCode("400");
	 		defaultResponse.setResponseMessage("payment type list is empty");
	 		return new ResponseEntity<DefaultMessage<Paymenttype>>(defaultResponse,HttpStatus.BAD_REQUEST);
		}
 		
	}
	
	@RequestMapping(path="/delete-type",method=RequestMethod.DELETE)
	public ResponseEntity<DefaultMessage<Boolean>> deletePaymentType(@RequestParam(required=true) int paymentTypeId){
		return null;
		
	}
	
	@RequestMapping(path="/update-type",method=RequestMethod.PUT)
	public ResponseEntity<DefaultMessage<Paymenttype>> updatePaymentType(@ModelAttribute Paymenttype paymentType){
		return null;
		
	}

	
	@RequestMapping(path="/find-type/type",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Paymenttype>> findPaymentTypeByType(@RequestParam String type){
		DefaultMessage<Paymenttype> defaultResponse = new DefaultMessage<Paymenttype>();
		Paymenttype newpaymentType = dbserviceFeignClient.fetchByType(type);
		if(newpaymentType != null) {
			defaultResponse.setResponse(newpaymentType);
	 		defaultResponse.setResponseCode("302");
	 		defaultResponse.setResponseMessage("PLease find the list of  payment type");
	 		return new ResponseEntity<DefaultMessage<Paymenttype>>(defaultResponse,HttpStatus.FOUND);
		}else {
			defaultResponse.setResponse(newpaymentType);
	 		defaultResponse.setResponseCode("404");
	 		defaultResponse.setResponseMessage("payment type list is empty");
	 		return new ResponseEntity<DefaultMessage<Paymenttype>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path="/fetch-payment-type",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Paymenttype>>> fetchPaymentTypes() {
		DefaultMessage<List<Paymenttype>> defaultResponse = new DefaultMessage<List<Paymenttype>>();
		List<Paymenttype> paymentTypeList = dbserviceFeignClient.fetchAllPaymentType();
		if(paymentTypeList.size()>=0 && paymentTypeList != null) {
			defaultResponse.setResponse(paymentTypeList);
	 		defaultResponse.setResponseCode("302");
	 		defaultResponse.setResponseMessage("PLease find the list of all payment types");
	 		return new ResponseEntity<DefaultMessage<List<Paymenttype>>>(defaultResponse,HttpStatus.FOUND);
		}else {
			defaultResponse.setResponse(paymentTypeList);
	 		defaultResponse.setResponseCode("404");
	 		defaultResponse.setResponseMessage("payment type list is empty");
	 		return new ResponseEntity<DefaultMessage<List<Paymenttype>>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
 		

	}
}
