package com.si.ha.device

import org.springframework.web.client.RestTemplate;


import groovy.transform.Canonical

@Canonical
class Profile {

	String generatorUrl
	
	String name
	
	Device device
	
	List<Transition> inputs
	
	List<Transition> outputs
	
	
	String generate() {
		RestTemplate rt = new RestTemplate()
		String generated = rt.getForEntity(generatorUrl, String, [inputs: inputs, outputs: outputs])
		return generated
	}
	
}
