package com.si.ha.device.service

import org.springframework.data.jpa.repository.JpaRepository

import com.si.ha.device.Device;

interface DeviceService {
	List<Device> findAll()
	
	Device create(Device device)
	
	Device get(Long id)
}
