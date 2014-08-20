package com.si.ha.device


import groovy.transform.Canonical

@Canonical
class Transition {
	Profile sender

	Profile receiver

	char messageType
}
