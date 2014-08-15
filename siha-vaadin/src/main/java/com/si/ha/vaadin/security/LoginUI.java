package com.si.ha.vaadin.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.si.ha.vaadin.HomeView;
import com.si.ha.vaadin.MainUI;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class LoginUI extends UI implements Button.ClickListener{
	private TextField username = new TextField("Username");
	private PasswordField password = new PasswordField("Password");
	private Button loginBtn = new Button("Login", this);
	private Label invalidPassword = new Label("Invalid username or password");




	@Override
	public void buttonClick(ClickEvent event) {
		Subject currentUser = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(
				username.getValue(), password.getValue());
		try {
			currentUser.login(token);
			VaadinService
					.reinitializeSession(VaadinService.getCurrentRequest());
			MainUI.getCurrent().getNavigator().navigateTo(HomeView.NAME);
		}
		catch (Exception e) {
			username.setValue("");
			password.setValue("");
			invalidPassword.setVisible(true);
		}

	}



	@Override
	protected void init(VaadinRequest request) {
		VerticalLayout l = new VerticalLayout();
		username.focus();
		l.addComponent(username);
		l.addComponent(password);
		l.addComponent(loginBtn);
		l.addComponent(invalidPassword);
		invalidPassword.setVisible(false);
		setContent(l);
	}
}
