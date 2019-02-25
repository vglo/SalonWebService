package com.habbib.dao.JPArepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Customerinfo;
import com.habbib.dao.entitiy.Shopinfo;

@Repository
public interface CustomerInfoRepository  extends JpaRepository<Customerinfo, Integer>{

	
	public List<Customerinfo> findByShopinfo(Shopinfo shopInfo);
	
	public Optional<Customerinfo> findByMobileAndShopinfo(String mobNumber, Shopinfo shopInfo);
}
