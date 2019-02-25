package com.habbib.dao.JPArepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Shopinfo;
import com.habbib.dao.entitiy.Staffinfo;

@Repository
public interface StaffInfoRepository extends JpaRepository<Staffinfo, Integer> {
	
	public Optional<Staffinfo> findByMobileAndShopinfo(String mobileNumber, Shopinfo shop);
	
	public List<Staffinfo> findByShopinfo(Shopinfo shpoinfo);

}
