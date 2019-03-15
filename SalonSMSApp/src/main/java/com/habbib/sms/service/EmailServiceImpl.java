package com.habbib.sms.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
    public String sendMessageWithAttachment(Bill bill,Customerinfo customer) {
        // ...
    	String response  = "Started api call";
        try {
        if(customer.getMobile() != null) {
        	response = sendSms(customer.getMobile(),bill.getGrandTotal(),bill.getBillNo());
            	
           }
        MimeMessage message = emailSender.createMimeMessage();
        
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        if(customer.getEmail() != null) {
        	helper.setTo(new InternetAddress(customer.getEmail()));
            //  helper.setCc(new InternetAddress("agrawaly52@gmail.com"));
              helper.setSubject("Thanks for visting Habbib salon");
              helper.setText("<html><body style='background-color:#F7F6F6;'><img src='cid:habbiblogo'><br><h2>Hi "
              		+ customer.getFirstName()+" "+customer.getLastName()+",</h2>"
              		+ "Visited Habbib Salon on "+LocalDate.now()+"<br><h4>Your Bill Summary with bill number: "+bill.getBillNo()+"</h4><h1>Total ₹"+bill.getGrandTotal()+"</h1>"
              		+ "<br><br><h3>Salon service total: "+bill.getTotal()+"</h3>"
              		+ "<br>CGST(9%): "+bill.getCsgtVal()
              		+ "<br>SGST(9%): "+bill.getSgstVal()
              		+ "<br>Discount: "+bill.getDiscountVal()
              		+ "<br><br>Amount charged: "+bill.getGrandTotal()
              		+ "<br> <p>paid in cash.</p><br><p><h5>Thanks,<br>Habbib Salon</h5></p></body></html>", true);
              String fileName = "config/habbib_logo.jpeg";
              
              ClassLoader classLoader = ClassLoader.getSystemClassLoader();
              FileSystemResource file = new FileSystemResource(new File(classLoader.getResource(fileName).getFile()));
              helper.addInline("habbiblogo", file);
              
              emailSender.send(message);
              
        }
       
        }catch(Exception e) {
        	return response+"error in email send "+e;
        }
		return "success";
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
	
	private String sendSms(String mobNumber,double BillAmount,String billNumber) {
		try {
			// Construct data
			String apiKey = "apikey=" + "VcQ7xubEFEg-6ufPP2KeYvVVJWAaiQyRH4a1IsemKK";
			String message = "&message=" + "Thanks for visiting habbib Salon, Your bill with bill number : "+billNumber+" and Your bill amount is:"+BillAmount;
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + mobNumber;
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			return "Error "+e;
		}
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