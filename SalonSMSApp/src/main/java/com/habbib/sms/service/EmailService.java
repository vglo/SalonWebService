package com.habbib.sms.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

	public void sendSimpleMessage(
		      String to, String subject, String text);
	
	  public void sendMessageWithAttachment(
		      String to, String subject, String text, String pathToAttachment);
}
