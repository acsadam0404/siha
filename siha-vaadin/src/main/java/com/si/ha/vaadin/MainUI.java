package com.si.ha.vaadin;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.DiscoveryNavigator;
import ru.xpoft.vaadin.security.ShiroSecurityNavigator;

import com.si.ha.ServiceLocator;
import com.si.ha.vaadin.security.LandingPage;
import com.si.ha.vaadin.security.LoginComp;
import com.si.ha.vaadin.security.LoginComp.LoginListener;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.UIClassSelectionEvent;
import com.vaadin.server.UICreateEvent;
import com.vaadin.server.UIProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("dawn")
@org.springframework.stereotype.Component("MainUI")
@Scope("prototype")
@Widgetset("com.si.ha.AppWidgetSet")
@Push
public class MainUI extends UI implements LoginListener {
	private static final Logger logger = Logger.getLogger(MainUI.class);

	private final VerticalLayout main = new VerticalLayout();
	private final Component menu = createMenu();
	private final VerticalLayout content = new VerticalLayout();

	public MainUI() {
		super();
		Locale locale = new Locale("hu", "HU");
		setLocale(locale);
		VaadinSession.getCurrent().setLocale(locale);
	}

	private Component createMenu() {
		HorizontalLayout l = new HorizontalLayout();
		l.addComponent(new Button("logout", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				SecurityUtils.getSubject().logout();
				VaadinSession.getCurrent().close();
				setContent(new LandingPage(MainUI.this));
			}
		}));
		return l;
	}

	@Override
	protected void init(VaadinRequest request) {
		main.setSizeFull();
		main.addStyleName("main-layout");
		setSizeFull();

		setNavigator(new ShiroSecurityNavigator(this, content));

		if (SecurityUtils.getSubject().isAuthenticated()) {
			init();
		}
		else {
			setContent(new LandingPage(this));
		}
	}

	private void init() {
		content.setSizeFull();
		main.addComponent(menu);
		main.addComponent(content);
		setContent(main);
	}

	@Override
	public ShiroSecurityNavigator getNavigator() {
		return (ShiroSecurityNavigator) super.getNavigator();
	}

	@Override
	public void onSuccessfulLogin() {
		init();
	}

}
