package com.habbib.dao.JPArepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Bill;
import com.habbib.dao.entitiy.Billhasservice;
import com.habbib.dao.entitiy.Salonservice;

@Repository
public interface BillHasServiceRepository extends JpaRepository<Billhasservice, Integer> {

	public List<Billhasservice> findByBill(Bill bill);
	
}
