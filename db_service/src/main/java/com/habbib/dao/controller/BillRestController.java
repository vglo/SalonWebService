package com.habbib.dao.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.BillHasServiceRepository;
import com.habbib.dao.JPArepository.BillRepository;
import com.habbib.dao.JPArepository.SalonServiceRepository;
import com.habbib.dao.entitiy.Bill;
import com.habbib.dao.entitiy.Billhasservice;
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
	
	
	@RequestMapping(value="/save-bill", method=RequestMethod.POST)
	public Bill saveBill(@RequestBody BillRequest billModel) {
		Bill bill = dbService.convertModelToEntity(billModel);
		Bill newbill = billRepository.save(bill);
		for(BillhasserviceRequest billHasServiceRequest : billModel.getBillhasservices()) {
			Billhasservice billServiceEntity = new Billhasservice();
			billServiceEntity.setQuantity(billHasServiceRequest.getQuantity());
			billServiceEntity.setBill(newbill);
			billServiceEntity.setSalonservice(salonService.findById(billHasServiceRequest.getIdSalonService()).get());
			billHasService.save(billServiceEntity);
		}
		return newbill;
	}
	
	@RequestMapping(value="/find/all-bills",method=RequestMethod.GET)
	public List<Bill> findAllBills(){
		return billRepository.findAll();
		
	}
	
	@RequestMapping(path="/filter-date-range",method=RequestMethod.GET)
	public List<Bill> filterByDateRange(@RequestParam String startDate,@RequestParam String endDate){
		if(startDate == null || endDate == null )
			throw new NullPointerException();
		 Date NewStartDate = Date.from(LocalDate.parse(startDate).atStartOfDay(ZoneId.systemDefault()).toInstant());
		 Date NewEndDate = Date.from(LocalDate.parse(endDate).atStartOfDay(ZoneId.systemDefault()).toInstant());
		List<Bill> billsList = billRepository.findByDateBetween(NewStartDate,NewEndDate);
		return billsList;
	}
	
	@RequestMapping(path="/filter-date",method=RequestMethod.GET)
	public List<Bill> filterByDate(){
		try {
			List<Bill> billsList = billRepository.findByDate(dbService.formateDate());
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
	public Optional<Bill> findByBillId(@PathVariable("id") int billId) {
		Optional<Bill> bill =  billRepository.findById(billId);
		return bill;
	}
	
	@RequestMapping(path="/fetch/billByBillNum/{bill-number}", method=RequestMethod.GET)
	public Optional<Bill> findByBillNum(@PathVariable("bill-number") String billNumber) {
		Optional<Bill> bill =  billRepository.findByBillNo(billNumber);
		return bill;
	}
}
