package com.munshi.device.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.munshi.device.bean.Device;
import com.munshi.device.controller.exception.DeviceNotFoundException;
import com.munshi.device.service.DeviceDAOService;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class DeviceController {
	
	@Autowired
	private DeviceDAOService deviceDAOService;
	
	@GetMapping("/devices/{id}")
	public Resource<Device> getDevicebyId(@PathVariable String id){
		
	
		Device device=deviceDAOService.getDevicebyID(id);
		if(device==null){
			throw new DeviceNotFoundException(id);
		}
		Resource<Device> resource= new Resource<Device>(device);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).getDevicebyId(id));
		resource.add(linkTo.withRel("self"));
		linkTo= linkTo(methodOn(this.getClass()).getDevicebyUserId(device.getUserId()));
		resource.add(linkTo.withRel("device-for-user"));
		return resource;
		
	}
	@GetMapping("/devices")
	public List<Resource<Device>> getDevicebyUserId(@RequestParam("userId") String userId){
		
	
		List<Device> deviceList=deviceDAOService.getDevicesbyUserID(userId);
		ArrayList<Resource<Device>> resourceList=new ArrayList<Resource<Device>>();
		for (Device device : deviceList) {
			Resource<Device> resource= new Resource<Device>(device);
			ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).getDevicebyId(device.getId()));
			resource.add(linkTo.withRel("self"));
			linkTo= linkTo(methodOn(this.getClass()).getDevicebyUserId(userId));
			resource.add(linkTo.withRel("device-for-user"));
			resourceList.add(resource);
		}
		
		return resourceList;
		
	}
	
	@PostMapping("/devices")
	public ResponseEntity<Object> addDevice(@RequestBody Device device){
		
		Device newDevice=deviceDAOService.addDevice(device);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).getDevicebyId(device.getId()));
		
		 return ResponseEntity.created(linkTo.toUri()).build();
		
	}
	
	@PutMapping("/devices/{id}")
	public Resource<Device> updateDevice(@PathVariable String id, @RequestBody Device device){
		
		Device updatedDevice=deviceDAOService.updateDevice(id,device);
		Resource<Device> resource= new Resource<Device>(updatedDevice);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).getDevicebyId(updatedDevice.getId()));
		resource.add(linkTo.withRel("self"));
		
		return resource;
		
	}
	
	@DeleteMapping("/devices/{id}")
	public Resource<Device> deleteDevice(@PathVariable String id){
		
		Device deletedDevice=deviceDAOService.deleteDevice(id);
		Resource<Device> resource= new Resource<Device>(deletedDevice);
		ControllerLinkBuilder linkTo= linkTo(methodOn(this.getClass()).getDevicebyId(deletedDevice.getId()));
		resource.add(linkTo.withRel("self"));
		
		return resource;
		
	}
	

}
