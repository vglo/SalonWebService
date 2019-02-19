package com.habbib.dao.JPArepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Billhasservice;

@Repository
public interface BillHasServiceRepository extends JpaRepository<Billhasservice, Integer> {

}
