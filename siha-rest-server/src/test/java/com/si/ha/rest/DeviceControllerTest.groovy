package com.si.ha.rest;

import static groovy.test.GroovyAssert.*
import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

import com.si.ha.rest.device.Device

class DeviceControllerTest extends RestControllerTest {

	@Test
	void findAll() {
		List<Device> devices = template.getForObject(
				"http://localhost:18080/siha/rest/devices",
				List.class);

		assert(!devices?.isEmpty())
	}

	@Test
	void get() {
		ResponseEntity<Device> entity = template.getForEntity(
				"http://localhost:18080/siha/rest/devices/1",
				Device.class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());
		Device device = entity.getBody();
		assert(device)
	}

	@Test
	void create() {
		Device created = template.postForObject("http://acspc.acsadam.hu:18080/siha/rest/devices/", new Device(name:"test"), Device.class)
		assert(created?.name == 'test')
	}
}
