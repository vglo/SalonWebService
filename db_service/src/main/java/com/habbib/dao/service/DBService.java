package com.habbib.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habbib.dao.JPArepository.CustomerInfoRepository;
import com.habbib.dao.JPArepository.PaymentTypeRepository;
import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.JPArepository.ShopTypeRepository;
import com.habbib.dao.JPArepository.StaffInfoRepository;
import com.habbib.dao.entitiy.Bill;
import com.habbib.dao.entitiy.Billhasservice;
import com.habbib.dao.entitiy.Customerinfo;
import com.habbib.dao.entitiy.Paymenttype;
import com.habbib.dao.entitiy.Shopinfo;
import com.habbib.dao.model.BillRequest;
import com.habbib.dao.model.BillhasserviceRequest;
import com.habbib.dao.model.CustomerRequest;
import com.habbib.dao.model.ShopinfoRequest;

@Service
public class DBService {
	
	@Autowired
	private ShopInfoRepository daoShop;
	
	@Autowired
	private CustomerInfoRepository daoCustomer;
	
	@Autowired
	private StaffInfoRepository daoStaff;
	
	@Autowired
	private PaymentTypeRepository daoPayment;
	
	@Autowired
	private ShopTypeRepository daoShopType;

	public Bill convertModelToEntity(BillRequest billRequest) {
		Bill bill = new Bill();
		bill.setBillNo(billRequest.getBillNo());
		bill.setCgstPer(billRequest.getCgstPer());
		bill.setCsgtVal(billRequest.getCsgtVal());
		bill.setSgstPer(billRequest.getSgstPer());
		bill.setSgstVal(billRequest.getSgstVal());
		bill.setTime(billRequest.getTime());
		bill.setTotal(billRequest.getTotal());
		bill.setDate(billRequest.getDate());
		bill.setGrandTotal(billRequest.getGrandTotal());
		bill.setShopinfo(daoShop.findById(billRequest.getIdShopInfo()).get());
		bill.setCustomerinfo(daoCustomer.findById(billRequest.getIdCustomerInfo()).get());
		bill.setStaffinfo(daoStaff.findById(billRequest.getIdStaffInfo()).get());
		bill.setPaymenttype(daoPayment.findById(billRequest.getIdPaymentType()).get());
		return bill;
		
	}

	public Customerinfo convertModelToEntityCustomer(CustomerRequest cust) {
		Customerinfo custInfo = new Customerinfo();
		custInfo.setAddress(cust.getAddress());
		custInfo.setDob(cust.getDob());
		custInfo.setEmail(cust.getEmail());
		custInfo.setFirstName(cust.getFirstName());
		custInfo.setLastName(cust.getLastName());
		custInfo.setMobile(cust.getMobile());
		custInfo.setShopinfo(daoShop.findById(cust.getIdShopInfo()).get());
		// TODO Auto-generated method stub
		return custInfo;
	}

	public Shopinfo convertModelToEntityShop(ShopinfoRequest shop) {
		// TODO Auto-generated method stub
		Shopinfo shopDetails = new Shopinfo();
		shopDetails.setAddress(shop.getAddress());
		shopDetails.setName(shop.getName());
		shopDetails.setPhone1(shop.getPhone1());
		shopDetails.setPhone2(shop.getPhone2());
		shopDetails.setShoptype(daoShopType.findById(shop.getIdShopType()).get());
		return shopDetails;
	}
	
}
