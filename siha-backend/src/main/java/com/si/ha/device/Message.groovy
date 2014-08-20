package com.si.ha.device


import groovy.transform.Canonical

@Canonical
class Message {
	
	char messageType
	
	String value
	
	String senderIP
	
}
