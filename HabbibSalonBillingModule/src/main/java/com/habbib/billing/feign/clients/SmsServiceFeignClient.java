package com.habbib.billing.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("sms-service")
public interface SmsServiceFeignClient {


	
	@RequestMapping(value="/email-sender/{toEmail}/{msg}/{Subject}", method=RequestMethod.GET)
	public void emailSenderWithoutAttch(@PathVariable String toEmail, @PathVariable String msg,@PathVariable  String Subject) ;
	
}
