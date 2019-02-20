package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.ModuleInfoRepository;
import com.habbib.dao.JPArepository.RoleRepository;
import com.habbib.dao.entitiy.Moduleinfo;
import com.habbib.dao.entitiy.Role;

@RestController
@RequestMapping("/dao")
public class RoleController {

	@Autowired
	private RoleRepository role;
	
	
	@RequestMapping(path="/delete-role/{id}", method=RequestMethod.DELETE)
	public void deleteModule(@PathVariable("id") int id) {
		role.deleteById(id);
	}
	
	@RequestMapping(path="/fetch/all-roles",method=RequestMethod.GET)
	public List<Role> findAllRoles() {
		List<Role> roleList = role.findAll();
		return roleList;
	}
	
	
	
}
