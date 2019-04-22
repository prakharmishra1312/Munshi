package com.munshi.device.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.munshi.device.bean.Device;

@Service
public interface DeviceDAOService {


		Device getDevicebyID(String id);
		List<Device> getDevicesbyUserID(String id);

		Device addDevice(Device device);

		Device deleteDevice(String id);

		Device updateDevice(String id, Device device);
}
