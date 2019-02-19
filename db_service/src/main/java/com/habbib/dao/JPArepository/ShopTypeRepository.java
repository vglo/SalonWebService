package com.habbib.dao.JPArepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Shoptype;

@Repository
public interface ShopTypeRepository  extends JpaRepository<Shoptype, Integer> {

}
