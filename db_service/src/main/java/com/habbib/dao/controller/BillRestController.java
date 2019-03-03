package com.habbib.dao.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.BillHasServiceRepository;
import com.habbib.dao.JPArepository.BillRepository;
import com.habbib.dao.JPArepository.CustomerInfoRepository;
import com.habbib.dao.JPArepository.SalonServiceRepository;
import com.habbib.dao.JPArepository.ShopInfoRepository;
import com.habbib.dao.entitiy.Bill;
import com.habbib.dao.entitiy.Billhasservice;
import com.habbib.dao.entitiy.Customerinfo;
import com.habbib.dao.entitiy.Shopinfo;
import com.habbib.dao.model.BillRequest;
import com.habbib.dao.model.BillhasserviceRequest;
import com.habbib.dao.service.DBService;

@RestController
@RequestMapping(value="/dao")
public class BillRestController {

	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private BillHasServiceRepository billHasService;
	
	@Autowired
	private DBService dbService;
	
	@Autowired
	private SalonServiceRepository salonService;

	@Autowired
	private ShopInfoRepository shopinfoRepo;
	
	
	@Autowired
	private CustomerInfoRepository custRepo;
	
	@Transactional
	@RequestMapping(value="/save-bill", method=RequestMethod.POST)
	public Bill saveBill(@RequestBody BillRequest billModel) {
		Bill bill = dbService.convertModelToEntity(billModel);
		List<Billhasservice> billHasServiceList = new ArrayList<Billhasservice>();
	
		for(BillhasserviceRequest billHasRequst : billModel.getBillhasservices()) {
			Billhasservice billHasService = new Billhasservice();
			billHasService.setBill(bill);
			billHasService.setQuantity(billHasRequst.getQuantity());
			billHasService.setSalonservice(salonService.getOne(billHasRequst.getIdSalonService()));
			billHasServiceList.add(billHasService);
		}
		bill.setBillhasservices(billHasServiceList);
		Bill newbill = billRepository.save(bill);
		
		return newbill;
	}
	
	@RequestMapping(value="/find/all-bills",method=RequestMethod.GET)
	public List<Bill> findAllBills(){
		return billRepository.findAll();
		
	}
	
	@RequestMapping(path="/find-billhasservice/bill-id",method=RequestMethod.GET)
	public List<Billhasservice> fetchBillhasServiceByBillId(@RequestParam int billId){
		Bill bill = billRepository.getOne(billId);
		if(bill == null) 
			throw new NullPointerException();
		
		List<Billhasservice> billsHasService = billHasService.findByBill(bill);
		return billsHasService;
	}
	
	@RequestMapping(path="/filter-date-range",method=RequestMethod.GET)
	public List<Bill> filterByDateRange(@RequestParam String startDate,@RequestParam String endDate,@RequestParam int shopId){
		if(startDate == null || endDate == null )
			throw new NullPointerException();
		List<Bill> billsList = new ArrayList<Bill>();
		Optional<Shopinfo> shopinfo = shopinfoRepo.findById(shopId);
		if(shopinfo.isPresent()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 Date NewStartDate = Date.from(LocalDate.parse(startDate,formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());
		 Date NewEndDate = Date.from(LocalDate.parse(endDate,formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());
		 billsList= billRepository.findByDateBetweenAndShopinfo(NewStartDate,NewEndDate,shopinfo.get());
		}
		return billsList;
	}
	
	@RequestMapping(path="/filter-date",method=RequestMethod.GET)
	public List<Bill> filterByDate(@RequestParam int shopId){
		try {
			List<Bill> billsList = new ArrayList<Bill>();
			Optional<Shopinfo> shopinfo = shopinfoRepo.findById(shopId);
			if(shopinfo.isPresent()) {
			 billsList = billRepository.findByDateAndShopinfo(dbService.formateDate(),shopinfo.get());
			}
			return billsList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(value="/delete-bill/{id}", method=RequestMethod.DELETE)
	public void deleteBill(@PathVariable("id") int id ) throws IllegalArgumentException{
		
		billRepository.deleteById(id);
	}
	
	@RequestMapping(value="/update-bill", method=RequestMethod.PUT)
	public void updateBill(@RequestBody Bill bill) {
		 billRepository.save(bill);
	}
	
	
	@RequestMapping(path="/fetch/billById/{id}", method=RequestMethod.GET)
	public Optional<Bill> findByBillId(@PathVariable("id") int billId,@RequestParam int shopId) {
		Shopinfo shop = shopinfoRepo.getOne(shopId);
		Optional<Bill> bill =  billRepository.findByIdBillAndShopinfo(billId, shop);
		return bill;
	}
	
	@RequestMapping(path="/fetch/billByBillNum/{bill-number}", method=RequestMethod.GET)
	public Optional<Bill> findByBillNum(@PathVariable("bill-number") String billNumber,@RequestParam int shopId) {
		Shopinfo shop = shopinfoRepo.getOne(shopId);
		Optional<Bill> bill =  billRepository.findByBillNoAndShopinfo(billNumber,shop);
		return bill;
	}
	
	@RequestMapping(path="/find-bill/shop-id",method=RequestMethod.GET)
	public List<Bill> findBillByShopId(@RequestParam int shopId){
		Shopinfo shop = shopinfoRepo.getOne(shopId);
		List<Bill> billList = billRepository.findByShopinfo(shop);
		return billList;
	}
	
	@RequestMapping(path="/find-bill/cust-id",method=RequestMethod.GET)
	public List<Bill> fetchBillByCustId(@RequestParam int custId){
		Customerinfo custinfo = custRepo.getOne(custId);
		List<Bill> billList = billRepository.findByCustomerinfo(custinfo);
		return billList;
	}
}
