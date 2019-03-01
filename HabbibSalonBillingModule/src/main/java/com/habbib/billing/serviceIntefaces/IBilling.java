package com.habbib.billing.serviceIntefaces;

import org.springframework.stereotype.Service;

import com.habbib.billing.dbrequest.model.BillRequest;
import com.habbib.billing.request.model.BillClientRequest;

@Service
public interface IBilling {

	//public Bill calculateServiceTotal(Bill billResponse,BillClientRequest billRequest);
	
	public BillRequest setRequestToResponse(BillRequest dbRequest, BillClientRequest clientReq);
}
