package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.BillRepository;
import com.habbib.dao.entitiy.Bill;
import com.habbib.dao.entitiy.Campaign;

@RestController
@RequestMapping(value="/dao")
public class BillRestController {

	@Autowired
	private BillRepository billRepository;
	
	@RequestMapping(value="/save-bill", method=RequestMethod.POST)
	public void  saveBill(@RequestBody Bill bill) {
		billRepository.save(bill);
	}
	
	@RequestMapping(value="/find/all-bills")
	public List<Bill> findAllBills(){
		return billRepository.findAll();
		
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
