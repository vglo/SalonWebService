package com.habbib.billing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.habbib.billing.entities.BillingField;

@Repository
public interface BillingRepository extends CrudRepository<BillingField,String>{
	

	
}
