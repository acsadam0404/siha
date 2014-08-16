package com.si.ha.device;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class MockDeviceServiceImpl implements DeviceService {
	List devices = [
		new Device(name: 'testdevice1', id: 1),
		new Device(name: 'testdevice2', id: 2)
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
