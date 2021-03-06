/**
 * 
 */
package com.habbib.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habbib.service.dbfeign.client.DBServiceFeignClient;
import com.habbib.service.request.model.SalonserviceRequest;
import com.habbib.service.response.model.Salonservice;
import com.habbib.service.response.model.Shopinfo;
import com.habib.utility.DefaultMessage;

/**
 * @author yash
 *
 */
@RestController
@RequestMapping("service")
public class SalonServiceController {
	
	@Autowired
	private DBServiceFeignClient dbClient;
	
	@RequestMapping(path="/save-service",method=RequestMethod.POST)
	public ResponseEntity<DefaultMessage<Salonservice>> insertService(@Valid @ModelAttribute SalonserviceRequest salonService ) {
		DefaultMessage<Salonservice> defualt = new DefaultMessage<Salonservice>();
		
		try {
		Optional<Shopinfo> shopInfo = dbClient.findByShopId(salonService.getShopId());
		if(!shopInfo.isPresent()) {
			defualt.setResponseCode("404");
			defualt.setResponseMessage("Shop with given id is not present/registered, please enter valid shop id");
			return new ResponseEntity<DefaultMessage<Salonservice>>(defualt,HttpStatus.BAD_REQUEST);
		}
		
		
		//check if service already exists or not
		Salonservice servicePresent = dbClient.validateService(salonService.getName(), salonService.getPrice(),salonService.getShopId());
		
		if(null != servicePresent) {
			defualt.setResponseCode("302");
			defualt.setResponseMessage("service already present");
			defualt.setResponse(servicePresent);
			return new ResponseEntity<DefaultMessage<Salonservice>>(defualt,HttpStatus.FOUND);
		}else {
			//saving salon service
			Salonservice service = dbClient.saveService(salonService);
			defualt.setResponseCode("200");
			defualt.setResponseMessage("service added");
			defualt.setResponse(service);
			return new ResponseEntity<DefaultMessage<Salonservice>>(defualt,HttpStatus.OK);
		}
	}catch (Exception e) {
		defualt.setResponse(null);
		defualt.setResponseCode("500");
		defualt.setResponseMessage("error occured"+e.getLocalizedMessage());
		return new ResponseEntity<DefaultMessage<Salonservice>>(defualt,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	@RequestMapping(path="/find-service/shop",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<List<Salonservice>>> fetchServiceByShop(@RequestParam(required=true) int shopId){
		DefaultMessage<List<Salonservice>> defualt = new DefaultMessage<List<Salonservice>>();
		
		List<Salonservice> salonServiceList = dbClient.getSalonServices(shopId);
		if(null != salonServiceList) {
			defualt.setResponseCode("302");
			defualt.setResponseMessage("Salon service list is found");
			defualt.setResponse(salonServiceList);
			return new ResponseEntity<DefaultMessage<List<Salonservice>>>(defualt,HttpStatus.OK);
		}else {
			//saving salon service
			defualt.setResponseCode("404");
			defualt.setResponseMessage("Salon service list is empty");
			defualt.setResponse(salonServiceList);
			return new ResponseEntity<DefaultMessage<List<Salonservice>>>(defualt,HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(path="/find-service/serviceid",method=RequestMethod.GET)
	public ResponseEntity<DefaultMessage<Salonservice>> fetchServiceById(@RequestParam(required=true) int serviceId,@RequestParam(required=true) int shopId){
		DefaultMessage<Salonservice> defualt = new DefaultMessage<Salonservice>();
		Optional<Salonservice> salonServiceList = dbClient.getServiceInfo(serviceId, shopId);
		if(salonServiceList.isPresent()) {
			defualt.setResponseCode("302");
			defualt.setResponseMessage("Salon service list is found");
			defualt.setResponse(salonServiceList.get());
			return new ResponseEntity<DefaultMessage<Salonservice>>(defualt,HttpStatus.OK);
		}else {
			//saving salon service
			defualt.setResponseCode("404");
			defualt.setResponseMessage("Salon service list is empty");
			defualt.setResponse(null);
			return new ResponseEntity<DefaultMessage<Salonservice>>(defualt,HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path="/update-service",method=RequestMethod.PUT)
	public ResponseEntity<DefaultMessage<Salonservice>> updateService(@ModelAttribute SalonserviceRequest salonService,@RequestParam int serviceId){
		DefaultMessage<Salonservice> defualt = new DefaultMessage<Salonservice>();
		try {
		Optional<Shopinfo> shopInfo = dbClient.findByShopId(salonService.getShopId());
		if(!shopInfo.isPresent()) {
			defualt.setResponseCode("404");
			defualt.setResponseMessage("Shop with given id is not present/registered, please enter valid shop id");
			return new ResponseEntity<DefaultMessage<Salonservice>>(defualt,HttpStatus.BAD_REQUEST);
		}
		
		Optional<Salonservice> servicePresent = dbClient.getServiceInfo(serviceId, salonService.getShopId());
		if(servicePresent.isPresent()) {
			Salonservice service = dbClient.updateService(salonService, serviceId);
			defualt.setResponseCode("200");
			defualt.setResponseMessage("Service updated successfully");
			defualt.setResponse(service);
			return new ResponseEntity<DefaultMessage<Salonservice>>(defualt,HttpStatus.OK);
		}else {
			//saving salon service
			defualt.setResponseCode("200");
			defualt.setResponseMessage("service not exists, not updated");
			defualt.setResponse(servicePresent.get());
			return new ResponseEntity<DefaultMessage<Salonservice>>(defualt,HttpStatus.OK);
		}
	}catch (Exception e) {
		defualt.setResponse(null);
		defualt.setResponseCode("500");
		defualt.setResponseMessage("error occured"+e.getLocalizedMessage());
		return new ResponseEntity<DefaultMessage<Salonservice>>(defualt,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	@RequestMapping(path="/delete-service",method=RequestMethod.DELETE)
	public ResponseEntity<DefaultMessage<Boolean>> deleteService(@RequestParam(required=true) int serviceId,@RequestParam(required=true) int shopId){
		DefaultMessage<Boolean> defaultMsg= new DefaultMessage<Boolean>();
		try {
			dbClient.deleteService(serviceId, shopId);
		}catch (Exception e) {
			defaultMsg.setResponse(false);
			defaultMsg.setResponseCode("400");
			defaultMsg.setResponseMessage("error occured in delete the service"+e.getLocalizedMessage());
			return new ResponseEntity<DefaultMessage<Boolean>>(defaultMsg,HttpStatus.BAD_REQUEST);
		}
		defaultMsg.setResponse(true);
		defaultMsg.setResponseCode("200");
		defaultMsg.setResponseMessage("service deleted with given id");
		return new ResponseEntity<DefaultMessage<Boolean>>(defaultMsg,HttpStatus.OK);
	}
}


