package com.habbib.sms.service;

import java.io.File;

import javax.mail.internet.MimeMessage;

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
      String to, String subject, String text, String pathToAttachment) {
        // ...
        try {
        MimeMessage message = emailSender.createMimeMessage();
          
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
         
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);
             
        FileSystemResource file 
          = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);
     
        emailSender.send(message);
        // ...
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }
}