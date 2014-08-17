package com.si.ha.vaadin;

import org.springframework.context.annotation.Scope;

import com.si.ha.vaadin.security.LandingPage;
import com.si.ha.vaadin.security.VaadinSecurityContext;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;

@org.springframework.stereotype.Component
@Scope("prototype")
public class HeaderComp extends CustomComponent {

	public HeaderComp() {
		HorizontalLayout l = new HorizontalLayout();
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
