package com.munshi.device.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.munshi.device.bean.Device;
import com.munshi.device.controller.exception.DeviceNotFoundException;
import com.munshi.device.repository.DeviceRepository;

@Service
public class DeviceDAOServiceImpl implements DeviceDAOService{
	
	@Autowired
	private DeviceRepository deviceRepository;

	@Override
	public Device getDevicebyID(String id) {
		Optional<Device> user = deviceRepository.findById(id);		
		if(!user.isPresent()){
			throw new DeviceNotFoundException("Device "+id+" not found");
		
		}
		return user.get();
	}

	@Override
	public Device addDevice(Device device) {
		return deviceRepository.save(device);
	}

	@Override
	public Device deleteDevice(String id) {
		// TODO Auto-generated method stub
		Device device =getDevicebyID(id);
		 deviceRepository.delete(device);
		 
		 return device;
	}

	@Override
	public Device updateDevice(String id, Device device) {
		getDevicebyID(id);
		return deviceRepository.save(device);
		 
	}

	@Override
	public List<Device> getDevicesbyUserID(String userId) {
		// TODO Auto-generated method stub
		Device device=new Device();
		device.setUserId(userId);
		Example<Device> deviceExample=Example.of(device);
		List<Device> results = deviceRepository.findAll(deviceExample);
		return results;
	}

}
