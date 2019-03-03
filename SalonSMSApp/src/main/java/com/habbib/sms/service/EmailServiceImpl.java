package com.habbib.sms.service;

import java.util.Date;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.habbib.sms.response.model.Bill;
import com.habbib.sms.response.model.Customerinfo;

@Service
public class EmailServiceImpl implements EmailService {
  
    @Autowired
    public JavaMailSender emailSender;
    
    @Autowired
    private HTMLEmailBody htmlService;
 
    
    //Sending email with attachments
    public void sendMessageWithAttachment(
      String to, String subject, String text) {
        // ...
        try {
        MimeMessage message = emailSender.createMimeMessage();
        message.setFrom();
        message.addRecipient(Message.RecipientType.TO,  new InternetAddress(to));
        message.setSubject("Here is your Bill Summary");
        message.setSentDate(new Date());
        message.setContent(htmlService.createEmailBody(), "text/html");
        emailSender.send(message);
        // ...
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }

	@Override
	public boolean sendSimpleMessage(String toEmailId, Bill bill, Customerinfo customerinfo) throws MailException,MailParseException,MailSendException{
	   try {
		SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(toEmailId); 
        message.setFrom("agrawaly52@gmail.com");
        message.setSubject("Thanks for visiting Habbib Salon"); 
        message.setText(buildMailBody(bill, customerinfo));
        emailSender.send(message);
	   }catch(Exception e) {
		   e.getMessage();
		   return false;
	   }
		return true;
	}
	
	private String buildMailBody(Bill bill, Customerinfo customer) {
		String heading = "Hi "+customer.getFirstName();
		String space = "\n";
		String body = "Thanks for using our service, your bill total is: "+bill.getGrandTotal();
		String price = "Price break up: ";
		String gst = "CGST: "+bill.getCsgtVal()+"/n SGST: "+bill.getSgstVal();
		String discount = "Discount: "+bill.getDiscountVal();
		String totalService = "Service Chanrge: "+bill.getTotal();
		String grandTotal = "Grand Total: "+bill.getGrandTotal();
		return heading.concat(space).concat(body).concat(space).concat(price).concat(space).concat(gst).concat(space).concat(discount).concat(space).concat(totalService).concat(space).concat(grandTotal);
	}
}