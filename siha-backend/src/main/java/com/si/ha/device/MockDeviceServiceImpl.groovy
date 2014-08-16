package com.si.ha.device;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
class MockDeviceServiceImpl implements DeviceService {

	@Override
	List<Device> findAll() {
		[
			new Device(name: 'testdevice1'), 
			new Device(name: 'testdevice2')
		]
	}


}
