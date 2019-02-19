package com.habbib.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.sms.service.EmailService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
@RequestMapping("/sms")
public class SMSEmailSenderController {
	
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
	
	@RequestMapping(value="/email-sender/{toEmail}/{msg}/{Subject}", method=RequestMethod.GET)
	public void emailSenderWithoutAttch(@PathVariable String toEmail, @PathVariable String msg,@PathVariable  String Subject) {
		emailService.sendSimpleMessage(toEmail, Subject, msg);
	}
	
	
	//to reciev any msg
	@RequestMapping(value="/sms-app")
	public void SmsApp() {
		
		
	}

}
