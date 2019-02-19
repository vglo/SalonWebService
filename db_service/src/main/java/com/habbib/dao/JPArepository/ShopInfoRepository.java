package com.habbib.dao.JPArepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Shopinfo;

@Repository
public interface ShopInfoRepository extends JpaRepository<Shopinfo, Integer> {

}
