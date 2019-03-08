package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableList;
import com.habbib.dao.JPArepository.ModuleInfoRepository;
import com.habbib.dao.JPArepository.RoleRepository;
import com.habbib.dao.entitiy.Moduleinfo;
import com.habbib.dao.entitiy.QRole;
import com.habbib.dao.entitiy.Role;
import com.habbib.dao.model.RoleRequest;
import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/dao")
public class RoleController {

	@Autowired
	private RoleRepository role;
	
	
	@RequestMapping(path="/delete-role/{id}", method=RequestMethod.DELETE)
	public void deleteRole(@PathVariable("id") int id) {
		role.deleteById(id);
	}
	
	@RequestMapping(path="/fetch/all-roles",method=RequestMethod.GET)
	public List<Role> findAllRoles() {
		List<Role> roleList = role.findAll();
		return roleList;
	}
	
	@RequestMapping(path="/find-role/{id}",method=RequestMethod.GET)
	public Role findRoleByid(@PathVariable int id){
		Optional<Role> roleObj = role.findById(id);
		if(roleObj.isPresent())
			return roleObj.get();
		return null;
	}
	
	
	
	@RequestMapping(path="/find-role-list",method=RequestMethod.GET)
	public List<Role> findRoleListByIds(@RequestParam List<Integer> rolesId){
		QRole  qRole = QRole.role1;
		Predicate predicate = qRole.idRole.in(rolesId);
		Iterable<Role> iterable = role.findAll(predicate);
		return ImmutableList.copyOf(iterable);
	}
	
	
	
}
