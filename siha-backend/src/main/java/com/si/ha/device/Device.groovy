package com.si.ha.device

import groovy.transform.EqualsAndHashCode

import javax.persistence.Entity
import javax.persistence.Table

import com.si.ha.BaseEntity

@Entity
@Table(name = "device")
@EqualsAndHashCode
class Device extends BaseEntity{
	String name
}
