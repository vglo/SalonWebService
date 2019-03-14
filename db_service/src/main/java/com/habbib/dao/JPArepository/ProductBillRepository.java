package com.habbib.dao.JPArepository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Productbill;

@Repository
public interface ProductBillRepository extends QuerydslPredicateExecutor<Productbill>{

	
}
