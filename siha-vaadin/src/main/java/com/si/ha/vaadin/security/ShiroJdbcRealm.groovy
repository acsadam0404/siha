package com.si.ha.vaadin.security

import org.apache.shiro.authc.AuthenticationInfo
import org.apache.shiro.authc.AuthenticationToken
import org.apache.shiro.authc.SimpleAuthenticationInfo
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.authz.AuthorizationException
import org.apache.shiro.authz.AuthorizationInfo
import org.apache.shiro.authz.Permission
import org.apache.shiro.authz.SimpleAuthorizationInfo
import org.apache.shiro.realm.AuthorizingRealm
import org.apache.shiro.subject.PrincipalCollection

import com.si.ha.ServiceLocator
import com.si.ha.security.Role
import com.si.ha.security.User
import com.si.ha.security.UserService

class ShiroJdbcRealm extends AuthorizingRealm {
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		User user = ServiceLocator.getBean(UserService.class).findByUsername(upToken.username)
		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}


	/**
	 this function loads user authorization data from "userManager" data source (database)
	 User, Role are custom POJOs (beans) and are loaded from database.
	 WildcardPermission implements shiros Permission interface, so my permissions in database gets accepted by shiro security
	 **/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
		Set<String>	roles= new HashSet<String>();
		Collection<User> principalsList	= principals.byType(User.class);

		if (principalsList.isEmpty()) {
			throw new AuthorizationException("Empty principals list!");
		}
		for (User userPrincipal : principalsList) {
			try {

				User user = ServiceLocator.getBean(UserService).findByUsername(userPrincipal.name);

				user.roles?.each { r ->
					roles.add(r.name)
				}
			} catch (Exception ex) {
				throw new AuthorizationException(ex);
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		info.setRoles(roles);

		return info;
	}
}
