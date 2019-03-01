/**
 * 
 */
package com.habbib.pdf.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.pdf.feign.client.DBServiceFeignClient;
import com.habbib.pdf.model.Bill;
import com.habbib.pdf.model.Billhasservice;
import com.habbib.pdf.model.Customerinfo;
import com.habbib.pdf.model.Shopinfo;
import com.habbib.pdf.request.model.PDFRequest;
import com.habbib.pdf.service.PDFCreator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @author yash
 *
 */
@RestController
@RequestMapping("/pdf")
public class PDFGenerator {

	@Autowired
	private PDFCreator pdfService;
	
	@Autowired
	private DBServiceFeignClient dbClient;
	
	private static final String TITLE = "TestReport";
	public static final String PDF_EXTENSION = ".pdf";

	@RequestMapping(path="/create-pdf",method=RequestMethod.POST)  
	public void getBillContent(@ModelAttribute PDFRequest pdfRequest) {
		 Document document = new Document();
		try {
			Optional<Customerinfo> cust = dbClient.findByCustId(pdfRequest.getCustomerId());
			Optional<Shopinfo> shop = dbClient.findByShopId(pdfRequest.getShopId());
			Optional<Bill> bill = dbClient.findByBillId(pdfRequest.getBillId());
			//Document is not auto-closable hence need to close it separately
			if(shop.isPresent() && bill.isPresent() && cust.isPresent()) {
				 PdfWriter.getInstance(document, new FileOutputStream(TITLE+PDF_EXTENSION));
		         document.open();
		        
		        PDFCreator.createBillReport(document, cust.get(),shop.get(),bill.get());	
		        
		        List<Billhasservice> billhasServiceList = dbClient.fetchBillhasServiceByBillId(bill.get().getIdBill());
		        bill.get().setBillHasService(billhasServiceList);
		        
		        
		        PDFCreator.createBillTable(document,bill.get());
			}
	        
	    }catch (DocumentException | FileNotFoundException e) {
	        e.printStackTrace();
	        System.out.println("FileNotFoundException occurs.." + e.getMessage());
	    }finally{
	        if(null != document){
	            document.close();
	      }
    }
         

	}	

    
}