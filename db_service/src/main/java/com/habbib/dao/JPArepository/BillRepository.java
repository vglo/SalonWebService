package com.habbib.dao.JPArepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>{

	public Optional<Bill> findByBillNo(String billNumber);
	
	public List<Bill> findByDate(Date date);
	
	public List<Bill> findByDateBetween(Date startDate, Date endDate);
	
}
