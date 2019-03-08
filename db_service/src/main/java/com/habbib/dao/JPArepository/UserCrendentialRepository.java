package com.habbib.dao.JPArepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Usercredential;

@Repository
public interface UserCrendentialRepository extends JpaRepository<Usercredential,Integer> {
	
	public Optional<Usercredential> findByUsername(String username);

}
