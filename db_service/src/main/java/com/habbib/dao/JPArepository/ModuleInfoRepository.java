package com.habbib.dao.JPArepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Moduleinfo;

@Repository
public interface ModuleInfoRepository extends JpaRepository<Moduleinfo, Integer> {

}
