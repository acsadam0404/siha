package com.si.ha.vaadin;

import org.springframework.context.annotation.Scope;

import ru.xpoft.vaadin.VaadinView;

import com.google.common.eventbus.Subscribe;
import com.si.ha.events.DeviceCreatedEvent;
import com.si.ha.events.EventBus;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;

@VaadinView(BuilderView.NAME)
@org.springframework.stereotype.Component
@Scope("prototype")
public class BuilderView extends AbstractView {
	public static final String NAME = "builder";
	
	private Label label;

	public BuilderView() {
		EventBus.register(this);
	}
	
	@Subscribe
	public void onDeviceCreation(final DeviceCreatedEvent event) {
		EventBus.post(new PushEvent() {
			
			@Override
			public void run() {
				label.setValue(event.getDevice().getName());
			}
		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		label = new Label("builderlabel");
		setCompositionRoot(label);
	}
}
