package com.si.ha.security;

import groovy.transform.EqualsAndHashCode

import javax.persistence.Entity
import javax.persistence.Table

@EqualsAndHashCode
class User {
	String username
	String password
	List<Role> roles = []
}
