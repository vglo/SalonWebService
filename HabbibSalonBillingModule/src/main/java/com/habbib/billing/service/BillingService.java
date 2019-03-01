package com.habbib.billing.service;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habbib.billing.dbrequest.model.BillRequest;
import com.habbib.billing.feign.clients.DBServiceFeignClient;
import com.habbib.billing.request.model.BillClientRequest;
import com.habbib.billing.serviceIntefaces.IBilling;
import com.habbib.billing.util.Utility;

@Service
public class BillingService implements IBilling {
	
	@Autowired
	private DBServiceFeignClient dbserviceFeignClient;
	public BillRequest setRequestToResponse(BillRequest dbRequest, BillClientRequest clientReq) {
		if(0 != clientReq.getCgstPer() && 0 != clientReq.getSgstPer())
		{
			dbRequest.setSgstVal(dbRequest.getTotal()*clientReq.getSgstPer()/100);
			dbRequest.setSgstPer(clientReq.getSgstPer());
			
			dbRequest.setCsgtVal(dbRequest.getTotal()*clientReq.getCgstPer()/100);
			dbRequest.setCgstPer(clientReq.getCgstPer());
			
		}
		if(0 != clientReq.getDescountPer()) {
			dbRequest.setDiscountPer(clientReq.getDescountPer());
			dbRequest.setDiscountVal(dbRequest.getTotal()*(clientReq.getDescountPer()/100));
		}
		
		// Save discount value directly.
		if(clientReq.getDiscountVal() != 0) {
			dbRequest.setDiscountVal(clientReq.getDiscountVal());
		}
		
		dbRequest.setGrandTotal(dbRequest.getTotal()+dbRequest.getCsgtVal()+dbRequest.getSgstVal()-dbRequest.getDiscountVal());
		
		if(0 != dbRequest.getGrandTotal()) {
			dbRequest.setDate(Utility.formateDate());
			dbRequest.setTime(LocalTime.now().toString());
			dbRequest.setBillNo(generateBillNumber());
			
			
		}else {
			//write
		}
		return dbRequest;
		
	}


	private final String generateBillNumber() {
		int count = dbserviceFeignClient.findAllBills().size();
		Date date = new Date();
		return count+"_"+new SimpleDateFormat("yyddMM").format(date);
	}
	
}
