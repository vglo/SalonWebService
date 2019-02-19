package com.habbib.dao.JPArepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Staffinfo;

@Repository
public interface StaffInfoRepository extends JpaRepository<Staffinfo, Integer> {

}
