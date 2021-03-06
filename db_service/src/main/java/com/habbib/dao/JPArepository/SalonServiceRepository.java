package com.habbib.dao.JPArepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Salonservice;
import com.habbib.dao.entitiy.Shopinfo;

@Repository
public interface SalonServiceRepository extends JpaRepository<Salonservice, Integer>{

	public List<Salonservice> findByShopinfo(Shopinfo shopInfo);
	
	public Optional<Salonservice> findByNameAndPriceAndShopinfo(String name,double price,Shopinfo shop);
	
	public Optional<Salonservice> findByIdSalonServiceAndShopinfo(int staffId,Shopinfo shop);
}
