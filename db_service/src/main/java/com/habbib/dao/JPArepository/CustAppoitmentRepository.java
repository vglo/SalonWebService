/**
 * 
 */
package com.habbib.dao.JPArepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Appointment;
import com.habbib.dao.entitiy.Customerinfo;
import com.habbib.dao.entitiy.Shopinfo;

/**
 * @author yash
 *
 */
@Repository
public interface CustAppoitmentRepository extends JpaRepository<Appointment, Integer> {

	public List<Appointment> findByShopinfo(Shopinfo shopinfo);
	
	public List<Appointment> findByCustomerinfo(Customerinfo custInfo);
}
