package com.habbib.dao.JPArepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habbib.dao.entitiy.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
