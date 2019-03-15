package com.habbib.sms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.sms.feign.client.DBServiceFeignClient;
import com.habbib.sms.request.model.EmailParam;
import com.habbib.sms.response.model.Bill;
import com.habbib.sms.response.model.Customerinfo;
import com.habbib.sms.service.EmailService;
import com.habib.utility.DefaultMessage;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@RequestMapping("/sms")
public class SMSEmailSenderController {
	
	@Autowired
	private DBServiceFeignClient dbClient;
	
	 // Find your Account Sid and Auth Token at twilio.com/console
	 public static final String ACCOUNT_SID =
	         "AC6473884ac01b46b52848df1488331013";
	 public static final String AUTH_TOKEN =
	         "c3bc1e508862197ab51f008f57788bdf";

	 @Autowired
	 private EmailService emailService;
	 
	
	 //to send sms notification
	@RequestMapping(value="/sms-sender")
	public String SmsSender() {
	
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		
		  Message message = Message .creator(new PhoneNumber("+918208386061"),
		  new PhoneNumber("+13347216334"), "Where's Wallace?") .create();
		 
		/*
		 * Message message = Message.creator( new
		 * com.twilio.type.PhoneNumber("whatsapp:+917276050048"), new
		 * com.twilio.type.PhoneNumber("whatsapp:+917507222645"), "Hello there!")
		 * .create();
		 */
	     return message.getSid();
	}
	
	
	@RequestMapping(value="/email-sender", method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<String>> emailSenderWithoutAttch(@ModelAttribute EmailParam emailContent) {
		
		DefaultMessage<String> dfaultMsg = new DefaultMessage<String>();
		Optional<Bill> bill = dbClient.findByBillId(emailContent.getBillId(), emailContent.getShopId());
		String successMsg = "Inside the email sender method";
		Optional<Customerinfo> customer = dbClient.findByCustId(emailContent.getCustId(), emailContent.getShopId());
		
		if(bill.isPresent() && customer.isPresent()) {
				successMsg = emailService.sendMessageWithAttachment(bill.get(), customer.get());
				dfaultMsg.setResponseCode("200");
				dfaultMsg.setResponseMessage("Email send succuessFully");
				dfaultMsg.setResponse(successMsg);
				return new ResponseEntity<DefaultMessage<String>>(dfaultMsg,HttpStatus.OK);
			
		}
		dfaultMsg.setResponseCode("500");
		dfaultMsg.setResponseMessage("Error occured");
		dfaultMsg.setResponse(successMsg);
		return new ResponseEntity<DefaultMessage<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//to reciev any msg
	@RequestMapping(value="/email",method=RequestMethod.GET)
	public void SmsApp() {
		//emailService.sendMessageWithAttachment("agrawaly52@gmail.com");
	}

}
