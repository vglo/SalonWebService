package com.habbib.sms.service;

import java.io.File;
import java.util.Date;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.bouncycastle.cms.Recipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
  
    @Autowired
    public JavaMailSender emailSender;
    
    @Autowired
    private HTMLEmailBody htmlService;
 
    
    //Sending mail without attachment
    public void sendSimpleMessage(
      String to, String subject, String text) {
        
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setFrom("agrawaly52@gmail.com");
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
        
    }
    
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
}