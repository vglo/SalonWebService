/**
 * 
 */
package com.habbib.pdf.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
			Optional<Customerinfo> cust = dbClient.findByCustId(pdfRequest.getCustomerId(),pdfRequest.getShopId());
			Optional<Shopinfo> shop = dbClient.findByShopId(pdfRequest.getShopId());
			Optional<Bill> bill = dbClient.findByBillId(pdfRequest.getBillId(), pdfRequest.getShopId());
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

	@ResponseBody
	@RequestMapping(value = "get/pdf", headers="Accept=*/*", method = RequestMethod.POST)
	public ResponseEntity<InputStreamResource> getPdfContractById(@ModelAttribute PDFRequest pdfRequest) throws IOException{
	        // Get the remove file based on the fileaddress
			ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	        // Set the input stream
	        InputStream inputstream = classLoader.getResourceAsStream("resources/invoice.pdf");
	        // asume that it was a PDF file
	        HttpHeaders responseHeaders = new HttpHeaders();
	        InputStreamResource inputStreamResource = new InputStreamResource(inputstream);
	        responseHeaders.setContentLength(inputStreamResource.contentLength());
	        responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
	        return new ResponseEntity<InputStreamResource> (inputStreamResource,
	                                   responseHeaders,
	                                   HttpStatus.OK);
	}
}