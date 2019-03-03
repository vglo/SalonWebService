/**
 * 
 */
package com.habbib.sms.service;

import org.springframework.stereotype.Service;

/**
 * @author yash
 *
 */
@Service
public class HTMLEmailBody {
	
	
	public String createEmailBody() {
		String header = "<body bgcolor=\"#70bbd9\"></body>";
		String tableHeading = "<h2>Left-align Headings</h2>";
		String Body ="<table align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" >"
				;
		String footer = "</body>";
		
		//htmlBody.append(header).append(tableHeading).append(footer);
		return header;
	}

}
