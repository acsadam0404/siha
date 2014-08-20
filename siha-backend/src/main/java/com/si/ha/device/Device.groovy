package com.si.ha.device

import groovy.transform.EqualsAndHashCode

import javax.persistence.Entity
import javax.persistence.Table

import com.si.ha.BaseEntity


import groovy.transform.Canonical

@Canonical
@Entity
@Table(name = "device")
class Device extends BaseEntity{
	
	String IP
	
	String outputs
	
	String inputs
}
