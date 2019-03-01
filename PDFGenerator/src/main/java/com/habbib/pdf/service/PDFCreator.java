/**
 * 
 */
package com.habbib.pdf.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habbib.pdf.feign.client.DBServiceFeignClient;
import com.habbib.pdf.model.Bill;
import com.habbib.pdf.model.Billhasservice;
import com.habbib.pdf.model.Customerinfo;
import com.habbib.pdf.model.Salonservice;
import com.habbib.pdf.model.Shopinfo;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;
/**
 * This is to create a PDF file.
 */

/**
 * @author yash
 *
 */
@Service
public class PDFCreator {


	
    private final static String[] HEADER_ARRAY = {"S.No.", "CompanyName", "Income", "Year"};
    private final static String[] HEADER_BILL = {"CUSTOMER DETAIL", "STAFF DETAIL"};
    private final static String[] HEADER_BILL_SUMMARY = {"ServiceName", "Unit Price", "Quantity","TotalAmount"};
    public final static Font SMALL_BOLD = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    public final static Font NORMAL_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 8,
            Font.NORMAL);
    
    public static void addMetaData(Document document, String sqlXMLFileName) {
        document.addTitle("Sample Report");
        document.addSubject("Using iText");
        document.addAuthor("Arun");
    }
    
    public static void addContent(Document document, List<Customerinfo> dataObjList) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(NORMAL_FONT);
        createReportTable(paragraph, dataObjList);
        document.add(paragraph);
    }
    
    public static void createBillReport(Document document, Customerinfo cust,Shopinfo shopDetail,Bill bill)  throws DocumentException{
    	Paragraph headerpreface = new Paragraph();
		/*
		 * CMYKColor magentaColor = new CMYKColor(0.f, 1.f, 0.f, 0.f);
		 * canvas.setColorStroke(magentaColor); canvas.moveTo(100, 100);
		 * canvas.lineTo(100, 800);
		 */
    	 CMYKColor magentaColor = new CMYKColor(0.f, 1.f, 0.f, 0.f);
    	LineSeparator separator = new LineSeparator();
    	
    	headerpreface.setFont(NORMAL_FONT);
 
    	headerpreface.add(new Chunk("Habbib Salon ", SMALL_BOLD));
        document.add(headerpreface);
        separator.setLineColor(magentaColor);
    	Chunk linebreak = new Chunk(separator);
    	document.add(linebreak);
    	Paragraph preface = new Paragraph();
        /*
		 * PdfPTable table = new PdfPTable(2); table.setWidthPercentage(100); if(null ==
		 * cust){ preface.add(new Chunk("No data to display.")); return; }
		 */
         addEmptyLine(preface, 1);
         preface.add(new Phrase("First Name: ", NORMAL_FONT));
         preface.add(new Phrase(cust.getFirstName(), NORMAL_FONT));
         addEmptyLine(preface, 1);
         preface.add(new Phrase("Last Name: ", PDFCreator.NORMAL_FONT));
         preface.add(new Phrase(cust.getLastName(), PDFCreator.NORMAL_FONT));
         addEmptyLine(preface, 1);
         preface.add(new Phrase("Address: ", PDFCreator.NORMAL_FONT));
         preface.add(new Phrase(cust.getAddress(), PDFCreator.NORMAL_FONT));
         addEmptyLine(preface, 1);
         preface.add(new Phrase("Mobile Number: ", PDFCreator.NORMAL_FONT));
         preface.add(new Phrase(cust.getMobile(), PDFCreator.NORMAL_FONT));
         preface.setAlignment(Element.ALIGN_LEFT);
         document.add(preface);
         separator.setLineWidth(3);
     	 document.add(linebreak);
     	 
     	
     	
    }
    
    
    public static void createBillTable(Document document,Bill bill) throws DocumentException {
    	Paragraph paragraph = new Paragraph();
    	PdfPTable table = new PdfPTable(4);
    	table.setWidthPercentage(100);
        table.setWidths(new float[] {2,1,1,1});
        paragraph.add(new Chunk("Bill number:- ", SMALL_BOLD));
        paragraph.add(new Phrase(bill.getBillNo(), NORMAL_FONT));
      
        addHeaderInTable(HEADER_BILL_SUMMARY, table);
        
        for(Billhasservice dataObject : bill.getBillHasService()){
      	Salonservice salonService = dataObject.getSalonservice(); 
       	if(salonService != null) {
       		addToTable(table, salonService.getName());
       		Double price = salonService.getPrice();
       		table.addCell(new Phrase(price.intValue()));
       		table.addCell(new Phrase(dataObject.getQuantity()));
       		table.addCell(new Phrase(price.intValue()*dataObject.getQuantity()));
      	}
                }
        paragraph.add(table);
        
        document.add(paragraph);
    }
    
    private static void createReportTable(Paragraph paragraph, List<Customerinfo> dataObjList)
    throws BadElementException {
        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        paragraph.add(new Chunk("Habbib Salon:- ", SMALL_BOLD));
        if(null == dataObjList){
            paragraph.add(new Chunk("No data to display."));
            return;
        }
        addHeaderInTable(HEADER_ARRAY, table);
        int count = 1;
        for(Customerinfo dataObject : dataObjList){
            addToTable(table, String.valueOf(count)+".");
            addToTable(table, dataObject.getFirstName());
            
            addToTable(table, dataObject.getLastName());
            addToTable(table, dataObject.getMobile());
            count++;
        }
        paragraph.add(table);
    }
    
    /** Helper methods start here **/  
    public static void addTitlePage(Document document, String title) throws DocumentException {
        Paragraph preface = new Paragraph();
        addEmptyLine(preface, 3);
        preface.add(new Phrase("Test Report: ", NORMAL_FONT));
        preface.add(new Phrase(title, PDFCreator.NORMAL_FONT));
        addEmptyLine(preface, 1);
        preface.add(new Phrase("Date: ", PDFCreator.SMALL_BOLD));
        preface.add(new Phrase(new Date().toString(), PDFCreator.NORMAL_FONT));
        addEmptyLine(preface, 1);
        preface.add(new Phrase("Report generated by: ", PDFCreator.SMALL_BOLD));
        preface.add(new Phrase("Arun", PDFCreator.NORMAL_FONT));
        addEmptyLine(preface, 2);
        preface.add(new Phrase("This is basically a sample report.", PDFCreator.NORMAL_FONT));
        document.addSubject("PDF : " + title);
        preface.setAlignment(Element.ALIGN_CENTER);
        document.add(preface);
        document.newPage();
    }
    
    public static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
    
    public static void addHeaderInTable(String[] headerArray, PdfPTable table){
        PdfPCell c1 = null;
        for(String header : headerArray) {
            c1 = new PdfPCell(new Phrase(header, PDFCreator.SMALL_BOLD));
            c1.setBackgroundColor(BaseColor.GRAY);
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(c1);
        }
        table.setHeaderRows(1);
    }
    
    public static void addToTable(PdfPTable table, String data){        
        table.addCell(new Phrase(data, PDFCreator.NORMAL_FONT));
    }
    
    public static void addToTableIntData(PdfPTable table, int data){        
        table.addCell(new Phrase(data));
    }
    
    public static Paragraph getParagraph(){        
        Paragraph paragraph = new Paragraph();
        paragraph.setFont(PDFCreator.NORMAL_FONT);
        addEmptyLine(paragraph, 1);
        return paragraph;
    }
}
