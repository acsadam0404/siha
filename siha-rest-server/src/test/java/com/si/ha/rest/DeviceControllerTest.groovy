package com.si.ha.rest;

import static groovy.test.GroovyAssert.*
import static org.junit.Assert.*

import org.junit.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestTemplate

import com.si.ha.rest.device.Device

class DeviceControllerTest  {

	@Test
	void getOrders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		RestTemplate template = new RestTemplate();

		HttpEntity<String> requestEntity = new HttpEntity<String>("",headers);

		ResponseEntity<Device> entity = template.getForEntity(
				"http://localhost:18080/siha/rest/devices",
				Device.class);

		String path = entity.getHeaders().getLocation().getPath();

		assertEquals(HttpStatus.CREATED, entity.getStatusCode());
		assertTrue(path.startsWith("/siha/rest/devices"));
		Device Device = entity.getBody();

		System.out.println ("The Device name is " + device.name);
		System.out.println ("The Location is " + entity.getHeaders().getLocation());
	}
}
