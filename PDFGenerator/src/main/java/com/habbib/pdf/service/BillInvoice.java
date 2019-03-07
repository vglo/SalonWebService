package com.habbib.pdf.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BillInvoice {

	public static void main(String[] args) {
		String pdfFilename = "src/main/resources/Invoice_Ex.pdf";
		BillInvoice generateInvoice = new BillInvoice();

		generateInvoice.createPDF(pdfFilename);

	}

	 /** The path to a regular font. */
   public static final String REGULAR = "src/main/resources/fonts/OpenSans-Regular.ttf";
   
   /** The path to a bold font. */
   public static final String BOLD = "src/main/resources/fonts/OpenSans-Bold.ttf";
   
   /** A <code>String</code> with a newline character. */
   public static final String NEWLINE = "\n";
  


	public void createPDF (String pdfFilename){

		try {
			OutputStream file = new FileOutputStream(new File(pdfFilename));
			Document document = new Document();
			PdfWriter.getInstance(document, file);

			//Inserting Image in PDF
			
			String invoice = "INVOICE" +"/4544";
			
			PdfPTable irdTable = new PdfPTable(2);
			irdTable.setWidthPercentage(100);
			irdTable.addCell(getIRDCell(""));
			irdTable.addCell(getIRDCell(""));
			irdTable.addCell(getIRDCell("")); // pass invoice number
			irdTable.addCell(getIRDCell("")); // pass invoice date				
			PdfPTable irhTable = new PdfPTable(3);
			irhTable.setWidthPercentage(100);

			irhTable.addCell(PdfPCellImg(PdfPCell.ALIGN_LEFT));
			PdfPCell pdfnewCel = new PdfPCell();
			pdfnewCel.addElement(new Phrase("\t\t\t\t\t"));
			pdfnewCel.setBorder(PdfPCell.NO_BORDER);
			irhTable.addCell(pdfnewCel);
			irhTable.addCell(getIRHCell(invoice, PdfPCell.ALIGN_RIGHT));
			PdfPCell invoiceTable = new PdfPCell (irdTable);
			invoiceTable.setBorder(0);
			irhTable.addCell(invoiceTable);
			FontSelector fs = new FontSelector();
			Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 13, Font.BOLD);
			fs.addFont(font);
			Phrase bill = fs.process("\n\n\nBill To,"); // customer information
			Paragraph name = new Paragraph("Yash Agrawal");
			name.setIndentationLeft(20);
			Paragraph contact = new Paragraph("7507222645");
			contact.setIndentationLeft(20);
			Paragraph address = new Paragraph("Panchkandil road, shirpur dist dhule");
			address.setIndentationLeft(20);

			PdfPTable billTable = new PdfPTable(4); //one page contains 15 records 
			billTable.setWidthPercentage(100);
			billTable.setWidths(new float[] { 7, 2,2,2 });
			billTable.setSpacingBefore(30.0f);
			billTable.addCell(getBillHeaderCell("Service Offered/Cosmetics purchased"));
			billTable.addCell(getBillHeaderCell("Units"));
			billTable.addCell(getBillHeaderCell("Unit Price"));
			billTable.addCell(getBillHeaderCell("Amount"));

			billTable.addCell(getBillRowCell("16Gb Memorycard \\n Serial:UR8531 "));
			billTable.addCell(getBillRowCell("1"));
			billTable.addCell(getBillRowCell("120.0"));
			billTable.addCell(getBillRowCell("120.0"));
			
			
			  PdfPTable validity = new PdfPTable(1); 
			  validity.setWidthPercentage(100);
			  validity.addCell(getValidityCell("Total"));
			  validity.addCell(getValidityCell("Discount")); 
			  validity.addCell(getValidityCell("CGST")); 
			  validity.addCell(getValidityCell("SGST" ));
			  validity.addCell(getValidityCell("Paid Amount" ));	    
			PdfPCell summaryL = new PdfPCell (validity);
			summaryL.setColspan (2);
			//summaryL.setPadding (10.0f);	                   
			billTable.addCell(summaryL);

			PdfPTable accounts = new PdfPTable(1);
			accounts.setWidthPercentage(100);
			accounts.addCell(getAccountsCellR("120.00"));
			accounts.addCell(getAccountsCellR("0"));
			accounts.addCell(getAccountsCellR("12.0"));
			accounts.addCell(getAccountsCellR("12.0"));
			accounts.addCell(getAccountsCellR("144"));			
			PdfPCell summaryR = new PdfPCell (accounts);
			summaryR.setColspan (2);         
			billTable.addCell(summaryR);  

			PdfPTable describer = new PdfPTable(1);
			describer.setWidthPercentage(100);
			describer.addCell(getdescCell(" "));
			describer.addCell(getdescCell("Goods once sold will not be taken back or exchanged || Subject to product justification || Product damage no one responsible || "
					+ " Service only at concarned authorized service centers"));	

			document.open();//PDF document opened........	
			document.add(irhTable);
			
			document.add(bill);
			document.add(name);
			document.add(contact);
			document.add(address);			
			document.add(billTable);
			document.add(describer);

			document.close();

			file.close();

			System.out.println("Pdf created successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PdfPCell PdfPCellImg(int alignment) throws BadElementException, MalformedURLException, IOException {
		 Image image = Image.getInstance("src/main/resources/habbib_logo.jpg");
		 image.setAbsolutePosition(120, 120);
        PdfPCell cell = new PdfPCell(image, false);
		cell.setPadding(1);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getIRHCell(String text, int alignment) {
		SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy");
		String date = simple.format(new Date());
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 14);
		font.setColor(BaseColor.DARK_GRAY);
		fs.addFont(font);
		//paragraph.add(LocalDate.now());
		Phrase phrase = fs.process(text);
		
		
		PdfPCell cell = new PdfPCell();
		cell.addElement(phrase);
		cell.addElement(new Phrase(date));
		//cell.setPadding(5);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getIRDCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		//cell.setPadding (5.0f);
		//cell.setBorderColor(BaseColor.LIGHT_GRAY);
		cell.setPadding(5);
		
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getBillHeaderCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 11);
		font.setColor(BaseColor.DARK_GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		return cell;
	}

	public static PdfPCell getBillRowCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (10.0f);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthTop(0);
		return cell;
	}

	public static PdfPCell getBillFooterCell(String text) {
		PdfPCell cell = new PdfPCell (new Paragraph (text));
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthTop(0);
		return cell;
	}

	public static PdfPCell getValidityCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);	
		cell.setHorizontalAlignment (Element.ALIGN_RIGHT);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthTop(0);
		cell.setPadding (5.0f);
		//cell.setBorder(0);
		return cell;
	}

	public static PdfPCell getAccountsCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);		
		cell.setBorderWidthRight(0);
		cell.setBorderWidthTop(0);
		cell.setPadding (5.0f);
		return cell;
	}
	public static PdfPCell getAccountsCellR(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);		
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setPadding (5.0f);
		cell.setPaddingRight(10.0f);
		return cell;
	}

	public static PdfPCell getdescCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell (phrase);	
		cell.setHorizontalAlignment (Element.ALIGN_CENTER);
		cell.setBorder(0);
		return cell;
	}


}
