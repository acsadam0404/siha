package com.si.ha.vaadin.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

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
	public static final String NAME = "login";

	private TextField usernameField = new TextField("Username");
	private PasswordField passwordField = new PasswordField("Password");
	private Button loginButton = new Button("Sign In", FontAwesome.UNLOCK);
	private Label invalidPasswordField = new Label("Invalid username or password");

	private LoginListener loginListener;


	@Override
	public void buttonClick(ClickEvent event) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(usernameField.getValue(), passwordField.getValue());
		try {
			currentUser.login(token);
			VaadinService.reinitializeSession(VaadinService.getCurrentRequest());
			loginListener.onSuccessfulLogin();
			MainUI.getCurrent().getNavigator().navigateTo(HomeView.NAME);
			
		} catch (Exception e) {
			usernameField.setValue("");
			passwordField.setValue("");
			invalidPasswordField.setVisible(true);
		}

	}

	public LoginComp(LoginListener loginListener) {
		this.loginListener = loginListener;
		
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
