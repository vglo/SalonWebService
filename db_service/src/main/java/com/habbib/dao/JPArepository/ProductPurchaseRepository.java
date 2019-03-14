package com.habbib.dao.JPArepository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Productpurchase;

@Repository
public interface ProductPurchaseRepository extends QuerydslPredicateExecutor<Productpurchase> {

}
