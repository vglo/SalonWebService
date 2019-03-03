/**
 * 
 */
package com.habbib.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.habbib.feign.client.fallback.DBServiceFallback;
import com.habbib.request.model.PaymentTypeRequest;
import com.habbib.response.model.Paymenttype;

/**
 * @author yash
 *
 */
@FeignClient(name="db-service", fallback = DBServiceFallback.class)
public interface DBServiceFeignClient {

	@RequestMapping(path="/dao/fetch-payment-types",method=RequestMethod.GET)
	public List<Paymenttype> fetchAllPaymentType();
	
	@RequestMapping(path="/fetch/payment-type/{id}",method=RequestMethod.GET)
	public Paymenttype fetchByPaymentTypeID(@PathVariable int id,@RequestParam int shopId);
	
	@RequestMapping(path="/dao/fetch-all/type",method=RequestMethod.GET)
	public Paymenttype fetchByType(@RequestParam String type );
	
	@RequestMapping(path="/dao/save-payment" ,method=RequestMethod.POST)
	public Paymenttype savePaymentType(@RequestBody PaymentTypeRequest paymentType);
	
	@RequestMapping(path="/dao/fetch-by-shop-id",method=RequestMethod.GET)
	public List<Paymenttype> fetchPaymentTypeByShopId(@RequestParam  int shopId);
	
	@RequestMapping(path="/dao/update-payment",method=RequestMethod.PUT)
	public Paymenttype updatePayment(@RequestBody PaymentTypeRequest paymentReq,@RequestParam int paymentId);
	
	@RequestMapping(path="/dao/delete-payment",method=RequestMethod.DELETE)
	public void deletePayment(@RequestParam int paymentId,@RequestParam int shopId);

}
