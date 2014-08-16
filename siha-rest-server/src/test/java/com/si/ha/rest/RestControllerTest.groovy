package com.si.ha.rest;

import org.junit.Before
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate

abstract class RestControllerTest {
	protected HttpHeaders headers
	protected RestTemplate template

	@Before
	void init() {
		template = new RestTemplate();
		template.setMessageConverters([
			new MappingJackson2HttpMessageConverter()
		])
		headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	}
	
}
