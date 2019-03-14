package com.habbib.dao.JPArepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Shopinfo;
import com.habbib.dao.entitiy.Shoptype;

@Repository
public interface ShopInfoRepository extends JpaRepository<Shopinfo, Integer> {
	
	public List<Shopinfo> findByShopinfo(Shopinfo shopInfo);
	
	public List<Shopinfo> findByShoptype(Shoptype shopType);

}
