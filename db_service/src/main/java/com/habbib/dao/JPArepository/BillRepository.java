package com.habbib.dao.JPArepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Bill;
import com.habbib.dao.entitiy.Customerinfo;
import com.habbib.dao.entitiy.Shopinfo;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>{

	public Optional<Bill> findByBillNoAndShopinfo(String billNumber,Shopinfo shopInfo);
	
	public Optional<Bill> findByIdBillAndShopinfo(int billId,Shopinfo shopInfo);
	
	public List<Bill> findByDateAndShopinfo(Date date,Shopinfo shopinfo);
	
	public List<Bill> findByDateBetweenAndShopinfo(Date startDate, Date endDate,Shopinfo shopinfo);
	
	public List<Bill> findByShopinfo(Shopinfo shopInfo);
	
	public List<Bill> findByCustomerinfo(Customerinfo custInfo);
	
}
