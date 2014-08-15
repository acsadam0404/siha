package com.si.ha.vaadin;

import org.apache.shiro.SecurityUtils;

import com.si.ha.vaadin.security.LoginView;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;

import ru.xpoft.vaadin.DiscoveryNavigator;

public class ShiroDiscoveryNavigator extends DiscoveryNavigator {

	public ShiroDiscoveryNavigator(UI ui, ComponentContainer container) {
		super(ui, container);
	}
	
	@Override
	public void navigateTo(String navigationState) {
		if (LoginView.NAME.equals(navigationState) && SecurityUtils.getSubject().isAuthenticated()) {
			super.navigateTo(HomeView.NAME);
		}
		else if (!SecurityUtils.getSubject().isAuthenticated()) {
			super.navigateTo(LoginView.NAME);
		}
		else {
			super.navigateTo(navigationState);
		}
	}

}
