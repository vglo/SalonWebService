package com.habbib.sms.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

	     Message message = Message
	             .creator(new PhoneNumber("+918208386061"), // to
	                     new PhoneNumber("+13347216334"), // from
	                     "Where's Wallace?")
	             .create();

	     return message.getSid();
	}
	
	@RequestMapping(value="/email-sender", method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<String>> emailSenderWithoutAttch(@ModelAttribute EmailParam emailContent) {
		if(emailContent.getToEmailId() == null)
			throw new NullPointerException();
		
		DefaultMessage<String> dfaultMsg = new DefaultMessage<String>();
		Optional<Bill> bill = dbClient.findByBillId(emailContent.getBillId(), emailContent.getShopId());
		
		Optional<Customerinfo> customer = dbClient.findByCustId(emailContent.getCustId(), emailContent.getShopId());
		
		if(bill.isPresent() && customer.isPresent()) {
			boolean mailSend = emailService.sendSimpleMessage(emailContent.getToEmailId(),bill.get(),customer.get());
			if(mailSend) {
				dfaultMsg.setResponseCode("200");
				dfaultMsg.setResponseMessage("Email send succuessFully");
				
				return new ResponseEntity<DefaultMessage<String>>(HttpStatus.OK);
			}else {
				dfaultMsg.setResponseCode("400");
				dfaultMsg.setResponseMessage("Email not send");
				
				return new ResponseEntity<DefaultMessage<String>>(HttpStatus.BAD_REQUEST);
			}
		}
		dfaultMsg.setResponseCode("500");
		dfaultMsg.setResponseMessage("Error occured");
		
		return new ResponseEntity<DefaultMessage<String>>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//to reciev any msg
	@RequestMapping(value="/email",method=RequestMethod.GET)
	public void SmsApp() {
		emailService.sendMessageWithAttachment("agrawaly52@gmail.com", "Order", "Please find the bill");
	}

}
