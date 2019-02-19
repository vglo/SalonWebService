package com.habbib.dao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.dao.JPArepository.ModuleInfoRepository;
import com.habbib.dao.entitiy.Moduleinfo;

@RestController
@RequestMapping(value="/dao")
public class ModuleController {

	@Autowired
	private ModuleInfoRepository moduleInfo;
	
	@RequestMapping(path="/save-module-detail")
	public void saveModuleDetails(@RequestBody Moduleinfo module) {
		
		moduleInfo.save(module);
	}
	
	@RequestMapping(path="/delete-module/{id}")
	public void deleteModule(@PathVariable("id") int id) {
		moduleInfo.deleteById(id);
	}
	
	@RequestMapping(path="/fetch/all-modules")
	public List<Moduleinfo> findAllModules() {
		List<Moduleinfo> moduleList = moduleInfo.findAll();
		return moduleList;
	}
	
	@RequestMapping(path="/fetch/moduleById/{id}")
	public Optional<Moduleinfo> findByModuleId(@PathVariable("id") int moduleId) {
		Optional<Moduleinfo> module =  moduleInfo.findById(moduleId);
		return module;
	}
	
	@RequestMapping(path="/update-module")
	public void uddateModule(@RequestBody Moduleinfo module) {
		moduleInfo.save(module);
	}
	

}
