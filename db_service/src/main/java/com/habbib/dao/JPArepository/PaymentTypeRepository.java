package com.habbib.dao.JPArepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Paymenttype;

@Repository
public interface PaymentTypeRepository extends JpaRepository<Paymenttype, Integer> {

	public List<Paymenttype> findByshopId(int shopId);
	
	public List<Paymenttype> findByType(String type);

}
