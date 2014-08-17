package com.si.ha.vaadin;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.security.ShiroSecurityNavigator;

import com.google.common.eventbus.Subscribe;
import com.si.ha.events.EventBus;
import com.si.ha.vaadin.security.LandingPage;
import com.si.ha.vaadin.security.SuccessfulLoginEvent;
import com.si.ha.vaadin.security.VaadinSecurityContext;
import com.vaadin.annotations.JavaScript;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component("MainUI")
@Scope("prototype")
@Theme("dawn")
@Widgetset("com.si.ha.AppWidgetSet")
@Push(value = PushMode.MANUAL, transport = Transport.LONG_POLLING)
public class MainUI extends UI {
	private static final Logger logger = Logger.getLogger(MainUI.class);

	private VerticalLayout main;
	private final VerticalLayout content = new VerticalLayout();

	public MainUI() {
		Locale locale = new Locale("hu", "HU");
		setLocale(locale);
		VaadinSession.getCurrent().setLocale(locale);
		setNavigator(new ShiroSecurityNavigator(this, content));
	}

	@Override
	protected void init(VaadinRequest request) {
		EventBus.register(this);
		setSizeFull();

		if (VaadinSecurityContext.getSubject().isAuthenticated()) {
			init();
		}
		else {
			setContent(new LandingPage());
		}
	}

	@Override
	public void detach() {
		EventBus.unregister(this);
		super.detach();
	}

	private void init() {
		main =  new VerticalLayout();
		main.setSizeFull();
		main.addStyleName("main-layout");
		content.setSizeFull();
		HeaderComp header = new HeaderComp();
		main.addComponent(header);
		main.addComponent(content);
		main.setExpandRatio(header, 0.1f);
		main.setExpandRatio(content, 0.9f);
		setContent(main);
	}

	@Override
	public ShiroSecurityNavigator getNavigator() {
		return (ShiroSecurityNavigator) super.getNavigator();
	}

	@Subscribe
	public void onSuccessfulLogin(SuccessfulLoginEvent event) {
		init();
	}

	/**
	 * EventBus.post(new PushEvent()) -el hívandó
	 */
	@Subscribe
	public void handlePush(final PushEvent event) {
		access(new Runnable() {
			@Override
			public void run() {
				event.run();
				push();
			}
		});

	}

}
