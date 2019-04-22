package com.munshi.device.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.munshi.device.bean.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, String> {
	
}
