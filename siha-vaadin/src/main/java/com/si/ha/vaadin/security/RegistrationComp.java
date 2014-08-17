package com.si.ha.vaadin.security;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class RegistrationComp extends CustomComponent implements Button.ClickListener {
	private TextField usernameField = new TextField("Username");
	private PasswordField passwordField = new PasswordField("Password");
	private Button registrationButton = new Button("Registration", FontAwesome.BELL);

	public RegistrationComp() {
		FormLayout l = new FormLayout();
		l.addComponent(usernameField);
		usernameField.setRequired(true);
		l.addComponent(passwordField);
		registrationButton.addClickListener(this);
		l.addComponent(registrationButton);
		setCompositionRoot(l);
	}

	@Override
	public void buttonClick(ClickEvent event) {
		System.out.println("remek, mostmár csak csinálni kell itt valamit");
	}
}
