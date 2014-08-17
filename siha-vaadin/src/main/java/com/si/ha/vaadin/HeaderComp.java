package com.si.ha.vaadin;

import com.si.ha.vaadin.security.LandingPage;
import com.si.ha.vaadin.security.VaadinSecurityContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

public class HeaderComp extends CustomComponent {

	public HeaderComp() {
		HorizontalLayout l = new HorizontalLayout();
		l.setSizeFull();
		l.addComponent(new Button("logout", new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				VaadinSecurityContext.getSubject().logout();
				MainUI.getCurrent().setContent(new LandingPage());
			}
		}));
		setCompositionRoot(l);
	}

}
