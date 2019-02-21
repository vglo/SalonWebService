package com.habbib.customer.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Utilities {
	
	public Date convertDateFormate(Date date) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-dd-MM");
		try {
		String newDate = formate.format(date);
		return formate.parse(newDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
