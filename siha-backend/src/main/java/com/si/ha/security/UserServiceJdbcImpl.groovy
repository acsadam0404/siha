package com.si.ha.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserServiceJdbcImpl implements UserService {
	@Override
	User findByUsername(String username) {
		return new User(username: 'user', password: 'user', roles: ['user'])
	}
	
}
