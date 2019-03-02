package com.habbib.dao.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habbib.dao.JPArepository.CustomerInfoRepository;
import com.habbib.dao.JPArepository.PaymentTypeRepository;
import com.habbib.dao.JPArepository.RoleRepository;
import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.JPArepository.ShopTypeRepository;
import com.habbib.dao.JPArepository.StaffInfoRepository;
import com.habbib.dao.entitiy.Appointment;
import com.habbib.dao.entitiy.Bill;
import com.habbib.dao.entitiy.Customerinfo;
import com.habbib.dao.entitiy.Salonservice;
import com.habbib.dao.entitiy.Shopinfo;
import com.habbib.dao.entitiy.Staffinfo;
import com.habbib.dao.model.AppointmentRequest;
import com.habbib.dao.model.BillRequest;
import com.habbib.dao.model.CustomerRequest;
import com.habbib.dao.model.SalonserviceRequest;
import com.habbib.dao.model.ShopinfoRequest;
import com.habbib.dao.model.StaffinfoRequest;

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
	
	@Autowired
	private RoleRepository daoRole;

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
		bill.setShopinfo(daoShop.getOne(billRequest.getShopId()));
		bill.setCustomerinfo(daoCustomer.getOne(billRequest.getCustId()));
		bill.setStaffinfo(daoStaff.getOne(billRequest.getServingStaff()));
		bill.setPaymenttype(daoPayment.getOne(billRequest.getPaymentType()));
		bill.setDiscountPer(billRequest.getDiscountPer());
		bill.setDiscountVal(billRequest.getDiscountVal());
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
		if(shop.getParentShopId() !=0 ) {
			Shopinfo parentShop = daoShop.getOne(shop.getParentShopId());
			shopDetails.setShopinfo(parentShop);
		}
			
		return shopDetails;
	}
	
	public Date formateDate() {
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
	
	public Date formateDate(String date) {
		try {
		DateFormat df = new SimpleDateFormat("yyyy-dd-MM");
		Date newDate = df.parse(date);
		
			return newDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public Appointment convertAppoitmentEntityToModel(AppointmentRequest appointmentReq) {
		Appointment appoitment = new Appointment();
		appoitment.setDate(appointmentReq.getDate());
		appoitment.setTime(appointmentReq.getTime());
		appoitment.setShopinfo(daoShop.getOne(appointmentReq.getIdShopInfo()));
		appoitment.setCustomerinfo(daoCustomer.findById(appointmentReq.getIdCustomerInfo()).get());
		return null;
	}

	public Staffinfo convertstaffModelToEntity(StaffinfoRequest staffInfoReq) {
		Staffinfo staffInfo = new Staffinfo();
		staffInfo.setDob(staffInfoReq.getDob());
		staffInfo.setEmail(staffInfoReq.getEmail());
		staffInfo.setFirstName(staffInfoReq.getFirstName());
		staffInfo.setLastName(staffInfoReq.getLastName());
		staffInfo.setMobile(staffInfoReq.getMobile());
		staffInfo.setShopinfo(daoShop.getOne(staffInfoReq.getShopId()));
		staffInfo.setRoleBean(daoRole.getOne(staffInfoReq.getRoleId()));
		return staffInfo;
	}

	public Salonservice convertServiceModelToEntity(SalonserviceRequest salonServiceReq) {
		Salonservice service = new Salonservice();
		service.setName(salonServiceReq.getName());
		service.setPrice(salonServiceReq.getPrice());
		service.setShopinfo(daoShop.getOne(salonServiceReq.getShopId()));
		return service;
	}
}
