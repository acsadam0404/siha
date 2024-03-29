package com.si.ha.device.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.si.ha.device.Device;

@Service
class MockDeviceServiceImpl implements DeviceService {
	List devices = [
		new Device(IP: 'testdevice1', id: 1),
		new Device(IP: 'testdevice2', id: 2)
	]
	
	@Override
	List<Device> findAll() {
		return devices
	}

	@Override
	Device create(Device device) {
		device.id = devices.size()
		devices << device
		return device
	}
	
	Device get(Long id) {
		int i = id.intValue()
		return devices[i]
	}
}
