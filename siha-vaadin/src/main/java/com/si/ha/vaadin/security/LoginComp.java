package com.si.ha.vaadin.security;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.si.ha.events.EventBus;
import com.si.ha.vaadin.HomeView;
import com.si.ha.vaadin.MainUI;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class LoginComp extends CustomComponent implements ClickListener {
	private static final Logger logger = Logger.getLogger(LoginComp.class);

	private TextField usernameField = new TextField("Username");
	private PasswordField passwordField = new PasswordField("Password");
	private Button loginButton = new Button("Sign In", FontAwesome.UNLOCK);
	private Label invalidPasswordField = new Label("Invalid username or password");

	@Override
	public void buttonClick(ClickEvent event) {
		Subject currentUser = VaadinSecurityContext.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(usernameField.getValue(), passwordField.getValue());
		try {
			currentUser.login(token);
			EventBus.post(new SuccessfulLoginEvent());
			MainUI.getCurrent().getNavigator().navigateTo(HomeView.NAME);
			
		} catch (Exception e) {
			logger.debug(e);
			usernameField.setValue("");
			passwordField.setValue("");
			invalidPasswordField.setVisible(true);
		}

	}

	public LoginComp() {
		FormLayout l = new FormLayout();
		l.setSizeUndefined();
		l.setSpacing(true);
		usernameField.focus();
		l.addComponent(usernameField);
		usernameField.setRequired(true);
		usernameField.focus();
		l.addComponent(passwordField);
		passwordField.setRequired(true);
		loginButton.addClickListener(this);
		
		l.addComponent(loginButton);
		l.addComponent(invalidPasswordField);
		invalidPasswordField.setVisible(false);
		
		setCompositionRoot(l);
	}

	public static interface LoginListener {
		void onSuccessfulLogin();
	}

}
