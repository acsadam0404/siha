package com.si.ha.vaadin.security;

import jcifs.dcerpc.msrpc.netdfs;

import com.si.ha.vaadin.security.LoginComp.LoginListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

public class LandingPage extends CustomComponent {
	public LandingPage() {
		setSizeFull();
		VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		
		TabSheet ts = new TabSheet();
		ts.setSizeUndefined();
		ts.addTab(new LoginComp(), "Sign In");
		ts.addTab(new RegistrationComp(), "Registration");
		
		
		root.addComponent(ts);
		root.setComponentAlignment(ts, Alignment.MIDDLE_CENTER);
		setCompositionRoot(root);
	}
}
