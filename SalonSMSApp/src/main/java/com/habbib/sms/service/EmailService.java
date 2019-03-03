package com.habbib.sms.service;

import org.springframework.stereotype.Service;

import com.habbib.sms.response.model.Bill;
import com.habbib.sms.response.model.Customerinfo;

@Service
public interface EmailService {

	
	  public void sendMessageWithAttachment(
		      String to, String subject, String text);

	public boolean sendSimpleMessage(String toEmailId, Bill bill, Customerinfo customerinfo);
}
