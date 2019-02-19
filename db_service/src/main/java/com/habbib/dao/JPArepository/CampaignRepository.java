package com.habbib.dao.JPArepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Campaign;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

}
