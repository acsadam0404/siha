package com.si.ha.rest;

import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

import com.si.ha.device.DeviceService
import com.si.ha.rest.device.Device

@RestController
@RequestMapping("/devices")
class DeviceController {
	private static final Logger logger = Logger.getLogger(DeviceController.class)

	@Autowired
	private DeviceService deviceService

	@RequestMapping(method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	List<Device> getDevices() {
		List devices = deviceService.findAll().collect { d ->
			//TODO lehetne dinamikusan egy transformer metódust hozzáadnia a controllernek a Device osztályhoz
			new Device(name: d.name)
		}
		return devices
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Device> createDevice(@RequestBody Device device, UriComponentsBuilder builder) {

		com.si.ha.device.Device createdDevice = deviceService.create(new com.si.ha.device.Device(name: device.name))

		HttpHeaders headers = new HttpHeaders()
		headers.setLocation(
				builder.path("/rest/devices/{id}")
				.buildAndExpand(createdDevice.id.toString()).toUri())

		return new ResponseEntity<Device>(device, headers, HttpStatus.CREATED)
	}
}