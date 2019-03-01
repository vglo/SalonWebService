/**
 * 
 */
package com.habbib.billing.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.habbib.billing.feign.clients.DBServiceFeignClient;

/**
 * @author yash
 *
 */
@Component
public class Utility {

	
	public static Date formateDate() {
		try {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-dd-MM");
		String newDate = df.format(date);
		
			return df.parse(newDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
