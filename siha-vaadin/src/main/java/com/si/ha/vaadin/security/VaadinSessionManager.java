package com.si.ha.vaadin.security;

import java.util.UUID;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.*;

import com.vaadin.server.VaadinSession;

/**
 * A {@link SessionManager} implementation that uses the {@link VaadinSession} for the current user to persist and locate the Shiro {@link Session}. This tightly ties the Shiro security Session
 * lifecycle to that of the VaadinSession allowing expiration, persistence, and clustering to be handled only in the Vaadin configuration rather than be duplicated in both the Vaadin and Shiro
 * configuration.
 * 
 * @author mpilone
 * http://mikepilone.blogspot.hu/2013/07/vaadin-shiro-and-push.html
 * 
 */
public class VaadinSessionManager implements SessionManager {

	/**
	 * The session attribute name prefix used for storing the Shiro Session in the VaadinSession.
	 */
	private final static String SESSION_ATTRIBUTE_PREFIX = VaadinSessionManager.class.getName() + ".session.";

	/**
	 * The session factory used to create new sessions. In the future, it may make more sense to simply implement a {@link Session} that is a lightweight wrapper on the {@link VaadinSession} rather
	 * than storing a {@link SimpleSession} in the {@link VaadinSession}. However by using a SimpleSession, the security information is contained in a neat bucket inside the overall VaadinSession.
	 */
	private SessionFactory sessionFactory;

	/**
	 * Constructs the VaadinSessionManager.
	 */
	public VaadinSessionManager() {
		sessionFactory = new SimpleSessionFactory();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.session.mgt.SessionManager#start(org.apache.shiro.session .mgt.SessionContext)
	 */
	@Override
	public Session start(SessionContext context) {

		// Retrieve the VaadinSession for the current user.
		VaadinSession vaadinSession = VaadinSession.getCurrent();

		// Assuming security is used within a Vaadin application, there should
		// always be a VaadinSession available.
		if (vaadinSession == null) {
			throw new IllegalStateException("Unable to locate VaadinSession " + "to store Shiro Session.");
		}

		// Create a new security session using the session factory.
		SimpleSession shiroSession = (SimpleSession) sessionFactory.createSession(context);

		// Assign a unique ID to the session now because this session manager
		// doesn't use a SessionDAO for persistence as it delegates to any
		// VaadinSession configured persistence.
		shiroSession.setId(UUID.randomUUID().toString());

		// Put the security session in the VaadinSession. We use the session's ID as
		// part of the key just to be safe so we can double check that the security
		// session matches when it is requested in getSession.
		vaadinSession.setAttribute(SESSION_ATTRIBUTE_PREFIX + shiroSession.getId(), shiroSession);

		return shiroSession;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.shiro.session.mgt.SessionManager#getSession(org.apache.shiro .session.mgt.SessionKey)
	 */
	@Override
	public Session getSession(SessionKey key) throws SessionException {

		// Retrieve the VaadinSession for the current user.
		VaadinSession vaadinSession = VaadinSession.getCurrent();

		String attributeName = SESSION_ATTRIBUTE_PREFIX + key.getSessionId();

		if (vaadinSession != null) {
			// If we have a valid VaadinSession, try to get the Shiro Session.
			SimpleSession shiroSession = (SimpleSession) vaadinSession.getAttribute(attributeName);

			if (shiroSession != null) {

				// Make sure the Shiro Session hasn't been stopped or expired (i.e. the
				// user logged out).
				if (shiroSession.isValid()) {
					return shiroSession;
				} else {
					// This is an invalid or expired session so we'll clean it up.
					vaadinSession.setAttribute(attributeName, null);
				}
			}
		}

		return null;
	}
}