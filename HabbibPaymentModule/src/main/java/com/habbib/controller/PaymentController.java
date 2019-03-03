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
	 		return new ResponseEntity<DefaultMessage<List<Paymenttype>>>(defaultResponse,HttpStatus.OK);
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
	public ResponseEntity<DefaultMessage<Boolean>> deletePaymentType(@RequestParam(required=true) int paymentTypeId,@RequestParam(required=true) int shopId){
		DefaultMessage<Boolean> defaultMsg= new DefaultMessage<Boolean>();
		try {
			dbserviceFeignClient.deletePayment(paymentTypeId, shopId);
		}catch (Exception e) {
			defaultMsg.setResponse(false);
			defaultMsg.setResponseCode("400");
			defaultMsg.setResponseMessage("error occured in delete the staff"+e.getLocalizedMessage());
			return new ResponseEntity<DefaultMessage<Boolean>>(defaultMsg,HttpStatus.BAD_REQUEST);
		}
		defaultMsg.setResponse(true);
		defaultMsg.setResponseCode("200");
		defaultMsg.setResponseMessage("staff deleted with given id");
		return new ResponseEntity<DefaultMessage<Boolean>>(defaultMsg,HttpStatus.OK);
	}
	
	@RequestMapping(path="/update-type",method=RequestMethod.PUT)
	public ResponseEntity<DefaultMessage<Paymenttype>> updatePaymentType(@ModelAttribute PaymentTypeRequest paymentType,@RequestParam(required=true) int paymentId){
		DefaultMessage<Paymenttype> defaultResponse = new DefaultMessage<Paymenttype>();
		Paymenttype type = dbserviceFeignClient.fetchByPaymentTypeID(paymentId, paymentType.getShopId());
		if(type != null) {
			Paymenttype newpaymentType = dbserviceFeignClient.savePaymentType(paymentType);
			defaultResponse.setResponse(newpaymentType);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage(" payment type updated ");
	 		return new ResponseEntity<DefaultMessage<Paymenttype>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(type);
	 		defaultResponse.setResponseCode("200");
	 		defaultResponse.setResponseMessage("payment type not updated");
	 		return new ResponseEntity<DefaultMessage<Paymenttype>>(defaultResponse,HttpStatus.OK);
		}
	}

	
	@RequestMapping(path="/find-type/type",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Paymenttype>> findPaymentTypeByType(@RequestParam String type){
		DefaultMessage<Paymenttype> defaultResponse = new DefaultMessage<Paymenttype>();
		Paymenttype newpaymentType = dbserviceFeignClient.fetchByType(type);
		if(newpaymentType != null) {
			defaultResponse.setResponse(newpaymentType);
	 		defaultResponse.setResponseCode("302");
	 		defaultResponse.setResponseMessage("PLease find the list of  payment type");
	 		return new ResponseEntity<DefaultMessage<Paymenttype>>(defaultResponse,HttpStatus.OK);
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
	 		return new ResponseEntity<DefaultMessage<List<Paymenttype>>>(defaultResponse,HttpStatus.OK);
		}else {
			defaultResponse.setResponse(paymentTypeList);
	 		defaultResponse.setResponseCode("404");
	 		defaultResponse.setResponseMessage("payment type list is empty");
	 		return new ResponseEntity<DefaultMessage<List<Paymenttype>>>(defaultResponse,HttpStatus.NOT_FOUND);
		}
 		

	}
}
